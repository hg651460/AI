/* ******************************************************************************
Author's name(s): Harshal Giradkar
Course Title: AI
Semester: Fall 2017
Assignment Number:2
Submission Date:10/18/2017
Purpose: Class to keep track of heuristic cost and path cost for visiting each index in maze
Input: Position x, Position y
Output: stores path for each position calculated using A* algorithm
*******************************************************************************/

public class Node implements  Comparable{
    private int x; //x
    private int y; //y
    private int heuristicCost; //Heuristic cost ℎ∗(𝑛, ghost) distance in terms of pacman out of place
    private int pathCost = 0;//𝑔(𝑛), depth of search space
    private int finalCost;   //𝑓(𝑛) = 𝛼 . 𝑔(𝑛) + 𝛽 . ℎ∗(𝑛)
    private Node parent;

    Node(int i, int j){
        this.x = i;
        this.y = j;

    }


    @Override
    public String toString(){
        return "["+this.x +", "+this.y +"]";
    }


    //equals method to compare indexes
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Node)) return false;

        Node node = (Node) o;

        if (x != node.x) return false;
        return y == node.y;

    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        return result;
    }

    @Override
    public int compareTo(Object o) {

        if (o==null){
            return -1;
        }

        if(this.equals(o)){
            return  0;
        }else{
            return this.pathCost - ((Node)o).pathCost;
        }
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }



    public int getHeuristicCost() {
        return heuristicCost;
    }

    public void setHeuristicCost( int ghostX, int ghostY) {
        this.heuristicCost = (ghostX - x) + (ghostY - y); //Heuristic cost ℎ∗(𝑛, ghost) distance in terms of pacman out of place
    }

    public int getPathCost() {
        return pathCost;
    }

    public void setPathCost(int pathCost) {
        this.pathCost = pathCost;
    }

    public int getFinalCost() {
        return (2 * pathCost) + (10 * heuristicCost);
    }

    public void setFinalCost(int finalCost) {

        this.finalCost= finalCost;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }
}//Node