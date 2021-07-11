package rookies.demo.utils;

import com.fasterxml.jackson.databind.ObjectMapper;

public class ConvertJSONString {
    public static String ObjToJSON(final Object obj){
        try{
            return new ObjectMapper().writeValueAsString(obj);
        } catch(Exception e){
            throw new RuntimeException(e);
        }
    }
}
