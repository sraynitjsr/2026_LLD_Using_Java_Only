public class MySingleton {

    private MySingleton() {
        if (Holder.INSTANCE != null) {
            throw new RuntimeException();
        }
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
