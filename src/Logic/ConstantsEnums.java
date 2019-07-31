package Logic;

import java.text.SimpleDateFormat;

public class ConstantsEnums {

     public enum FileType {
         NONE,
         FILE,
         FOLDER
    }

    public static final String EmptyString = "";

     public static SimpleDateFormat dateFormat = new SimpleDateFormat("dd.mm.yyyy-hh:mm:ss:sss");

}
