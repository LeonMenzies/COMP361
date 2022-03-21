import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JPanel;

/**
 *
 */
public class Panel extends JPanel{

    private final Board board;
    private final ArrayList<Tromino> trominos;
    private final Tile missing;


    public Panel(int width, int height, int tileSize, int missingX, int missingY){

        Tile[][] temp = new Tile[width][height];
        this.trominos = new ArrayList<>();

        for(int row = 0; row < width; row ++){
            for(int col = 0; col < height; col ++){
                temp[row][col] = new Tile(row  * tileSize, col * tileSize, tileSize);
            }
        }

        missing = temp[missingX][missingY];
        board = new Board(temp, missing);
    }


    public void run(){
        trominos.clear();
        recursiveRun(this.board, this.missing);
        this.repaint();
    }

    public void recursiveRun(Board quad, Tile missingTile){


        if(quad.getTiles().length == 2){

            ArrayList<Tile> constructTromino = new ArrayList<>();
            for(Tile t : quad.return2DArray()){
                if(!t.equals(missingTile)){
                    constructTromino.add(t);
                }
            }
            this.trominos.add(new Tromino(constructTromino, board.getTiles().length));



        } else {
            ArrayList<Board> quads = quad.divide();
            ArrayList<Tile> constructTromino = new ArrayList<>();

            int missingQ = 0;
            int quadSize = quads.get(0).getTiles().length - 1;
    
            for(int i = 0; i < 4;i++){
                Board q = quads.get(i);

                    if(q.containsMissingTile()){
                        recursiveRun(q, q.getMissingTile());
                        missingQ = i;
                    } else {

                    //place the tromino
                    if(i == 0){
                        Tile t1 = q.getTileAt(quadSize, quadSize);
                        constructTromino.add(t1);
                    } else if(i == 1){
                        Tile t2 = q.getTileAt(0, quadSize);
                        constructTromino.add(t2);
                    }else if(i == 2){
                        Tile t3 = q.getTileAt(quadSize, 0);
                        constructTromino.add(t3);
                    }else if(i == 3){
                        Tile t4 = q.getTileAt(0, 0);
                        constructTromino.add(t4);
                    }
                }
            }

            this.trominos.add(new Tromino(constructTromino,  board.getTiles().length));


            if(missingQ != 0){
                Board q = quads.get(0);
                Tile t = q.getTileAt(quadSize, quadSize);
                q.setMissing(t);
                recursiveRun(q, q.getTileAt(quadSize, quadSize));
            }
            if(missingQ != 1){
                Board q = quads.get(1);
                Tile t = q.getTileAt(0, quadSize);
                q.setMissing(t);
                recursiveRun(q, q.getTileAt(0, quadSize));
            }
            if(missingQ != 2){
                Board q = quads.get(2);
                Tile t = q.getTileAt(quadSize, 0);
                q.setMissing(t);
                recursiveRun(q, q.getTileAt(quadSize, 0));
            }
            if(missingQ != 3){
                Board q = quads.get(3);
                Tile t = q.getTileAt(0, 0);
                q.setMissing(t);
                recursiveRun(q, q.getTileAt(0, 0));
            }
        }
    }

    @Override
    public void paint(Graphics g) {

        missing.redraw(g, Color.BLACK);

        trominos.sort((t1, t2) -> {
            int i = t1.getXCentre() - t2.getXCentre();
            if (i == 0) {
                return t1.getYCentre() - t2.getYCentre();
            }
            return i;
        });


        for(Tromino t : trominos){
            t.paint(g, new Color((int)(Math.random() * 0x1000000)));
        }
    }
}
