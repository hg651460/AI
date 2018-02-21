/* ******************************************************************
************
Author’s name(s):    Harshal Giradkar
Course Title:       Artificial Intelligence
Semester:           Fall 2017
Assignment Number : 1
Submission Date:    9/27/2017
Purpose:    This program keeps track of possible node states
Input:      Node State
Output:     Node's left state, right state, up state, down state
Help:       worked alone.
*********************************************************************
********* */


package com.tryAgain;

import java.util.Arrays;

/**
 * Created by harsh on 9/25/2017.
 */
public class Node {

    int[] state;

    private Node left;
    private Node right;
    private Node up;
    private Node down;

    private boolean isVisited;
    private String path;


    public Node(int[] state, String path) {
        this.state = state;
        this.path = path;
    }


//******************************************************
//*** Purpose: This method slides 0 to left
//*** Input: node state
//*** Output: new state with 0 moved left
//******************************************************
    public Node left() {
        int[] tempState = Arrays.copyOf(this.state, this.state.length);

        for (int i = 0; i < this.state.length; i++) {
            if (this.state[i] == 0) {
                if (!(i == 0 || i == 4 || i == 8 || i == 12)) {
                    int temp = tempState[i - 1];
                    tempState[i - 1] = tempState[i];
                    tempState[i] = temp;
                    Node leftNode = new Node(tempState, this.path + "->L");
                    return leftNode;
                }

            }
        }
        return null;
    }


//******************************************************
//*** Purpose: This method slides 0 to right
//*** Input: node state
//*** Output: new state with 0 moved right
//******************************************************
    public Node right() {
        int[] tempState = Arrays.copyOf(this.state, this.state.length);

        for (int i = 0; i < this.state.length; i++) {
            if (this.state[i] == 0) {
                if (!(i == 3 || i == 7 || i == 11 || i == 15)) {
                    int temp = tempState[i + 1];
                    tempState[i + 1] = tempState[i];
                    tempState[i] = temp;
                    Node tempNode = new Node(tempState, this.path + "->R");
                    return tempNode;
                }

            }
        }
        return null;
    }


//******************************************************
//*** Purpose: This method slides 0 up
//*** Input: node state
//*** Output: new state with 0 moved up
//******************************************************
    public Node up() {
        int[] tempState = Arrays.copyOf(this.state, this.state.length);

        for (int i = 0; i < this.state.length; i++) {
            if (this.state[i] == 0) {
                if (!(i == 0 || i == 1 || i == 2 || i == 3)) {
                    int temp = tempState[i - 4];
                    tempState[i - 4] = tempState[i];
                    tempState[i] = temp;
                    Node leftNode = new Node(tempState, this.path + "->U");
                    return leftNode;
                }

            }
        }
        return null;
    }


//******************************************************
//*** Purpose: This method slides 0 down
//*** Input: node state
//*** Output: new state with 0 moved down
//******************************************************
    public Node down() {
        int[] tempState = Arrays.copyOf(this.state, this.state.length);

        for (int i = 0; i < this.state.length; i++) {
            if (this.state[i] == 0) {
                if (!(i == 12 || i == 13 || i == 14 || i == 15)) {
                    int temp = tempState[i + 4];
                    tempState[i + 4] = tempState[i];
                    tempState[i] = temp;
                    Node leftNode = new Node(tempState, this.path + "->L");
                    return leftNode;
                }

            }
        }
        return null;
    }


    public int[] getState() {
        return this.state;
    }

    public void setState(int[] state) {
        this.state = state;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    public Node getUp() {
        return up;
    }

    public void setUp(Node up) {
        this.up = up;
    }

    public Node getDown() {
        return down;
    }

    public void setDown(Node down) {
        this.down = down;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public boolean isVisited() {
        return isVisited;
    }

    public void setVisited(boolean visited) {
        isVisited = visited;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Node)) return false;

        Node node = (Node) o;

        return Arrays.equals(getState(), node.getState());

    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(getState());
    }

    @Override
    public String toString() {
        return "Node{" +
                "state=" + Arrays.toString(state) +
                '}';
    }
}
