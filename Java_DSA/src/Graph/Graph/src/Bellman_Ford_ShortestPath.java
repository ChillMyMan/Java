package Graph.Graph.src;

import java.util.Iterator;

public class Bellman_Ford_ShortestPath {
    private double[] distTo; // distTo[v] = distance of shortest s->v path
    private DirectedEdge[] edgeTo; // edgeTo[v] = last edge on shortest s->v path
    private boolean[] onQueue; // onQueue[v] = is v currently on the queue
    private Queue<Integer> queue; // queue of vertices to relax
    private int cost;  // number of calls to relax()
    private Iterator<DirectedEdge> cycle; // negative cycle (or null if no such cycle)

    public Bellman_Ford_ShortestPath(EdgeWeightedDirectedGraph g, int s){
        distTo = new double[g.V()];
        edgeTo = new DirectedEdge[g.V()];
        onQueue = new boolean[g.V()];

        for(int v = 0; v < g.V(); v++)
            distTo[v] = Double.POSITIVE_INFINITY;
        distTo[s] = 0.0;

        queue = new Queue<Integer>();
        queue.enqueue(s);
        onQueue[s] = true;
        while (!queue.isEmpty() && !hasNegativeCycle()){
            int v = queue.dequeue();
            onQueue[v] = false;
            relax(g, v);
        }

        assert check(g, s);
    }

    private void relax(EdgeWeightedDirectedGraph g, int v){
        for (Iterator<DirectedEdge> it = g.adj(v); it.hasNext(); ) {
            DirectedEdge e = it.next();
            int w = e.to();
            if(distTo[w] > distTo[v] + e.weight()){
                distTo[w] = distTo[v] + e.weight();
                edgeTo[w] = e;
                if(!onQueue[w]){
                    queue.enqueue(w);
                    onQueue[w] = true;
                }
                if(cost++ % g.V() == 0){
                    findNegative();
                    if(hasNegativeCycle())
                        return;
                }
            }
        }
    }

    public boolean hasNegativeCycle(){
        return cycle != null;
    }

    public Iterator<DirectedEdge> negativeCycle(){
        return cycle;
    }

    private void findNegative(){
        int V = edgeTo.length;
        EdgeWeightedDirectedGraph spt = new EdgeWeightedDirectedGraph(V);
        for(int v = 0; v < V; v++){
            if(edgeTo[v] != null)
                spt.addEdge(edgeTo[v]);
        }

        EdgeWeightedDirectedCycle finder = new EdgeWeightedDirectedCycle(spt);
        cycle = finder.cycle();
    }

    public double distTo(int v){
        validateVertex(v);
        if(hasNegativeCycle())
            throw new UnsupportedOperationException("Negative cost cycle exists");
        return distTo[v];
    }

    public boolean hasPathTo(int v){
        validateVertex(v);
        return distTo[v] < Double.POSITIVE_INFINITY;
    }

    public Iterable<DirectedEdge> pathTo(int v){
        validateVertex(v);
        if(hasNegativeCycle())
            throw new UnsupportedOperationException("Negative cost cycle exists");
        if(!hasPathTo(v))
            return null;
        Stack<DirectedEdge> path = new Stack<DirectedEdge>();
        for(DirectedEdge e = edgeTo[v]; e != null; e = edgeTo[e.from()])
            path.push(e);
        return path;
    }

    private boolean check(EdgeWeightedDirectedGraph g, int s){
        if(hasNegativeCycle()){
            double weight = 0.0;
            for (Iterator<DirectedEdge> it = negativeCycle(); it.hasNext(); ) {
                DirectedEdge e = it.next();
                weight += e.weight();
            }
            if(weight >= 0.0){
                System.err.println("Error : weight of negative cycle = " + weight);
                return false;
            }
        }else {
            if(distTo[s] != 0.0 || edgeTo[s] != null){
                System.err.println("distanceTo[s] and edgeTo[s] inconsistent");
                return false;
            }
            for(int v = 0; v < g.V(); v++){
                if(v == s)
                    continue;
                if(edgeTo[v] == null && distTo[v] != Double.POSITIVE_INFINITY){
                    System.err.println("distTo[] and edgeTo[] inconsistent");
                    return false;
                }
            }
            for(int v = 0; v < g.V(); v++){
                for (Iterator<DirectedEdge> it = g.adj(v); it.hasNext(); ) {
                    DirectedEdge e = it.next();
                    int w = e.to();
                    if(distTo[v] + e.weight() < distTo[w]){
                        System.err.println("edge " + e + " not relaxed");
                        return false;
                    }
                }
            }
            for(int w = 0; w < g.V(); w++){
                if(edgeTo[w] == null) continue;
                DirectedEdge e = edgeTo[w];
                int v = e.from();
                if(w != e.to())
                    return false;
                if(distTo[v] + e.weight() != distTo[w]){
                    System.err.println("edge " + e + " on shortest path not tight");
                    return false;
                }
            }
        }
        System.out.println("Satisfies optimality conditions");
        System.out.println();
        return true;
    }

    private void validateVertex(int v){
        int V = distTo.length;
        if(v < 0 || v >= V)
            throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V - 1));
    }
}
