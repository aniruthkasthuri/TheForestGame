package byog.World;

import byog.TileEngine.Elements;
import byog.TileEngine.TETile;

import java.io.Serializable;

/**
 * Minions class that creates the minions.
 *
 * @author Shruti S Aniruth K
 **/
public class Minions implements Serializable {
    /**
     * widthLoc gives the width location of the minion.
     **/
    private int widthLoc;
    /**
     * lengthLoc gives the length location of the minion.
     **/
    private int lengthLoc;
    /**
     * sets the tiles.
     **/
    private TETile[][] tiles;
    /**
     * saves previous location.
     **/
    private TETile previous;
    /**
     * saves count.
     **/
    private int count = 0;
    /**
     * declares the player.
     **/
    private Player p;
    /**
     * declares the player input string.
     **/
    private PlayerInputString pas;


    /**
     * constructor for minions.
     *
     * @param wid : the width location of the minion
     * @param len : the length location of the minion
     * @param til : the tiles saved
     * @param pa  :  the player
     **/
    public Minions(int wid, int len, TETile[][] til, Player pa) {
        widthLoc = wid;
        lengthLoc = len;
        tiles = til;
        previous = tiles[widthLoc][lengthLoc];
        tiles[widthLoc][lengthLoc] = Elements.MINIONS;
        p = pa;
    }

    /**
     * constructor for minions.
     *
     * @param wid : the width location of the minion
     * @param len : the length location of the minion
     * @param til : the tiles saved
     * @param pa  : the player
     **/
    public Minions(int wid, int len, TETile[][] til, PlayerInputString pa) {

        widthLoc = wid;
        lengthLoc = len;
        tiles = til;
        previous = tiles[widthLoc][lengthLoc];
        tiles[widthLoc][lengthLoc] = Elements.MINIONS;
        pas = pa;
    }

    public Player getP() {
        return p;
    }

    public int getCount() {
        return count;
    }

    public PlayerInputString getPas() {
        return pas;
    }

    public TETile getPrevious() {
        return previous;
    }

    public TETile[][] getTiles() {
        return tiles;
    }

    public int getWidthLoc() {
        return widthLoc;
    }

    public int getLengthLoc() {
        return lengthLoc;
    }

    /**
     * updateLives().
     */
    public void updateLives() {
        Elements.lives().setChar(p.getLives());
    }

    /**
     * updateNumKilled().
     */
    public void updateNumKilled() {
        Elements.numminkilled().setChar(p.getNumberOfMinionsKilled());
    }

    /**
     * @param widthPos  takes in the width location
     * @param lengthPos takes in the length location
     * @return a string
     */
    public String checkLocation(int widthPos, int lengthPos) {
        if (p == null) {
            if (tiles[widthLoc][lengthLoc] == Elements.GMINIONS) {
                if (Math.abs(widthLoc - widthPos) == 1
                        && Math.abs(lengthLoc - lengthPos) == 0) {
                    pas.setNumberOfMinionsKilled(pas.getNumberOfMinionsKilled()
                            + 1);
                    updateNumKilled();
                    tiles[widthLoc][lengthLoc] = previous;
                    return "GT";
                }
                return "GF";
            } else {
                if (Math.abs(widthLoc - widthPos) == 1
                        && Math.abs(lengthLoc - lengthPos) == 0) {
                    pas.setLives(pas.getLives() - 1);
                    updateLives();
                    tiles[widthLoc][lengthLoc] = previous;
                    previous = tiles[20][20];
                    this.widthLoc = 20;
                    this.lengthLoc = 20;
                    return "BT";
                }
                return "BF";
            }
        }
        if (tiles[widthLoc][lengthLoc] == Elements.GMINIONS) {
            if (Math.abs(widthLoc - widthPos) == 1
                    && Math.abs(lengthLoc - lengthPos) == 0) {
                p.setNumberOfMinionsKilled(p.getNumberOfMinionsKilled() + 1);
                updateNumKilled();
                tiles[widthLoc][lengthLoc] = previous;
                return "GT";
            }
            return "GF";
        } else {
            if (Math.abs(widthLoc - widthPos) == 1
                    && Math.abs(lengthLoc - lengthPos) == 0) {
                p.setLives(p.getLives() - 1);
                updateLives();
                tiles[widthLoc][lengthLoc] = previous;
                previous = tiles[20][20];
                this.widthLoc = 20;
                this.lengthLoc = 20;
                return "BT";
            }
            return "BF";
        }
    }

    /**
     * mover().
     */
    public void mover() {
        if (Player.getIterCount() % 30 == 0) {
            this.randomizer();
        }
    }

    /**
     * moveMinionUp.
     *
     * @param type : takes in the type of tile.
     */
    public void moveMinionUp(TETile type) {
        if (tiles[widthLoc][lengthLoc + 1] == Elements.FLOOR) {
            tiles[widthLoc][lengthLoc] = previous;
            previous = tiles[widthLoc][lengthLoc + 1];
            tiles[widthLoc][lengthLoc + 1] = type;
            lengthLoc++;
        }
    }

    /**
     * moveMinionDown.
     *
     * @param type the type of tile.
     */
    public void moveMinionDown(TETile type) {
        if (tiles[widthLoc][lengthLoc - 1] == Elements.FLOOR) {
            tiles[widthLoc][lengthLoc] = previous;
            previous = tiles[widthLoc][lengthLoc - 1];
            tiles[widthLoc][lengthLoc - 1] = type;
            lengthLoc--;
        }
    }

    /**
     * moveMinionRight.
     *
     * @param type : takes in the type of tiles
     */
    public void moveMinionRight(TETile type) {
        if (tiles[widthLoc + 1][lengthLoc] == Elements.FLOOR) {
            tiles[widthLoc][lengthLoc] = previous;
            previous = tiles[widthLoc + 1][lengthLoc];
            tiles[widthLoc + 1][lengthLoc] = type;
            widthLoc++;
        }
    }

    /**
     * @param type : the type of tile is passed in.
     */
    public void moveMinionLeft(TETile type) {
        if (tiles[widthLoc - 1][lengthLoc] == Elements.FLOOR) {
            tiles[widthLoc][lengthLoc] = previous;
            previous = tiles[widthLoc - 1][lengthLoc];
            tiles[widthLoc - 1][lengthLoc] = type;
            widthLoc--;
        }
    }

    /**
     * randommizes the movement of the minion.
     */
    public void randomizer() {
        count++;
        int randomVal = (int) (Math.random() * 3);
        if (randomVal == 0) {
            if (count > 40) {
                moveMinionUp(Elements.MINIONS);
            } else {
                moveMinionUp(Elements.GMINIONS);
            }
        } else if (randomVal == 1) {
            if (count > 40) {
                moveMinionDown(Elements.MINIONS);
            } else {
                moveMinionDown(Elements.GMINIONS);
            }
        } else if (randomVal == 2) {
            if (count > 40) {
                moveMinionRight(Elements.MINIONS);
            } else {
                moveMinionRight(Elements.GMINIONS);
            }
        } else if (randomVal == 3) {
            if (count > 40) {
                moveMinionLeft(Elements.MINIONS);
            } else {
                moveMinionLeft(Elements.GMINIONS);
            }
        }
    }
}
