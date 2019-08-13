package Logic;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class InputValidation
{
    public Boolean checkInputStringLen(String i_String)
    {
        if(i_String.length() == 0 || i_String.length() > 50)
        {
            return false;
        }
        return true;
    }

    public Boolean checkInputActiveRepository(String i_ActiveRepository)
    {
        Path RepositoryPath = Paths.get(i_ActiveRepository  + File.separator + ".magit");
        return Files.exists(RepositoryPath);
    }
    public Boolean makeCommitToOpenChanges(String i_makeCommit)
    {
        i_makeCommit= i_makeCommit.toLowerCase();
        if(i_makeCommit.equals("y") || i_makeCommit.equals("n"))
            return true;
        else
            return false;

    }

    public Boolean isbranchExist(String branchName,String PathBranchFileString) {
        Path PathBranchFile = Paths.get(PathBranchFileString);
        return Files.exists(PathBranchFile);
    }
}
