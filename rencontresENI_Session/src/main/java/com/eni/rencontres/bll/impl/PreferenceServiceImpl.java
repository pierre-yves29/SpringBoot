package com.eni.rencontres.bll.impl;

import com.eni.rencontres.bll.PreferenceService;
import com.eni.rencontres.bo.Preference;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PreferenceServiceImpl implements PreferenceService {
    private List<Preference> preferences = new ArrayList<>();

    public PreferenceServiceImpl(){
        preferences.add(new Preference("M","Masculin"));
        preferences.add(new Preference("F","Feminin"));
        preferences.add(new Preference("T", "Transgenre"));
    }

    public List<Preference> getPreferences(){
        return preferences;
    }
}
