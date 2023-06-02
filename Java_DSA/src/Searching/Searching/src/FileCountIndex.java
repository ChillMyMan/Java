import java.io.File;
import java.util.HashMap;

public class FileCountIndex {
    private FileCountIndex() {
    }

    public static void main(String[] args) {
        HashMap<String, HashMap<File, Integer>> hm = new HashMap<String, HashMap<File, Integer>>();

        for (String filename : args) {
            StdOut.println(" " + filename);
            File file = new File(filename);
            In in = new In(file);

            while (!in.isEmpty()) {
                String word = in.readString();
                if (!hm.containsKey(word))
                    hm.put(word, new HashMap<File, Integer>());
                HashMap<File, Integer> h1 = hm.get(word);

                if (!h1.containsKey(file))
                    h1.put(file, 1);
                else
                    h1.put(file, h1.get(file) + 1);
            }
        }
    }
}
