package Zip;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import static Logic.ConstantsEnums.EmptyString;

public class ZipFile {
    String m_dirObject;

    public ZipFile(String m_dirObject) {
        this.m_dirObject = m_dirObject;
    }

    public void zipFile(String i_Sha1,String i_FileContent)
    {
        StringBuilder sb = new StringBuilder();
        sb.append(i_FileContent);

        File ZipFile = new File(m_dirObject + "/" + i_Sha1 + ".zip");
        ZipOutputStream out = null;
        try {
            out = new ZipOutputStream(new FileOutputStream(ZipFile));
            ZipEntry e = new ZipEntry(i_Sha1+".txt");
            out.putNextEntry(e);
            byte[] data = sb.toString().getBytes();
            out.write(data, 0, data.length);
            out.closeEntry();
            out.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void zipFileToBranchActive(String ActiveBranchName,String commitSha1)
    {
        StringBuilder sb = new StringBuilder();
        sb.append(commitSha1);

        File ZipFile = new File(m_dirObject + "/../branches/" + ActiveBranchName + ".zip");
        ZipOutputStream out = null;
        try {
            out = new ZipOutputStream(new FileOutputStream(ZipFile));
            ZipEntry e = new ZipEntry(ActiveBranchName+".txt");
            out.putNextEntry(e);
            byte[] data = sb.toString().getBytes();
            out.write(data, 0, data.length);
            out.closeEntry();
            out.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void unZipIt(String zipFile, String outputFolder){
        byte[] buffer = new byte[1024];
        try{
            ZipInputStream zis =
                    new ZipInputStream(new FileInputStream(zipFile));

            //get the zipped file list entry
            ZipEntry ze = zis.getNextEntry();
            String fileName = ze.getName();
            File newFile = new File(outputFolder + File.separator + fileName);
            FileOutputStream fos = new FileOutputStream(newFile);
            int len;
            while ((len = zis.read(buffer)) > 0) {
                fos.write(buffer, 0, len);
            }
            fos.close();
            zis.closeEntry();
            zis.close();
        }catch(IOException ex){
            ex.printStackTrace();
        }
    }
}
