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
    private int range;

    //number of primes so far
    private int number_primes;

    //count of numbers ending in 1,3,7, or 9
    private double number_end_1;
    private double number_end_3;
    private double number_end_7;
    private double number_end_9;

    //count of pairs of 1 followed by i
    private double one_followed_by_1;
    private double one_followed_by_3;
    private double one_followed_by_7;
    private double one_followed_by_9;

    //track whether or not last number ended in 1
    private boolean is_one = false;

    public Primes() {
        this.number_primes = 0;
        this.range = 100;
    }

    public Primes(int range) {
        this.number_primes = 0;
        this.range = range;
    }
    
    /**
     * set statistics for primes from 1 to n
     * where n is the number of primes
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
     * @param prime     int
     */
    public void update_stats(int prime) {
        int ones_place = get_ones_place(prime);

        switch (ones_place) {
            case 1:
                //increment count of current prime
                number_end_1++;
                //if the previous number was one
                if (is_one) {
                    //increment count of pair
                    one_followed_by_1++;
                }

                is_one = true;
                break;
            case 3:
                number_end_3++;
                if (is_one) {
                    one_followed_by_3++;
                    is_one = false;
                }
                break;
            case 7:
                number_end_7++;
                if (is_one) {
                    one_followed_by_7++;
                    is_one = false;
                }
                break;
            case 9:
                number_end_9++;
                if (is_one) {
                    one_followed_by_9++;
                    is_one = false;
                }
                break;
        }

    }

    /**
     * find prime number greater than or equal to 
     * current number
     * @param current_number    int
     * @return                  int
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
     * @param prime_to_test int
     * @return              boolean
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
     * returns the ones place of a number using recursion
     * http://stackoverflow.com/questions/9962420/how-to-get-the-place-of-a-number-in-java-eg-tens-thousands-etc
     * @param number    int
     * @return          int
     */
    public int get_ones_place(int number) {

        //if number is small enough, return it
        if (number < 10) {
            return number;
        }

        //find the log-base-10 of the number
        int tenthPower = (int) Math.floor(Math.log10(number));
        //raise 10 to the power of the log to get size of number
        int place = (int) Math.pow(10, tenthPower);
        //run the program again. recurse until number is < 10
        place = get_ones_place((number % place));

        //find ones place using modulo
        return place;
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

        one_followed_by_1 = 0;
        one_followed_by_3 = 0;
        one_followed_by_7 = 0;
        one_followed_by_9 = 0;
    }

    /**
     * set range
     * @param range int
     */
    public void set_range(int range) {
        this.range = range;
    }

    /**
     * get range
     * @return  int 
     */
    public int get_range() {
        return this.range;
    }

    /**
     * get number of primes so far
     * @return   int
     */
    public int get_number_primes() {
        return this.number_primes;
    }

    /**
     * Outputs results of program
     */
    public void output() {

        System.out.println("#1's: " + number_end_1 / number_primes * 100 + "%");
        System.out.println("#3's: " + number_end_3 / number_primes * 100 + "%");
        System.out.println("#7's: " + number_end_7 / number_primes * 100 + "%");
        System.out.println("#9's: " + number_end_9 / number_primes * 100 + "%");

        System.out.println("1's following 1: " + one_followed_by_1 / number_end_1 * 100 + "%");
        System.out.println("3's following 1: " + one_followed_by_3 / number_end_1 * 100 + "%");
        System.out.println("7's following 1: " + one_followed_by_7 / number_end_1 * 100 + "%");
        System.out.println("9's following 1: " + one_followed_by_9 / number_end_1 * 100 + "%");

    }
}
