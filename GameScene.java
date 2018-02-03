/**
 * Created by poonna on 4/19/15 AD.
 */
import java.awt.Graphics;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GameScene extends JPanel implements ActionListener, KeyListener {
    public static final int KEY_UP = 0;
    public static final int KEY_DOWN = 1;
    public static final int KEY_LEFT = 2;
    public static final int KEY_RIGHT = 3;
    public static final int KEY_FIRE = 4;

    private boolean[] keyStates = new boolean[5];

    private SpaceShip player;
    private ArrayList<GameObject> enemies = new ArrayList<GameObject>();
    private ArrayList<GameObject> bullets = new ArrayList<GameObject>();

    private Random random;
    private int score;

    public GameScene() {
        setBackground(Color.BLACK);
        setDoubleBuffered(true);

        player = new SpaceShip(this, 400, 500);

        random = new Random();

        for (int i = 0; i < 3; i++) {
            Alien alien = new Alien(this, 100 + random.nextInt(700), 100 + random.nextInt(200));
            enemies.add(alien);
        }

        setFocusable(true);
        addKeyListener(this);

        Timer timer = new Timer(1000/50, this);
        timer.start();
    }

    public void update() {
        ArrayList<GameObject> deadList = new ArrayList<GameObject>();

        player.update();
        for (GameObject object : bullets) {
            if (object.update() == false) {
                deadList.add(object);
            }
        }
        for (GameObject object : enemies) {
            object.update();
        }

        for (GameObject bullet : bullets) {
            for (GameObject enemy : enemies) {
                if (bullet.collidedWith(enemy)) {
                    deadList.add(bullet);
                    deadList.add(enemy);
                    score += 10;
                }
            }
        }

        for (GameObject deadObject : deadList) {
            if (enemies.contains(deadObject)) {
                enemies.remove(deadObject);

                Alien alien = new Alien(this, 100 + random.nextInt(700), 100 + random.nextInt(200));
                enemies.add(alien);
            }
            if (bullets.contains(deadObject)) {
                bullets.remove(deadObject);
            }
        }

        repaint();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        player.draw(g);
        for (GameObject object : bullets) {
            object.draw(g);
        }
        for (GameObject object : enemies) {
            object.draw(g);
        }

        g.setColor(Color.WHITE);
        g.drawString("Score: " + score, 10, 20);
    }

    public boolean getKeyState(int key) {
        return keyStates[key];
    }

    public void addScore(int points) {
        score += points;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        update();
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // Do nothing
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                keyStates[KEY_UP] = true;
                break;
            case KeyEvent.VK_DOWN:
                keyStates[KEY_DOWN] = true;
                break;
            case KeyEvent.VK_LEFT:
                keyStates[KEY_LEFT] = true;
                break;
            case KeyEvent.VK_RIGHT:
                keyStates[KEY_RIGHT] = true;
                break;
            case KeyEvent.VK_SPACE:
                keyStates[KEY_FIRE] = true;
                Bullet bullet = new Bullet(this, player.getX(), player.getY());
                bullets.add(bullet);
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                keyStates[KEY_UP] = false;
                break;
            case KeyEvent.VK_DOWN:
                keyStates[KEY_DOWN] = false;
                break;
            case KeyEvent.VK_LEFT:
                keyStates[KEY_LEFT] = false;
                break;
            case KeyEvent.VK_RIGHT:
                keyStates[KEY_RIGHT] = false;
                break;
            case KeyEvent.VK_SPACE:
                keyStates[KEY_FIRE] = false;
                break;
        }
    }
}
