/* ******************************************************************************
Author's name(s): Harshal Giradkar
Course Title: AI
Semester: Fall 2017
Assignment Number:2
Submission Date:10/18/2017
Purpose:  Catch the red ghost using shortest path
Input: Initial ghost position and Initial packman position
Output: Shortest path to ghost using A* algorithm
*******************************************************************************/

import java.awt.*;
import java.util.PriorityQueue;
import java.util.Stack;
import javax.swing.*;

    //***********************************************************************
    public class Maze extends JFrame
    {

        //*** can keep track of visited cell positions here
        static boolean [][] visited;

        //*** the maze itself
        //***    0 means Power Pellet
        //***    1 means wall
        //***    2 means Stripes
        //***    3 means Pirate
        static int [][] mazePlan =
                {
                        {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
                        {1,0,1,0,0,3,0,0,0,0,0,0,0,0,0,0,1,0,1},
                        {1,0,1,0,0,0,1,1,1,1,1,1,1,0,0,0,1,0,1},
                        {1,0,1,0,0,0,0,0,0,1,0,0,0,0,0,0,1,0,1},
                        {1,0,0,0,0,0,0,0,0,1,2,0,0,0,0,0,3,0,1},
                        {1,0,0,0,0,0,0,1,0,0,0,1,0,0,0,0,0,0,1},
                        {1,1,1,0,0,0,0,1,0,0,0,1,0,0,0,0,1,1,1},
                        {1,0,0,0,0,0,0,1,1,1,1,1,0,0,0,0,0,0,1},
                        {1,2,1,1,1,0,0,0,0,3,0,0,0,0,0,0,0,0,1},
                        {1,0,0,0,1,0,0,1,1,1,1,1,0,0,0,0,0,0,1},
                        {1,0,0,0,1,0,0,1,0,0,0,1,0,0,0,0,0,0,1},
                        {1,0,0,0,1,0,0,1,0,0,0,1,0,3,1,1,1,0,1},
                        {1,0,0,0,1,0,0,1,1,1,1,1,0,0,1,0,0,3,1},
                        {1,1,1,0,1,0,0,0,0,0,0,0,0,0,1,0,1,1,1},
                        {1,0,0,3,1,0,0,1,1,1,1,1,2,0,1,0,0,0,1},
                        {1,0,0,0,1,0,0,1,0,0,0,1,0,0,1,0,0,0,1},
                        {1,0,1,1,1,3,0,1,0,0,0,1,0,0,1,0,1,0,1},
                        {1,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1,1,0,1},
                        {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,3,1},
                        {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
                };

        //*** set up the maze wall positions and set all visited states to false
        static MazePanel mp = new MazePanel(mazePlan, visited);

        //*** set up and display main characters' initial maze positions
        static int  ghostX = 15, ghostY = 8;              //** Ghost
        static int  pacmanX = 1, pacmanY = 1;             //*** Pacman

        //*** each maze cell is 37 pixels long and wide
        static int panelWidth = 37;

        //*** a simple random number generator for random search
        static int randomInt(int n) {return (int)(n * Math.random());}

        static PriorityQueue<Node> OPEN= new PriorityQueue<Node>();


        //******************************************************
        //*** main constructor
        //******************************************************
        public Maze()
        {
            //*** display the ghost
            mp.setupChar(ghostX, ghostY, "resrc/ghost.gif");

            //*** display Pacman
            mp.setupChar(pacmanX, pacmanY, "resrc/pacman.gif");

            //*** set up the display panel
            getContentPane().setLayout(new GridLayout());
            setSize(mazePlan[0].length*panelWidth, mazePlan[0].length*panelWidth);
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            getContentPane().add(mp);
        }


        //******************************************************
        //*** a delay routine
        //******************************************************
        public void wait(int milliseconds)
        {
            try
            {Thread.sleep(milliseconds);}
            catch (Exception e)
            {}
        }


        //******************************************************
        //*** move Pacman to position (i, j)
        //******************************************************

        public void movePacman(int i, int j)
        {
            mp.setupChar(i, j, "resrc/pacman.gif");
        }


        //******************************************************
        //*** remove Pacman from position (i, j)
        //******************************************************

        public void removePacman(int i, int j)
        {
            mp.removeChar(i, j);
        }


        //******************************************************
        //*** move ghost to position (i, j)
        //******************************************************
        public void moveGhost(int i , int j)
        {
            mp.setupChar(i, j, "resrc/ghost.gif");
        }


        //******************************************************
        //*** remove ghost from position (i, j)
        //******************************************************
        public void removeGhost(int i , int j)
        {
            mp.removeChar(i, j);
            mp.setupChar(i, j, "resrc/pellet.gif");

        }

        //******************************************************
        //*** is position (i,j) a power-pellet cell?
        //******************************************************
        public boolean openSpace(int i, int j)
        {
            return (mazePlan[i][j] == 0);
        }

        //*********************************************************************
        //***checks if up move is possible for pacman at position (i,j) or not
        //*********************************************************************
        public boolean moveUp(int i, int j)
        {
            return mazePlan[i-1][j] != 1 && mazePlan[i-1][j] != 2 && mazePlan[i-1][j] != 3;
        }

        //*********************************************************************
        //***checks if move down possible for pacman at position (i,j) or not
        //*********************************************************************
        public boolean moveDown(int i, int j)
        {
            return mazePlan[i+1][j] != 1 && mazePlan[i+1][j] != 2 && mazePlan[i+1][j] != 3;
        }

        //***********************************************************************
        //***checks if left move is possible for pacman at position (i,j) or not
        //***********************************************************************
        public boolean moveLeft(int i, int j)
        {
            return mazePlan[i][j-1] != 1 && mazePlan[i][j-1] != 2 && mazePlan[i][j-1] != 3;
        }

        //**************************************************************************
        //*** checks if right move is possible for pacman at position (i,j) or not
        //**************************************************************************
        public boolean moveRight(int i, int j)
        {
            return mazePlan[i][j+1] != 1 && mazePlan[i][j+1] != 2 && mazePlan[i][j+1] != 3;
        }


        //******************************************************
        //***   MODIFY HERE --  MODIFY HERE  --  MODIFY HERE
        //******************************************************
        public static void main(String [] args)
        {

            //*** create a new frame and make it visible
            Maze mz = new Maze();
            mz.setVisible(true);

            MazeHelper mazeHelper = new MazeHelper(visited);

            //take ghost node as end of path
            Node ghostNode = new Node(ghostX, ghostY);
            Node pacMan = new Node(pacmanX, pacmanY);

            //column = mazePlan[0].length
            //row = mazePlan.length
            Stack<Node> packmanPath = mazeHelper.calculatePacmanPath(mz, ghostNode, pacMan, mazePlan.length, mazePlan[0].length);

            Node movePacMan  = new Node(pacmanX, pacmanY);

            mz.removePacman(pacMan.getX(), pacMan.getY());

            //print the path on maze panel from pacman initial node to the ghost node
            do {
//                Node oldLocation =  packmanPath.pop();
                 movePacMan = packmanPath.pop();

                //*** move Pacman to new location (x, y)
                mz.movePacman(movePacMan.getX(), movePacMan.getY());

                //*** delay updating the screen
                //*** change this parameter as you wish
                mz.wait(200);

                //*** remove Pacman from location (x, y)
                mz.removePacman(movePacMan.getX(), movePacMan.getY());

                //move ghost in the maze
                //for static ghost comment this line
                ghostNode = mazeHelper.moveGhost(mz, ghostNode, movePacMan);

                //calculate path from current pacman position to current ghost position
                packmanPath = mazeHelper.calculatePacmanPath(mz, ghostNode, movePacMan, mazePlan[0].length, mazePlan.length);

            }while (!(packmanPath.peek().getX() == ghostNode.getX() && packmanPath.peek().getY() == ghostNode.getY()) && !packmanPath.isEmpty());


            mz.movePacman(ghostNode.getX(), ghostNode.getY());

        } // main

    } // Maze

