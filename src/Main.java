//

// Method 1-> (mannual Console Method) ->used switch concept with cases to use this system

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Manager object to handle usage tracking, capacity limits, and billing calculations
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

                // Step 2: Prompt resource selection
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

                // Start usage session (Manager handles capacity availability check)
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

                // Option 6: Display all currently active  usage resources
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


        scanner.close();
    }
}




// another method to use with API endpoints
//
//import com.sun.net.httpserver.HttpServer;
//import com.sun.net.httpserver.HttpExchange;
//import java.io.InputStream;
//import java.io.OutputStream;
//import java.io.IOException;
//import java.net.InetSocketAddress;
//import java.util.ArrayList;
//import java.util.List;
//
//public class Main {
//
//    private static UsageManager manager = new UsageManager();
//    private static List <User>users = new ArrayList<>();
//    private static List <Resource>resources = new ArrayList<>();
//    private static List <Service>services = new ArrayList<>();
//
//    public static void main(String[] args) throws IOException {
//        // Initial Sample Data Setup
//        users.add(new User(1, "Yash"));
//        users.add(new User(2, "Rahul"));
//        users.add(new User(3, "Aman"));
//
//        resources.add(new Resource("R1", "Meeting Room", 2));
//        resources.add(new Resource("R2", "Conference Hall", 1));
//        resources.add(new Resource("r3", "GYM", 5));
//        resources.add(new Resource("R4", "Parking Area", 4));
//
//        services.add(new Service(1, "Hourly Service", 30, 10));
//
//        // Create HTTP Server at http://localhost:8080/
//        HttpServer server = HttpServer.create(new InetSocketAddress(8091), 0);
//
//        // ---------------- API ENDPOINTS ----------------
//
//        // 1. Get All Users: GET http://localhost:8080/users
//        server.createContext("/users", exchange -> {
//            String response = users.toString();
//            sendJsonResponse(exchange, response);
//        });
//
//        // 2. Get All Resources: GET http://localhost:8080/resources
//        server.createContext("/resources", exchange -> {
//            String response = resources.toString();
//            sendJsonResponse(exchange, response);
//        });
//
//        // 3. Start Usage: POST http://localhost:8080/start?userId=1&resIndex=0
//        server.createContext("/start", exchange -> {
//            if ("POST".equals(exchange.getRequestMethod())) {
//                String query = exchange.getRequestURI().getQuery(); // \
//                int userId = Integer.parseInt(getParam(query, "userId"));
//                int resIndex = Integer.parseInt(getParam(query, "resIndex"));
//
//                User user = users.get(userId - 1);
//                Resource resource = resources.get(resIndex);
//
//                Usage usage = manager.startUsage(user, resource, services.get(0));
//
//                if (usage != null) {
//                    sendJsonResponse(exchange, "Usage Started Successfully! Usage ID: " + usage.getId());
//                } else {
//                    sendJsonResponse(exchange, "REJECTED: " + resource.getName() + " is FULL!");
//                }
//            }
//        });
//
//        // 4. Stop Usage: POST http://localhost:8080/stop?usageId=1&hours=2
//        server.createContext("/stop", exchange -> {
//            if ("POST".equals(exchange.getRequestMethod())) {
//                String query = exchange.getRequestURI().getQuery();
//                int usageId = Integer.parseInt(getParam(query, "usageId"));
//                long hours = Long.parseLong(getParam(query, "hours"));
//
//                Bill bill = manager.stopUsage(usageId, hours);
//
//                if (bill != null) {
//                    sendJsonResponse(exchange, "Bill Generated: Total Amount = ₹" + bill.getAmount());
//                } else {
//                    sendJsonResponse(exchange, "ERROR: Invalid Usage ID");
//                }
//            }
//        });
//
//        // 5. Get Bills: GET http://localhost:8080/bills
//        server.createContext("/bills", exchange -> {
//            sendJsonResponse(exchange, "Check Console/Terminal for all generated bills.");
//            manager.showAllBills();
//        });
//
//        server.start();
//        System.out.println(" Server active at: http://localhost:8091");
//    }
//
//    // Helper method to send text response
//    private static void sendJsonResponse(HttpExchange exchange, String response) throws IOException {
//        exchange.sendResponseHeaders(200, response.getBytes().length);
//        OutputStream os = exchange.getResponseBody();
//        os.write(response.getBytes());
//        os.close();
//    }
//
//    // Simple helper to read parameters from URL (e.g. ?userId=1&resIndex=0)
//    private static String getParam(String query, String paramName) {
//        for (String pair : query.split("&")) {
//            String[] kv = pair.split("=");
//            if (kv[0].equals(paramName)) return kv[1];
//        }
//        return "0";
//    }
//}