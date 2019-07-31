package Logic;
import com.sun.javafx.scene.control.skin.VirtualFlow;
import org.apache.commons.codec.digest.DigestUtils;

import java.util.ArrayList;
import java.util.List;

import static Logic.ConstantsEnums.EmptyString;

public class Folder
{
    private List<BlobData> LibraryFiles;

    public void setLibraryFiles(List<BlobData> libraryFiles) {
        LibraryFiles = libraryFiles;
    }

    private Boolean rootFolder = false;

    public Folder() {
        LibraryFiles = new ArrayList<BlobData>();
    }

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

    @Override
    public String toString() {
        return "Folder{" +
                "LibraryFiles=" + LibraryFiles +
                ", rootFolder=" + rootFolder +
                '}';
    }


    public String printArray() {
        Integer index=0;
        String ArrayString=EmptyString;
        for(BlobData blob : LibraryFiles)
        {
            ArrayString += blob.getM_Sha1() + "\n";
        }
        return ArrayString;
    }

}

