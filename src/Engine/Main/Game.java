package Engine.Main;

import Engine.Drawing.DrawingPanel;
import Engine.Test.TestObject;

/**
 *
 * @author Moolt
 */
public class Game {

    private final double MS_PER_UPDATE = 17D;
    private Scene level;
    private Window gameWindow;
    private DrawingPanel drawingPanel;

    public Game() {
        this.level = new Scene();
        this.drawingPanel = new DrawingPanel(level);
        this.gameWindow = new Window(drawingPanel, 640, 480);
        this.GameLoop();
    }

    //http://gameprogrammingpatterns.com/game-loop.html
    private void GameLoop() {
        Entity e = new TestObject();
        level.AddEntity(e);      

        double previous = System.currentTimeMillis();
        double lag = 0D;

        while (true) {
            double current = System.currentTimeMillis();
            double elapsed = current - previous;
            previous = current;
            lag += elapsed;

            //process input

            while (lag >= MS_PER_UPDATE) {
                this.Update();
                lag -= MS_PER_UPDATE;
            }

            this.Draw(lag / MS_PER_UPDATE);
        }
    }

    private void Draw(double lag) {
        this.drawingPanel.Draw(lag);
    }

    private void Update() {
        level.Update();
    }
}
