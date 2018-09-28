package CHelper.src.algo;

import java.util.Arrays;

public class segmentTree {
    /* Tree */
    private int cur;
    private int[] arr;
    private int size;
    private int org;

    public void treeArrayInt(int length) {
        size = length;
        arr = new int[length];
        cur = -1;
    }

    public int[] aray() {
        return arr;
}

    public int getLeft(int parent) {
        return arr[parent * 2 + 1];
    }

    public int getRight(int parent) {
        return arr[parent * 2 + 2];
    }

    public int get(int node) {
        return arr[node];
    }

    public int getArray(int node) {
        return arr[size - org + node];
    }

    public void set(int node, int Value) {
        arr[node] = Value;
    }

    public int parent(int node){
        if(node%2==0)
            return (node-2)/2;
        return (node-1)/2;
    }

    /*SEG*/
    public segmentTree(int length){
        org = math.nextPowerOf2(length);
        size = 2*org-1;
        treeArrayInt(size);
    }

    public void print(){
        int j=0;
        for(int i=0; i<size; i++){
            System.out.print(arr[i]+" ");
            if(i==j) {
                System.out.println();
                j = 2*(j+1);
            }
        }
    }

    
    /* SUM TREE */
    public void sumTree(int[] arr){
        // Set Last Level of Tree
        int j=0;
        for(int i=size/2; i<=size/2+arr.length-1; i++) {
            set(i, arr[j]);
            j++;
        }

        //Construct Tree
        int start = size/4;
        while(start>=1) {
            for (int i = start; i <= 2*start; i++) {
                set(i, getLeft(i)+getRight(i));
            }
            start/=2;
        }
        set(0, getLeft(0)+getRight(0));
    }

    public void sumTreeUpdate(int index, int value){
        index = (size/2)+index;
        if(get(index)!=value) {
            set(index, value);
            int parent = parent(index);

            while (parent > 0) {
                set(parent, getLeft(parent) + getRight(parent));
                parent = parent(parent);
            }
            set(0, getLeft(0) + getRight(0));
        }
    }

    private int sumTreeGetUtil(int ss, int se, int qs, int qe, int si)
    {
        // If segment of this node is a part of given range, then return
        // the sum of the segment
        if (qs <= ss && qe >= se)
            return get(si);

        // If segment of this node is outside the given range
        if (se < qs || ss > qe)
            return 0;

        // If a part of this segment overlaps with the given range
        int mid = ss + (se - ss) / 2;
        return sumTreeGetUtil(ss, mid, qs, qe, 2 * si + 1) + sumTreeGetUtil(mid + 1, se, qs, qe, 2 * si + 2);
    }

    public int sumTreeGet(int StartIndex, int EndIndex){
        return sumTreeGetUtil(0, size/2, StartIndex, EndIndex, 0);
    }

    /* MAX TREE */
    public void maxTree(int[] arr){
        // Set Last Level of Tree
        int j=0;
        for(int i=size/2; i<=size/2+arr.length-1; i++) {
            set(i, arr[j]);
            j++;
        }

        //Construct Tree
        int start = size/4;
        while(start>=1) {
            for (int i = start; i <= 2*start; i++) {
                set(i, Math.max(getLeft(i), getRight(i)));
            }
            start/=2;
        }
        set(0, Math.max(getLeft(0), getRight(0)));
    }

    public void maxTreeUpdate(int index, int value){
        index = (size/2)+index;
        if(get(index)!=value) {
            set(index, value);
            int parent = parent(index);

            while (parent > 0) {
                set(parent, Math.max(getLeft(parent), getRight(parent)));
                parent = parent(parent);
            }
            set(0, Math.max(getLeft(0), getRight(0)));
        }
    }

    private int maxTreeGetUtil(int ss, int se, int qs, int qe, int si)
    {
        // If segment of this node is a part of given range, then return
        // the sum of the segment
        if (qs <= ss && qe >= se)
            return get(si);

        // If segment of this node is outside the given range
        if (se < qs || ss > qe)
            return 0;

        // If a part of this segment overlaps with the given range
        int mid = ss + (se - ss) / 2;
        return Math.max(maxTreeGetUtil(ss, mid, qs, qe, 2 * si + 1), maxTreeGetUtil(mid + 1, se, qs, qe, 2 * si + 2));
    }

    public int maxTreeGet(int StartIndex, int EndIndex){
        return sumTreeGetUtil(0, size/2, StartIndex, EndIndex, 0);
    }


    /* XOR TREE */
    public void xorTree(int[] arr){
        // Set Last Level of Tree
        int j=0;
        for(int i=size/2; i<=size/2+arr.length-1; i++) {
            set(i, arr[j]);
            j++;
        }
        //System.out.println(Arrays.toString(this.arr));
        //Construct Tree
        int start = size/4;
        while(start>=1) {
            for (int i = start; i <= 2*start; i++) {
                set(i, getLeft(i)^getRight(i));
            }
            start/=2;
        }
        set(0, getLeft(0)^getRight(0));
    }

    public void xorTreeUpdate(int index, int value){
        index = (size/2)+index;
        if(get(index)!=value) {
            set(index, value);
            int parent = parent(index);

            while (parent > 0) {
                set(parent, getLeft(parent) ^ getRight(parent));
                parent = parent(parent);
            }
            set(0, getLeft(0) ^ getRight(0));
        }
    }

    private int xorTreeGetUtil(int ss, int se, int qs, int qe, int si)
    {
        // If segment of this node is a part of given range, then return
        // the sum of the segment
        if (qs <= ss && qe >= se)
            return get(si);

        // If segment of this node is outside the given range
        if (se < qs || ss > qe)
            return 0;

        // If a part of this segment overlaps with the given range
        int mid = ss + (se - ss) / 2;
        return xorTreeGetUtil(ss, mid, qs, qe, 2 * si + 1) ^ xorTreeGetUtil(mid + 1, se, qs, qe, 2 * si + 2);
    }

    public int xorTreeGet(int StartIndex, int EndIndex){
        return xorTreeGetUtil(0, size/2, StartIndex, EndIndex, 0);
    }

}
