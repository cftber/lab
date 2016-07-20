package com.tgzhao.core.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by tgzhao on 16/7/17.
 */
public abstract class DateUtil {

    /**
     * 日期短格式yyyy-MM-dd
     */
    public static final String DATE_SHORT = "yyyy-MM-dd";

    /**
     * 24小时格式
     */
    public static final String DATE_MINUTES = "yyyy-MM-dd HH:mm:ss";

    /**
     * 24小时格式
     */
    public static final String DATE_MINUTES_12 = "yyyy-MM-dd hh-mm-ss";

    private static final String dateFormate_Now = "yyyyMMdd";

    private static final SimpleDateFormat dateformat = new SimpleDateFormat(DATE_MINUTES);

    private static final SimpleDateFormat dateformat_now = new SimpleDateFormat(dateFormate_Now);

    /**
     * mill 2 format date
     * @param input mill
     * @param patten
     * @return
     */
    public static String longToDate(Long input, String patten) {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(input);
        return new SimpleDateFormat(patten).format(cal.getTime());
    }

    public static String longToShowDate(Long diff) {
        String time = "";
        if (diff > 0) {
            diff = diff / 1000;
            Long hour = diff / (3600);
            if (hour > 0) {
                time += hour + "小时";
            }
            Long minute = diff % 3600 / 60;
            if (minute > 0) {
                time += minute + "分";
            }
            Long second = diff % (60) / 60;
            if (second > 0) {
                time += second + "秒";
            }
        }
        return time;
    }

    public static String showDate(Long time) {
        Long nowTime = dateTime();
        Long diff = nowTime - time;
        Long hour = diff / 3600;
        if (hour > 24) {
            return longToDate(time,"yyyy-MM-dd");
//            return hour / 24 + "天前";
        }
        if (hour > 0) {
            return hour + "小时前";
        }
        Long minute = diff % 3600 / 60;
        if (minute > 0) {
            return minute + "分钟前";
        }
        return "刚刚";
    }

    public static Date lastDayOfMonth(int year, int month) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month - 1);
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DATE));
        return formatDate(cal.getTime());
    }

    public static Date firstDayOfMonth(int year, int month) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month - 1);
        cal.set(Calendar.DAY_OF_MONTH, cal.getMinimum(Calendar.DATE));
        return formatDate(cal.getTime());
    }

    public static String getDateText(Date date) {
        if (date != null) {
            return dateformat.format(date);
        }
        return "";
    }

    public static String getCurrentDateText() {
        Date date = new Date();
        return dateformat_now.format(date);
    }

    public static String dayMove(String date, int len) {
        return dateMove(date, len, Calendar.DATE, DATE_SHORT);
    }

    public static String dayMove(Date date, int len) {
        return dateMove(date, len, Calendar.DATE, DATE_SHORT);
    }

    public static String dayMove(Date date, int len, String pattern) {
        return dateMove(date, len, Calendar.DATE, pattern);
    }

    public static String dayMove(String date, int len, String pattern) {
        return dateMove(date, len, Calendar.DATE, pattern);
    }

    public static Date dayMoveNoFormat(Date date, int len) {
        return dateMoveToDate(date, len, Calendar.DATE);
    }

    public static String mouthMove(Date date, int len) {
        return dateMove(date, len, Calendar.MONTH, DATE_SHORT);
    }

    public static String mouthMove(String date, int len) {
        return dateMove(date, len, Calendar.MONTH, DATE_SHORT);
    }

    public static String mouthMove(Date date, int len, String pattern) {
        return dateMove(date, len, Calendar.MONTH, pattern);
    }

    public static String mouthMove(String date, int len, String pattern) {
        return dateMove(date, len, Calendar.MONTH, pattern);
    }

    public static String yearMove(String date, int len) {
        return dateMove(date, len, Calendar.YEAR, DATE_SHORT);
    }

    public static String yearMove(Date date, int len) {
        return dateMove(date, len, Calendar.YEAR, DATE_SHORT);
    }

    public static String yearMove(String date, int len, String pattern) {
        return dateMove(date, len, Calendar.YEAR, pattern);
    }

    public static String yearMove(Date date, int len, String pattern) {
        return dateMove(date, len, Calendar.YEAR, pattern);
    }


    private static String dateMove(String date, int len, int field, String pattern) {
        return dateMove(stringToDate(date, pattern), len, field, pattern);
    }


    private static String dateMove(Date date, int len, int field, String pattern) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(field, len);
        return dateToString(cal.getTime(), pattern);
    }


    public static Long nowDateHourMove(Integer len) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.HOUR_OF_DAY, len);
        return cal.getTimeInMillis() / 1000;
    }


    private static Date dateMoveToDate(Date date, int len, int field) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(field, len);
        return cal.getTime();
    }


    public static String time() {
        return dateToString(new Date(), DATE_MINUTES_12);
    }


    public static String time24() {
        return time24(new Date());
    }


    public static String time24(Date date) {
        return dateToString(date, DATE_MINUTES);
    }


    public static String nowDate() {
        return dateToString(new Date(), DATE_SHORT);
    }

    public static boolean isSameDate(Date date) {
        if (dateToString(new Date()).equals(dateToString(date))) {
            return true;
        }
        return false;
    }

    public static Date dateReducingTime(Date date, int minute) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(Calendar.MINUTE, -minute);
        return calendar.getTime();
    }


    /**
     * 将当前时间减少几分钟
     *
     * @param minute
     * @return
     */
    public static Date currentDateReducingTime(int minute) {
        Calendar calendar = new GregorianCalendar();
        return dateReducingTime(calendar.getTime(), minute);
    }

    /**
     * @return String
     * @description -返回系统现在时间的毫秒数
     */
    public static String timeMilliseconds() {
        return String.valueOf(new Date().getTime());
    }

    /**
     * @param pattern -格式 <br>
     *                Date date -日期对象
     * @return String -日期字符串
     * @description 将日期对象date转化成格式pattern的日期字符串
     */
    public static String dateToString(Date date, String pattern) {
        return new SimpleDateFormat(pattern).format(date);
    }

    /**
     * @param date
     * @return String
     * @description 返回指定时间的字符串 (yyyy-MM-dd hh-mm-ss),12小时格式
     */
    public static String timeToString(Date date) {
        return dateToString(date, DATE_MINUTES_12);
    }

    public static String dateToString(Date date) {
        if (date != null) {
            return dateToString(date, DATE_SHORT);
        } else {
            return null;
        }
    }


    /**
     * @param dateStr -日期字符串 <br>
     *                String pattern -转化格式
     * @return Date -转化成功返回该格式的日期对象,失败返回null
     * @description -按格式pattern将字符串dateStr转化为日期
     */
    public static Date stringToDate(String dateStr, String pattern) {
        Date date = null;
        if (!StringUtil.isBlank(dateStr)) {
            try {
                date = new SimpleDateFormat(pattern).parse(dateStr);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return date;
    }

    public static Date stringTo12Time(String timeStr) {
        return stringToDate(timeStr, DATE_MINUTES_12);
    }


    public static Date stringTo24Time(String timeStr) {
        return stringToDate(timeStr, DATE_MINUTES);
    }


    public static Date stringToDate(String dateStr) {
        return stringToDate(dateStr, DATE_SHORT);
    }


    public static String format(String dateString, String pattern) {
        String result = null;
        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
        try {
            result = dateFormat.format(dateFormat.parse(dateString));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return result;
    }


    public static String format(String dateString) {
        return format(dateString, DATE_SHORT);
    }


    public static Date formatDate(Date date) {
        String dateString = dateToString(date);
        return stringToDate(dateString);
    }

    public static String formatDate(Date date, String pattern) {
        return dateToString(date, pattern);
    }

    /**
     * 判断当前日期   > 传入的日期 返回true。
     * 判断当前日期   <= 传入的日期 返回false。
     *
     * @param vDate
     * @return
     */
    public static boolean isAfterDate(Date vDate) {
        Calendar nowDate = GregorianCalendar.getInstance();
        return nowDate.after(vDate);
    }

    /**
     * 判断日期的相隔天数
     *
     * @param startDate
     * @param endDate
     * @return
     */
    public static int distDates(Date startDate, Date endDate) {
        long totalDate = 0;
        Calendar startCalendar = Calendar.getInstance();
        startCalendar.setTime(startDate);
        Calendar endalendar = Calendar.getInstance();
        endalendar.setTime(endDate);
        long timestart = startCalendar.getTimeInMillis();
        long timeend = endalendar.getTimeInMillis();
        totalDate = Math.abs((timeend - timestart)) / (1000 * 60 * 60 * 24);
        return Long.valueOf(totalDate).intValue();
    }

    /**
     * 判断传入的日期与服务器当前日期的相隔天数
     *
     * @param vDate
     * @return
     */
    public static int distCurrentDates(Date vDate) {
        return distDates(new Date(), vDate);
    }

    /**
     * 判断日期相隔的时、分、秒
     */
    public static Long[] distTime(Date startDate, Date endDate) {
        long hour = 0;
        long min = 0;
        long sec = 0;
        if (startDate != null && endDate != null) {
            long time1 = startDate.getTime();
            long time2 = endDate.getTime();
            long diff;
            if (time1 < time2) {
                hour = 0L;
                min = 0L;
                sec = 0L;
            } else {
                diff = time1 - time2;
                hour = diff / (60 * 60 * 1000);
                min = ((diff / (60 * 1000)) - hour * 60);
                sec = (diff / 1000 - hour * 60 * 60 - min * 60);
            }
        }
        Long[] times = {hour, min, sec};
        return times;
    }

    /**
     * 判断日期相隔的天、时、分
     */
    public static Long[] distMinute(Date startDate, Date endDate) {
        long day = 0;
        long hour = 0;
        long min = 0;
        if (startDate != null && endDate != null) {
            long time1 = startDate.getTime();
            long time2 = endDate.getTime();
            long diff;
            if (time1 < time2) {
                day = 0L;
                hour = 0L;
                min = 0L;
            } else {
                diff = time1 - time2;
                day = diff / (1000 * 60 * 60 * 24);
                hour = (diff / (60 * 60 * 1000) - day * 24);
                min = ((diff / (60 * 1000)) - day * 24 * 60 - hour * 60);
            }
        }
        Long[] times = {day, hour, min};
        return times;
    }

    /**
     * 判断传入日期与服务器当期日期相隔的时、分、秒
     */
    public static Long[] distCurrentTime(Date vDate) {
        return distTime(vDate, new Date());
    }

    /**
     * 判断传入日期与服务器当期日期相隔的天、时、分
     */
    public static Long[] distCurrentMinute(Date vDate) {
        return distMinute(vDate, new Date());
    }

    /**
     * 返回当前日期的星期数
     * 星期天为1，星期一为：2星期二为3，以此类推星期天为7
     *
     * @return
     */
    public static int dayOfWeek() {
        Calendar nowDate = GregorianCalendar.getInstance();
        return nowDate.get(Calendar.DAY_OF_WEEK);
    }

    /**
     * 获取传入日期的星期
     *
     * @param date
     * @return
     */
    public static String dayWeek(Date date) {
        String[] weeks = {"sunday", "monday", "tuesday", "wednesday", "thursday", "friday", "saturday"};
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int dayInweek = cal.get(Calendar.DAY_OF_WEEK) - 1;
        return weeks[dayInweek];
    }

    /**
     * 当前时间与传入时间的比较
     */
    public static boolean distTime(String startTime, String endTime) {
        Calendar nowTime = Calendar.getInstance();
        Calendar sTime = Calendar.getInstance();
        Calendar eTime = Calendar.getInstance();
        nowTime.set(Calendar.SECOND, 0);
        String[] sa = startTime.split(":");
        String[] ea = endTime.split(":");
        if (sa.length > 1 && ea.length > 1) {
            sTime.set(Calendar.HOUR_OF_DAY, Integer.valueOf(sa[0]));
            sTime.set(Calendar.MINUTE, Integer.valueOf(sa[1]));
            eTime.set(Calendar.HOUR_OF_DAY, Integer.valueOf(ea[0]));
            eTime.set(Calendar.MINUTE, Integer.valueOf(ea[1]));
            return nowTime.compareTo(sTime) > 0 && nowTime.compareTo(eTime) < 0;
        }
        return true;
    }


    public static Date lastWeekBegin() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DATE, -8);
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        Calendar dayInWeek = (Calendar) calendar.clone();
        dayInWeek.add(Calendar.DATE, calendar.getActualMinimum(Calendar.DAY_OF_WEEK) - dayOfWeek + 1);
        Date firstDayInWeek = dayInWeek.getTime();
        return formatDate(firstDayInWeek);
    }

    public static Date lastWeekEnd() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(lastWeekBegin());
        calendar.add(Calendar.DATE, 6);
        Date endDayInWeek = calendar.getTime();

        return formatDate(endDayInWeek);
    }

    /**
     * 返回date集合
     *
     * @param begin
     * @param end
     * @return
     */
    public static List<Date> days(String begin, String end) {
        int distDates = distDates(stringToDate(begin), stringToDate(end)) + 1;
        List<Date> days = new ArrayList<Date>();
        for (int i = 1; i <= distDates; i++) {
            days.add(dayMoveNoFormat(stringToDate(begin), distDates - i));
        }
        return days;
    }

    public static List<Date> days(Date begin, Date end) {
        int distDates = distDates(begin, end) + 1;
        List<Date> days = new ArrayList<Date>();
        for (int i = 1; i <= distDates; i++) {
            days.add(dayMoveNoFormat(begin, distDates - i));
        }
        return days;
    }

    public static List<Date> days(String dateStr) {
        Date date = stringToDate(dateStr);
        List<Date> days = new ArrayList<Date>();
        for (int i = 0; i < 2; i++) {
            days.add(DateUtil.dayMoveNoFormat(date, 0 - i));
        }
        return days;
    }

    public static void main(String[] a) {
        System.out.println(lastDayOfMonth(2013, 12).getTime());
        System.out.println(firstDayOfMonth(2013, 12));
    }

    public static boolean belongToday(Date createAt) {
//        return new DateTime(dateTime.getYearOfCentury(),dateTime.getMonthOfYear(),dateTime.getMonthOfYear(),0,0,0).toDate();
        Date startdate = new Date();
        startdate.setHours(0);
        startdate.setMinutes(0);
        startdate.setSeconds(0);

        Date enddate = new Date();
        enddate.setHours(23);
        enddate.setMinutes(59);
        enddate.setSeconds(19);

        return createAt.after(startdate) && createAt.before(enddate);
    }

    public static Long dateTime() {
        return new Date().getTime() / 1000;
    }

    public static Long dateTime(Date date) {
        return date.getTime() / 1000;
    }

    /**
     * 获取一天的最后一毫秒值
     * @param date
     * @return
     */
    public static long getEndOfDay(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        cal.set(Calendar.MILLISECOND, 999);
        return cal.getTimeInMillis();
    }



    /**
     * 获取一天的第一毫秒值
     * @param date
     * @return
     */
    public static long getBeginOfDay(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 00);
        cal.set(Calendar.MINUTE, 00);
        cal.set(Calendar.SECOND, 00);
        cal.set(Calendar.MILLISECOND, 000);
        return cal.getTimeInMillis();
    }

    /**
     * 获取一个月的第一毫秒
     * @param date
     * @return
     */
    public static long getBeginOfMonth(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.DAY_OF_MONTH, 01);
        cal.set(Calendar.HOUR_OF_DAY, 00);
        cal.set(Calendar.MINUTE, 00);
        cal.set(Calendar.SECOND, 00);
        cal.set(Calendar.MILLISECOND, 000);
        return cal.getTimeInMillis();
    }

    /**
     * 获取一个月的最后一毫秒
     * @param date
     * @return
     */
    public static long getEndOfMonth(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MONTH, 1);
        return getBeginOfMonth(cal.getTime()) - 1;
    }

    /**
     * 星期几，从1到7
     * @param date
     * @return
     */
    public static int getDayOfWeek(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int index = cal.get(Calendar.DAY_OF_WEEK);
        index -= 1;
        if(index == 0) {
            index = 7;
        }
        return index;
    }

}
