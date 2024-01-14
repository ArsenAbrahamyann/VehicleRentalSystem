package vehicleRentalService;

import java.util.List;
import java.util.Map;

public class Application {
    public static void main(String[] args) {
        UserConsole.createDataFileIfNotExists();
        Map<String, List<?>> data = UserConsole.loadStateFromFile();
        VehicleManagement vehicleManagement = new VehicleManagement((List<Vehicle>) data.get("vehicles"));
        CustomerManagement customerManagement = new CustomerManagement((List<Customer>) data.get("customers"));
        RentSystem rentSystem = new RentSystem((List<Rent>) data.get("rents"));
        UserConsole userConsole = new UserConsole(vehicleManagement, customerManagement, new PricingAndBillingSystem(), rentSystem);
        userConsole.startConsole();
    }
}
