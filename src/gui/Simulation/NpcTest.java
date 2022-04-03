package Gui.Simulation;

import Gui.Simulation.Student;
import com.sun.glass.ui.Screen;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.jfree.fx.FXGraphics2D;
import org.jfree.fx.ResizableCanvas;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.NoninvertibleTransformException;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.Buffer;
import java.nio.channels.NetworkChannel;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

public class NpcTest extends Application {

    private ResizableCanvas canvas;

    @Override
    public void start(Stage stage) throws Exception {
        BorderPane mainPane = new BorderPane();
        canvas = new ResizableCanvas(g -> draw(g), mainPane);
        mainPane.setCenter(canvas);
        FXGraphics2D g2d = new FXGraphics2D(canvas.getGraphicsContext2D());
        new AnimationTimer() {
            long last = -1;
            @Override
            public void handle(long now) {
                if(last == -1)
                    last = now;
                update((now - last) / 1000000000.0);
                last = now;
                draw(g2d);
            }
        }.start();

        stage.setScene(new Scene(mainPane, 1500, 800));
        stage.setTitle("NPCs");

//        stage.setX(Screen.getScreens().get(1).getX());
//        stage.setY(Screen.getScreens().get(1).getY());
        stage.show();
        draw(g2d);

        canvas.setOnMouseMoved(event -> {
            for(Student student : this.students) {
                student.setTarget(new Point2D.Double(100, 100));
            }
        });

    }

    ArrayList<Student> students;
    double timer;

    public void init() {
        this.students = new ArrayList<>();
        while (this.students.size() < 5) {
            Student student = new Student(new Point2D.Double(Math.random()*1000, Math.random()*1000), 0);
            if(!student.checkCollision(this.students)) {
                this.students.add(student);
            }
        }

        timer = 0;
    }


    public void draw(FXGraphics2D g2) {
        g2.setTransform(new AffineTransform());
        g2.setBackground(new Color(255,255,255));
        g2.clearRect(0,0,(int)canvas.getWidth(), (int)canvas.getHeight());

        for(Student student : this.students) {
            student.draw(g2);
        }
    }

    public void update(double frameTime) {
        timer+=frameTime;

        for (Student student : this.students) {
            student.update(this.students);
        }
    }
}