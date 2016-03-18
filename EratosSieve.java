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
 *
 * method to use to find the prime Methods are: 1 = Linked List, 2 = Array List,
 * 3 = 1D array, 4 = 2D array
 */
package primetest;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.ArrayList;

public class EratosSieve {

    public boolean[] Sieve;
    public boolean[][] Sieve2D;

    // create a linked list for Sieve
    LinkedList<Boolean> llSieve = new LinkedList<Boolean>();

    // create a array list for Sieve
    ArrayList<Boolean> AlSieve = new ArrayList<Boolean>();

    public int N;
    public static final int MAX_SIZE = Integer.MAX_VALUE - 1;

    /**
     * method to use to find the prime Methods are: 1 = Linked List, 2 = Array
     * List, 3 = 1D array, 4 = 2D array.
     */
    public static final int METHOD = 2;

    public EratosSieve(int range) {
        this.N = range;

        switch (METHOD) {
            case 1:
                //using linked lists
                generateLLEratosSieve();
                break;
            case 2:
                //using array list
                generateALEratosSieve();
                break;
            case 3:
                //using arrays
                generateArrayEratosSieve();
                break;

            case 4:
                //using 2d arrays
                generate2DArrayEratosSieve();
                break;
            default:
                //using arrays
                generateArrayEratosSieve();
                break;
        }
    }

    /**
     * build a boolean array that indicates prime numbers
     */
    public void generateArrayEratosSieve() {

        try {
            // initially assume all integers are prime
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
     * build a boolean linked list that indicates prime numbers
     */
    public void generateLLEratosSieve() {
        try {
            // initially assume all integers are prime

            for (int i = 0; i <= N; i++) {
                llSieve.add(true);
            }

            // mark non-primes <= N using Sieve of Eratosthenes
            for (int i = 2; i * i <= N; i++) {

                // if i is prime, then mark multiples of i as nonprime
                // suffices to consider mutiples i, i+1, ..., N/i
                if (llSieve.get(i)) {
                    for (int j = i; i * j <= N; j++) {
                        llSieve.set(i * j, false);
                    }
                }
            }
        } catch (NegativeArraySizeException ex) {
            System.out.println("cannot create array larger than " + Integer.MAX_VALUE);
        }
    }

    /**
     * build a boolean array list that indicates prime numbers
     */
    public void generateALEratosSieve() {
        try {
            // initially assume all integers are prime

            for (int i = 0; i <= N; i++) {
                AlSieve.add(true);
            }

            // mark non-primes <= N using Sieve of Eratosthenes
            for (int i = 2; i * i <= N; i++) {

                // if i is prime, then mark multiples of i as nonprime
                // suffices to consider mutiples i, i+1, ..., N/i
                if (AlSieve.get(i)) {
                    for (int j = i; i * j <= N; j++) {
                        AlSieve.set(i * j, false);
                    }
                }
            }
        } catch (NegativeArraySizeException ex) {
            System.out.println("cannot create array larger than " + Integer.MAX_VALUE);
        }
    }

    /**
     * build a boolean linked list of arrays that indicate prime numbers
     */
    public void generate2DArrayEratosSieve() {
        try {
            //create bounds for 2D array
            int rows = N < MAX_SIZE ? N + 1 : MAX_SIZE;
            int cols = N < MAX_SIZE ? 1 : N - MAX_SIZE;
            //System.out.println("Rows: "+rows+" Cols: "+cols);

            // initially assume all integers are prime
            Sieve2D = new boolean[cols][rows];

            for (int i = 2; i <= N; i++) {
                //if i is outside bounds, then move to next row
                if (i > Integer.MAX_VALUE) {
                    Sieve2D[1][i - Integer.MAX_VALUE] = true;
                } else {
                    Sieve2D[0][i] = true;
                }
            }
            for (int k = 0; k < cols; k++) {
                // mark non-primes <= N using Sieve of Eratosthenes
                for (int i = 2; i * i <= N; i++) {

                    // if i is prime, then mark multiples of i as nonprime
                    // suffices to consider mutiples i, i+1, ..., N/i
                    if (Sieve2D[k][i]) {
                        for (int j = i; i * j <= N; j++) {
                            if (i * j > Integer.MAX_VALUE) {
                                Sieve2D[1][(i * j) - Integer.MAX_VALUE] = false;
                            } else {
                                Sieve2D[0][i * j] = false;
                            }
                        }
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
        boolean return_value = false;

        /**
         * method to use to find the prime Methods are: 1 = Linked List, 2 =
         * Array List, 3 = 1D array, 4 = 2D array.
         */
        switch (METHOD) {
            case 1:
                //using linked lists
                return_value = llSieve.get(number_to_test);
                break;
            case 2:
                //using linked lists
                return_value = AlSieve.get(number_to_test);
                break;
            case 3:
                //using arrays
                return_value = Sieve[number_to_test];
                break;

            case 4:
                if (number_to_test > Integer.MAX_VALUE) {
                    return_value = Sieve2D[1][number_to_test - Integer.MAX_VALUE];
                }

                return_value = Sieve2D[0][number_to_test];
                break;
            default:
                //using arrays
                return_value = Sieve[number_to_test];
                break;
        }

        return return_value;

    }
}
