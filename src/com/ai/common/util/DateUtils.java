package com.ai.common.util;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 时间工具自定义标签函数用
 * @author fangll
 *
 */
public class DateUtils {  
	
	private static Log logger=LogFactory.getLog(DateUtils.class);		

	/**
	 * getTime
	 * @return
	 */
	public static Date getTime(){
		Calendar calendar=Calendar.getInstance();
		return calendar.getTime();
	}	

	/**
	 * legal holiday  such as lundar 0101-0107  solar 0501-0507,1001-1007
	 * @param date
	 * @return
	 * @throws Exception
	 */
	public static boolean isHoliday(Date date) throws Exception{		
		String str=DateUtils.formatDate(date, "MMdd");
		GregorianCalendar cal = DateUtils.dateConvCalendar(date);
		String lunarStr=LunarCalendar.solarToLundar(
				cal.get(Calendar.YEAR),cal.get(Calendar.MONTH)+1,
				cal.get(Calendar.DATE));	
		logger.debug("\r\n\t "+cal.get(Calendar.YEAR)+(cal.get(Calendar.MONTH)+1)+
				cal.get(Calendar.DATE));
		logger.debug(lunarStr);			
		lunarStr=StringUtils.substring(lunarStr,4);
		ArrayList<String> chunjie=new ArrayList<String>();
		ArrayList<String> list=new ArrayList<String>();
		list.add("0101");list.add("0102");list.add("0103");    //元旦1-3
		for (int i = 1; i <=7; i++) {
			list.add("050"+i);           //五一
			list.add("100"+i);           //十一
			chunjie.add("010"+i);        //农历春节   
		}			
		if(list.indexOf(str)!=-1 ||chunjie.indexOf(lunarStr)!=-1){
			return true;
		}else{
			return false;
		}		
	}
	
	/**
	 * get day of week such as (1,2...7)
	 * @param date
	 * @return
	 */
	public static int dayOfWeek(Date date){		
		Calendar aCalendar=Calendar.getInstance();
		aCalendar.setTime(date);
		int x=aCalendar.get(Calendar.DAY_OF_WEEK);
		switch (x) {
			case Calendar.MONDAY:	x=1;break;
			case Calendar.TUESDAY:	x=2;break;
			case Calendar.WEDNESDAY:x=3;break;
			case Calendar.THURSDAY:	x=4;break;
			case Calendar.FRIDAY:	x=5;break;
			case Calendar.SATURDAY:	x=6;break;		
			case Calendar.SUNDAY:	x=7;break;
		}
		return x;	
	}
	
	/**
	 * get all date(Date) list between begin date and end date
	 * @param beginDate
	 * @param endDate
	 * @param dateFormat
	 * @return
	 * @throws Exception
	 */
	public static ArrayList getDateList(Object beginDate, Object endDate,
			String dateFormat) throws Exception {
		ArrayList list = new ArrayList();
		int count = DateUtils.getDiffDays(beginDate, endDate, dateFormat);		
		Date date;
		if (count > 0) {
			date = DateUtils.objectConvDate(beginDate, dateFormat);
		} else {
			date = DateUtils.objectConvDate(endDate, dateFormat);
		}
		for (int i = 0; i < count + 1; i++) {
			list.add(date);
			logger.debug(DateUtils.formatDate(date, dateFormat));
			date = DateUtils.addDay(date, 1);
		}
		return list;
	}
	

	public static Date addMinute(Date date, int num) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(cal.MINUTE, num);
		return cal.getTime();
	}	
	
	public static Date addHour(Date date, int num) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(cal.HOUR, num);
		return cal.getTime();
	}
	
	public static Date addDay(Date date, int num) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(cal.DATE, num);
		return cal.getTime();
	}
	
	public static Date addMonth(Date date, int num) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(cal.MONTH, num);
		return cal.getTime();
	}	
	
	public static Date addYear(Date date, int num) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(cal.YEAR, num);
		return cal.getTime();
	}		
	/**
	 * object conver to date 
	 * @param date           can be String,Date,GregorianCalendar
	 * @param format
	 * @return
	 * @throws Exception
	 */
	public static Date objectConvDate(Object date,String format){
		Date dateA = new Date();
		if(date instanceof String){
			SimpleDateFormat sdf = new SimpleDateFormat(format);	
			try{
				dateA = sdf.parse((String) date);		
			}catch(Exception ex){
				ex.printStackTrace();
			}
    	}else if(date instanceof Date){
    		dateA=(Date)date;
		}else if(date instanceof GregorianCalendar){
			dateA=((GregorianCalendar)date).getTime();    	
		}else{
    		throw new java.lang.IllegalArgumentException("无效的参数");
    	}					
		return dateA;
	}
    /**
	 * java.util.Date Convert java.util.GregorianCalendar
	 * @param date java.util.Date
	 * @return GregorianCalendar
	 */
	public static GregorianCalendar dateConvCalendar(Date date) {
		GregorianCalendar cal = new GregorianCalendar(
				date.getYear() + 1900,date.getMonth(), date.getDate(),
				date.getHours(),date.getMinutes(), date.getSeconds() );
		return cal;
	}
	
	/**
	 * get today before or after day
	 * @param count >0 after day,<0 berfor day
	 * @return
	 */
	public static GregorianCalendar getCurAfterDayCal(int count) {
		GregorianCalendar cal = new GregorianCalendar();
		cal.add(Calendar.DATE, count);
		return cal;
	}	
	  /**
     *  format Calendar
     * @param cal             GregorianCalendar,Date           
     * @param dateFormat      日期格式:缺省为E yyyy年MM月dd日常用格式yyyyMMdd HH:mm:ss
     * @return
	 * @throws Exception 
     */
    public static String formatDate(Object date, String dateFormat){
    	if(date==null) return "";
    	GregorianCalendar cal=DateUtils.dateConvCalendar(DateUtils.objectConvDate(date, dateFormat));    	
		String str = dateFormat == null ? "E yyyy年MM月dd日" : dateFormat;
		SimpleDateFormat format = new SimpleDateFormat(str);
		String curdate = format.format(cal.getTime());		
		return curdate;
	}
    
    /**
	 * get day between date1 and date2
	 * @param beginDate    can be String,Date,GregorianCalendar type
	 * @param endDate      can be String,Date,GregorianCalendar type
	 * @param format       if String must be set date format
	 * @return
	 * @throws Exception
	 */
    public static int getDiffDays(Object beginDate ,
    		Object endDate,String format) throws Exception {
    	Object[] objs=new Object[2];
    	objs[0]=beginDate;
    	objs[1]=endDate;
    	Date[] tmpDate=new Date[2];    	
    	for (int i = 0; i < objs.length; i++) {
    		tmpDate[i]=DateUtils.objectConvDate(objs[i], format);
    		logger.debug(tmpDate[i]);
		}    	
		long lBeginTime = tmpDate[0].getTime();
		long lEndTime = tmpDate[1].getTime();
		int iDay = (int) ((lEndTime - lBeginTime) / 86400000);
		return iDay;
	}
    
    /**
     * 
     * 功能描述：
     *
     * @author  余根宁
     * <p>创建日期 ：May 30, 2011 3:42:59 PM</p>
     *
     * @param dateStr
     * @return
     *
     * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
     */
    public static Date parseDate(String dateStr){
        Date date = null;
        if(StringUtils.isBlank(dateStr)){
            return null;
        }
        String tmpStr = dateStr.replaceAll("-", "");
        tmpStr = tmpStr.replaceAll("/", "");
        tmpStr = tmpStr.replaceAll(" ", "");
        String format = "";
        if(StringUtils.isBlank(tmpStr)){
            return null;
        }
        
        if(tmpStr.length()==4){
            format = "yyyy";
        } else if(tmpStr.length()==6){
            format = "yyyyMM";
        } else if(tmpStr.length()==8){
            format = "yyyyMMdd";
        } else if(tmpStr.length()==14){
            format = "yyyyMMddHHmmss";
        }
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        try{
            return formatter.parse(tmpStr);
        } catch(ParseException err){
            err.printStackTrace();
            return null;
        }
        
    }
    
    /**
	 * 功能描述：根据日期字符串创建的Timestamp对象,使用的日期格式：yyyy-MM-dd HH:mm:ss
	 * 
	 * @author 吴宇振 2012-2-22
	 * 
	 * @param dateStr 日期字符串
	 * @return Timestamp对象。
	 */
	public static Timestamp parseTimestamp(String dateStr) {
		return parseTimestamp(dateStr, "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 功能描述：根据指定的日期格式和日期字符串创建的Timestamp对象
	 * 
	 * @author 吴宇振 2012-2-22
	 * 
	 * @param dateStr 日期字符串
	 * @pramm dateFormat 日期格式
	 * @return Timestamp对象。
	 */
	public static Timestamp parseTimestamp(String dateStr, String dateFormat) {
		SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
		try {
			Date date = sdf.parse(dateStr);
			return new Timestamp(date.getTime());
		} catch (ParseException e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}
	}
	
	/**
	 * 功能描述：根据指定的日期格式和日期字符串创建的Date对象
	 * 
	 * @author 吴宇振 2012-2-27
	 * 
	 * @param dateStr 日期字符串
	 * @param dateFormat 日期格式
	 * @return
	 */
	public static Date parseDate(String dateStr, String dateFormat) {
		SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
		try {
			return sdf.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}
	}
	
	/**
	 * 取得数据库时间，解析时间
	 */
	public static String getDateyyyymm(Timestamp t) {
		String str = t.toString();
		str = str.replace("-", "");
		String date = str.substring(0, 6);
		return date;
	}

	public static String getDateddmmmiss(Timestamp t) {
		String str = t.toString();
		str = str.replace("-", "").replace(":", "").replace(" ", "");
		String time = str.substring(6, 14);
		return time;
	}
	
	
	/**
	 * 判断时间是否在时间段内
	 * 
	 * @param date
	 *            当前时间 yyyy-MM-dd HH:mm:ss
	 * @param strDateBegin
	 *            开始时间 00:00:00
	 * @param strDateEnd
	 *            结束时间 00:05:00
	 * @return
	 */
	public static boolean isInDate(Date date, String strDateBegin,
			String strDateEnd) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String strDate = sdf.format(date);
		// 截取当前时间时分秒
		int strDateH = Integer.parseInt(strDate.substring(11, 13));
		int strDateM = Integer.parseInt(strDate.substring(14, 16));
		int strDateS = Integer.parseInt(strDate.substring(17, 19));
		// 截取开始时间时分秒
		int strDateBeginH = Integer.parseInt(strDateBegin.substring(0, 2));
		int strDateBeginM = Integer.parseInt(strDateBegin.substring(3, 5));
		int strDateBeginS = Integer.parseInt(strDateBegin.substring(6, 8));
		// 截取结束时间时分秒
		int strDateEndH = Integer.parseInt(strDateEnd.substring(0, 2));
		int strDateEndM = Integer.parseInt(strDateEnd.substring(3, 5));
		int strDateEndS = Integer.parseInt(strDateEnd.substring(6, 8));
		if ((strDateH >= strDateBeginH && strDateH <= strDateEndH)) {
			// 当前时间小时数在开始时间和结束时间小时数之间
			if (strDateH > strDateBeginH && strDateH < strDateEndH) {
				return true;
				// 当前时间小时数等于开始时间小时数，分钟数在开始和结束之间
			} else if (strDateH == strDateBeginH && strDateM >= strDateBeginM
					&& strDateM <= strDateEndM) {
				return true;
				// 当前时间小时数等于开始时间小时数，分钟数等于开始时间分钟数，秒数在开始和结束之间
			} else if (strDateH == strDateBeginH && strDateM == strDateBeginM
					&& strDateS >= strDateBeginS && strDateS <= strDateEndS) {
				return true;
			}
			// 当前时间小时数大等于开始时间小时数，等于结束时间小时数，分钟数小等于结束时间分钟数
			else if (strDateH >= strDateBeginH && strDateH == strDateEndH
					&& strDateM <= strDateEndM) {
				return true;
				// 当前时间小时数大等于开始时间小时数，等于结束时间小时数，分钟数等于结束时间分钟数，秒数小等于结束时间秒数
			} else if (strDateH >= strDateBeginH && strDateH == strDateEndH
					&& strDateM == strDateEndM && strDateS <= strDateEndS) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
}
