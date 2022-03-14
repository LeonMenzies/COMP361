import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Stream;

import javax.swing.JPanel;

public class Board extends JPanel{

    private Quadrant quadrent;

    public Board(int width, int height, int tileSize){

        Tile[][] temp = new Tile[width][height];

        for(int row = 0; row < width; row ++){
            for(int col = 0; col < height; col ++){
                temp[row][col] = new Tile(row  * tileSize, col * tileSize, tileSize);
            }
        }
        int ranX = (int)(Math.random() * width);
        int ranY = (int)(Math.random() * width);
        temp[ranX][ranY].setMissing();

        quadrent = new Quadrant(temp, temp[ranX][ranY]);
    }


    public void run(){
        ArrayList<Quadrant> quads = quadrent.divide();

        Color c = ranCol();

        for(int i = 0; i < quads.size();i++){
            Quadrant q = quads.get(i);
            int quadSize = q.getTiles().length - 1;

            if(q.containsMissingTile()){
                recursiveRun(q, q.getMissingTile());
            } else {
                if(i == 0){
                    Tile t1 = q.getTileAt(quadSize, quadSize);
                    t1.setColor(c);
                    q.setMissing(t1);
                    recursiveRun(q, t1);
                } else if(i == 1){
                    Tile t2 = q.getTileAt(0, quadSize);
                    t2.setColor(c);
                    q.setMissing(t2);
                    recursiveRun(q, t2);
                }else if(i == 2){
                    Tile t3 = q.getTileAt(quadSize, 0);
                    t3.setColor(c);
                    q.setMissing(t3);
                    recursiveRun(q, t3);
                }else if(i == 3){
                    Tile t4 = q.getTileAt(0, 0);
                    t4.setColor(c);
                    q.setMissing(t4);
                    recursiveRun(q, t4);
                }
            }
        }
  
    }

    public void recursiveRun(Quadrant quad, Tile missingTile){
        this.repaint();
        if(quad.getTiles().length == 2){


            Color c = ranCol();

            for(Tile t : quad.return2DArray()){
                if(!t.equals(missingTile)){
                    t.setColor(c);
                }
            }
            return;
        } else {
            ArrayList<Quadrant> quads = quad.divide();
            Color c = ranCol();

            for(int i = 0; i < quads.size();i++){
                Quadrant q = quads.get(i);
                int quadSize = q.getTiles().length - 1;
    
                if(q.containsMissingTile()){
                    recursiveRun(q, q.getMissingTile());
                } else {
                    if(i == 0){
                        Tile t1 = q.getTileAt(quadSize, quadSize);
                        t1.setColor(c);
                        recursiveRun(q, t1);
                    } else if(i == 1){
                        Tile t2 = q.getTileAt(0, quadSize);
                        t2.setColor(c);
                        recursiveRun(q, t2);
                    }else if(i == 2){
                        Tile t3 = q.getTileAt(quadSize, 0);
                        t3.setColor(c);
                        recursiveRun(q, t3);
                    }else if(i == 3){
                        Tile t4 = q.getTileAt(0, 0);
                        t4.setColor(c);
                        recursiveRun(q, t4);
                    }
                }
            }
            
        }
    }

    @Override
    public void paint(Graphics g) {
        Arrays.stream(quadrent.getTiles()).flatMap(Arrays::stream).forEach(x -> x.redraw(g));

    }

    public Color ranCol(){
        return new Color((int)(Math.random() * 0x1000000));
    }
}


class Quadrant{
    private final Tile[][] tileArray;
    private Tile missingTile;


    public Quadrant(Tile[][] tileArray, Tile missingTile){
        this.tileArray = tileArray;
        this.missingTile = missingTile;
    }

    public boolean containsMissingTile(){
        return this.missingTile != null;
    }

    public Tile getMissingTile(){
        return missingTile;
    }

    public void setMissing(Tile t){
        this.missingTile = t;
    }

    public Tile getTileAt(int row, int col){
        return tileArray[row][col];
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

            int newQuadSize = tileArray.length / 2;

            Tile[][] quadrant = new Tile[newQuadSize][newQuadSize];
            boolean quadContainsMissing = false;

            for (int i = x; i < newQuadSize + x; i++) {
                for (int j = y; j < newQuadSize + y; j++) {

                    Tile t = tileArray[i][j];
                    quadrant[i - x][j - y] = t;

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