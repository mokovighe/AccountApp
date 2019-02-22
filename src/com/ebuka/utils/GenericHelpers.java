package com.ebuka.utils;

import java.io.PrintWriter;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.ArrayUtils;

import com.google.gson.JsonObject;

import io.jsonwebtoken.impl.crypto.MacProvider;

public class GenericHelpers {
	public static Key key = null;
	public static final String AUTHORIZATION_HEADER = "Authorization";
	static DateFormat myDateTimeFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	static DateFormat myDateFormat = new SimpleDateFormat("dd-M-yyyy");
	static String myDateTimeFormatString = "yyyy-MM-dd hh:mm:ss";
	static String myDateFormatString = "yyy-MM-dd";
	static String myTimeFormatString = "hh:mm aaa";
	static DateFormat myTimeFormat = new SimpleDateFormat("hh:mm aaa");
	static TimeZone lagosTimeZone = TimeZone.getTimeZone("Africa/Lagos");
	public static String algorithmKey = "DESede";
	public static final String secretKey = "sec-ebuka";
	public static Key secret = MacProvider.generateKey();
	
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
	
	public static Date addMinuteToCurrentDate(int minute)
	{
		Calendar cal = Calendar.getInstance(lagosTimeZone);
		cal.add(Calendar.MINUTE, minute);
		Date timeNow = new Date(cal.getTimeInMillis());
		System.out.println("Current Date: " + timeNow);
		        
		return timeNow;
	}
	
	public static String addMonthToCurrentDate(int monthValue)
	{
		Calendar cal = Calendar.getInstance(lagosTimeZone);
		SimpleDateFormat df = new SimpleDateFormat(myDateTimeFormatString);
		cal.add(Calendar.MONTH, -monthValue);
		Date timeNow = new Date(cal.getTimeInMillis());
		String sendingDate = df.format(timeNow);
		System.out.println("Date " + monthValue + " months back:: " + sendingDate);
		        
		return sendingDate;
	}
	
	public static String getCurrentDateWithoutTime(){
        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat(myDateFormatString);
        String sendingDate = df.format(c.getTime());
        
        System.out.println("Current Date without Time: " + sendingDate);

        return sendingDate ;
    }
	
	public static String getCurrentDateString(){
        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat(myDateTimeFormatString);
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
        SimpleDateFormat sendingTime = new SimpleDateFormat(myTimeFormatString);
        String currentTime =  sendingTime.format(c.getTime());
        
        System.out.println("Current Time: " + currentTime);
        return currentTime;
    }
		
	public static boolean isValidAccountNumber(String target)
    {
        boolean valid = true;
        String regexStr = "^[0-9]{10}$";
        if (target == null || target.matches(regexStr)==false) {
            valid = false;
        }
        return valid;       
    }
	
	/**private static byte[] getSalt()
    {
        SecureRandom sr;
        byte[] salt = null;
		try {
			sr = SecureRandom.getInstance("SHACHUKWUEBUKA");
			salt = new byte[16];
	        sr.nextBytes(salt);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
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
	
	public static JsonObject jsonResponse(int httpCode, Boolean status, String message)
	{
		JsonObject json = new JsonObject();
		json.addProperty("STATUS", status);
		json.addProperty("HTTPCODE", httpCode);
		if(httpCode == 200)
		{
			json.addProperty("TOKEN", message);
		}
		else {
			json.addProperty("MESSAGE", message);
		}
		return json;
	}
	
	public static Map<String, String[]> getQueryParameters(HttpServletRequest req)
	{
		Map<String, String[]> queryParams = new HashMap<>();
		String queryString = req.getQueryString();
		if(queryString == null)
		{
			return queryParams;
		}
		String[] parameters = queryString.split("&");
		for(String parameter:parameters)
		{
			String[] keyValuePair = parameter.split("=");
			String[] values = queryParams.get(keyValuePair[0]);
			values = (String[]) ArrayUtils.add(values, keyValuePair.length==1 ? "" : keyValuePair[1]);
			queryParams.put(keyValuePair[0], values);
		}
		return queryParams;
	}
	
	public static void showMessage(PrintWriter out, JsonObject jsonRes)
	{
		//PrintWriter out = response.getWriter();
		try {
            out.println(jsonRes);
        } finally {
            out.close();
        }
	}
}
