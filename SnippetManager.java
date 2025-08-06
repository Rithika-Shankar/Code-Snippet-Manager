import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class SnippetManager {
    private final List<Snippet> snippets = new ArrayList<>();
    private final String FILE_NAME = "snippets.txt";

    public SnippetManager() {
        loadSnippets();
    }

    public void addSnippet(Snippet s) {
        snippets.add(s);
        saveSnippets();
    }

    public void viewAll() {
        if (snippets.isEmpty()) {
            System.out.println("No snippets found.");
            return;
        }
        for (Snippet s : snippets) {
            System.out.println(s);
        }
    }

    public void search(String keyword) {
        boolean found = false;
        for (Snippet s : snippets) {
            if (s.title.contains(keyword) || s.language.contains(keyword) || s.tags.contains(keyword)) {
                System.out.println(s);
                found = true;
            }
        }
        if (!found) System.out.println("No matching snippets found.");
    }

    public void delete(String title) {
        boolean removed = snippets.removeIf(s -> s.title.equalsIgnoreCase(title));
        if (removed) {
            System.out.println("Snippet deleted.");
            saveSnippets();
        } else {
            System.out.println("Snippet not found.");
        }
    }

    private void loadSnippets() {
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = br.readLine()) != null) {
                Snippet s = Snippet.deserialize(line);
                if (s != null) snippets.add(s);
            }
        } catch (IOException ignored) {}
    }

    private void saveSnippets() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(FILE_NAME))) {
            for (Snippet s : snippets) {
                pw.println(s.serialize());
            }
        } catch (IOException e) {
            System.out.println("Error saving snippets.");
        }
    }
}
