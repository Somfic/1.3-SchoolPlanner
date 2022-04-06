package data;

import gui.simulation.Npc;

import java.util.Comparator;

public class NpcSorter implements Comparator<Npc> {
    @Override
    public int compare(Npc npc1, Npc npc2) {
        return Double.compare(npc1.getPosition().y, npc2.getPosition().y);
    }
}
