package entities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;


public abstract class Entity {
    protected int x_pos;
    protected int y_pos;
    protected boolean isRemoved = false;
    protected Image image;

    public abstract void update();
    public abstract void render(GraphicsContext graphicsContext);
    public void remove() {
        isRemoved = true;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public int getX_pos() {
        return x_pos;
    }

    public int getY_pos() {
        return y_pos;
    }

    public int getX_Center() {
        return (int) (x_pos + image.getWidth() / 2);
    }

    public int getY_Center() {
        return (int) (y_pos + image.getWidth() / 2);
    }
}
