import java.util.ArrayList;
import java.util.stream.Stream;

/**
 * A board holds the 2d array of tiles that represents the board
 */
public class Board{
    private final Tile[][] tileArray;
    private Tile missingTile;

    public Board(Tile[][] tileArray, Tile missingTile){
        this.tileArray = tileArray;
        this.missingTile = missingTile;
    }

    /**
     * Check if this board contains a missing tile
     * @return - boolean if true or false
     */
    public boolean containsMissingTile(){
        return this.missingTile != null;
    }

    /**
     * Given a certain location on the board create a new board
     * containing those tiles
     * @param x - x start location
     * @param y - y start location
     * @return - The new board
     */
    public Board runSplit(int x, int y){

        int newQuadSize = tileArray.length / 2;

        Tile[][] quadrant = new Tile[newQuadSize][newQuadSize];
        Tile missingTile = null;

        for (int i = x; i < newQuadSize + x; i++) {
            for (int j = y; j < newQuadSize + y; j++) {

                Tile t = tileArray[i][j];
                quadrant[i - x][j - y] = t;

                if(t.equals(this.missingTile)){
                    missingTile = t;
                }
            }
        }

        return new Board(quadrant, missingTile);
    }

    /**
     * A method that runs the splitting algorithm to create 4 new quadrants of the board
     * @return - An arraylist that contains the 4 new boards
     */
    public ArrayList<Board> divide(){
        ArrayList<Board> quadrants = new ArrayList<>();
        int newQuadSize = tileArray.length / 2;

        //splits on the 4 different quads
        quadrants.add(runSplit(0, 0));
        quadrants.add(runSplit(newQuadSize, 0));
        quadrants.add(runSplit(0, newQuadSize));
        quadrants.add(runSplit(newQuadSize, newQuadSize));

        return quadrants;
    }

    /**
     * Getters and settes
     */
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
}