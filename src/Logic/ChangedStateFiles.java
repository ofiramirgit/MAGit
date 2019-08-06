package Logic;

public class ChangedStateFiles {
    private String m_fileName;
    private ConstantsEnums.FileState m_fileState;

    public ChangedStateFiles(String m_fileName, ConstantsEnums.FileState m_fileState) {
        this.m_fileName = m_fileName;
        this.m_fileState = m_fileState;
    }

    public String getM_fileName() {
        return m_fileName;
    }

    public ConstantsEnums.FileState getM_fileState() {
        return m_fileState;
    }
}
