package flappyBird;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.MathUtils;

public class FlappyBird extends ApplicationAdapter {
    private OrthographicCamera camera;
    private SpriteBatch batch;
    private Texture birdTexture;
    private Texture pipeTexture;
    private Texture gameOverTexture;
    private Vector2 birdPosition;
    private Vector2 birdVelocity;
    private Rectangle birdBounds;
    private Rectangle[] pipeBounds;
    private float pipeTimer;
    private boolean gameOver;
    private int score;

    @Override
    public void create() {
        camera = new OrthographicCamera();
        camera.setToOrtho(true, 640, 480);
        batch = new SpriteBatch();
        birdTexture = new Texture("bird.png");
        pipeTexture = new Texture("pipe.png");
        gameOverTexture = new Texture("gameover.png");

        resetGame();
    }

    private void resetGame() {
        birdPosition = new Vector2(320, 240);
        birdVelocity = new Vector2(0, 0);
        birdBounds = new Rectangle(birdPosition.x, birdPosition.y, 50, 50);
        pipeBounds = new Rectangle[2];
        pipeBounds[0] = new Rectangle(640, 0, 80, 600);
        pipeBounds[1] = new Rectangle(640, 0, 80, 600);
        pipeTimer = 0;
        gameOver = false;
        score = 0;
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();
        batch.setProjectionMatrix(camera.combined);

        batch.begin();
        batch.draw(birdTexture, birdPosition.x, birdPosition.y);
        for (Rectangle pipe : pipeBounds) {
            batch.draw(pipeTexture, pipe.x, pipe.y);
        }
        if (gameOver) {
            batch.draw(gameOverTexture, 320 - gameOverTexture.getWidth() / 2, 240 - gameOverTexture.getHeight() / 2);
        }
        batch.end();

        if (!gameOver) {
            updateGame();
        } else if (Gdx.input.justTouched()) {
            resetGame();
        }
    }

    private void updateGame() {
        handleInput();
        updateBird();
        updatePipes();
        checkCollisions();
    }

    private void handleInput() {
        if (Gdx.input.justTouched()) {
            birdVelocity.y = -10;
        }
    }

    private void updateBird() {
        birdVelocity.y += 0.5;
        birdPosition.y += birdVelocity.y;
        birdBounds.setPosition(birdPosition.x, birdPosition.y);
    }

    private void updatePipes() {
        pipeTimer += Gdx.graphics.getDeltaTime();
        if (pipeTimer > 2) {
            pipeTimer = 0;
            float gapY = MathUtils.random(100, 400);
            pipeBounds[0].y = gapY + 150; // top pipe
            pipeBounds[1].y = gapY - pipeBounds[1].height; // bottom pipe
            score++; // Increment score when new pipes are created
        }

        for (Rectangle pipe : pipeBounds) {
            pipe.x -= 4;
            if (pipe.x < -pipe.width) {
                pipe.x = 640; // Reset pipe position
            }
        }
    }

    private void checkCollisions() {
        for (Rectangle pipe : pipeBounds) {
            if (pipe.overlaps(birdBounds)) {
                gameOver = true;
            }
        }
        if (birdPosition.y < 0 || birdPosition.y > 480) {
            gameOver = true;
        }
    }

    @Override
    public void dispose() {
        batch.dispose();
        birdTexture.dispose();
        pipeTexture.dispose();
        gameOverTexture.dispose();
    }
}
