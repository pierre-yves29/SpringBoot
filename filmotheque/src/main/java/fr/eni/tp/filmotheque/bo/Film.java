package fr.eni.tp.filmotheque.bo;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Film {
    private long id;
    private String titre;
    private int annee;
    private int duree;
    private String synopsis;

    private Participant realisateur;
    private List<Participant> acteurs = new ArrayList<>();

    private List<Avis> avis = new ArrayList<>();

    private Genre genre;

    public Film() {
    }

    public Film(String titre, int annee, int duree, String synopsis) {
        this.titre = titre;
        this.annee = annee;
        this.duree = duree;
        this.synopsis = synopsis;
    }

    public Film(long id, String titre, int annee, int duree, String synopsis) {
        this.id = id;
        this.titre = titre;
        this.annee = annee;
        this.duree = duree;
        this.synopsis = synopsis;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public int getAnnee() {
        return annee;
    }

    public void setAnnee(int annee) {
        this.annee = annee;
    }

    public int getDuree() {
        return duree;
    }

    public void setDuree(int duree) {
        this.duree = duree;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public Participant getRealisateur() {
        return realisateur;
    }

    public void setRealisateur(Participant realisateur) {
        this.realisateur = realisateur;
    }

    public List<Participant> getActeurs() {
        return acteurs;
    }

    public void addActeur(Participant acteur) {
        acteurs.add(acteur);
    }

    public void removeActeur(Participant acteur) {
        acteurs.remove(acteur);
    }

    public List<Avis> getAvis() {
        return avis;
    }

    public void addAvis(Avis avis){
        this.avis.add(avis);
    }

    public void removeAvis(Avis avis){
        this.avis.remove(avis);
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    @Override
    public String toString() {
//        return "Film{" +
//                "id=" + id +
//                ", titre='" + titre + '\'' +
//                ", annee=" + annee +
//                ", duree=" + duree +
//                ", synopsis='" + synopsis + '\'' +
//                ", realisateur=" + realisateur +
//                ", acteurs=" + acteurs +
//                ", genre=" + genre +
//                '}';
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Film(%d)%n", id));
        sb.append(String.format("%5sTitre : %s[annee : %d, duree : %d minutes]%n","",titre,annee,duree));
        sb.append(String.format("%5sSynopsis : %s%n","",synopsis));
        String ar = "";
        if (acteurs.contains(realisateur)){
            ar = " - [Realisateur, Acteur]";
        }
        sb.append(String.format("%5sRealisateur : %s%s%n","",realisateur.toString(),ar));
        sb.append(String.format("%5sActeurs : [",""));
        for(int index = 0 ; index < acteurs.size() ; index++){
            sb.append(acteurs.get(index).toString());
            if (index < acteurs.size() - 1){
                sb.append(", ");
            }
        }
        sb.append(String.format("]%n"));
        if(genre != null) {
            sb.append(String.format("%5sGenre : %s%n", "", genre.toString()));
        }
        sb.append(String.format("%5sAvis : [",""));
        for(int index = 0 ; index < avis.size() ; index++){
            sb.append(avis.get(index).toString());
        }
        sb.append(String.format("]%n"));
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Film film = (Film) o;
        return id == film.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
