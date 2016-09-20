package chengxinet.chengxilibs.utils;

import android.text.TextUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;


public class TimeTurners {

	private final static long minute = 60 * 1000;// 1分钟
	private final static long hour = 60 * minute;// 1小时
	private final static long day = 24 * hour;// 1天
	private final static long month = 31 * day;// 月
	private final static long year = 12 * month;// 年

	/**
	 *
	 * @param user_time 时间格式：2015-09-06 16:09:12
	 * @return 时间戳
	 */
	public static long getTimeStamp(String user_time, String format) {
		String re_time = null;
		SimpleDateFormat sdf;
		if(TextUtils.isEmpty(format)) {
			sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		}else{
			sdf = new SimpleDateFormat(format);
		}
		sdf.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
		Date d;
		try {
			d = sdf.parse(user_time);
			long l = d.getTime();
			return l;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	public static long getTimeStamp(String user_time) {
		return getTimeStamp(user_time, null);
	}

	/**
	 *
	 * @param cc_time 时间戳(10位)
	 * @return 时间字符串 yyyy-MM-dd HH:mm
	 */
	public static String getTimeStr(String cc_time) {
		if (!TextUtils.isEmpty(cc_time)) {
			return getTimeStr(cc_time, "yyyy-MM-dd HH:mm");
		} else {
			return "";
		}
	}

	/**
	 *
	 * @param cc_time cc_time 时间戳(10位)
	 * @param format 时间格式
	 * @return 时间字符串（时间格式返回）
	 */
	public static String getTimeStr(String cc_time, String format) {
		String re_StrTime = null;
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		// 例如：cc_time=1291778220
		sdf.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
		long lcc_time = Long.valueOf(cc_time);
		re_StrTime = sdf.format(new Date(lcc_time * 1000L));
		return re_StrTime;
	}

	/**
	 *
	 * @param cc_time cc_time 时间戳(10位)
	 * @return 时间字符串（例如：6天前）
     */
	public static String getTime(String cc_time) {

		long lcc_time = Long.valueOf(cc_time);
		if (TextUtils.isEmpty(cc_time)) {
			return "";
		}
		long diff = new Date().getTime() - lcc_time * 1000L;
		long r = 0;
		MyLog.logd("time", diff + "");

		if (diff > year) {
			r = (diff / year);
			return r + " 年前";
		}
		if (diff > month) {
			r = (diff / month);
			if (r != 1) {
				return r + " 个月前";
			} else {
				return "上个月";
			}
		}
		if (diff > day) {
			r = (diff / day);
			if (r < 7) {
				return r + " 天前";
			} else {
				r =  r / 7;
				if (r != 4) {
					if (r != 1) {
						return r + " 星期前";
					} else {
						return "上星期";
					}
				} else {
					return "上个月";
				}
			}
		}
		if (diff > hour) {
			r = (diff / hour);
			return r + " 小时前";
		}
		if (diff > minute) {
			r = (diff / minute);
			return r + " 分钟前";
		}
		return "刚刚";
	}
}
