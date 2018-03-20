package com.lfsr;

import java.util.List;

/**
 * This class is in change to keep
 * a record of how the lfsr was evolving
 * during the iterations
 * @author jacobotapia
 */
public class Step {
    
    private int seed, newSeed, outBit;
    private String binarySeed, newBinarySeed;
    private List<Integer> taps;
    
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
