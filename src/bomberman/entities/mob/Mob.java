package entities.mob;

import entities.AnimatedEntity;
import entities.Entity;
import javafx.scene.image.Image;

public abstract class Mob extends AnimatedEntity {

    private int direction;

    public Mob(int x, int y, Image img) {
        super( x, y, img);
    }

    @Override
    public void update() {

    }

    public abstract void move();
}
