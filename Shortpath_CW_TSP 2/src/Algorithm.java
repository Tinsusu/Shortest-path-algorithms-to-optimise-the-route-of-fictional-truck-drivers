import org.w3c.dom.ls.LSOutput;

//Clarke and wright's algorithm
import java.util.*;
// we implement clark and write

public class Algorithm {

    private List<Vertex> vertices;
    private PriorityQueue<SavingPairs> Q;
    private List<SavingPairs> SortList;

    private int capacity = 30;
    private ArrayList<Route> routes;

    // Step 1: calculate saving s(i,j) = d(D,i)+ d(D,j) - d(i,j) we have done it since we input data in  the saving Pairs
    public Algorithm(Graph graph) {

        this.vertices = graph.getVertices();
        //   this.Q = new PriorityQueue<SavingPairs>();
        this.SortList = new ArrayList<SavingPairs>();
// Step 2: Rank the data, in this step we put all saving pair into the priority Q and then sort them decending order of saving
        //for (SavingPairs SavingPairs : graph.getSavingPairsList()) {
        //    this.Q.add(SavingPairs);
        // }
        Q = graph.getSavingPairsList();
        routes = graph.makeInitRoutes();
        while (!Q.isEmpty()) {
            // System.out.println(Q.poll());
            SavingPairs getout = Q.poll();
            SortList.add(getout);
        }
        System.out.println("After sort");
        System.out.println(SortList);
        for (SavingPairs SavingPairs : SortList) {
            System.out.println("Saving Pair: " + SavingPairs);
            System.out.println("Saving: " + SavingPairs.getWeight());
            System.out.println("start: " + SavingPairs.getStartVertex());
            System.out.println("target: " + SavingPairs.getTargetVertex());
            System.out.println("start Demand: " + SavingPairs.getStartVertex().getDemand());
            System.out.println("End Demad: " + SavingPairs.getTargetVertex().getDemand());
            System.out.println("");
            System.out.println("");
        }

        // Step 3 create route
        System.out.println("All vertex" + vertices);
        System.out.println("List afeter sort" + SortList);
        float totalLength = 0;
        int numRoutes = 0;
        for (Route route : routes) {
            if (route.getOrderedVertices().size() > 0) {
                totalLength += route.getTotaldistance();
                System.out.println(route.getOrderedVertices() + " before"+ route.getTotaldistance());
                numRoutes++;
            }
            System.out.println(route.getOrderedVertices());
        }

        System.out.println("Before merge Carlk&Wright total length: " + totalLength + " with " + numRoutes + " routes.");

        for (SavingPairs sij : SortList){
             checkingRoute(sij);}
        //results


        float beforetotalLength = 0;
        int beforenumRoutes = 0;
        for (Route route : routes) {
            if (route.getOrderedVertices().size() > 0) {
                beforetotalLength += route.getTotaldistance();
                System.out.println(route.getOrderedVertices() + " "+ route.getTotaldistance());
                beforenumRoutes++;
            }
            System.out.println(route.getOrderedVertices());
        }

        System.out.println("Carlk&Wright total length: " + beforetotalLength + " with " + beforenumRoutes + " routes.");
    }

    // r1=1,2,3  r2=4,5,6  saving pair 1,6 merge is then for(i=0 i less than r1.size() i++) r2.add(r1.get(i)) after finishing for routes.delete(r2)
    //  saving pair 3,4 same but switch r1 and r2
    //  saving pair 1,4 Collections.reverse(r1) do the for size of r2 do r1.add(r2.get(i)) and then delete r2
    // saving pair 3,6 reverse r2 and for each vertex in r2 for 0 to size  do r1.add(reversed r2) and then delete r2


    boolean mergeRoute(Route r1, Route r2, SavingPairs s) {
        List<Vertex> rr1 = r1.getOrderedVertices();
        List<Vertex> rr2 = r2.getOrderedVertices();
//Check first case
//     for(Route r:routes){
            if (r1.IsStartRoute(s.getStartVertex()) && r2.IsEndRoute(s.getTargetVertex())) {
                for (int i = 0; i < rr1.size(); i++) {
                    rr2.add(rr1.get(i));
                }
  //              if (r.getOrderedVertices().contains(r1)) {
                   r2.setTotaldemand(r1.getTotaldemand()+r2.getTotaldemand());
                   r2.setTotaldistance(r2.getTotaldistance()+r1.getTotaldistance()-s.getWeight());
                    routes.remove(r1); //should we remove R1?

                    return true;
                }
//            }
            else if (r1.IsEndRoute(s.getTargetVertex()) && r2.IsStartRoute(s.getStartVertex())) {
                for (int i = 0; i < rr2.size(); i++) {
                    rr1.add(rr2.get(i));
                }

  //              if (r.getOrderedVertices().contains(r2)) {
                    r1.setTotaldemand(r1.getTotaldemand()+r2.getTotaldemand());
                    r1.setTotaldistance(r2.getTotaldistance()+r1.getTotaldistance()-s.getWeight());
                    routes.remove(r2);

                    return true;
                }
                //  routes.remove(r2);
            //}
            else if (r1.IsStartRoute(s.getStartVertex()) && r2.IsStartRoute(s.getStartVertex())) {
                Collections.reverse(rr1);
                for (int i = 0; i < rr2.size(); i++) {
                    rr1.add(rr2.get(i));
                }

              //  if (r.getOrderedVertices().contains(r2)) {
                    r1.setTotaldemand(r1.getTotaldemand()+r2.getTotaldemand());
                    r1.setTotaldistance(r2.getTotaldistance()+r1.getTotaldistance()-s.getWeight());
                    routes.remove(r2);
                    //  routes.remove(r2);
                    return true;
                }
            else if (r1.IsEndRoute(s.getTargetVertex()) && r2.IsStartRoute(s.getStartVertex())) {
                    Collections.reverse(rr2);
                    for (int i = 0; i < rr2.size(); i++) {
                        rr1.add(rr2.get(i));
                    }
            //        if (r.getOrderedVertices().contains(r2)) {
                        r1.setTotaldemand(r1.getTotaldemand()+r2.getTotaldemand());
                        r1.setTotaldistance(r2.getTotaldistance()+r1.getTotaldistance()-s.getWeight());
                        routes.remove(r2);
                        //  routes.remove(r2);
                        return true;
                    }
          //  else if (!(r1.IsStartRoute(s.getStartVertex()) || r1.IsEndRoute(s.getStartVertex()))) {
                 //       if (r.getOrderedVertices().contains(r1)) {
            //                routes.remove(r1);
             //           }
                        //   routes.remove(r1);

            //        } else {

              //          if (r.getOrderedVertices().contains(r2)) {
              //              routes.remove(r2);
              //          }
                        //   routes.remove(r2);
               //     }

       //         }
         //   }
        return false;
        }
   // }

            //route_temp.Contains(s.getTargetVertex()) OR route_temp.Contains(s.getTargetVertex()))
/*
        void initRoute(){
            //init data lists
          List<Route> routes = new ArrayList<>();

            //make a new route for every vertex in the vertexArrayList
            for (Vertex vertex : vertices) {
                Route route = new Route();
                route.addVertex(vertices.get(0)); //get depot
                route.addVertex(vertex);
                routes.add(route);
                routeHappy.put(vertex,route);
            }
        }

*/
    void checkingRoute (SavingPairs sij) {
//        for (SavingPairs sij : SortList) {
           Vertex i = sij.getStartVertex();
           Vertex j = sij.getTargetVertex();

           for (Route r1 : routes) {
                        //System.out.println(r1.getOrderedVertices());
                        for (Route r2 : routes) {
                            if (!(r1 == r2) && r1.getOrderedVertices().contains(i)
                                    && r2.getOrderedVertices().contains(j)
                                    && ((r1.getTotaldemand() + r2.getTotaldemand()) <= capacity)) {
                                  System.out.println("SAVING PAIRs: "+sij);
                                  System.out.println("R1: "+r1.getOrderedVertices()+" Demand:"+r1.getTotaldemand());
                                  System.out.println("R2: "+r2.getOrderedVertices()+" Demand:"+r2.getTotaldemand());
                                    double sum = r1.getTotaldemand()+r2.getTotaldemand();
                                System.out.println("capacity: "+ capacity+ " R1R2 Demand:"+sum);

                                //(!(r1 == r2) && r1.getOrderedVertices().contains(i) && r2.getOrderedVertices().contains(j))
                                 mergeRoute(r1, r2, sij);
                                 return;
                               //  mergeRoute(r2, r1, sij);
                              //  if (r1.IsStartRoute(sij.getStartVertex()) && r2.IsEndRoute(sij.getTargetVertex())) {
                              //      for (int x = 0; x < r1.getOrderedVertices().size(); x++) {
                             //           r2.getOrderedVertices().add(r1.getOrderedVertices().get(x));
                             //       }
                                    //should we remove R1?
                              //      System.out.println(routes);
                             //       for (Route r : routes)
                              //          if (r.getOrderedVertices().contains(r1)) {
                              //              routes.remove(r1);
                               //         }
                              //  }

                            }
                        }
                    }
                }
            }
       // }












