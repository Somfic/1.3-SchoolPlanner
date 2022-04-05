package gui.simulation;

import data.Person;
import data.Schedule;
import data.map.Map;
import javafx.util.Pair;
import org.dyn4j.geometry.Vector2;

import java.awt.*;
import java.util.ArrayList;
import java.util.stream.Collectors;

public abstract class Npc {
    Vector2 position;
    Vector2 target;

    private final Person person;

    public Npc(Person person) {
        this.person = person;
    }

    public void setPosition(Vector2 position) {
        this.position = position;
    }

    public Vector2 getPosition() {
        return position;
    }

    public Vector2 getNextPathPoint(Map map) {
        route(map.getObstacles(), this.position, this.target);
    }

    private ArrayList<Vector2> route(int[][] obstacles, Point position, Point target) {

        ArrayList<Vector2> route = new ArrayList<>();
        int[][] paths = new int[obstacles.length][obstacles[0].length];

        // Return target if we've already arrived
        if (position.x == target.x && position.y == target.y) {
            ArrayList<Vector2> path = new ArrayList<>();
            path.add(new Vector2(position.x, position.y));
            return path;
        }

        // Set everything to 0
//        for (int i = 0; i< obstacles.size(); i++) {
//            for (int j = 0; j<obstacles.get(i).size(); j++) {
//                paths.get(i).set(j, 0);
//            }
//        }

        // Set the target tile to 1
        paths[target.x][target.y] = 1;

        boolean change = false;
        int i = 1;
        while (true) {
            for (int y = 0; y <= obstacles.length - 1; y++) {
                for (int x = 0; x <= obstacles[y].length - 1; x++) {
                    if (paths[y][x] == i) {
                        if (y != 0 && obstacles[y - 1][x] == 1 && paths[y - 1][x] == 0) {
                            paths[y - 1][x] = i + 1;
                            change = true;
                        }

                        if (y != obstacles.length - 1 && obstacles[y + 1][x] == 1 && paths[y + 1][x] == 0) {
                            paths[y + 1][x] = i + 1;
                            change = true;
                        }

                        if (x != 0 && obstacles[y][x - 1] == 1 && paths[y][x - 1] == 0) {
                            paths[y][x - 1] = i + 1;
                            change = true;
                        }

                        if (x != obstacles[y].length - 1 && obstacles[y][x + 1] == 1 && paths[y][x + 1] == 0) {
                            paths[y][x + 1] = i + 1;
                            change = true;
                        }
                    }
                }
            }

            if (!change || paths[position.y][position.x] != 0) {
                break;
            }

            change = false;
            i++;
        }

        if (obstacles[position.y][position.x] == 0) {
            return new ArrayList<>();
        }

        int x = position.x;
        int y = position.y;

        for (int j = i + 1; j >= 1; j--) {
            if (y >= 1 && paths[y - 1][x] == j) {
                y--;
                route.add(new Vector2(x, y));
            } else if (y != obstacles.length - 1 && paths[y + 1][x] == j) {
                y++;
                route.add(new Vector2(x, y));
            } else if (x != obstacles[0].length - 1 && paths[y][x + 1] == j) {
                x++;
                route.add(new Vector2(x, y));
            } else if (x >= 1 && paths[y][x - 1] == j) {
                x--;
                route.add(new Vector2(x, y));
            }
        }
        return route;
    }

    private ArrayList<Pair<Integer, Integer>> route(ArrayList<ArrayList<Integer>> map, int positionX, int positionY, int targetX, int targetY) {

        ArrayList<Pair<Integer, Integer>> route = new ArrayList<>();
        ArrayList<ArrayList<Integer>> paths = (ArrayList<ArrayList<Integer>>) map.stream().map(ArrayList::new).collect(Collectors.toList());

        if (positionX == targetX && positionY == targetY) {
            return new ArrayList<>();
        }
        for (int i = 0; i < map.size(); i++) {
            for (int j = 0; j < map.get(i).size(); j++) {
                paths.get(i).set(j, 0);
            }
        }
        paths.get(targetY).set(targetX, 1);
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

            if (!change || paths.get(positionY).get(positionX) != 0) {
                break;
            }
            change = false;
            i++;
        }
        if (map.get(positionY).get(positionX) == 0) {
            return new ArrayList<>();
        }
        int x = positionX;
        int y = positionY;
        for (int j = i + 1; j >= 1; j--) {
            if (y >= 1 && paths.get(y - 1).get(x) == j) {
                y--;
                route.add(new Pair<>(x, y));
            } else if (y != map.size() - 1 && paths.get(y + 1).get(x) == j) {
                y++;
                route.add(new Pair<>(x, y));
            } else if (x != map.get(0).size() - 1 && paths.get(y).get(x + 1) == j) {
                x++;
                route.add(new Pair<>(x, y));
            } else if (x >= 1 && paths.get(y).get(x - 1) == j) {
                x--;
                route.add(new Pair<>(x, y));
            }
        }
        return route;
    }


    abstract Vector2 calculateTarget(Schedule schedule, int period, MapInfo mapInfo);

    public void resetTarget() {
        this.target = null;
    }
}
