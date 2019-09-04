package com.company.ai_in_games_1.AStar_search_algorithm;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class AStarAlgorithm {

    private Node[][] searchSpace; // Grid of Nodes
    private Node startNode; // Start Point
    private Node finalNode; // Final Point

    private List<Node> closedSet; // Already evaluated
    private Queue<Node> openSet; // Not yest evaluated

    public AStarAlgorithm() {
        this.searchSpace = new Node[Constants.NUM_ROWS][Constants.NUM_COLS];
        this.openSet = new PriorityQueue<>(new NoteComparator());
        this.closedSet = new ArrayList<>();
        initializeSearchSpace();
    }

    public void initializeSearchSpace() {
        for (int rowIndex = 0; rowIndex < Constants.NUM_ROWS; rowIndex++) {
            for (int colIndex = 0; colIndex < Constants.NUM_COLS; colIndex++) {
                Node node = new Node(rowIndex, colIndex);
                this.searchSpace[rowIndex][colIndex] = node;
            }
        }

        // Set obstacles or blocks
        this.searchSpace[1][7].setBlock(true);
        this.searchSpace[2][3].setBlock(true);
        this.searchSpace[2][4].setBlock(true);
        this.searchSpace[2][5].setBlock(true);
        this.searchSpace[2][6].setBlock(true);
        this.searchSpace[2][7].setBlock(true);

        // Start and final Nodes
        this.startNode = this.searchSpace[3][3];
        this.finalNode = this.searchSpace[1][6];

    }

    public void search() {

        // start with the node
        startNode.setH(manhattanHeuristic(startNode, finalNode));
        openSet.add(startNode);

        // The algorithm terminate when there is no item left in the open
        while (!openSet.isEmpty()) {

            // poll: returns the node with the smallest f=g+h value
            Node currentNode = openSet.poll();
            System.out.println(currentNode + " Predecessor is: " + currentNode.getPredecessor());

            // if we find the terminal state we`ve done
            if (currentNode.equals(finalNode)) return;

            // Update sets
            openSet.remove(currentNode);
            closedSet.add(currentNode);

            // Visit all neighbours actual Node
            for (Node neighbour: getAllNeighbors(currentNode)) {

                // We already consider this case
                if (closedSet.contains(neighbour)) continue;

                // We consider that state so done with that one
                if (!openSet.contains(neighbour)) openSet.add(neighbour);

                // Set the predecessor to be able to track the shortest path
                neighbour.setPredecessor(currentNode) ;
            }
        }
    }

    private List<Node> getAllNeighbors(Node node) {

        List<Node> neighbours = new ArrayList<>();
        int row = node.getRowIndex();
        int col = node.getColIndex();

        // Store the neighbours in list
        // NOTE: Every Node cna have only 4 neighbours

        // Block above
        if (row - 1 >= 0 && !this.searchSpace[row - 1][col].isBlock()) {
            searchSpace[row - 1][col].setG(node.getG() + Constants.HORIZONTAL_VERTICAL_COST);
            searchSpace[row - 1][col].setH(manhattanHeuristic(searchSpace[row - 1][col], finalNode));
            neighbours.add(this.searchSpace[row - 1][col]);
        }

        // Block below
        if (row + 1 < Constants.NUM_ROWS && !this.searchSpace[row + 1][col].isBlock()) {
            searchSpace[row + 1][col].setG(node.getG() + Constants.HORIZONTAL_VERTICAL_COST);
            searchSpace[row + 1][col].setH(manhattanHeuristic(searchSpace[row + 1][col], finalNode));
            neighbours.add(this.searchSpace[row + 1][col]);
        }

        // Block to the left
        if (col - 1 >= 0 && !this.searchSpace[row][col - 1].isBlock()) {
            searchSpace[row][col - 1].setG(node.getG() + Constants.HORIZONTAL_VERTICAL_COST);
            searchSpace[row][col - 1].setH(manhattanHeuristic(searchSpace[row][col - 1], finalNode));
            neighbours.add(this.searchSpace[row][col -1]);
        }

        // Block to the right
        if (col + 1 < Constants.NUM_COLS && !this.searchSpace[row][col + 1].isBlock()) {
            searchSpace[row][col + 1].setG(node.getG() + Constants.HORIZONTAL_VERTICAL_COST);
            searchSpace[row][col + 1].setH(manhattanHeuristic(searchSpace[row][col + 1], finalNode));
            neighbours.add(this.searchSpace[row][col + 1]);
        }

        return neighbours;
    }

    // manhattan distance
    private int manhattanHeuristic(Node startNode, Node finalNode) {
        return (Math.abs(startNode.getRowIndex() - finalNode.getRowIndex()) +
                Math.abs(startNode.getColIndex() - finalNode.getColIndex()));
    }

    public void showPath() {
        System.out.println("SHORTEST PATH WITH A* SEARCH:");

        Node node = this.finalNode;

        while (node != null) {
            System.out.println(node);
            node = node.getPredecessor();
        }
    }
}















