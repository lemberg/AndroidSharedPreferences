package com.ls.shared_prefs_article;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


public class MainActivity extends Activity {
	private static final int SETTINGS_REQUEST = 1000;

	private TextView txtEnableBackground;
	private TextView txtServerAddress;
	private TextView txtColor;
	private TextView txtColors;
	private TextView txtSwitch;
	private TextView txtRecQuality;
	private TextView txtStorePath;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initViews();
		fillViews();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.menu_main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();

		//noinspection SimplifiableIfStatement
		if (id == R.id.action_settings) {
			SettingsActivity.startThisActivityForResult(this, SETTINGS_REQUEST);
			return true;
		}

		return super.onOptionsItemSelected(item);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		switch (requestCode) {
			case SETTINGS_REQUEST:
				fillViews();
		}
		super.onActivityResult(requestCode, resultCode, data);
	}

	private void initViews() {
		txtEnableBackground = (TextView) findViewById(R.id.txt_enable_background);
		txtServerAddress = (TextView) findViewById(R.id.txt_server_address);
		txtColor = (TextView) findViewById(R.id.txt_color);
		txtColors = (TextView) findViewById(R.id.txt_colors);
		txtSwitch = (TextView) findViewById(R.id.txt_switch);
		txtRecQuality = (TextView) findViewById(R.id.txt_rec_quality);
		txtStorePath = (TextView) findViewById(R.id.txt_store_path);
	}

	private void fillViews() {
		AppSettings settings = AppSettings.getSettings(this);

		txtEnableBackground.setText(String.valueOf(settings.isEnableBackground()));
		txtServerAddress.setText(settings.getServerAddress());
		txtColor.setText(settings.getColor());
		txtColors.setText(settings.getColors().toString());
		txtSwitch.setText(String.valueOf(settings.isSwitchValue()));
		txtRecQuality.setText(Utils.formatQualityStr(settings));
		txtStorePath.setText(settings.getStorePath());
	}
}
