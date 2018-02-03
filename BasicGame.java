/**
 * Created by poonna on 4/24/15 AD.
 */
import java.awt.*;
import javax.swing.JFrame;

public class BasicGame {
    public static void main(String[] args) {
        JFrame game = new JFrame();
        game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        game.setSize(800, 600);
        game.add(new GameScene(), BorderLayout.CENTER);
        game.setVisible(true);
    }
}
