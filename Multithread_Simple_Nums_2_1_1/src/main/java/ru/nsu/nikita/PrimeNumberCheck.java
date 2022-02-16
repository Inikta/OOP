package ru.nsu.nikita;

public class PrimeNumberCheck {
    public static boolean isPrime(Integer num) {
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
