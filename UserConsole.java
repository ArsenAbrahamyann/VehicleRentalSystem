package vehicleRentalService;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class UserConsole {
    private static final String FILE_PATH = "data.ser";
    private boolean runApplication = true;

    private Scanner sc;
    private VehicleManagement vehicleManagement;
    private CustomerManagement customerManagement;
    private PricingAndBillingSystem pricingAndBillingSystem;
    private RentSystem rentSystem;
    private Customer currentCustomer = null;


    public UserConsole(VehicleManagement vehicleManagement, CustomerManagement customerManagement, PricingAndBillingSystem pricingAndBillingSystem, RentSystem rentSystem) {
        this.vehicleManagement = vehicleManagement;
        this.customerManagement = customerManagement;
        this.pricingAndBillingSystem = pricingAndBillingSystem;
        this.rentSystem = rentSystem;
        sc = new Scanner(System.in);
    }

    public void startConsole() {
        while (runApplication) {
            System.out.println("1. Rent a Vehicle");
            System.out.println("2. Return a Vehicle");
            System.out.println("3. Display Rental Information");
            System.out.println("4. Add transport");
            System.out.println("5. Delete transport");
            System.out.println("6. Update transport");
            System.out.println("7. Register customer");
            System.out.println("8. Exit");
            System.out.print("Enter your choice: ");
            System.out.println();
            String option = sc.nextLine();

            switch (option) {
                case "1" -> rentVehicle();
                case "2" -> returnVehicle();
                case "3" -> displayRentalInformation();
                case "4" -> addTransport();
                case "5" -> deleteTransport();
                case "6" -> updateTransport();
                case "7" -> registerCustomer();
                case "8" -> {
                    shoutDownApplication();
                    continue;
                }
                case "0" -> System.exit(0);
                default -> System.out.println("Invalid option, please enter valid option");
            }
//            sc.nextLine();
//            System.out.println("To Continue to main menu enter 1, to exist enter 0");

        }

    }

    private void shoutDownApplication() {
        System.out.println("GoodBy!");
        UserConsole.saveStateInFile(vehicleManagement.getVehicles(), customerManagement.getCustomers(), rentSystem.getRents());
        runApplication = false;
    }

    private void registerCustomer() {
        System.out.println("Enter FirstName: ");
        String name = sc.next();
        System.out.println("Enter LastName: ");
        String lastName = sc.next();
        System.out.println("Enter PhoneNumber: ");
        String phoneNumber = sc.next();
        System.out.println("Enter LicenseNumber: ");
        String licenseNumber = sc.next();

        Customer customer = new Customer(name, lastName, phoneNumber, licenseNumber);
        customerManagement.addCustomers(customer);
        currentCustomer = customer;


        System.out.println("Registered!");
    }

    private void updateTransport() {
        List<Vehicle> vehicles = vehicleManagement.getVehicles();
        if (!vehicles.isEmpty()) {
            System.out.println("Enter transport id from bellow list: ");
            for (Vehicle vehicle : vehicles) {
                System.out.println(vehicle);
            }
            String id = sc.next();
            Vehicle vehicleFound = null;
            for (Vehicle vehicle : vehicles) {
                if (vehicle.getVehicleId().equals(id)) {
                    vehicleFound = vehicle;
                }
            }
            if (vehicleFound != null) {
                System.out.println("Enter Rental price or 0 if you don't want to change.");
                double rentalPrice = 0;
                boolean bool = true;
                while (bool) {
                    try {
                        rentalPrice = sc.nextDouble();
                        sc.nextLine();
                        bool = false;
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid option, please enter valid option");
                        sc.nextLine();
                    }
                }
                if (rentalPrice > 0) {
                    vehicleFound.setRentalPrice(rentalPrice);
                    vehicleManagement.updateVehicle(vehicleFound);
                    System.out.println("Updated!");
                } else if (rentalPrice < 0) {
                    System.out.println("Invalid rental price.");
                }
                System.out.println("Not changed!");
            } else {
                System.out.println("Transport with id: " + id + " not fount.");
            }
        } else {
            System.out.println("Transports not found!");
        }
    }

    private void deleteTransport() {
        System.out.println("Enter transport id from bellow list: ");
        List<Vehicle> vehicles = vehicleManagement.getVehicles();
        for (Vehicle vehicle : vehicles) {
            System.out.println(vehicle);
        }
        String id = sc.next();
        Vehicle vehicleFound = null;
        for (Vehicle vehicle : vehicles) {
            if (vehicle.getVehicleId().equals(id)) {
                vehicleFound = vehicle;
            }
        }
        if (vehicleFound != null) {
            vehicleManagement.deleteVehicle(vehicleFound.getVehicleId());
            System.out.println("Deleted!");
        } else {
            System.out.println("Transport with id: " + id + " not found!");
        }
    }

    private void addTransport() {
        System.out.println("Enter breand:");
        String breand = sc.next();
        System.out.println("Enter model:");
        String model = sc.next();
        System.out.println("Enter rental price per day in $: ");
        double rentalPrice = 0;
        boolean bool = true;
        while (bool) {
            try {
                rentalPrice = sc.nextDouble();
                sc.nextLine();
                bool = false;
            } catch (InputMismatchException e) {
                System.out.println("Invalid option, please enter valid option");
                sc.nextLine();
            }
        }
        System.out.println("Select Type Vehicle:");
        System.out.println("1 -> Car\n2 -> Van\n3 -> Bike");
        int vehicleTypeOption = 0;
        bool = true;
        while (bool) {
            try {
                vehicleTypeOption = sc.nextInt();
                sc.nextLine();
                bool = false;
            } catch (InputMismatchException e) {
                System.out.println("Invalid option, please enter valid option");
                sc.nextLine();
            }
        }
        VehicleType vehicleType = VehicleType.CAR;
        Vehicle vehicle;
        switch (vehicleTypeOption) {
            case 2 -> {
                vehicleType = VehicleType.VAN;
                vehicle = new Van(vehicleType, rentalPrice, breand, model, true, "Yes");
            }
            case 3 -> {
                vehicleType = VehicleType.BIKE;
                vehicle = new Bike(vehicleType, rentalPrice, breand, model, true, "Turbo");
            }
            default -> {
                System.out.println("Invalid option, will be used Car by default!");
                vehicle = new Car(vehicleType, rentalPrice, breand, model, true, "GAS", "YES", 5);
            }
        }
        vehicleManagement.addVehicle(vehicle);
        System.out.println("Added!");
    }

    private void displayRentalInformation() {
        System.out.println("Enter transport id from bellow list: ");
        List<Vehicle> vehicles = vehicleManagement.getVehicles();
        for (Vehicle vehicle : vehicles) {
            System.out.println(vehicle);
        }
        String id = sc.next();
        Vehicle vehicleFound = null;
        for (Vehicle vehicle : vehicles) {
            if (vehicle.getVehicleId().equals(id)) {
                vehicleFound = vehicle;
            }
        }
        if (vehicleFound != null) {
            List<Rent> rents = new ArrayList<>(rentSystem.getRentalHistoryByVehicleId(vehicleFound.getVehicleId()));
            if (!rents.isEmpty()) {
                for (Rent rent : rents) {
                    System.out.println(rent);
                }
            } else {
                System.out.println("Not Found!");
            }
        } else {
            System.out.println("Not found!");
        }
    }

    private void rentVehicle() {
        if (currentCustomer == null) {
            System.out.println("Action not allowed, Please register first!");
        } else {
            System.out.println("Select Type Vehicle:");
            System.out.println("1 -> Car\n2 -> Van\n3 -> Bike\n4(or in case of Invalid option) -> All types");
            List<Vehicle> vehicles = new ArrayList<>();
            int option = 0;
            boolean bool = true;
            while (bool) {
                try {
                    option = sc.nextInt();
                    sc.nextLine();
                    bool = false;
                } catch (InputMismatchException e) {
                    System.out.println("Invalid option, please enter valid option");
                    sc.nextLine();
                }
            }
            switch (option) {
                case 1 ->
                        vehicles = vehicleManagement.getVehicles().stream().filter(vehicle -> vehicle.getVehicleType().getName().equals("Car") && vehicle.isAvailability()).collect(Collectors.toList());
                case 2 ->
                        vehicles = vehicleManagement.getVehicles().stream().filter(vehicle -> vehicle.getVehicleType().getName().equals("Van") && vehicle.isAvailability()).collect(Collectors.toList());
                case 3 ->
                        vehicles = vehicleManagement.getVehicles().stream().filter(vehicle -> vehicle.getVehicleType().getName().equals("Bike") && vehicle.isAvailability()).collect(Collectors.toList());
                default ->
                        vehicles = vehicleManagement.getVehicles().stream().filter(Vehicle::isAvailability).collect(Collectors.toList());
            }

            if (!vehicles.isEmpty()) {
                for (Vehicle vehicle : vehicles) {
                    System.out.println(vehicle);
                }
                System.out.println("Enter transport id from bellow list: ");
                String id = sc.next();
                Vehicle vehicleFound = null;
                for (Vehicle vehicle : vehicles) {
                    if (vehicle.getVehicleId().equals(id)) {
                        vehicleFound = vehicle;
                    }
                }
                System.out.println("Please enter rental period in days:");
                int rentalPeriod = 0;
                 bool = true;
                while (bool) {
                    try {
                        rentalPeriod = sc.nextInt();
                        sc.nextLine();
                        bool = false;
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid option, please enter valid option");
                        sc.nextLine();
                    }
                }

                if (vehicleFound != null && rentalPeriod != 0) {
                    rentSystem.rentVehicle(vehicleFound, currentCustomer, rentalPeriod);
                    System.out.println("Rented");
                } else {
                    System.out.println("Something went wrong!");
                }
            } else {
                System.out.println("Vehicles not found, please add vehicles!");
            }
        }
    }

    private void returnVehicle() {
        if (currentCustomer == null) {
            System.out.println("Action not allowed, Please register first!");
        } else {
            List<Rent> rents = rentSystem.getRents().stream()
                    .filter(rent -> rent.getCustomerName().equals(currentCustomer.getName())).toList();
            List<Vehicle> vehicles = vehicleManagement.getVehicles().stream()
                    .filter(vehicle -> !vehicle.isAvailability() && rents.stream()
                            .anyMatch(rent -> rent.getVehicleID().equals(vehicle.getVehicleId()))).toList();
            if (!vehicles.isEmpty()) {
                System.out.println("Enter transport id from bellow list: ");
                for (Vehicle vehicle : vehicles) {
                    System.out.println(vehicle);
                }
                String id = sc.next();
                Vehicle vehicleFound = null;
                for (Vehicle vehicle : vehicles) {
                    if (vehicle.getVehicleId().equals(id)) {
                        vehicleFound = vehicle;
                    }
                }
                if (vehicleFound != null) {
                    rentSystem.returnVehicleByVehicleId(vehicleFound.getVehicleId());
                    System.out.println("Returned!");
                } else {
                    System.out.println("Something went wrong!");
                }
            } else {
                System.out.println("Transports not found!");
            }

        }
    }

    public static void createDataFileIfNotExists() {
        File file = new File(FILE_PATH);
        try {
            file.createNewFile();
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void saveStateInFile(List<Vehicle> vehicles, List<Customer> customers, List<Rent> rents) {
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(FILE_PATH, true))) {
            objectOutputStream.writeObject(vehicles);
            objectOutputStream.writeObject(customers);
            objectOutputStream.writeObject(rents);
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static Map<String, List<?>> loadStateFromFile() {
        Map<String, List<?>> data = new HashMap<>();
        File dataFile = new File(FILE_PATH);
        if (dataFile.exists() && dataFile.length() > 0) {
            try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(FILE_PATH))) {
                List<Vehicle> vehicles = (List<Vehicle>) objectInputStream.readObject();
                List<Customer> customers = (List<Customer>) objectInputStream.readObject();
                List<Rent> rents = (List<Rent>) objectInputStream.readObject();
                data.put("vehicles", vehicles);
                data.put("customers", customers);
                data.put("rents", rents);
            } catch (IOException | ClassNotFoundException e) {
                System.out.println("Error: " + e.getMessage());
            }
        } else {
            data.put("vehicles", new ArrayList<>());
            data.put("customers", new ArrayList<>());
            data.put("rents", new ArrayList<>());
        }
        return data;
    }
}
