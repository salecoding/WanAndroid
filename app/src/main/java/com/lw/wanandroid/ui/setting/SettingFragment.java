package com.lw.wanandroid.ui.setting;

import android.os.Bundle;
import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceFragmentCompat;

import com.lw.wanandroid.R;


/**
 * Created by lw on 2017-09-05.
 */

public class SettingFragment extends PreferenceFragmentCompat {
    private Preference mSettingAutoUpdate, mCheckUpdate, mAbout;

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        addPreferencesFromResource(R.xml.settings_preference_fragment);
        mSettingAutoUpdate = findPreference("settingAutoUpdate");
        mCheckUpdate = findPreference("checkUpdate");
        mAbout = findPreference("about");
    }

    public static SettingFragment newInstance() {
        return new SettingFragment();
    }
}
