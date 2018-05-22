package byog.World;

import byog.TileEngine.Elements;
import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.World.Flappyier.FlappyBird;
import byog.World.Snaker.Snake;
import edu.princeton.cs.introcs.StdAudio;
import edu.princeton.cs.introcs.StdDraw;

import java.awt.Color;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.InputStream;
//import sun.audio.*;


public class Player implements Serializable {
    private static int iterCount = 0;
    private static Snake sn;
    private static FlappyBird fb;
    private TETile[][] tiles;
    private int widthPos, lengthPos;
    private TERenderer ter;
    private int count = 0;
    private long seed;
    private int numChanged = 0;
    private int numOfPicks = 1;
    private int numOfUses = 5;
    private int numOfStepsMoved = 0;
    private TETile x;
    private int numBullets = 0;
    private int locLenBullet, locWidBullet;
    private int lives = 5;
    private int numberOfMinionsKilled = 0;
    private int mouseXLoc, mouseYLoc;
    private String description;
    private boolean snakeGame, flappyGame;
    private MyWorld m;
    PlayMusic player = new PlayMusic();
    private int secretLocX = 20;
    private int secretLocY = 20;

    /*AudioPlayer MGP = AudioPlayer.player;
    AudioStream BGM;
    AudioData MD;
    ContinuousAudioDataStream loop = null;*/

    private Minions m1, m2, m3, m4;

    public Player(TETile[][] t, int widthpos, int lengthpos, long seed1) {
        tiles = t;
        widthPos = widthpos;
        lengthPos = lengthpos;
        //tiles[20][20] = Elements.pl_d;
        this.seed = seed1;
        StdDraw.enableDoubleBuffering();
    }

    public static Snake getSn() {
        return sn;
    }

    public static void setSn(Snake sn1) {
        Player.sn = sn1;
    }

    public static int getIterCount() {
        return iterCount;
    }

    public static void setIterCount(int iterCount1) {
        Player.iterCount = iterCount1;
    }

    public static FlappyBird getFb() {
        return fb;
    }

    public static void setFb(FlappyBird fb1) {
        Player.fb = fb1;
    }

    public boolean isSnakeGame() {
        return snakeGame;
    }

    public void setSnakeGame(boolean snakeGame1) {
        this.snakeGame = snakeGame1;
    }

    public boolean isFlappyGame() {
        return flappyGame;
    }

    public void setFlappyGame(boolean flappyGame1) {
        this.flappyGame = flappyGame1;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description1) {
        this.description = description1;
    }

    public Minions getM1() {
        return m1;
    }

    public Minions getM2() {
        return m2;
    }

    public Minions getM3() {
        return m3;
    }

    public Minions getM4() {
        return m4;
    }

    public int getNumChanged() {
        return numChanged;
    }

    public void setNumChanged(int numChanged1) {
        this.numChanged = numChanged1;
    }

    public int getNumOfPicks() {
        return numOfPicks;
    }

    public void setNumOfPicks(int numOfPicks1) {
        this.numOfPicks = numOfPicks1;
    }

    public int getNumOfUses() {
        return numOfUses;
    }

    public void setNumOfUses(int numOfUses1) {
        this.numOfUses = numOfUses1;
    }

    public long getSeed() {
        return seed;
    }

    public void setSeed(long seed1) {
        this.seed = seed1;
    }

    public TETile getX() {
        return x;
    }

    public void setX(TETile x1) {
        this.x = x1;
    }

    public int getLocWidBullet() {
        return locWidBullet;
    }

    public void setLocWidBullet(int locWidBullet1) {
        this.locWidBullet = locWidBullet1;
    }

    public int getNumBullets() {
        return numBullets;
    }

    public void setNumBullets(int numBullets1) {
        this.numBullets = numBullets1;
    }

    public int getNumOfStepsMoved() {
        return numOfStepsMoved;
    }

    public void setNumOfStepsMoved(int numOfStepsMoved1) {
        this.numOfStepsMoved = numOfStepsMoved1;
    }

    public TETile[][] getTiles() {
        return tiles;
    }

    public void setTiles(TETile[][] tiles1) {
        this.tiles = tiles1;
    }

    public int getWidthPos() {
        return widthPos;
    }

    public void setWidthPos(int widthPos1) {
        this.widthPos = widthPos1;
    }

    public MyWorld getM() {
        return m;
    }

    public void setM(MyWorld m11) {
        this.m = m11;
    }

    public int getLengthPos() {
        return lengthPos;
    }

    public void setLengthPos(int lengthPos1) {
        this.lengthPos = lengthPos1;
    }

    public int getMouseXLoc() {
        return mouseXLoc;
    }

    public void setMouseXLoc(int mouseXLoc1) {
        this.mouseXLoc = mouseXLoc1;
    }

    public int getMouseYLoc() {
        return mouseYLoc;
    }

    public void setMouseYLoc(int mouseYLoc1) {
        this.mouseYLoc = mouseYLoc1;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count1) {
        this.count = count1;
    }

    public int getLives() {
        return lives;
    }

    public void setLives(int lives1) {
        this.lives = lives1;
    }

    public TERenderer getTer() {
        return ter;
    }

    public void setTer(TERenderer ter1) {
        this.ter = ter1;
    }

    public int getLocLenBullet() {
        return locLenBullet;
    }

    public void setLocLenBullet(int locLenBullet1) {
        this.locLenBullet = locLenBullet1;
    }

    public int getNumberOfMinionsKilled() {
        return numberOfMinionsKilled;
    }

    public void setNumberOfMinionsKilled(int numberOfMinionsKilled1) {
        this.numberOfMinionsKilled = numberOfMinionsKilled1;
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

    public void movePlayer() {
        makeMinions();
        ter = new TERenderer();
        ter.initialize(60, 40);
        //player.play("byog/World/1-33_Azalea_Town.wav", 0, 2);
        movePlayerPart2();

    }

    public void movePlayerPart2() {
        PlayMusic player = new PlayMusic();
        player.play("byog/World/1-33_Azalea_Town.wav", 0, 1);
        snakeGame = false; flappyGame = false;
        boolean yeets = false;
        int countery = 1;
        while (count != 10000000) {
            if (numChanged == 2){
                if (tiles[secretLocX][secretLocY].description() == Elements.PL_L.description()){
                    for (int d = 0; d < 60; d++){
                        for (int e = 0; e < 40; e++){
                            if (tiles[d][e] == Elements.WATER){
                                tiles[d][e] = Elements.COMMY;
                                yeets = true;
                                this.setLives(1);
                                this.setNumberOfMinionsKilled(4);
                                Elements.lives().setChar(this.getLives());
                                Elements.lives().setChar(this.getNumberOfMinionsKilled());
                            }
                        }
                    }
                }
            }
            if (numChanged == 3){
                countery = 1;
                if (tiles[20][20].description() == Elements.PL_L.description()){
                    for (int d = 0; d < 60; d++){
                        for (int e = 0; e < 40; e++){
                            if (tiles[d][e] == Elements.WATER){
                                tiles[d][e] = Elements.TEXAS;
                                yeets = true;
                                this.setLives(1);
                                this.setNumberOfMinionsKilled(4);
                                Elements.lives().setChar(this.getLives());
                                Elements.lives().setChar(this.getNumberOfMinionsKilled());
                            }
                        }
                    }
                }
            }
            if (yeets == true && countery == 1 && numChanged == 2){
                countery++;
                try {
                    player.stop();
                    player.play("byog/World/National Anthem of USSR.wav", 0, 5);
                }
                catch (Exception e){
                }
            }
            if (widthPos == 30 && lengthPos == 20 && !snakeGame) {
                sn = new Snake(this);
                sn.startGame();
                StdDraw.pause(10000);
                snakeGame = true;
            }
            if (widthPos == 10 && lengthPos == 20 && !flappyGame) {
                fb = new FlappyBird(this);
                fb.startGame();
                StdDraw.pause(5000);
                flappyGame = true;
            }
            if (this.lives == 0) {
                lives = 5;
                numberOfMinionsKilled = 0;
                LosingScreen ls = new LosingScreen();
                return;
            }
            if (this.numberOfMinionsKilled == 15) {
                lives = 5;
                numberOfMinionsKilled = 0;
                WinningScreen ws = new WinningScreen();
                return;
            }
            if (m1 != null) {
                m1.mover();
                if (m1.checkLocation(widthPos, lengthPos).equals("GT")) {
                    m1 = null;
                }
            }
            if (m2 != null) {
                m2.mover();
                if (m2.checkLocation(widthPos, lengthPos).equals("GT")) {
                    m2 = null;
                }
            }
            if (m3 != null) {
                m3.mover();
                if (m3.checkLocation(widthPos, lengthPos).equals("GT")) {
                    m3 = null;
                }
            }
            if (m4 != null) {
                m4.mover();
                if (m4.checkLocation(widthPos, lengthPos).equals("GT")) {
                    m4 = null;
                }
            }
            mouseLoc();
            iterCount++;
            if (StdDraw.isKeyPressed('W')) {
                numOfStepsMoved++;
                upKeyPressed();
            }
            if (StdDraw.isKeyPressed('D')) {
                numOfStepsMoved++;
                rightKeyPressed();
            }
            if (StdDraw.isKeyPressed('S')) {
                numOfStepsMoved++;
                downKeyPressed();
            }
            if (StdDraw.isKeyPressed('A')) {
                numOfStepsMoved++;
                leftKeyPressed();
            }
            if (StdDraw.isKeyPressed('E')) {
                player.stop();
                this.saveGame();
                TitleScreen t = new TitleScreen();
                t.pickOption();
                return;
            }
            if (StdDraw.isKeyPressed('Q')) {
                player.stop();
                this.saveGameQ();
                System.exit(0);
                return;
            }
            sendBulletsFromRight();
            moveBullets();
            ter.renderFrame(tiles);
        }
    }

    public void changeMap(String direction) {
        numChanged++;
        snakeGame = false;
        flappyGame = false;
        if (direction.equals("up")) {
            seed += 10;
            m.setSEED(Long.toString(seed));
            tiles = m.getTiles();
            tiles[widthPos][lengthPos] = Elements.FLOOR;
            makeMinions();
            widthPos = 30;
            lengthPos = 1;
            StdDraw.show(60);
        }
        if (direction.equals("down")) {
            seed -= 10;
            m.setSEED(Long.toString(seed));
            tiles = m.getTiles();
            tiles[widthPos][lengthPos] = Elements.FLOOR;
            makeMinions();
            widthPos = 30;
            lengthPos = 38;
            StdDraw.show(60);
        }
        if (direction.equals("left")) {
            seed -= 50;
            m.setSEED(Long.toString(seed));
            tiles = m.getTiles();
            tiles[widthPos][lengthPos] = Elements.FLOOR;
            makeMinions();
            widthPos = 57;
            lengthPos = 20;
            StdDraw.show(60);
        }
        if (direction.equals("right")) {
            seed += 50;
            m.setSEED(Long.toString(seed));
            tiles = m.getTiles();
            tiles[widthPos][lengthPos] = Elements.FLOOR;
            makeMinions();
            widthPos = 2;
            lengthPos = 20;
            StdDraw.show(60);
        }

        addRocks();

        if (numChanged % 2 == 0) {
            addAxe();
        }
    }

    public void addRocks() {
        int randomer = (int) (Math.random() * 15) + 10;
        //System.out.println("random: " + randomer);
        for (int i = 1; i < 59; i++) {
            for (int y = 1; y < 39; y++) {
                if (tiles[i][y] == Elements.FLOOR) {
                    randomer--;
                    if (randomer == 0) {
                        tiles[i][y] = Elements.MOUNTAIN;
                        randomer = (int) (Math.random() * 20) + 10;
                    }
                }
            }
        }
    }

    public void upKeyPressed() {
        if (tiles[widthPos][lengthPos + 1] == Elements.LOCKED_DOOR) {
            changeMap("up");
        }
        if (tiles[widthPos][lengthPos + 1] == Elements.MOUNTAIN) {
            if (numOfPicks != 0) {
                double[] x1 = StdAudio.read(
                        "byog/World/Falling_Rock.wav");
                StdAudio.play(x1);
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
                StdDraw.show(600);
            }
        }
        if (hasAxe(widthPos, lengthPos + 1)) {
            pickUpAxe(widthPos, lengthPos + 1);
        }
        if (tiles[widthPos][lengthPos + 1] == Elements.FLOOR) {
            tiles[widthPos][lengthPos + 1] = Elements.PL_U;
            tiles[widthPos][lengthPos] = Elements.FLOOR;
            count++;
            lengthPos += 1;
            StdDraw.show(60);
        }
    }

    public void downKeyPressed() {
        if (tiles[widthPos][lengthPos - 1] == Elements.LOCKED_DOOR) {
            changeMap("down");
        }
        if (tiles[widthPos][lengthPos - 1] == Elements.MOUNTAIN) {
            if (numOfPicks != 0) {
                double[] i = StdAudio.read(
                        "byog/World/Falling_Rock.wav");
                StdAudio.play(i);
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
                StdDraw.show(600);
            }
        }
        if (hasAxe(widthPos, lengthPos - 1)) {
            pickUpAxe(widthPos, lengthPos - 1);
        }
        if (tiles[widthPos][lengthPos - 1] == Elements.FLOOR) {
            tiles[widthPos][lengthPos - 1] = Elements.PL_D;
            tiles[widthPos][lengthPos] = Elements.FLOOR;
            count++;
            lengthPos -= 1;
            StdDraw.show(60);
        }
    }

    public void rightKeyPressed() {
        System.out.println(tiles[widthPos + 1][lengthPos]);
        System.out.println(Elements.FLOOR);
        if (tiles[widthPos + 1][lengthPos] == Elements.LOCKED_DOOR) {
            changeMap("right");
        }
        if (tiles[widthPos + 1][lengthPos] == Elements.MOUNTAIN) {
            if (numOfPicks != 0) {
                double[] i = StdAudio.read(
                        "byog/World/Falling_Rock.wav");
                StdAudio.play(i);
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
                StdDraw.show(600);
            }
        }
        if (hasAxe(widthPos + 1, lengthPos)) {
            pickUpAxe(widthPos + 1, lengthPos);
        }
        if (tiles[widthPos + 1][lengthPos] == Elements.FLOOR) {
            System.out.println("FLOOR WORKS");
            tiles[widthPos + 1][lengthPos] = Elements.PL_R;
            tiles[widthPos][lengthPos] = Elements.FLOOR;
            count++;
            widthPos += 1;
            StdDraw.show(60);
        }
    }

    public void leftKeyPressed() {
        if (tiles[widthPos - 1][lengthPos] == Elements.LOCKED_DOOR) {
            changeMap("left");
        }
        if (tiles[widthPos - 1][lengthPos] == Elements.MOUNTAIN) {
            if (numOfPicks != 0) {
                double[] x1 = StdAudio.read(
                        "byog/World/Falling_Rock.wav");
                StdAudio.play(x1);
                numOfUses--;
                tiles[widthPos - 1][lengthPos] = Elements.FLOOR;
                StdDraw.show(600);
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
            StdDraw.show(60);
        }
    }

    public void addAxe() {
        int count1 = 2;
        int randomer = (int) (Math.random() * 50) + 300;
        //System.out.println("random: " + randomer);
        for (int i = 1; i < 59; i++) {
            for (int y = 1; y < 39; y++) {
                //System.out.println("YEETERS");
                if (tiles[i][y] == Elements.FLOOR) {
                    randomer--;
                    if (randomer == 0) {
                        count1--;
                        tiles[i][y] = Elements.PICK;
                        randomer = (int) (Math.random() * 20) + 10;
                    }
                    if (count1 == 0) {
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

    public void saveGameQ() {
        File f = new File("./player.txt");
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
    public void saveGame() {
        File f = new File("./player.txt");
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

        TitleScreen x1 = new TitleScreen();
        x1.pickOption();
        return;
    }

    public Player loadGame() {
        File f = new File("./player.txt");
        if (f.exists()) {
            try {
                FileInputStream fs = new FileInputStream(f);
                ObjectInputStream os = new ObjectInputStream(fs);
                return (Player) os.readObject();
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

        return new Player(m.getTiles(), 20, 20,
                m.getSEED());
    }

    public void setAllTiles() {
        for (int i = 0; i < 60; i++) {
            for (int y = 0; y < 42; y++) {
                if (tiles[i][y].getFilepath() != null) {
                    tiles[i][y] = Elements.check(tiles[i][y]);
                } else if (tiles[i][y].character() != '\u0000') {
                    tiles[i][y] = Elements.checkMore(tiles[i][y]);
                }
                //System.out.println(tiles[x][y].getFilepath());
                if (tiles[i][y].getFilepath() == null) {
                    System.out.println(x);
                    // System.out.println(y);
                }
            }
        }
    }

    public void mouseLoc() {
        mouseXLoc = (int) (StdDraw.mouseX());
        mouseYLoc = (int) (StdDraw.mouseY());

        if (mouseXLoc < 60 && mouseYLoc < 40) {
            description = tiles[mouseXLoc][mouseYLoc].description();
            for (int x1 = 0; x1 < 6; x1++) {
                //System.out.println("integer: " + x);
                tiles[52 + x1][40] = new TETile(description.charAt(x1),
                        Color.yellow, Color.darkGray, "nothing");
            }
        }
    }
}
