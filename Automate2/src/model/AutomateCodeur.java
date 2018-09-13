/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author R�mi
 */
public class AutomateCodeur extends AutomateCellulaire1D {

    public AutomateCodeur(String message) {
        setMessage(message);
        setArray(new Cell[this.getTaille()]);
    }

    public void nextGenerationUnivInfini() {
        int size = this.getTaille();
        Cell[] nextGen = new Cell[size];
        Cell[] neighbours = new Cell[BINARY_BASE];
        for (int i = 0; i < size; i++) {
            //Initialisation des voisins
            neighbours[0] = getArray()[(i - 1 + size) % size];
            neighbours[1] = getArray()[(i + 1) % size];
            //Nouvel état de la cellule
            nextGen[i] = getArray()[i].nextState(neighbours);
        }
        setMessage(nextGen);
        setArray(nextGen);
        setGeneration();
    }
    
    public String getKeyInString(){
        StringBuilder bytes = new StringBuilder();
        for (Cell cell : getKey()) {
            bytes.append((cell.isAlive()) ? '1' : '0');
        }
        String input = bytes.toString();
        StringBuilder output = new StringBuilder();
        for (int i = 0; i <= input.length() - OCTET_SIZE; i += OCTET_SIZE) {
            output.append(Integer.parseInt(input.substring(i, i + OCTET_SIZE), BINARY_BASE));
        }
        return output.toString();
    }

    public void coder(int iteration){
        for (int i = 1; i < iteration; i++) {
            this.nextGenerationUnivInfini();
        }
        //Initialisation de la clé 
        int size = this.getTaille();
        Cell[] key = new Cell[size];
        Cell[] neighbours = new Cell[BINARY_BASE];
        for (int i = 0; i < size; i++) {
            //Initialisation des voisins
            neighbours[0] = getArray()[(i - 1 + size) % size];
            neighbours[1] = getArray()[(i + 1) % size];
            //Nouvel état de la cellule
            key[i] = getArray()[i].nextState(neighbours);
        }
        setKey(key);
    }
}
