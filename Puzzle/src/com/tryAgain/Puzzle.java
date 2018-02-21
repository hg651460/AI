/* ******************************************************************
************
Author’s name(s):    Harshal Giradkar
Course Title:       Artificial Intelligence
Semester:           Fall 2017
Assignment Number : 1
Submission Date:    9/27/2017
Purpose:    This program solves the puzzle
Input:      Initial State and Goal State
Output:     Possible moves to achieve goal state
Help:       worked alone.
*********************************************************************
********* */


package com.tryAgain;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.util.*;


public class Puzzle {

    private static final int MAX_DEPTH = 15;

//******************************************************
//*** Purpose: This method calls BFS method
//*** Input: Initial state and goal state
//*** Output: result from breadth first search
//******************************************************
    public String solvePuzzleBFS(int initial_state[], int goal_state[]){

        Node rootNode = new Node(initial_state,"root");
        Node goalNode = new Node(goal_state,"goal");
        return  BFS(goalNode,rootNode);
    }


//******************************************************
//*** Purpose: This method compares goal node with
//*** initial node and its child node
//*** Input: root node
//*** Output: path to the goal node
//******************************************************


    public String BFS(Node goalnode, Node initNode){
        int depth=0;
        if(initNode.equals(goalnode)){
            return  initNode.getPath();
        }

        Queue<Node> queue = new ArrayDeque<Node>();
        queue.add(initNode);

        long startTime = System.currentTimeMillis();

        do {
            Node node = queue.poll();

            if (node.equals(goalnode)) {
                return node.getPath();
            } else {

                if(System.currentTimeMillis() - startTime > 25000)
                {
                    return "Path not available for current input.";
                }

                Node leftNode = node.left();
                if (leftNode != null) {
                    queue.offer(leftNode);
                }
                Node rightNode = node.right();
                if (rightNode != null) {
                    queue.offer(rightNode);
                }

                Node upNode = node.up();
                if (upNode != null) {
                    queue.offer(upNode);
                }

                Node downNode = node.down();
                if (downNode != null) {
                    queue.offer(downNode);
                }
                //depth++;

            }

                depth++;
//            if (!queue.isEmpty() && !node.equals(goalnode) && depth < 50 ){
//                break;
//            }

        }while (!queue.isEmpty() ) ;

        return String.format("Result not found");
    }

//******************************************************
//*** Purpose: This method calls DFS method
//*** Input: Initial state and goal state
//*** Output: result from depth first search
//******************************************************
    public String solvePuzzleDFS(int initial_state[], int goal_state[]){

        Node rootNode = new Node(initial_state,"root");
        Node goalNode = new Node(goal_state,"goal");

        String result=null;

        for(int i=0; i<MAX_DEPTH;i++){
            result = DFS(goalNode,rootNode, i);

            if(result!=null ){
                return  result;
            }

        }
        return String.format("Result not found for depth MAX_DEPTH:%s. Please try again by increasing depth", MAX_DEPTH);

    }


//******************************************************
//*** Purpose: This method compares goal node with
//*** initial node and its child node until the goal is found
//*** Input: goal node and node to be compared
//*** Output: path to goal
//******************************************************

    public String DFS(Node goalnode, Node node, int depth) {

        String result=null;

        if(node==null){
            return  null;
        }

       if(depth==0){
           if(goalnode.equals(node)){
               return node.getPath();
           }
       }else if(depth>0){
           Node leftNode = node.left();
           result = DFS(goalnode, leftNode, depth-1);
           if(result!=null){
               return  result;
           }

           Node rightNode = node.right();
           result = DFS(goalnode, rightNode, depth-1);
           if(result!=null){
               return  result;
           }

           Node upNode = node.up();
           result = DFS(goalnode, upNode, depth-1);
           if(result!=null){
               return  result;
           }

           Node downNode = node.down();
           result = DFS(goalnode, downNode, depth-1);
           if(result!=null){
               return  result;
           }
       }

       return  null;

    }

    public static void main(String[] args) {


  /*      Scanner input = null;
        try {
            input = new Scanner(new FileInputStream(args[1]));

            //input = new Scanner(new FileReader("test.dat"));

        } catch (FileNotFoundException e) {
            //e.printStackTrace();
            System.out.println("File is not found");
            System.exit(0);
        }

        //read each line in the file and split the line content on the basis of space
        String[] firstLine = input.nextLine().split("\\s+");
        String[] secondLine = input.nextLine().split("\\s+");

        int[] initial_state = new int[firstLine.length];
        int[] goal_state = new int[secondLine.length];

        for(int i = 0;i < firstLine.length;i++)
        {
            initial_state[i] = Integer.parseInt(firstLine[i]);
        }

        for(int i = 0;i < secondLine.length;i++)
        {
            goal_state[i] = Integer.parseInt(secondLine[i]);
        }
        System.out.println(Arrays.toString(firstLine));
        System.out.println("done");
        System.out.println(Arrays.toString(initial_state));
        input.close();
*/

        int initial_state[] = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 0};
//        int goal_state[] = {2, 3, 4, 0, 1, 6, 7, 8, 5, 10, 11, 12, 9, 13, 14, 15};
        int goal_state[] = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 0, 12, 13, 14, 15};

        Puzzle solvePuzzle = new Puzzle();
        String result = null;


/*        if (args[0].equals("BFS")) {
            result = solvePuzzle.solvePuzzleBFS(initial_state, goal_state);
        }
        else if (args[0].equals("DFS")){
            result = solvePuzzle.solvePuzzleDFS(initial_state, goal_state);
        }
        else {
        }
        */
     //   String dfsResult = solvePuzzle.solvePuzzleDFS(initial_state, goal_state);
     //   System.out.println("DFS : " + dfsResult);

        String bfsResult = solvePuzzle.solvePuzzleBFS(initial_state, goal_state);
        System.out.println("BFS : " + bfsResult);

       // System.out.println( result);
    }
}
