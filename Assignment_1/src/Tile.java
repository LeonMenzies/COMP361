import java.awt.Color;
import java.awt.Graphics;

/**
 * The board is made up of tiles. A Tile contains
 * a location and size. they can be redrawn using
 * this information with a passed color
 */
public class Tile {

    private int x, y;
    private final int size;

    public Tile(int x, int y, int size) {
        this.x = x;
        this.y = y;
        this.size = size;
    }

    /**
     * Redraw this tile with given params
     * @param g - graphics object
     * @param c1 - The color for this tile
     */
    public void redraw(Graphics g, Color c1) {
        g.setColor(c1);
        g.fillRect(x, y, size, size);
    }

    /**
     * Getters, Setters and toString
     */
    public int getSize(){
        return this.size;
    }

    public int getX(){
        return this.x;
    }

    public int getY(){
        return this.y;
    }

    @Override
    public String toString() {
        return "Tile{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
