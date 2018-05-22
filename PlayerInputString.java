package byog.World;

import byog.TileEngine.Elements;
import byog.TileEngine.TETile;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

public class PlayerInputString implements Serializable {
    private TETile[][] tiles;
    private int widthPos, lengthPos;
    private int count = 0;
    private long seed;
    private int numChanged = 0;
    private int numOfPicks = 1;
    private int numOfUses = 5;
    private int numOfStepsMoved = 0;
    private TETile x;
    private int numBullets = 0;
    private int locLenBullet, locWidBullet;
    private int iterCount = 0;
    private int lives = 5;
    private int numberOfMinionsKilled = 0;
    private Minions m1, m2, m3, m4;
    private MyWorld m;
    private String saveMoveString = "";
    private String moveStrings = "";

    public PlayerInputString(MyWorld w, int widthpos, int lengthpos,
                             long seed1) {
        tiles = MyWorld.getTiles();
        widthPos = widthpos;
        lengthPos = lengthpos;
        tiles[20][20] = Elements.PL_D;
        this.seed = seed1;
        //StdDraw.enableDoubleBuffering();
    }

    public static PlayerInputString loadGame(PlayerInputString x) {
        System.out.println("byebyebye");
        File f = new File("./rinp.txt");
        if (f.exists()) {
            try {
                FileInputStream fs = new FileInputStream(f);
                ObjectInputStream os = new ObjectInputStream(fs);
                PlayerInputString y = (PlayerInputString) os.readObject();
                x.tiles = y.tiles;
                x.seed = y.seed;
                x.moveStrings = y.saveMoveString;
                x.setAllTiles();
                return x;
            } catch (FileNotFoundException e) {
                System.out.println("file not found");
                System.exit(0);
            } catch (IOException e) {
                System.out.println(e);
                System.exit(0);
            } catch (ClassNotFoundException e) {
                System.out.println("class not found");
                System.exit(0);
            }
        }
        System.out.println("I COME HERE");
        return new PlayerInputString(new MyWorld(), 20, 20, 123);
    }

    public int getNumOfUses() {
        return numOfUses;
    }

    public int getNumOfPicks() {
        return numOfPicks;
    }

    public int getNumChanged() {
        return numChanged;
    }

    public int getNumberOfMinionsKilled() {
        return numberOfMinionsKilled;
    }

    public void setNumberOfMinionsKilled(int numberOfMinionsKilled1) {
        this.numberOfMinionsKilled = numberOfMinionsKilled1;
    }

    public int getLives() {
        return lives;
    }

    public void setLives(int lives1) {
        this.lives = lives1;
    }

    public String getMoveStrings() {
        return moveStrings;
    }

    public void setMoveStrings(String moveStrings1) {
        this.moveStrings = moveStrings1;
    }

    public String getSaveMoveString() {
        return saveMoveString;
    }

    public void setSaveMoveString(String saveMoveString1) {
        this.saveMoveString = saveMoveString1;
    }

    public int getIterCount() {
        return iterCount;
    }

    public int getCount() {
        return count;
    }

    public int getLocLenBullet() {
        return locLenBullet;
    }

    public int getLengthPos() {
        return lengthPos;
    }

    public int getLocWidBullet() {
        return locWidBullet;
    }

    public int getNumBullets() {
        return numBullets;
    }

    public int getNumOfStepsMoved() {
        return numOfStepsMoved;
    }

    public int getWidthPos() {
        return widthPos;
    }

    public void makeMinions() {
        int count1 = 4;
        int randomer = (int) (Math.random() * 50) + 10;
        //System.out.println("random: " + randomer);
        for (int x1 = 1; x1 < 59; x1++) {
            for (int y = 1; y < 39; y++) {
                //System.out.println("YEETERS");
                if (tiles[x1][y] == Elements.FLOOR) {
                    randomer--;
                    if (randomer == 0) {
                        if (count1 == 4) {
                            m1 = new Minions(x1, y, tiles, this);
                        }
                        if (count1 == 3) {
                            m2 = new Minions(x1, y, tiles, this);
                        }
                        if (count1 == 2) {
                            m3 = new Minions(x1, y, tiles, this);
                        }
                        if (count1 == 1) {
                            m4 = new Minions(x1, y, tiles, this);
                        }
                        if (count1 == 0) {
                            return;
                        }
                        randomer = (int) (Math.random() * 50) + 10;
                        count1--;
                    }
                }
            }
        }
    }

    public void changeMap(String direction) {
        numChanged++;
        if (direction.equals("up")) {
            seed += 10;
            m.setSEED(Long.toString(seed));
            tiles = m.getTiles();
            tiles[widthPos][lengthPos] = Elements.FLOOR;
            makeMinions();
            widthPos = 30;
            lengthPos = 1;
            //StdDraw.show(60);
        }
        if (direction.equals("down")) {
            seed -= 10;
            m.setSEED(Long.toString(seed));
            tiles = m.getTiles();
            tiles[widthPos][lengthPos] = Elements.FLOOR;
            makeMinions();
            widthPos = 30;
            lengthPos = 38;
            //StdDraw.show(60);
        }
        if (direction.equals("left")) {
            seed -= 50;
            m.setSEED(Long.toString(seed));
            tiles = m.getTiles();
            tiles[widthPos][lengthPos] = Elements.FLOOR;
            makeMinions();
            widthPos = 57;
            lengthPos = 20;
            //StdDraw.show(60);
        }
        if (direction.equals("right")) {
            seed += 50;
            m.setSEED(Long.toString(seed));
            tiles = m.getTiles();
            tiles[widthPos][lengthPos] = Elements.FLOOR;
            makeMinions();
            widthPos = 2;
            lengthPos = 20;
            //StdDraw.show(60);
        }
    }

    public void upKeyPressed() {
        if (tiles[widthPos][lengthPos + 1] == Elements.LOCKED_DOOR) {
            changeMap("up");
        }
        if (tiles[widthPos][lengthPos + 1] == Elements.MOUNTAIN) {
            if (numOfPicks != 0) {
                tiles[widthPos][lengthPos + 1] = Elements.FLOOR;
                numOfUses--;
                if (numOfUses == 0) {
                    numOfPicks = 0;
                    Elements.pickaxes().setChar(numOfPicks);
                }
                if (numOfPicks > 1 && numOfUses / (numOfPicks - 1) == 5.0) {
                    numOfPicks--;
                    Elements.pickaxes().setChar(numOfPicks);
                }
            }
        }
        if (hasAxe(widthPos, lengthPos + 1)) {
            pickUpAxe(widthPos, lengthPos + 1);
        }
        if (tiles[widthPos][lengthPos + 1] == Elements.FLOOR) {
            numOfStepsMoved++;
            tiles[widthPos][lengthPos + 1] = Elements.PL_U;
            tiles[widthPos][lengthPos] = Elements.FLOOR;
            count++;
            lengthPos += 1;
        }
    }

    public void downKeyPressed() {
        if (tiles[widthPos][lengthPos - 1] == Elements.LOCKED_DOOR) {
            changeMap("down");
        }
        if (tiles[widthPos][lengthPos - 1] == Elements.MOUNTAIN) {
            if (numOfPicks != 0) {
                numOfUses--;
                tiles[widthPos][lengthPos - 1] = Elements.FLOOR;
                if (numOfUses == 0) {
                    numOfPicks = 0;
                    Elements.pickaxes().setChar(numOfPicks);
                }
                if (numOfPicks > 1 && numOfUses / (numOfPicks - 1) == 5.0) {
                    numOfPicks--;
                    Elements.pickaxes().setChar(numOfPicks);
                }
                //StdDraw.show(600);
            }
        }
        if (hasAxe(widthPos, lengthPos - 1)) {
            pickUpAxe(widthPos, lengthPos - 1);
        }
        if (tiles[widthPos][lengthPos - 1] == Elements.FLOOR) {
            numOfStepsMoved++;
            tiles[widthPos][lengthPos - 1] = Elements.PL_D;
            tiles[widthPos][lengthPos] = Elements.FLOOR;
            count++;
            lengthPos -= 1;
            //StdDraw.show(60);
        }
    }

    public void rightKeyPressed() {
        if (tiles[widthPos + 1][lengthPos] == Elements.LOCKED_DOOR) {
            changeMap("right");
        }
        if (tiles[widthPos + 1][lengthPos] == Elements.MOUNTAIN) {
            if (numOfPicks != 0) {
                numOfUses--;
                tiles[widthPos + 1][lengthPos] = Elements.FLOOR;
                if (numOfUses == 0) {
                    numOfPicks = 0;
                    Elements.pickaxes().setChar(numOfPicks);
                }
                if (numOfPicks > 1 && numOfUses / (numOfPicks - 1) == 5.0) {
                    numOfPicks--;
                    Elements.pickaxes().setChar(numOfPicks);
                }
                //StdDraw.show(600);
            }
        }
        if (hasAxe(widthPos + 1, lengthPos)) {
            pickUpAxe(widthPos + 1, lengthPos);
        }
        if (tiles[widthPos + 1][lengthPos] == Elements.FLOOR) {
            numOfStepsMoved++;
            tiles[widthPos + 1][lengthPos] = Elements.PL_R;
            tiles[widthPos][lengthPos] = Elements.FLOOR;
            count++;
            widthPos += 1;
            //StdDraw.show(60);
        }
    }

    public void leftKeyPressed() {
        if (tiles[widthPos - 1][lengthPos] == Elements.LOCKED_DOOR) {
            changeMap("left");
        }
        if (tiles[widthPos - 1][lengthPos] == Elements.MOUNTAIN) {
            if (numOfPicks != 0) {
                numOfUses--;
                tiles[widthPos - 1][lengthPos] = Elements.FLOOR;
                //StdDraw.show(600);
                if (numOfUses == 0) {
                    numOfPicks = 0;
                    Elements.pickaxes().setChar(numOfPicks);
                }
                if (numOfPicks > 1 && numOfUses / (numOfPicks - 1) == 5.0) {
                    numOfPicks--;
                    Elements.pickaxes().setChar(numOfPicks);
                }
            }
        }
        if (hasAxe(widthPos - 1, lengthPos)) {
            pickUpAxe(widthPos - 1, lengthPos);
        }
        if (tiles[widthPos - 1][lengthPos] == Elements.FLOOR) {
            tiles[widthPos - 1][lengthPos] = Elements.PL_L;
            tiles[widthPos][lengthPos] = Elements.FLOOR;
            count++;
            widthPos -= 1;
            numOfStepsMoved++;
            //StdDraw.show(60);
        }
    }

    public boolean hasAxe(int wid, int len) {
        if (tiles[wid][len] == Elements.PICK) {
            return true;
        }
        return false;
    }

    public void pickUpAxe(int wid, int len) {
        tiles[wid][len] = Elements.FLOOR;
        if (numOfPicks < 10) {
            numOfPicks++;
            numOfUses += 5;
            Elements.pickaxes().setChar(numOfPicks);
        }
    }

    public void addAxe() {
        int count2 = 2;
        int randomer = (int) (Math.random() * 50) + 300;
        //System.out.println("random: " + randomer);
        for (int x1 = 1; x1 < 59; x1++) {
            for (int y = 1; y < 39; y++) {
                //System.out.println("YEETERS");
                if (tiles[x1][y] == Elements.FLOOR) {
                    randomer--;
                    if (randomer == 0) {
                        count2--;
                        tiles[x1][y] = Elements.PICK;
                        randomer = (int) (Math.random() * 20) + 10;
                    }
                    if (count2 == 0) {
                        return;
                    }
                }
            }
        }
    }

    public void sendBulletsFromRight() {
        if (numBullets == 0) {
            if (numOfStepsMoved % 100 == 1) {
                locWidBullet = (int) (Math.random() * 39);
                locLenBullet = 58;
                x = tiles[locLenBullet][locWidBullet];
                tiles[locLenBullet][locWidBullet] = Elements.BULLETRIGHT;
                numBullets++;
            }
        }
    }

    public void moveBullets() {
        if (iterCount % 30 == 0) {
            if (numBullets > 0) {
                if (locLenBullet == 1) {
                    tiles[locLenBullet][locWidBullet] = x;
                    numBullets--;
                    return;
                }
                tiles[locLenBullet][locWidBullet] = x;
                locLenBullet--;
                x = tiles[locLenBullet][locWidBullet];
                tiles[locLenBullet][locWidBullet] = Elements.BULLETRIGHT;
            }
        }
    }

    public void saveGame() {
        File f = new File("./rinp.txt");
        try {
            if (!f.exists()) {
                f.createNewFile();
            }
            FileOutputStream fs = new FileOutputStream(f);
            ObjectOutputStream os = new ObjectOutputStream(fs);
            os.writeObject(this);
        } catch (FileNotFoundException e) {
            System.out.println("file not found");
            System.exit(0);
        } catch (IOException e) {
            System.out.println(e);
            System.exit(0);
        }
        return;
    }

    public void setAllTiles() {
        for (int x1 = 0; x1 < 60; x1++) {
            for (int y = 0; y < 42; y++) {
                if (tiles[x1][y].getFilepath() != null) {
                    tiles[x1][y] = Elements.check(tiles[x1][y]);
                } else if (tiles[x1][y].character() != '\u0000') {
                    tiles[x1][y] = Elements.checkMore(tiles[x1][y]);
                }
            }
        }
    }

    public TETile[][] getTiles() {
        return this.tiles;
    }

    public long getSeed() {
        return this.seed;
    }
}
