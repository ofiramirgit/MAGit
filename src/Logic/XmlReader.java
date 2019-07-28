package Logic;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class XmlReader {
//    File xmlFile = new File("C:\\Users\\OL\\Desktop\\Java Course\\EX1\\ex1-blob.xml");
    private static final String JAXB_XML_GAME_PACKAGE_NAME = "logic";

    InputStream inputStream;

    private JAXBContext context;

    public XmlReader(String i_XMLLocation){
        try {
            inputStream = new FileInputStream("C:\\Users\\OL\\Desktop\\Java Course\\EX1\\ex1-blob.xml");
            Repo repo1 = deserializeFrom(inputStream);
            System.out.println(repo1);
        }catch(JAXBException | FileNotFoundException e ) {
            e.printStackTrace();
        }
    }

    private Repo deserializeFrom(InputStream in) throws JAXBException {
        JAXBContext jc = JAXBContext.newInstance(JAXB_XML_GAME_PACKAGE_NAME);
        Unmarshaller u = jc.createUnmarshaller();
        return (Repo) u.unmarshal(in);
    }
}

//    private static void fromXmlFileToObject() {
//        System.out.println("\nFrom File to Object");
//
//        try {
//
//            File file = new File(FILE_NAME);
//            JAXBContext jaxbContext = JAXBContext.newInstance(Customer.class);
//
//            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
//            Customer customer = (Customer) jaxbUnmarshaller.unmarshal(file);
//            System.out.println(customer);
//
//        } catch (JAXBException e) {
//            e.printStackTrace();
//        }
//
//    }

class Repo{
//    private String m_RepositoryName;
    private String location;

    @Override
    public String toString() {
        return "Repo{" +
                "location='" + location + '\'' +
                '}';
    }
}
