package com.company.ai_in_games_1.AStar_search_algorithm;

import java.util.Comparator;

public class NoteComparator implements Comparator<Node> {

    @Override
    public int compare(Node node1, Node node2) {

        if (node1.getF() < node2.getF()) return -1;
        if (node1.getF() > node2.getF()) return +1;
        return 0;
    }
}
