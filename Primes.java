// @file: Primes.java
// @author: scaperoth@gmail.com
// @description: Class that finds prime numbers
// and prime number pairs within a range 0 - n
// 
// 
package primetest;

import java.util.*;

public class Primes {
    
    EratosSieve es;

    //amount of numbers to look through
    public int range;

    //number of primes so far
    public int number_primes;

    //track whether or not last number ended in 1
    private int last_prime = 0;

    //count of numbers ending in 1,3,7, or 9
    public double primes_end_1 = 0;
    public double primes_end_3 = 0;
    public double primes_end_7 = 0;
    public double primes_end_9 = 0;

    //count of pairs of a number q followed by a
    public double q_followed_by_1 = 0;
    public double q_followed_by_3 = 0;
    public double q_followed_by_7 = 0;
    public double q_followed_by_9 = 0;

    public Primes() {
        this.number_primes = 0;
        //default search is 100
        this.range = 100;
        es = new EratosSieve(range);
    }

    public Primes(int range) {
        this.range = range;
        es = new EratosSieve(range);
    }

    /**
     * set statistics for primes from 1 to n where n is the number of primes
     */
    public void run() {
        //reset vars before getting stats
        reset();

        int i = 1;

        //loop until i is finish value
        do {
            //increment i
            i++;

            //set i to the next prime value
            i = nextPrime(i);

            //update the counts with new prime
            updateStats(i);

        }while (i != -1);

    }

    /**
     * updates the statistics with prime also checks and sets if prime number is
     * 1 and increments each pair: 1-1, 1-3, 1-7, etc.
     *
     * @param prime int
     */
    public void updateStats(int prime) {
        int onesPlace = onesPlace(prime);
        boolean is_pair = false;

        if (onesPlace(last_prime) == 1) {
            is_pair = true;
        }
        
        switch (onesPlace) {
            case 1:
                //increment count of current prime
                primes_end_1++;
                //if the previous number was one
                if (is_pair) {
                    //increment count of pair
                    q_followed_by_1++;
                }
                //update what last prime is
                last_prime = prime;

                break;
            case 3:
                //increment count of current prime
                primes_end_3++;
                if (is_pair) {
                    q_followed_by_3++;
                }
                //update what last prime is
                last_prime = prime;

                break;
            case 7:
                //increment count of current prime
                primes_end_7++;
                if (is_pair) {
                    q_followed_by_7++;
                }
                //update what last prime is
                last_prime = prime;
                break;
            case 9:
                //increment count of current prime
                primes_end_9++;
                if (is_pair) {
                    q_followed_by_9++;
                }
                //update what last prime is
                last_prime = prime;
                break;
        }

    }

    /**
     * find prime number greater than or equal to current number
     *
     * @param current_number int
     * @return int
     */
    public int nextPrime(int current_number) {
        int next_prime = last_prime;

        //iterate each number within range bound
        do {
            //check if current number is prime
            if (isPrime(current_number)) {
                //increase number of primes found
                number_primes++;
                next_prime = current_number;
            }
            //if it's not, increment current number
            current_number += 1;
        } while (next_prime == last_prime && current_number <= range);

        //if we have reached the end of the output
        //return a kill value
        if (current_number > range) {
            return -1;
        }

        //return the prime number
        return next_prime;
    }
    
    public boolean isPrime(int number_to_check){
        return es.isPrime(number_to_check);
    }

    /**
     * returns the ones place of a number
     *
     * @param number int
     * @return int
     */
    public int onesPlace(int number) {
        //find ones place using modulo
        return number % 10;
    }

    /**
     * resets variables
     */
    private void reset() {
        number_primes = 0;
        primes_end_1 = 0;
        primes_end_3 = 0;
        primes_end_7 = 0;
        primes_end_9 = 0;

        q_followed_by_1 = 0;
        q_followed_by_3 = 0;
        q_followed_by_7 = 0;
        q_followed_by_9 = 0;
    }

    /**
     * Outputs results of program
     */
    public void output() {
        System.out.println("# primes: " + number_primes);
        System.out.println("#1's: " + (number_primes == 0 ? 0 : round(primes_end_1 / number_primes * 100, 2)) + "%");
        System.out.println("#3's: " + (number_primes == 0 ? 0 : round(primes_end_3 / number_primes* 100, 2)) + "%");
        System.out.println("#7's: " + (number_primes == 0 ? 0 : round(primes_end_7 / number_primes * 100, 2)) + "%");
        System.out.println("#9's: " + (number_primes == 0 ? 0 : round(primes_end_9 / number_primes * 100, 2)) + "%");

        System.out.println("1's following 1: " + (primes_end_1 == 0 ? 0 : round(q_followed_by_1 / primes_end_1 * 100, 2)) + "%");
        System.out.println("3's following 1: " + (primes_end_1 == 0 ? 0 : round(q_followed_by_3 / primes_end_1 * 100, 2)) + "%");
        System.out.println("7's following 1: " + (primes_end_1 == 0 ? 0 : round(q_followed_by_7 / primes_end_1 * 100, 2)) + "%");
        System.out.println("9's following 1: " + (primes_end_1 == 0 ? 0 : round(q_followed_by_9 / primes_end_1 * 100, 2)) + "%");

    }
    
    public double round(double number, int place){
        double modifier = Math.pow(10, place);
        return Math.round(number * modifier) / modifier;
    }
}
