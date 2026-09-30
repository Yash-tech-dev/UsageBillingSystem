import java.time.LocalDateTime;

public class Usage {

    private int id;
    private User user;
    private Resource resource;
    private Service service;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    public Usage(int id, User user, Resource resource, Service service) {
        this.id = id;
        this.user = user;
        this.resource = resource;
        this.service = service;
        this.startTime = LocalDateTime.now();
    }
    // All the required fields in Usage
    public int getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public Resource getResource() {
        return resource;
    }

    public Service getService() {
        return service;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void stopUsage() {
        this.endTime = LocalDateTime.now();
        this.resource.decrementUsage();  // here after stopping the use of resource we are decrementing the usage
    }

    public boolean isActive() {
        return endTime == null;
    }
}