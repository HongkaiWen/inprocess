package jvm;

/**
 * Created by hongkai on 2017/1/11.
 */
public class CalcJvm {

    /**
     * 堆初始比例
     */
    private static double initHeapRatio = 0.33;
    /**
     * 堆最大值比例
     */
    private static double maxHeapRatio = 0.66;
    /**
     * 年轻代比例
     */
    private static Integer newRatio = 3;
    /**
     * metaspace初始值比例
     */
    private static double initMetaspaceRatio = 0.11;

    /**
     * 总内存
     */
    private static Integer total = 300;


    public static void main(String args[]){
        System.out.println(String.format("-Xms%dm", (int)(total * initHeapRatio)));
        System.out.println(String.format("-Xmx%dm", (int)(total * maxHeapRatio)));
        System.out.println(String.format("-XX:NewRatio=%d", newRatio));
        System.out.println(String.format("-XX:MetaspaceSize=%dm", (int)(total * initMetaspaceRatio)));
    }
}
