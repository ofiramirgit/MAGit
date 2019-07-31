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


    public BlobData(String m_Name, String m_Sha1, FileType m_Type, String m_ChangedBy, String m_Date) {
        this.m_Name = m_Name;
        this.m_Sha1 = m_Sha1;
        this.m_Type = m_Type;
        this.m_ChangedBy = m_ChangedBy;
        this.m_Date = m_Date;
    }

    public String getM_Sha1() {
        return m_Sha1;
    }

    @Override
    public String toString()
    {
        String str = m_Name + ", " + m_Sha1 + ", "
                    + m_Type.toString() + ", " +
                    m_ChangedBy + ", " + m_Date ;
        return str;
    }

   /* public String CompareSha1()
    {
        //if the content of file with the same sha1 is changed so sha1 should change.

        //if(blob changed){
            if exist check if changed
            check with the last commit that saved in head branch. if all file exist or if its new file
            add to array of blob changed
            //return toString();

    }*/

}
