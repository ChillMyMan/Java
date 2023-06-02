package Graph.Graph.src;

import java.util.Iterator;

public class Dijkstra_ShortestPaths {
    private double[] distTo; // distTo[v] = distance of shortest s->v path
    private DirectedEdge[] edgeTo; // edgeTo[v] = last edge on shortest s->v path
    private IndexMinPQ<Double> pq; // priority queue of vertices

    public Dijkstra_ShortestPaths(EdgeWeightedDirectedGraph g, int s){
        for(DirectedEdge e : g.edges()){
            if(e.weight() < 0)
                throw new IllegalArgumentException("Edge " + e + " has negative weight");
        }

        distTo = new double[g.V()];
        edgeTo = new DirectedEdge[g.V()];
        validateVertex(s);

        for(int v = 0; v < g.V(); v++)
            distTo[v] = Double.POSITIVE_INFINITY;
        distTo[s] = 0.0;

        pq = new IndexMinPQ<Double>(g.V());
        pq.insert(s, distTo[s]);

        while(!pq.isEmpty()){
            int v = pq.delMin();
            for (Iterator<DirectedEdge> it = g.adj(v); it.hasNext(); ) {
                DirectedEdge e = it.next();
                relax(e);
            }
        }
    }

    // Update the new distance :
    private void relax(DirectedEdge e){
        int v = e.from();
        int w = e.to();
        if(distTo[w] > distTo[v] + e.weight()){
            distTo[w] = distTo[v] + e.weight();
            edgeTo[w] = e;
            if(pq.contains(w))
                pq.decreaseKey(w, distTo[w]);
            else pq.insert(w, distTo[w]);
        }
    }

    public double distTo(int v){
        validateVertex(v);
        return distTo[v];
    }

    public boolean hasPathTo(int v){
        validateVertex(v);
        return distTo[v] < Double.POSITIVE_INFINITY;
    }

    public Iterator<DirectedEdge> pathTo(int v){
        validateVertex(v);
        if(!hasPathTo(v))
            return null;
        Stack<DirectedEdge> path = new Stack<DirectedEdge>();
        for(DirectedEdge e = edgeTo[v]; e != null; e = edgeTo[e.from()])
            path.push(e);
        return (Iterator<DirectedEdge>) path;
    }


    // Check optimality conditions :
    // (i) for all edges e : distTo[e.to()] <= distTo[e.from()] + e.weight()
    // (ii) for all edge e on the shortest path : distTo[e.to()] == distTo[e.from()] + e.weight()
    private boolean check(EdgeWeightedDirectedGraph g, int s){

        // check that edge weights are nonnegative
        for(DirectedEdge e : g.edges()){
            if(e.weight() < 0){
                System.err.println("Negative edge weight detected");
                return false;
            }
        }

        // check that distTo[v] and edgeTo[v] are consistent
        if(distTo[s] != 0.0 || edgeTo[s] != null){
            System.err.println("distTo[s] and edgeTo[s] inconsistent");
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

        // check that all edges e = v->w satisfy distTo[w] <= distTo[v] + e.weight()
        for(int v = 0; v < g.V(); v++){
            for (Iterator<DirectedEdge> it = g.adj(v); it.hasNext(); ) {
                DirectedEdge e = it.next();
                int w = e.to();
                if(distTo[v] + e.weight() < distTo[w]){
                    System.err.println("Edge " + e + " not relaxed");
                }
            }
        }

        for(int w = 0; w < g.V(); w++){
            if(edgeTo[w] == null)
                continue;
            DirectedEdge e = edgeTo[w];
            int v = e.from();
            if(w != e.to())
                return false;
            if(distTo[v] + e.weight() != distTo[w]){
                System.err.println("Edge " + e + " on shortest path not tight");
                return false;
            }
        }

        return true;
    }

    // throw new IllegalArgumentException unless {@code 0 <= v < V}
    private void validateVertex(int v){
        int V = distTo.length;
        if(v < 0 || v >= V)
            throw new IllegalArgumentException("Vertex " + v + " is not between 0 and " + (V - 1));
    }
}
