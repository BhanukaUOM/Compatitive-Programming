package CHelper.src.algo;

import java.util.ArrayList;
import java.util.List;

public class trie {
    private static final int ALPHABET_SIZE = 26;
    private static TrieNode root;

    public trie(){
        root = new TrieNode();
    }

    private static class TrieNode
    {
        TrieNode[] children = new TrieNode[ALPHABET_SIZE];
        boolean isEndOfWord;

        TrieNode(){
            isEndOfWord = false;
            for (int i = 0; i < ALPHABET_SIZE; i++)
                children[i] = null;
        }
    };

    public static void insert(String key)
    {
        int level;
        int length = key.length();
        int index;

        TrieNode pCrawl = root;

        for (level = 0; level < length; level++)
        {
            index = key.charAt(level) - 'a';
            if (pCrawl.children[index] == null)
                pCrawl.children[index] = new TrieNode();

            pCrawl = pCrawl.children[index];
        }
        pCrawl.isEndOfWord = true;
    }

    public static void insert(String[] arr){
        for(int i =0; i<arr.length; i++)
            insert(arr[i].toLowerCase());
    }

    public static boolean search(String key)
    {
        int level;
        int length = key.length();
        int index;
        TrieNode pCrawl = root;

        for (level = 0; level < length; level++)
        {
            index = key.charAt(level) - 'a';

            if (pCrawl.children[index] == null)
                return false;

            pCrawl = pCrawl.children[index];
        }

        return (pCrawl != null && pCrawl.isEndOfWord);
    }

    private static List<StringBuilder> str;
    public static List<StringBuilder> searchAutoComplete(String key)
    {
        str = new ArrayList<>();
        StringBuilder tmp = new StringBuilder();

        int length = key.length();
        int index;
        TrieNode pCrawl = root;

        for (int level = 0; level < length; level++)
        {
            index = key.charAt(level) - 'a';

            if (pCrawl.children[index] == null)
                return null;

            pCrawl = pCrawl.children[index];
        }

        if (pCrawl != null){
            searchAutoCompleteUtil(new StringBuilder(key), pCrawl);
        };
        return str;
    }

    private static void searchAutoCompleteUtil(StringBuilder tmp, TrieNode pCrawl){
        if(pCrawl.isEndOfWord) {
            str.add(tmp);
        }

        for(int i=0; i<ALPHABET_SIZE; i++){
            if(pCrawl.children[i]!=null)
                searchAutoCompleteUtil(new StringBuilder(tmp).append((char)(i+'a')), pCrawl.children[i]);
        }
    }

}
