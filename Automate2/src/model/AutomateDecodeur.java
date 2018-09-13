/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Rémi
 */
public class AutomateDecodeur extends AutomateCellulaire1D {

     public AutomateDecodeur(byte[] message, byte[] key) {
        this.setArrayByteToCell(message);
        this.initPreviousState(key);
    }

    //Méthode permettant de transformer un tableau de bytes en un string
    public void setArrayByteToCell(byte[] message) {
        Cell[] cell = new Cell[message.length*OCTET_SIZE];
        int indice = 0;
        for (byte b : message) {
            int val = b;
            for (int i = 0; i < OCTET_SIZE; i++) {
               cell[i + indice] = new Cell(((val & BYTE_COMP) != 0), false);
                val <<= 1;
            }
            indice += OCTET_SIZE;
        }
        this.setArray(cell);
        setMessage(getArray());
    }

    //Méthode qui initialise le tableau de cellule permettant le décryptage
    public void initPreviousState(byte[] key) {
        int size = key.length*OCTET_SIZE;    
        Cell[] cellKey = new Cell[size];
        int indice = 0;
        for (byte b : key) {
            int val = b;
            for (int i = 0; i < OCTET_SIZE; i++) {
               cellKey[i + indice] = new Cell(((val & BYTE_COMP) != 0), false);
               val <<= 1;
            }
            indice += OCTET_SIZE;
        }
        setKey(cellKey);

        for (int i = 0; i < size; i++) {
        	getArray()[i].setPreviousState(getKey()[i].isAlive());
        }
    }

  //Méthode permettant de passer à la génération suivante de cellule
    public void nextGenerationUnivInfiniReverse() {
        int size = this.getTaille();
        Cell[] nextGen = new Cell[size];
        Cell[] neighbours = new Cell[BINARY_BASE];
        for (int i = 0; i < size; i++) {
            //Initialisation des voisins
            neighbours[0] = getArray()[(i - 1 + size) % size];
            neighbours[1] = getArray()[(i + 1) % size];
            //Nouvel Ã©tat de la cellule
            nextGen[i] = getArray()[i].nextStateInv(neighbours);
        }
        setArray(nextGen);
        setGeneration();
    }

    public void decoder(int iteration){
        for (int i = 1; i < iteration; i++) {
            this.nextGenerationUnivInfiniReverse();
        }
        setMessage(getArray());
    }

}
