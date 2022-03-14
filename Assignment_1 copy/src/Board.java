import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Stream;

import javax.swing.JPanel;

public class Board extends JPanel{

    private final Tile[][] board;
    private Tile missingTile;

    public Board(int width, int height, int tileSize){

        board = new Tile[width][height];

        for(int row = 0; row < width; row ++){
            for(int col = 0; col < height; col ++){
                board[row][col] = new Tile(row  * tileSize, col * tileSize, tileSize);
            }
        }
        setRandomMissing();
    }

    public void setRandomMissing(){
        System.out.println("here");
        missingTile = board[(int) (Math.random() * board.length)][(int) (Math.random() * board.length)];
        missingTile.setMissing();
        this.repaint();
    }

    public Tile[][] getBoard(){
        return this.board;
    }

    public void run(Tile[][] board){
        ArrayList<Quadrant> quads = new Quadrant(board, missingTile).divide();



        for(Quadrant q : quads){
            if(q.containsMissingTile()){
                System.out.println("here");
                recursiveRun(q, this.missingTile);
            }
        }
    }

    public void recursiveRun(Quadrant q, Tile missingTile){
        if(q.getTiles().length == 2){

            Color c = new Color((int)(Math.random() * 0x1000000));

            for(Tile t : q.return2DArray()){

                if(!t.equals(missingTile)){
                    t.setColor(c);
                }
            }
        }
    }

    @Override
    public void paint(Graphics g) {
        Arrays.stream(board).flatMap(Arrays::stream).parallel().forEach(x -> x.redraw(g));
    }
}


class Quadrant{
    private final Tile[][] tileArray;
    private final Tile missingTile;

    public Quadrant(Tile[][] tileArray, Tile missingTile){
        this.tileArray = tileArray;
        this.missingTile = missingTile;
    }

    public boolean containsMissingTile(){
        return this.missingTile != null;
    }

    public Tile[][] getTiles(){
        return tileArray;
    }

    public Tile[] return2DArray(){
        return Stream.of(tileArray).flatMap(Stream::of).toArray(Tile[]::new);
    }

    public ArrayList<Quadrant> divide(){
        ArrayList<Quadrant> quadrants = new ArrayList<>();

        int x = 0;
        int y = 0;

        for(int runs = 0; runs < 4; runs ++) {

            Tile[][] quadrant = new Tile[tileArray.length / 2][tileArray.length / 2];
            boolean quadContainsMissing = false;

            for (int i = x; i < tileArray.length / 2; i++) {
                for (int j = y; j < tileArray.length / 2; j++) {

                    Tile t = tileArray[i][j];
                    quadrant[i][j] = t;

                    if(t.equals(this.missingTile)){
                        quadContainsMissing = true;
                    }
                }
            }

            if(runs == 0){
                x = tileArray.length / 2;
            } else if(runs == 1){
                x = 0;
                y = tileArray.length / 2;
            } else if(runs == 2){
                x = tileArray.length / 2;
                y = tileArray.length / 2;
            }


            if(quadContainsMissing){
                quadrants.add(new Quadrant(quadrant, this.missingTile));
            } else {
                quadrants.add(new Quadrant(quadrant, null));
            }

        }
        return quadrants;
    }
}