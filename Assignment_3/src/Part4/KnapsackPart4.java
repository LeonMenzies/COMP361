package Part4;

import java.util.ArrayList;
import java.util.Arrays;

/***
 * The Part4 0-N Knapsack algorithm is used to calculate the optimal amount the can fit
 * in the given Knapsack using dynamic programming
 *
 * Leon Menzies - 300543278
 */
public class KnapsackPart4 {

    int[][] results;

    public KnapsackPart4(int W, int[] w, int[] v, int[] N){
        ArrayList<Integer> w1 = new ArrayList<>();
        ArrayList<Integer> v1 = new ArrayList<>();


        for(int i = 0; i < N.length; i++){
            for(int j = 0; j < N[i]; j++) {
                w1.add(w[i]);
                v1.add(v[i]);
            }
        }

        results = new int[w1.size() + 1][W + 1];

        run(results, W, w1.stream().mapToInt(i -> i).toArray(), v1.stream().mapToInt(i -> i).toArray());
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
            }
        }
        results = V;
    }

    public int getResults(){
        return results[results.length - 1][results[0].length - 1];
    }

    public static void main(String[] args){

        int[] w = {6, 7, 1, 2, 5};
        int[] v = {20, 1, 100, 46, 21};
        int[] N = {5, 5, 5, 5, 5};

        KnapsackPart4 results = new KnapsackPart4(10, w, v, N);
        System.out.println(Arrays.deepToString(results.results).replace("], ", "]\n").replace("[[", "[").replace("]]", "]"));
    }
}
