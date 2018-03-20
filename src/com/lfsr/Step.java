package com.lfsr;

import java.util.List;

/**
 *
 * Proyecto 1 - Seguridad Informática
 * Simulador de LFSR
 * Autor: Jacobo Misael Tapia de la Rosa
 * Matricula: 1336590   Carrera: ITC-11
 * Correo Electronico: A01336590@itesm.mx
 * Fecha de creacion: 18-mar-2018
 * Fecha última modificiacion: 20-mae-2018
 * Nombre Archivo: Step.java
 * Descripción: Clase para guardar los pasos de la simulación que se ha ido ejecutando.
 * 
 * @author jatapiaro
 */
public class Step {
    
    /*
    * @param int seed, semilla con la que se genera newSeed
    * @param int newSeed, nueva semilla generada tras el proceso de seed
    * @param outBit, bit que se removio de la semilla inicial
    * @param String binarySeed, valor de seed en binario
    * @param String newBinarySeed, valor de newSeed en binario
    * @param List<Integer> taps, las posiciones de tap usadas para el proceso
    */
    private int seed, newSeed, outBit;
    private String binarySeed, newBinarySeed;
    private List<Integer> taps;
    
    /*
    * Constructor
    * @param int seed, semilla con la que se genera newSeed
    * @param int newSeed, nueva semilla generada tras el proceso de seed
    * @param outBit, bit que se removio de la semilla inicial
    * @param String binarySeed, valor de seed en binario
    * @param String newBinarySeed, valor de newSeed en binario
    * @param List<Integer> taps, las posiciones de tap usadas para el proceso
    */
    public Step(int seed, String binarySeed, int outBit, int newSeed, String newBinarySeed,
            List<Integer> taps) {
        this.seed = seed;
        this.binarySeed = binarySeed;
        this.newSeed = newSeed;
        this.outBit = outBit;
        this.newSeed = newSeed;
        this.newBinarySeed = newBinarySeed;
        this.taps = taps;
    }

    /**
     * @return the seed
     */
    public int getSeed() {
        return seed;
    }

    /**
     * @param seed the seed to set
     */
    public void setSeed(int seed) {
        this.seed = seed;
    }

    /**
     * @return the newSeed
     */
    public int getNewSeed() {
        return newSeed;
    }

    /**
     * @param newSeed the newSeed to set
     */
    public void setNewSeed(int newSeed) {
        this.newSeed = newSeed;
    }

    /**
     * @return the taps
     */
    public List<Integer> getTaps() {
        return taps;
    }

    /**
     * @param taps the taps to set
     */
    public void setTaps(List<Integer> taps) {
        this.taps = taps;
    }

    /**
     * @return the outBit
     */
    public int getOutBit() {
        return outBit;
    }

    /**
     * @param outBit the outBit to set
     */
    public void setOutBit(int outBit) {
        this.outBit = outBit;
    }

    /**
     * @return the binarySeed
     */
    public String getBinarySeed() {
        return binarySeed;
    }

    /**
     * @param binarySeed the binarySeed to set
     */
    public void setBinarySeed(String binarySeed) {
        this.binarySeed = binarySeed;
    }

    /**
     * @return the newBinarySeed
     */
    public String getNewBinarySeed() {
        return newBinarySeed;
    }

    /**
     * @param newBinarySeed the newBinarySeed to set
     */
    public void setNewBinarySeed(String newBinarySeed) {
        this.newBinarySeed = newBinarySeed;
    }
    
    @Override
    public boolean equals(Object o) {
        /*
        * Si el valor de la semilla, es igual al de otra semilla
        * entonces ambos objetos son iguales.
        */
        return ((Step)o).seed == this.seed;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.binarySeed);
        sb.append(": ");
        sb.append(this.outBit + "^");
        int counter = 0;
        for( int i : this.taps ) {
            sb.append(i);
            if ( counter < this.taps.size()-1 ) {
                sb.append("^");
            }
            counter++;
        }
        sb.append(" --> ");
        sb.append(this.newBinarySeed);
        return sb.toString();
    }
    
}
