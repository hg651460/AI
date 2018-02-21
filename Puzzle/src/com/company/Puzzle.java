package com.company;

import java.util.LinkedList;
import java.util.Stack;

public class Puzzle {

   // int initial_state[] = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,0};
   // int goal_state[] = {2,3,4,0,1,6,7,8,5,10,11,12,9,13,14,15};

    public int[] solvePuzzle(int initial_state[], int goal_state[]){

        int result[] = new int[16];
        Stack stack = new Stack();
        ///
        State initialState = new State(initial_state);
        State goalState = new State(goal_state);

        //init the tree
        initialState.formTheTree(initialState,initialState);

        //find the end state
       String r1 =  initialState.findTheNode(initialState, goalState, stack);

        System.out.println(r1);


        return result;
    }





    public static void main(String[] args) {
	// write your code here

        int initial_state[] = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,0};
        int goal_state[] = {2,3,4,0,1,6,7,8,5,10,11,12,9,13,14,15};

        Puzzle solvePuzzle = new Puzzle();

        solvePuzzle.solvePuzzle(initial_state, goal_state);
    }
}
