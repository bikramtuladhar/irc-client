package io.mrarm.irc.util;

import java.util.Random;

public class RandomGenerator {

    public static void main(String[] args) {
        usingMathClass();
    }

    public static int usingMathClass() {
        final int min = 20;
        final int max = 2000;
        return new Random().nextInt((max - min) + 1) + min;
    }
}