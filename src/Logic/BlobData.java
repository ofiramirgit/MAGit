package Logic;
import java.util.*;
import  java.lang.*;

public class BlobData {

    private String m_Name;
    private Long m_Sha1;
    private ConstantsEnums.FileType m_Type;
    private String m_ChangedBy;
    private Date  m_Date;

    @Override
    public String toString()
    {
        String str = m_Name + ", " + m_Sha1 + ", "
                    + m_Type.toString() + ", " +
                    m_ChangedBy + ", " + m_Date.toString() ;
        return str;
    }

}
