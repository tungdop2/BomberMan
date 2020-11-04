package bomberman;

import bomberman.entities.Entity;
import bomberman.entities.bomb.Bomb;
import bomberman.entities.mob.Bomber;
import bomberman.entities.terrain.Grass;
import bomberman.entities.terrain.Wall;
import bomberman.graphics.Sprite;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class BombermanGame extends Application {

    public static final int WIDTH = 31;
    public static final int HEIGHT = 13;

    public static List<Entity> mobs = new ArrayList<>();
    public static List<Entity> bombs = new ArrayList<>();
    public static List<Entity> textures = new ArrayList<>();

    private GraphicsContext gc;
    private Canvas canvas;

    private Entity bomberman = new Bomber(1, 1, Sprite.player_right.getFxImage());
    private Entity bomb = new Bomb(2, 2, Sprite.bomb.getFxImage());

    public static void main(String[] args) {
        Application.launch(BombermanGame.class);
    }

    @Override
    public void start(Stage stage) {
        // Tao Canvas
        canvas = new Canvas(Sprite.SCALED_SIZE * WIDTH, Sprite.SCALED_SIZE * HEIGHT);
        gc = canvas.getGraphicsContext2D();

        // Tao root container
        Group root = new Group();
        root.getChildren().add(canvas);

        // Tao scene
        Scene scene = new Scene(root, Sprite.SCALED_SIZE * WIDTH, Sprite.SCALED_SIZE * HEIGHT);

        // Them scene vao stage
        stage.setScene(scene);
        stage.show();

        final double ns = 1000000000.0 / 60.0;

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                render();
                update();
            }
        };

        timer.start();

        createMap();

        mobs.add(bomberman);
        bombs.add(bomb);
    }

    public void createMap() {
        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < HEIGHT; j++) {
                Entity object;
                if (j == 0 || j == HEIGHT - 1 || i == 0 || i == WIDTH - 1) {
                    object = new Wall(i, j, Sprite.wall.getFxImage());
                } else {
                    object = new Grass(i, j, Sprite.grass.getFxImage());
                }
                textures.add(object);
            }
        }
    }

    public void update() {
        mobs.forEach(Entity::update);
        bombs.forEach(Entity::update);
    }

    public void render() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        textures.forEach(g -> g.render(gc));
        mobs.forEach(g -> g.render(gc));
        bombs.forEach(g -> g.render(gc));
    }


}
