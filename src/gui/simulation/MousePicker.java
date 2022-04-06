package gui.simulation;

import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import org.dyn4j.dynamics.Body;
import org.dyn4j.dynamics.DetectResult;
import org.dyn4j.dynamics.World;
import org.dyn4j.dynamics.joint.MotorJoint;
import org.dyn4j.geometry.Convex;
import org.dyn4j.geometry.Geometry;
import org.dyn4j.geometry.MassType;
import org.dyn4j.geometry.Transform;

import java.awt.geom.AffineTransform;
import java.awt.geom.NoninvertibleTransformException;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by johan on 2017-03-08.
 */
public class MousePicker {

    private Point2D mousePos = null;

    private Body body;
    private MotorJoint joint;

    public MousePicker(Node node) {
        EventHandler<? super MouseEvent> oldMouseClicked = node.getOnMouseClicked();
        EventHandler<? super MouseEvent> oldMouseReleased = node.getOnMouseReleased();
        EventHandler<? super MouseEvent> oldMouseDragged = node.getOnMouseDragged();

        node.setOnMouseClicked(e -> {
            if (oldMouseClicked != null) {
                oldMouseClicked.handle(e);
            }
            if (e.getButton() == MouseButton.PRIMARY) {
                this.mousePos = new Point2D.Double(e.getX(), e.getY());
            }
        });

        node.setOnMouseReleased(e -> {
            if (oldMouseReleased != null) {
                oldMouseReleased.handle(e);
            }
            this.mousePos = null;
        });

        node.setOnMouseDragged(e -> {
            if (oldMouseDragged != null) {
                oldMouseDragged.handle(e);
            }
            this.mousePos = new Point2D.Double(e.getX(), e.getY());
        });

    }

    public void update(World world, AffineTransform transform, double scale) {
        if (mousePos == null) {
            if (body != null) {
                world.removeBody(body);
                world.removeJoint(joint);
                body = null;
                joint = null;
            }
            return;
        }

        try {
            Point2D localMouse = transform.inverseTransform(mousePos, null);
            localMouse = new Point2D.Double(localMouse.getX() / scale, localMouse.getY() / -scale);

            if (body == null && joint == null) {
                Convex convex = Geometry.createCircle(0.1);
                Transform tx = new Transform();
                tx.translate(localMouse.getX(), localMouse.getY());

                // detect bodies under the mouse pointer
                List<DetectResult> results = new ArrayList<>();

                boolean detect = world.detect(
                        convex,
                        tx,
                        null, // no, don't filter anything using the Filters
                        false, // include sensor fixtures
                        false, // include inactive bodies
                        false, // we don't need collision info
                        results);

                if (detect) {
                    Body target = results.get(0).getBody();

                    target.setAutoSleepingEnabled(false);
                    target.setAsleep(false);
                    body = new Body();
                    body.setMass(MassType.INFINITE);
                    body.addFixture(convex);
                    body.getTransform().setTranslation(localMouse.getX(), localMouse.getY());
                    world.addBody(body);

                    joint = new MotorJoint(target, body);
                    joint.setCollisionAllowed(false);
                    joint.setMaximumForce(1000.0);
                    joint.setMaximumTorque(0.01);

                    world.addJoint(joint);
                }
            }

            if (body != null) {
                body.getTransform().setTranslation(localMouse.getX(), localMouse.getY());
            }
        } catch (NoninvertibleTransformException e) {
            e.printStackTrace();
        }
    }

}
