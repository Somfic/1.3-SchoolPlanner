package gui.simulation;

import javafx.scene.Node;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;

import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;


/**
 * Created by johan on 15-2-2017.
 * Rewritten by owen on 5-4-2022.
 */
public class Camera {
    private Point2D centerPoint;
    private double zoom = 1;
    private Point2D lastMousePos;
    private final SimulationView simView;

    public Camera(SimulationView simulationView) {
        Node canvas = simulationView.getPane();
        this.simView = simulationView;
        this.centerPoint = new Point2D.Double(-simView.getCanvas().getWidth() / 2, -simView.getCanvas().getHeight() / 2);

        canvas.setOnMousePressed(e -> lastMousePos = new Point2D.Double(e.getX(), e.getY()));
        canvas.setOnMouseDragged(this::mouseDragged);
        canvas.setOnScroll(this::mouseScroll);
    }


    public AffineTransform getTransform() {
        AffineTransform tx = new AffineTransform();
        tx.translate(simView.getCanvas().getWidth() / 2, simView.getCanvas().getHeight() / 2);
        tx.scale(zoom, zoom);
        tx.translate(centerPoint.getX(), centerPoint.getY());
        double rotation = 0;
        tx.rotate(rotation);
        return tx;
    }

    public void mouseDragged(MouseEvent e) {
        if (e.getButton() == MouseButton.PRIMARY) {
            centerPoint = new Point2D.Double(
                    centerPoint.getX() - (lastMousePos.getX() - e.getX()) / zoom,
                    centerPoint.getY() - (lastMousePos.getY() - e.getY()) / zoom
            );
            lastMousePos = new Point2D.Double(e.getX(), e.getY());
            simView.setToUpdateBackground(true);
        }
    }

    public void mouseScroll(ScrollEvent e) {
        double newZoom = zoom * (1 + e.getDeltaY() / 500.0f);
        if (newZoom > 0.7909116947642189 && newZoom < 2.783600819634628) {
            zoom = newZoom;
            simView.setToUpdateBackground(true);
        }
    }
}
