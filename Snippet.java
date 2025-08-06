public class Snippet {
    String title;
    String language;
    String tags;
    String code;

    public Snippet(String title, String language, String tags, String code) {
        this.title = title;
        this.language = language;
        this.tags = tags;
        this.code = code;
    }

    @Override
    public String toString() {
        return "Title: " + title +
                "\nLanguage: " + language +
                "\nTags: " + tags +
                "\nCode:\n" + code + "\n";
    }

    public String serialize() {
        return title + "##" + language + "##" + tags + "##" + code.replace("\n", "\\n");
    }

    public static Snippet deserialize(String data) {
        String[] parts = data.split("##", 4);
        if (parts.length != 4) return null;
        return new Snippet(parts[0], parts[1], parts[2], parts[3].replace("\\n", "\n"));
    }
}
