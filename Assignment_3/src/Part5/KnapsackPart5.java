package Part5;

import java.util.*;

/***
 * The Part5 0-N Knapsack algorithm is used to calculate the optimal amount the can fit
 * in the given Knapsack using graph search
 *
 * Leon Menzies - 300543278
 */
public class KnapsackPart5 {

    Node root;
    int results = 0;

    public KnapsackPart5(int W, int[] w, int[] v, int[] N){

        this.root = new Node(0, 0);

        create(this.root, W, w, v, N);
        search();
    }

    private void create(Node node, int W, int[] w, int[] v, int[] N) {

        for (int i = 0; i < w.length; i++) {
            //Only create a new node if you can
            if(N[i] > 0){
                int newW = w[i] + node.getW();
                int newV = v[i] + node.getV();

                //Check the weight is not over the limit
                if(newW <= W){
                    Node newNode = new Node(newW, newV);
                    node.addChild(newNode);

                    int[] copiedArray = Arrays.copyOf(N, N.length);
                    copiedArray[i] = copiedArray[i] - 1;
                    create(newNode, W, w, v, copiedArray);
                }
            }
        }
    }


    private void search(){
        Queue<Node> fringe = new LinkedList<>();
        fringe.add(this.root);

        while(!fringe.isEmpty()){
            Node n = fringe.poll();
            if(n.getV() > this.results){
                this.results = n.getV();
            }

            if(!n.getVisited()){
                n.setVisited();
                fringe.addAll(n.getChildren());
            }
        }
    }

    public int getResults(){
        return results;
    }

    public static void main(String[] args){

        int[] w = {6, 7, 1, 2, 5};
        int[] v = {20, 1, 100, 46, 21};
        int[] N = {5, 5, 5, 5, 5};

        KnapsackPart5 results = new KnapsackPart5(10, w, v, N);

        System.out.println(results.getResults());
    }
}
