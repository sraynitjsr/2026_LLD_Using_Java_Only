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
}
