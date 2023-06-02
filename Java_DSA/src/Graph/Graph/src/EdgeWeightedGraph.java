package Graph.Graph.src;

import java.util.Random;

public class EdgeWeightedGraph {
    private static final String NEWLINE = System.getProperty("line.seperator");
    private final int V; // number of vertices
    private int E;
    private Bag<Edge>[] adj; // adjacency

    public EdgeWeightedGraph(int V){
        if(V < 0)
            throw new IllegalArgumentException("Number of vertices must be nonnegative");
        this.V = V;
        this.E = 0;
        adj = (Bag<Edge>[]) new Bag[V];
        for(int v = 0; v < V; v++)
            adj[v] = new Bag<Edge>();
    }

    public EdgeWeightedGraph(int V, int E){
        this(V);
        if(E < 0)
            throw new IllegalArgumentException("Number of edges must be nonnegative");
        for(int i = 0; i < E; i++){
            Random random = new Random();
            int v = random.nextInt(V);
            int w = random.nextInt(V);
            double weight = Math.round(100 * random.nextInt() / 100.0);
            Edge e = new Edge(v, w, weight);
            addEdge(e);
        }
    }

    public EdgeWeightedGraph(EdgeWeightedGraph g){
        this(g.V());
        this.E = g.E();
        for(int v = 0; v < g.V(); v++){
            Stack<Edge> reverse = new Stack<Edge>();
            for(Edge e : g.adj[v])
                reverse.push(e);
            for (Edge e : reverse)
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

    public void addEdge(Edge e){
        int v = e.either();
        int w = e.other(v);
        validateVertex(v);
        validateVertex(w);
        adj[v].add(e);
        adj[w].add(e);
        E++;
    }

    public Iterable<Edge> adj(int v){
        validateVertex(v);
        return adj[v];
    }

    public int degree(int v){
        validateVertex(v);
        return adj[v].size();
    }

    public Iterable<Edge> edges(){
        Bag<Edge> list = new Bag<Edge>();
        for(int v = 0; v < V; v++){
            int selfLoops = 0;
            for(Edge e : adj[v]){
                if(e.other(v) > v)
                    list.add(e);
                else if(e.other(v) == v){
                    if(selfLoops % 2 == 0)
                        list.add(e);
                    selfLoops++;
                }
            }
        }
        return list;
    }

    public String toString(){
        StringBuilder s = new StringBuilder();
        s.append(V + " " + E + NEWLINE);
        for(int v = 0; v < V; v++){
            s.append(v + " : ");
            for(Edge e : adj[v])
                s.append(e + " ");
            s.append(NEWLINE);
        }
        return s.toString();
    }
}
