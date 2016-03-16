// @file: PrimeTest.java
// @author: scaperoth@gmail.com
// @description: main class to test primes.test
//
package primetest;

import java.util.*;

public class PrimeTest {

    public static void main(String[] args) {
        int iterations = 30;
        Primes primes = new Primes();
        
        for(int i = 1; i<=iterations;i++){
            int range = (int)Math.pow(2, i);
            System.out.println("iteration: "+i);
            System.out.println("range: "+range);
            primes.set_range(range);
            primes.get_stats();
            primes.output();
        }

        
    }
}
