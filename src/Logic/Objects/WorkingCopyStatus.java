package Logic.Objects;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class WorkingCopyStatus
{
    private Set<String> m_DeletedFilesList;
    private List<String> m_ChangedFilesList = new ArrayList<>();
    private List<String> m_NewFilesList = new ArrayList<>();


    public Set<String> getM_DeletedFilesList() {
        return m_DeletedFilesList;
    }

    public void setM_DeletedFilesList(Set<String> m_DeletedFilesList) {
        this.m_DeletedFilesList = m_DeletedFilesList;
    }

    public List<String> getM_ChangedFilesList() {
        return m_ChangedFilesList;
    }

    public void setM_ChangedFilesList(List<String> m_ChangedFilesList) {
        this.m_ChangedFilesList = m_ChangedFilesList;
    }

    public List<String> getM_NewFilesList() {
        return m_NewFilesList;
    }

    public void setM_NewFilesList(List<String> m_NewFilesList) {
        this.m_NewFilesList = m_NewFilesList;
    }

    public Boolean isChanged()
    {
        if(m_ChangedFilesList.size()==0 && m_NewFilesList.size()==0 && m_DeletedFilesList.isEmpty())
            return false;
        return true;
    }
}
