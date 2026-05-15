public class MyFactory {
    
    // Product Interface
    interface Vehicle {
        void drive();
    }
    
    // Concrete Products
    static class Car implements Vehicle {
        @Override
        public void drive() {
            System.out.println("Driving a Car 🚗");
        }
    }
    
    static class Bike implements Vehicle {
        @Override
        public void drive() {
            System.out.println("Riding a Bike 🚲");
        }
    }
    
    static class Truck implements Vehicle {
        @Override
        public void drive() {
            System.out.println("Driving a Truck 🚚");
        }
    }
    
    // Factory Class
    static class VehicleFactory {
        public Vehicle createVehicle(String type) {
            if (type == null || type.isEmpty()) {
                return null;
            }
            
            switch (type.toLowerCase()) {
                case "car":
                    return new Car();
                case "bike":
                    return new Bike();
                case "truck":
                    return new Truck();
                default:
                    throw new IllegalArgumentException("Unknown vehicle type: " + type);
            }
        }
    }
    
    public static void Start() {
        System.out.println("\n=== Factory Pattern Demo ===");
        
        VehicleFactory factory = new VehicleFactory();
        
        // Create different vehicles using the factory
        Vehicle car = factory.createVehicle("car");
        car.drive();
        
        Vehicle bike = factory.createVehicle("bike");
        bike.drive();
        
        Vehicle truck = factory.createVehicle("truck");
        truck.drive();
        
        // Try invalid type
        try {
            Vehicle unknown = factory.createVehicle("plane");
            unknown.drive();
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
        
        System.out.println("Factory Pattern: Encapsulates object creation logic");
    }
}
