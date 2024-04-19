package com.eni.rencontres.controller.converter;

import com.eni.rencontres.bll.PreferenceService;
import com.eni.rencontres.bo.Preference;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StringToPreferenceConverter implements Converter<String, Preference> {
    private PreferenceService preferenceService;

    public StringToPreferenceConverter(
            PreferenceService preferenceService
    ) {
        System.out.println("Converter is a bean");
        this.preferenceService = preferenceService;
    }

    @Override
    public Preference convert(String idString) {
        Integer id = Integer.parseInt(idString);
        System.out.println(id);
        return preferenceService.getPreferenceById(id);
    }
}
