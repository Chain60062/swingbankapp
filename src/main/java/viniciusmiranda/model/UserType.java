package viniciusmiranda.model;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public enum UserType 
{
    CLIENT(1),
    EMPLOYEE(2),
    MANAGER(3),
    DIRECTOR(4);

     private static final Map<Integer,UserType> lookup 
          = new HashMap<>();

     static {
          for(UserType w : EnumSet.allOf(UserType.class))
               lookup.put(w.getCode(), w);
     }

     private int code;

     private UserType(int code) {
          this.code = code;
     }

     public int getCode() { return code; }

     public static UserType get(int code) { 
          return lookup.get(code); 
     }
}