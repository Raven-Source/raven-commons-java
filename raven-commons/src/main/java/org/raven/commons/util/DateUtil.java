package org.raven.commons.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.commons.lang3.time.FastDateFormat;
import org.raven.commons.constant.DateFormatStringConstant;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * @author yi.liang
 * @since JDK1.8
 * date 2019.06.24 18:19
 */
@Slf4j
public class DateUtil {

    private DateUtil() {
    }

    public static final FastDateFormat ISO_DATE_FORMAT = FastDateFormat.getInstance(DateFormatStringConstant.DATE_FORMAT_STRING);
    public static final FastDateFormat DATE_MONTH_FORMAT = FastDateFormat.getInstance("yyyyMM");
    public static final FastDateFormat SIMPLE_DATE_FORMAT = FastDateFormat.getInstance("yyyy-MM-dd HH:mm:ss");
    public static final FastDateFormat DATE_DAY_FORMAT = FastDateFormat.getInstance("yyyyMMdd");
    public static final FastDateFormat DATE_TIME17_FORMAT = FastDateFormat.getInstance("yyyyMMddHHmmssSSS");
    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern(DateFormatStringConstant.DATE_FORMAT_STRING);

    /**
     * @param date
     * @return
     */
    public static String format(Date date) {
        if (date == null)
            return null;
        return ISO_DATE_FORMAT.format(date);
    }

    // /**
    //  *
    //  * @return
    //  */
    // public static String formatTime14() {
    //     // if (date == null)
    //     //     return null;
    //     String format = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    //     return format;
    // }

    /**
     * @return
     */
    public static String formatTime14(Date date) {
        if (date == null)
            return null;
        return SIMPLE_DATE_FORMAT.format(date);
    }

    /**
     * @param date
     * @return
     */
    public static String format(ZonedDateTime date) {
        if (date == null)
            return null;
        return date.format(DATE_TIME_FORMATTER);
    }

    /**
     * @param str
     * @return
     */
    public static Date parse(String str) {

        try {
            Long time = Long.parseLong(str);
            return new Date(time);
        } catch (Exception ex) {
        }

        try {
            return DateUtils.parseDate(str, DateFormatStringConstant.DESERIALIZE_DATE_FORMAT_STRING);
        } catch (Exception ex) {
        }

        return null;
    }

    /**
     * @param obj
     * @return
     */
    public static Date toDate(LocalDate obj) {

        if (obj == null)
            return null;

        ZoneId zone = ZoneId.systemDefault();
        Instant instant = obj.atStartOfDay().atZone(zone).toInstant();
        return Date.from(instant);

    }

    /**
     * @param obj
     * @return
     */
    public static Date toDate(LocalDateTime obj) {

        LocalDateTime localDateTime = LocalDateTime.now();
        ZoneId zone = ZoneId.systemDefault();
        Instant instant = localDateTime.atZone(zone).toInstant();
        return Date.from(instant);
    }

    /**
     * @param obj
     * @return
     */
    public static Date toDate(LocalDate localDate, LocalTime obj) {

        LocalDateTime localDateTime = LocalDateTime.of(localDate, obj);
        ZoneId zone = ZoneId.systemDefault();
        Instant instant = localDateTime.atZone(zone).toInstant();
        return Date.from(instant);
    }

    /***
     * 获取当前日期 	格式：201001
     * @return
     */

    public static String formatDateMonth(Date date) {
        if (date == null)
            return null;
        return DATE_MONTH_FORMAT.format(date);
    }

    public static Date parseDateMonth(String str) {
        if(StringUtil.isEmpty(str)){
            return null;
        }
        try {
            return DATE_MONTH_FORMAT.parse(str);
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
            return null;
        }
    }

    /***
     * 获取当前日期 	格式：20100101
     * @return
     */
    public static String formatDateDay(Date date) {
        if (date == null)
            return null;
        return DATE_DAY_FORMAT.format(date);
    }

    /***
     * 获取当前时间 格式：20150101120101000
     * @return
     */
    public static String formatDateTime17(Date date) {
        if (date == null)
            return null;
        return DATE_TIME17_FORMAT.format(date);
    }
}