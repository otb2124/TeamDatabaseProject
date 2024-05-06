//package finalExam;

class PaperInfo {
    private String author;
    private String title;
    private String abstractText;

    public PaperInfo(String author, String title, String abstractText) {
        this.author = author;
        this.title = title;
        this.abstractText = abstractText;
    }

 
    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public String getAbstractText() {
        return abstractText;
    }
}
