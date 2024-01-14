package vehicleRentalService;

public class Car extends Vehicle {
    private String fuelType;
    private String isConvertible;
    private int numDoors;

    public Car(VehicleType vehicleType, double rentalPrice, String breand, String model, boolean isAvailability) {
        super(vehicleType, rentalPrice, breand, model, isAvailability);
    }

    public Car(VehicleType vehicleType, double priceDay, String breand, String model, boolean isAvailability, String fuelType, String isConvertible, int numDoors) {
        super(vehicleType, priceDay, breand, model, isAvailability);
        this.fuelType = fuelType;
        this.isConvertible = isConvertible;
        this.numDoors = numDoors;
    }


    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Number of Doors: " + numDoors);
        System.out.println("Convertible: " + isConvertible);
        System.out.println("Fuel Type: " + fuelType);
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public void setIsConvertible(String isConvertible) {
        this.isConvertible = isConvertible;
    }

    public void setNumDoors(int numDoors) {
        this.numDoors = numDoors;
    }

    public String getFuelType() {
        return fuelType;
    }

    public String getIsConvertible() {
        return isConvertible;
    }

    public int getNumDoors() {
        return numDoors;
    }


    @Override
    public String toString() {
        return "Car{" +
                "vehicleId='" + super.getVehicleId() + '\'' +
                " breand='" + super.getBreand() + '\'' +
                " model='" + super.getModel() + '\'' +
                " rentalPrice='" + super.getRentalPrice() + '\'' +
                " fuelType='" + fuelType + '\'' +
                ", isConvertible='" + isConvertible + '\'' +
                ", numDoors=" + numDoors +
                '}';
    }
}
