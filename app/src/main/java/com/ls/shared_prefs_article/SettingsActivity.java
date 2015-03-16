package com.ls.shared_prefs_article;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceFragment;
import android.view.MenuItem;

public class SettingsActivity extends PreferenceActivity {

	public static void startThisActivity(Context context) {
		Intent intent = new Intent(context, SettingsActivity.class);
		context.startActivity(intent);
	}

	public static void startThisActivityForResult(Activity activity, int requestCode) {
		Intent intent = new Intent(activity, SettingsActivity.class);
		activity.startActivityForResult(intent, requestCode);
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//addPreferencesFromResource(R.xml.preferences);
		if (savedInstanceState == null) {
			getFragmentManager().beginTransaction().replace(android.R.id.content, new MyPreferenceFragment()).commit();
		}
		initActionBar();
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (item.getItemId() == android.R.id.home) {
			finish();
			return true;
		}

		return false;
	}

	public static class MyPreferenceFragment extends PreferenceFragment {
		private AppSettings settings;

		@Override
		public void onCreate(final Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			addPreferencesFromResource(R.xml.preferences);

			Preference serverAddressPrefs = findPreference("server_address");
			serverAddressPrefs.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
				@Override
				public boolean onPreferenceChange(Preference preference, Object newValue) {
					preference.setSummary(String.valueOf(newValue));
					return true;
				}
			});

			Preference colorPrefs = findPreference("color");
			colorPrefs.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
				@Override
				public boolean onPreferenceChange(Preference preference, Object newValue) {
					preference.setSummary(String.valueOf(newValue));
					return true;
				}
			});

			Preference colorsPrefs = findPreference("colors");
			colorsPrefs.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
				@Override
				public boolean onPreferenceChange(Preference preference, Object newValue) {
					preference.setSummary(String.valueOf(newValue));
					return true;
				}
			});

			Preference storePathPrefs = findPreference("store_path");
			storePathPrefs.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
				@Override
				public boolean onPreferenceChange(Preference preference, Object newValue) {
					preference.setSummary(String.valueOf(newValue));
					return true;
				}
			});
		}

		@Override
		public void onActivityCreated(Bundle savedInstanceState) {
			super.onActivityCreated(savedInstanceState);
			settings = AppSettings.getSettings(getActivity());

			Preference serverAddressPrefs = findPreference("server_address");
			serverAddressPrefs.setSummary(settings.getServerAddress());

			Preference colorPrefs = findPreference("color");
			colorPrefs.setSummary(settings.getColor());

			Preference colorsPrefs = findPreference("colors");
			colorsPrefs.setSummary(settings.getColors().toString());

			Preference storePathPrefs = findPreference("store_path");
			storePathPrefs.setSummary(settings.getStorePath());
		}

		@Override
		public void onPause() {
			super.onPause();
			getPreferenceScreen().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(sharedPrefsChangeListener);
		}

		@Override
		public void onResume() {
			super.onResume();
			updateSummary();
			getPreferenceScreen().getSharedPreferences().registerOnSharedPreferenceChangeListener(sharedPrefsChangeListener);
		}

		private final SharedPreferences.OnSharedPreferenceChangeListener sharedPrefsChangeListener = new SharedPreferences.OnSharedPreferenceChangeListener() {
			@Override
			public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
				settings.load();
				updateSummary();
			}
		};

		private void updateSummary() {
			Preference qualityPrefs = getPreferenceScreen().findPreference("quality");
			qualityPrefs.setSummary(Utils.formatQualityStr(settings));
		}
	}

	private void initActionBar() {
		ActionBar actionBar = getActionBar();
		if (actionBar != null) {
			actionBar.setHomeButtonEnabled(true);
			actionBar.setDisplayHomeAsUpEnabled(true);
		}
	}
}
