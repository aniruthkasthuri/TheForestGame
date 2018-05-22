package byog.World;

import edu.princeton.cs.introcs.StdDraw;

import java.awt.*;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

public class Instructions {
    private static String filename = "Player.ser";
    private static FileOutputStream fos = null;
    private static ObjectOutputStream out = null;

    public Instructions() {
        StdDraw.clear(StdDraw.PINK);
        StdDraw.setXscale(0, 300);
        StdDraw.setYscale(0, 300);
        print();
    }

    public void print() {
        StdDraw.setFont();
        StdDraw.setPenColor(StdDraw.WHITE);
        Font font1 = new Font("Arial", Font.BOLD,30 );
        StdDraw.setFont(font1);
        StdDraw.text(150, 290, "Instructions");
        Font font2 = new Font("Arial", Font.BOLD,15 );
        StdDraw.setFont(font2);
        StdDraw.text(150, 250, "Move with the keys A W S D. The first world is a practice world.\n");
        StdDraw.text(150, 230, "Enter a yellow doors to start! \n You can break rocks with pickaxes.\n");
        StdDraw.text(150, 210, "You can find pickaxes.\n");
        StdDraw.setPenColor(StdDraw.BOOK_LIGHT_BLUE);
        StdDraw.text(150, 190,"To win: Kill 15 pink minions!");
        StdDraw.text(150, 170,"To lose: Lose all your lives. You lose lives if the black minions eat you.");
        StdDraw.setPenColor(StdDraw.WHITE);
        StdDraw.text(150, 150,"Mini Games:  Sometimes a mini game will start!");
        StdDraw.text(150, 130,"\tSnake: Increase your # Minions killed count in 10 sec. Exit with space bar.");
        StdDraw.text(150, 110, "\tFly: Use space bar to fly. Every five points = One life.");
        StdDraw.setFont();
        StdDraw.setPenColor(StdDraw.BOOK_LIGHT_BLUE);
        Font font3 = new Font("Arial", Font.BOLD,30 );
        StdDraw.setFont(font3);
        StdDraw.text(150, 80, "Type e to return to intro screen");
        StdDraw.show();
        boolean cont = false;
        while (!cont) {
            if (StdDraw.isKeyPressed('E')) {

                TitleScreen t = new TitleScreen();
                cont = true;

            }
        }
    }

}



