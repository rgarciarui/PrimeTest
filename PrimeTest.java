// @file: PrimeTest.java
// @author: scaperoth@gmail.com
// @description: main class to test Primes.java
// loops through from 1-2^n where n is number of iterations
// and outputs the results at each step
// 
//
package primetest;

import java.util.*;

public class PrimeTest {

    public static void main(String[] args) {
        int iterations = 40;
        Primes primes = new Primes();
        
        for(int i = 1; i<=iterations;i++){
            //set new range to 2^i
            int range = (int)Math.pow(2, i);
            
            //set output for new cycle
            System.out.println("iteration: "+i);
            System.out.println("range: "+range);
            
            //set new range
            primes.range = range;
            
            //run the program
            primes.get_stats();
            
            //output the results
            primes.output();
        }

        
    }
}
