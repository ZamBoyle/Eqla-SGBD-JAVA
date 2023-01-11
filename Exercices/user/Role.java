package Exercices.user;

public final class Role {
    public static final int ADMIN = 1;
    public static final int LECTEUR = 2;
    public static final int BIBLIOTHECAIRE = 3;
    private Role() {
    }
}

/*
public enum E_Role {
    ADMIN(1), LECTEUR(2), BIBLIOTHECAIRE(3);
    private final int value;

    private E_Role(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
*/