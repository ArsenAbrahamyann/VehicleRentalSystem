package vehicleRentalService;

public class Bike extends Vehicle{
    private String bikeType;

    public Bike(VehicleType vehicleType, double priceDay, String breand, String model, boolean isAvailability, String bikeType) {
        super(vehicleType,priceDay, breand, model, isAvailability);
        this.bikeType = bikeType;
    }

    public String getBikeType() {
        return bikeType;
    }

    public void setBikeType(String bikeType) {
        this.bikeType = bikeType;
    }

    @Override
    public String toString() {
        return "Bike{" +
                "vehicleId='" + super.getVehicleId() + '\'' +
                " breand='" + super.getBreand() + '\'' +
                " model='" + super.getModel() + '\'' +
                " rentalPrice='" + super.getRentalPrice() + '\'' +
                "bikeType='" + bikeType + '\'' +
                '}';
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("BikeType: " + bikeType);
    }
}
