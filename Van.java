package vehicleRentalService;

public class Van extends Vehicle {
    private String isFourWheelDrive;

    public Van(VehicleType vehicleType, double priceDay, String breand, String model, boolean isAvailability, String isFourWheelDrive) {
        super(vehicleType, priceDay, breand, model, isAvailability);
        this.isFourWheelDrive = isFourWheelDrive;
    }

    public String getIsFourWheelDrive() {
        return isFourWheelDrive;
    }

    public void setIsFourWheelDrive(String isFourWheelDrive) {
        this.isFourWheelDrive = isFourWheelDrive;
    }

    @Override
    public String toString() {
        return "Van{" +
                "vehicleId='" + super.getVehicleId() + '\'' +
                " breand='" + super.getBreand() + '\'' +
                " model='" + super.getModel() + '\'' +
                " rentalPrice='" + super.getRentalPrice() + '\'' +
                "isFourWheelDrive='" + isFourWheelDrive + '\'' +
                '}';
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("IsFourWheelDrive: " + isFourWheelDrive);
    }
}
