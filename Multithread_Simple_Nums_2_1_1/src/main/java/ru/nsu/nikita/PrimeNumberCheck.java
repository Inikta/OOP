package ru.nsu.nikita;

public class PrimeNumberCheck {

    /**
     * General algorithm for checking if integer number is prime.
     * @param number number to check.
     * @return true if number is prime. False otherwise.
     */
    public static boolean isPrime(Integer number) {
        int num = Math.abs(number);
        if (num != 0) {
            for (int i = 2; i <= Math.floor(Math.sqrt(num)); i++) {
                if (num % i == 0) {
                    return false;
                }
            }
            return true;
        } else {
            return false;
        }
    }
}
