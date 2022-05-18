package Part3;


import java.util.LinkedList;
import java.util.Queue;

/***
 * The Part3 0-N Knapsack algorithm is used to calculate the optimal amount the can fit
 * in the given Knapsack using brute force
 *
 * Leon Menzies - 300543278
 */
public class KnapsackPart3 {

    int results;

    public KnapsackPart3(int W, int[] w, int[] v, int[] N){

//        ArrayList<Integer> w1 = new ArrayList<>();
//        ArrayList<Integer> v1 = new ArrayList<>();

        Queue<Integer> w1 = new LinkedList<>();
        Queue<Integer> v1 = new LinkedList<>();

        for(int i = 0; i < N.length; i++){
            for(int j = 0; j < N[i]; j++) {
                w1.add(w[i]);
                v1.add(v[i]);
            }
        }
        results = run(W,  w1.stream().mapToInt(i -> i).toArray(), v1.stream().mapToInt(i -> i).toArray(), w1.size());


    }


        private int run(int W, int[] w, int[] v, int n) {

        if (n <= 0) {
            return 0;
        } else if (w[n - 1] > W) {
            return run(W, w, v, n - 1);
        } else {
            return Math.max(run(W, w, v, n - 1), v[n - 1] + run(W - w[n - 1], w, v, n - 1));
        }
    }

    public int getResults(){
        return results;
    }

    public static void main(String[] args){
        int[] w = {6, 7, 1, 2, 5};
        int[] v = {20, 1, 100, 46, 21};
        int[] N = {5, 5, 5, 5, 5};


        KnapsackPart3 results = new KnapsackPart3(10, w, v, N);
        System.out.println(results.getResults());
    }
}
