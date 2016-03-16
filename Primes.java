// @file: Primes.java
// @author: scaperoth@gmail.com
// @description: Class that finds prime numbers
// and prime number pairs within a range 0 - n
// 
// 
package primetest;

import java.util.*;

public class Primes {

    //amount of numbers to look through
    public int range;

    //number of primes so far
    public int number_primes;

    //track whether or not last number ended in 1
    private int last_prime = 0;

    //count of numbers ending in 1,3,7, or 9
    public double number_end_1;
    public double number_end_3;
    public double number_end_7;
    public double number_end_9;

    //count of pairs of a number q followed by i
    public double q_followed_by_1;
    public double q_followed_by_3;
    public double q_followed_by_7;
    public double q_followed_by_9;

    public Primes() {
        this.number_primes = 0;
        this.range = 100;
    }

    public Primes(int range) {
        this.number_primes = 0;
        this.range = range;
    }

    /**
     * set statistics for primes from 1 to n where n is the number of primes
     */
    public void get_stats() {
        reset();
        int current_prime = 0;
        if (range > 2) {
            for (int i = 2; i < range; i++) {
                i = get_next_prime(i);
                update_stats(i);
            }
        }

    }

    /**
     * updates the statistics with prime also checks and sets if prime number is
     * 1 and increments each pair: 1-1, 1-3, 1-7, etc.
     *
     * @param prime int
     */
    public void update_stats(int prime) {
        int ones_place = get_ones_place(prime);
        boolean is_pair = false;

        if (last_prime == 1) {
            is_pair = true;
        }

        switch (ones_place) {
            case 1:
                //increment count of current prime
                number_end_1++;
                //if the previous number was one
                if (is_pair) {
                    //increment count of pair
                    q_followed_by_1++;
                }
                //update what last prime is
                last_prime = 1;
                
                break;
            case 3:
                //increment count of current prime
                number_end_3++;
                if (is_pair) {
                    q_followed_by_3++;
                }
                //update what last prime is
                last_prime = 3;
                
                break;
            case 7:
                //increment count of current prime
                number_end_7++;
                if (is_pair) {
                    q_followed_by_7++;
                }
                //update what last prime is
                last_prime = 7;
                break;
            case 9:
                //increment count of current prime
                number_end_9++;
                if (is_pair) {
                    q_followed_by_9++;
                }
                //update what last prime is
                last_prime = 9;
                break;
        }

    }

    /**
     * find prime number greater than or equal to current number
     *
     * @param current_number int
     * @return int
     */
    public int get_next_prime(int current_number) {
        int next_prime = 0;

        //check to make sure current prime is valid
        if (current_number <= 1) {
            return -1;
        }

        //iterate each number within range bound
        do {
            //check if current number is prime
            if (is_prime(current_number)) {
                //increase number of primes found
                number_primes++;
                next_prime = current_number;
            }
            //if it's not, increment current number
            current_number += 1;
        } while (next_prime == 0);

        //return the prime number
        return next_prime;
    }

    /**
     * returns whether or not number is prime
     * http://www.mkyong.com/java/how-to-determine-a-prime-number-in-java/
     *
     * @param prime_to_test int
     * @return boolean
     */
    private boolean is_prime(int prime_to_test) {

        //check if n is a multiple of 2
        if (prime_to_test % 2 == 0) {
            return false;
        }
        //if not, then just check the odds
        for (int i = 3; i * i <= prime_to_test; i += 2) {
            if (prime_to_test % i == 0) {
                return false;
            }
        }
        return true;

    }

    /**
     * returns the ones place of a number
     *
     * @param number int
     * @return int
     */
    public int get_ones_place(int number) {
        //find ones place using modulo
        return number % 10;
    }

    /**
     * resets variables
     */
    private void reset() {
        number_primes = 0;
        number_end_1 = 0;
        number_end_3 = 0;
        number_end_7 = 0;
        number_end_9 = 0;
        
        q_followed_by_1 = 0;
        q_followed_by_3 = 0;
        q_followed_by_7 = 0;
        q_followed_by_9 = 0;
    }

    /**
     * Outputs results of program
     */
    public void output() {

        System.out.println("#1's: " + number_end_1 / number_primes * 100 + "%");
        System.out.println("#3's: " + number_end_3 / number_primes * 100 + "%");
        System.out.println("#7's: " + number_end_7 / number_primes * 100 + "%");
        System.out.println("#9's: " + number_end_9 / number_primes * 100 + "%");

        System.out.println("1's following 1: " + q_followed_by_1 / number_end_1 * 100 + "%");
        System.out.println("3's following 1: " + q_followed_by_3 / number_end_1 * 100 + "%");
        System.out.println("7's following 1: " + q_followed_by_7 / number_end_1 * 100 + "%");
        System.out.println("9's following 1: " + q_followed_by_9 / number_end_1 * 100 + "%");

    }
}
