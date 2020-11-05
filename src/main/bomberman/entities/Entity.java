package entities;

import constants.Constant;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;


public abstract class Entity {
    protected int x_pos;
    protected int y_pos;
    protected boolean isRemoved = false;
    protected Image image;
    protected RectangleBox boundedBox;

    public Entity(int x, int y, Image image) {
        x_pos = x;
        y_pos = y;
        this.image = image;
    }

    public abstract void update();
    public void render(GraphicsContext graphicsContext) {
        graphicsContext.drawImage(image, x_pos, y_pos);
    }
    public void remove() {
        isRemoved = true;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public RectangleBox getBoundedBox() {
        return boundedBox;
    }

    public boolean isColliding(Entity other) {
        RectangleBox otherBox = other.getBoundedBox();
        return boundedBox.checkCollision(otherBox);
    }
}
