package vehicleRentalService;

import java.io.Serial;
import java.io.Serializable;

class Rent implements Serializable {
    @Serial
    private static final long serialVersionUID = -4257253755983035706L;
    private String vehicleID;
    private String customerName;
    private double totalCost;
    private int rentalPeriod;

    public Rent(String vehicleID, String customerName, double totalCost, int rentalPeriod) {
        this.vehicleID = vehicleID;
        this.customerName = customerName;
        this.totalCost = totalCost;
        this.rentalPeriod = rentalPeriod;
    }

    public String getVehicleID() {
        return vehicleID;
    }

    public void setVehicleID(String vehicleID) {
        this.vehicleID = vehicleID;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    public int getRentalPeriod() {
        return rentalPeriod;
    }

    public void setRentalPeriod(int rentalPeriod) {
        this.rentalPeriod = rentalPeriod;
    }

    @Override
    public String toString() {
        return "Rent{" +
                "vehicleID='" + vehicleID + '\'' +
                ", customerName='" + customerName + '\'' +
                ", totalCost=" + totalCost +
                ", rentalPeriod=" + rentalPeriod +
                '}';
    }
}
