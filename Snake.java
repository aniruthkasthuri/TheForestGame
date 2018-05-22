package byog.World.Snaker;

import byog.TileEngine.Elements;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.Timer;

import byog.World.Player;

public class Snake implements ActionListener, KeyListener {

    private static Snake snake;

    private JFrame jframe;

    private RenderPanel renderPanel;

    private Timer timer = new Timer(20, this);

    private ArrayList<Point> snakeParts = new ArrayList<Point>();

    private static final int UP = 0, DOWN = 1, LEFT = 2, RIGHT = 3, SCALE = 10;

    private int ticks = 0, direction = DOWN, score, tailLength = 10, time;

    private Point head, cherry;

    private Random random;

    private boolean over = false, paused;

    private Dimension dim;

    private Player play;

    //public static void main(String[] args)
    //{
    //snake = new Snake();
    //}

    public Snake(Player p) {
        play = p;
        dim = Toolkit.getDefaultToolkit().getScreenSize();
        jframe = new JFrame("Snake");
        jframe.setVisible(true);
        jframe.setSize(805, 700);
        jframe.setResizable(false);
        jframe.setLocation(dim.width / 2 - jframe.getWidth() / 2,
                dim.height / 2 - jframe.getHeight() / 2);
        jframe.add(renderPanel = new RenderPanel());
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.addKeyListener(this);
        //startGame();
    }

    public ArrayList<Point> getSnakeParts() {
        return snakeParts;
    }

    public Point getHead() {
        return head;
    }

    public Point getCherry() {
        return cherry;
    }

    public int getTime() {
        return time;
    }

    public int getScore() {
        return score;
    }

    public int getTailLength() {
        return tailLength;
    }

    public boolean isOver() {
        return over;
    }

    public boolean isPaused() {
        return paused;
    }

    public Dimension getDim() {
        return dim;
    }

    public static int getSCALE() {
        return SCALE;
    }

    public static Snake getSnake() {
        return snake;
    }

    public Player getPlay() {
        return play;
    }


    public void startGame() {
        over = false;
        paused = false;
        time = 0;
        score = 0;
        tailLength = 5;
        ticks = 0;
        direction = DOWN;
        head = new Point(0, -1);
        random = new Random();
        snakeParts.clear();
        cherry = new Point(random.nextInt(79),
                random.nextInt(66));
        timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        renderPanel.repaint();
        ticks++;

        if (ticks % 2 == 0 && head != null && !over && !paused) {
            time++;
            //System.out.println("TIME: "+time);
            snakeParts.add(new Point(head.x, head.y));
            if (time == 200) {
                over = true;
            }
            if (direction == UP) {
                if (head.y - 1 >= 0 && noTailAt(head.x, head.y - 1)) {
                    head = new Point(head.x, head.y - 1);
                } else {
                    over = true;

                }
            }

            if (direction == DOWN) {
                if (head.y + 1 < 67 && noTailAt(head.x, head.y + 1)) {
                    head = new Point(head.x, head.y + 1);
                } else {
                    over = true;
                }
            }

            if (direction == LEFT) {
                if (head.x - 1 >= 0 && noTailAt(head.x - 1, head.y)) {
                    head = new Point(head.x - 1, head.y);
                } else {
                    over = true;
                }
            }

            if (direction == RIGHT) {
                if (head.x + 1 < 80 && noTailAt(head.x + 1, head.y)) {
                    head = new Point(head.x + 1, head.y);
                } else {
                    over = true;
                }
            }

            if (snakeParts.size() > tailLength) {
                snakeParts.remove(0);

            }

            if (cherry != null) {
                if (head.equals(cherry)) {
                    score += 1;
                    play.setNumberOfMinionsKilled(
                            play.getNumberOfMinionsKilled() + 1);
                    //System.out.println("YEET: " +
                    // play.numberOfMinionsKilled);
                    Elements.numminkilled().setChar(
                            play.getNumberOfMinionsKilled());
                    tailLength++;
                    cherry.setLocation(random.nextInt(79),
                            random.nextInt(66));
                }
            }
        }
    }

    public boolean noTailAt(int x, int y) {
        for (Point point : snakeParts) {
            if (point.equals(new Point(x, y))) {
                return false;
            }
        }
        return true;
    }

    public void keyPressed(KeyEvent e) {
        int i = e.getKeyCode();

        if ((i == KeyEvent.VK_A || i == KeyEvent.VK_LEFT)
                && direction != RIGHT) {
            direction = LEFT;
        }

        if ((i == KeyEvent.VK_D || i == KeyEvent.VK_RIGHT)
                && direction != LEFT) {
            direction = RIGHT;
        }

        if ((i == KeyEvent.VK_W || i == KeyEvent.VK_UP)
                && direction != DOWN) {
            direction = UP;
        }

        if ((i == KeyEvent.VK_S || i == KeyEvent.VK_DOWN)
                && direction != UP) {
            direction = DOWN;
        }

        if (i == KeyEvent.VK_SPACE) {
            if (over) {
                jframe.setVisible(false);
                jframe.dispose();
                //startGame();
            } else {
                paused = !paused;
            }
        }
    }

    public void keyReleased(KeyEvent e) {
    }

    public void keyTyped(KeyEvent e) {
    }

}
