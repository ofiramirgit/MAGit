//package Logic;
//import javax.xml.bind.JAXBContext;
//import javax.xml.bind.JAXBException;
//import javax.xml.bind.Unmarshaller;
//import java.io.*;
//import java.nio.charset.Charset;
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.nio.file.Paths;
//import java.util.List;
//import java.util.zip.ZipEntry;
//import java.util.zip.ZipOutputStream;
//
//import GeneratedXML.MagitRepository;
//import org.apache.commons.codec.digest.DigestUtils;
//
//
//public class XmlReader {
//    private static final String JAXB_XML_GAME_PACKAGE_NAME = "GeneratedXML";
//
//    private InputStream inputStream = null;
//    private MagitRepository magitRepository;
//
//    public XmlReader(String i_XMLLocation){
//        try {
//            inputStream = new FileInputStream(i_XMLLocation);
//        }catch(FileNotFoundException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void buildFromXML()
//    {
//        try {
//        magitRepository = deserializeFrom(inputStream);
//        }catch(JAXBException  e ) {
//            e.printStackTrace();
//        }
//
//        initRepository();
//
//        buildFromMagitRepository();
//
//        // BuildRepository(magitRepository);
//    }
//
//
//    public void buildFromMagitRepository()
//    {
//        buildBlobs(magitRepository.getMagitBlobs());
//    }
//
//
//
//    private void foldersBuilder()
//    {
//        Folder mainRepository = new Folder();
//        //mainRepository.setRootFolder(true);
//
//        for(MagitRepository.MagitFolders.MagitSingleFolder folder : magitRepository.getMagitFolders().getMagitSingleFolder())
//        {
//            if(folder.getIsRoot())
//            {
//
//            }
//        }
//
//            for(MagitRepository.MagitFolders.MagitSingleFolder folder : magitRepository.getMagitFolders().getMagitSingleFolder())
//      {
//          mainRepository.AddNewItem(rec(folder));
//      }
//    }
//
//    private BlobData rec(Object object)
//    {
//        switch (object)
//        {
//            case (instanceof MagitRepository.MagitCommits.MagitSingleCommit)
//                break;
//
//        }
//
//
//
//
//        if(isFolderExist(folder))
//        {
//
//        }
//        else
//        {
//            for(MagitRepository.MagitFolders.MagitSingleFolder.Items.Item item : folder.getItems().getItem())
//            {
//                if(item.getType().equals("blob"))
//                {
//                     if(isBlobExist(item.getId().toString()))
//                     {
//
//                     }
//                    else
//                     {
//                         folder.createBlob();
//                     }
//                }
//                else if(item.getType().equals("folder"))
//                {
//
//                }
//            }
//        }
//    }
//
//    private  Boolean isFolderExist(MagitRepository.MagitFolders.MagitSingleFolder folder)
//    {
//        return false;
//    }
//
//    private  Boolean isBlobExist(String blobId)
//    {
//        return false;
//    }
//
//
//    private void buildBlobs(MagitRepository.MagitBlobs blobs)
//    {
//        File file;
//        String sha1;
//        InputStream inputStreamBlob;
//        for(MagitRepository.MagitBlobs.MagitBlob blob : magitRepository.getMagitBlobs().getMagitBlob()) {
//            try {
//                inputStreamBlob = new ByteArrayInputStream(blob.getContent().getBytes(Charset.forName("UTF-8")));
//                String outputFile = "C:\\magit\\" +DigestUtils.sha1Hex(blob.getContent());
//                Files.copy(inputStreamBlob, Paths.get(outputFile));
//                file = new File(outputFile);
//
//            }
//            catch (IOException e){
//
//            }
//        }
//             //zip
//      //       sha1 = DigestUtils.sha1Hex(blob.getContent());
//     //        file = new File(sha1);
//    }
//
//
//    private MagitRepository deserializeFrom(InputStream in) throws JAXBException {
//        JAXBContext jc = JAXBContext.newInstance(JAXB_XML_GAME_PACKAGE_NAME);
//        Unmarshaller u = jc.createUnmarshaller();
//        return (MagitRepository)u.unmarshal(in);
//    }
//
//    private void printRep(MagitRepository i_MagitRepository)
//    {
//        System.out.println(i_MagitRepository.getLocation());
//
//        System.out.println(i_MagitRepository.getName());
//
//        for(MagitRepository.MagitBlobs.MagitBlob b : i_MagitRepository.getMagitBlobs().getMagitBlob())
//        {
//            System.out.println(b.getId());
//            System.out.println(b.getName());
//            System.out.println(b.getLastUpdater());
//            System.out.println(b.getLastUpdateDate());
//            System.out.println(b.getContent());
//            System.out.println("SHA-1: " + DigestUtils.sha1Hex(b.getContent()));
//        }
//    }
//    private void BuildRepository(MagitRepository i_MagitRepository) {
//        for(MagitRepository.MagitFolders.MagitSingleFolder F : i_MagitRepository.getMagitFolders().getMagitSingleFolder())
//        {
//
//            System.out.println("-------Folder "+F.getId()+"--------");
//            System.out.println("Id:               " + F.getId());
//            System.out.println("Last Update Date: " + F.getLastUpdateDate());
//            System.out.println("Last Updater:     " + F.getLastUpdater());
//            System.out.println("Name:             " + F.getName());
//            for(MagitRepository.MagitFolders.MagitSingleFolder.Items.Item item : F.getItems().getItem()) {
//                System.out.println("    ---item "+item.getId()+"---------");
//                System.out.println("    Type:                " + item.getType());
//                if(item.getType().equals("blob"))
//                {
//                    printBlob(i_MagitRepository.getMagitBlobs(),item.getId());
//                }
//            }
//            System.out.print("\n");
//        }
//    }
//
//    private void printBlob(MagitRepository.MagitBlobs blobs,Byte id)
//    {
//        //MagitRepository.MagitBlobs.MagitBlob blob = blobs.getMagitBlob().get(id);
//        for(MagitRepository.MagitBlobs.MagitBlob blob : blobs.getMagitBlob())
//        {
//            if(blob.getId().equals(id))
//            {
//                System.out.println("    Blob Id:             " + blob.getId());
//                System.out.println("    Blob Name:           " + blob.getName());
//                System.out.println("    Blob Last Updater:   " + blob.getLastUpdater());
//                System.out.println("    Blob Date Update:    " + blob.getLastUpdateDate());
//                System.out.println("    Blob Content:        " + blob.getContent());
//                System.out.println("    SHA-1:               " + DigestUtils.sha1Hex(blob.getContent()));
//            }
//        }
//    }
//
//    private void initRepository() {
//
//        Path location = Paths.get( magitRepository.getLocation() + "\\" + magitRepository.getName() + "\\.magit");
//
//        Boolean dirExists = Files.exists(location);
//        if (dirExists) {
//
//            System.out.println("Directory Alerady Exists");
//        } else {
//            try {
//                Files.createDirectories(location);
//            } catch (IOException ioExceptionObj) {
//                System.out.println("Problem Occured While Creating The Directory Structure= " + ioExceptionObj.getMessage());
//            }
//        }
//
//    }
//
//}