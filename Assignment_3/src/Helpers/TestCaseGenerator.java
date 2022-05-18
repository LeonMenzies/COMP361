package Helpers;

import Part3.KnapsackPart3;
import Part4.KnapsackPart4;
import Part5.KnapsackPart5;


public class TestCaseGenerator {
    public int passed = 0;


    public TestCaseGenerator(int cases){


        for (int i = 0; i < cases; i++) {


            //Generate the inputs for this test case
            int n = (int)(Math.random() * 5);
            int[] w = new int[n];
            int[] v = new int[n];
            int[] N = new int[n];
            int W = (int)(Math.random() * 45 + 5);

            for (int j = 0; j < n; j++) {
                w[j] = (int)(Math.random() * 20) + 1;
                v[j] = (int)(Math.random() * 100) + 1;
                N[j] = (int)(Math.random() * 5) + 1;
            }

            //Find desired output using brute force
            int bruteResults = new KnapsackPart3(W, w, v, N).getResults();

            //Test the rest is correct
            KnapsackPart4 part4 = new KnapsackPart4(W, w, v, N);
            KnapsackPart5 part5 = new KnapsackPart5(W, w, v, N);

            //print results
            System.out.println("Test Number: " + i);

            if (part4.getResults() == bruteResults) {
                passed += 1;
                System.out.println("Part 4 Passed - expected: " + bruteResults + " Actual: " + part4.getResults());
            } else {
                System.out.println("Part 4 Failed - expected: " + bruteResults + " Actual: " + part4.getResults());
            }

            if (part5.getResults() == bruteResults) {
                passed += 1;
                System.out.println("Part 5 Passed - expected: " + bruteResults + " Actual: " + part5.getResults());
            } else {
                System.out.println("Part 5 Failed - expected: " + bruteResults + " Actual: " + part5.getResults());
            }
            System.out.println("--------------------------------------------");
        }
    }


    public static void main(String[] args){

        int n = 5000;

        TestCaseGenerator tc = new TestCaseGenerator(n);
        System.out.println("Accuracy: " + tc.passed / (n * 2) * 100);
    }
}
