public class MyMain {

    // Singleton Class (Inner Static Class Approach)
    static class Singleton {

        // Private constructor
        private Singleton() {
            if (Holder.INSTANCE != null) {
                throw new RuntimeException("Use getInstance() method");
            }
        }

        // Static inner class - loaded only when needed
        private static class Holder {
            private static final Singleton INSTANCE = new Singleton();
        }

        public static Singleton getInstance() {
            return Holder.INSTANCE;
        }

        public void showMessage() {
            System.out.println("Singleton instance working!");
        }
    }

    // Main method
    public static void main(String[] args) {

        Singleton obj1 = Singleton.getInstance();
        Singleton obj2 = Singleton.getInstance();

        obj1.showMessage();

        // Check if both references point to same instance
        if (obj1 == obj2) {
            System.out.println("Only ONE instance exists ✅");
        } else {
            System.out.println("Multiple instances exist ❌");
        }
    }
}
