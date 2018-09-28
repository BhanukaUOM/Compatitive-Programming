package CHelper.src.algo;

public class treeArrayString {
    private static int cur;
    private static String[] arr;
    private static int size;

    public treeArrayString(String root, int length){
        //size = (int)math.pow(2, length)-1;
        size = length;
        arr = new String[length];
        arr[0] = root;
        cur = 0;
    }

    public treeArrayString(int length){
        size = length;
        arr = new String[length];
        cur = -1;
    }

    public static void setLeft(String key){
        if(cur==-1)
            cur = 0;
        else
            cur = cur*2+1;
        arr[cur] = key;
    }

    public static void setLeft(String key, int parent){
        if(cur==-1)
            cur = 0;
        else
            cur = parent*2+1;
        arr[cur] = key;
    }

    public static void setRight(String key){
        if(cur==-1)
            cur = 0;
        else
            cur = cur*2+2;
        arr[cur] = key;
    }

    public static void setRight(String key, int parent){
        if(cur==-1)
            cur = 0;
        else
            cur = parent*2+2;
        arr[cur] = key;
    }

    public static String getLeft(){
        if(cur==-1)
            return null;
        else
            return arr[cur*2+1];
    }

    public static String getLeft(int parent){
        return arr[parent*2+1];
    }

    public static String getRight(){
        if(cur==-1)
            return null;
        else
            return arr[cur*2+2];
    }

    public static String getRight(int parent){
        return arr[parent*2+2];
    }

    public static String get(int node){
        return arr[node];
    }

    public static void set(int node, String Value){
        arr[node] = Value;
    }

    public static void print(){
        int j=0;
        for(int i=0; i<size; i++){
            if (arr[i] != null)
                System.out.print(arr[i]+" ");
            else
                System.out.print(null+" ");
            if(i==j) {
                System.out.println();
                j = 2*(j+1);
            }
        }
    }
}
