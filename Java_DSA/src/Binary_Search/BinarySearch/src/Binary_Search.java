package Binary_Search.BinarySearch.src;

public class Binary_Search {
    private Binary_Search() {
    }

    public static int indexOf(int[] a, int key) {
        int lo = 0, hi = a.length - 1;
        while (lo <= hi) {
            int mid = (hi - lo) / 2;
            if (a[mid] < key)
                lo = mid + 1;
            else if (a[mid] > key)
                hi = mid - 1;
            else
                return mid;
        }
        return -1;
    }

    @Deprecated
    public static int rank(int[] a, int key) {
        return indexOf(a, key);
    }

    public static void main(String[] args) {
        int[] a = { 3, 5, 7, 9 };
        int key = 5;
        if(indexOf(a, key) == -1)
            System.out.println("Not found");
        else System.out.println("Index " + indexOf(a, key));
    }
}