package vehicleRentalService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RentSystem {
    private List<Rent> rents;
    private final VehicleManagement vehicleManagement = new VehicleManagement();
    private final PricingAndBillingSystem pricingAndBillingSystem = new PricingAndBillingSystem();

    public RentSystem() {
        this.rents = new ArrayList<>();
    }

    public RentSystem(List<Rent> rents) {
        this.rents = rents;
    }

    public List<Vehicle> getAllAvailableVehiclesForRent() {
        return vehicleManagement.getVehicles().stream()
                .filter(Vehicle::isAvailability)
                .collect(Collectors.toList());
    }

    public List<Vehicle> getAllAvailableVehiclesForRentByType(String type) {
        return vehicleManagement.getVehiclesByType(type).stream()
                .filter(Vehicle::isAvailability)
                .collect(Collectors.toList());
    }

    public void rentVehicle(Vehicle vehicle, Customer customer, int rentalPeriod) {
        Rent rent = new Rent(vehicle.getVehicleId(), customer.getName(), pricingAndBillingSystem.adjustTheRentalPrice(rentalPeriod, vehicle.getRentalPrice()), rentalPeriod);
        rents.add(rent);
        vehicle.setAvailability(false);
    }

    public void returnVehicleByVehicleId(String vehicleId) {
        Rent rentToRemove = null;
        for (Rent rent : rents) {
            if (rent.getVehicleID().equals(vehicleId)) {
                rentToRemove = rent;
            }
        }
        if (rentToRemove != null) {
            Vehicle vehicle = vehicleManagement.getVehicleById(vehicleId);
            if (vehicle != null) {
                vehicle.setAvailability(true);
            }
        }
    }

    public List<Rent> getRentalHistoryByVehicleId(String vehicleId) {
        return rents.stream()
                .filter(rent -> rent.getVehicleID().equals(vehicleId))
                .collect(Collectors.toList());
    }

    public List<Vehicle> getRentedVehicleByCustomerName(String customerName) {
        List<Rent> rentList = rents.stream()
                .filter(rent -> rent.getCustomerName().equals(customerName)).toList();
        return vehicleManagement.getVehicles().stream()
                .filter(vehicle -> !vehicle.isAvailability() && rentList.stream().anyMatch(rent -> rent.getVehicleID().equals(vehicle.getVehicleId())))
                .collect(Collectors.toList());

    }

    public List<Rent> getRents() {
        return rents;
    }
}
