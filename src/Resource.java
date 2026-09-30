public class Resource {

    private String resourceId;
    private String name;
    private int maxCapacity;
    private int activeCount;

    public Resource(String resourceId, String name, int maxCapacity) {
        this.resourceId = resourceId;
        this.name = name;
        this.maxCapacity = maxCapacity;
        this.activeCount = 0;
    }
    // All the required fields in Resource
    public String getResourceId() {
        return resourceId;
    }

    public String getName() {
        return name;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public int getActiveCount() {
        return activeCount;
    }

    public boolean isAvailable() {
        return activeCount < maxCapacity;
    }

    public boolean incrementUsage() {
        if (isAvailable()) {
            activeCount++;
            return true;
        }
        return false;
    }

    public void decrementUsage() {
        if (activeCount > 0) {
            activeCount--;
        }
    }

    @Override
    public String toString() {
        return "Resource{ID='" + resourceId + "', Name='" + name +
                "', Capacity=" + activeCount + "/" + maxCapacity + "}";
    }
}