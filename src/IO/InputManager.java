package IO;

import javafx.scene.Scene;
import javafx.scene.input.*;

import java.util.Hashtable;

public class InputManager {

    private static Mouse mouse;
    private static Keyboard keys;

    private static Scene scene;

    private static MouseEvent mouseClickedEvent;
    private static MouseEvent mouseDraggedEvent;

    private static ScrollEvent scrollEvent;

    private static KeyEvent keyPressEvent;
    private static KeyEvent keyReleaseEvent;

    public static Mouse getMouse() {
        return mouse;
    }

    public static Keyboard getKeys() {
        return keys;
    }

    public static void setup(Scene scene) {
        InputManager.scene = scene;

        mouse = new Mouse();
        keys = new Keyboard();

        InputManager.scene.setOnMouseClicked(event -> {
            mouseClickedEvent = event;
        });

        InputManager.scene.setOnMouseDragged(event -> {
            mouseDraggedEvent = event;
        });

        InputManager.scene.setOnScroll(event -> {
            scrollEvent = event;
        });

        InputManager.scene.setOnKeyPressed(event -> {
            keyPressEvent = event;
        });

        InputManager.scene.setOnKeyReleased(event -> {
            keyReleaseEvent = event;
        });
    }

    public static void update() {
        if (mouseClickedEvent != null) {
            mouse.setLeftButton(mouseClickedEvent.getButton() == MouseButton.PRIMARY);
            mouse.setMiddleButton(mouseClickedEvent.getButton() == MouseButton.MIDDLE);
            mouse.setRightButton(mouseClickedEvent.getButton() == MouseButton.SECONDARY);

            mouseClickedEvent = null;
        }

        if (mouseDraggedEvent != null) {
            mouse.setX(mouseDraggedEvent.getX());
            mouse.setY(mouseDraggedEvent.getY());

            mouseDraggedEvent = null;
        }

        if (scrollEvent != null) {
            mouse.setDeltaScrollX(scrollEvent.getDeltaX());
            mouse.setDeltaScrollY(scrollEvent.getDeltaY());

            scrollEvent = null;
        }

        if (keyPressEvent != null) {
            keys.setKey(keyPressEvent.getCode(), true);

            keyPressEvent = null;
        }

        if (keyReleaseEvent != null) {
            keys.setKey(keyReleaseEvent.getCode(), false);

            keyReleaseEvent = null;
        }
    }

    private static class Mouse {
        private double x;
        private double y;

        private double deltaScrollX;
        private double deltaScrollY;

        private boolean leftButton;
        private boolean middleButton;
        private boolean rightButton;

        void setLeftButton(boolean state) {
            this.leftButton = state;
        }

        void setMiddleButton(boolean state) {
            this.middleButton = state;
        }

        void setRightButton(boolean state) {
            this.rightButton = state;
        }

        void setX(double x) {
            this.x = x;
        }

        void setY(double y) {
            this.y = y;
        }

        void setDeltaScrollX(double delta) {
            this.deltaScrollX = delta;
        }

        void setDeltaScrollY(double delta) {
            this.deltaScrollY = delta;
        }

        public double getX() {
            return this.x;
        }

        public double getY() {
            return this.y;
        }

        public boolean isLeftButton() {
            return this.leftButton;
        }

        public boolean isMiddleButton() {
            return this.middleButton;
        }

        public boolean isRightDown() {
            return this.rightButton;
        }

        public double getDeltaScrollX() {
            return this.deltaScrollX;
        }

        public double getDeltaScrollY() {
            return this.deltaScrollY;
        }
    }

    private static class Keyboard {
        private final Hashtable<KeyCode, Boolean> keys;

        public Keyboard() {
            this.keys = new Hashtable<>();
        }

        void setKey(KeyCode key, boolean state) {
            this.keys.put(key, state);
        }

        public boolean isKeyDown(KeyCode key) {
            return this.keys.get(key);
        }

        public boolean isKeyUp(KeyCode key) {
            return !this.keys.get(key);
        }
    }
}
