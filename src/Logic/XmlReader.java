package Logic;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;

import GeneratedXML.MagitRepository;


public class XmlReader {
    private static final String JAXB_XML_GAME_PACKAGE_NAME = "GeneratedXML";

    InputStream inputStream;

    private JAXBContext context;

    public XmlReader(String i_XMLLocation){
        try {
            inputStream = new FileInputStream("C:\\Users\\OL\\Desktop\\Java Course\\EX1\\ex1-small.xml");
            MagitRepository magitRepository = deserializeFrom(inputStream);
            //printRep(magitRepository);
            BuildRepository(magitRepository);
        }catch(JAXBException | FileNotFoundException e ) {
            e.printStackTrace();
        }
    }

    private MagitRepository deserializeFrom(InputStream in) throws JAXBException {
        JAXBContext jc = JAXBContext.newInstance(JAXB_XML_GAME_PACKAGE_NAME);
        Unmarshaller u = jc.createUnmarshaller();
        return (MagitRepository)u.unmarshal(in);
    }

    private void printRep(MagitRepository i_MagitRepository)
    {
        System.out.println(i_MagitRepository.getLocation());

        System.out.println(i_MagitRepository.getName());

        for(MagitRepository.MagitBlobs.MagitBlob b : i_MagitRepository.getMagitBlobs().getMagitBlob())
        {
            System.out.println(b.getId());
            System.out.println(b.getName());
            System.out.println(b.getLastUpdater());
            System.out.println(b.getLastUpdateDate());
            System.out.println(b.getContent());
        }
    }
    private void BuildRepository(MagitRepository i_MagitRepository) {
        for(MagitRepository.MagitFolders.MagitSingleFolder F : i_MagitRepository.getMagitFolders().getMagitSingleFolder())
        {
            System.out.println("-------Folder "+F.getId()+"--------");
            System.out.println("Id:               " + F.getId());
            System.out.println("Last Update Date: " + F.getLastUpdateDate());
            System.out.println("Last Updater:     " + F.getLastUpdater());
            System.out.println("Name:             " + F.getName());
//            MagitRepository.MagitFolders.MagitSingleFolder.Items blobs = F.getItems();
            for(MagitRepository.MagitFolders.MagitSingleFolder.Items.Item item : F.getItems().getItem()) {
                System.out.println("    ---item "+item.getId()+"---------");
                System.out.println("    Type:         " + item.getType());
            }
            System.out.print("\n");

        }

    }

}