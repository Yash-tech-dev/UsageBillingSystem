public class HourlyBillingCalculator implements BillingCalculator {

    @Override
    public double calculateAmount(long hours, Service service) {
        if (hours <= 0) {
            return 0;
        }

        if (hours == 1) {
            return service.getFirstHourPrice();
        }
// business logic for calculating amount according to hours
        return service.getFirstHourPrice() + (hours - 1) * service.getAdditionalHourPrice();
    }
}