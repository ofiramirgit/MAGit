package Logic.Objects;
import Logic.Objects.BlobData;

import java.util.ArrayList;
import java.util.List;

import static Logic.ConstantsEnums.EmptyString;

public class Folder
{
    private List<BlobData> LibraryFiles;
    private Boolean rootFolder = false;


    public void setLibraryFiles(List<BlobData> libraryFiles) {
        LibraryFiles = libraryFiles;
    }

    public List<BlobData> getLibraryFiles() {
        return LibraryFiles;
    }

    public Folder() {
        LibraryFiles = new ArrayList<BlobData>();
    }

    public Folder(String i_FolderContent)
    {
        String[] blobDataStrings = i_FolderContent.split(" ~ ");
        LibraryFiles = new ArrayList<BlobData>();
        for(String blobData : blobDataStrings)
        {
            LibraryFiles.add(new BlobData(blobData));
        }
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

    /* public String CompareSha1()
    {
        //if the content of one of the blobData changed so sha1 should change.
        for(BlobData blob : LibraryFiles)
        {
         //if(blob changed)
            //return toString();
        }
    }*/


    public String printArray() {
        Integer index=0;
        String ArrayString=EmptyString;
        for(BlobData blob : LibraryFiles)
            ArrayString += blob.toString()+" ~ ";
        ArrayString = ArrayString.substring(0, ArrayString.length() - 3);
        return ArrayString;
    }

}

