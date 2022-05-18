package Part5;

import java.util.ArrayList;

/***
 * The Part1 Knapsack algorithm is used to calculate the optimal amount the can fit
 * in the given Knapsack using dynamic programming
 *
 * Leon Menzies - 300543278
 */
public class Node {

    private int w;
    private int v;
    private ArrayList<Node> children;
    private boolean visited;

    public Node(int w, int v){
        this.children = new ArrayList<>();
        this.visited = false;
        this.w = w;
        this.v = v;
    }

    public int getW(){
        return this.w;
    }

    public int getV(){
        return this.v;
    }

    public void addChild(Node child){
        this.children.add(child);
    }

    public ArrayList<Node> getChildren(){
        return this.children;
    }

    public void setVisited(){
        this.visited = true;
    }

    public boolean getVisited(){
        return this.visited;
    }
}
