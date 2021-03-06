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
        int iterations = 31;
        for (int i = 1; i <= iterations; i++) {
            //set new range to 2^i
            int range = (int) Math.pow(2, i);
            //set output for new cycle
            System.out.println("iteration: " + i);
            System.out.println("range: " + range);
            
            Primes primes = new Primes(range);

            //run the program
            primes.run();

            //output the results
            primes.output();
        }
    }
}
