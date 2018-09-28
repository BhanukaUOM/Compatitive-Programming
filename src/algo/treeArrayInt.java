package CHelper.src.algo;

public class treeArrayInt {
    private static int cur;
    private static int[] arr;
    private static int size;

    public treeArrayInt(int root, int length){
        //size = (int)math.pow(2, length)-1;
        size = length;
        arr = new int[length];
        arr[0] = root;
        cur = 0;
    }

    public treeArrayInt(int length){
        size = length;
        arr = new int[length];
        cur = -1;
    }

    public static void setLeft(int key){
        if(cur==-1)
            cur = 0;
        else
            cur = cur*2+1;
        arr[cur] = key;
    }

    public static void setLeft(int key, int parent){
        if(cur==-1)
            cur = 0;
        else
            cur = parent*2+1;
        arr[cur] = key;
    }

    public static void setRight(int key){
        if(cur==-1)
            cur = 0;
        else
            cur = cur*2+2;
        arr[cur] = key;
    }

    public static void setRight(int key, int parent){
        if(cur==-1)
            cur = 0;
        else
            cur = parent*2+2;
        arr[cur] = key;
    }

    public static int getLeft(){
        if(cur==-1)
            return -1;
        else
            return arr[cur*2+1];
    }

    public static int getLeft(int parent){
        return arr[parent*2+1];
    }

    public static int getRight(){
        if(cur==-1)
            return -1;
        else
            return arr[cur*2+2];
    }

    public static int getRight(int parent){
        return arr[parent*2+2];
    }

    public static int get(int node){
        return arr[node];
    }

    public static void set(int node, int Value){
        arr[node] = Value;
    }

    public static int parent(){
        if(cur%2==0)
            return (cur-2)/2;
        return (cur-1)/2;
    }

    public static int parent(int node){
        if(node%2==0)
            return (node-2)/2;
        return (node-1)/2;
    }

    public static void print(){
        int j=0;
        for(int i=0; i<size; i++){
            System.out.print(arr[i]+" ");
            if(i==j) {
                System.out.println();
                j = 2*(j+1);
            }
        }
    }
}
