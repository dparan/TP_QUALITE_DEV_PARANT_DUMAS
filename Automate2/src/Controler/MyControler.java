package Controler;
import Model.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 *
 * @author rdumas
 */
public class MyControler{
    //Attribut
    AutomateCodeur ACC;
    AutomateDecodeur ACD;
    AutomateCellulaire2D AC2D;
    
    public MyControler(){}
    
    public String[] coder(String message) throws InterruptedException, FileNotFoundException, IOException{
        ACC = new AutomateCodeur(message);
        ACC.initArray();
        ACC.coder(20);
        byte[] dataMessage = ACC.getMessage().getBytes(StandardCharsets.ISO_8859_1);
        byte[] dataKey = ACC.getKeyInString().getBytes(StandardCharsets.ISO_8859_1);
        String[] listeFichier = new String[2];
        try{
            //On écrit les fichiers de message et clé
            File messageFile = new File("message");
            if(!messageFile.exists()) {
               messageFile.createNewFile();
            } 
            FileOutputStream out1 = new FileOutputStream(messageFile, false);
            out1.write(dataMessage);
            out1.close();
            
            File keyFile = new File("key");
            if(!keyFile.exists()) {
               keyFile.createNewFile();
            } 
            FileOutputStream out2 = new FileOutputStream(keyFile,false);
            out2.write(dataKey);
            out2.close();
            
            //On enregistre leur chemin
            listeFichier[0] = messageFile.getAbsolutePath();
            listeFichier[1] = keyFile.getAbsolutePath();
            
        } catch(IOException  execption){
            System.out.println(execption.getMessage());
        }
        //On retourne l'emplacement des fichiers
        return listeFichier;
    }
    
    public String decoder(byte[] message, byte[] key) throws IOException, InterruptedException{
        ACD = new AutomateDecodeur(message, key);
        ACD.decoder(20);
        return ACD.getMessage();
    }
}
