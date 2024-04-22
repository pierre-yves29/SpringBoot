package com.eni.rencontres.dal;

import com.eni.rencontres.bo.Preference;

import java.util.List;

public interface PreferenceDAO {
    void create(Preference preference);
    Preference read(long id);
    void update(Preference preference);
    void delete(Preference preference);
    List<Preference> findAll();
}
