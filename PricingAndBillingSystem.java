package vehicleRentalService;

public class PricingAndBillingSystem {

    public double adjustTheRentalPrice(int rentalPeriod, double costPerDay) {
        if (rentalPeriod == 1) {
            return costPerDay;
        } else {
            double dailyDiscountPercentage = 1.25;
            double actualDiscount = rentalPeriod * dailyDiscountPercentage;
            double costWithoutDiscount = rentalPeriod * costPerDay;
            return costWithoutDiscount - (costWithoutDiscount * actualDiscount / 100);
        }
    }
}
