package byog.World;

import edu.princeton.cs.introcs.StdDraw;

import java.awt.*;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
//import sun.audio.*;

public class WinningScreen {
    private static String filename = "Player.ser";
    private static FileOutputStream fos = null;
    private static ObjectOutputStream out = null;
    /*
    AudioPlayer MGP = AudioPlayer.player;
    AudioStream BGM;
    AudioData MD;
    ContinuousAudioDataStream loop = null;
    */

    public WinningScreen() {
        StdDraw.clear(StdDraw.PINK);
        StdDraw.setXscale(0, 300);
        StdDraw.setYscale(0, 300);
        print();


    }

    public void print() {
        Font font1 = new Font("Arial", Font.BOLD,90 );
        StdDraw.setFont(font1);
        StdDraw.setPenColor(StdDraw.WHITE);
        StdDraw.text(150, 200, "You have won!");
        Font font2 = new Font("Arial", Font.BOLD,30 );
        StdDraw.setFont(font2);
        StdDraw.setPenColor(StdDraw.WHITE);
        StdDraw.text(150, 120, "Type E to return to main menu");
        StdDraw.text(150, 100, "Type Q to exit");
        StdDraw.show();
        boolean cont = false;
        while (!cont) {
            if (StdDraw.isKeyPressed('E')) {
                System.out.println("SHROOTS");
                TitleScreen t = new TitleScreen();
                cont = true;

            }
            if (StdDraw.isKeyPressed('Q')) {
                System.out.println("HEY!");
                System.exit(0);
            }
        }
    }

}

