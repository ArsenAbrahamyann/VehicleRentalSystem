package vehicleRentalService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class VehicleManagement {
    private List<Vehicle> vehicles;

    public VehicleManagement() {
        this.vehicles = new ArrayList<>();
    }

    public VehicleManagement(List<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }

    public void addVehicle(Vehicle vehicle) {
        vehicles.add(vehicle);
    }

    public void deleteVehicle(String id) {
        Vehicle vehicleToRemove = null;
        for (Vehicle vehicle : vehicles) {
            if (vehicle.getVehicleId().equals(id)) {
                vehicleToRemove = vehicle;
            }
        }
        if (vehicleToRemove != null) {
            vehicles.remove(vehicleToRemove);
        }
    }

    public void updateVehicle(Vehicle vehicle) {
        for (Vehicle v : vehicles) {
            if (v.getVehicleId().equals(vehicle.getVehicleId())) {
                v.setVehicleType(vehicle.getVehicleType());
                v.setAvailability(vehicle.isAvailability());
                v.setModel(vehicle.getModel());
                v.setBreand(vehicle.getBreand());
                v.setRentalPrice(vehicle.getRentalPrice());
            }
        }
    }

    public List<Vehicle> getVehicles() {
        return vehicles;
    }

    public List<Vehicle> getVehiclesByType(String type) {
        return vehicles.stream()
                .filter(vehicle -> vehicle.getVehicleType().getName().equals(type))
                .collect(Collectors.toList());
    }

    public Vehicle getVehicleById(String vehicleId) {
        return vehicles.stream()
                .filter(vehicle -> vehicle.getVehicleId().equals(vehicleId))
                .findFirst().orElse(null);
    }
}
