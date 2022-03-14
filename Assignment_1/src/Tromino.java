import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Collections;

public class Tromino {

    ArrayList<Tile> tiles;
    private int tileSize;
    private int xCentre;
    private int yCentre;
    private int height;
    private String orientation;


    public Tromino(ArrayList<Tile> tiles, int height) {
        this.height = height;
        this.tiles = tiles;
        this.tileSize = tiles.get(0).getSize();
        findCentre();

    }

    public void findCentre() {

        ArrayList<Integer> xVals = new ArrayList<>();
        ArrayList<Integer> yVals = new ArrayList<>();

        for (Tile t : tiles) {
            xVals.add(t.getX());
            yVals.add(t.getY());
        }

        Collections.sort(xVals);
        Collections.sort(yVals);

        yCentre = (yVals.get(0) + tileSize) / tileSize;
        xCentre = (xVals.get(0) + tileSize) / tileSize;

        findOrientation(xVals, yVals);
    }

    public void findOrientation(ArrayList<Integer> xVals, ArrayList<Integer> yVals) {

        double allX = xVals
                .stream()
                .mapToDouble(a -> a)
                .average().getAsDouble() / tileSize + 0.5;

        double allY = yVals
                .stream()
                .mapToDouble(a -> a)
                .average().getAsDouble() / tileSize + 0.5;




        if (allX < xCentre && allY < yCentre) {
            orientation = "UL";
        } else if (allX > xCentre && allY < yCentre) {
            orientation = "UR";
        } else if (allX < xCentre && allY > yCentre) {
            orientation = "LL";
        } else if (allX > xCentre && allY > yCentre) {
            orientation = "LR";
        }

    }


    @Override
    public String toString() {

        return xCentre + " " + (height - yCentre) + " " + orientation;
    }


    public void paint(Graphics g, Color c) {


        for (Tile t : tiles) {
            t.redraw(g, c);
        }

        System.out.println(this);
    }

    public int getXCentre() {
        return this.xCentre;
    }

    public int getYCentre() {
        return this.yCentre;
    }


}
