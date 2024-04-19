package com.eni.rencontres.bll.impl;

import com.eni.rencontres.bll.PreferenceService;
import com.eni.rencontres.bo.Preference;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PreferenceServiceImpl implements PreferenceService {
    private List<Preference> preferences = new ArrayList<>();

    public PreferenceServiceImpl() {
        preferences.add(new Preference(1, "M", "Masculin"));
        preferences.add(new Preference(2, "F", "Féminin"));
        preferences.add(new Preference(3, "T", "Transgenre"));
    }

    @Override
    public List<Preference> getPreferences() {
        return preferences;
    }

    @Override
    public Preference getPreferenceById(int id) {
        return preferences.stream().filter(preference -> preference.getId() == id).findAny().orElse(null);
    }
}
