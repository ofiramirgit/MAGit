package UI;

public class Menu {

    public void printMenu()
    {
        System.out.print(
                        "-1. Change username\n"+
                        "0.  Load from XML\n"+
                        "1.  Working copy status\n"+
                        "2.  Show current branch history\n"+
                        "3.  Show current commit file system information\n"+
                        "4.  Commit\n"+
                        "5.  Create new branch\n"+
                        "6.  List available branches\n"+
                        "7.  Check out branch\n"+
                        "8.  Export repository to XML\n"+
                        "9.  Switch repository\n"+
                        "10. Merge with branch\n"+
                        "11. Exit\n"+
                                "12.Bonus1\n"
        );
    }

    public void printInfoMenu(String i_CurrentUser, String i_RepositoryLocation)
    {
        System.out.println("Magit Menu      " +
                "Current User: " + i_CurrentUser +
                "       Current repository location " + i_RepositoryLocation);
    }

}
