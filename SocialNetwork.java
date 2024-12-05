import java.util.*;

public class SocialNetwork{
    private static Map<String, Set<String>> graph = new HashMap<>();

    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        int choice;

        System.out.println("Welcome to the Social Network Graph!");

        do{
            System.out.println("\nMenu:");
            System.out.println("1. Add User");
            System.out.println("2. Connect Users as Friends");
            System.out.println("3. Display Social Network");
            System.out.println("4. Check if Two Users are Connected");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch(choice){
                case 1:
                    addUser(scanner);
                    break;
                case 2:
                    connectUsers(scanner);
                    break;
                case 3:
                    displayNetwork();
                    break;
                case 4:
                    checkConnection(scanner);
                    break;
                case 5:
                    System.out.println("Exiting program. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 5);

        scanner.close();
    }

    private static void addUser(Scanner scanner) {
        System.out.print("Enter the username to add: ");
        String username = scanner.nextLine();
        if (graph.containsKey(username)) {
            System.out.println(username + " already exists in the network.");
        } else {
            graph.put(username, new HashSet<>());
            System.out.println(username + " has been added to the network.");
        }
    }

    private static void connectUsers(Scanner scanner) {
        System.out.print("Enter the first username: ");
        String user1 = scanner.nextLine();
        System.out.print("Enter the second username: ");
        String user2 = scanner.nextLine();

        if (!graph.containsKey(user1) || !graph.containsKey(user2)) {
            System.out.println("One or both users do not exist. Add them first.");
            return;
        }

        graph.get(user1).add(user2);
        graph.get(user2).add(user1);
        System.out.println(user1 + " and " + user2 + " are now friends.");
    }

    private static void displayNetwork() {
        System.out.println("\nSocial Network Graph:");
        for (String user : graph.keySet()) {
            System.out.println(user + " -> " + graph.get(user));
        }
    }

    private static void checkConnection(Scanner scanner) {
        System.out.print("Enter the first username: ");
        String user1 = scanner.nextLine();
        System.out.print("Enter the second username: ");
        String user2 = scanner.nextLine();

        if (!graph.containsKey(user1) || !graph.containsKey(user2)) {
            System.out.println("One or both users do not exist in the network.");
            return;
        }

        if (isConnected(user1, user2)) {
            System.out.println(user1 + " and " + user2 + " are connected.");
        } else {
            System.out.println(user1 + " and " + user2 + " are not connected.");
        }
    }

    private static boolean isConnected(String user1, String user2) {
        Set<String> visited = new HashSet<>();
        Queue<String> queue = new LinkedList<>();
        queue.add(user1);
        visited.add(user1);

        while (!queue.isEmpty()) {
            String current = queue.poll();
            if (current.equals(user2)) {
                return true;
            }

            for (String neighbor : graph.get(current)) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.add(neighbor);
                }
            }
        }

        return false;
    }
}