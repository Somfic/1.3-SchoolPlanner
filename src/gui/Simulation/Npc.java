package gui.simulation;

import data.Person;
import data.Schedule;
import data.map.Map;
import javafx.util.Pair;
import logging.Logger;
import org.dyn4j.geometry.Vector2;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public abstract class Npc {
    Vector2 position = new Vector2(20, 15);
    Vector2 target = new Vector2( 20, 15);

    private final Person person;
    private List<Vector2> route;

    public Npc(Person person) {
        this.person = person;
    }

    public void setPosition(Vector2 position) {
        this.position = position;
    }

    public Vector2 getPosition() {
        return position;
    }

    public Vector2 calculatePositionOnRoute(int iteration, double factor) {
        if(this.route == null || iteration < 0) {
            return this.position;
        }

        if(iteration >= this.route.size()) {
            return this.target;
        }

        if(iteration == this.route.size() - 1) {
            return this.route.get(iteration);
        }

        // Linear interpolation
        Vector2 p1 = this.route.get(iteration);
        Vector2 p2 = this.route.get(iteration + 1);

        double x = p1.x + (p2.x - p1.x) * factor;
        double y = p1.y + (p2.y - p1.y) * factor;

        return new Vector2(x, y);
    }

    abstract void calculateTarget(Schedule schedule, int period, MapInfo mapInfo);

    public void resetTarget() {
        this.target = null;
    }

    public Person getPerson() {
        return person;
    }

    public void calculateRoute(Map map) {
        int[][] obstacles = map.getObstacles();

        // Print the map
        for(int i = 0; i < obstacles.length; i++) {
            for(int j = 0; j < obstacles[i].length; j++) {
                int value = obstacles[i][j];

                if(value == 1) {
                    System.out.print("\t");
                } else {
                    System.out.print(obstacles[i][j] + "\t");
                }
            }
            System.out.println();
        }

        ArrayList<ArrayList<Integer>> obstacleList = new ArrayList<>();
        for (int[] obstacle : obstacles) {
            ArrayList<Integer> row = new ArrayList<>();
            for (int i : obstacle) {
                row.add(i);
            }
            obstacleList.add(row);
        }

        List<Point> route = PathFinder.route(obstacleList, new Point((int) this.position.x, (int) this.position.y), new Point((int) this.target.x, (int) this.target.y));

        // Convert the route to a list of Vector2
        List<Vector2> result = new ArrayList<>();
        for (Point p : route) {
            result.add(new Vector2(p.x, p.y));
        }

        this.route = result;

        Logger.debug("Route calculated for " + this.person.getName() + " from " + this.position + " to " + this.target + ": " + this.route);
    }
}
