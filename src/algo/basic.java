package CHelper.src.algo;

public class basic
{
    public static int in(int[] nums, int target){ for (int i=0; i<nums.length; i++) if(nums[i]==target) return i; return -1; }
    public static int in(long[] nums, long target){ for (int i=0; i<nums.length; i++) if(nums[i]==target) return i; return -1; }
    public static int in(char[] nums, char target){ for (int i=0; i<nums.length; i++) if(nums[i]==target) return i; return -1; }
    public static int in(String[] nums, String target){ for (int i=0; i<nums.length; i++) if(nums[i].equals(target)) return i; return -1; }
    public static int in(double[] nums, double target){ for (int i=0; i<nums.length; i++) if(nums[i]==target) return i; return -1; }
    public static int in(float[] nums, float target){ for (int i=0; i<nums.length; i++) if(nums[i]==target) return i; return -1; }
    public static int sum(int[] arr){ int total=0; for (int i=0; i<arr.length; i++) total+=arr[i]; return total; }
    public static long sum(long[] arr){ long total=0; for (int i=0; i<arr.length; i++) total+=arr[i]; return total; }
    public static float sum(float[] arr){ float total=0; for (int i=0; i<arr.length; i++) total+=arr[i]; return total; }
    public static double sum(double[] arr){ double total=0; for (int i=0; i<arr.length; i++) total+=arr[i]; return total; }
    public static int min(int num1, int num2){ if(num1>=num2) return num2; return num1; }
    public static long min(long num1, long num2){ if(num1>num2) return num2; return num1; }
    public static float min(float num1, float num2){ if(num1>num2) return num2; return num1; }
    public static double min(double num1, double num2){ if(num1>num2) return num2; return num1; }
    public static int min(int[] arr){ int minimum=arr[0]; for (int i=1; i<arr.length; i++) min(arr[i-1], arr[i]); return minimum; }
    public static long min(long[] arr){ long minimum=arr[0]; for (int i=1; i<arr.length; i++) min(arr[i-1], arr[i]); return minimum; }
    public static float min(float[] arr){ float minimum=arr[0]; for (int i=1; i<arr.length; i++) min(arr[i-1], arr[i]); return minimum; }
    public static double min(double[] arr){ double minimum=arr[0]; for (int i=1; i<arr.length; i++) min(arr[i-1], arr[i]); return minimum; }
    public static int max(int num1, int num2){ if(num1<=num2) return num2; return num1; }
    public static long max(long num1, long num2){ if(num1<num2) return num2; return num1; }
    public static float max(float num1, float num2){ if(num1<num2) return num2; return num1; }
    public static double max(double num1, double num2){ if(num1<num2) return num2; return num1; }
    public static int max(int[] arr){ int maximum=arr[0]; for (int i=1; i<arr.length; i++) max(arr[i-1], arr[i]); return maximum; }
    public static long max(long[] arr){ long maximum=arr[0]; for (int i=1; i<arr.length; i++) max(arr[i-1], arr[i]); return maximum; }
    public static float max(float[] arr){ float maximum=arr[0]; for (int i=1; i<arr.length; i++) max(arr[i-1], arr[i]); return maximum; }
    public static double max(double[] arr){ double maximum=arr[0]; for (int i=1; i<arr.length; i++) max(arr[i-1], arr[i]); return maximum; }
    public static int toInt(String str){ return Integer.parseInt(str);}
    public static int toInt(long num){ return (int)num;}
    public static int toInt(float num){ return (int)num;}
    public static int toInt(double num){ return (int)num;}
    public static double toDouble(String str){return Double.parseDouble(str);}
    public static double toDouble(int num){ return (double)num;}
    public static double toDouble(long num){ return (double)num;}
    public static double toDouble(float num){ return (double)num;}
    public static float toFloat(String str){return Float.parseFloat(str);}
    public static float toFloat(int num){ return (float)num;}
    public static float toFloat(long num){ return (float)num;}
    public static float toFloat(double num){ return (float)num;}
    public static long toLong(String str){return Long.parseLong(str);}
    public static long toLong(int num){ return (long)num;}
    public static long toLong(float num){ return (long)num;}
    public static long toLong(double num){ return (long)num;}
    public static String toString(int num){ return Integer.toString(num);}
    public static String toString(char ch){ return Character.toString(ch);}
    public static String toString(long num){ return Long.toString(num);}
    public static String toString(double num){ return Double.toString(num);}
    public static String toString(float num){ return Float.toString(num);}
    public static int abs(int num){if(num<0) return -num; return num;};
    public static long abs(long num){if(num<0) return -num; return num;}
    public static float abs(float x){if (x < 0.0) return -x; return  x;}
    public static double abs(double x){if (x < 0.0) return -x; return  x;}
    public static float avg(int[] a){ int N = a.length; int sum = 0; for (int i = 0; i < N; i++) sum += a[i];  return (float)sum/N;}
    public static float avg(long[] a){ int N = a.length; long sum = 0; for (int i = 0; i < N; i++) sum += a[i];  return (float)sum/N;}
    public static float avg(float[] a){ int N = a.length; float sum = 0.0f; for (int i = 0; i < N; i++) sum += a[i];  return sum/N;}
    public static double avg(double[] a){ int N = a.length; double sum = 0.0; for (int i = 0; i < N; i++) sum += a[i];  return sum/N;}
    public static int[] reverse(int[] a){ int N = a.length; for (int i = 0; i < N/2; i++) {   int temp = a[i];   a[i] = a[N-1-i];   a[N-i-1] = temp; } return a;}
    public static long[] reverse(long[] a){ int N = a.length; for (int i = 0; i < N/2; i++) {   long temp = a[i];   a[i] = a[N-1-i];   a[N-i-1] = temp; } return a;}
    public static float[] reverse(float[] a){ int N = a.length; for (int i = 0; i < N/2; i++) {   float temp = a[i];   a[i] = a[N-1-i];   a[N-i-1] = temp; } return a;}
    public static double[] reverse(double[] a){ int N = a.length; for (int i = 0; i < N/2; i++) {   double temp = a[i];   a[i] = a[N-1-i];   a[N-i-1] = temp; } return a;}
    public static char[] reverse(char[] a){ int N = a.length; for (int i = 0; i < N/2; i++) {   char temp = a[i];   a[i] = a[N-1-i];   a[N-i-1] = temp; } return a;}
    public static String[] reverse(String[] a){ int N = a.length; for (int i = 0; i < N/2; i++) {   String temp = a[i];   a[i] = a[N-1-i];   a[N-i-1] = temp; } return a;}
    public static double sqrt(double num){ return Math.sqrt(num);}
    public static double pow(double base, double exp) { return Math.pow(base,exp); };
}
