/**
 * Created by poonna on 4/24/15 AD.
 */
import javax.swing.*;
import java.awt.*;

public class SpaceShip extends GameObject {
    private GameScene scene;
    private Image image;
    private int x, y;
    private int speed = 10;

    public SpaceShip(GameScene scene, int x, int y) {
        this.scene = scene;

        ImageIcon loader = new ImageIcon(getClass().getResource("images/star.png"));
        image = loader.getImage();

        this.x = x - image.getWidth(null) / 2;
        this.y = y - image.getHeight(null) / 2;
    }

    @Override
    public boolean update() {
        if (scene.getKeyState(GameScene.KEY_UP)) {
            y -= speed;
        }
        if (scene.getKeyState(GameScene.KEY_DOWN)) {
            y += speed;
        }
        if (scene.getKeyState(GameScene.KEY_LEFT)) {
            x -= speed;
        }
        if (scene.getKeyState(GameScene.KEY_RIGHT)) {
            x += speed;
        }

        return true;
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(image, x, y, scene);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x, y, image.getWidth(null), image.getHeight(null));
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
