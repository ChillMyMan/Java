package Union_Find.UnionFind.src;

public class UF {
    private int[] parent;
    private int[] rank;
    private int count;

    public UF(int n) {
        if (n < 0)
            throw new IllegalArgumentException();
        count = n;
        parent = new int[n];
        rank = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            rank[i] = 0;
        }
    }

    public int find(int p) {
        validate(p);
        while (p != parent[p]) {
            parent[p] = parent[parent[p]];
            p = parent[p];
        }
        return p;
    }

    private void validate(int p) {
        int n = parent.length;
        if(p < 0 || p >= n)
            throw new IllegalArgumentException("Index " + p + " is not between 0 and " + (n - 1));
    }

    public int count() {
        return count;
    }

    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    public void union(int p, int q) {
        int rootP = find(p);
        int rootQ = find(q);
        if (rootP == rootQ)
            return;
        if(rank[rootP] > rank[rootQ])
            parent[rootP] = rootQ;
        else if(rank[rootP] < rank[rootQ])
            parent[rootQ] = rootP;
        else {
            parent[rootP] = rootQ;
            rank[rootP]++;
        }
        count--;
    }

}