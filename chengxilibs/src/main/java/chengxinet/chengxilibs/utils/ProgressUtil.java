package chengxinet.chengxilibs.utils;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningServiceInfo;
import android.content.Context;

import java.util.ArrayList;

public class ProgressUtil {
	public static boolean isServiceRunning(Context mContext, String className) {
		ActivityManager myManager = (ActivityManager) mContext
				.getSystemService(Context.ACTIVITY_SERVICE);
		ArrayList<RunningServiceInfo> runningService = (ArrayList<RunningServiceInfo>) myManager
				.getRunningServices(200);
		for (int i = 0; i < runningService.size(); i++) {
			if (runningService.get(i).service.getClassName().toString()
					.equals(className)) {
				return true;
			}
		}
		return false;
	}
}
