package com.lfsr;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jatapiaro
 */
public class LFSR {
    
    private int intialSeed, newSeed;
    private int tap;
    private String initialBinarySeed, newBinarySeed;
    private List<Step> stepList;
    
    
    public LFSR( String initialBinarySeed ) {
        this.initialBinarySeed = initialBinarySeed;
        this.intialSeed = Integer.parseInt(initialBinarySeed, 2);
        this.tap = 0;
    }
    
    public LFSR ( int initialSeed ) {
        this.intialSeed = initialSeed;
        this.initialBinarySeed = Integer.toBinaryString(this.intialSeed);
        this.tap = 0;
    }
    
    public LFSR( String initialBinarySeed, int tap ) {
        this.initialBinarySeed = initialBinarySeed;
        this.intialSeed = Integer.parseInt(initialBinarySeed, 2);
        this.tap = tap;
    }
    
    public LFSR ( int initialSeed, int tap ) {
        this.intialSeed = initialSeed;
        this.initialBinarySeed = Integer.toBinaryString(this.intialSeed);
        this.tap = tap;
    }
    
    public void makeProcess() {
        
        int auxSeed = new Integer(this.intialSeed);
        String auxBinarySeed = this.initialBinarySeed;
        
        this.stepList = new ArrayList<Step>();
        int binarySeedLength = auxBinarySeed.length();
        int realTap = (binarySeedLength - tap) - 1;
        
        
        while( true ) {
            
            int a = Character.getNumericValue(auxBinarySeed.charAt(realTap));
            int b = Character.getNumericValue(auxBinarySeed.charAt(0));
            int c = a ^ b;
            String newBinaryString = auxBinarySeed.substring(1, binarySeedLength) + c;
            int newSeed = Integer.parseInt(newBinaryString, 2);
            
            Step s = new Step(auxSeed, auxBinarySeed, b, newSeed, newBinaryString, a);
            
            auxBinarySeed = newBinaryString;
            auxSeed = newSeed;
           
            if ( !stepList.contains(s) ) {
                getStepList().add(s);
            } else {
                break;
            }
        }
           
    }

    /**
     * @return the intialSeed
     */
    public int getIntialSeed() {
        return intialSeed;
    }

    /**
     * @param intialSeed the intialSeed to set
     */
    public void setIntialSeed(int intialSeed) {
        this.intialSeed = intialSeed;
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
     * @return the tap
     */
    public int getTap() {
        return tap;
    }

    /**
     * @param tap the tap to set
     */
    public void setTap(int tap) {
        this.tap = tap;
    }

    /**
     * @return the initialBinarySeed
     */
    public String getInitialBinarySeed() {
        return initialBinarySeed;
    }

    /**
     * @param initialBinarySeed the initialBinarySeed to set
     */
    public void setInitialBinarySeed(String initialBinarySeed) {
        this.initialBinarySeed = initialBinarySeed;
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

    /**
     * @return the stepList
     */
    public List<Step> getStepList() {
        return stepList;
    }
    
    @Override
    public String toString() {
        return this.intialSeed + " : " + this.initialBinarySeed;
    }
    
}
