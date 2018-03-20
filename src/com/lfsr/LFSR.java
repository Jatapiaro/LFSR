package com.lfsr;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jatapiaro
 */
public class LFSR {
    
    private int intialSeed;
    private List<Integer> taps;
    private String initialBinarySeed;
    private List<Step> stepList;
    
    
    public LFSR( String initialBinarySeed ) {
        this.initialBinarySeed = initialBinarySeed;
        this.intialSeed = Integer.parseInt(initialBinarySeed, 2);
        this.taps = new ArrayList<Integer>();
        this.stepList = new ArrayList<Step>();
    }
    
    public LFSR ( int initialSeed ) {
        this.intialSeed = initialSeed;
        this.initialBinarySeed = Integer.toBinaryString(this.intialSeed);
        while( this.initialBinarySeed.length() < 4 ) {
            this.initialBinarySeed = "0" + this.initialBinarySeed;
        } 
        this.taps = new ArrayList<Integer>();
        this.stepList = new ArrayList<Step>();
    }
    
    /*
    * Make all the process
    * when a cycle is completed, I mean
    * when we get a repeated random we stop the process
    */
    public void makeProcess() {
        
        if ( this.taps.size() == 0 ) {
            return;
        }
        
        int auxSeed = new Integer(this.intialSeed);
        String auxBinarySeed = this.initialBinarySeed;
        
        this.stepList = new ArrayList<Step>();
        int binarySeedLength = auxBinarySeed.length();
        List<Integer> realTaps = new ArrayList<Integer>();
        boolean s = false;
        int tapsXor = -1;
        for ( int t : taps ) {
            int realTap = (binarySeedLength - t) - 1;
            int res = Character.getNumericValue(auxBinarySeed.charAt(realTap));
            realTaps.add(res);
            if ( s == false ) {
                s = true;
                tapsXor = res;
            } else {
                tapsXor = tapsXor ^ res;
            }
        }
        
        
        while( true ) {
            
            int a = tapsXor;
            int b = Character.getNumericValue(auxBinarySeed.charAt(0));
            int c = a ^ b;
            String newBinaryString = auxBinarySeed.substring(1, binarySeedLength) + c;
            int newSeed = Integer.parseInt(newBinaryString, 2);
            
            Step st = new Step(auxSeed, auxBinarySeed, b, newSeed, newBinaryString, realTaps);
            auxBinarySeed = newBinaryString;
            auxSeed = newSeed;
           
            if ( !stepList.contains(st) ) {
                getStepList().add(st);
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
        this.initialBinarySeed = Integer.toBinaryString(this.intialSeed);
        while( this.initialBinarySeed.length() < 4 ) {
            this.initialBinarySeed = "0" + this.initialBinarySeed;
        } 
        this.taps = new ArrayList<Integer>();;
    }
    
    public void addTap( int tap ) {
        if ( tap >= this.initialBinarySeed.length()-1 ){
            return;
        }
        if ( !this.taps.contains(tap) ) {
            this.taps.add(tap);
        }
    }
    
    public void resetTaps() {
        this.taps = new ArrayList<Integer>();
    }

    /**
     * @return the tap
     */
    public List<Integer> getTaps() {
        return taps;
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
        this.intialSeed = Integer.parseInt(initialBinarySeed, 2);
        this.taps = new ArrayList<Integer>();
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
