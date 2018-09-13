package controler;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.logging.Level;
import java.util.logging.Logger;

import model.*;

/**
 *
 * @author rdumas
 */
public class MyControler{
    //Attribut
    AutomateCodeur aCC;
    AutomateDecodeur aCD;
    static final int ITERATION = 20;
    static final int NB_FILE = 2;

    //Méthode du controler qui gère le codage du message
    //S'occupe d'appeler le modèle pour écrire dans les fichiers
    public String[] coder(String message) throws IOException{
        aCC = new AutomateCodeur(message);
        aCC.initArray();
        aCC.coder(ITERATION);
        byte[] dataMessage = aCC.getMessage().getBytes(StandardCharsets.ISO_8859_1);
        byte[] dataKey = aCC.getKeyInString().getBytes(StandardCharsets.ISO_8859_1);
        String[] listeFichier = new String[NB_FILE];
        
        //On écrit les fichiers du message et de la clé
        File messageFile = new File("message");
        if(!messageFile.exists() && !messageFile.createNewFile()) {
        	Logger.getLogger(MyControler.class.getName()).log(Level.SEVERE, null,("Failed to create message file"));
        }
        File keyFile = new File("key");
        if(!keyFile.exists() && !keyFile.createNewFile()) {
        	Logger.getLogger(MyControler.class.getName()).log(Level.SEVERE, null,("Failed to create key file"));
        } 
        try (FileOutputStream out1 = new FileOutputStream(messageFile, false);
        	 FileOutputStream out2 = new FileOutputStream(keyFile,false);){
	        out1.write(dataMessage);
	        out2.write(dataKey);
	        
	        //On enregistre leur chemin
	        listeFichier[0] = messageFile.getAbsolutePath();
	        listeFichier[1] = keyFile.getAbsolutePath();
        }
        //On retourne l'emplacement des fichiers
        return listeFichier;
    }
    
    //Méthode du controler qui gère le codage du message
    //S'occupe de décoder les fichiers à partir du modèle
    public String decoder(byte[] message, byte[] key){
        aCD = new AutomateDecodeur(message, key);
        aCD.decoder(ITERATION);
        return aCD.getMessage();
    }
}
