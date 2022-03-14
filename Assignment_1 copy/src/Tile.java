import java.awt.Color;
import java.awt.Graphics;

public class Tile {

    public int x, y;
    private final int size;
    private Color c;
    private boolean missingTile;

    public Tile(int x, int y, int size) {
        this.x = x;
        this.y = y;
        this.size = size;
        this.missingTile = false;
        this.c = Color.WHITE;
    }

    public void setMissing(){
        this.missingTile = true;
        this.c = Color.BLACK;
    }

    public void setColor(Color c){
        this.c = c;
    }

    public void redraw(Graphics g) {
        g.setColor(c);
        g.fillRect(x, y, size, size);
    }
}
