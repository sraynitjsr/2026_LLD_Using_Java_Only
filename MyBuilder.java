public class MyBuilder {

    // Product class
    public static class Computer {
        // Required parameters
        private final String CPU;
        private final String RAM;

        // Optional parameters
        private final boolean isGraphicsCardEnabled;
        private final boolean isBluetoothEnabled;
        private final String storageType;
        private final int storageCapacity;

        private Computer(Builder builder) {
            this.CPU = builder.CPU;
            this.RAM = builder.RAM;
            this.isGraphicsCardEnabled = builder.isGraphicsCardEnabled;
            this.isBluetoothEnabled = builder.isBluetoothEnabled;
            this.storageType = builder.storageType;
            this.storageCapacity = builder.storageCapacity;
        }

        @Override
        public String toString() {
            return "Computer [CPU=" + CPU + ", RAM=" + RAM + 
                   ", Graphics Card=" + isGraphicsCardEnabled + 
                   ", Bluetooth=" + isBluetoothEnabled + 
                   ", Storage=" + storageType + " " + storageCapacity + "GB]";
        }

        // Builder class
        public static class Builder {
            // Required parameters
            private final String CPU;
            private final String RAM;

            // Optional parameters - initialized to default values
            private boolean isGraphicsCardEnabled = false;
            private boolean isBluetoothEnabled = false;
            private String storageType = "HDD";
            private int storageCapacity = 500;

            public Builder(String CPU, String RAM) {
                this.CPU = CPU;
                this.RAM = RAM;
            }

            public Builder setGraphicsCardEnabled(boolean enabled) {
                this.isGraphicsCardEnabled = enabled;
                return this;
            }

            public Builder setBluetoothEnabled(boolean enabled) {
                this.isBluetoothEnabled = enabled;
                return this;
            }

            public Builder setStorageType(String storageType) {
                this.storageType = storageType;
                return this;
            }

            public Builder setStorageCapacity(int capacity) {
                this.storageCapacity = capacity;
                return this;
            }

            public Computer build() {
                return new Computer(this);
            }
        }
    }

    public static void main(String[] args) {
        // Building a basic computer with only required parameters
        Computer basicComputer = new Computer.Builder("Intel i3", "8GB").build();
        System.out.println("Basic Computer: " + basicComputer);

        // Building a gaming computer with all optional parameters
        Computer gamingComputer = new Computer.Builder("Intel i9", "32GB")
                .setGraphicsCardEnabled(true)
                .setBluetoothEnabled(true)
                .setStorageType("SSD")
                .setStorageCapacity(1024)
                .build();
        System.out.println("Gaming Computer: " + gamingComputer);

        // Building a mid-range computer with some optional parameters
        Computer midRangeComputer = new Computer.Builder("AMD Ryzen 5", "16GB")
                .setStorageType("SSD")
                .setStorageCapacity(512)
                .setBluetoothEnabled(true)
                .build();
        System.out.println("Mid-range Computer: " + midRangeComputer);
    }
}
