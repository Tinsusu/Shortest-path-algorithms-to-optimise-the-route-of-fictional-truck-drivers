import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args)throws InterruptedException {

        long startTime = System.currentTimeMillis();
      //  TimeUnit.SECONDS.sleep(5); graph that read from file
      //  Graph graph = new Graph();
       Graph graph = readFile();

     //Run Clarke and Wright
       new Algorithm(graph);

     //   Run TSP
        /*
        double[][] graphTsp = graphTsp();
        TravellingSalesman TSP = new TravellingSalesman(graphTsp);
        TSP.findsolution(0,1,0);
        TSP.display();
*/
        long endTime = System.currentTimeMillis();
        long timeElapsed = endTime-startTime;
        System.out.println("Execution time in millisecond : "+timeElapsed);
    }

    public static double[][]graphTsp() {

        Graph graph = readFile();
        double[][]graphTsp = new double[graph.getVertices().size()][graph.getVertices().size()];
        for (int i =0; i < graph.getVertices().size(); i++) {
            for (int j =0; j < graph.getVertices().size(); j++) {
              graphTsp[i][j]=  graph.measuredistance(graph.getVertices().get(i),graph.getVertices().get(j));

            }
        }
        return graphTsp;
    }



    private static double capacity=35; // <<<<<=== key in truck's capacity here
    public static Graph readFile() {
        Graph graph = new Graph();

     // Data path
        File myFile = new File("Data/trydata.txt"); // <<=== Select the data and change at pathname
        Scanner myReader = null;
        try {
            myReader = new Scanner(myFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        while (myReader.hasNextLine()) {
            String data = myReader.nextLine();
            String[] line = data.split(" ");
            Vertex v = new Vertex(Integer.parseInt(line[0]), Double.parseDouble(line[1]), Double.parseDouble(line[2]), Double.parseDouble(line[3]));
            graph.addVertex(v);
        }
        graph.setDepot(graph.getVertices().get(0));
         //   for (Vertex i : graph.getVertices())
           //     for (Vertex j : graph.getVertices()) {

        // i Start from 1 we do not count depot

        for(int i=1; i<graph.getVertices().size();i++){
            for(int j=i+1;j<graph.getVertices().size();j++){
                Vertex vi = graph.getVertices().get(i);
                Vertex vj = graph.getVertices().get(j);

                System.out.println("Print all vertext  " +vi.getId() + " (" + vi.getX() +","+vi.getY()+")");
                           // for (SavingPairs o : graph.getSavingPairsList()) {
                           //     if(graph.getSavingPairsList().contains(o)&& (o.getStartVertex().equals(i) && o.getTargetVertex().equals(j) && o.getWeight() == graph.calsaving(i, j))) {
                          //          graph.getSavingPairsList().remove(o);
                                //    if(!graph.getSavingPairsList().contains(new SavingPairs(vi,vj,graph.calsaving(vi,vj))))
                //check demand
                System.out.println(vi.getDemand()+"+"+vj.getDemand()+"<="+capacity);
                                if((vi.getDemand()+vj.getDemand())<=capacity)
                                    graph.addSavingPairs(new SavingPairs(vi, vj, graph.calsaving(vi, vj)));
                                else System.out.println("The demand greater than capa city ("+vi.getId()+","+vj.getId()+")");
                               // }
                            }
                           // System.out.println("Print all vertext "+  +"to Vertex "+ graph.getVertices().get(j));
                        }
        return graph;
                }


}



/* Test UCberkly DATA


         Vertex vertex0  = new Vertex(0,0,0,0);
        graph.addVertex(vertex0);
        Vertex vertex1  = new Vertex(1,4,0,12);
        graph.addVertex(vertex1);
        Vertex vertex2  = new Vertex(2,4,0,12);
        graph.addVertex(vertex2);

        Vertex vertex3  = new Vertex(3,2.83,0,6);
        graph.addVertex(vertex3);
        Vertex vertex4  = new Vertex(4,4,0,16);
        graph.addVertex(vertex4);
        Vertex vertex5  = new Vertex(5,5,0,15);
        graph.addVertex(vertex5);
        Vertex vertex6  = new Vertex(6,2,0,10);
        graph.addVertex(vertex6);
        Vertex vertex7  = new Vertex(7,4.24,0,8);
        graph.addVertex(vertex7);

        graph.addSavingPairs(new SavingPairs(vertex0,vertex1,4));
        graph.addSavingPairs(new SavingPairs(vertex0,vertex2,4));
        graph.addSavingPairs(new SavingPairs(vertex0,vertex3,2.83));
        graph.addSavingPairs(new SavingPairs(vertex0,vertex4,4));
        graph.addSavingPairs(new SavingPairs(vertex0,vertex5,5));
        graph.addSavingPairs(new SavingPairs(vertex0,vertex6,2));
        graph.addSavingPairs(new SavingPairs(vertex0,vertex7,4.24));

        graph.addSavingPairs(new SavingPairs(vertex1,vertex2,5.66));
        graph.addSavingPairs(new SavingPairs(vertex1,vertex3,6.32));
        graph.addSavingPairs(new SavingPairs(vertex1,vertex4,8));
        graph.addSavingPairs(new SavingPairs(vertex1,vertex5,8.54));
        graph.addSavingPairs(new SavingPairs(vertex1,vertex6,4.47));
        graph.addSavingPairs(new SavingPairs(vertex1,vertex7,3.16));

        graph.addSavingPairs(new SavingPairs(vertex2,vertex3,2.83));
        graph.addSavingPairs(new SavingPairs(vertex2,vertex4,5.66));
        graph.addSavingPairs(new SavingPairs(vertex2,vertex5,8.06));
        graph.addSavingPairs(new SavingPairs(vertex2,vertex6,6));
        graph.addSavingPairs(new SavingPairs(vertex2,vertex7,7.62));

        graph.addSavingPairs(new SavingPairs(vertex3,vertex4,2.83));
        graph.addSavingPairs(new SavingPairs(vertex3,vertex5,5.39));
        graph.addSavingPairs(new SavingPairs(vertex3,vertex6,4.47));
        graph.addSavingPairs(new SavingPairs(vertex3,vertex7,7.07));

        graph.addSavingPairs(new SavingPairs(vertex4,vertex5,3));
        graph.addSavingPairs(new SavingPairs(vertex4,vertex6,4.47));
        graph.addSavingPairs(new SavingPairs(vertex4,vertex7,7.62));

        graph.addSavingPairs(new SavingPairs(vertex5,vertex6,4.12));
        graph.addSavingPairs(new SavingPairs(vertex5,vertex7,7));

        graph.addSavingPairs(new SavingPairs(vertex6,vertex7,3.16));

 */

/*

Graph graph = new Graph();


        Vertex vertex1  = new Vertex(1,0,0,0);
        graph.addVertex(vertex1);
        Vertex vertex2  = new Vertex(2,25,0,4);
        graph.addVertex(vertex2);

        Vertex vertex3  = new Vertex(3,43,0,6);
        graph.addVertex(vertex3);
        Vertex vertex4  = new Vertex(4,57,0,5);
        graph.addVertex(vertex4);
        Vertex vertex5  = new Vertex(5,43,0,4);
        graph.addVertex(vertex5);
        Vertex vertex6  = new Vertex(6,61,0,7);
        graph.addVertex(vertex6);
        Vertex vertex7  = new Vertex(7,29,0,3);
        graph.addVertex(vertex7);
        Vertex vertex8  = new Vertex(8,42,0,5);
        graph.addVertex(vertex8);
        Vertex vertex9  = new Vertex(9,48,0,4);
        graph.addVertex(vertex9);
        Vertex vertex10  = new Vertex(10,71,0,4);
        graph.addVertex(vertex10);


        graph.addSavingPairs(new SavingPairs(vertex1,vertex2,25));
        graph.addSavingPairs(new SavingPairs(vertex1,vertex3,43));
        graph.addSavingPairs(new SavingPairs(vertex1,vertex4,57));
        graph.addSavingPairs(new SavingPairs(vertex1,vertex5,43));
        graph.addSavingPairs(new SavingPairs(vertex1,vertex6,61));
        graph.addSavingPairs(new SavingPairs(vertex1,vertex7,29));
        graph.addSavingPairs(new SavingPairs(vertex1,vertex8,41));
        graph.addSavingPairs(new SavingPairs(vertex1,vertex9,48));
        graph.addSavingPairs(new SavingPairs(vertex1,vertex10,71));

        graph.addSavingPairs(new SavingPairs(vertex2,vertex3,29));
        graph.addSavingPairs(new SavingPairs(vertex2,vertex4,34));
        graph.addSavingPairs(new SavingPairs(vertex2,vertex5,43));
        graph.addSavingPairs(new SavingPairs(vertex2,vertex6,68));
        graph.addSavingPairs(new SavingPairs(vertex2,vertex7,49));
        graph.addSavingPairs(new SavingPairs(vertex2,vertex8,66));
        graph.addSavingPairs(new SavingPairs(vertex2,vertex9,72));
        graph.addSavingPairs(new SavingPairs(vertex2,vertex10,91));

        graph.addSavingPairs(new SavingPairs(vertex3,vertex4,52));
        graph.addSavingPairs(new SavingPairs(vertex3,vertex5,72));
        graph.addSavingPairs(new SavingPairs(vertex3,vertex6,96));
        graph.addSavingPairs(new SavingPairs(vertex3,vertex7,72));
        graph.addSavingPairs(new SavingPairs(vertex3,vertex8,81));
        graph.addSavingPairs(new SavingPairs(vertex3,vertex9,89));
        graph.addSavingPairs(new SavingPairs(vertex3,vertex10,114));

        graph.addSavingPairs(new SavingPairs(vertex4,vertex5,45));
        graph.addSavingPairs(new SavingPairs(vertex4,vertex6,71));
        graph.addSavingPairs(new SavingPairs(vertex4,vertex7,71));
        graph.addSavingPairs(new SavingPairs(vertex4,vertex8,95));
        graph.addSavingPairs(new SavingPairs(vertex4,vertex9,99));
        graph.addSavingPairs(new SavingPairs(vertex4,vertex10,108));

        graph.addSavingPairs(new SavingPairs(vertex5,vertex6,27));
        graph.addSavingPairs(new SavingPairs(vertex5,vertex7,36));
        graph.addSavingPairs(new SavingPairs(vertex5,vertex8,65));
        graph.addSavingPairs(new SavingPairs(vertex5,vertex9,65));
        graph.addSavingPairs(new SavingPairs(vertex5,vertex10,65));

        graph.addSavingPairs(new SavingPairs(vertex6,vertex7,40));
        graph.addSavingPairs(new SavingPairs(vertex6,vertex8,66));
        graph.addSavingPairs(new SavingPairs(vertex6,vertex9,62));
        graph.addSavingPairs(new SavingPairs(vertex6,vertex10,46));

        graph.addSavingPairs(new SavingPairs(vertex7,vertex8,31));
        graph.addSavingPairs(new SavingPairs(vertex7,vertex9,31));
        graph.addSavingPairs(new SavingPairs(vertex7,vertex10,43));

        graph.addSavingPairs(new SavingPairs(vertex8,vertex9,11));
        graph.addSavingPairs(new SavingPairs(vertex8,vertex10,46));

        graph.addSavingPairs(new SavingPairs(vertex9,vertex10,36));






/*
        graph.addSavingPairs(new SavingPairs(vertex0,vertex1,graph.measuredistance(vertex0,vertex1)));
        graph.addSavingPairs(new SavingPairs(vertex0,vertex2,graph.measuredistance(vertex0,vertex2)));
        graph.addSavingPairs(new SavingPairs(vertex0,vertex3,graph.measuredistance(vertex0,vertex3)));
        System.out.println(graph.measuredistance(vertex0,vertex1));
        System.out.println(graph.measuredistance(vertex0,vertex3));
        System.out.println(graph.measuredistance(vertex1,vertex3));
        System.out.println(graph.calsaving(vertex1,vertex3));

     //add the saving pairs into the graph.
 */
/*
        graph.addSavingPairs(new SavingPairs(vertex0,vertex1,graph.calsaving(vertex0,vertex1)));
        graph.addSavingPairs(new SavingPairs(vertex0,vertex2,graph.calsaving(vertex0,vertex2)));
        graph.addSavingPairs(new SavingPairs(vertex0,vertex3,graph.calsaving(vertex0,vertex3)));
        graph.addSavingPairs(new SavingPairs(vertex1,vertex2,graph.calsaving(vertex1,vertex2)));
        graph.addSavingPairs(new SavingPairs(vertex2,vertex3,graph.calsaving(vertex2,vertex3)));
*/
// test the code
// System.out.println(graph.measuredistance(vertex0,vertex1));
//  System.out.println(graph.measuredistance(vertex0,vertex3));
//  System.out.println(graph.measuredistance(vertex1,vertex3));

//   System.out.println(graph.calsaving(vertex1,vertex2));
//   System.out.println(graph.calsaving(vertex2,vertex3));
//   System.out.println(graph.calsaving(vertex0,vertex2));
//   System.out.println(graph.calsaving(vertex0,vertex3));
//  System.out.println(graph.calsaving(vertex0,vertex3));
//   System.out.println(graph.calsaving(vertex0,vertex1));


//     System.out.println(graph.getSavingPairsList().get(2).getTargetVertex().getDemand());

//   System.out.println(graph.getSavingPairsList());



