package Logic;
import java.util.List;

public class Folder
{
    private List<BlobData> LibraryFiles;
    private Boolean rootFolder = false;

    public void setRootFolder(Boolean rootFolder) {
        this.rootFolder = rootFolder;
    }

    public Boolean getRootFolder() {
        return rootFolder;
    }

    public void AddNewItem(BlobData blobData)
    {
        LibraryFiles.add(blobData);
    }
}

