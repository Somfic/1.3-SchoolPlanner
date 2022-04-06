package gui.simulation;

import javafx.util.Pair;
import logging.Logger;
import org.dyn4j.geometry.Vector2;

import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;


public class PathFinder {
    public static ArrayList<Point> route(ArrayList<ArrayList<Integer>> map, Point position, Point target) {
        ArrayList<Point> route = new ArrayList<>();
        ArrayList<ArrayList<Integer>> paths = (ArrayList<ArrayList<Integer>>) map.stream().map(ArrayList::new).collect(Collectors.toList());

        if (position.x == target.x && position.y == target.y) {
            ArrayList<Point> a = new ArrayList<>();
            a.add(position);
            return a;
        }
        for (int i = 0; i < map.size(); i++) {
            for (int j = 0; j < map.get(i).size(); j++) {
                paths.get(i).set(j, 0);
            }
        }
        paths.get(target.y).set(target.x, 1);
        boolean change = false;
        int i = 1;
        while (true) {
            for (int y = 0; y <= map.size() - 1; y++) {
                for (int x = 0; x <= map.get(y).size() - 1; x++) {
                    if (paths.get(y).get(x) == i) {
                        if (y != 0 && map.get(y - 1).get(x) == 1 && paths.get(y - 1).get(x) == 0) {
                            paths.get(y - 1).set(x, i + 1);
                            change = true;
                        }

                        if (y != map.size() - 1 && map.get(y + 1).get(x) == 1 && paths.get(y + 1).get(x) == 0) {
                            paths.get(y + 1).set(x, i + 1);
                            change = true;
                        }
                        if (x != 0 && map.get(y).get(x - 1) == 1 && paths.get(y).get(x - 1) == 0) {
                            paths.get(y).set(x - 1, i + 1);
                            change = true;
                        }
                        if (x != map.get(y).size() - 1 && map.get(y).get(x + 1) == 1 && paths.get(y).get(x + 1) == 0) {
                            paths.get(y).set(x + 1, i + 1);
                            change = true;
                        }
                    }
                }
            }

            if (!change || paths.get(position.y).get(position.x) != 0) {
                break;
            }
            change = false;
            i++;
        }
        if (map.get(position.y).get(position.x) == 0) {
            ArrayList<Point> a = new ArrayList<>();
            a.add(position);
            return a;
        }
        int x = position.x;
        int y = position.y;
        route.add(position);
        for (int j = i + 1; j >= 1; j--) {
            if (y >= 1 && paths.get(y - 1).get(x) == j) {
                y--;
                route.add(new Point(x, y));
            } else if (y != map.size() - 1 && paths.get(y + 1).get(x) == j) {
                y++;
                route.add(new Point(x, y));
            } else if (x != map.get(0).size() - 1 && paths.get(y).get(x + 1) == j) {
                x++;
                route.add(new Point(x, y));
            } else if (x >= 1 && paths.get(y).get(x - 1) == j) {
                x--;
                route.add(new Point(x, y));
            }
        }
        return route;
    }
}