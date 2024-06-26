package com.eni.rencontres.bo;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

/**
 * Model for a person
 * @version 1.0
 * @author qprigent
 */
public class Person {
    private long id;
    private String name;
    private LocalDate birthdate;
    private String description;
    private String gender;
    private List<String> preferences;
    private float latitude;
    private float longitude;

    public Person() {
    }

    public Person(String name, LocalDate birthdate, String description, String gender, List<String> preferences, float latitude, float longitude) {
        this.name = name;
        this.birthdate = birthdate;
        this.description = description;
        this.gender = gender;
        this.preferences = preferences;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Person(long id, String name, LocalDate birthdate, String description, String gender, List<String> preferences, float latitude, float longitude) {
        this.id = id;
        this.name = name;
        this.birthdate = birthdate;
        this.description = description;
        this.gender = gender;
        this.preferences = preferences;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public List<String> getPreferences() {
        return preferences;
    }

    public void setPreferences(List<String> preferences) {
        this.preferences = preferences;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Person{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", birthdate=").append(birthdate);
        sb.append(", description='").append(description).append('\'');
        sb.append(", gender='").append(gender).append('\'');
        sb.append(", preferences=").append(preferences);
        sb.append(", latitude=").append(latitude);
        sb.append(", longitude=").append(longitude);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return id == person.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
