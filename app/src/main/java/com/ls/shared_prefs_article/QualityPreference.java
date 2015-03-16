package com.ls.shared_prefs_article;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.DialogPreference;
import android.util.AttributeSet;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.TextView;

public class QualityPreference extends DialogPreference {

	private final int[] SAMPLE_RATES = {11025, 22050, 44100, 48000};
	private final int[] BIT_RATES = {50, 72, 96, 128, 256};

	private Settings settings;

	private SeekBar seekbarSamplingRate;
	private TextView txtSamplingRateSummary;
	private SeekBar seekbarBitRate;
	private TextView txtBitRateSummary;
	private CheckBox ckeckboxStereo;

	public QualityPreference(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		init(context);
	}

	public QualityPreference(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}

	@Override
	protected void onBindDialogView(View view) {
		settings = new Settings();
		settings.load(getSharedPreferences());
		initViews(view);
		fillViews();
		super.onBindDialogView(view);
	}

	@Override
	protected void onDialogClosed(boolean positiveResult) {
		super.onDialogClosed(positiveResult);
		if (positiveResult) {
			SharedPreferences.Editor editor = getEditor();
			settings.save(editor);
			editor.commit();
		}
	}

	private void init(Context context) {
		setPersistent(false);
		setDialogLayoutResource(R.layout.record_quality_pref_dialog);
		setPositiveButtonText(android.R.string.ok);
		setNegativeButtonText(android.R.string.cancel);
	}

	private final SeekBar.OnSeekBarChangeListener sliderChangeListener = new SeekBar.OnSeekBarChangeListener() {
		@Override
		public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
			if (seekBar.getId() == R.id.seekbar_sampling_rate) {
				if (fromUser) settings.setSamplingRate(SAMPLE_RATES[progress]);
				txtSamplingRateSummary.setText(String.format("%d Hz", settings.getSamplingRate()));
			}
			else if (seekBar.getId() == R.id.seekbar_bit_rate) {
				if (fromUser) settings.setBitRate(BIT_RATES[progress]);
				txtBitRateSummary.setText(String.format("%d Kb/s", settings.getBitRate()));
			}
		}

		@Override
		public void onStartTrackingTouch(SeekBar seekBar) {}

		@Override
		public void onStopTrackingTouch(SeekBar seekBar) {}
	};

	private final CompoundButton.OnCheckedChangeListener checkBoxChangeListener = new CompoundButton.OnCheckedChangeListener() {
		@Override
		public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
			settings.setStereo(isChecked);
		}
	};

	private void initViews(View root) {
		seekbarSamplingRate = (SeekBar) root.findViewById(R.id.seekbar_sampling_rate);
		txtSamplingRateSummary = (TextView) root.findViewById(R.id.txt_sampling_rate_summary);
		seekbarBitRate = (SeekBar) root.findViewById(R.id.seekbar_bit_rate);
		txtBitRateSummary = (TextView) root.findViewById(R.id.txt_bit_rate_summary);
		ckeckboxStereo = (CheckBox) root.findViewById(R.id.ckeckbox_stereo);

		seekbarSamplingRate.setOnSeekBarChangeListener(sliderChangeListener);
		seekbarSamplingRate.setMax(SAMPLE_RATES.length - 1);

		seekbarBitRate.setOnSeekBarChangeListener(sliderChangeListener);
		seekbarBitRate.setMax(BIT_RATES.length - 1);

		ckeckboxStereo.setOnCheckedChangeListener(checkBoxChangeListener);
	}

	private void fillViews() {
		int i = Utils.findInIntArray(SAMPLE_RATES, settings.getSamplingRate());
		seekbarSamplingRate.setProgress(i == -1 ? 0 : i);
		i = Utils.findInIntArray(BIT_RATES, settings.getBitRate());
		seekbarBitRate.setProgress(i == -1 ? 0 : i);
		ckeckboxStereo.setChecked(settings.isStereo());
	}
}
