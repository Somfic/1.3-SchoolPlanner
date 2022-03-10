package IO;

import javafx.scene.Scene;
import javafx.scene.input.*;

import java.util.Hashtable;

public class InputManager {

    public final Mouse mouse;
    public final Keyboard keys;

    private final Scene scene;

    private MouseEvent mouseClickedEvent;
    private MouseEvent mouseDraggedEvent;

    private ScrollEvent scrollEvent;

    private KeyEvent keyPressEvent;
    private KeyEvent keyReleaseEvent;

    public InputManager(Scene scene) {
        this.scene = scene;

        this.mouse = new Mouse();
        this.keys = new Keyboard();

        this.scene.setOnMouseClicked(event -> {
            this.mouseClickedEvent = event;
        });

        this.scene.setOnMouseDragged(event -> {
            this.mouseDraggedEvent = event;
        });

        this.scene.setOnScroll(event -> {
            this.scrollEvent = event;
        });

        this.scene.setOnKeyPressed(event -> {
            this.keyPressEvent = event;
        });

        this.scene.setOnKeyReleased(event -> {
            this.keyReleaseEvent = event;
        });
    }

    public void update() {
        if(this.mouseClickedEvent != null) {
            this.mouse.setLeftButton(this.mouseClickedEvent.getButton() == MouseButton.PRIMARY);
            this.mouse.setMiddleButton(this.mouseClickedEvent.getButton() == MouseButton.MIDDLE);
            this.mouse.setRightButton(this.mouseClickedEvent.getButton() == MouseButton.SECONDARY);

            this.mouseClickedEvent = null;
        }

        if(this.mouseDraggedEvent != null) {
            this.mouse.setX(this.mouseDraggedEvent.getX());
            this.mouse.setY(this.mouseDraggedEvent.getY());

            this.mouseDraggedEvent = null;
        }

        if(this.scrollEvent != null) {
            this.mouse.setDeltaScrollX(this.scrollEvent.getDeltaX());
            this.mouse.setDeltaScrollY(this.scrollEvent.getDeltaY());

            this.scrollEvent = null;
        }

        if(this.keyPressEvent != null) {
            this.keys.setKey(this.keyPressEvent.getCode(), true);
            
            this.keyPressEvent = null;
        }

        if(this.keyReleaseEvent != null) {
            this.keys.setKey(this.keyReleaseEvent.getCode(), false);

            this.keyReleaseEvent = null;
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
