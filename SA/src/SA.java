/* ******************************************************************************
Author's name(s): Harshal Giradkar
Course Title: AI
Semester: Fall 2017
Assignment Number:2
Submission Date:10/18/2017
Purpose:  TSP Program using Simulated annealing (SA) 
Input:Local optimal route
Output:Probabilistic search for shortest path
Help:Internet for understanding SA application
*******************************************************************************/

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class SA
    {
       public static void main(String[] args){
        {
           
            double[][] distances = new double[][]{
                {0,20,0,0,7} 
                ,{20,0,8,14,12}
                ,{0,8,0,10,15}
                ,{0,14,10,0,10}
                ,{7,12,15,10,0}};
                
                List<Integer> localOptimal=new ArrayList<Integer>();
                
               /* Setting initial local optimal route given in question */ 	 
                localOptimal.add(0);
                localOptimal.add(1);
                localOptimal.add(2);
                localOptimal.add(3);
                localOptimal.add(4);
                
                tour currentSolution = new tour(localOptimal,compute_tour(localOptimal,distances));
                int iteration = -1;

                double temperature = 10000.0;
                double delta_e = 0;
                double coolingRate = 0.999;
                double absoluteTemperature = 0.00001;

                while (temperature > absoluteTemperature)
                {
               	 /* Copy the current solution to a temp */ 
               	 tour tempSolution = currentSolution;
                
                	  /* Monte Carlo Iterations */ 
                	 for (iteration = 0 ; iteration < 2 ; iteration++) 
                	 { 
                		   tempSolution=perturb_tour(tempSolution,distances); 
                		   /* check tour exists or not*/ 
                		    delta_e = tempSolution.getDistance()-currentSolution.getDistance(); 
               		    
                		   /*check if path Exits or not*/
                		   if(checkPath(tempSolution.getSeries(),distances))
                		   {
              		    	 System.out.println("Tour S		"+currentSolution.getSeries().toString()+"		Tour S' "+tempSolution.getSeries().toString()+"		E(S)="+currentSolution.getDistance()+"		E(S')="+tempSolution.getDistance()+"		Delta E= "+delta_e+"	Valid Path");
 
                			   /* Is the new solution better than the old? */      
	                		    if (delta_e < 0.0) 
	                		    {                   		           
	                		    	/* Accept the new, better, solution */ 
	                              	currentSolution=tempSolution;
	                		    }
	                		    else
	                		    { /* Probabilistically accept a worse solution */ 
	                		    	  if (Math.exp(delta_e/temperature)> Math.random()) 
	                		    	  {
	                		    		  currentSolution=tempSolution;
	                		    	  }
	                		    }
                		    }
                		                   	 
                	 }
                		
                	  temperature *= coolingRate;

                      iteration++;
                }
 
        }
        }
       
       public static tour perturb_tour(tour tempRoute,double[][] distances) {
           
    	   List<Integer> newOrder=new ArrayList<Integer>();
           for (int i = 0; i < tempRoute.getSeries().size(); i++)
           	newOrder.add(tempRoute.getSeries().get(i));
    	   
    	   int firstRandomCityIndex=0,secondRandomCityIndex=0;
           
           //Randomly generating numbers
           do {
           	 firstRandomCityIndex=new Random().nextInt(5);;
         	     secondRandomCityIndex= new Random().nextInt(5); 
           }while(firstRandomCityIndex==secondRandomCityIndex);

           int dummy = newOrder.get(firstRandomCityIndex);
           newOrder.set(firstRandomCityIndex,newOrder.get(secondRandomCityIndex));
           newOrder.set(secondRandomCityIndex,dummy);

           return new tour(newOrder,compute_tour(newOrder,distances));

       } 
       

       private static double compute_tour(List<Integer> order,double[][] distances)
              {
                  double distance = 0;

                  for (int i = 0; i < order.size() - 1; i++)
                  {
                      distance += distances[order.get(i)][order.get(i + 1)];
                  }

                  if (order.size() > 0)
                  {
                      distance += distances[order.get(order.size()-1)][order.get(0)];
                  }

                  return distance;
              }
       
       private static boolean checkPath(List<Integer> newOrder,double[][] distance){
    	   boolean check=true;
    	   int count=0;
    	   while(count<newOrder.size()){
    	   		if(count+1==newOrder.size())
    	   		{
    	   		   if((distance[newOrder.get(count)][newOrder.get(0)])==0)
    	   		   	{
    	   		      check=false;
    	   		      return check;
    	   		   	}
    	   		   else 
    	   		   {
    	   			   return check;
    	   		   }
    	   		 }
    	   	else if(distance[newOrder.get(count)][newOrder.get(count+1)]==0)
    	         {
    	         check=false;
    	         return check;
    	         }
    	   count++;
    	   }
    	   return check;
    	   }

/*Data Structure to represent tour*/
       static class tour{
    	   List<Integer> series=new ArrayList<Integer>();
    	   double distance_sum;
    	   public tour(List<Integer> series,double distance_sum){this.series=series;this.distance_sum=distance_sum;}

    	    public List<Integer> getSeries(){return series;}
    	    
    	    public double getDistance(){return distance_sum;}
    	    
    	    public void setSeries(List<Integer> series){this.series=series;}
    	    
    	    public void setDistance(int distance_sum){this.distance_sum=distance_sum;}

    	   }
       }
   
