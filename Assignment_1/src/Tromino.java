import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Collections;

/**
 * This class takes 3 tiles and creates a tromino, the 3 tiles
 * can then be used to find a centre point, an orientation
 * and to draw the tromino on the graphics panel
 */
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

    /**
     * Find the centre point of the three tiles
     */
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

    /**
     * find the orientation
     * @param xVals - All three x locations
     * @param yVals - All three y locations
     */
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

    /**
     * print out lcoationa nd orientation in the correct format
     * @return - The string to be printed
     */
    @Override
    public String toString() {
        return xCentre + " " + (height - yCentre) + " " + orientation;
    }

    /**
     * Paint the tromino and print out the toString
     * @param g - The graphics object
     * @param c - The color of this tromino
     */
    public void paint(Graphics g, Color c) {
        for (Tile t : tiles) {
            t.redraw(g, c);
        }
        System.out.println(this);
    }

    /**
     * Getters
     */
    public int getXCentre() {
        return this.xCentre;
    }

    public int getYCentre() {
        return this.yCentre;
    }
}
