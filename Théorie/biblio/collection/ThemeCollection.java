package biblio.collection;

import biblio.BOL.Theme;

import java.util.ArrayList;
import java.util.List;

public class ThemeCollection{
    private List<Theme> themes = new ArrayList<Theme>();

    public ThemeCollection() {
        themes = new ArrayList<Theme>();
    }

    public void addTheme(Theme theme) {
        themes.add(theme);
    }

    public void removeTheme(Theme theme) {
        themes.remove(theme);
    }

    public Theme getTheme(int id) {
        for (Theme theme : themes) {
            if (theme.getId() == id) {
                return theme;
            }
        }
        return null;
    }

    public List<Theme> getThemes() {
        return themes;
    }

    public void setThemes(List<Theme> themes) {
        this.themes = themes;
    }

    public void displayThemes() {
        for (Theme theme : themes) {
            System.out.println(theme);
        }
    }



}
