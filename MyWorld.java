package byog.World;

import byog.TileEngine.Elements;
import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;

import java.io.Serializable;
import java.util.Random;

/**
 * class creates the world.
 *
 * @author Aniruth , Shruti Satrawada
 **/
public class MyWorld implements Serializable {
    /**
     * declares instance variables.
     **/
    private static final int WIDTH = 60;
    /**
     * the maximum width.
     */
    private static final int HEIGHT = 42;
    /**
     * the maximum height.
     */
    private static long seed;
    /**
     * the random seed.
     */
    private static int widthLoc = 20;
    /**
     * the starting width.
     */
    private static int heightLoc = 20;
    /**
     * the starting height.
     */
    private static Random random;
    /**
     * the random number generator.
     */
    private static String prevAdded = "";
    /**
     * the previous location.
     */
    private static String prevDir = "";
    /**
     * the previous direction.
     */
    private static TETile[][] tilesF;

    private static void randomStructure(TETile[][] tiles) {
        int tileNum = random.nextInt(4);
        switch (tileNum) {
            case 1:
                createHallway(tiles);
                break;
            case 0:
                createRoom(tiles);
                break;
            case 2:
                createHallway(tiles);
                break;
            case 3:
                createHallway(tiles);
                break;
            default:
                return;
        }
    }

    public static Random getRandom() {
        return random;
    }

    /**
     * main.
     **/
    public static void fillin(TETile[][] tiles) {
        tiles[0][41] = Elements.L;
        tiles[1][41] = Elements.I;
        tiles[2][41] = Elements.V;
        tiles[3][41] = Elements.E;
        tiles[4][41] = Elements.S;
        tiles[5][41] = Elements.COLON;
        tiles[6][41] = Elements.lives();
        tiles[0][40] = Elements.NUM;
        tiles[1][40] = Elements.O;
        tiles[2][40] = Elements.F;
        tiles[3][40] = Elements.P;
        tiles[4][40] = Elements.I;
        tiles[5][40] = Elements.C;
        tiles[6][40] = Elements.K;
        tiles[7][40] = Elements.A;
        tiles[8][40] = Elements.X;
        tiles[9][40] = Elements.E;
        tiles[10][40] = Elements.S;
        tiles[11][40] = Elements.COLON;
        tiles[12][40] = Elements.pickaxes();
        tiles[39][40] = Elements.C;
        tiles[40][40] = Elements.U;
        tiles[41][40] = Elements.R;
        tiles[42][40] = Elements.SPACE;
        tiles[43][40] = Elements.L;
        tiles[44][40] = Elements.O;
        tiles[45][40] = Elements.C;
        tiles[46][40] = Elements.A;
        tiles[47][40] = Elements.T;
        tiles[48][40] = Elements.I;
        tiles[49][40] = Elements.O;
        tiles[50][40] = Elements.N;
        tiles[51][40] = Elements.COLON;
        tiles[39][41] = Elements.N;
        tiles[40][41] = Elements.U;
        tiles[41][41] = Elements.M;
        tiles[42][41] = Elements.SPACE;
        tiles[43][41] = Elements.M;
        tiles[44][41] = Elements.I;
        tiles[45][41] = Elements.N;
        tiles[46][41] = Elements.I;
        tiles[47][41] = Elements.O;
        tiles[48][41] = Elements.N;
        tiles[49][41] = Elements.S;
        tiles[50][41] = Elements.SPACE;
        tiles[51][41] = Elements.K;
        tiles[52][41] = Elements.I;
        tiles[53][41] = Elements.L;
        tiles[54][41] = Elements.L;
        tiles[55][41] = Elements.COLON;
        tiles[56][41] = Elements.numminkilled();
    }
    public static void createField() {
        TERenderer ter = new TERenderer();
        ter.initialize(WIDTH, HEIGHT);
        TETile[][] tiles = new TETile[WIDTH][HEIGHT];
        int height = tiles[0].length;
        int width = tiles.length;
        int count = 0;
        for (int x = 0; x < WIDTH; x++) {
            for (int y = 0; y < HEIGHT - 2; y++) {
                tiles[x][y] = Elements.WATER;
            }
            fillin(tiles);
            for (int y = 40; y < height; y++) {
                if (tiles[x][y] == null) {
                    tiles[x][y] = Elements.NOTHING;
                }
            }
        }
        int x = 300;
        int y = 200;
        int z = 250;
        for (int i = 0; i < x; i++) {
            MyWorld.randomStructure(tiles);
            if (i == 100) {
                if (tiles[1][1] == Elements.WATER) {
                    widthLoc = 1;
                    heightLoc = 1;
                    MyWorld.randomStructure(tiles);
                }
            }
            if (i == y) {
                if (tiles[30][10] == Elements.WATER) {
                    widthLoc = 10;
                    heightLoc = 30;
                    MyWorld.randomStructure(tiles);
                }
            }
            if (i == z) {
                if (tiles[5][37] == Elements.WATER) {
                    widthLoc = 5;
                    heightLoc = 37;
                    MyWorld.randomStructure(tiles);
                }
            }
        }
        createHorPath(tiles);
        createVerticalPath(tiles);
        createWalls(tiles);
        tilesF = tiles;
    }

    public static void createHorPath(TETile[][] tiles) {
        for (int x = 1; x < WIDTH - 1; x++) {
            tiles[x][20] = Elements.FLOOR;
        }
        tiles[0][20] = Elements.LOCKED_DOOR;
        tiles[59][20] = Elements.LOCKED_DOOR;
    }

    public static void createVerticalPath(TETile[][] tiles) {
        for (int x = 1; x < HEIGHT - 1; x++) {
            tiles[30][x] = Elements.FLOOR;
        }
        tiles[30][0] = Elements.LOCKED_DOOR;
        tiles[30][39] = Elements.LOCKED_DOOR;
    }

    /**
     * creates hallways.
     *
     * @param tiles : gets the grid.
     **/
    public static void createHallway(TETile[][] tiles) {
        String direc = randomDir();
        Hallway x = new Hallway(random.nextInt(HEIGHT - 1), direc,
                widthLoc, heightLoc, tiles, prevAdded, prevDir);
        x.createHallway();
        widthLoc = x.width();
        heightLoc = x.len();
        prevAdded = "Hallway";
        prevDir = direc;
    }

    /**
     * creates rooms.
     *
     * @param tiles : gets the grid.
     **/
    public static void createRoom(TETile[][] tiles) {
        String direc = randomDir();
        /*
        Changed to match new parameters in Roomy... the image did change a
        little Roomy x = new Roomy(RANDOM.nextInt(HEIGHT - 1),
        RANDOM.nextInt(HEIGHT - 1), direc, widthLoc, heightLoc,
        tiles, WIDTH, HEIGHT, prevAdded, prevDir);
        */
        Roomy x = new Roomy(random.nextInt(5) + 3, random.nextInt(5) + 3,
                direc, widthLoc, heightLoc, tiles, prevDir);
        x.createRoom();
        widthLoc = x.widthPos();
        heightLoc = x.lengthPos();
        prevAdded = "Room";
        prevDir = direc;
    }

    public static TETile[][] getTiles() {
        MyWorld.createField();
        return tilesF;
    }

    /**
     * randomDir: pics a random direction.
     *
     * @return returns the direction.
     **/
    private static String randomDir() {
        int tileNum = random.nextInt(4);
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
                return "";
        }
    }

    /**
     * creates walls.
     *
     * @param tiles : gets the grid.
     **/
    public static void createWalls(TETile[][] tiles) {
        for (int x = 0; x < WIDTH; x++) {
            for (int y = 0; y < HEIGHT; y++) {
                if (tiles[x][y] == Elements.FLOOR) {
                    if (tiles[x + 1][y] == Elements.WATER) {
                        tiles[x + 1][y] = Elements.TREE;
                    }
                    if (tiles[x - 1][y] == Elements.WATER) {
                        tiles[x - 1][y] = Elements.TREE;
                    }
                    if (tiles[x][y + 1] == Elements.WATER) {
                        tiles[x][y + 1] = Elements.TREE;
                    }
                    if (tiles[x][y - 1] == Elements.WATER) {
                        tiles[x][y - 1] = Elements.TREE;
                    }
                    if (tiles[x + 1][y + 1] == Elements.WATER) {
                        tiles[x + 1][y + 1] = Elements.TREE;
                    }
                    if (tiles[x + 1][y - 1] == Elements.WATER) {
                        tiles[x + 1][y - 1] = Elements.TREE;
                    }
                    if (tiles[x - 1][y + 1] == Elements.WATER) {
                        tiles[x - 1][y + 1] = Elements.TREE;
                    }
                    if (tiles[x - 1][y - 1] == Elements.WATER) {
                        tiles[x - 1][y - 1] = Elements.TREE;
                    }
                }
            }
        }
    }

    /**
     * removes walls that are too small.
     *
     * @param t : gets the grid
     **/

    public static void removeSomeWalls(TETile[][] t) {
        for (int x = 1; x < WIDTH - 1; x++) {
            for (int y = 1; y < HEIGHT - 1; y++) {
                if (t[x][y] == Elements.TREE
                        && t[x + 1][y] == Elements.FLOOR) {
                    if (t[x - 1][y] == Elements.FLOOR
                            && t[x][y + 1] == Elements.FLOOR) {
                        if (t[x][y - 1] == Elements.FLOOR
                                && t[x + 1][y + 1] == Elements.FLOOR) {
                            if (t[x + 1][y - 1] == Elements.FLOOR
                                    && t[x - 1][y + 1] == Elements.FLOOR) {
                                if (t[x - 1][y - 1] == Elements.FLOOR) {
                                    t[x][y] = Elements.FLOOR;
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public static long getSEED() {
        return seed;
    }

    /**
     * Randomly select whether to create a room or a hall.
     *
     * @param : provides the grid
     **/
    public static void setSEED(String input) {
        seed = 0;
        int counts = 0;
        boolean unique = false;
        if (input.length() == 0) {
            input = "23486723462";
        }
        for (int te = 0; te < input.length(); te++) {
            if (!(input.charAt(te) >= 48 && input.charAt(te) <= 57)) {
                counts++;
            }
            if (counts == input.length()) {
                input = "23486723462";
            }
        }
        widthLoc = 20;
        heightLoc = 20;
        String newUp = "";
        for (int x = 1; x < input.length() - 1; x++) {
            newUp = newUp + input.charAt(x);
        }
        newUp = input.replaceAll("[^\\d.]", "");
        if (newUp.length() > 19) {
            newUp = Long.toString((long) (Math.pow(2, 100) - 1));
        }
        seed = Long.parseLong(newUp);
        random = new Random(seed);
    }
}

