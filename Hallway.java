package byog.World;

import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;
import byog.TileEngine.Elements;

import java.io.Serializable;

/**
 * @author ShrutiS @Anakin
 * Class creates Hallways in the World.
 **/
public class Hallway implements Serializable{
    /**
     * declares the instance variables.
     */
    private int lenOfHall;
    /**
     * length of the hall.
     **/
    private String direct;
    /**
     * the direction of the hallway.
     **/
    private int width, len;
    /**
     * width and length of the hallway.
     **/
    private TETile[][] tiles;
    /**
     * the grid.
     **/
    private static final int MAX_W = 60;
    /**
     * the maximum width of the grid.
     **/
    private static final int MAX_L = 40;
    /**
     * the maximum length of the grid.
     **/
    private String prevDir, prevAdded;
    /**
     * the previous direction and previous added.
     **/

    /**
     * @param len1  is the length of the hallway
     * @param dir   is the direction the hallway will created in
     * @param l     the length of the length of the hallway
     * @param w     is the width of the hallway
     * @param til   the tiles that form the grid
     * @param prevA direction where hallway was prev added
     * @param prevD location where hallway was prev added
     **/
    public Hallway(int len1, String dir, int w, int l, TETile[][] til,
                   String prevA, String prevD) {
        lenOfHall = len1;
        direct = dir;
        width = w;
        len = l;
        tiles = til;
        prevDir = prevD;
        prevAdded = prevA;
    }

    /** **/
    public void createHallway() {
        if (hasItems()) {
            while (lenOfHall > 0 && len > 0 && width > 0
                    && len < MAX_W - 1 && width < MAX_L - 1) {
                tiles[len][width] = Elements.FLOOR;
                addLeftWall();
                addRightWall();
                changePos();
                lenOfHall--;
            }
            if (width == 0) {
                tiles[len][width] = Elements.TREE;
                width++;
            } else if (width == MAX_L - 1) {
                tiles[len][width] = Elements.TREE;
                width--;
            }
            if (len == 0) {
                tiles[len][width] = Elements.TREE;
                len++;
            } else if (len == MAX_W - 1) {
                tiles[len][width] = Elements.TREE;
                len--;
            }
        }
    }

    /**
     * getter method for variable widthPos.
     *
     * @return the widthPos instance variable
     */
    public int width() {
        return width;
    }

    /**
     * getter method for variable lengthPos.
     *
     * @return length of the Position
     */
    public int len() {
        return len;
    }

    /**
     * getter method for variable tiles.
     *
     * @return the grid
     */
    public TETile[][] tiles() {
        return tiles;
    }

    /**
     * @return whether or not there are items
     **/
    public boolean hasItems() {
        if (direct.equals("up") && !(prevDir.equals("down"))) {
            for (int x = 0; (x < lenOfHall) && ((x + len) < MAX_W); x++) {
                if (tiles[len + x][width] == Elements.FLOOR) {
                    return false;
                }
            }
        } else if (direct.equals("down") && !(prevDir.equals("up"))) {
            for (int x = 0; (x < lenOfHall) && ((len - x) > 0); x++) {
                if (tiles[len - x][width] == Elements.FLOOR) {
                    return false;
                }
            }
        } else if (direct.equals("right") && !(prevDir.equals("left"))) {
            for (int x = 0; (x < lenOfHall) && ((width + x) < MAX_L); x++) {
                if (tiles[len][width + x] == Elements.FLOOR) {
                    return false;
                }
            }
        } else if (direct.equals("left") && !(prevDir.equals("right"))) {
            for (int x = 0; (x < lenOfHall) && ((width - x) > 0); x++) {
                if (tiles[len][width - x] == Elements.FLOOR) {
                    return false;
                }
            }
        }
        return true;
    }

    /** **/
    public void addRightWall() {
        if (direct.equals("right") || direct.equals("left")
                && width <= (MAX_W - 1)) {
            if (tiles[len][width + 1] != Elements.FLOOR) {
                tiles[len][width + 1] = Elements.TREE;
            }
        } else if (direct.equals("up") || direct.equals("down")
                && (len) >= 1) {
            if (tiles[len - 1][width] != Elements.FLOOR) {
                tiles[len - 1][width] = Elements.TREE;
            }
        }
    }

    /** **/
    public void addLeftWall() {
        if (direct.equals("left") || direct.equals("right") && width >= (1)) {
            if (tiles[len][width - 1] != Elements.FLOOR) {
                tiles[len][width - 1] = Elements.TREE;
            }
        } else if (direct.equals("up") || direct.equals("down")
                && len <= (MAX_L - 1)) {
            if (tiles[len + 1][width] != Elements.FLOOR) {
                tiles[len + 1][width] = Elements.TREE;
            }
        }
    }

    /** **/
    public void changePos() {
        if (direct.equals("right") && len != 0) {
            len++;
        } else if (direct.equals("left") && len != MAX_L) {
            len--;
        } else if (direct.equals("down") && width != 0) {
            width--;
        } else if (direct.equals("up") && width != MAX_W) {
            width++;
        }
    }
}
