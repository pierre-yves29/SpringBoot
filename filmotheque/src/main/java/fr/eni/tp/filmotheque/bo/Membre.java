package fr.eni.tp.filmotheque.bo;

public class Membre extends Personne {
    private String pseudo;
    private String MotDePasse;
    private boolean admin = false;

    public Membre() {
        super();
    }

    public Membre(String nom, String prenom, String pseudo, String motDePasse) {
        super(nom, prenom);
        this.pseudo = pseudo;
        MotDePasse = motDePasse;
    }

    public Membre(long id, String nom, String prenom, String pseudo, String motDePasse) {
        super(id, nom, prenom);
        this.pseudo = pseudo;
        MotDePasse = motDePasse;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public String getMotDePasse() {
        return MotDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        MotDePasse = motDePasse;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    @Override
    public String toString() {
        return super.toString() + " - Membre (" +
                "pseudo='" + pseudo + '\'' +
                ", admin=" + admin +
                ") ";
    }
}
