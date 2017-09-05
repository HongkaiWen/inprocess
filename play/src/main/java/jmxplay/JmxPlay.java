package jmxplay;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryPoolMXBean;
import java.lang.management.MemoryUsage;
import java.util.concurrent.TimeUnit;

public class JmxPlay {

    public static void main(String args[]) throws InterruptedException {
        MemoryMXBean mxBean = ManagementFactory.getMemoryMXBean();
        MemoryUsage nonHeapMemoryUsage = mxBean.getNonHeapMemoryUsage();
        System.out.println("no-heap used  --->  " + formatSize(nonHeapMemoryUsage.getUsed()));
        System.out.println("no-heap max  --->  " + formatSize(nonHeapMemoryUsage.getMax()));
        System.out.println("no-heap committed  --->  " + formatSize(nonHeapMemoryUsage.getCommitted()));
        System.out.println("no-heap init  --->  " + formatSize(nonHeapMemoryUsage.getInit()));

        System.out.println("---------------------------------------------------------------");
        System.out.println("");

        printMemoryPoolStatus();

        TimeUnit.SECONDS.sleep(1000);
        System.out.println("end");
    }

    private static void printMemoryPoolStatus(){
        for (MemoryPoolMXBean memoryMXBean : ManagementFactory.getMemoryPoolMXBeans()) {
            System.out.println(String.format("%s memory pool name:", memoryMXBean.getName()));
            System.out.println("\t used --->  " + formatSize(memoryMXBean.getUsage().getUsed()));
            System.out.println("\t max --->  " + formatSize(memoryMXBean.getUsage().getMax()));
            System.out.println("\t committed --->  " + formatSize(memoryMXBean.getUsage().getCommitted()));
            System.out.println("\t init --->  " + formatSize(memoryMXBean.getUsage().getInit()));
            System.out.println("---------------------------------------------------------------");
            System.out.println("");
        }
    }

    private static String formatSize(long size){
        if(-1 == size){
            return "unlimited";
        }
        return String.format("%d mb", size/1024/1024);
    }
}
