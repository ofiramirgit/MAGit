package Logic.Objects;

import static Logic.ConstantsEnums.EmptyString;

public class BranchData {

    private String m_BranchName = EmptyString;
    private Boolean m_IsActive = false;
    private String m_CommitSha1 = EmptyString;
    private String m_CommitMessage = EmptyString;

    public BranchData(String i_BranchName,Boolean i_IsActive,String i_CommitSha1, String i_CommitMessage)
    {
        m_BranchName = i_BranchName;
        m_IsActive =i_IsActive;
        m_CommitSha1 =i_CommitSha1;
        m_CommitMessage =i_CommitMessage;
    }

    public String getM_BranchName() {
        return m_BranchName;
    }
    public Boolean getM_IsActive() {
        return m_IsActive;
    }
    public String getM_CommitSha1() {
        return m_CommitSha1;
    }
    public String getM_CommitMessage() {
        return m_CommitMessage;
    }
}