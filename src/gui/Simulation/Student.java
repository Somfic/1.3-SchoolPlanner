package Gui.Simulation;

import javafx.util.Pair;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class Student {
    private Point2D position;
    private double angle;
    private ArrayList<BufferedImage> sprites;
    private double speed;
    private double frame;
    private Point2D target;
    private static double rotationSpeed = 0.1;


    public Student(Point2D position, double angle) {

        this.position = position;
        this.angle = angle;
        this.speed = 3;
        this.target = position;
        this.frame = Math.random()*10;

        this.sprites = new ArrayList<>();
        try {
            BufferedImage image = ImageIO.read(getClass().getResourceAsStream("/npc.png"));
            int w = image.getWidth()/2;
            int h = image.getHeight()/3;
            for(int y = 0; y < 3; y++) {
                for(int x = 0; x < 2; x++) {
                    this.sprites.add(image.getSubimage(x * w, y * h, w,h));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void update(ArrayList<Student> students) {
        if (target.distanceSq(position) < 32)
            return;

        double targetAngle = Math.atan2(this.target.getY() - this.position.getY(), this.target.getX() - this.position.getX());
        double rotation = targetAngle - this.angle;
        while (rotation < -Math.PI) {
            rotation += 2*Math.PI;
        }
        while (rotation > Math.PI) {
            rotation -= 2*Math.PI;
        }

        double oldAngle = this.angle;
        if (rotation < -rotationSpeed) {
            this.angle -= rotationSpeed;
        } else if (rotation > rotationSpeed) {
            this.angle += rotationSpeed;
        } else {
            this.angle = targetAngle;
        }

        Point2D oldPosition = this.position;

        this.position = new Point2D.Double(
            this.position.getX() + this.speed * Math.cos(this.angle),
            this.position.getY() + this.speed * Math.sin(this.angle));
        boolean hasCollision = false;
        hasCollision = hasCollision || checkCollision(students);


        if (hasCollision) {
            this.position = oldPosition;
            this.frame = 0;
            this.angle = oldAngle + rotationSpeed;
        } else {
            this.frame += 0.1;
        }
    }

    public boolean checkCollision(ArrayList<Student> students) {
        boolean hasCollision = false;
        for (Student student : students) {
            if (student != this) {
                if (student.position.distanceSq(position) < 64*64) {
//                    hasCollision = true;
                }
            }
        }
        return hasCollision;
    }

    public void draw(Graphics2D g2d) {
        int centerX = sprites.get(0).getWidth()/2;
        int centerY = sprites.get(0).getHeight()/2;
        AffineTransform tx = new AffineTransform();
        tx.translate(position.getX() - centerX, position.getY() - centerY);
        tx.rotate(angle + Math.PI/2, centerX, centerY);
        tx.translate(0, 20);
        tx.scale(0.5, 0.5);


        g2d.drawImage(this.sprites.get((int)Math.floor(frame) % this.sprites.size()), tx, null);


//        g2d.setColor(Color.white);
//        g2d.draw(new Ellipse2D.Double(position.getX()-32, position.getY()-32, 8, 8));
//        g2d.draw(new Line2D.Double(position, target));
    }

    public void setTarget(Point2D newTarget) {
        this.target = newTarget;
    }

    public static ArrayList<Pair<Integer, Integer>> route(ArrayList<ArrayList<Integer>> map, int sourceX, int sourceY, int destinationX, int destinationY) {
        ArrayList<Pair<Integer, Integer>> route = new ArrayList<>();
        ArrayList<ArrayList<Integer>> paths = (ArrayList<ArrayList<Integer>>) map.stream().map(ArrayList::new).collect(Collectors.toList());

        if(sourceX == destinationX && sourceY == destinationY) {
            return new ArrayList<>();
        }
        for (int i = 0; i<map.size(); i++) {
            for (int j = 0; j<map.get(i).size(); j++) {
                paths.get(i).set(j, 0);
            }
        }
        paths.get(destinationY).set(destinationX, 1);
        boolean change = false;
        int i = 1;
        while (true) {
            for (int y = 0; y <= map.size() - 1; y++) {
                for (int x = 0; x <= map.get(y).size() - 1; x++) {
                    if (paths.get(y).get(x) == i) {
                        if (y != 0 && map.get(y - 1).get(x) == 1 && paths.get(y-1).get(x) == 0) {
                            paths.get(y - 1).set(x, i+1);
                            change = true;
                        }

                        if (y != map.size() - 1 && map.get(y + 1).get(x) == 1 && paths.get(y+1).get(x) == 0) {
                            paths.get(y + 1).set(x, i+1);
                            change = true;
                        }
                        if (x != 0 && map.get(y).get(x - 1) == 1 && paths.get(y).get(x-1) == 0) {
                            paths.get(y).set(x - 1, i+1);
                            change = true;
                        }
                        if (x != map.get(y).size() -1&& map.get(y).get(x + 1) == 1 && paths.get(y).get(x+1) == 0) {
                            paths.get(y).set(x + 1, i+1);
                            change = true;
                        }
                    }
                }
            }

            if(!change || paths.get(sourceY).get(sourceX) !=0) {
                break;
            }
            change = false;
            i++;
        }
        if(map.get(sourceY).get(sourceX) == 0) {
            return new ArrayList<>();
        }
        int x = sourceX;
        int y = sourceY;
        for (int j = i+1; j>=1; j--) {
            if(y >= 1 && paths.get(y-1).get(x) == j) {
                y--;
                route.add(new Pair<>(x, y));
            }
            else if(y != map.size() -1 && paths.get(y+1).get(x) == j) {
                y++;
                route.add(new Pair<>(x, y));
            }
            else if(x != map.get(0).size() -1 && paths.get(y).get(x+1) == j) {
                x++;
                route.add(new Pair<>(x, y));
            }
            else if(x >= 1 && paths.get(y).get(x-1) == j) {
                x--;
                route.add(new Pair<>(x, y));
            }
        }
        return route;
    }
}
