package bomberman.entities.mob.enemy;

import bomberman.BombermanGame;
import bomberman.entities.mob.Mob;
import bomberman.graphics.Sprite;
import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

public class Balloom extends Enemy {

    public Balloom(int x, int y, Image img) {
        super(x, y, img);
        setSpeed(1);
        setSprite(Sprite.balloom_dead);
    }

    @Override
    public void update() {
        move();
    }

    @Override
    public void move() {
        if (!isRemoved()) {
            if (x % Sprite.SCALED_SIZE == 0 && y % Sprite.SCALED_SIZE == 0) {
                play();
                animate = 0;
            } else {
                animate++;
            }

            switch (getDirection()) {
                case 0: {
                    img = Sprite.movingSprite(Sprite.balloom_right1, Sprite.balloom_right2, Sprite.balloom_right3, animate, 30).getFxImage();
                    x += getSpeed();
                    break;
                }
                case 1: {
                    img = Sprite.movingSprite(Sprite.balloom_left1, Sprite.balloom_left2, Sprite.balloom_left3, animate, 30).getFxImage();
                    x -= getSpeed();
                    break;
                }
                case 2: {
                    y -= getSpeed();
                    break;
                }
                case 3: {
                    y += getSpeed();
                    break;
                }
                default: {
                    break;
                }
            }
        }
    }

    public void play() {
        ArrayList<Integer> _direction = new ArrayList<>();
        if (canMove(x - getSpeed(), y)) _direction.add(1);
        if (canMove(x + getFat() - 1 + getSpeed(), y)) _direction.add(0);
        if (canMove(x, y + Sprite.SCALED_SIZE - 1 + getSpeed())) _direction.add(3);
        if (canMove(x, y - getSpeed())) _direction.add(2);
        if (_direction.size() == 0) {
            setDirection(-1);
        } else {
            double index = Math.random() * (_direction.size());
            setDirection(_direction.get((int) index));
        }
    }

//    @Override
//    public void kill() {
//        if (getDeadTime() == -1) {
//            setDeadTime(System.currentTimeMillis());
//        }
//        if (System.currentTimeMillis() - getDeadTime() <= 400) {
//            img = Sprite.balloom_dead.getFxImage();
//        } else {
//            img = Sprite.movingSprite(Sprite.mob_dead1, Sprite.mob_dead2, Sprite.mob_dead3, System.currentTimeMillis() - getDeadTime(), 600).getFxImage();
//        }
//    }
}
