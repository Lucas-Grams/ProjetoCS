package model;

public class Work {
    private int id;
    private String title;
    private int note;
    private Plataform plataform;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getNote() {
        return note;
    }

    public void setNote(int note) {
        this.note = note;
    }

    public Plataform getPlataform() {
        return plataform;
    }

    public void setPlataform(Plataform plataform) {
        this.plataform = plataform;
    }
}
