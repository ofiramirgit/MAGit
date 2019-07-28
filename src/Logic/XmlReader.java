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
            inputStream = new FileInputStream("C:\\Users\\OL\\Desktop\\Java Course\\EX1\\ex1-blob.xml");
            MagitRepository magitRepository = deserializeFrom(inputStream);
            printRep(magitRepository);
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
}