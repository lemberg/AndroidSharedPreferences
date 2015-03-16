package com.ls.shared_prefs_article;

import android.app.Activity;
import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class AppSettings extends Settings {

	public static AppSettings getSettings(Activity activity) {
		return getSettings(activity.getApplication());
	}

	public static AppSettings getSettings(Application application) {
		return ((MyApplication) application).settings;
	}

	private final MyApplication application;

	public AppSettings(MyApplication application) {
		this.application = application;
	}


	public void load() {
		SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(application);
		load(prefs);
	}

	public void save() {
		SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(application);
		save(prefs);
	}

	public void saveDeferred() {
		SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(application);
		saveDeferred(prefs);
	}
}
