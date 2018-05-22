package byog.World;

import byog.TileEngine.Elements;
import edu.princeton.cs.introcs.StdDraw;

import java.io.*;
import java.awt.Font;

import byog.TileEngine.TETile;


import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//import sun.audio.*;


//import java.util.Random;

public class TitleScreen implements Serializable{
    private boolean cont = false;
    private static String filename = "Player.ser";
    private static FileOutputStream fos = null;
    private static ObjectOutputStream out = null;
    private Player p = new Player(new TETile[60][42], 1, 1, 1);
    PlayMusic player = new PlayMusic();

    /*AudioPlayer MGP = AudioPlayer.player;
    AudioStream BGM;
    AudioData MD;
    ContinuousAudioDataStream loop = null;*/

    public TitleScreen() {
        StdDraw.clear(StdDraw.PINK);
        StdDraw.setXscale(0, 300);
        StdDraw.setYscale(0, 300);
        pickOption();


    }

    public static TitleScreen loadGame() {
        File f = new File("./player.txt");
        if (f.exists()) {
            try {
                FileInputStream fs = new FileInputStream(f);
                ObjectInputStream os = new ObjectInputStream(fs);
                return (TitleScreen) os.readObject();
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

        return new TitleScreen();
    }

    public void pickOption() {
        Font font = new Font("Arial", Font.BOLD, 60);
        StdDraw.setFont(font);
        StdDraw.setPenColor(StdDraw.WHITE);
        StdDraw.show();
        cont = false;
        boolean playCompleted = false;
        player.play("byog/World/1-01_Opening_Movie.wav", 0, 1);

        while (!cont) {
            StdDraw.text(150, 200, "Forest Game");
            Font font1 = new Font("Arial", Font.BOLD,30 );
            StdDraw.setFont(font1);
            StdDraw.text(150, 110, "Type N to enter new game");
            StdDraw.text(150, 80, "Type L to load saved game");
            StdDraw.text(150, 50, "Type Q to quit game");
            StdDraw.text(150, 20, "Type I to read the " +
                    "instructions");

            Font font2 = new Font("Arial", Font.BOLD, 60);
            StdDraw.setFont(font2);
            StdDraw.show();
            if (StdDraw.isKeyPressed('N')) {
                System.out.println("N");
                cont = true;
                enterSeedScreen();
            }
            if (StdDraw.isKeyPressed('L')) {
                //MGP.stop(BGM);
                player.stop();
                p = p.loadGame();
                p.setAllTiles();
                Elements.lives().setChar(p.getLives());
                Elements.numminkilled().setChar(p.getNumberOfMinionsKilled());
                p.movePlayer();
            }
            if (StdDraw.isKeyPressed('Q')) {
                System.out.println("HEY!");
                System.exit(0);
            }
            if (StdDraw.isKeyPressed('I')) {
                System.out.println("HEY!");
                cont = true;
                Instructions i =  new Instructions();
            }
        }
    }

    public void enterSeedScreen() {
        StdDraw.clear(StdDraw.PINK);
        StdDraw.setXscale(0, 300);
        StdDraw.setYscale(0, 300);
        StdDraw.setFont();
        StdDraw.setPenColor(StdDraw.WHITE);
        StdDraw.show();
        cont = false;
        String seed = "";
        while (!cont) {
            Font font1 = new Font("Arial", Font.BOLD,30 );
            StdDraw.setFont(font1);
            StdDraw.text(150, 150, "Enter Seed below");
            Font font2 = new Font("Arial", Font.BOLD,5);
            StdDraw.setFont(font2);
            StdDraw.text(150, 100, "When you are done entering"
                    + " type S to start the game");
            Font font3 = new Font("Arial", Font.BOLD,30 );
            StdDraw.setFont(font3);
            seed = listenToTyping(seed);
            System.out.println(seed);
            if (StdDraw.isKeyPressed('S')) {
                StdDraw.clear(StdDraw.PINK);
                //MGP.stop(BGM);
                StdDraw.enableDoubleBuffering();
                player.stop();
                createWorld(seed);
                cont = true;
                return;

            }
        }
    }

    public String cleanSeed(String seed) {
        String seedNew = "";
        for (int i = 0; i < seed.length(); i++) {
            if (seed.charAt(i) >= 48 && seed.charAt(i) <= 57) {
                seedNew += seed.charAt(i);
            }
        }
        return seedNew;

    }

    public String listenToTyping(String s) {
        while (StdDraw.hasNextKeyTyped()) {
            char a = StdDraw.nextKeyTyped();
            if (a >= 48 && a <= 57) {
                s = s + a;
            }
            draw(s);
            //System.out.print(s + " ");
        }
        return s;
    }

    public void draw(String c) {
        StdDraw.clear(StdDraw.PINK);
        Font font1 = new Font("Arial", Font.BOLD,30 );
        StdDraw.setFont(font1);
        StdDraw.text(150, 200, "Enter Seed below");
        Font font2 = new Font("Arial", Font.BOLD,20 );
        StdDraw.setFont(font2);
        StdDraw.text(150, 150, "When you are done entering"
                + " type S to start the game");
        Font font3 = new Font("Arial", Font.BOLD,30 );
        StdDraw.setFont(font3);
        StdDraw.text(150, 70, c);
        StdDraw.show();
    }

    public void createWorld(String seed) {
        MyWorld w = new MyWorld();
        w.setSEED(seed);
        Player x = new Player(w.getTiles(), 20, 20, w.getSEED());
        x.movePlayer();
    }
}

