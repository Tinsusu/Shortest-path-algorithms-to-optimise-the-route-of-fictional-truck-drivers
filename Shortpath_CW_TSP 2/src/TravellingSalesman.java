import java.util.ArrayList;
import java.util.List;

import static java.lang.System.out;

public class TravellingSalesman {
    //adjacency matrix contains  graph information

    private double[][] graph;
    // we have to track weather we have visited nodes or not
    private boolean[] visitedNode;


    private double minDistance;
    private List<Integer> normalpath;
    private List<Integer> bestpathever;

    public TravellingSalesman(double [][] graph){
        this.graph = graph;
        this.visitedNode = new boolean[graph.length];
        this.normalpath = new ArrayList<>();
        this.bestpathever = new ArrayList<Integer>();
        initialize();

    }
    private void initialize(){
        //start with vertex index0
        visitedNode[0] = true;
        normalpath.add(0);
        minDistance = Double.MAX_VALUE;

    }
    private boolean isValid(int vertex, int positionNow){
        //if the node is already been visited then we can not visit again
        if(visitedNode[vertex])
            return false;
        //is there a connection between 2 Nodes ?
        if(graph[positionNow][vertex]==0)
            return false;
        return true;
    }
    public void findsolution(int positionNow,int counter,double cost){
        //base-case
        //we have considered all the vertices in the G(V,E)
        //AND there is a connection between the last and the first nodes
       if(counter==graph.length && graph[positionNow][0]!=0){
           normalpath.add(0);
           normalpath.forEach(num-> System.out.print(num+" "));
           out.println("\nDistance:"+(cost+graph[positionNow][0])+"\n");

           if(cost+graph[positionNow][0]< minDistance){
               minDistance = cost+graph[positionNow][0];
               bestpathever.addAll(normalpath);
               }


           normalpath.remove(normalpath.size()-1);
           return;
       }
       // we have to consider all the i vertices in the G(V,E) graph
        for(int i=0;i<graph.length;i++){
            if(isValid(i,positionNow)){
                visitedNode[i] = true;
                normalpath.add(i);

                findsolution(i, counter+1,cost+graph[positionNow][i]);

                //Backtracking
               /*
                visited[i]=false;
                path.remove(path.size()-1);
               */

            }
        }
    }
    public void display(){
        System.out.println("Minimum Distance :"+ minDistance);
        System.out.println(bestpathever);

        List<Integer> tail = bestpathever.subList(Math.max(bestpathever.size() - 9, 0), bestpathever.size());
        System.out.println(tail);
    }

}

