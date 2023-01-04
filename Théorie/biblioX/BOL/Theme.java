package biblioX.BOL;

public class Theme {
    private int id;
    private String theme;

    public Theme() {
    }

    public Theme(int id, String theme) {
        this.id = id;
        this.theme = theme;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    @Override
    public String toString() {
        return "Theme [id=" + id + ", theme=" + theme + "]";
    }
}
