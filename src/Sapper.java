import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import Sapper.Box;
import Sapper.Position;
import Sapper.Ranges;
import Sapper.Game;

public class Sapper extends JFrame {

    private Game game;
    private JPanel panel;
    private JLabel label;
    private final int COLUMNS = 9;
    private final int ROWS = 9;
    private final int BOMBS = 10;
    private final int IMAGE_SIZE = 50;

    public static void main(String[] args) {
        new Sapper();
    }

    private Sapper() {
        game = new Game(COLUMNS, ROWS, BOMBS);
        game.start();
        setImages();
        initLabel();
        initPanel();
        initFrame();
    }


    private void initLabel () {
        label = new JLabel("Good luck!!!");
        add(label, BorderLayout.SOUTH);
    }

    private void initPanel() {
        panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponents(g);
                for (Position position : Ranges.getAllPositions())
                    g.drawImage((Image) game.getBox(position).image,
                            position.x * IMAGE_SIZE, position.y * IMAGE_SIZE, this);
            }
        };
        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int x = e.getX() / IMAGE_SIZE;
                int y = e.getY() / IMAGE_SIZE;
                Position position = new Position(x, y);
                if (e.getButton() == MouseEvent.BUTTON1) {
                    game.pressLeftButton(position);
                }
                if (e.getButton() == MouseEvent.BUTTON2) {
                    game.start();
                }
                if (e.getButton() == MouseEvent.BUTTON3) {
                    game.pressRightButton(position);
                }
                label.setText(getMessage());
                panel.repaint();
            }
        });
        panel.setPreferredSize(new Dimension(Ranges.getSize().x * IMAGE_SIZE, Ranges.getSize().y * IMAGE_SIZE));
        add(panel);
    }

    private String getMessage() {
        switch (game.getState()) {
            case PLAYED: return "Think twice!";
            case BOMBED: return "You lose!";
            case WINNER: return "Congratulations!";
            default: return "Welcome!";
        }
    }

    private void initFrame() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Sapper");
        setResizable(false);
        setVisible(true);
        pack();
        setLocationRelativeTo(null);
        setIconImage(getImage("icon"));
    }

    private void setImages() {
        for (Box box : Box.values()) {
            box.image = getImage(box.name().toLowerCase());
        }
    }

    private Image getImage (String name) {
        String filename = "images/" + name.toLowerCase() + ".png";
        ImageIcon icon = new ImageIcon(getClass().getResource(filename));
        return icon.getImage();
    }
}
