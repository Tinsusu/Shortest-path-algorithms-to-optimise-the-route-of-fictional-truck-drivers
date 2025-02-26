import java.util.ArrayList;
import java.util.List;
// this class contains information about vertices

class Vertex {

    private int id;
    private SavingPairs minSavingPairs;        // SavingPairsTo[v] = shortest SavingPairs from tree vertex to non-tree vertex
   // private boolean visited;
  //  private Vertex previousVertex;
    //miniDistance we do not use it for now.
   // private double minDistance = Double.POSITIVE_INFINITY;   // to detect whether heap is in need of refresh because of better weighted SavingPairs
    private List<SavingPairs> adjacencies;
    private double x;
    private double y;
    private double demand;

    public int getId() {
        return id;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getDemand() {
        return demand;
    }

    public Vertex(int id, double x, double y, double demand) {
        this.id = id;
        this.adjacencies = new ArrayList<>();
        this.x=x;
        this.y=y;
        this.demand=demand;

    }

   // public Vertex getPreviousVertex() {
   //     return previousVertex;
  //  }

    public void addSavingPairs(SavingPairs SavingPairs){
        this.adjacencies.add(SavingPairs);
    }

    public SavingPairs getMinSavingPairs() {
        return minSavingPairs;
    }

    public void setMinSavingPairs(SavingPairs minSavingPairs) {
        this.minSavingPairs = minSavingPairs;
    }

    public List<SavingPairs> getAdjacencies() {
        return adjacencies;
    }

   // public double getMinDistance() {
   //     return minDistance;
  //  }

  //  public void setMinDistance(double minDistance) {
   //     this.minDistance = minDistance;
  //  }

   // public void setPreviousVertex(Vertex previousVertex) {
   //     this.previousVertex = previousVertex;
   // }

  //  public boolean isVisited() {
  //      return visited;
  //  }

  //  public void setVisited(boolean visited) {
  //      this.visited = visited;
 //   }

    @Override
    public String toString() {
        return ""+this.id;
    }



}