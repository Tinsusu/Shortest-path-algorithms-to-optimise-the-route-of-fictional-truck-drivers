import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
//this class is a graph, the graph manages add vertices and add  Saving Pairs.
// we also make measure distance method, and calculate saving here.

public class Graph {

    private List<Vertex> vertices;
    private PriorityQueue<SavingPairs> SavingPairsList;
    private List<Route> ListofRoutes;
    private Vertex Depot;

    public void setDepot(Vertex depot) {
        Depot = depot;
    }

    public Vertex getDepot() {
        return Depot;
    }

    public Graph() {
        this.vertices = new ArrayList<>();
        this.SavingPairsList = new PriorityQueue<>();
        this.ListofRoutes = new ArrayList<>();
    }

    public void addVertex(Vertex vertex) {
        this.vertices.add(vertex);
    }

    public List<Vertex> getVertices() {
        return vertices;
    }

    public void setVertices(List<Vertex> vertices) {
        this.vertices = vertices;
    }

    public void addSavingPairs(SavingPairs SavingPairs) {
        Vertex startVertex = SavingPairs.getStartVertex();
        Vertex targetVertex = SavingPairs.getTargetVertex();

        this.vertices.get(vertices.indexOf(startVertex)).addSavingPairs(new SavingPairs(startVertex, targetVertex, SavingPairs.getWeight()));
        this.vertices.get(vertices.indexOf(targetVertex)).addSavingPairs(new SavingPairs(targetVertex, startVertex, SavingPairs.getWeight()));
        this.SavingPairsList.add(SavingPairs);
    }


// calcualate Euclidean distance
    public double measuredistance(Vertex startVertex, Vertex targetVertex) {
        double deltaX = (startVertex.getX() - targetVertex.getX());
        double deltaY = (startVertex.getY() - targetVertex.getY());
        double dis = Math.sqrt((deltaX * deltaX) + (deltaY * deltaY));
        return dis;

    }


public ArrayList<Route> makeInitRoutes(){
       ArrayList<Route> routes = new ArrayList<>();
       for(int i=1;i<vertices.size();i++){
           routes.add(new Route(vertices.get(i),2*measuredistance(getDepot(),vertices.get(i))));

       }
        return routes;
}

//try new
    /*
    public ArrayList<Route> makeInitRoutes(){
        ArrayList<Route> routes = new ArrayList<>();
        for(int i=1;i<vertices.size();i++){
            routes.add(new Route(vertices.get(i),2*vertices.get(i).getX()));

        }
        return routes;
    }
*/

//calculate saving s(i,j) = d(D,i)+ d(D,j) - d(i,j)

    public double calsaving(Vertex i, Vertex j){
        double saving = measuredistance(vertices.get(0),i)+measuredistance(vertices.get(0),j)-measuredistance(i,j);
        System.out.println("Distance from "+i.getId()+" to "+j.getId()+" Distance= "+measuredistance(i,j));
        return saving;

    }


    public PriorityQueue<SavingPairs> getSavingPairsList() {
        return SavingPairsList;
    }

    public void setSavingPairsList(PriorityQueue<SavingPairs> SavingPairsList) {
        this.SavingPairsList = SavingPairsList;
    }

}