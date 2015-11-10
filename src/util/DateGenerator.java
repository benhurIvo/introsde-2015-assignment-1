/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Random;

/**
 *
 * @author benhur
 */
public class DateGenerator {
    
     public static String generateDate(int d1,int d2) {
	 Calendar cal2 = Calendar.getInstance();
        cal2.set(Calendar.YEAR, d1); // today minus one year
        Date dMin = cal2.getTime();
        cal2.set(Calendar.YEAR, d2); // today plus one year
        Date dMax = cal2.getTime();
	
        long MILLIS_PER_DAY = 1000*60*60*24;
        GregorianCalendar s = new GregorianCalendar();
        s.setTimeInMillis(dMin.getTime());
        GregorianCalendar e = new GregorianCalendar();
        e.setTimeInMillis(dMax.getTime());
       
        // Get difference in milliseconds
        long endL   =  e.getTimeInMillis() +  e.getTimeZone().getOffset(e.getTimeInMillis());
        long startL = s.getTimeInMillis() + s.getTimeZone().getOffset(s.getTimeInMillis());
        long dayDiff = (endL - startL) / MILLIS_PER_DAY;
       
        Calendar cal = Calendar.getInstance();
        cal.setTime(dMin);
        cal.add(Calendar.DATE, new Random().nextInt((int)dayDiff));          
        return new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX").format(cal.getTime());
    }
}
