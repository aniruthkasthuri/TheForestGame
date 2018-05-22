package byog.World.Snaker;

import byog.World.Player;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import javax.swing.JPanel;

public class RenderPanel extends JPanel {

    public static final Color GREEN = new Color(0000000);

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Snake snake = Player.getSn();

        g.setColor(GREEN);

        g.fillRect(0, 0, 800, 700);

        g.setColor(Color.BLUE);

        for (Point point : snake.getSnakeParts()) {
            g.fillRect(point.x * Snake.getSCALE(),
                    point.y * Snake.getSCALE(), Snake.getSCALE(),
                    Snake.getSCALE());
        }

        g.fillRect(snake.getHead().x * Snake.getSCALE(),
                snake.getHead().y * Snake.getSCALE(), Snake.getSCALE(),
                Snake.getSCALE());

        g.setColor(Color.RED);

        g.fillRect(snake.getCherry().x * Snake.getSCALE(),
                snake.getCherry().y * Snake.getSCALE(), Snake.getSCALE(),
                Snake.getSCALE());

        String string = "Score: " + snake.getScore() + ", Length: "
                + snake.getTailLength() + ", Time: " + snake.getTime() / 20;

        g.setColor(Color.white);

        g.drawString(string, (int) (getWidth() / 2 - string.length() * 2.5f),
                10);

        string = "Game Over!";

        if (snake.isOver()) {
            g.drawString(string, (int) (getWidth()
                            / 2 - string.length() * 2.5f),
                    (int) snake.getDim().getHeight() / 4);
        }

        string = "Paused!";

        if (snake.isPaused() && !snake.isOver()) {
            g.drawString(string, (int) (getWidth() / 2 - string.length()
                            * 2.5f),
                    (int) snake.getDim().getHeight() / 4);
        }
    }
}
