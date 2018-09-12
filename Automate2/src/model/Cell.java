package model;

public class Cell {

    //Attributs
    private boolean state;
    private boolean previousState; //Etat de la cellule en t-2
    char etat;

    //Constructeurs
    public Cell() {
        //Par défaut, une nouvelle Cellule est morte
        this.state = false;
        this.previousState = false;

    }

    public Cell(boolean state, boolean previousState) {
        this.setState(state);
        this.previousState = previousState;
    }

    //Méthodes
    public boolean isAlive() {
        return this.state;
    }

    public boolean isAlivePrev() {
        return this.previousState;
    }

    public void setState(boolean state) {
        if (state) {
            this.etat = '█';
        } else {
            this.etat = '░';
        }
        this.previousState = this.isAlive();
        this.state = state;
    }

    public void setPreviousState(boolean state) {
        this.previousState = state;
    }

    public Cell nextState(Cell[] neighbours) {
        int nbAlive = 0;
        int indice = 0;
        while (indice < neighbours.length) {
            if (neighbours[indice].isAlive()) {
                nbAlive++;
            }
            indice++;
        }
        if (this.isAlive() && nbAlive == 1) {
            return new Cell(true ^ this.previousState, this.state);
        } else if (!this.isAlive() && nbAlive != 1) {
            return new Cell(true ^ this.previousState, this.state);
        } else {
            return new Cell(false ^ this.previousState, this.state);
        }
    }

    public Cell nextStateInv(Cell[] neighbours) {
        int nbAlive = 0;
        int indice = 0;
        while (indice < neighbours.length) {
            if (neighbours[indice].isAlive()) {
                nbAlive++;
            }
            indice++;
        }
        if (!this.isAlive() && ((nbAlive == 1 && this.previousState) || (nbAlive != 1 && !this.previousState))) {
            return new Cell(true, this.state);
        } else if (this.isAlive() && ((nbAlive == 1 && !this.previousState) || (nbAlive != 1 && this.previousState))) {
            return new Cell(true, this.state);
        } else {
            return new Cell(false, this.state);
        }
    }
}
