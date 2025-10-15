package ru.mirea.zverevds.data.storage.sharedpref;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Collections;
import java.util.List;

import ru.mirea.zverevds.data.storage.FindingStorage;
import ru.mirea.zverevds.data.models.Finding;

public class SharedPrefFindingStorage implements FindingStorage {
    private static final String PREFERENCES_NAME = "finding_prefs";
    private static final String KEY_FINDING_NAME = "finding_";
    private static final String DEFAULT_FINDING_NAME = "Nothing found";

    private final SharedPreferences sharedPreferences;

    public SharedPrefFindingStorage(Context context) {
        this.sharedPreferences = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);
    }

    @Override
    public Finding get(int id) {
        String mushroomId = sharedPreferences.getString(KEY_FINDING_NAME + id, DEFAULT_FINDING_NAME);
        return new Finding(id, mushroomId, "", "");
    }

    @Override
    public boolean saveToCollection(Finding finding) {
        if (finding.getId() <= 0){
            return false;
        }
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_FINDING_NAME + finding.getId(), finding.getMushroomName());
        return editor.commit();
    }

    @Override
    public boolean delete(int id) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove(KEY_FINDING_NAME + id);
        return editor.commit();
    }

    @Override
    public boolean edit(int id, String newLocation) {
        return false;
    }

    @Override
    public List<Finding> getCollection() {
        return Collections.emptyList();
    }
}
