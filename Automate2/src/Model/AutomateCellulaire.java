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
public abstract class AutomateCellulaire {

    static int DEFAULT_SIZE = 10;
    static int DEFAULT_GEN = 1;
    private int generation;

    public void setGeneration() {
        this.generation++;
    }

    public int getGeneration() {
        return this.generation;
    }

    abstract public void initArray();

    @Override
    abstract public String toString();

}
