/**
 * Created by poonna on 4/24/15 AD.
 */
import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class GameObject {
    // update() returns true in most cases, and false when it needs to destroy itself
    public abstract boolean update();

    public abstract void draw(Graphics g);

    public abstract Rectangle getBounds();

    public boolean collidedWith(GameObject other) {
        return getBounds().intersects(other.getBounds());
    }
}
