package com.time;


/**
 * 将以秒为时间的整数转换成HH:mm:ss的格式
 * @author zealjiang
 * @time 2016年4月15日下午2:32:59
 */
public class SecToTime {

	public static void main(String[] args) {
		System.out.println(secToTime(9918171000L));
		System.out.println(secToExactTime(9918171000L));
	}
	
	// a integer to xx:xx:xx
    public static String secToTime(long time) {
    	time=time/1000;
        String timeStr = null;
        long hour = 0;
        long minute = 0;
        long second = 0;
        if (time <= 0)
            return "00:00";
        else {
            minute = time / 60;
            if (minute < 60) {
                second = time % 60;
                timeStr = unitFormat(minute) + ":" + unitFormat(second);
            } else {
                hour = minute / 60;
                if (hour > 99)
                    return "99:59:59";
                minute = minute % 60;
                second = time - hour * 3600 - minute * 60;
                timeStr = unitFormat(hour) + ":" + unitFormat(minute) + ":" + unitFormat(second);
            }
        }
        return timeStr;
    }
    
	/**
	 * 转换成精确的时间
	 * @author zealjiang
	 * @param time
	 * @return
	 */
    public static String secToExactTime(long time) {
    	time=time/1000;
        String timeStr = null;
        long hour = 0;
        long minute = 0;
        long second = 0;
        if (time <= 0)
            return "00:00";
        else {
            minute = time / 60;
            if (minute < 60) {
                second = time % 60;
                timeStr = unitFormat(minute) + ":" + unitFormat(second);
            } else {
                hour = minute / 60;
                minute = minute % 60;
                second = time - hour * 3600 - minute * 60;
                timeStr = unitFormat(hour) + ":" + unitFormat(minute) + ":" + unitFormat(second);
            }
        }
        return timeStr;
    }

    public static String unitFormat(long i) {
        String retStr = null;
        if (i >= 0 && i < 10)
            retStr = "0" + Long.toString(i);
        else
            retStr = "" + i;
        return retStr;
    }
}
