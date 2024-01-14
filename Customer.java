package vehicleRentalService;

import java.io.Serial;
import java.io.Serializable;

public class Customer implements Serializable {
    @Serial
    private static final long serialVersionUID = -3953960963348734985L;
    private String name;
    private String lastName;
    private String contactInformation;
    private String driverLicenseNumber;

    public String getContactInformation() {
        return contactInformation;
    }

    public String getDriverLicenseNumber() {
        return driverLicenseNumber;
    }

    public Customer(String name, String lastName, String contactInformation, String driverLicenseNumber) {
        this.name = name;
        this.lastName = lastName;
        this.contactInformation = contactInformation;
        this.driverLicenseNumber = driverLicenseNumber;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setContactInformation(String contactInformation) {
        this.contactInformation = contactInformation;
    }

    public void setDriverLicenseNumber(String driverLicenseNumber) {
        this.driverLicenseNumber = driverLicenseNumber;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", contactInformaition='" + contactInformation + '\'' +
                ", driverslicenseNumber='" + driverLicenseNumber + '\'' +
                '}';
    }
}
