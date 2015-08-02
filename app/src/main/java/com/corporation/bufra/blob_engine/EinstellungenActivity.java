package com.corporation.bufra.blob_engine;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.widget.Toast;

import com.corporation.bufra.blob_engine.OpenGL.MainRenderer;

/**
 * Created by Clemens on 01.08.2015.
 */
public class EinstellungenActivity extends PreferenceActivity implements Preference.OnPreferenceChangeListener {

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        //Unnoetiges Toast fuer Marcs Kompetenz aussprechen:
        Toast.makeText(this, R.string.einstellungen_geoeffnet, Toast.LENGTH_SHORT).show();

        //Noch ein unnoetigen Kommentar für den Herrn Marc!!!!!
        addPreferencesFromResource(R.xml.preferences);

        Preference figurPref = findPreference(getString(R.string.preference_figurlist_key));
        figurPref.setOnPreferenceChangeListener(this);

        Preference namePref = findPreference(getString(R.string.preference_name_key));
        figurPref.setOnPreferenceChangeListener(this);

        //SharedPreferences ändern
        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);
        String preferenceString = sharedPrefs.getString(figurPref.getKey(), "");
        onPreferenceChange(figurPref, preferenceString);
        System.out.println(preferenceString);
    }

    @Override
    public boolean onPreferenceChange(Preference preference, Object value){
        if (Integer.valueOf((String)value) == 1){
            MainRenderer.setTriangle(true);
        }

        return true;
    }

}
