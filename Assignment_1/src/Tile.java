import java.awt.Color;
import java.awt.Graphics;


public class Tile {

    private int x, y;
    private final int size;

    public Tile(int x, int y, int size) {
        this.x = x;
        this.y = y;
        this.size = size;
    }

    public void redraw(Graphics g, Color c1) {
        g.setColor(c1);
        g.fillRect(x, y, size, size);
    }

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
