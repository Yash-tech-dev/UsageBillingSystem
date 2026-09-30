import java.time.Duration;
import java.util.ArrayList;
import java.util.List;



public class UsageManager {

    private List <Usage> usages = new ArrayList<> ();
    private List <Bill> bills = new ArrayList<>();
    private BillingCalculator billingCalculator = new HourlyBillingCalculator();

    private int nextUsageId = 1;
    private int nextBillId = 1;
 //Method to define to start use of resource
    public Usage startUsage(User user, Resource resource, Service service) {
        if (!resource.incrementUsage()) {
            System.out.println("Usage rejected: " + resource.getName() + " is full.");
            return null;
        }

        Usage usage = new Usage(nextUsageId++, user, resource, service);
        usages.add(usage);

        System.out.println(user.getName() + " started using " + resource.getName());
        return usage;
    }
    //  //Method to define to stop use of resource
    public Bill stopUsage(int usageId, long billableHours) {
        Usage usage = findUsage(usageId);
        if (usage == null) {
            System.out.println("Usage not found for ID: " + usageId);
            return null;
        }
        if (!usage.isActive()) {
            System.out.println("This usage is already stopped.");
            return null;
        }
        // Stop usage and release slot
        usage.stopUsage();
        // Directly calculating amount using provided hours
        double amount = billingCalculator.calculateAmount(billableHours, usage.getService());

        Bill bill = new Bill(nextBillId++, usage, billableHours, amount);
        bills.add(bill);

        System.out.println("Usage stopped successfully.");
        System.out.println(bill);

        return bill;
    }

    private Usage findUsage(int usageId) {
        for (Usage u: usages) {
            if (u.getId() == usageId) {
                return u;
            }}
        return null;
    }

    //Method to show all active users
    public void showActiveUsages() {
        System.out.println("\n--- Active Usages ---");
        boolean found = false;

        for (Usage u : usages) {
            if (u.isActive()) {
                found = true;
                System.out.println("Usage ID: " + u.getId() + " | User: " + u.getUser().getName() + " | Resource: " + u.getResource().getName());
            }
        }
        if (!found) {
            System.out.println("No active usages.");
        }
    }

    //Method to show all active bills of users

    public void showAllBills() {
        System.out.println("\n--- Generated Bills -----");
        if (bills.isEmpty()) {
            System.out.println("No bills generated yet.");
            return;
        }
        for (Bill b : bills) {
            System.out.println(b);
        }
    }
}