package view;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
public class Fenetre extends JFrame{
    //Attribut
    JPanel panel;
    MyControler controler;
    File message;
    File key;
    
    //Constructeur
    public Fenetre(){
        //Instanciation du controler
        controler = new MyControler();
        //Position et taille de la fenetre
        this.setSize(800,600);
        this.setTitle("Automate Codeur/Decodeur");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //Interface et Layout
        panel = new JPanel();
        panel.setLayout(new BorderLayout());
        
        this.displayMenu();
    }
    
    public void displayMenu(){
        this.panel.removeAll();
        //Titre
        JLabel titre = new JLabel("Automate Codeur/Décodeur",JLabel.CENTER);
        titre.setFont(new Font("Arial", Font.BOLD, 24));
        Border paddingTitre = BorderFactory.createEmptyBorder(30,0,30,0);
        titre.setBorder(paddingTitre);
        //Panel de selection
        JPanel selection = new JPanel();
        selection.setLayout(new BoxLayout(selection, BoxLayout.PAGE_AXIS));
        
        JButton coder = new JButton("Coder");
        coder.setMaximumSize(new Dimension(150,40));
        coder.setAlignmentX(CENTER_ALIGNMENT);
        coder.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                 Fenetre.this.coderView();
            }
        });
        JButton decoder = new JButton("Décoder");
        decoder.setMaximumSize(new Dimension(150,40));
        decoder.setAlignmentX(CENTER_ALIGNMENT);
        decoder.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                 Fenetre.this.decoderView();
            }
        });
        selection.add(coder);
        selection.add(decoder);
        
        panel.add(titre,BorderLayout.PAGE_START);
        panel.add(selection, BorderLayout.CENTER);
        this.add(panel);
        this.setVisible(true);
    }
    public void coderView(){
        panel.removeAll();
        
        ////////////////////////////////////////////////////
        ////////////////////////////////////////////////////
        //Label d'instructions
        JLabel instruction = new JLabel("Entrer ci-dessous le texte à coder :");
        instruction.setFont(new Font("Arial", Font.PLAIN, 18));
        Border paddingInstruction = BorderFactory.createEmptyBorder(30,0,30,0);
        instruction.setBorder(paddingInstruction);
        
        //Champs texte à coder
        JTextArea textArea = new JTextArea();
  
        ////////////////////////////////////////////////////
        ////////////////////////////////////////////////////
        //Panel des boutons
        JPanel panelBouton = new JPanel();
        Border paddingPanelBouton = BorderFactory.createEmptyBorder(30,0,30,0);
        panelBouton.setBorder(paddingPanelBouton);
        panelBouton.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        
        //Bouton de retour au menu
        JButton retour = new JButton("Retour");
        retour.setMaximumSize(new Dimension(150,40));
        retour.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                   Fenetre.this.displayMenu();
            }
        });
        c.weightx = 1;
        c.ipady = 20;
        c.ipadx = 50;
        c.gridx = 0;
        c.gridy = 0;
        panelBouton.add(retour,c);
        
        //Bouton pour déclencher le codage
        JButton coder = new JButton("Coder");
        coder.setMaximumSize(new Dimension(150,40));
        coder.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                try {
                    String message;
                    message = ((JTextArea)Fenetre.this.panel.getComponent(1)).getText();
                    String[] listeChemin = Fenetre.this.controler.coder(message);
                    JOptionPane.showMessageDialog(Fenetre.this, 
                                                    "Message codé avec succès ! \n Message sauvé dans: " + listeChemin[0] +
                                                    "\n Clé sauvée dans: " + listeChemin[1],
                                                    "Succès !",
                                                    JOptionPane.PLAIN_MESSAGE);
                } catch (InterruptedException | IOException ex) {
                    Logger.getLogger(Fenetre.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        c.gridx = 5;
        c.gridy = 0;
        panelBouton.add(coder,c);
        ////////////////////////////////////////////////////
        ////////////////////////////////////////////////////
        
        panel.add(instruction, BorderLayout.PAGE_START);
        panel.add(textArea, BorderLayout.CENTER);
        panel.add(panelBouton, BorderLayout.PAGE_END);   
        this.add(panel);
        this.setVisible(true);
    }
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
        JLabel labelKey = new JLabel("Clé: ");
        //Champs du chemin des fichiers
        Border boderPath = BorderFactory.createLineBorder(Color.black, 1);
        JTextPane messageFilePath = new JTextPane();
        messageFilePath.setPreferredSize(new Dimension(400,20));
        messageFilePath.setEditable(false);
        messageFilePath.setBorder(boderPath);
        JTextPane keyFilePath = new JTextPane();
        keyFilePath.setPreferredSize(new Dimension(400,20));
        keyFilePath.setBorder(boderPath);
        keyFilePath.setEditable(false);
        //Bouton pour ouvrir le fileChooser
        JButton openMessage = new JButton("Ouvrir");
        JButton openKey = new JButton("Ouvrir");
        openMessage.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                int returnVal = fileChooser.showOpenDialog(Fenetre.this);
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    Fenetre.this.message = fileChooser.getSelectedFile();
                    String messagePath = Fenetre.this.message.getPath();
                    ((JTextPane)((JPanel)((JPanel)(Fenetre.this.panel.getComponent(0))).getComponent(0)).getComponent(1)).setText(messagePath);
                }
            }
        });
        openKey.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                int returnVal = fileChooser.showOpenDialog(Fenetre.this);
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    Fenetre.this.key = fileChooser.getSelectedFile();
                    String keyPath = Fenetre.this.key.getPath();
                    ((JTextPane)((JPanel)((JPanel)(Fenetre.this.panel.getComponent(0))).getComponent(0)).getComponent(4)).setText(keyPath);
                }
            }
        });
        
        c1.ipadx = 1;
        c1.gridx = 0;
        c1.gridy = 0;
        c1.insets = new Insets(0,20,0,20);
        panelSelection.add(labelMessage,c1);
        c1.fill = GridBagConstraints.NONE;
        c1.weightx = 0;
        c1.gridx = 1;
        c1.gridy = 0;
        panelSelection.add(messageFilePath,c1);
        c1.ipadx = 1;
        c1.gridx = 2;
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
        c1.gridx = 2;
        c1.gridy = 1;
        panelSelection.add(openKey,c1);
        
        ////////////////////////////////////////////////////
        ////////////////////////////////////////////////////
        //Panel des boutons
        JPanel panelBouton = new JPanel();
        Border paddingPanelBouton = BorderFactory.createEmptyBorder(30,0,30,0);
        panelBouton.setBorder(paddingPanelBouton);
        panelBouton.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        
        //Bouton de retour au menu
        JButton retour = new JButton("Retour");
        retour.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                   Fenetre.this.displayMenu();
            }
        });
        c.weightx = 1;
        c.ipady = 20;
        c.ipadx = 50;
        c.gridx = 0;
        c.gridy = 0;
        panelBouton.add(retour,c);
        //Bouton pour décoder
        JButton decoder = new JButton("Décoder");
        decoder.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                try {
                    byte[] messageByte = Files.readAllBytes(message.toPath());
                    byte[] keyByte = Files.readAllBytes(key.toPath());
                    String messageDecode = Fenetre.this.controler.decoder(messageByte, keyByte);
                    ((JTextPane)Fenetre.this.panel.getComponent(1)).setText(messageDecode);
                } catch (IOException | InterruptedException ex) {
                    Logger.getLogger(Fenetre.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
        });
        c.gridx = 5;
        c.gridy = 0;
        panelBouton.add(decoder,c);
        ////////////////////////////////////////////////////
        ////////////////////////////////////////////////////

        panelMenu.add(panelSelection, BorderLayout.CENTER);
        panel.add(panelMenu, BorderLayout.PAGE_START);
        
        JTextPane message = new JTextPane();
        panel.add(message, BorderLayout.CENTER);
        
        panel.add(panelBouton, BorderLayout.PAGE_END);

        this.add(panel);
        this.setVisible(true);
    }
}
