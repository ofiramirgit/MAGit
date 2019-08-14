package Logic.Objects;

import static Logic.ConstantsEnums.EmptyString;

public class CommitData {
    private String m_SHA1 = EmptyString;
    private String m_Message = EmptyString;
    private String m_CreatedTime = EmptyString;
    private String m_CreatedBy = EmptyString;

    public CommitData(String m_SHA1, String m_Message, String m_CreatedTime, String m_CreatedBy) {
        this.m_SHA1 = m_SHA1;
        this.m_Message = m_Message;
        this.m_CreatedTime = m_CreatedTime;
        this.m_CreatedBy = m_CreatedBy;
    }

    public String getM_SHA1() {
        return m_SHA1;
    }
    public String getM_Message() {
        return m_Message;
    }
    public String getM_CreatedTime() {
        return m_CreatedTime;
    }
    public String getM_CreatedBy() {
        return m_CreatedBy;
    }
}