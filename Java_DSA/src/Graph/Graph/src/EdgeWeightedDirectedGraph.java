package Graph.Graph.src;

import java.util.Iterator;
import java.util.Random;

public class EdgeWeightedDirectedGraph {
    private static final String NEWLINE = System.getProperty("line.seperator");
    private final int V;
    private int E;
    Bag<DirectedEdge>[] adj;
    private int[] indegree;

    public EdgeWeightedDirectedGraph(int V){
        if(V < 0)
            throw new IllegalArgumentException("Number of vertices in a DirectedGraph must be nonnegative");
        this.V = V;
        this.E = 0;
        this.indegree = new int[V];
        adj = (Bag<DirectedEdge>[]) new Bag[V];
        for(int v = 0; v < V; v++)
            adj[v] = new Bag<DirectedEdge>();
    }

    public EdgeWeightedDirectedGraph(int V, int E){
        this(V);
        if(E < 0)
            throw new IllegalArgumentException("Number of edges in a DirectedGraph must be nonnegative");
        for(int i = 0; i < E; i++){
            Random random = new Random();
            int v = random.nextInt(V);
            int w = random.nextInt(V);
            double weight = 0.01 * random.nextDouble(100);
            DirectedEdge e = new DirectedEdge(v, w, weight);
            addEdge(e);
        }
    }

    public EdgeWeightedDirectedGraph(EdgeWeightedDirectedGraph g){
        this(g.V());
        this.E = g.E();
        for(int v = 0; v < g.V(); v++)
            this.indegree[v] = g.indegree(v);
        for(int v = 0; v < g.V(); v++){
            Stack<DirectedEdge> reverse = new Stack<DirectedEdge>();
            for(DirectedEdge e : g.adj[v])
                reverse.push(e);
            for(DirectedEdge e : reverse)
                adj[v].add(e);
        }
    }

    public int V(){
        return V;
    }

    public int E(){
        return E;
    }

    private void validateVertex(int v){
        if(v < 0 || v > V)
            throw new IllegalArgumentException("Vertex " + v + " is not between 0 and " + (V - 1));
    }

    public int indegree(int v){
        validateVertex(v);
        return indegree[v];
    }

    public int outdegree(int v){
        validateVertex(v);
        return adj[v].size();
    }

    public void addEdge(DirectedEdge e){
        int v = e.from();
        int w = e.to();
        validateVertex(v);
        validateVertex(w);
        adj[v].add(e);
        indegree[w]++;
        E++;
    }

    public Iterator<DirectedEdge> adj(int v){
        validateVertex(v);
        return (Iterator<DirectedEdge>) adj[v];
    }

    public Iterable<DirectedEdge> edges(){
        Bag<DirectedEdge> list = new Bag<DirectedEdge>();
        for(int v = 0; v < V; v++){
            for(DirectedEdge e : adj[v])
                list.add(e);
        }
        return list;
    }

    public String toString(){
        StringBuilder s = new StringBuilder();
        s.append(V + " " + E + NEWLINE);
        for(int v = 0; v < V; v++){
            s.append(v + " : ");
            for(DirectedEdge e : adj[v])
                s.append(e + " ");
            s.append(NEWLINE);
        }
        return s.toString();
    }

    public static void main(String[] args) {

    }
}
