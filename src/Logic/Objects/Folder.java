package Logic.Objects;
import java.util.ArrayList;
import java.util.List;

import static Logic.ConstantsEnums.EmptyString;

public class Folder
{
    private List<BlobData> LibraryFiles;
    private Boolean rootFolder = false;

    public Folder() {
        LibraryFiles = new ArrayList<>();
    }
    public Folder(String i_FolderContent) {
        String[] blobDataStrings = i_FolderContent.split(" ~ ");
        LibraryFiles = new ArrayList<>();
        for(String blobData : blobDataStrings)
        {
            LibraryFiles.add(new BlobData(blobData));
        }
    }

    public List<BlobData> getLibraryFiles() {
        return LibraryFiles;
    }
    public String printArray() {
        String ArrayString=EmptyString;
        for(BlobData blob : LibraryFiles)
            ArrayString += blob.toString() + " ~ ";
        ArrayString = ArrayString.substring(0, ArrayString.length() - 3);
        return ArrayString;
    }
    public void AddNewItem(BlobData blobData) {
        LibraryFiles.add(blobData);
    }

    @Override
    public String toString() {
        return "Folder{" +
                "LibraryFiles=" + LibraryFiles +
                ", rootFolder=" + rootFolder +
                '}';
    }

}

