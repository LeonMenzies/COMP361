package Part2;

import java.util.ArrayList;

/***
 * The Part2 0-1 Knapsack algorithm is used to calculate the optimal amount the can fit
 * in the given Knapsack using dynamic programming
 *
 * Leon Menzies - 300543278
 */
public class KnapsackPart2 {



    ArrayList<Double> problemSize;
    ArrayList<Long> time;

    int[][] results;
    long startTime;

    public KnapsackPart2(int W, int[] w, int[] v){
        this.results = new int[w.length + 1][W + 1];
        this.problemSize = new ArrayList<>();
        this.time = new ArrayList<>();

        //record start time
        this.startTime = System.currentTimeMillis();
        run(results, W, w, v);
    }

    private void run(int[][]V, int W, int[] w, int[] v) {

        //Set first row
        for(int i = 0; i < W; i++){
            V[0][i] = 0;
        }

        //Main algorithm
        for(int j = 1; j <= w.length; j++){
            for(int k = 1; k <= W; k++){

                if(w[j - 1] > k){
                    V[j][k] = V[j-1][k];
                } else {
                    V[j][k] = Math.max(V[j-1][k], V[j-1][k - w[j - 1]] + v[j - 1]);
                }
                time.add(System.currentTimeMillis() - this.startTime);
            }
        }
        results = V;
    }

    public int getResults(){
        return results[results.length - 1][results[0].length - 1];
    }

    public void printGraphInfo(){
        System.out.println(this.time);
    }

    public static void main(String[] args){
        int[] w = {7, 5, 5, 4};
        int[] v = {49, 30, 25, 24};

        KnapsackPart2 results = new KnapsackPart2(10, w, v);
        results.printGraphInfo();
        System.out.println(results.getResults());


    }
}
