import java.util.ArrayList;
import java.util.List;

public class Route implements Comparable<Route> {

    private double totaldistance;
    //private Vertex startVertex;
    //private Vertex targetVertex;
    private double totaldemand;
    private double savings;
    private List<Vertex> orderedVertices;

    Route(Vertex v, double distance) {
        orderedVertices = new ArrayList<Vertex>();
        orderedVertices.add(v);
        totaldemand = v.getDemand();
        savings = 0;
        this.totaldistance=distance;
    }
    public boolean IsEndRoute(Vertex vertex){
        return  orderedVertices.get(orderedVertices.size()-1) == vertex;
    }
    public boolean IsStartRoute(Vertex vertex){
        return orderedVertices.get(0)== vertex;
    }
    public boolean RouteContains(Vertex vertex){
       return orderedVertices.contains(vertex);
    }
    public List<Vertex> getOrderedVertices() {
        return orderedVertices;
    }

    public double getTotaldistance() {

        return totaldistance;
    }

    public Vertex getStartVertex() {
        return orderedVertices.get(0);
    }

    public Vertex getTargetVertex() {
        return orderedVertices.get(orderedVertices.size()-1);
    }

    public double getTotaldemand() {
        return totaldemand;
    }

    public double getSavings() {
        return savings;
    }

    public void setTotaldemand(double totaldemand) {
        this.totaldemand = totaldemand;
    }
    public void setTotaldistance(double totaldistance){
        this.totaldistance=totaldistance;
    }

    void AddRoute(Vertex vertex){
        this.orderedVertices.add(vertex);

   }
   public void addVertex(SavingPairs s){
        totaldemand += s.getStartVertex().getDemand()+s.getTargetVertex().getDemand();
        orderedVertices.add(s.getStartVertex());
        orderedVertices.add(s.getTargetVertex());

   }
   void Remove(Vertex vertex){
        this.orderedVertices.remove(vertex);
   }

   boolean Contains(Vertex vertex){
        this.orderedVertices.contains(vertex);
       return true;
   }

   void AddDemand(double demand){
        this.totaldemand= totaldemand+demand;
   }

   void Adddistance(double distance){
        this.totaldistance=totaldistance+distance;
   }



    @Override
    public String toString() {
        String text = null;
        for (Vertex vertex : orderedVertices) {
            text = "Savings: " + savings;
        }
        return text;
    }

    @Override
    public int compareTo(Route r) {
        return Double.compare(r.savings, this.savings);
    }


}
