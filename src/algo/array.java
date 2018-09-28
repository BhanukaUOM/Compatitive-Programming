package CHelper.src.algo;

import java.util.ArrayList;

public class array {
    public static void subsets(int[] arr)
    {
        int n = arr.length;

        for (int i = 0; i < (1<<n); i++)
        {
            System.out.print("{ ");

            for (int j = 0; j < n; j++)

                if ((i & (1 << j)) > 0)
                    System.out.print(arr[j] + " ");

            System.out.println("}");
        }
    }

    public static ArrayList<ArrayList<Integer>> permutations(int[] arr) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();

        //start from an empty list
        result.add(new ArrayList<>());

        for (int i = 0; i < arr.length; i++) {
            //list of list in current iteration of the array num
            ArrayList<ArrayList<Integer>> current = new ArrayList<>();

            for (ArrayList<Integer> l : result) {
                // # of locations to insert is largest index + 1
                for (int j = 0; j < l.size()+1; j++) {
                    l.add(j, arr[i]);

                    ArrayList<Integer> temp = new ArrayList<>(l);
                    current.add(temp);
                    l.remove(j);
                }
            }

            result = new ArrayList<>(current);
        }

        return result;
    }
}
