public class MyMain {

    public static void main(String[] args) {

        // Singleton Pattern Demo
        System.out.println("=== Singleton Pattern ===");
        MySingleton obj1 = MySingleton.getInstance();
        MySingleton obj2 = MySingleton.getInstance();

        obj1.showMessage();
        System.out.println("Same instance? " + (obj1 == obj2));

        // Builder Pattern Demo
        System.out.println("\n=== Builder Pattern ===");
        
        // Building a basic computer with only required parameters
        MyBuilder.Computer basicComputer = new MyBuilder.Computer.Builder("Intel i3", "8GB").build();
        System.out.println("Basic Computer: " + basicComputer);

        // Building a gaming computer with all optional parameters
        MyBuilder.Computer gamingComputer = new MyBuilder.Computer.Builder("Intel i9", "32GB")
                .setGraphicsCardEnabled(true)
                .setBluetoothEnabled(true)
                .setStorageType("SSD")
                .setStorageCapacity(1024)
                .build();
        System.out.println("Gaming Computer: " + gamingComputer);

        // Building a mid-range computer with some optional parameters
        MyBuilder.Computer midRangeComputer = new MyBuilder.Computer.Builder("AMD Ryzen 5", "16GB")
                .setStorageType("SSD")
                .setStorageCapacity(512)
                .setBluetoothEnabled(true)
                .build();
        System.out.println("Mid-range Computer: " + midRangeComputer);
    }
}
