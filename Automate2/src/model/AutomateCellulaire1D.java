/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.nio.charset.StandardCharsets;
/**
 *
 * @author Plouffi
 */
public abstract class AutomateCellulaire1D extends AutomateCellulaire {

    private String message;
    private int taille;
    private Cell[] tabCell;
    private Cell[] key;
    protected static final int OCTET_SIZE = 8;
    protected static final int BINARY_BASE = 2;
    protected static final int BYTE_COMP = 128;
    
    public int getTaille() {
        return this.taille;
    }

    public void setTaille(String message) {
        this.taille = message.getBytes(StandardCharsets.ISO_8859_1).length*OCTET_SIZE;
    }
    
    public void setTaille(byte[] message) {
        this.taille = message.length;
    }

    //Méthode permettant de transformer un tableau de cellule en un string
    public void setMessage(Cell[] array) {
    	//Constrution du string bianire, exemple: 01101011...
        StringBuilder bytes = new StringBuilder();
        for (Cell cell : array) {
            bytes.append((cell.isAlive()) ? '1' : '0');
        }
        String input = bytes.toString();
        
        //Construction du string message
        //On traduit chaque octet du string précédent en un char
        StringBuilder output = new StringBuilder();
        for (int i = 0; i <= input.length() - OCTET_SIZE; i += OCTET_SIZE) {
            output.append((char) Integer.parseInt(input.substring(i, i + OCTET_SIZE), BINARY_BASE));
        }
        this.message = output.toString();
        setTaille(this.message);
    }

    public void setMessage(String message) {
        this.message = message;
        setTaille(this.message);
    }

    public String getMessage() {
        return this.message;
    }

    public void setArray(Cell[] array) {
        this.tabCell = array;
    }

    public Cell[] getArray() {
        return this.tabCell;
    }

    public Cell[] getKey() {
        return this.key;
    }

    public void setKey(Cell[] key) {
        this.key = key;
    }

    @Override
    public void initArray() {
        byte[] bytes = this.getMessage().getBytes(StandardCharsets.ISO_8859_1);
        int indice = 0;
        //Traduit chaque byte en 8 cellules 
        for (byte b : bytes) {
            int val = b;
            //Ajout des cellules
            for (int i = 0; i < OCTET_SIZE; i++) {
                this.tabCell[i + indice] = new Cell(((val & BYTE_COMP) != 0), false);
                val <<= 1;
            }
            indice += OCTET_SIZE;
        }

    }
}
