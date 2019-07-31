package Zip;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import static Logic.ConstantsEnums.EmptyString;

public class ZipFile {
    String m_dirObject;

    public ZipFile(String m_dirObject) {
        this.m_dirObject = m_dirObject;
    }

    public void zipFile(File i_toZip,String i_FileContent,String i_Sha1)
    {
        StringBuilder sb = new StringBuilder();
        sb.append(i_FileContent);

        File ZipFile = new File(m_dirObject + "/" + i_Sha1 + ".zip");
        ZipOutputStream out = null;
        try {
            out = new ZipOutputStream(new FileOutputStream(ZipFile));
            ZipEntry e = new ZipEntry(i_toZip.getName() + ".txt");
            out.putNextEntry(e);
            byte[] data = sb.toString().getBytes();
            out.write(data, 0, data.length);
            out.closeEntry();
            out.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void unZipIt()
    {

    }



}
