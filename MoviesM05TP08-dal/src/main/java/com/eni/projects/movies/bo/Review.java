package com.eni.projects.movies.bo;

import java.util.Objects;

/**
 * Model for a review
 * @version 1.0
 * @author qprigent
 */
public class Review {
    private long id;
    private int grade;
    private String comments;
    private Member member;

    public Review() {
    }

    public Review(int grade, String comments) {
        this.grade = grade;
        this.comments = comments;
    }

    public Review(long id, int grade, String comments) {
        this.id = id;
        this.grade = grade;
        this.comments = comments;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Review{");
        sb.append("id=").append(id);
        sb.append(", grade=").append(grade);
        sb.append(", comments='").append(comments).append('\'');
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Review review = (Review) o;
        return id == review.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
