public class Service {

    private int id;
    private String name;
    private double firstHourPrice;
    private double additionalHourPrice;

    public Service(int id, String name, double firstHourPrice, double additionalHourPrice) {
        this.id = id;
        this.name = name;
        this.firstHourPrice = firstHourPrice;
        this.additionalHourPrice = additionalHourPrice;
    }
//All the required fields in Service
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getFirstHourPrice() {
        return firstHourPrice;
    }

    public double getAdditionalHourPrice() {
        return additionalHourPrice;
    }
}