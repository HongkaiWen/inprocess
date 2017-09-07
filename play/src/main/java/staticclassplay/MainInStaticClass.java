package staticclassplay;

public class MainInStaticClass {

    static class Main {
        static void main() {
            //将主方法写到静态内部类中，从而不必为每个源文件都这种一个类似的主方法
            new MainInStaticClass().print();
        }
    }

    public static void main(String[] args) {
        new MainInStaticClass().print();
    }

    public void print() {
        System.out.println("main in static inner class");
    }
}


