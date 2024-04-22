package com.eni.rencontres.bll.impl;

import com.eni.rencontres.bll.PreferenceService;
import com.eni.rencontres.bo.Preference;
import com.eni.rencontres.dal.PreferenceDAO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PreferenceServiceImpl implements PreferenceService {
    private PreferenceDAO preferenceDAO;

    public PreferenceServiceImpl(
            PreferenceDAO preferenceDAO
    ) {
        this.preferenceDAO = preferenceDAO;
    }

    @Override
    public List<Preference> getPreferences() {
        return preferenceDAO.findAll();
    }

    @Override
    public Preference getPreferenceById(int id) {
        return preferenceDAO.read(id);
    }
}
