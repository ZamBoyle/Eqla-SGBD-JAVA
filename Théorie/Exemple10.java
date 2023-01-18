import Exemples.user.Role;
import Exemples.app.Menu;
public class Exemple10 {
    public static void main(String[] args) {
        Menu menu = new Menu(Role.LECTEUR);
        menu.menuPrincipal();
    }
}