package net.i77soft.wolf;

import java.util.ArrayList;

public class Probability {
    // P(m, n) internal
    private static int __permutations(int m, int n) {
        // Must ensure m <= n.
        int result = n;
        for (int i = n - 1; i >= m; i--) {
            result *= i;
        }
        return result;
    }

    // P(m, n)
    public static int permutations(int m, int n) {
        // Ensure m <= n.
        MinMax ensure = new MinMax(m, n);
        m = ensure.min();
        n = ensure.max();

        return Probability.__permutations(m, n);
    }

    // C(m, n), internal
    public static int __combinations(int m, int n) {
        // Must ensure m <= n.
        int result = n;
        int limit = n - m;
        for (int i = n - 1; i > limit; i--) {
            result *= i;
        }
        int divisor = m;
        for (int i = m - 1; i > 0; i--) {
            divisor *= i;
        }
        return (result / divisor);
    }

    // C(m, n)
    public static int combinations(int m, int n) {
        // Ensure m <= n.
        MinMax ensure = new MinMax(m, n);
        m = ensure.min();
        n = ensure.max();

        return Probability.__combinations(m, n);
    }

    public static void displayAnswer(int no, int m, int[] numbers) {
        if (m > numbers.length)
            m = numbers.length;

        String answer = "";
        for (int i = 0; i < m; i++) {
            if (i < m - 1)
                answer += String.format("%d, ", numbers[i] + 1);
            else
                answer += String.format("%d", numbers[i] + 1);
        }
        System.out.printf("No. %d, answer = %s\n", no + 1, answer);
    }

    public static void displayAllAnswers(int m, ArrayList<int[]> answers) {
        System.out.println();

        int no = 0;
        for (int[] numbers : answers) {
            displayAnswer(no, m, numbers);
            no++;
        }

        System.out.println();
    }

    // C(m, n) recursive
    private static void permutationAll(int depth, int m, int n,
            int[] numbers, int[] flags, ArrayList<int[]> permuts) {
        // Whether is ending condition?
        if (depth < m) {
            for (int i = 0; i < n; i++) {
                if (flags[i] == 0) {
                    // doMove()
                    flags[i] = 1;
                    numbers[depth] = i;
                    // Search the next depth
                    Probability.permutationAll(depth + 1, m, n, numbers, flags, permuts);
                    // undoMove()
                    numbers[depth] = -1;
                    flags[i] = 0;
                }
            }
        } else {
            // Reach the ending depth, find out a result.
            int len = numbers.length;
            int[] newNumbers = new int[len];
            for (int i = 0; i < len; i++) {
                newNumbers[i] = numbers[i];
            }
            permuts.add(newNumbers);
        }
    }

    // C(m, n)
    public static ArrayList<int[]> permutAll(int m, int n) {
        // Ensure m <= n.
        MinMax ensure = new MinMax(m, n);
        int wolfs = ensure.min();
        int total = ensure.max();

        ArrayList<int[]> result = new ArrayList<int[]>();
        int[] numbers = new int[total];
        int[] flags = new int[total];

        for (int i = 0; i < total; i++) {
            numbers[i] = -1;
            flags[i] = 0;
        }

        Probability.permutationAll(0, wolfs, total, numbers, flags, result);

        // Display all permutations
        // Probability.displayAllResults(m, result);

        return result;
    }

    // C(m, n) recursive
    private static void combinationAll(int depth, int lastIndex, int m, int n,
            int[] numbers, ArrayList<int[]> combins) {
        // Whether is ending condition?
        if (depth < m) {
            for (int i = lastIndex + 1; i < n; i++) {
                // doMove()
                numbers[depth] = i;
                // Search the next depth
                Probability.combinationAll(depth + 1, i, m, n, numbers, combins);
                // undoMove()
                numbers[depth] = -1;
            }
        } else {
            // Reach the ending depth, find out a result.
            int len = numbers.length;
            int[] newNumbers = new int[len];
            for (int i = 0; i < len; i++) {
                newNumbers[i] = numbers[i];
            }
            combins.add(newNumbers);
        }
    }

    // C(m, n)
    public static ArrayList<int[]> combinAll(int m, int n) {
        // Ensure m <= n.
        MinMax ensure = new MinMax(m, n);
        m = ensure.min();
        n = ensure.max();

        ArrayList<int[]> result = new ArrayList<int[]>();
        int[] numbers = new int[n];

        for (int i = 0; i < n; i++) {
            numbers[i] = -1;
        }

        Probability.combinationAll(0, -1, m, n, numbers, result);

        // Display all combinations
        // Probability.displayAllResults(m, result);

        return result;
    }
}
