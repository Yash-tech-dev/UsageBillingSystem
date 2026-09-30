public class Bill {

    private int id;
    private Usage usage;
    private long billableHours;
    private double amount;

    public Bill(int id, Usage usage, long billableHours, double amount) {
        this.id = id;
        this.usage = usage;
        this.billableHours = billableHours;
        this.amount = amount;
    }
  //  All the required fields in Bill
    public int getId() {
        return id;
    }

    public Usage getUsage() {
        return usage;
    }

    public long getBillableHours() {
        return billableHours;
    }

    public double getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return "Bill ID: " + id
                + ", User: " + usage.getUser().getName()
                + ", Resource: " + usage.getResource().getName()
                + ", Hours: " + billableHours
                + ", Amount: ₹" + amount;
    }
}