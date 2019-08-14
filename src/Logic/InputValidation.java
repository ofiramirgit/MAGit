package Logic;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class InputValidation
{
    public Boolean checkInputStringLen(String i_String) {
        return (!(i_String.length()==0) && !(i_String.length() > 50));
    }
    public Boolean checkInputActiveRepository(String i_ActiveRepository) {
        Path RepositoryPath = Paths.get(i_ActiveRepository  + File.separator + ".magit");
        return Files.exists(RepositoryPath);
    }
    public Boolean makeCommitToOpenChanges(String i_makeCommit) {
        i_makeCommit = i_makeCommit.toLowerCase();
        return (i_makeCommit.equals("y") || i_makeCommit.equals("n"));
    }
    public Boolean isBranchExist(String branchName,String PathBranchFileString) {
        Path PathBranchFile = Paths.get(PathBranchFileString);
        return Files.exists(PathBranchFile);
    }
    public boolean validSha1(String i_Sha1Input) {
        return (i_Sha1Input.length() == 40);
    }

    public Boolean validOptionXmlRepositoryExist(String i_Option) {
        return(i_Option.equals("1") || i_Option.equals("2"));
    }
}