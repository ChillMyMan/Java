package Sorting.Sorting.src;

public class MergeSort {
    private MergeSort(){}

    private static void Merge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi){
        assert isSorted(a, lo, mid);
        assert isSorted(a, mid, hi);

        for(int i = lo; i <= hi; i++)
            aux[i] = a[i];

        int i = lo, j = mid + 1;
        for(int k = lo; k <= hi; k++){
            if(i > mid)                   a[k] = aux[j++];
            else if(j > hi)               a[k] = aux[i++];
            else if(less(aux[j], aux[i]))  a[k] = aux[j++];
            else                          a[k] = aux[i++];
        }

        assert isSorted(a, lo, hi);
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

    private static boolean less(Comparable v, Comparable w){
        return v.compareTo(w) < 0;
    }

    private static void sort(Comparable[] a, Comparable[] aux, int lo, int hi){
        if(hi <= lo) return;
        int mid = (lo + hi) / 2;
        sort(a, aux, lo, mid);
        sort(a, aux, mid + 1, hi);
        Merge(a, aux, lo, mid, hi);
    }

    public static void sort(Comparable[] a){
        Comparable[] aux = new Comparable[a.length];
        sort(a, aux, 0, a.length - 1);
        assert isSorted(a);
    }

    private static void Merge(Comparable[] a, int[] index, int[] aux, int lo, int mid, int hi){
        for(int k = lo; k <= hi; k++)
            aux[k] = index[k];
        int i = lo, j = mid + 1;
        for(int k = lo; k <= hi; k++){
            if(i > mid)                         index[k] = aux[j++];
            else if(j > hi)                     index[k] = aux[i++];
            else if(less(a[aux[j]], a[aux[i]])) index[k] = aux[j++];
            else                                index[k] = aux[i++];
        }
    }

    public static int[] indexSort(Comparable[] a){
        int n = a.length;
        int[] index = new int[n];
        for(int i = 0; i < n; i++)
            index[i] = i;
        int[] aux = new int[n];
        sort(a, index, aux, 0, n - 1);
        return index;
    }

    private static void sort(Comparable[] a, int[] index, int[] aux, int lo, int hi){
        if(hi <= lo) return;
        int mid = (lo + hi) / 2;
        sort(a, index, aux, lo, mid);
        sort(a, index, aux, mid + 1, hi);
        Merge(a, index, aux, lo, mid, hi);
    }

    private static void show(Comparable[] a){
        for(int i = 0; i < a.length; i++)
            System.out.println(a[i]);
    }
}
