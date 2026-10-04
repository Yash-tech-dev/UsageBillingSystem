//

// Method 1-> (mannual Console Method) ->used switch concept with cases to use this system

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);


        UsageManager manager = new UsageManager();
// already existed user but we can aslo add new user
        List <User>users = new ArrayList<>();
        users.add(new User(1, "Yash"));
        users.add(new User(2, "Rahul"));
        users.add(new User(3, "Aman"));
//all the available resources in system

        List <Resource>resources = new ArrayList<>();
        resources.add(new Resource("R1", "Meeting Room", 2));
        resources.add(new Resource("R2", "Conference Hall", 1));
        resources.add(new Resource("r3", "GYM", 5));
        resources.add(new Resource("R4", "Parking Area", 4));

        // Initialize pricing model services (e.g., Hourly rate structure)
        List <Service> services = new ArrayList<>();
        services.add(new Service(1, "Hourly Service", 30, 10));
        while (true) {
            System.out.println("\n--- USAGE & BILLING SYSTEM ---");
            System.out.println("1. Add User");
            System.out.println("2. View Users");
            System.out.println("3. View Resources");
            System.out.println("4. Start Usage");
            System.out.println("5. Stop Usage (Generate Bill)");
            System.out.println("6. Show Active Usages");
            System.out.println("7. Show Bills");
            System.out.println("8. Exit");
            System.out.print("Enter choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            // Option 1: Register a new user in the system
            if (choice == 1) {
                System.out.print("Enter User Name: ");
                String name = scanner.nextLine();
                int id = users.size() + 1; // Auto-generate next user ID
                users.add(new User(id, name));
                System.out.println("User created with ID: " + id);

                // Option 2: Display list of all registered users
            } else if (choice == 2) {
                System.out.println("\n--- Users ---");
                for (User u : users) {
                    u.displayUser();
                }

                // Option 3: Display all system resources and their capacity status
            } else if (choice == 3) {
                System.out.println("\n--- Resources ---");
                for (Resource r : resources) {
                    System.out.println(r);
                }

                // Option 4: Initiate a new resource usage session
            } else if (choice == 4) {
                // Step 1: Prompt user selection
                System.out.println("\nSelect User ID:");
                for (User u : users) {
                    System.out.println(u.getId() + ". " + u.getName());
                }
                int userId = scanner.nextInt();

                // Find user object matching the selected ID
                User selectedUser = null;
                for (User u : users) {
                    if (u.getId() == userId) {
                        selectedUser = u;
                        break;
                    }
                }

                // Validation check for invalid user selection
                if (selectedUser == null) {
                    System.out.println("Invalid User ID.");
                    continue;
                }

                // Step 2: To Check all Resources
                System.out.println("\nSelect Resource (1 to " + resources.size() + "):");
                for (int i = 0; i < resources.size(); i++) {
                    System.out.println((i + 1) + ". " + resources.get(i).getName());
                }
                int resChoice = scanner.nextInt();

                // Validation check for invalid resource selection
                if (resChoice < 1 || resChoice > resources.size()) {
                    System.out.println("Invalid Resource selection.");
                    continue;
                }
                Resource selectedResource = resources.get(resChoice - 1);

                // Start usage session
                manager.startUsage(selectedUser, selectedResource, services.get(0));

                // Option 5: It will End active usage session, release slot, and generate bill
            } else if (choice == 5) {
                manager.showActiveUsages();

                System.out.print("Enter Usage ID to stop: ");
                int usageId = scanner.nextInt();

                System.out.print("Enter Usage Duration in Hours (e.g., 2): ");
                long hours = scanner.nextLong();

                // Process stopping of usage and compute billing amount
                manager.stopUsage(usageId, hours);

                // Option 6 : display all currently active  usage resources
            } else if (choice == 6) {
                manager.showActiveUsages();

                // Option 7: Display list of all previously generated bills
            } else if (choice == 7) {
                manager.showAllBills();

                // Option 8: Terminate application execution
            } else if (choice == 8) {
                System.out.println("Exiting system...");
                break;

            } else {
                System.out.println("Invalid choice, try again.");
            }
        }



    }
}


