/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author scaperoth@gmail.com
 * @description builds a sieve of Eratosthenes style boolean array based on the
 * code from http://introcs.cs.princeton.edu/java/14array/PrimeSieve.java.html
 */
package primetest;

import java.util.Arrays;

public class EratosSieve {

    public boolean[] Sieve;
    public int N;

    public EratosSieve(int range) {
        this.N = range;

        // initially assume all integers are prime
        try {
            Sieve = new boolean[N + 1];

            for (int i = 2; i <= N; i++) {
                Sieve[i] = true;
            }

            // mark non-primes <= N using Sieve of Eratosthenes
            for (int i = 2; i * i <= N; i++) {

            // if i is prime, then mark multiples of i as nonprime
                // suffices to consider mutiples i, i+1, ..., N/i
                if (Sieve[i]) {
                    for (int j = i; i * j <= N; j++) {
                        Sieve[i * j] = false;
                    }
                }
            }
        } catch (NegativeArraySizeException ex) {
            System.out.println("cannot create array larger than " + Integer.MAX_VALUE);
        }
    }

    /**
     * returns the ith value of the Sieve as boolean
     *
     * @param number_to_test
     * @return boolean
     */
    public boolean isPrime(int number_to_test) {
        //System.out.println(number_to_test);
        //System.out.println(Arrays.toString(Sieve));
        //System.out.println(Sieve[number_to_test]);
        return Sieve[number_to_test];
    }
}
