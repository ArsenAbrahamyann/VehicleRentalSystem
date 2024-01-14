package vehicleRentalService;

public enum VehicleType {
    CAR("Car"), BIKE("Bike"), VAN("Van");

    private String name;

    VehicleType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
