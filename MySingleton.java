public class MySingleton {

    private static boolean created = false;

    private MySingleton() {
        if (created) {
            throw new RuntimeException();
        }
        created = true;
    }

    private static class Holder {
        private static final MySingleton INSTANCE = new MySingleton();
    }

    public static MySingleton getInstance() {
        return Holder.INSTANCE;
    }

    public void showMessage() {
        System.out.println("Singleton instance working!");
    }

    public static void Start() {
        System.out.println("=== Singleton Pattern ===");
        MySingleton obj1 = MySingleton.getInstance();
        MySingleton obj2 = MySingleton.getInstance();

        obj1.showMessage();
        System.out.println("Same instance? " + (obj1 == obj2));
    }
}
