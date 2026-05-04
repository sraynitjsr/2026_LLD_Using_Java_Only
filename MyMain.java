public class MyMain {

    public static void main(String[] args) {

        MySingleton obj1 = MySingleton.getInstance();
        MySingleton obj2 = MySingleton.getInstance();

        obj1.showMessage();

        System.out.println(obj1 == obj2);
    }
}
