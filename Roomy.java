package byog.World;

import byog.TileEngine.TETile;

import java.io.Serializable;

/**
 * class Roomy creates the rooms.
 *
 * @author ShrutiS Aniruth
 */
public class Roomy implements Serializable {
    /**
     * the grid.
     */
    private static final int MAX_W = 60;
    /**
     * maximum width.
     */
    private static final int MAX_L = 40;
    /**
     * maximum length.
     */
    /**
     * declare all variables.
     */
    private int lengthOfRoom, widthOfRoom;
    /**
     * length of the room and the width of the room.
     */
    private String direction;
    /**
     * direction of the room.
     */
    private int widthPos, lengthPos;
    /**
     * the width and length positions.
     */
    private TETile[][] tiles;
    /**
     * random seed generator.
     */
    private String prevDir, prevAdded;
    /**
     * the previous direction and location
     */

    /**
     * @param d the direction of the room
     * @param w the width of the room
     * @param l the length of the room
     * @param t the grid
     */
    public Roomy(int lval, int wval, String d, int w, int l, TETile[][] t,
                 String pD) {
        lengthOfRoom = lval;
        widthOfRoom = wval;
        direction = d;
        widthPos = w;
        lengthPos = l;
        tiles = t;
        prevDir = pD;
    }

    /**
     * randomDir: picks a random direction to create the room.
     *
     * @return the direction
     */
    private static String randomDir() {
        int tileNum = MyWorld.getRandom().nextInt(4);
        switch (tileNum) {
            case 0:
                return "up";
            case 1:
                return "down";
            case 2:
                return "left";
            case 3:
                return "right";
            default:
                return "null";
        }
    }

    /**
     * getter method for variable widthPos.
     *
     * @return the widthPos instance variable
     */
    public int widthPos() {
        return widthPos;
    }

    /**
     * getter method for variable lengthPos.
     *
     * @return length of the Position
     */
    public int lengthPos() {
        return lengthPos;
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
     * createRoom: creates the Room in a random direction.
     */
    public void createRoom() {
        String direc = randomDir();
        System.out.println(widthOfRoom);
        for (int i = 0; i < lengthOfRoom; i++) {
            if (lengthOfRoom > 1 && lengthPos - i > 0 && widthPos - i > 0
                    && lengthPos + i < MAX_W - 1
                    && widthPos + i < MAX_L - 1) {
                if (direc.equals("up")) {
                    Hallway x = new Hallway(widthOfRoom, direc, widthPos,
                            lengthPos + i, tiles, prevAdded, prevDir);
                    x.createHallway();
                }
                if (direc.equals("down")) {
                    Hallway x = new Hallway(widthOfRoom, direc, widthPos,
                            lengthPos - i, tiles, prevAdded, prevDir);
                    x.createHallway();
                }
                if (direc.equals("right")) {
                    Hallway x = new Hallway(widthOfRoom, direc,
                            widthPos + i, lengthPos, tiles,
                            prevAdded, prevDir);
                    x.createHallway();
                }
                if (direc.equals("left")) {
                    Hallway x = new Hallway(widthOfRoom, direc,
                            widthPos - i, lengthPos, tiles, prevAdded,
                            prevDir);
                    x.createHallway();
                }
            }

        }

    }
}
