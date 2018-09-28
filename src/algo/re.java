package CHelper.src.algo;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class re {
    public static boolean find(String str, String pattern){
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(str);
        return m.find();
    }

    public static int count(String str, String pattern){
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(str);

        int count = 0;
        while(m.find())
            count++;
        return count;
    }

    public static String replaceAll(String str, String pattern, String replacer){
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(str);

        return m.replaceAll(replacer);
    }
}
