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

    //M�thode permettant de passer � la g�n�ration suivante de cellule
    public void nextGenerationUnivInfini() {
        int size = this.getTaille();
        //Prochaine g�n�ration de cellule
        Cell[] nextGen = new Cell[size];
        //Tableau buffer des voisins d'une cellule
        Cell[] neighbours = new Cell[BINARY_BASE];
        //Parcours de la g�n�ration actuelle
        for (int i = 0; i < size; i++) {
            //Initialisation des voisins
            neighbours[0] = getArray()[(i - 1 + size) % size];
            neighbours[1] = getArray()[(i + 1) % size];
            //Nouvel �tat de la cellule
            nextGen[i] = getArray()[i].nextState(neighbours);
        }
        //Mis � jour
        setMessage(nextGen);
        setArray(nextGen);
        setGeneration();
    }
    
    //Renvoie la cl� du message
    public String getKeyInString(){
    	//Constrution du string bianire, exemple: 01101011...
        StringBuilder bytes = new StringBuilder();
        for (Cell cell : getKey()) {
            bytes.append((cell.isAlive()) ? '1' : '0');
        }
        
        //Construction du string message
        //On traduit chaque octet du string pr�c�dent en un char
        String input = bytes.toString();
        StringBuilder output = new StringBuilder();
        for (int i = 0; i <= input.length() - OCTET_SIZE; i += OCTET_SIZE) {
            output.append((char)Integer.parseInt(input.substring(i, i + OCTET_SIZE), BINARY_BASE));
        }
        return output.toString();
    }

    public void coder(int iteration){
    	//Cryptage 
        for (int i = 1; i < iteration; i++) {
            this.nextGenerationUnivInfini();
        }
        //Initialisation de la cl� (g�n�ration suivant le message) 
        int size = this.getTaille();
        Cell[] key = new Cell[size];
        Cell[] neighbours = new Cell[BINARY_BASE];
        for (int i = 0; i < size; i++) {
            //Initialisation des voisins
            neighbours[0] = getArray()[(i - 1 + size) % size];
            neighbours[1] = getArray()[(i + 1) % size];
            //Nouvel �tat de la cellule
            key[i] = getArray()[i].nextState(neighbours);
        }
        setKey(key);
    }
}
