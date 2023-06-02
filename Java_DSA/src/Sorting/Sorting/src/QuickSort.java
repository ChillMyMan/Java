package Sorting.Sorting.src;

import edu.princeton.cs.algs4.StdOut;

public class QuickSort {
    private QuickSort(){}

    public static void sort(Comparable[] a, int lo, int hi){
        if(hi <= lo) return;
        int j = partition(a, lo, hi);
        sort(a, lo, j - 1);
        sort(a, j + 1, hi);
        assert isSorted(a, lo, hi);
    }

    private static boolean less(Comparable v, Comparable w){
        return v.compareTo(w) < 0;
    }

    private static void exch(Object[] a, int i, int j){
        Object swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }

    private static boolean isSorted(Comparable[] a){
        return isSorted(a, 0, a.length - 1);
    }

    private static boolean isSorted(Comparable[] a, int lo, int hi){
        for(int i = lo + 1; i <= hi; i++)
            if(less(a[i], a[i - 1]))
                return false;
        return true;
    }

    private static int partition(Comparable[] a, int lo, int hi){
        int i = lo;
        int j = hi + 1;
        Comparable v = a[lo];
        while (true){
            while (less(a[++i], v))
                if(i == hi) break;
            while(less(v, a[--j]))
                if(j == lo) break;
            if(i >= j) break;
            exch(a, i, j);
        }
        exch(a, lo, j);
        return j;
    }

    public static Comparable select(Comparable[] a, int k){
        if(k < 0 || k >= a.length)
            throw new IllegalArgumentException("Index is not between 0 and " + a.length + " : " + k);
        int lo = 0, hi = a.length - 1;
        while (lo < hi){
            int i = partition(a, lo, hi);
            if(i > k) hi = i - 1;
            else if(i < k) lo = i + 1;
            else return a[i];
        }
        return a[lo];
    }

    private static void show(Comparable[] a){
        for(int i = 0; i < a.length; i++)
            System.out.println(a[i]);
    }
}
