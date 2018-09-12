/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.nio.charset.StandardCharsets;

/**
 *
 * @author Plouffi
 */
abstract public class AutomateCellulaire1D extends AutomateCellulaire {

    private String message;
    private int taille;
    private Cell[] tabCell;
    private Cell[] key;

    public int getTaille() {
        return this.taille;
    }

    public void setTaille(String message) {
        this.taille = message.getBytes(StandardCharsets.ISO_8859_1).length*8;
    }
    
    public void setTaille(byte[] message) {
        this.taille = message.length;
    }

    public void setMessage(Cell[] array) {
        StringBuilder bytes = new StringBuilder();
        for (Cell cell : array) {
            bytes.append((cell.isAlive()) ? '1' : '0');
        }
        String input = bytes.toString();
        String output = "";
        for (int i = 0; i <= input.length() - 8; i += 8) {
            int k = Integer.parseInt(input.substring(i, i + 8), 2);
            output += (char) k;
        }
        this.message = output;
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
        for (byte b : bytes) {
            int val = b;
            for (int i = 0; i < 8; i++) {
                this.tabCell[i + indice] = new Cell(!((val & 128) == 0), false);
                val <<= 1;
            }
            indice += 8;
        }

    }

    @Override
    public String toString() {
        String res = "";
        res += "Génération : " + getGeneration() + "\n";
        res += this.getMessage();
        res += "\n";
        for (int i = 0; i < this.getTaille(); i++) {
            res += this.tabCell[i].etat + " ";
        }
        res += "\n";
        return res;
    }
}
