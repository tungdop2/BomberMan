package bomberman.entities;

import bomberman.BombermanGame;
import bomberman.entities.bomb.Bomb;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public abstract class AnimatedEntity extends Entity {

    protected double _animate = 0;
    protected final int MAX_ANIMATE = 1000; //save the animation status and dont let this get too big

    public void animate() {
        if(_animate < MAX_ANIMATE) {
            _animate += 1;
        } else {
            _animate = 0; //reset animation
        }
    }

    public AnimatedEntity(int x, int y, Image img) {
        super(x, y, img);
    }

    @Override
    public void update() {
        if (_remove) {
            if (this instanceof Bomb)
            {
                BombermanGame.bombs.remove(this);
            }
        }
    }

    @Override
    public void render(GraphicsContext gc) {
        super.render(gc);
    }
}
