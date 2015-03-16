package com.ls.shared_prefs_article;

public class Utils {
	public static int findInIntArray(int[] array, int val) {
		for (int i = 0; i < array.length; ++i) {
			if (array[i] == val) return i;
		}
		return -1;
	}

	public static String formatQualityStr(Settings settings) {
		return String.format("%d Hz %d Kb/sec %s", settings.getSamplingRate(), settings.getBitRate(),
				settings.isStereo() ? "Stereo" : "Mono");
	}
}
