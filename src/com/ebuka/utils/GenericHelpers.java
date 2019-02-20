package com.ebuka.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class GenericHelpers {
	static DateFormat myDateTimeFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
	static DateFormat myDateFormat = new SimpleDateFormat("dd/M/yyyy");
	static DateFormat myTimeFormat = new SimpleDateFormat("hh:mm aaa");
	static TimeZone lagosTimeZone = TimeZone.getTimeZone("Africa/Lagos");
	
	public static java.sql.Date getDateFromString(String myDate)
	{
		java.sql.Date sqlDate = null;
		try {
			myDateTimeFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
			Date dt =(Date) new SimpleDateFormat(myDateTimeFormat.toString()).parse(myDate);
			sqlDate = new java.sql.Date(dt.getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		System.out.println("Converted Date String: " + sqlDate);
		return sqlDate;
	}
	
	public static String getStringFromDate(Date myDate)
	{
		String strDate = myDateTimeFormat.format(myDate);  
        System.out.println("Converted String Date: " + strDate);
        return strDate;
	}
	
	public static Date getCurrentDate()
	{
		Calendar cal = Calendar.getInstance(lagosTimeZone);         
		Date timeNow = new Date(cal.getTimeInMillis());
		System.out.println("Current Date: " + timeNow);
		return timeNow;
	}
	
	public static String getCurrentDateString(){
        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("dd/M/yyyy HH:mm:ss");
        String sendingDate = df.format(c.getTime());
        System.out.println("Current Date in String: " + sendingDate);
        return sendingDate ;
    }
	
	public static long getCurrentDateTimestamp()
	{
		Date dNow = new Date();
        Calendar calendar = Calendar.getInstance(lagosTimeZone);
        calendar.setTime(dNow);
        long timestamp = calendar.getTimeInMillis();
        
        System.out.println("Current Date in timestamp: " + timestamp);
        return timestamp;
	}
	
	public static String getCurrentTime(){
        Calendar c = Calendar.getInstance();
        SimpleDateFormat sendingTime = new SimpleDateFormat(myTimeFormat.toString());
        String currentTime =  sendingTime.format(c.getTime());
        
        System.out.println("Current Time: " + currentTime);
        return currentTime;
    }
	
	public static String getCurrentDateWithoutTime(){
        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat(myDateFormat.toString());
        String sendingDate = df.format(c.getTime());
        
        System.out.println("Current Date without Time: " + sendingDate);

        return sendingDate ;
    }
	
	public static boolean isValidAccountNumber(String target)
    {
        boolean valid = true;
        String regexStr = "^[0-9]{10}$";
        if (target == null || target.matches(regexStr)==false) {
            valid = false;
        }
        return valid;

        /**if (target.length() < 10 || target.length() > 13) {
         return false;
         } else {
         return android.util.Patterns.PHONE.matcher(target).matches();
         }**/
    }
	
	/**private static byte[] getSalt() throws NoSuchAlgorithmException
    {
        SecureRandom sr = SecureRandom.getInstance("SHACHUKWUEBUKA");
        byte[] salt = new byte[16];
        sr.nextBytes(salt);
        return salt;
    }**/
	
	public static String getHashString(String stringToHash)
    {
        String generatedString = null;
        MessageDigest md = null;
        
        try {
            md = MessageDigest.getInstance("SHA-512");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        byte[] digest = md.digest(stringToHash.getBytes());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < digest.length; i++) {
            sb.append(Integer.toString((digest[i] & 0xff) + 0x100, 16).substring(1));
        }
        generatedString = sb.toString().toUpperCase();
        
        return generatedString;
    }
}
