package fr.eni.tp.filmotheque.bo;

import java.util.Objects;

public class Avis {
    private long id;
    private int note;
    private String commentaire;
    private Membre membre;

    public Avis() {
    }

    public Avis(int note, String commentaire, Membre membre) {
        this.note = note;
        this.commentaire = commentaire;
        this.membre = membre;
    }

    public Avis(long id, int note, String commentaire, Membre membre) {
        this.id = id;
        this.note = note;
        this.commentaire = commentaire;
        this.membre = membre;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getNote() {
        return note;
    }

    public void setNote(int note) {
        this.note = note;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public Membre getMembre() {
        return membre;
    }

    public void setMembre(Membre membre) {
        this.membre = membre;
    }

    @Override
    public String toString() {
        return "Avis de " + membre.toString() +
                " - note=" + note +
                ", commentaire='" + commentaire + '\'';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Avis avis = (Avis) o;
        return id == avis.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
