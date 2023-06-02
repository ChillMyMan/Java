package Graph.Graph.src;

import java.util.Iterator;

public class EdgeWeightedDirectedCycle {
    private boolean[] marked;
    private DirectedEdge[] edgeTo;
    private boolean[] onStack;
    private Stack<DirectedEdge> cycle;

    public EdgeWeightedDirectedCycle(EdgeWeightedDirectedGraph g){
        marked = new boolean[g.V()];
        onStack = new boolean[g.V()];
        edgeTo = new DirectedEdge[g.V()];
        for(int v = 0; v < g.V(); v++)
            if(!marked[v])
                dfs(g, v);
        assert check();
    }

    private void dfs(EdgeWeightedDirectedGraph g, int v){
        onStack[v] = true;
        marked[v] = true;
        for(DirectedEdge e : g.adj[v]){
            int w = e.to();
            if(cycle != null)
                return;
            else if(!marked[w]){
                edgeTo[w] = e;
                dfs(g, w);
            }
            else if(onStack[w]){
                cycle = new Stack<DirectedEdge>();
                DirectedEdge f = e;
                while (f.from() != w){
                    cycle.push(f);
                    f = edgeTo[f.from()];
                }
                cycle.push(f);
                return;
            }
        }
        onStack[v] = false;
    }

    public boolean hasCycle(){
        return cycle != null;
    }

    public Iterator<DirectedEdge> cycle(){
        return (Iterator<DirectedEdge>) cycle;
    }

    private boolean check(){
        if(hasCycle()){
            DirectedEdge first = null, last = null;
            for(DirectedEdge e : cycle){
                if(first == null)
                    first = e;
                if(last != null){
                    if(last.to() != e.from())
                        System.err.printf("Cycle edges %s and %s not incident\n", last, e);
                    return false;
                }
                last = e;
            }
            if(last.to() != first.from()){
                System.err.printf("Cycle edges %s and %s not incident\n", last, first);
                return false;
            }
        }
        return true;
    }
}
