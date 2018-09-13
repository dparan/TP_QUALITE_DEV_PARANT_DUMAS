package view;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.border.Border;

import controler.*;
/**
 *
 * @author rdumas
 */
/* pas le choix car on h�rite de JFRAME */

public class Fenetre extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//Attribut
    JPanel panel;
    /* transcient permet � l'objet d'�tre ignor� lors de la serialisation */
    transient MyControler controler; 
    File message;
    File key;
    static final int WIDTH_FRAME = 800;
    static final int HEIGHT_FRAME = 600;
    static final int FONT_SIZE = 24;
    static final int FONT_SIZE_INSTRUCTION = 18;
    static final int TOP_BOT = 30;
    static final int LEFT_RIGHT = 0; 
    static final int BT_WIDTH = 150;
    static final int BT_HEIGHT = 40;
    static final int PATH_WIDTH = 400;
    static final int PATH_HEIGHT = 20;
    static final int INTERNAL_PADDING_Y = 20;
    static final int INTERNAL_PADDING_X = 50;
    static final int GRID_X = 5;
    static final int GRID_Y = 0;
    static final int INSET_LR = 20;
    static final int GRID_X_PANEL_SELECTION = 2;

    static final int COMPONENT_WANTED = 4;
    
    //Constructeur
    public Fenetre(){
        //Instanciation du controler
        controler = new MyControler();
        //Position et taille de la fenetre
        this.setSize(WIDTH_FRAME,HEIGHT_FRAME);
        this.setTitle("Automate Codeur/Decodeur");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //Interface et Layout
        panel = new JPanel();
        panel.setLayout(new BorderLayout());
        
        this.displayMenu();
    }
    
    //M�thode qui affiche le menu
    public void displayMenu(){
        this.panel.removeAll();
        // Titre
        JLabel titre = new JLabel("Automate Codeur/D�codeur",JLabel.CENTER);
        titre.setFont(new Font("Arial", Font.BOLD, FONT_SIZE));
        Border paddingTitre = BorderFactory.createEmptyBorder(TOP_BOT,LEFT_RIGHT,TOP_BOT,LEFT_RIGHT);
        titre.setBorder(paddingTitre);
        //Panel de selection
        JPanel selection = new JPanel();
        selection.setLayout(new BoxLayout(selection, BoxLayout.PAGE_AXIS));
        
        JButton coder = new JButton("Coder");
        coder.setMaximumSize(new Dimension(BT_WIDTH,BT_HEIGHT));
        coder.setAlignmentX(CENTER_ALIGNMENT);
        //Utilisation de m�thode anonymes pour g�rer la gestion des �venements li�s aux boutons
        coder.addActionListener((ActionEvent e)->Fenetre.this.coderView());
        JButton decoder = new JButton("D�coder");
        decoder.setMaximumSize(new Dimension(BT_WIDTH,BT_HEIGHT));
        decoder.setAlignmentX(CENTER_ALIGNMENT);
        decoder.addActionListener((ActionEvent e)->Fenetre.this.decoderView());
        selection.add(coder);
        selection.add(decoder);
        
        panel.add(titre,BorderLayout.PAGE_START);
        panel.add(selection, BorderLayout.CENTER);
        this.add(panel);
        this.setVisible(true);
    }
    
    //M�thode qui affiche la vue pour d�coder
    public void coderView(){
        panel.removeAll();
        
        ////////////////////////////////////////////////////
        ////////////////////////////////////////////////////
        //Label d'instructions
        JLabel instruction = new JLabel("Entrer ci-dessous le texte � coder :");
        instruction.setFont(new Font("Arial", Font.PLAIN, FONT_SIZE_INSTRUCTION));
        Border paddingInstruction = BorderFactory.createEmptyBorder(TOP_BOT,LEFT_RIGHT,TOP_BOT,LEFT_RIGHT);
        instruction.setBorder(paddingInstruction);
        
        //Champs texte � coder
        JTextArea textArea = new JTextArea();
  
        ////////////////////////////////////////////////////
        ////////////////////////////////////////////////////
        //Panel des boutons
        JPanel panelBouton = new JPanel();
        Border paddingPanelBouton = BorderFactory.createEmptyBorder(TOP_BOT,LEFT_RIGHT,TOP_BOT,LEFT_RIGHT);
        panelBouton.setBorder(paddingPanelBouton);
        panelBouton.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        
        //Bouton de retour au menu
        JButton retour = new JButton("Retour");
        retour.setMaximumSize(new Dimension(BT_WIDTH,BT_HEIGHT));
        retour.addActionListener((ActionEvent e)->Fenetre.this.displayMenu());
        c.weightx = 1;
        c.ipady = INTERNAL_PADDING_Y;
        c.ipadx = INTERNAL_PADDING_X;
        c.gridx = 0;
        c.gridy = 0;
        panelBouton.add(retour,c);
        
        //Bouton pour d�clencher le codage
        JButton coder = new JButton("Coder");
        coder.setMaximumSize(new Dimension(BT_WIDTH,BT_HEIGHT));
        coder.addActionListener((ActionEvent e)->{
            try {
                String messagePanel = ((JTextArea)Fenetre.this.panel.getComponent(1)).getText();
                String[] listeChemin = Fenetre.this.controler.coder(messagePanel);
                JOptionPane.showMessageDialog(Fenetre.this, 
                                                "Message cod� avec succ�s ! \n Message sauv� dans: " + listeChemin[0] +
                                                "\n Cl� sauv�e dans: " + listeChemin[1],
                                                "Succ�s !",
                                                JOptionPane.PLAIN_MESSAGE);
            } catch (IOException ex) {
                Logger.getLogger(Fenetre.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        c.gridx = GRID_X;
        c.gridy = GRID_Y;
        panelBouton.add(coder,c);
        ////////////////////////////////////////////////////
        ////////////////////////////////////////////////////
        
        panel.add(instruction, BorderLayout.PAGE_START);
        panel.add(textArea, BorderLayout.CENTER);
        panel.add(panelBouton, BorderLayout.PAGE_END);   
        this.add(panel);
        this.setVisible(true);
    }
    
    //M�thode qui affiche la vue pour d�coder
    public void decoderView(){
        panel.removeAll();
        
        JPanel panelMenu = new JPanel();
        panelMenu.setLayout(new BorderLayout());
        ////////////////////////////////////////////////////
        ////////////////////////////////////////////////////
        //Champs de saisie des fichier message et key          
        JPanel panelSelection = new JPanel();
        panelSelection.setLayout(new GridBagLayout());
        GridBagConstraints c1 = new GridBagConstraints();
        
        //FileChooser pour sélectionner un fichier
        JFileChooser fileChooser = new JFileChooser();
        //Labels des fichier à sélectionner
        JLabel labelMessage = new JLabel("Message: ");
        JLabel labelKey = new JLabel("Cl�: ");
        //Champs du chemin des fichiers
        Border boderPath = BorderFactory.createLineBorder(Color.black, 1);
        JTextPane messageFilePath = new JTextPane();
        messageFilePath.setPreferredSize(new Dimension(PATH_WIDTH,PATH_HEIGHT));
        messageFilePath.setEditable(false);
        messageFilePath.setBorder(boderPath);
        JTextPane keyFilePath = new JTextPane();
        keyFilePath.setPreferredSize(new Dimension(PATH_WIDTH,PATH_HEIGHT));
        keyFilePath.setBorder(boderPath);
        keyFilePath.setEditable(false);
        //Bouton pour ouvrir le fileChooser
        JButton openMessage = new JButton("Ouvrir");
        JButton openKey = new JButton("Ouvrir");
        openMessage.addActionListener((ActionEvent e)->{
	        int returnVal = fileChooser.showOpenDialog(Fenetre.this);
	        if (returnVal == JFileChooser.APPROVE_OPTION) {
	            Fenetre.this.message = fileChooser.getSelectedFile();
	            String messagePath = Fenetre.this.message.getPath();
	            ((JTextPane)((JPanel)((JPanel)(Fenetre.this.panel.getComponent(0))).getComponent(0)).getComponent(1)).setText(messagePath);
	        }
        });
        openKey.addActionListener((ActionEvent e)->{
	        int returnVal = fileChooser.showOpenDialog(Fenetre.this);
	        if (returnVal == JFileChooser.APPROVE_OPTION) {
	            Fenetre.this.key = fileChooser.getSelectedFile();
	            String keyPath = Fenetre.this.key.getPath();
	            ((JTextPane)((JPanel)((JPanel)(Fenetre.this.panel.getComponent(0))).getComponent(0)).getComponent(COMPONENT_WANTED)).setText(keyPath);
	        }
        });
        
        c1.ipadx = 1;
        c1.gridx = 0;
        c1.gridy = 0;
        c1.insets = new Insets(0,INSET_LR,0,INSET_LR);
        panelSelection.add(labelMessage,c1);
        c1.fill = GridBagConstraints.NONE;
        c1.weightx = 0;
        c1.gridx = 1;
        c1.gridy = 0;
        panelSelection.add(messageFilePath,c1);
        c1.ipadx = 1;
        c1.gridx = GRID_X_PANEL_SELECTION;
        c1.gridy = 0;
        panelSelection.add(openMessage,c1);
        
        c1.ipadx = 1;
        c1.gridx = 0;
        c1.gridy = 1;
        panelSelection.add(labelKey,c1);
        c1.fill = GridBagConstraints.NONE;
        c1.weightx = 0;
        c1.gridx = 1;
        c1.gridy = 1;
        panelSelection.add(keyFilePath,c1);
        c1.ipadx = 1;
        c1.gridx = GRID_X_PANEL_SELECTION;
        c1.gridy = 1;
        panelSelection.add(openKey,c1);
        
        ////////////////////////////////////////////////////
        ////////////////////////////////////////////////////
        //Panel des boutons
        JPanel panelBouton = new JPanel();
        Border paddingPanelBouton = BorderFactory.createEmptyBorder(TOP_BOT,LEFT_RIGHT,TOP_BOT,LEFT_RIGHT);
        panelBouton.setBorder(paddingPanelBouton);
        panelBouton.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        
        //Bouton de retour au menu
        JButton retour = new JButton("Retour");
        retour.addActionListener((ActionEvent e)->Fenetre.this.displayMenu());
        c.weightx = 1;
        c.ipady = INTERNAL_PADDING_Y;
        c.ipadx = INTERNAL_PADDING_X;
        c.gridx = 0;
        c.gridy = 0;
        panelBouton.add(retour,c);
        //Bouton pour d�coder
        JButton decoder = new JButton("D�coder");
        decoder.addActionListener((ActionEvent e)->{
			try {
			    byte[] messageByte = Files.readAllBytes(message.toPath());
			    byte[] keyByte = Files.readAllBytes(key.toPath());
			    String messageDecode = Fenetre.this.controler.decoder(messageByte, keyByte);
			    ((JTextPane)Fenetre.this.panel.getComponent(1)).setText(messageDecode);
			} catch (IOException ex) {
			    Logger.getLogger(Fenetre.class.getName()).log(Level.SEVERE, null, ex);
			}
        });
        c.gridx = GRID_X;
        c.gridy = GRID_Y;
        panelBouton.add(decoder,c);
        ////////////////////////////////////////////////////
        ////////////////////////////////////////////////////

        panelMenu.add(panelSelection, BorderLayout.CENTER);
        panel.add(panelMenu, BorderLayout.PAGE_START);
        
        panel.add(new JTextPane(), BorderLayout.CENTER);
        
        panel.add(panelBouton, BorderLayout.PAGE_END);

        this.add(panel);
        this.setVisible(true);
    }
}
