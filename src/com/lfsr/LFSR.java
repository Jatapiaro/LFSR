package com.lfsr;

import java.util.ArrayList;
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
 * Nombre Archivo: LFSR.java
 * Archivos relacionados: Step.java
 * Ejecucion: Acceder a https://seguridad-informatica.herokuapp.com/
 *  y seleccionar el proyecto LFSR
 * Descripción: Programa encargado de simular el comportamiento de un LFSR,
 *  guardando así todos los pasos hasta que se repita la semilla.
 * 
 * @author jatapiaro
 */
public class LFSR {
    
    /*
    * @param int initialSeed semilla inicial en decimal
    * @param List<Integer> taps, lista de taps para realizar el xor
    * @param String initialBinarySeed, semilla inicial en binario
    * @param List<Step> stepList, lista de pasos que realizo el procedimiento
    */
    private int intialSeed;
    private List<Integer> taps;
    private String initialBinarySeed;
    private List<Step> stepList;
    
    /*
    * Constructor
    * @param String initialBinarySeed, semilla inicial en binario
    */
    public LFSR( String initialBinarySeed ) {
        this.initialBinarySeed = initialBinarySeed;
        //Convierte la semilla de un String a un entero
        this.intialSeed = Integer.parseInt(initialBinarySeed, 2);
        //Inicializa los pasos y los taps
        this.taps = new ArrayList<Integer>();
        this.stepList = new ArrayList<Step>();
    }
    
    /*
    * Constructor
    * @param int initialSEED, semilla inicial como un entero
    */
    public LFSR ( int initialSeed ) {
        this.intialSeed = initialSeed;
        /*
        * Convierte el entero a una cadena de carácteres con su equivalente en binario
        * Posteriormente si el número de cáracteres de esa conversión es menor a 4
        * agrega los 0's restantes.
        */
        this.initialBinarySeed = Integer.toBinaryString(this.intialSeed);
        while( this.initialBinarySeed.length() < 4 ) {
            this.initialBinarySeed = "0" + this.initialBinarySeed;
        } 
        //Inicializa los pasos y los taps
        this.taps = new ArrayList<Integer>();
        this.stepList = new ArrayList<Step>();
    }
    
    /*
    * Realiza el proceso de LFSR
    */
    public void makeProcess() {
        
        /* Si no hay ningún tap, no realiza la ejecución */
        if ( this.taps.size() == 0 ) {
            return;
        }
        
        /*
        * Variables auxiliares para guardar nuestro punto de inicio
        * y asignar el siguiente paso al que hemos llegado.
        */
        int auxSeed = new Integer(this.intialSeed);
        String auxBinarySeed = this.initialBinarySeed;
        
        /* Inicializamos nuestros steps */
        this.stepList = new ArrayList<Step>();
        
        /* Obtenemos el tamaño/numero de bits de nuestra semilla */
        int binarySeedLength = auxBinarySeed.length();
        /*
        * Iniciaizamos los 'realTaps' y una variable que indica si será nuestra 
        *  primer iteración.
        * También inicializamos la variable donde se guardara el resultado 
        * del xor de los taps. (Recordemos que el xor es asociativo)
        */
        List<Integer> realTaps = new ArrayList<Integer>();
        boolean s = false;
        
        int tapsXor = -1;
        
        /*
        * Para cada uno de los taps
        */
        for ( int t : taps ) {
            /*
            * RealTap, ejemplo, si tenemos [0][1][0][1]
            * y escogemos el tap 1, nos quedariamos con el segundo [0]
            * pues contamos de derecha a izquierda ls posiciones es decir
            * [3][2][1][0]
            * Ahora el real tap es igual al tamaño de la semilla, menos el tap 't'
            * en este caso t = 1, tamanoSemilla = 4 (4-1) = 3
            * El -1 a ese resultado es para que no se salga de la dimensión del arreglo
            * por lo tanto 3-1 = 2. Como el string en código lo leemos de izquierda 
            * a derecha tendriamos binaryString.charAt(2) = [0]
            */
            int realTap = (binarySeedLength - t) - 1;
            //Obtenemos el valor númerico del caracter obtenido
            int res = Character.getNumericValue(auxBinarySeed.charAt(realTap));
            //Lo agregamos a la lista de taps
            realTaps.add(res);
            if ( s == false ) {
                /*
                * Si es la primera vez que pasamos por aqui
                * solo asigna a tapsXor el primer tap.
                */
                s = true;
                tapsXor = res;
            } else {
                /*
                * Si ya hemos pasado por aquí, a tapsXor aplicale un xor 
                * con el siguiente tap
                */
                tapsXor = tapsXor ^ res;
            }
        }
        
        /*
        * Esta parte se ejecutará mientras
        * no salga una nueva semilla o random repetido.
        */
        while( true ) {
            
            int a = tapsXor;
            /* 
            * Obtenemos el cáracter que va a salir de la semilla 
            * y aplicamos el xor con el resultado del xor de todos los taps
            * guardando el resultado en c
            */
            int b = Character.getNumericValue(auxBinarySeed.charAt(0));
            int c = a ^ b;
            /*
            * Nuestra nueva semilla será
            * la semilla anterior sin el primer elemento
            * y añadiendo al final de ella el resultado obtenido en el xor
            */
            String newBinaryString = auxBinarySeed.substring(1, binarySeedLength) + c;
            int newSeed = Integer.parseInt(newBinaryString, 2);
            
            /*
            * Creamos una variable auxiliar st de la clase Step
            * donde guardamos que semilla teniamos, y que semilla generamos
            */
            Step st = new Step(auxSeed, auxBinarySeed, b, newSeed, newBinaryString, realTaps);
            /* Reasignamos nuestras variables para la siguiente iteración */
            auxBinarySeed = newBinaryString;
            auxSeed = newSeed;
           
            /* 
            * Si la lista de pasos no contiene nuestro paso la agregamos 
            * caso contrario detenemos la ejecución.
            */
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
    
    /*
    * Añade un tap a la simulación
    * @param tap
    */
    public void addTap( int tap ) {
        /*
        * Si el tap es mayor o igual que el numero de bits de la semilla
        * no lo agregamos
        */
        if ( tap >= this.initialBinarySeed.length()-1 ){
            return;
        }
        if ( !this.taps.contains(tap) ) {
            this.taps.add(tap);
        }
    }
    
    /*
    * Resetea los taps, es decir, limpia la lista de taps a usar
    */
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
