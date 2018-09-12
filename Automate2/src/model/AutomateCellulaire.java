package model;

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

    static int defaultSize = 10;
    static int defaultGen = 1;
    private int generation;

    public void setGeneration() {
        this.generation++;
    }

    public int getGeneration() {
        return this.generation;
    }

    public abstract void initArray();

    @Override
    public abstract String toString();

}
