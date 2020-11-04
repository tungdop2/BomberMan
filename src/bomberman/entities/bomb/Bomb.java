package bomberman.entities.bomb;

import bomberman.BombermanGame;
import bomberman.entities.AnimatedEntity;
import bomberman.graphics.Sprite;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Bomb extends AnimatedEntity {
    private int _timeToExplode = 12;

    public Bomb(int x, int y, Image img) {
        super(x, y, img);
    }

    @Override
    public void update() {
        if(_timeToExplode > 0) {
            _timeToExplode--;
        }
        else {
            _remove = true;
        }

        super.update();
        animate();
    }

    @Override
    public void render(GraphicsContext gc) {
        Sprite _tmp_sprite = Sprite.movingSprite(Sprite.bomb, Sprite.bomb_1, Sprite.bomb_2, _animate, 6);
        img = _tmp_sprite.getFxImage();
        super.render(gc);
    }
}
