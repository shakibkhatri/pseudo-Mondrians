package lottery;

/*
 * -Shakib Khatri
 * 
 * https://shakibkhatri.netlify.app/
 */

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class PseudoMondrian extends JFrame {

    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;

    public PseudoMondrian() {
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setTitle("Pseudo Mondrian");
        add(new MondrianPanel());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new PseudoMondrian().setVisible(true);
        });
    }
}

class MondrianPanel extends JPanel {

    private static final int MIN_SIZE = 50;
    private static final int MAX_SIZE = 300;
    private static final int MAX_DEPTH = 4;

    private Random random;

    public MondrianPanel() {
        random = new Random();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawMondrian(g, 0, 0, getWidth(), getHeight(), 0);
    }

    private void drawMondrian(Graphics g, int x, int y, int width, int height, int depth) {
        if (depth >= MAX_DEPTH || width <= MIN_SIZE || height <= MIN_SIZE) {
            g.setColor(getRandomColor());
            g.fillRect(x, y, width, height);
        } else {
            int choice =random.nextInt(3);
            if (choice == 0) { 
                int split = getRandomNumber(MIN_SIZE, height - MIN_SIZE);
                drawMondrian(g, x, y, width, split, depth + 1);
                drawMondrian(g, x, y + split, width, height - split, depth + 1);
            } else if (choice == 1) {
                int split = getRandomNumber(MIN_SIZE, width - MIN_SIZE);
                drawMondrian(g, x, y, split, height, depth + 1);
                drawMondrian(g, x + split, y, width - split, height, depth + 1);
            } else { 
                g.setColor(getRandomColor());
                g.fillRect(x, y, width, height);
            }
        }
    }

    private Color getRandomColor() {
        return new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256));
    }

    private int getRandomNumber(int min, int max) {
        return random.nextInt(max - min + 1) + min;
    }
}


