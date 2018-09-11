package Model;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Greg
 */
public class AutomateCellulaire2D extends AutomateCellulaire {

    private int row;
    private int column;
    private Cell[][] tabCell;

    public AutomateCellulaire2D() {
        this.row = DEFAULT_SIZE;
        this.column = DEFAULT_SIZE;
        this.tabCell = new Cell[this.row][this.column];
    }

    public AutomateCellulaire2D(int row, int col) {
        this.row = row;
        this.column = col;
        this.tabCell = new Cell[this.row][this.column];
    }

    public int getRow() {
        return this.row;
    }

    public int getColumn() {
        return this.column;
    }

    public void setArray(Cell[][] array) {
        this.tabCell = array;
    }

    public Cell[][] getArray() {
        return this.tabCell;
    }

    @Override
    public void initArray() {
        for (int i = 0; i < this.getRow(); i++) {
            for (int j = 0; j < this.getColumn(); j++) {
                this.tabCell[i][j] = new Cell(Math.random() < 0.5, false);
            }
        }
    }

    public void nextGenerationUnivFini() {
        //Ligne 0 - Ligne size-1 - Colonne 0 - Colonne size-1

        Cell cellDead = new Cell();
        int sizeR = this.getRow();
        int sizeC = this.getColumn();
        Cell[][] nextGen = new Cell[sizeR][sizeC];
        Cell[] neighbours = new Cell[8];

        for (int i = 0; i < sizeR; i++) {
            for (int j = 0; j < sizeC; j++) {
                //Initialisation des voisins en cellule morte.
                for (int k = 0; k < 8; k++) {
                    neighbours[k] = cellDead;
                }
                if (i == 0) {
                    if (j == 0) {
                        //Coin Haut-Gauche 0/1/2/3/5 HS
                        neighbours[4] = this.tabCell[i][j + 1];
                        neighbours[6] = this.tabCell[i + 1][j];
                        neighbours[7] = this.tabCell[i + 1][j + 1];
                    } else if (j == sizeC - 1) {
                        //Coin Haut-Droit 0/1/2/4/7 HS
                        neighbours[3] = this.tabCell[i][j - 1];
                        neighbours[5] = this.tabCell[i + 1][j - 1];
                        neighbours[6] = this.tabCell[i + 1][j];
                    } else {
                        //Première ligne 0/1/2 HS
                        neighbours[3] = this.tabCell[i][j - 1];
                        neighbours[4] = this.tabCell[i][j + 1];
                        neighbours[5] = this.tabCell[i + 1][j - 1];
                        neighbours[6] = this.tabCell[i + 1][j];
                        neighbours[7] = this.tabCell[i + 1][j + 1];
                    }
                } else if (i == sizeR - 1) {
                    if (j == 0) {
                        //Coin Bas-Gauche 0/3/5/6/7 HS
                        neighbours[1] = this.tabCell[i - 1][j];
                        neighbours[2] = this.tabCell[i - 1][j + 1];
                        neighbours[4] = this.tabCell[i][j + 1];
                    } else if (j == sizeC - 1) {
                        //Coin Bas-Droit 2/4/5/6/7 HS
                        neighbours[0] = this.tabCell[i - 1][j - 1];
                        neighbours[1] = this.tabCell[i - 1][j];
                        neighbours[3] = this.tabCell[i][j - 1];
                    } else {
                        //Dernière ligne 5/6/7 HS
                        neighbours[0] = this.tabCell[i - 1][j - 1];
                        neighbours[1] = this.tabCell[i - 1][j];
                        neighbours[2] = this.tabCell[i - 1][j + 1];
                        neighbours[3] = this.tabCell[i][j - 1];
                        neighbours[4] = this.tabCell[i][j + 1];
                    }
                } else if (j == 0) {
                    //Première colonne 0/3/5 HS
                    neighbours[1] = this.tabCell[i - 1][j];
                    neighbours[2] = this.tabCell[i - 1][j + 1];
                    neighbours[4] = this.tabCell[i][j + 1];
                    neighbours[6] = this.tabCell[i + 1][j];
                    neighbours[7] = this.tabCell[i + 1][j + 1];
                } else if (j == sizeC - 1) {
                    //Dernière colonne 2/4/7 HS
                    neighbours[0] = this.tabCell[i - 1][j - 1];
                    neighbours[1] = this.tabCell[i - 1][j];
                    neighbours[3] = this.tabCell[i][j - 1];
                    neighbours[5] = this.tabCell[i + 1][j - 1];
                    neighbours[6] = this.tabCell[i + 1][j];
                } else {
                    //Case interne
                    neighbours[0] = this.tabCell[i - 1][j - 1];
                    neighbours[1] = this.tabCell[i - 1][j];
                    neighbours[2] = this.tabCell[i - 1][j + 1];
                    neighbours[3] = this.tabCell[i][j - 1];
                    neighbours[4] = this.tabCell[i][j + 1];
                    neighbours[5] = this.tabCell[i + 1][j - 1];
                    neighbours[6] = this.tabCell[i + 1][j];
                    neighbours[7] = this.tabCell[i + 1][j + 1];
                }
                //Nouvel état de la cellule
                nextGen[i][j] = this.tabCell[i][j].nextState(neighbours);
            }
        }

        setArray(nextGen);
        setGeneration();
    }

    public void nextGenerationUnivInfini() {
        //Ligne 0 - Ligne size-1 - Colonne 0 - Colonne size-1

        int sizeR = this.getRow();
        int sizeC = this.getColumn();
        Cell[][] nextGen = new Cell[sizeR][sizeC];
        Cell[] neighbours = new Cell[8];
        for (int i = 0; i < sizeR; i++) {
            for (int j = 0; j < sizeC; j++) {
                //Initialisation des voisins
                neighbours[0] = this.tabCell[(i - 1 + sizeR) % sizeR][(j - 1 + sizeC) % sizeC];
                neighbours[1] = this.tabCell[(i - 1 + sizeR) % sizeR][j];
                neighbours[2] = this.tabCell[(i - 1 + sizeR) % sizeR][(j + 1) % sizeC];
                neighbours[3] = this.tabCell[i][(j - 1 + sizeC) % sizeC];
                neighbours[4] = this.tabCell[i][(j + 1) % sizeC];
                neighbours[5] = this.tabCell[(i + 1) % sizeR][(j - 1 + sizeC) % sizeC];
                neighbours[6] = this.tabCell[(i + 1) % sizeR][j];
                neighbours[7] = this.tabCell[(i + 1) % sizeR][(j + 1) % sizeC];
                //Nouvel état de la cellule
                nextGen[i][j] = this.tabCell[i][j].nextState(neighbours);
            }
        }
        setArray(nextGen);
        setGeneration();

    }

    @Override
    public String toString() {
        String res = "";
        res += "Génération : " + getGeneration() + "\n";
        for (int i = 0; i < this.getRow(); i++) {
            for (int j = 0; j < this.getColumn(); j++) {
                res += this.tabCell[i][j].etat + " ";
            }
            res += "\n";
        }

        return res;
    }

}
