/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;


/**
 *
 * @author Rémi
 */
public class AutomateDecodeur extends AutomateCellulaire1D {

     public AutomateDecodeur(byte[] message, byte[] key) {
        this.setArray(message);
        this.initPreviousState(key);
    }

    
    public void setArray(byte[] message) {
        Cell[] cell = new Cell[message.length*8];
        int indice = 0;
        for (byte b : message) {
            int val = b;
            for (int i = 0; i < 8; i++) {
               cell[i + indice] = new Cell(!((val & 128) == 0), false);
                val <<= 1;
            }
            indice += 8;
        }
        this.setArray(cell);
        setMessage(getArray());
    }

    public void initPreviousState(byte[] key) {
        int size = key.length*8;    
        Cell[] cellKey = new Cell[size];
        int indice = 0;
        for (byte b : key) {
            int val = b;
            for (int i = 0; i < 8; i++) {
               cellKey[i + indice] = new Cell(!((val & 128) == 0), false);
               val <<= 1;
            }
            indice += 8;
        }
        setKey(cellKey);
        
        for (int i = 0; i < size; i++) {
            getArray()[i].setPreviousState(getKey()[i].isAlive());
        }
    }

    public void nextGenerationUnivInfiniReverse() {
        int size = this.getTaille();
        Cell[] nextGen = new Cell[size];
        Cell[] neighbours = new Cell[2];
        for (int i = 0; i < size; i++) {
            //Initialisation des voisins
            neighbours[0] = getArray()[(i - 1 + size) % size];
            neighbours[1] = getArray()[(i + 1) % size];
            //Nouvel état de la cellule
            nextGen[i] = getArray()[i].nextStateInv(neighbours);
        }
        setArray(nextGen);
        setGeneration();
    }

    public void decoder(int iteration) throws InterruptedException {
        for (int i = 1; i < iteration; i++) {
            this.nextGenerationUnivInfiniReverse();
        }
        setMessage(getArray());
    }

}
