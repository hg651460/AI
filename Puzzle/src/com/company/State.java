
package com.company;

import java.util.Arrays;
import java.util.Stack;

/**
 * Created by harsh on 9/24/2017.
 */
public class State {

    // {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,0}

    private int[] currentState;
    private int[] tempState;

    private State parent;

    private State up;
    private State down;
    private State left;
    private State right;


    //const
    public State(int[] currentState) {
        this.currentState = currentState;
        this.tempState = currentState;
    }

    public int[] getCurrentState() {
        return currentState;
    }

    public void setCurrentState(int[] currentState) {
        this.currentState = currentState;
    }


    public void formTheTree(State node, State rootnode){
        if(node!=null) {
            node.left = left(node,rootnode);
            node.right = right(node,rootnode);
            node.up = up(node,rootnode);
            node.down = down(node,rootnode);
            if (node.left != null) {
                formTheTree(node.left, rootnode);
            }

            if (node.right != null) {
                formTheTree(node.right, rootnode);
            }
            if (node.up != null) {
                formTheTree(node.up, rootnode);
            }
            if (node.down != null) {
                formTheTree(node.down, rootnode);
            }
        }
    }


    public String findTheNode(State node, State goalState, Stack stack)
    {
        String path = "";
        //formTheTree(node);
        if(node==null){
            return "";
        }

        if (node.equals(goalState)){
                    //print path
            return path;
        }else if (node.getLeft()!=null && node.getLeft().equals(goalState)){
           // stack.push("L");
            path = path + "L ";
        }else if (node.getRight()!=null && node.getRight().equals(goalState)){
          //  stack.push("R");
            path = path + "R ";
        }else if (node.getUp()!=null && node.getUp().equals(goalState)){
           // stack.push("U");
            path = path + "U ";
        }else if (node.getDown()!=null && node.getDown().equals(goalState)){
          //  stack.push("D");
            path = path + "D ";
        }
        else
        {
            if(node.getLeft()!=null) {
                findTheNode(node.getLeft(), goalState, stack);
            }
            if(node.getRight()!=null) {
                findTheNode(node.getRight(), goalState, stack);
            }
            if(node.getUp()!=null) {
                findTheNode(node.getUp(), goalState, stack);
            }
            if(node.getDown()!=null) {
                findTheNode(node.getDown(), goalState, stack);
            }
        }

        return path;
    }


    private boolean isDuplicate(State travelNode, State toBeChecked ){
        boolean result =false;

        if(travelNode.equals(toBeChecked)){
            return true;
        }

        if(travelNode.getLeft()!=null){
            boolean r1= isDuplicate(travelNode.getLeft(), toBeChecked);
             result = result || r1;
            if(result){
                return result;
            }
        }

        if(travelNode.getDown()!=null){
            result  = result || isDuplicate(travelNode.getDown(), toBeChecked);

            if(result){
                return result;
            }
        }

        if(travelNode.getRight()!=null){
            result  = result || isDuplicate(travelNode.getRight(), toBeChecked);

            if(result){
                return result;
            }
        }

        if(travelNode.getUp()!=null){
            result  = result || isDuplicate(travelNode.getUp(), toBeChecked);

            if(result){
                return result;
            }
        }

        return result;
    }


    public State left(State node,State rootNode){
        if(node==null){
            return null;
        }

        node.tempState = Arrays.copyOf(node.currentState, node.currentState.length);;

        for(int i=0;i<node.currentState.length;i++){
            if(node.currentState[i]==0){

                if(i==0||i==4||i==8||i==12){
                    System.out.println("left invalid operataion");
                    return null;
                }else {

                    int temp = node.tempState[i - 1];
                    node.tempState[i - 1] = node.tempState[i];
                    node.tempState[i] = temp;
                    break;
                }
            }
        }
        State newState= new State(node.tempState);
        newState.parent = node;

        if(newState.isDuplicate(rootNode, newState)){
           return null;
        }

        return newState;
    }

    public State right(State node,State rootNode){
        if(node==null){
            return null;
        }

        node.tempState = Arrays.copyOf(node.currentState, node.currentState.length);;
        for(int i=0;i<node.currentState.length;i++) {
            if (node.currentState[i] == 0) {
                if (i == 3 || i == 7 || i == 11 || i == 15) {
                    System.out.println("right invalid operataion");
                    return null;
                } else {


                    int temp = node.tempState[i + 1];
                    node.tempState[i + 1] = node.tempState[i];
                    node.tempState[i] = temp;
                    break;
                }
            }
        }
        State newState= new State(node.tempState);
        newState.parent = node;

        if(newState.isDuplicate(rootNode, newState)){
            return null;
        }
        return newState;
    }

    public State up(State node,State rootNode){
        if(node==null){
            return null;
        }

        node.tempState = Arrays.copyOf(node.currentState, node.currentState.length);;
        for(int i=0;i<node.currentState.length;i++) {
            if (node.currentState[i] == 0) {
                if (i == 0 || i == 1 || i == 2 || i == 3) {
                    System.out.println("up invalid operataion");
                    return null;
                } else {

                    int temp = node.tempState[i - 4];
                    node.tempState[i - 4] = node.tempState[i];
                    node.tempState[i] = temp;
                    break;
                }
            }
        }
        State newState= new State(node.tempState);
        newState.parent = node;
        if(newState.isDuplicate(rootNode, newState)){
            return null;
        }
        return newState;
    }

    public State down(State node,State rootNode){
        if(node==null){
            return null;
        }

        node.tempState = Arrays.copyOf(node.currentState, node.currentState.length);;

        for(int i=0;i<node.currentState.length;i++) {
            if (node.currentState[i] == 0) {
                if (i == 12 || i == 13 || i == 14 || i == 15) {
                    System.out.println("down invalid operataion");
                    return null;
                } else {

                    int temp = node.tempState[i + 4];
                    node.tempState[i + 4] = node.tempState[i];
                    node.tempState[i] = temp;
                    break;
                }
            }
        }
        State newState= new State(node.tempState);
        newState.parent = node;
        if(newState.isDuplicate(rootNode, newState)){
            return null;
        }
        return newState;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof State)) return false;

        State state = (State) o;

        return Arrays.equals(getCurrentState(), state.getCurrentState());

    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(getCurrentState());
    }

    public State getUp() {
        return up;
    }

    public State getDown() {
        return down;
    }

    public State getLeft() {
        return left;
    }

    public State getRight() {
        return right;
    }

    @Override
    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(currentState[0]+" "+currentState[1]+ " "+ currentState[2]+" "+ currentState[3]+"\n ");
        stringBuffer.append(currentState[4]+" "+currentState[5]+ " "+ currentState[6]+" "+ currentState[7]+"\n ");
        stringBuffer.append(currentState[8]+" "+currentState[9]+ " "+ currentState[10]+" "+ currentState[11]+"\n ");
        stringBuffer.append(currentState[12]+" "+currentState[13]+ " "+ currentState[14]+" "+ currentState[15]+"\n ");

        return  stringBuffer.toString();
    }
}
