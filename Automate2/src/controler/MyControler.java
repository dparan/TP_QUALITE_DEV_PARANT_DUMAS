package controler;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import model.*;

/**
 *
 * @author rdumas
 */
public class MyControler{
    //Attribut
    AutomateCodeur aCC;
    AutomateDecodeur aCD;
    
    
    public String[] coder(String message) throws InterruptedException, IOException{
        aCC = new AutomateCodeur(message);
        aCC.initArray();
        aCC.coder(20);
        byte[] dataMessage = aCC.getMessage().getBytes(StandardCharsets.ISO_8859_1);
        byte[] dataKey = aCC.getKeyInString().getBytes(StandardCharsets.ISO_8859_1);
        String[] listeFichier = new String[2];
        FileOutputStream out1 = null;
        FileOutputStream out2 = null;
        try
        {
            //On écrit les fichiers de message et clé
            File messageFile = new File("message");
            if(!messageFile.exists()) {
               messageFile.createNewFile();
            } 
            out1 = new FileOutputStream(messageFile, false);
            out1.write(dataMessage);
            
            File keyFile = new File("key");
            if(!keyFile.exists()) {
               keyFile.createNewFile();
            } 
            out2 = new FileOutputStream(keyFile,false);
            out2.write(dataKey);
            
            //On enregistre leur chemin
            listeFichier[0] = messageFile.getAbsolutePath();
            listeFichier[1] = keyFile.getAbsolutePath();
            
        }finally {
        	out1.close();
        	out2.close();
        }
        //On retourne l'emplacement des fichiers
        return listeFichier;
    }
    
    public String decoder(byte[] message, byte[] key) throws InterruptedException{
        aCD = new AutomateDecodeur(message, key);
        aCD.decoder(20);
        return aCD.getMessage();
    }
}
