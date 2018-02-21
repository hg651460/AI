/* ******************************************************************************
Author's name(s): Harshal Giradkar
Course Title: AI
Semester: Fall 2017
Assignment Number:2
Submission Date:10/18/2017
Purpose:  Calculate path from current packman position to current ghost position
Input: Current ghost position and current packman position
Output: Shortest path to ghost using A* algorithm
*******************************************************************************/

import java.util.PriorityQueue;
import java.util.Stack;


public class MazeHelper {

    //*** can keep track of visited cell positions here
    private boolean [][] visited;

    private PriorityQueue<Node> OPEN;


    public MazeHelper(boolean[][] visited) {
        this.visited = visited;

    }

    //methode to move ghost randomly
    Node moveGhost(Maze mz, Node ghostNode, Node packManNode){

        double randDir = (Math.random()*4);

        if(randDir < 1){
            //move ghost down
            if ( mz.moveDown(ghostNode.getX(), ghostNode.getY()) && packManNode.getX()!= ghostNode.getX()+1){
                mz.wait(400);
                mz.removeGhost(ghostNode.getX(),ghostNode.getY());
                ghostNode = new Node(ghostNode.getX()+1 , ghostNode.getY());
                mz.wait(300);
                mz.moveGhost(ghostNode.getX(),ghostNode.getY());

            }
        }else if(randDir <=2 && randDir >=1){
            //move ghost up
            if (mz.moveUp(ghostNode.getX(), ghostNode.getY()) && packManNode.getX()!= ghostNode.getX()-1){
                mz.wait(400);
                mz.removeGhost(ghostNode.getX(),ghostNode.getY());
                ghostNode = new Node(ghostNode.getX()-1 , ghostNode.getY());
                mz.wait(300);
                mz.moveGhost(ghostNode.getX(),ghostNode.getY());

            }

        }
        //Ghost will move to its left
        else if(randDir<=3 && randDir >2){
            if ( mz.moveLeft(ghostNode.getX(), ghostNode.getY()) && packManNode.getY()!= ghostNode.getY()-1){
                mz.wait(400);
                mz.removeGhost(ghostNode.getX(),ghostNode.getY());
                ghostNode = new Node(ghostNode.getX() , ghostNode.getY()-1);
                mz.wait(300);
                mz.moveGhost(ghostNode.getX(),ghostNode.getY());

            }

        }
        //Ghost will move to its right
        else if( randDir<=4 && randDir>3){
            if ( mz.moveRight(ghostNode.getX(), ghostNode.getY()) && packManNode.getY()!= ghostNode.getY()+1){
                mz.wait(400);
                mz.removeGhost(ghostNode.getX(),ghostNode.getY());
                ghostNode = new Node(ghostNode.getX() , ghostNode.getY()+1);
                mz.wait(300);
                mz.moveGhost(ghostNode.getX(),ghostNode.getY());

            }

        }


        return  ghostNode;

    }

    //**************************************************************************
    //*********CHECK cost of visiting immediate (up, down, left, right) nodes
    //**************************************************************************
    void checkCost(Node current, Node temp, int cost){

        //return if temp node already visited
        if(temp == null || visited[temp.getX()][temp.getY()]){
            return;
        }

        //𝑔(𝑛), depth of search space
        temp.setPathCost( current.getPathCost() + 10);

        //𝑓(𝑛) = 𝛼 . 𝑔(𝑛) + 𝛽 . ℎ∗(𝑛)
        int final_cost = 2 * (temp.getPathCost()) + (10 * temp.getHeuristicCost());

        //check if OPEN has temp node
        boolean inOpen = OPEN.contains(temp);

        //if temp node not in OPEN and temp final cost is less than current final cost
        if(!inOpen || final_cost<cost){
            temp.setFinalCost( final_cost);

            //set temp parent
            temp.setParent( current);
            if(!inOpen){
                OPEN.offer(temp);
            }
        }
    }

    Stack<Node> calculatePacmanPath(Maze mz, Node ghostNode , Node pacManNode,  int mazeXLength, int mazeYLength){

        OPEN= new PriorityQueue<Node>();

        //*** Pacman's currentNode board position
        int gbx = pacManNode.getX(), gby = pacManNode.getY();

        //***initially all visited nodes are false
        visited = new boolean[20][20];


        //pacman's initial position
        Node node = new Node(gbx,gby);

        //add the initial position to the OPEN list.
        OPEN.add(node);
        int count = 0;
//

        Node currentNode, tempNode;

        while(true){

            currentNode = OPEN.poll();

            //if current node null then break out of the loop
            if(currentNode==null){
                break;
            }

            //set mazeplane[i][j] visited as true
            visited[currentNode.getX()][currentNode.getY()]=true;

            //if goal node found break out of the loop
            if(currentNode.equals(ghostNode)){
                break;
            }

            //visit adjacent up node from current node
            if(currentNode.getX()-1>0 && mz.moveUp(currentNode.getX(), currentNode.getY()) ){
                tempNode = new Node(currentNode.getX()-1, currentNode.getY());
                checkCost(currentNode, tempNode, currentNode.getFinalCost());

            }

            //visit adjacent left node from current node
            if(currentNode.getY()-1>0 && mz.moveLeft(currentNode.getX(), currentNode.getY()) ){
                tempNode = new Node(currentNode.getX(), currentNode.getY()-1);
                checkCost(currentNode, tempNode, currentNode.getFinalCost());

            }

            //visit adjacent right node from current node
            if(currentNode.getY()+1<mazeYLength && mz.moveRight(currentNode.getX(), currentNode.getY()) ){
                tempNode = new Node(currentNode.getX(), currentNode.getY()+1);
                checkCost(currentNode, tempNode, currentNode.getFinalCost());



            }

            //visit adjacent down node from current node
            if(currentNode.getX()+1<mazeXLength && mz.moveDown(currentNode.getX(), currentNode.getY()) ){
                tempNode = new Node(currentNode.getX()+1, currentNode.getY());
                checkCost(currentNode, tempNode, currentNode.getFinalCost());
            }

        }

        Stack<Node> packmanPath = new Stack<Node>();

        if(visited[ghostNode.getX()][ghostNode.getY()]){

            //push ghost node in the stack
            packmanPath.push(ghostNode);


            Node current = currentNode;

            //Trace the path from ghost node to initial node
            while(current.getParent()!=null){
                packmanPath.push(current.getParent());
                current = current.getParent();
            }

            // remove the current location from the path
            packmanPath.pop();
        }



        return  packmanPath;

    }
}
