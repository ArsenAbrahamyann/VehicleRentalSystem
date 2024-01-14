package vehicleRentalService;

import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;

public class Vehicle implements Serializable {
    @Serial
    private static final long serialVersionUID = -6111756114159340931L;
    private String vehicleId;
    private VehicleType vehicleType;
    private String breand;
    private String model;
    private boolean isAvailability;
    private double rentalPrice;

    public Vehicle(VehicleType vehicleType, double rentalPrice, String breand, String model, boolean isAvailability) {
        this.vehicleId = UUID.randomUUID().toString();
        this.vehicleType = vehicleType;
        this.breand = breand;
        this.model = model;
        this.isAvailability = isAvailability;
        this.rentalPrice = rentalPrice;
    }
    public void displayInfo() {
        System.out.println("VehicleID: " + vehicleId);
        System.out.println("VehicleType: " + vehicleType);
        System.out.println("PriceDay: " + rentalPrice + "$");
        System.out.println("Breand: " + breand);
        System.out.println("Model: " + model);
        System.out.println("IsAvailability: " + isAvailability);
    }

    public boolean isAvailability() {
        return isAvailability;
    }

    public double getRentalPrice() {
        return rentalPrice;
    }

    public String getVehicleId() {
        return vehicleId;
    }
    public VehicleType getVehicleType() {
        return vehicleType;
    }
    public String getBreand() {
        return breand;
    }
    public String getModel() {
        return model;
    }

    public void setVehicleId(String vehicleId) {
        this.vehicleId = vehicleId;
    }

    public void setVehicleType(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }

    public void setBreand(String breand) {
        this.breand = breand;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setAvailability(boolean availability) {
        isAvailability = availability;
    }

    public void setRentalPrice(double rentalPrice) {
        this.rentalPrice = rentalPrice;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "vehicleId=" + vehicleId +
                ", vehicleType=" + vehicleType +
                ", breand='" + breand + '\'' +
                ", model='" + model + '\'' +
                ", isAvailability=" + isAvailability +
                ", priceDay=" + rentalPrice +
                '}';
    }
}

