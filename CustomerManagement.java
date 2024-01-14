package vehicleRentalService;

import java.util.ArrayList;
import java.util.List;

public class CustomerManagement {
    List<Customer> customers;

    public CustomerManagement() {
        this.customers = new ArrayList<>();
    }

    public CustomerManagement(List<Customer> customers) {
        this.customers = customers;
    }

    public void addCustomers(Customer customer) {
        this.customers.add(customer);
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public Customer getCustomerByLicenseId(String licenseId) {
        for (Customer customer : customers) {
            if (customer.getDriverLicenseNumber().equals(licenseId)) {
                return customer;
            }
        }
        return null;
    }
}
