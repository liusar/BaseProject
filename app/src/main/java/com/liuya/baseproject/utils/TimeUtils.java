package com.liuya.baseproject.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import android.R.integer;


/**
 * 专门处理有关时间、日期等业务的类
 *
 */
public class TimeUtils {

    /**
     * 将 当前时间 与 指定时间 做差运算，得到指定格式的返回值
     *
     * @param date 指定时间
     * @return 返回 ："XX秒前"、"XX分钟前"、"XX小时前"、或 "yyyy-MM-dd HH:mm:ss" 四种格式中的一种
     */
    public static String getCreateString(Date date) {
        return getCreateString(date, null, false);
    }

    /**
     * 将 当前时间 与 指定时间 做差运算，得到指定格式的返回值
     *
     * @param date     指定时间
     * @param template 返回值的一种指定格式
     * @return 返回 ："XX秒前"、"XX分钟前"、"XX小时前"、或 template(默认为:"yyyy-MM-dd HH:mm:ss") 四种格式中的一种
     */
    public static String getCreateString(Date date, String template) {
        return getCreateString(date, template, false);
    }

    /**
     * 将 当前时间 与 指定时间 做差运算，得到指定格式的返回值
     *
     * @param date        指定时间
     * @param template    返回值的一种指定格式
     * @param showJustNow 是否返回"刚刚"这种格式的返回值
     * @return 返回 ："刚刚"、"XX秒前"、"XX分钟前"、"XX小时前"、或 template(默认为:"yyyy-MM-dd HH:mm:ss") 五种格式中的一种
     */
    public static String getCreateString(Date date, String template, boolean showJustNow) {
        // 将参数date转换为Calendar格式
        Calendar calendarOld = Calendar.getInstance();
        if (date != null)
            calendarOld.setTime(date);
        // 返回值的格式
        if (StringUtils.isNullOrEmpty(template)) {
            template = "MM-dd HH:mm";
        }
        SimpleDateFormat sdf = new SimpleDateFormat(template, Locale.US);
        // 当前系统时间
        Calendar calendarNow = Calendar.getInstance();
        if (calendarNow.get(Calendar.YEAR) - calendarOld.get(Calendar.YEAR) > 0) {
            SimpleDateFormat sdfYear = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.US);
            return sdfYear.format(date);
        } else if (calendarNow.get(Calendar.MONTH) - calendarOld.get(Calendar.MONTH) > 0) {
            return sdf.format(date);
        } else if (calendarNow.get(Calendar.DAY_OF_MONTH) - calendarOld.get(Calendar.DAY_OF_MONTH) > 0) {
            return sdf.format(date);
        } else if (calendarNow.get(Calendar.HOUR_OF_DAY) - calendarOld.get(Calendar.HOUR_OF_DAY) > 0) {
            int i = calendarNow.get(Calendar.HOUR_OF_DAY) - calendarOld.get(Calendar.HOUR_OF_DAY);
            return i + "小时前";
        } else if (calendarNow.get(Calendar.MINUTE) - calendarOld.get(Calendar.MINUTE) > 0) {
            int i = calendarNow.get(Calendar.MINUTE) - calendarOld.get(Calendar.MINUTE);
            return i + "分钟前";
        } else if (calendarNow.get(Calendar.SECOND) - calendarOld.get(Calendar.SECOND) > 0) {
            int i = calendarNow.get(Calendar.SECOND) - calendarOld.get(Calendar.SECOND);
            return i + "秒前";
        } else if (showJustNow
                && calendarNow.get(Calendar.SECOND) - calendarOld.get(Calendar.SECOND) == 0) {
            return "刚刚";
        } else {
            return sdf.format(date);
        }
    }

    /**
     * 将 当前时间 与 指定时间 做差运算，判断是否是参数xhours小时之前
     *
     * @param dateString   指定时间,yyyy-MM-dd-HH-mm格式
     * @param xhours 间隔时间
     * @return true在x小时之前，false在x小时之内
     */
    public static Boolean isMoreThanHours(String dateString, int xhours) {
        return isMoreThanMinutes(dateString, xhours * 60);
    }

    /**
     * 将 当前时间 与 指定时间 做差运算，判断是否是参数xMinutes之前
     * @param dateString 指定时间,yyyy-MM-dd-HH-mm格式
     * @param xMinutes 间隔时间
     * @return true在xMinutes之前，false在xMinutes之内
     */
    public static Boolean isMoreThanMinutes(String dateString, int xMinutes) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-HH-mm");
        try {
            Date nowDate = new Date();
            long interval = nowDate.getTime() - sdf.parse(dateString).getTime();
            interval /= 1000 * 60; // 分
            if (interval >= xMinutes) {
                return true;
            } else {
                return false;
            }
        } catch (ParseException e) {
//            UtilsLog.log("CacheTime", "Error:" + e.getMessage());
        }
        return true;
    }

    /**
     * 将 当前时间 与 指定时间 做差运算，得到指定格式的返回值："刚刚"、XX秒前、XX分钟前、XX小时前、X天前、"yyyy-MM-dd"<br>
     * 如果差值大于6天，则显示"yyyy-MM-dd"; 如果差值大于0天，小于等于6天，则显示，X天前。
     *
     * @param date 指定时间
     * @return "刚刚"、XX秒前、XX分钟前、XX小时前、X天前、"yyyy-MM-dd"
     */
    public static String getDateString(Date date) {
        Calendar calendarOld = Calendar.getInstance();
        if (date != null) {
            calendarOld.setTime(date);// 将指定时间转换为日历对象
        }
        Calendar calendarNew = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        if (calendarNew.get(Calendar.YEAR) - calendarOld.get(Calendar.YEAR) > 0) {
            return sdf.format(date);
        } else if (calendarNew.get(Calendar.MONTH) - calendarOld.get(Calendar.MONTH) > 0) {
            return sdf.format(date);
        } else if (calendarNew.get(Calendar.DAY_OF_MONTH) - calendarOld.get(Calendar.DAY_OF_MONTH) >= 6) {
            return sdf.format(date);
        } else if ((calendarNew.get(Calendar.DAY_OF_MONTH) - calendarOld.get(Calendar.DAY_OF_MONTH) > 0)) {
            int i = calendarNew.get(Calendar.DAY_OF_MONTH) - calendarOld.get(Calendar.DAY_OF_MONTH);
            return i + "天前";
        } else if (calendarNew.get(Calendar.HOUR_OF_DAY) - calendarOld.get(Calendar.HOUR_OF_DAY) > 0) {
            int i = calendarNew.get(Calendar.HOUR_OF_DAY) - calendarOld.get(Calendar.HOUR_OF_DAY);
            return i + "小时前";
        } else if (calendarNew.get(Calendar.MINUTE) - calendarOld.get(Calendar.MINUTE) > 0) {
            int i = calendarNew.get(Calendar.MINUTE) - calendarOld.get(Calendar.MINUTE);
            return i + "分钟前";
        } else if (calendarNew.get(Calendar.SECOND) - calendarOld.get(Calendar.SECOND) > 0) {
            int i = calendarNew.get(Calendar.SECOND) - calendarOld.get(Calendar.SECOND);
            return i + "秒前";
        } else if (calendarNew.get(Calendar.SECOND) - calendarOld.get(Calendar.SECOND) == 0) {
            return "刚刚";
        } else {
            return sdf.format(date);
        }
    }

    /**
     * 获取"yyyy-MM-dd HH:mm:ss"格式的当前时间
     */
    public static String getStringDate() {
        return getStringDate(null);
    }

    /**
     * 获取当前时间的 template 格式<br>
     * <p/>
     * if template is null, template = "yyyy-MM-dd HH:mm:ss";
     */
    public static String getStringDate(String template) {
        if (StringUtils.isNullOrEmpty(template)) {
            template = "yyyy-MM-dd HH:mm:ss";
        }
        SimpleDateFormat formatter = new SimpleDateFormat(template, Locale.getDefault());
        return formatter.format(new Date());
    }

    /**
     * 时间格式转换(去掉秒)，yyyy-MM-dd xx:xx:xx为：yyyy-MM-dd HH:mm
     */
    public static String getStringDateRemoveSec(String date) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss",
                Locale.getDefault());
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault());
        Date d = new Date();
        try {
            d = formatter.parse(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        String dateString = f.format(d);
        return dateString;
    }

    /**
     * 时间格式转换。将 "yyyy-MM-dd HH:mm:ss" 转为templateTo格式<br>
     * <p/>
     * 如果templateTo传入空，则 templateTo = "yyyy-MM-dd";
     */
    public static String transDate(String dateStr, String templateTo) {
        if (StringUtils.isNullOrEmpty(templateTo)) {
            templateTo = "yyyy-MM-dd";
        }
        return transDate(dateStr, null, templateTo);
    }

    /**
     * 将指定日期 从 templateFrom 格式转换为 templateTo 格式。
     *
     * @param dateStr      字符串类型的日期
     * @param templateFrom 指定日期的模板(默认为)"yyyy-MM-dd HH:mm:ss"
     * @param templateTo   转换成的模板
     * @return 转换成templateTo格式的模板后的字符串
     */
    public static String transDate(String dateStr, String templateFrom, String templateTo) {
        if (StringUtils.isNullOrEmpty(templateFrom)) {
            templateFrom = "yyyy-MM-dd HH:mm:ss";
        }
        SimpleDateFormat sdfFrom = new SimpleDateFormat(templateFrom, Locale.getDefault());
        SimpleDateFormat sdfTo = new SimpleDateFormat(templateTo, Locale.getDefault());
        Date date = new Date();
        try {
            date = sdfFrom.parse(dateStr);
        } catch (Exception e) {
            e.printStackTrace();
            try {
                date = sdfTo.parse(dateStr);
            } catch (ParseException e1) {
                e1.printStackTrace();
            }
        }
        return sdfTo.format(date);
    }

    /**
     * 给定一个秒数，转化为00:00:00格式的时间
     *
     * @param time
     * @return
     */
    public static String formateTime(int time) {
        int mHour, mMinute, mSecond;// 时分秒
        StringBuilder sb_time = new StringBuilder();
        mHour = time / 3600;
        if (mHour > 9)
            sb_time.append(mHour + ":");
        else if (mHour >= 0) {
            sb_time.append("0" + mHour + ":");
        } else {
            sb_time.append("00:");
        }
        time = time % 3600;
        mMinute = time / 60;
        if (mMinute > 9) {
            sb_time.append(mMinute + ":");
        } else if (mMinute >= 0) {
            sb_time.append("0" + mMinute + ":");
        } else {
            sb_time.append("00:");
        }
        mSecond = time % 60;
        if (mSecond > 9) {
            sb_time.append(mSecond);
        } else if (mSecond >= 0) {
            sb_time.append("0" + mSecond);
        }
        return sb_time.toString();
    }

    /**
     * 获取保留3位小数的当前时间<br>
     */
    public static String getStringDateFor3Decimal() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS",
                Locale.getDefault());
        return format.format(new Date());
    }

    /**
     * 获取保留3位小数的当前时间减去一年<br>
     */
    public static String getStringDateFor3DecimalByLastyear() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS",
                Locale.getDefault());
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.YEAR, -1);
        return format.format(calendar.getTime());
    }

    /**
     * 返回 2014-9-1 2014-12-12;
     *
     * @param c
     * @return author 王松宾
     */
    public static String getStringDateWithCalendar(Calendar c) {
        String tempDate = new SimpleDateFormat("yyyy-MM-dd").format(c.getTime());
        String[] params = tempDate.split("-");
        StringBuffer buffer = new StringBuffer();
        buffer.append(params[0]);
        char firstChar = params[1].charAt(0);
        if (firstChar == '0') {
            params[1] = params[1].substring(1);
        }
        buffer.append("-" + params[1]);
        firstChar = params[2].charAt(0);
        if (firstChar == '0') {
            params[2] = params[2].substring(1);
        }
        buffer.append("-" + params[2]);
        return buffer.toString();
    }

    ;

    /**
     * 判断两个日期是否是同一天
     *
     * @param date1 date1
     * @param date2 date2
     * @return
     */
    public static boolean isSameDate(Date date1, Date date2) {
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);

        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);

        boolean isSameYear = cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR);
        boolean isSameMonth = isSameYear && cal1.get(Calendar.MONTH) == cal2.get(Calendar.MONTH);
        boolean isSameDate = isSameMonth
                && cal1.get(Calendar.DAY_OF_MONTH) == cal2.get(Calendar.DAY_OF_MONTH);

        return isSameDate;
    }

    /**
     * 返回中文单位的日期
     *
     * @param dateString   例如:2015-1-1 10:10:10
     * @param isIgnoreYear true:返回例如:“1月1日”格式;false:返回例如“2015年1月1日”格式
     * @return 例如：“2015年1月1日”或“1月1日”
     */
    public static String formatChineseDate(String dateString, boolean isIgnoreYear) {
        SimpleDateFormat format = null;
        Calendar calendar = Calendar.getInstance();
        String date = "";
        if (13 < dateString.length()) {
            format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        } else {
            format = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        }
        if (!StringUtils.isNullOrEmpty(dateString)) {
            try {
                calendar.setTime(format.parse(dateString));
                if (!isIgnoreYear) {
                    date = calendar.get(Calendar.YEAR) + "年";
                }
                date += ((calendar.get(Calendar.MONTH) + 1) + "月"
                        + calendar.get(Calendar.DAY_OF_MONTH) + "日");
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return date;
    }

    ;

    /**
     * 返回中文单位的日期
     *
     * @param dateString   例如:2015-01-01 10:10:10
     * @param isIgnoreYear true:返回例如:“01月01日”格式;false:返回例如“2015年01月01日”格式
     * @return 例如：“2015年01月01日”或“01月01日”
     */
    public static String formatChineseDate2(String dateString, boolean isIgnoreYear) {
        SimpleDateFormat format = null;
        Calendar calendar = Calendar.getInstance();
        String date = "";
        if (13 < dateString.length()) {
            format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        } else {
            format = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        }
        if (!StringUtils.isNullOrEmpty(dateString)) {
            try {
                calendar.setTime(format.parse(dateString));
                if (!isIgnoreYear) {
                    date = calendar.get(Calendar.YEAR) + "年";
                }
                if (calendar.get(Calendar.MONTH) + 1 < 10) {
                    date += "0" + (calendar.get(Calendar.MONTH) + 1) + "月";
                } else {
                    date += (calendar.get(Calendar.MONTH) + 1) + "月";
                }
                if (calendar.get(Calendar.DAY_OF_MONTH) < 10) {
                    date += "0" + calendar.get(Calendar.DAY_OF_MONTH) + "日";
                } else {
                    date += calendar.get(Calendar.DAY_OF_MONTH) + "日";
                }
                // date += ((calendar.get(Calendar.MONTH) + 1) + "月"
                // + calendar.get(Calendar.DAY_OF_MONTH) + "日");
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return date;
    }

    /**
     * 将 当前时间 与 指定时间 做差运算，得到指定的返回字符串：
     * "刚刚"、"30分钟之前"、"1个小时之前"、"今天"、"昨天"、"过去"<br>
     * <ul>
     * <li>"刚刚" 1-15分钟</li>
     * <li>"30分钟之前" 15-30分钟</li>
     * <li>"1个小时之前" 30分钟-1个小时</li>
     * <li>"今天" 超过1个小时且日期同一天</li>
     * <li>"昨天" 日期为后一天（不超过48小时）</li>
     * <li>"过去" 超过两天</li>
     * </ul>
     *
     * @param date 指定时间，单位 ：毫秒
     * @return "刚刚"、"30分钟之前"、"1个小时之前"、"今天"、"昨天"、"过去"
     */
    public static String getDateString(long date) {
        Calendar calendarOld = Calendar.getInstance();
        calendarOld.setTimeInMillis(date);// 将指定时间转换为日历对象

        Calendar calendarNew = Calendar.getInstance();

        long diff = calendarNew.getTimeInMillis() - calendarOld.getTimeInMillis();
        long minute_15 = 15 * 60 * 1000;
        long minute_30 = 30 * 60 * 1000;
        long hour_1 = 60 * 60 * 1000;
        long hour_24 = 24 * 60 * 60 * 1000;
        long hour_48 = 48 * 60 * 60 * 1000;

        if( diff <= 0){
//            return "系统时间有误";
            return "刚刚";
        }
        if ((calendarNew.get(Calendar.DAY_OF_YEAR) - calendarOld.get(Calendar.DAY_OF_YEAR) == 0)) {
            if( diff > 0 && diff <= minute_15){
                return "刚刚";
            }else if( diff > minute_15 && diff <= minute_30){
                return "30分钟之前";
            }else if( diff > minute_30 && diff <= hour_1){
                return "1个小时之前";
            }else if( diff > hour_1 && diff <= hour_24){
                return "今天";
            }else{//可能是同日不同年
                return "过去";
            }
        }else {
            if( diff <= hour_48){
                if(calendarNew.get(Calendar.DAY_OF_YEAR) - calendarOld.get(Calendar.DAY_OF_YEAR) == 1
                        || calendarNew.get(Calendar.DAY_OF_YEAR) - calendarOld.get(Calendar.DAY_OF_YEAR) == -365){//可能跨年
                    return "昨天";
                }else{
                    return "过去";
                }
            }else{
                return "过去";
            }
        }
    }
}
