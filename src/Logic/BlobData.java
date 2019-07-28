package Logic;
import java.util.*;
import  java.lang.*;

import static Logic.ConstantsEnums.*;
import Logic.ConstantsEnums.*;

import javax.xml.bind.annotation.*;

public class BlobData {
    private String m_Id = EmptyString;
    private String m_Name = EmptyString;
    private String m_Sha1 = EmptyString;
    private FileType m_Type = FileType.NONE;
    private String m_ChangedBy = EmptyString;
    private String m_Date;
    private Boolean m_Changed = false;

    @Override
    public String toString()
    {
        String str = m_Name + ", " + m_Sha1 + ", "
                    + m_Type.toString() + ", " +
                    m_ChangedBy + ", " + m_Date.toString() ;
        return str;
    }

}
