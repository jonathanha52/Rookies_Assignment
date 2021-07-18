package rookies.demo.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import rookies.demo.model.Roles;
import rookies.demo.model.RoleName;
import static rookies.demo.utils.ConvertJSONString.ObjToJSON;;

public class ConvertToJSONTest {
    private final String TEST_JSON = "{\"id\":1,\"roleName\":\"ADMIN\"}";
    private final Roles TEST_ROLE = new Roles(1, RoleName.ADMIN);
    @Test
    public void testConvertObjToJson(){
        String result = ObjToJSON(TEST_ROLE);
        System.out.println(result);
        assertEquals(TEST_JSON, result);
    }
    
}
