package model;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Greg
 * Classe d'automate cellulaire qui en définit les attributs et les méthodes principales
 */
public abstract class AutomateCellulaire {

    static final int DEFAULT_SIZE = 10;
    static final int DEFAULT_GEN = 1;
    private int generation;

    public void setGeneration() {
        this.generation++;
    }

    public int getGeneration() {
        return this.generation;
    }

    //Méthode permettant d'initialiser l'univers de l'automate
    public abstract void initArray();

}
