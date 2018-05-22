package byog.World.Flappyier;

import byog.TileEngine.Elements;
import byog.World.Player;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.Timer;

public class FlappyBird implements ActionListener, MouseListener, KeyListener {

    private static FlappyBird flappyBird;

    private final int width = 800, height = 800;

    private Renderer renderer;

    private Rectangle bird;

    private ArrayList<Rectangle> columns;

    private int ticks, yMotion, score;

    private boolean gameOver, started;

    private Random rand;
    private JFrame jframe;

    private Player play;

    public FlappyBird(Player p) {
        play = p;
    }

    public JFrame getJframe() {
        return jframe;
    }

    public Player getPlay() {
        return play;
    }

    public void startGame() {
        jframe = new JFrame();
        Timer timer = new Timer(20, this);

        renderer = new Renderer();
        rand = new Random();

        jframe.add(renderer);
        jframe.setTitle("Flappy Bird");
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.setSize(width, height);
        jframe.addMouseListener(this);
        jframe.addKeyListener(this);
        jframe.setResizable(false);
        jframe.setVisible(true);

        bird = new Rectangle(width / 2 - 10, height / 2 - 10, 20,
                20);
        columns = new ArrayList<Rectangle>();

        addColumn(true);
        addColumn(true);
        addColumn(true);
        addColumn(true);

        timer.start();
    }

    public void addColumn(boolean start) {
        int space = 300;
        int width1 = 100;
        int height1 = 50 + rand.nextInt(300);

        if (start) {
            columns.add(new Rectangle(this.width + width1 + columns.size() * 300,
                    this.height - height1 - 120, width1, height1));
            columns.add(new Rectangle(this.width + width1 + (columns.size() - 1)
                    * 300, 0, width1, this.height - height1 - space));
        } else {
            columns.add(new Rectangle(columns.get(columns.size() - 1).x
                    + 600, this.height - height1 - 120, width1, height1));
            columns.add(new Rectangle(columns.get(columns.size() - 1).x,
                    0, width1, this.height - height1 - space));
        }
    }

    public void paintColumn(Graphics g, Rectangle column) {
        g.setColor(Color.green.darker());
        g.fillRect(column.x, column.y, column.width, column.height);
    }

    public void jump() {
        if (gameOver) {
            bird = new Rectangle(width / 2 - 10, height / 2 - 10,
                    20, 20);
            columns.clear();
            yMotion = 0;
            score = 0;

            addColumn(true);
            addColumn(true);
            addColumn(true);
            addColumn(true);

            gameOver = false;
        }

        if (!started) {
            started = true;
        } else if (!gameOver) {
            if (yMotion > 0) {
                yMotion = 0;
            }

            yMotion -= 10;
        }
    }

    public void actionPerformed(ActionEvent e) {
        int speed = 10;
        ticks++;
        if (started) {
            for (int i = 0; i < columns.size(); i++) {
                Rectangle column = columns.get(i);
                column.x -= speed;
            }
            if (ticks % 2 == 0 && yMotion < 15) {
                yMotion += 2;
            }
            for (int i = 0; i < columns.size(); i++) {
                Rectangle column = columns.get(i);
                if (column.x + column.width < 0) {
                    columns.remove(column);
                    if (column.y == 0) {
                        addColumn(false);
                    }
                }
            }
            bird.y += yMotion;
            for (Rectangle column : columns) {
                if (column.y == 0 && bird.x + bird.width / 2 > column.x
                        + column.width / 2 - 10 && bird.x + bird.width / 2
                        < column.x + column.width / 2 + 10) {
                    score++;
                    if (score % 5 == 0) {
                        play.setLives(play.getLives() + 1);
                        //System.out.println("YEET: " + play.lives);
                        Elements.lives().setChar(play.getLives());
                    }
                }
                if (column.intersects(bird)) {
                    gameOver = true;
                    if (bird.x <= column.x) {
                        bird.x = column.x - bird.width;
                    } else {
                        if (column.y != 0) {
                            bird.y = column.y - bird.height;
                        } else if (bird.y < column.height) {
                            bird.y = column.height;
                        }
                    }
                }
            }
            if (bird.y > height - 120 || bird.y < 0) {
                gameOver = true;
            }
            if (bird.y + yMotion >= height - 120) {
                bird.y = height - 120 - bird.height;
                gameOver = true;
            }
        }
        renderer.repaint();
    }

    public void repaint(Graphics g) {
        g.setColor(Color.cyan);
        g.fillRect(0, 0, width, height);

        g.setColor(Color.orange);
        g.fillRect(0, height - 120, width, 120);

        g.setColor(Color.green);
        g.fillRect(0, height - 120, width, 20);

        g.setColor(Color.red);
        g.fillRect(bird.x, bird.y, bird.width, bird.height);

        for (Rectangle column : columns) {
            paintColumn(g, column);
        }

        g.setColor(Color.white);
        g.setFont(new Font("Arial", 1, 100));

        if (!started) {
            g.drawString("Click to start!", 75, height / 2 - 50);
        }

        if (gameOver) {
            g.drawString("Game Over!", 100, height / 2 - 50);
            jframe.setVisible(false);
            jframe.dispose();
        }

        if (!gameOver && started) {
            g.drawString(String.valueOf(score), width / 2 - 25, 100);
        }
    }


    public void mouseClicked(MouseEvent e) {
        jump();
    }

    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            jump();
        }
    }

    public void mousePressed(MouseEvent e) {
    }

    public void mouseReleased(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

    public void keyTyped(KeyEvent e) {

    }

    public void keyPressed(KeyEvent e) {

    }

}
