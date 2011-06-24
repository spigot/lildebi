
package info.guardianproject.lildebi;

import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.EditTextPreference;

public class PreferencesActivity extends android.preference.PreferenceActivity implements OnSharedPreferenceChangeListener
{
    CheckBoxPreference startOnBootCheckBox;

    /* save the preferences in Imps so they are accessible everywhere */
    @Override
    public void onSharedPreferenceChanged(SharedPreferences prefs, String key)
    {    	
    	if (key.equals(getString(R.string.pref_start_on_boot_key)))
    	{
    		boolean startOnBoot = prefs.getBoolean(key, false);
    	}
    	else if (key.equals(getString(R.string.pref_post_start_key)))
    	{
    		DebiHelper.postStartScript = prefs.getString(key, 
    					getString(R.string.default_post_start_script));
    	}
    	else if (key.equals(getString(R.string.pref_pre_stop_key)))
    	{
    		DebiHelper.preStopScript = prefs.getString(key,
    				getString(R.string.default_pre_stop_script));
    	}
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
    	super.onCreate(savedInstanceState);
    	addPreferencesFromResource(R.xml.preferences);    
    	startOnBootCheckBox = (CheckBoxPreference) findPreference(getString(R.string.pref_start_on_boot_key));
    }

    @Override
    protected void onResume()
    {
    	super.onResume();
    	getPreferenceScreen().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    protected void onPause()
    {
    	super.onPause();
    	getPreferenceScreen().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);    
    }

}
