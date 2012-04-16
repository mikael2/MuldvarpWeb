package no.hials.muldvarpweb.web;

import java.io.*;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author mikael
 */
@Named
@SessionScoped
public class FileUpload implements Serializable {
    public void handleFileUpload(FileUploadEvent event) {
        UploadedFile file = event.getFile();
        System.out.println("Got file: " + file.getFileName());
        
        String parent = "/home/mikael";
        byte[] buffer = new byte[512];
        
        BufferedOutputStream bos = null;
        InputStream is = null;
        try {
            FileOutputStream fos = new FileOutputStream(new File(parent,Long.toHexString(System.currentTimeMillis())));
            bos = new BufferedOutputStream(fos);
            
            is = file.getInputstream();
            int length;
            while((length = is.read(buffer)) != -1) {
                bos.write(buffer, 0, length);
            }
        } catch(Exception e) {
            // TODO: Report
            
        } finally {
            if(bos != null) {
                try {bos.close();} catch(Exception e2) {}
            }
            if(is != null) {
                try {is.close();} catch(Exception e2) {}
            }
            
        }
    }    
}
