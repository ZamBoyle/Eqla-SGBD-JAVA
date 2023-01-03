package biblio.collection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import biblio.BOL.Theme;

public class ThemeCollection {
    private List<Theme> themes; 

    public ThemeCollection() {
        themes = new ArrayList<Theme>();
    }

    public ThemeCollection(List<Theme> themes) {
        this.themes = themes;
    }

    public ThemeCollection(Theme[] themes) {
        this(new ArrayList<Theme>(Arrays.asList(themes)));
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
