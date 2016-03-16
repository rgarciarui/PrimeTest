// @file: PrimeTest.java
// @author: scaperoth@gmail.com
// @description: main class to test primes.test
//
package primetest;

import java.util.*;

public class PrimeTest {

    public static void main(String[] args) {
        int iterations = 10;
        Primes primes = new Primes();
        
        for(int i = 1; i<iterations;i++){
            System.out.println("iteration: "+i);
            primes.set_range((int)Math.pow(10, i));
            primes.set_number_primes(0);
            primes.get_stats();
            primes.output();
        }

        
    }
}
