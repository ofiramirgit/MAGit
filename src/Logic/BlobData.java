package Logic;
import java.util.*;
import  java.lang.*;

public class BlobData {

    private String m_Name;
    private long m_Sha1;
    private ConstantsEnums.FileType m_Type;
    private String m_ChangedBy;
    private Date  m_Date;

    public String toString()
    {
        String str;
        str = m_Name + ", " + m_Sha1 + ", " + m_Type + ", " + m_ChangedBy + ", ";
        return str;
    }

}
