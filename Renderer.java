package byog.World.Flappyier;

import java.awt.Graphics;

import byog.World.Player;

import javax.swing.JPanel;

public class Renderer extends JPanel {

    private static final long serialVersionUID = 1L;

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Player.getFb().repaint(g);
    }

}
