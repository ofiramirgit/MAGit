package Logic;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static Logic.ConstantsEnums.EmptyString;

public class InputValidation
{
    public Boolean checkInputStringLen(String i_String) {
        return (!(i_String.length()==0) && !(i_String.length() > 50));
    }
    public Boolean checkInputActiveRepository(String i_ActiveRepository) {
        Path RepositoryPath = Paths.get(i_ActiveRepository  + File.separator + ".magit");
        return Files.exists(RepositoryPath);
    }
    public Boolean inputYesNo(String i_makeCommit) {
        i_makeCommit = i_makeCommit.toLowerCase();
        return (i_makeCommit.equals("y") || i_makeCommit.equals("n"));
    }
    public Boolean isBranchExist(String PathBranchesNames,String BranchName) {
        File BranchNamesFile = new File(PathBranchesNames);
        String []ArrNames = getContentOfFile(BranchNamesFile).split(System.lineSeparator());
        for(String name:ArrNames) {
            if (name.equals(BranchName))
                return true;
        }
        return false;
    }
    public boolean validSha1(String i_Sha1Input) {
        return (i_Sha1Input.length() == 40);
    }

    public Boolean validOptionXmlRepositoryExist(String i_Option) {
        return(i_Option.equals("1") || i_Option.equals("2"));
    }
    private String getContentOfFile(File i_File) {
        String fileContent = EmptyString;
        Path path = Paths.get(i_File.getAbsolutePath());

        try {
            fileContent=EmptyString;
            if(path.toFile().length()>0)
                fileContent = new String(Files.readAllBytes(path));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return fileContent;
    }
}
