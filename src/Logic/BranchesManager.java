package Logic;

import Logic.Objects.Branch;

import java.util.ArrayList;
import java.util.List;

import static Logic.ConstantsEnums.EmptyString;

public class BranchesManager {
    List<Branch> BranchArray;

    public BranchesManager() {
        BranchArray = new ArrayList<Branch>();
    }

    public String getSha1ofBranchByName(String i_BranchName)
    {
        String BranchSha1 =EmptyString;
        for(Branch branch : BranchArray)
        {
            if(branch.getName().equals(i_BranchName))
            {
                BranchSha1 = branch.getSH1_Commit();
            }
        }
        if(BranchSha1.equals(EmptyString)) {
            //error: Branch Not Found
        } else {

            return BranchSha1;
        }
        return BranchSha1;//forTesting
    }
}
