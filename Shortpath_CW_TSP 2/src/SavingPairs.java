class SavingPairs implements Comparable<SavingPairs>{
    private double weight;
    private Vertex startVertex;
    private Vertex targetVertex;

    // this Savingpair class contains informations about saving pairs.
    //rename things

    public SavingPairs(Vertex startVertex, Vertex targetVertex, double weight) {
        this.weight = weight;
        this.startVertex = startVertex;
        this.targetVertex = targetVertex;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public Vertex getStartVertex() {
        return startVertex;
    }

    public void setStartVertex(Vertex startVertex) {
        this.startVertex = startVertex;
    }

    public Vertex getTargetVertex() {
        return targetVertex;
    }

    public void setTargetVertex(Vertex targetVertex) {
        this.targetVertex = targetVertex;
    }

    //this minimum then put
    //this make it maximum sort so
    @Override
    public int compareTo(SavingPairs otherSavingPairs) {
        return  (-Double.compare(this.weight, otherSavingPairs.getWeight()));
    }
    @Override
    public String toString() {
        return this.startVertex+"-"+this.targetVertex;
    }
}