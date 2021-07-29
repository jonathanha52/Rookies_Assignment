package rookies.demo.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.RequestBody;
import rookies.demo.exception.RoleException.RoleNameNotFoundException;
import rookies.demo.model.RoleName;
import rookies.demo.model.Roles;
import rookies.demo.model.Users;
import rookies.demo.payload.request.LoginRequest;
import rookies.demo.payload.request.SignupRequest;
import rookies.demo.payload.response.JwtResponse;
import rookies.demo.payload.response.MessageResponse;
import rookies.demo.repository.RoleRepository;
import rookies.demo.security.jwt.JwtAuthTokenFilter;
import rookies.demo.security.jwt.JwtUtils;
import rookies.demo.security.service.UserDetailsImpl;
import rookies.demo.service.impl.UserService;

@CrossOrigin(origins="*", maxAge = 3600)
@RestController
@RequestMapping("api/v1/auth")
public class AuthController {
    
    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final RoleRepository roleRepository;
    private final PasswordEncoder encoder;
    private final JwtUtils jwtUtils;
    private final JwtAuthTokenFilter jwtAuthTokenFilter;

    public AuthController (AuthenticationManager authenticationManager, UserService userService,
        RoleRepository roleRepository, PasswordEncoder encoder, JwtUtils jwtUtils, JwtAuthTokenFilter jwtAuthTokenFilter){
            this.authenticationManager = authenticationManager;
            this.userService = userService;
            this.roleRepository = roleRepository;
            this.encoder = encoder;
            this.jwtUtils = jwtUtils;
            this.jwtAuthTokenFilter = jwtAuthTokenFilter;
        }
    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest){
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        // if go there, the user/password is correct
        SecurityContextHolder.getContext().setAuthentication(authentication);
        // generate jwt to return to client
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
            .map(item -> item.getAuthority())
            .collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponse(jwt,
                                                 userDetails.getId(),
                                                 userDetails.getUsername(),
                                                 userDetails.getEmail(),
                                                 roles));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        if (this.userService.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity
                .badRequest()
                .body(new MessageResponse("Error: Username is already taken!"));
        }

        if (this.userService.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity
                .badRequest()
                .body(new MessageResponse("Error: Email is already in use!"));
        }

        // Create new user's account
        Users user = new Users();
        user.setEmail(signUpRequest.getEmail());
        user.setFirstName(signUpRequest.getFirstName());
        user.setLastName(signUpRequest.getLastName());
        user.setPassword(encoder.encode(signUpRequest.getPassword()));
        user.setUsername(signUpRequest.getUsername());
        String strRoles = signUpRequest.getRole();
        Roles userRole;
        if (strRoles == null) {
            userRole = roleRepository.findByName(RoleName.CUSTOMER)
                .orElseThrow(() -> new RoleNameNotFoundException(RoleName.CUSTOMER.name()));
        } else {
                switch (strRoles.toLowerCase()) {
                    case "admin":
                        userRole = roleRepository.findByName(RoleName.ADMIN)
                            .orElseThrow(() -> new RoleNameNotFoundException(RoleName.ADMIN.name()));

                        break;
                    default:
                        userRole = roleRepository.findByName(RoleName.CUSTOMER)
                            .orElseThrow(() -> new RoleNameNotFoundException(RoleName.CUSTOMER.name()));
                
            };
        }

        user.setRole(userRole);
        this.userService.insertUser(user);

        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }

    @PostMapping("/signout")
    public ResponseEntity<?> signout(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String jwt = jwtAuthTokenFilter.parseJwt(request);
        if (jwt != null && jwtUtils.blacklistingJwt(jwt)){
            return ResponseEntity.ok(new MessageResponse("Loggout successfully"));
        }
        return ResponseEntity.badRequest().body(new MessageResponse("There is a problem with JWT token"));
    }
}
