package CHelper.src.algo;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class string {

    public static String sortString(String inputString){
        char tempArray[] = inputString.toCharArray();
        Arrays.sort(tempArray);
        return new String(tempArray);
    }

    public static int[] counterAlpha(String str){
        int[] c = new int[26];
        for(char i : str.toCharArray()){
            c[i - 'a']++;
        }
        return c;
    }

    public static int[] counterNum(String str){
        int[] c = new int[10];
        for(char i : str.toCharArray()){
            c[i-'0']++;
        }
        return c;
    }

    /*public static String sort(String inputString){
        Character tempArray[] = new Character[inputString.length()];
        for (int i = 0; i < inputString.length(); i++) {
            tempArray[i] = inputString.charAt(i);
        }

        Arrays.sort(tempArray, new Comparator<Character>(){

            @Override
            public int compare(Character c1, Character c2)
            {
                return Character.compare(Character.toLowerCase(c1),
                        Character.toLowerCase(c2));
            }
        });

        StringBuilder sb = new StringBuilder(tempArray.length);
        for (Character c : tempArray)
            sb.append(c.charValue());

        return sb.toString();
    }*/

    public static StringBuilder replaceAll(StringBuilder string, String pattern, String replace){
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(string);
        return new StringBuilder(m.replaceAll(replace));
    }

    public static String replaceAll(String string, String pattern, String replace){
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(string);
        return m.replaceAll(replace);
    }

    public static boolean isPalindrome(String s) {
        int N = s.length();
        for (int i = 0; i < N/2; i++)
            if (s.charAt(i) != s.charAt(N-1-i))
                return false;
        return true;
    }

    public static boolean isSorted(String[] a){
        for (int i = 1; i < a.length; i++){
            if (a[i-1].compareTo(a[i]) > 0)
                return false;
        }
        return true;
    }

    public static boolean isSorted(char[] a){
        for (int i = 1; i < a.length; i++){
            if (a[i-1]>a[i])
                return false;
        }
        return true;
    }

    public static int longestSubstringWithoutRepeatingCharacters(String s) {
        if (s.length() == 0) {
            return 0;
        }
        int maxLen = 0;
        int len = 1;
        int checkIndex = 0;
        char[] chars = s.toCharArray();
        for (int i = 1; i < chars.length; i++) {
            len++;
            for (int j = checkIndex; j < i; j++) {
                if (chars[i] == chars[j]) {
                    if (maxLen < len - 1) {
                        maxLen = len - 1;
                    }
                    checkIndex = j + 1;
                    len = i - j;
                    break;
                }
            }
        }
        return Math.max(maxLen, len);
    }

    public static HashMap<String, Integer> counter(String[] arr)
    {
        HashMap<String, Integer> counter = new HashMap<>();

        for (String a : arr) {
            if (counter.containsKey(a)) {
                int oldValue = counter.get(a);
                counter.put(a, oldValue + 1);
            } else {
                counter.put(a, 1);
            }
        }
        return counter;
    }

    public static HashMap<Character, Integer> counter(char[] arr)
    {
        HashMap<Character, Integer> counter = new HashMap<>();

        for (char a : arr) {
            if (counter.containsKey(a)) {
                int oldValue = counter.get(a);
                counter.put(a, oldValue + 1);
            } else {
                counter.put(a, 1);
            }
        }
        return counter;
    }

    public static HashMap<Character, Integer> counter(String str)
    {
        HashMap<Character, Integer> counter = new HashMap<>();

        for (char a : str.toCharArray()) {
            if (counter.containsKey(a)) {
                int oldValue = counter.get(a);
                counter.put(a, oldValue + 1);
            } else {
                counter.put(a, 1);
            }
        }
        return counter;
    }

    public static boolean isPangrams(String str){
        int n = str.length();
        str = str.toLowerCase();
        boolean count[] = new boolean[26];
        int c = 0;

        for(int i=0; i<n; i++){
            int index = str.charAt(i)-'a';
            if (index>=0 && index<26 && count[index]==false){
                count[index]=true;
                c++;
            }
            if(c==26)
                return true;
        }
        return false;
    }

    public static boolean isAnagram(String str1, String str2){
        int l1 = str1.length();
        int l2 = str2.length();
        if(l1!=l2)
            return false;

        str1 = str1.toLowerCase();
        str2 = str2.toLowerCase();

        int[] count = new int[256];
        for(int i=0; i<l1; i++){
                count[str1.charAt(i)]++;
                count[str2.charAt(i)]--;
        }

        for(int i=0; i<256; i++){
            if(count[i] != 0)
                return false;
        }
        return true;
    }

    public static StringBuilder searchKMP(String txt, String pat)
    {
        StringBuilder sb = new StringBuilder();
        int M = pat.length();
        int N = txt.length();

        // create lps[] that will hold the longest
        // prefix suffix values for pattern
        int lps[] = new int[M];
        int j = 0;  // index for pat[]

        // Preprocess the pattern (calculate lps[]
        // array)
        // length of the previous longest prefix suffix
        int len = 0;
        int i = 1;
        lps[0] = 0;  // lps[0] is always 0

        // the loop calculates lps[i] for i = 1 to M-1
        while (i < M)
        {
            if (pat.charAt(i) == pat.charAt(len))
            {
                len++;
                lps[i] = len;
                i++;
            }
            else  // (pat[i] != pat[len])
            {
                // This is tricky. Consider the example.
                // AAACAAAA and i = 7. The idea is similar
                // to search step.
                if (len != 0)
                {
                    len = lps[len-1];

                    // Also, note that we do not increment
                    // i here
                }
                else  // if (len == 0)
                {
                    lps[i] = len;
                    i++;
                }
            }
        }

        i = 0;  // index for txt[]
        while (i < N)
        {
            if (pat.charAt(j) == txt.charAt(i))
            {
                j++;
                i++;
            }
            if (j == M)
            {
                sb.append((i-j)+" ");
                j = lps[j-1];
            }

            // mismatch after j matches
            else if (i < N && pat.charAt(j) != txt.charAt(i))
            {
                // Do not match lps[0..lps[j-1]] characters,
                // they will match anyway
                if (j != 0)
                    j = lps[j-1];
                else
                    i = i+1;
            }
        }
        return sb;
    }

    public static int searchKMPFirstOnly(String txt, String pat)
    {
        int M = pat.length();
        int N = txt.length();

        // create lps[] that will hold the longest
        // prefix suffix values for pattern
        int lps[] = new int[M];
        int j = 0;  // index for pat[]

        // Preprocess the pattern (calculate lps[]
        // array)
        // length of the previous longest prefix suffix
        int len = 0;
        int i = 1;
        lps[0] = 0;  // lps[0] is always 0

        // the loop calculates lps[i] for i = 1 to M-1
        while (i < M)
        {
            if (pat.charAt(i) == pat.charAt(len))
            {
                len++;
                lps[i] = len;
                i++;
            }
            else  // (pat[i] != pat[len])
            {
                // This is tricky. Consider the example.
                // AAACAAAA and i = 7. The idea is similar
                // to search step.
                if (len != 0)
                {
                    len = lps[len-1];

                    // Also, note that we do not increment
                    // i here
                }
                else  // if (len == 0)
                {
                    lps[i] = len;
                    i++;
                }
            }
        }


        i = 0;  // index for txt[]
        while (i < N)
        {
            if (pat.charAt(j) == txt.charAt(i))
            {
                j++;
                i++;
            }
            if (j == M)
            {
                return i-j;
                //j = lps[j-1];
            }

            // mismatch after j matches
            else if (i < N && pat.charAt(j) != txt.charAt(i))
            {
                // Do not match lps[0..lps[j-1]] characters,
                // they will match anyway
                if (j != 0)
                    j = lps[j-1];
                else
                    i = i+1;
            }
        }
        return -1;
    }

    public static int countKMP(String txt, String pat)
    {
        int count = 0;
        int M = pat.length();
        int N = txt.length();

        // create lps[] that will hold the longest
        // prefix suffix values for pattern
        int lps[] = new int[M];
        int j = 0;  // index for pat[]

        // Preprocess the pattern (calculate lps[]
        // array)
        // length of the previous longest prefix suffix
        int len = 0;
        int i = 1;
        lps[0] = 0;  // lps[0] is always 0

        // the loop calculates lps[i] for i = 1 to M-1
        while (i < M)
        {
            if (pat.charAt(i) == pat.charAt(len))
            {
                len++;
                lps[i] = len;
                i++;
            }
            else  // (pat[i] != pat[len])
            {
                // This is tricky. Consider the example.
                // AAACAAAA and i = 7. The idea is similar
                // to search step.
                if (len != 0)
                {
                    len = lps[len-1];

                    // Also, note that we do not increment
                    // i here
                }
                else  // if (len == 0)
                {
                    lps[i] = len;
                    i++;
                }
            }
        }


        i = 0;  // index for txt[]
        while (i < N)
        {
            if (pat.charAt(j) == txt.charAt(i))
            {
                j++;
                i++;
            }
            if (j == M)
            {
                count++;
                j = lps[j-1];
            }

            // mismatch after j matches
            else if (i < N && pat.charAt(j) != txt.charAt(i))
            {
                // Do not match lps[0..lps[j-1]] characters,
                // they will match anyway
                if (j != 0)
                    j = lps[j-1];
                else
                    i = i+1;
            }
        }
        return count;
    }

    /*aho-corasick Algo*/
    //R- alphabet size
    //M - Max number of states [= Sum of the length of all keywords]

    //out[] - output function - Bit i in this mask is 1 iff the word
    //                          with index i appears when machine enters
    //                          this state

    //f[] - failure function

    //g[][] - goto function(Trie)
/*
    private static int R = 26,M = 500;
    private static int[] out,f;
    private static int[][] g;

    //Initialize all values in
    //failure function to -1
    //goto function to -1
    //out function to 0 [default when created]
    private static void init(){
        out = new int[M];f = new int[M];g = new int[M][R];
        Arrays.fill(f, -1);
        for(int[] row:g){
            Arrays.fill(row, -1);
        }
    }

    private static int buildMatchingMachine(String[] arr,int k){

        //Initialize step is called
        init();

        //Initially we just have 0 state
        int states=1;

        //Construct values for goto function same as
        //building a Trie for arr[]
        for(int i=0;i<k; i++){

            int currentState=0;
            char[] word = arr[i].toCharArray();

            for(int j=0; j<word.length;j++){

                int ch = word[j]-'a';

                //Allocate a new node (create a new state) if a
                // node for ch doesn't exist
                if(g[currentState][ch]==-1){
                    g[currentState][ch] = states++;
                }

                currentState = g[currentState][ch];
            }

            //Add current word in output function
            out[currentState] |= (1<<i);

        }

        //For all characters which don't have an edge from root
        //(or state 0) in Trie, add a goto edge to state 0 itself

        for(int ch=0; ch<R; ch++){
            if(g[0][ch]==-1){
                g[0][ch]=0;
            }
        }

        //let's build the failure function now
        //Failure function is computed in breadth first order
        //using a queue
        Queue<Integer> q = new LinkedList<Integer>();

        //Iterate over every possible input
        for(int ch=0;ch<R;ch++){
            //All nodes of depth 1 have failure function value as 0
            //For example, in above diagram we move to 0 from states 1 and 3
            if(g[0][ch]!=0){
                f[g[0][ch]]=0;
                q.add(g[0][ch]);
            }
        }

        //Now queue has states 1 and 3
        while(!q.isEmpty()){
            //Remove the front state from queue
            int state = q.poll();

            //For the removed state, find failure function for all
            //those characters for which goto function is not defined
            for(int ch=0; ch<R; ch++){

                //If goto function is defined
                if(g[state][ch]!=-1){

                    //Find failure state of removed state
                    int failure = f[state];

                    //Find the deepest node labelled by proper suffix
                    //of string from root to current state
                    while(g[failure][ch]==-1){
                        failure = f[failure];
                    }

                    failure = g[failure][ch];
                    f[g[state][ch]] = failure;

                    //Merge output values
                    out[g[state][ch]] |= out[failure];

                    //Insert the next level node (of Trie) in queue
                    q.add(g[state][ch]);
                }
            }
        }

        return states;
    }

    //Returns the next state machine will transform to using goto
    //and failure functions
    //currentState - The current state of the machine.
    //               [Must be between 0 and no. of states -1, inclusive]
    //nextInput - The next character that enters the machine

    private static int findNextState(int currentState, char nextInput){

        int ch= nextInput-'a';

        //If goto function is not defined use failure function
        while(g[currentState][ch]==-1){
            currentState = f[currentState];
        }

        return g[currentState][ch];
    }

    //Finds all occurences of all array words in text
    public static int[] countAhoCorasick(String[] arr, int k, String text){
        //StringBuilder[] count = new StringBuilder[k];
        //for(int i=0; i<k; i++)
          //  count[i] = new StringBuilder();
        int[] count = new int[k];
        //Preprocess patterns
        //Build machine with goto, failure and output functions
        buildMatchingMachine(arr,k);

        //Initialize the current state
        int currentState=0;

        //Traverse the text through the built machine to find all
        //occurences of words in arr[]
        for(int i=0; i<text.length(); i++){

            currentState = findNextState(currentState,text.charAt(i));

            //If match not found, move to next state
            if(out[currentState]==0) continue;

            //Match found, print all matching words of arr[] using
            //output function
            for(int j=0; j<k; j++){

                if((out[currentState] & (1<<j))==(1<<j)){
                    count[j]++;
                    //count[j].append((i-arr[j].length()+1)+","+i+" ");
                    //System.out.println("Word "+arr[j]+" appears from "+(i-arr[j].length()+1)+" to "+ i);
                }

            }

        }
        return count;
    }*/
}