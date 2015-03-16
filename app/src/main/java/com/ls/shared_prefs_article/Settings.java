package com.ls.shared_prefs_article;

import android.content.SharedPreferences;

import java.util.Collections;
import java.util.Set;

public class Settings {

	private static final String ENABLE_BACKGROUND_KEY = "enable_background";
	private static final String SERVER_ADDRESS_KEY = "server_address";
	private static final String COLOR_KEY = "color";
	private static final String COLORS_KEY = "colors";
	private static final String SWITCH_KEY = "switch";
	private static final String SAMPLING_RATE_KEY = "sampling_rate";
	private static final String BIT_RATE_KEY = "bit_rate";
	private static final String STEREO_KEY = "stereo";
	private static final String STORE_PATH_KEY = "store_path";

	private boolean enableBackground;
	private String serverAddress;
	private String color;
	private Set<String> colors;
	private boolean switchValue;
	private int samplingRate;
	private int bitRate;
	private boolean stereo;
	private String storePath;

	public boolean isEnableBackground() {
		return enableBackground;
	}

	public void setEnableBackground(boolean enableBackground) {
		this.enableBackground = enableBackground;
	}

	public String getServerAddress() {
		return serverAddress;
	}

	public void setServerAddress(String serverAddress) {
		this.serverAddress = serverAddress;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public Set<String> getColors() {
		return colors;
	}

	public void setColors(Set<String> colors) {
		this.colors = colors;
	}

	public boolean isSwitchValue() {
		return switchValue;
	}

	public void setSwitchValue(boolean switchValue) {
		this.switchValue = switchValue;
	}

	public int getSamplingRate() {
		return samplingRate;
	}

	public void setSamplingRate(int samplingRate) {
		this.samplingRate = samplingRate;
	}

	public int getBitRate() {
		return bitRate;
	}

	public void setBitRate(int bitRate) {
		this.bitRate = bitRate;
	}

	public boolean isStereo() {
		return stereo;
	}

	public void setStereo(boolean stereo) {
		this.stereo = stereo;
	}

	public String getStorePath() {
		return storePath;
	}

	public void setStorePath(String storePath) {
		this.storePath = storePath;
	}

	public void load(SharedPreferences prefs) {
		enableBackground = prefs.getBoolean(ENABLE_BACKGROUND_KEY, false);
		serverAddress = prefs.getString(SERVER_ADDRESS_KEY, "server1.com");
		color = prefs.getString(COLOR_KEY, null);
		colors = prefs.getStringSet(COLORS_KEY, Collections.<String>emptySet());
		switchValue = prefs.getBoolean(SWITCH_KEY, false);
		samplingRate = prefs.getInt(SAMPLING_RATE_KEY, 22050);
		bitRate = prefs.getInt(BIT_RATE_KEY, 96);
		stereo = prefs.getBoolean(STEREO_KEY, false);
		storePath = prefs.getString(STORE_PATH_KEY, null);
	}

	public void save(SharedPreferences prefs) {
		SharedPreferences.Editor editor = prefs.edit();
		save(editor);
		editor.commit();
	}

	public void saveDeferred(SharedPreferences prefs) {
		SharedPreferences.Editor editor = prefs.edit();
		save(editor);
		editor.apply();
	}

	public void save(SharedPreferences.Editor editor) {
		editor.putBoolean(ENABLE_BACKGROUND_KEY, enableBackground);
		editor.putString(SERVER_ADDRESS_KEY, serverAddress);
		editor.putString(COLOR_KEY, color);
		editor.putStringSet(COLORS_KEY, colors);
		editor.putBoolean(SWITCH_KEY, switchValue);
		editor.putInt(SAMPLING_RATE_KEY, samplingRate);
		editor.putInt(BIT_RATE_KEY, bitRate);
		editor.putBoolean(STEREO_KEY, stereo);
		editor.putString(STORE_PATH_KEY, storePath);
	}
}
