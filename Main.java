import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        SnippetManager manager = new SnippetManager();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- SnipSync ---");
            System.out.println("1. Add Snippet");
            System.out.println("2. View All");
            System.out.println("3. Search");
            System.out.println("4. Delete");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");

            int choice = Integer.parseInt(sc.nextLine());

            switch (choice) {
                case 1 -> {
                    System.out.print("Title: ");
                    String title = sc.nextLine();
                    System.out.print("Language: ");
                    String lang = sc.nextLine();
                    System.out.print("Tags (comma-separated): ");
                    String tags = sc.nextLine();
                    System.out.println("Enter code (end with a single line 'END'):");
                    StringBuilder code = new StringBuilder();
                    String line;
                    while (!(line = sc.nextLine()).equals("END")) {
                        code.append(line).append("\n");
                    }
                    manager.addSnippet(new Snippet(title, lang, tags, code.toString()));
                    System.out.println("Snippet added!");
                }
                case 2 -> manager.viewAll();
                case 3 -> {
                    System.out.print("Search by keyword (title/lang/tag): ");
                    String key = sc.nextLine();
                    manager.search(key);
                }
                case 4 -> {
                    System.out.print("Enter title to delete: ");
                    String title = sc.nextLine();
                    manager.delete(title);
                }
                case 5 -> {
                    System.out.println("Exiting...");
                    return;
                }
                default -> System.out.println("Invalid option.");
            }
        }
    }
}
