package Logic;

import java.text.SimpleDateFormat;

public class ConstantsEnums {

     public enum FileType {
         NONE,
         FILE,
         FOLDER
    }

    public enum FileState{
         CREATED,
        DELETED,
        CHANGED
    }

    public static final String EmptyString = "";

     public static SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy-hh:mm:ss:sss");

}
