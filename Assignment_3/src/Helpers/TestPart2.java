package Helpers;

import Part2.KnapsackPart2;

public class TestPart2 {
    public int passed = 0;

    public TestPart2(int cases){

        for (int i = 0; i < cases; i++) {

            //Generate the inputs for this test case
            int n = (int)(Math.random() * 5);
            int[] w = new int[n];
            int[] v = new int[n];
            int W = (int)(Math.random() * 45 + 5);

            for (int j = 0; j < n; j++) {
                w[j] = (int)(Math.random() * 20) + 1;
                v[j] = (int)(Math.random() * 100) + 1;
            }

            //Find desired output using brute force
            int bruteResults = runBruteForce(W, w, v, w.length);

            //Test the rest is correct
            KnapsackPart2 part2 = new KnapsackPart2(W, w, v);

            //print results
            System.out.println("Test Number: " + i);

            if (part2.getResults() == runBruteForce(W, w, v, w.length)) {
                passed += 1;
                System.out.println("Part 2 Passed - expected: " + bruteResults + " Actual: " + part2.getResults());
            } else {
                System.out.println("Part 2 Failed - expected: " + bruteResults + " Actual: " + part2.getResults());
            }

            System.out.println("--------------------------------------------");
        }
    }

    private int runBruteForce(int W, int[] w, int[] v, int n){
        if (n <= 0) {
            return 0;
        } else if (w[n - 1] > W) {
            return runBruteForce(W, w, v, n - 1);
        } else {
            return Math.max(runBruteForce(W, w, v, n - 1), v[n - 1] + runBruteForce(W - w[n - 1], w, v, n - 1));
        }
    }

    public static void main(String[] args){

        int n = 100;

        TestCaseGenerator tc = new TestCaseGenerator(n);
        System.out.println("Accuracy: " + tc.passed / (n * 2) * 100);
    }
}
