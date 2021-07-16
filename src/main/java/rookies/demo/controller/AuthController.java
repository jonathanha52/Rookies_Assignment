package rookies.demo.controller;

import java.util.List;
import java.util.stream.Collectors;
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

//import io.swagger.v3.oas.annotations.parameters.RequestBody;
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
import rookies.demo.repository.UsersRepository;
import rookies.demo.security.jwt.JwtUtils;
import rookies.demo.security.service.UserDetailsImpl;

@CrossOrigin(origins="*", maxAge = 3600)
@RestController
@RequestMapping("api/v1/auth")
public class AuthController {
    
    final private AuthenticationManager authenticationManager;
    final private UsersRepository usersRepository;
    final private RoleRepository roleRepository;
    final private PasswordEncoder encoder;
    final private JwtUtils jwtUtils;

    public AuthController (AuthenticationManager authenticationManager, UsersRepository usersRepository,
        RoleRepository roleRepository, PasswordEncoder encoder, JwtUtils jwtUtils){
            this.authenticationManager = authenticationManager;
            this.usersRepository = usersRepository;
            this.roleRepository = roleRepository;
            this.encoder = encoder;
            this.jwtUtils = jwtUtils;
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
        if (usersRepository.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity
                .badRequest()
                .body(new MessageResponse("Error: Username is already taken!"));
        }

        if (usersRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity
                .badRequest()
                .body(new MessageResponse("Error: Email is already in use!"));
        }

        // Create new user's account
        Users user = new Users();
        user.setEmail(signUpRequest.getEmail());
        user.setFirstName(signUpRequest.getFirstName());
        user.setLastName(signUpRequest.getLastName());
        user.setPassword(signUpRequest.getPassword());
        user.setUsername(encoder.encode(signUpRequest.getPassword()));
        String strRoles = signUpRequest.getRole();
        System.out.println(strRoles);
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
        usersRepository.save(user);

        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }
}
