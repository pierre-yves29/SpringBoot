package com.eni.rencontres.bll;

import com.eni.rencontres.bo.Preference;

import java.util.List;

public interface PreferenceService {
    List<Preference> getPreferences();
    Preference getPreferenceById(int id);
}
