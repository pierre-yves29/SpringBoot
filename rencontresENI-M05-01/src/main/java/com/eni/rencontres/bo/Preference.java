package com.eni.rencontres.bo;

import java.util.Objects;

/**
 * Model for a preference
 * A preference is equivalent to the sexual orientation
 * @version 1.0
 * @author qprigent
 */
public class Preference {
    private int id;
    private String code;
    private String label;

    public Preference() {
    }

    public Preference(String code, String label) {
        this.code = code;
        this.label = label;
    }

    public Preference(int id, String code, String label) {
        this.id = id;
        this.code = code;
        this.label = label;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Preference{");
        sb.append("id=").append(id);
        sb.append(", code='").append(code).append('\'');
        sb.append(", label='").append(label).append('\'');
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Preference that = (Preference) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
