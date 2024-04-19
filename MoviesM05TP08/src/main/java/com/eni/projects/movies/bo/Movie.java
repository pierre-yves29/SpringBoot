package com.eni.projects.movies.bo;

import jakarta.validation.constraints.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Model for a movie
 * @version 1.0
 * @author qprigent
 */
public class Movie {
    private long id;

    @NotBlank(message = "Le titre ne doit pas être vide")
    @Size(max = 250, message = "Le titre doit faire {max} caractères maximum")
    private String title;

    @NotNull
    @Min(value = 1900, message = "La date ne peut pas être antérieure à {value}")
    private int year;

    @Min(value = 1, message  = "la durée doit être d'au moins {value} minute(s)")
    private int duration;

    @Size(min = 20, max = 250, message = "Le synopsis doit faire entre {min} et {max} caractères")
    private String synopsis;

    @NotNull
    private Genre genre;

    private List<Review> reviews = new ArrayList<>();

    @NotNull
    private Participant director;

    private List<Participant> actors = new ArrayList<>();

    public Movie() {
    }

    public Movie(String title, int year, int duration, String synopsis) {
        this.title = title;
        this.year = year;
        this.duration = duration;
        this.synopsis = synopsis;
    }

    public Movie(long id, String title, int year, int duration, String synopsis) {
        this.id = id;
        this.title = title;
        this.year = year;
        this.duration = duration;
        this.synopsis = synopsis;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public void addReview(Review review) {
        reviews.add(review);
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void removeReview(Review review) {
        reviews.remove(review);
    }

    public Participant getDirector() {
        return director;
    }

    public void addActor(Participant actor) {
        actors.add(actor);
    }

    public void removeActor(Participant actor) {
        actors.remove(actor);
    }

    public List<Participant> getActors() {
        return actors;
    }

    public void setDirector(Participant director) {
        this.director = director;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Movie{");
        sb.append("id=").append(id);
        sb.append(", title='").append(title).append('\'');
        sb.append(", year=").append(year);
        sb.append(", duration=").append(duration);
        sb.append(", synopsis='").append(synopsis).append('\'');
        sb.append(",review='").append(reviews).append('\'');
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;
        return id == movie.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
