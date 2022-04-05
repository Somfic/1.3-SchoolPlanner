package gui;

import javafx.scene.canvas.GraphicsContext;

public interface GameNode {
    void onStart();

    void onRender(GraphicsContext context);

    void onUpdate(double deltaTime);
}
