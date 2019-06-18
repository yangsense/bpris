package com.ai.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by IntelliJ IDEA.
 * User: zongjianhui
 * Date: 13-1-6
 * Time: 下午4:19
 * To change this template use File | Settings | File Templates.
 */
public class TimeUtil {

    public static Date addHour(Date date,int _hour) throws Exception{
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(date);

        if (_hour > 0)
            //cal.add(GregorianCalendar.HOUR, _hour);
        	cal.add(Calendar.HOUR, _hour);
        return cal.getTime();
    }

    public static Date addMinute(Date date,int _minute) throws Exception{
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(date);

        if (_minute > 0)
           // cal.add(GregorianCalendar.MINUTE, _minute);
        	cal.add(Calendar.MINUTE, _minute);
        return cal.getTime();
    }


    public static int compareTime(Date _date1,Date _date2){
        if(_date1 ==null || _date2 ==null){
              throw new RuntimeException("传入的时间为空");
        }

        Calendar c1=Calendar.getInstance();
        Calendar c2=Calendar.getInstance();
        c1.setTime(_date1);
        c2.setTime(_date2);
        return c1.compareTo(c2);

    }

    public static Long getTimeInMillis(){
       return getTimeInMillis(0,0,0,0);
    }

    public static Long getTimeInMillis(int hour,int second,int minute,int millisecond){
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, hour);
        cal.set(Calendar.SECOND, second);
        cal.set(Calendar.MINUTE, minute);
        cal.set(Calendar.MILLISECOND, millisecond);
        return cal.getTimeInMillis();
    }

    public static Long getDateTime(String _date) throws ParseException {
       if(_date != null && !"".equals(_date)){

           SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
           Date date = df.parse(_date);
           return date.getTime();

       }
        return null;

    }

    public static Long getCurrentTimeInMillis(){
        Calendar cal = Calendar.getInstance();

        return cal.getTimeInMillis();
    }

    /**
     * time 格式是hh:MM:ss
     * @param time
     * @return
     */
    public static Long getTimeMilliseconds(String time){
        Calendar cal = Calendar.getInstance();
        if(time != null && !"".equals(time)){
            String[] array = time.split(":");
            if(array.length > 0 && array.length < 4){
                cal.set(Calendar.HOUR_OF_DAY, Integer.parseInt(array[0]) == 0?0:Integer.parseInt(array[0]));
                cal.set(Calendar.MINUTE, Integer.parseInt(array[1]));
                cal.set(Calendar.SECOND, Integer.parseInt(array[2]));
                cal.set(Calendar.MILLISECOND, 0);
            }
        }

        return cal.getTimeInMillis();
    }

    public static String getCurrentDate(Date date){
        return  getCurrentDate(date,"yyyy-MM-dd HH:mm:ss");
    }

    public static String getCurrentDate(Date date,String formateString){
        SimpleDateFormat dateformat=new SimpleDateFormat(formateString);
        return  dateformat.format(date);
    }


    public static void main(String [] args){
        String sql = "from_unixtime(unix_tim'%%%'estamp())), - 1)), 1, 7), -01) where like '%%%'";
//        String s = "^set.+=";

        sql = sql.toLowerCase();

        //去除set 语句
//        Pattern pattern = Pattern.compile("^set.+=");
//        Matcher matcher = pattern.matcher(sql);
//        System.out.println(matcher.find());

        //去除数字.数字
//        sql = sql.replaceAll("\\d+(\\.\\d+)*\\.\\d+"," test ");

        //去除create 前缀
//        sql = sql.replaceAll("^\\s+create\\s+table\\s+.+\\s+as\\s+select"," select ");

//        sql = sql.replaceAll(",\\s*-\\s*\\d+",",jianshuzi");  //去除-123 示例 replace(aa,/,-123) 结果 replace(aa,xiegang,jianshuzi)

        sql = sql.replaceAll("'\\s*%+\\s*'","baifenhao");  //去除%/ 示例 replace(aa,/,'%%%') 结果 replace(aa,xiegang,baifenhao)

//        sql = sql.replaceAll("((((1[6-9]|[2-9]\\d)\\d{2})-(0?[13578]|1[02])-(0?[1-9]|[12]\\d|3[01]))|(((1[6-9]|[2-9]\\d)\\d{2})-(0?[13456789]|1[012])-(0?[1-9]|[12]\\d|30))|(((1[6-9]|[2-9]\\d)\\d{2})-0?2-(0?[1-9]|1\\d|2[0-8]))|(((1[6-9]|[2-9]\\d)(0[48]|[2468][048]|[13579][26])|((16|[2468][048]|[3579][26])00))-0?2-29-)) (20|21|22|23|[0-1]?\\d):[0-5]?\\d:[0-5]?\\d","aaaaaaaaaaaaaaaaaaaaaaaa,"); //去除特殊资源/ 示例 replace(aa,/,-) 结果 replace(aa,/,hanggang)

//        sql = sql.replaceAll(",\\s*-",",henggang");
        System.out.println(sql);
    }
}
