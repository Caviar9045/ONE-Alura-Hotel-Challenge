package com.alura.hotel.logic;

import java.util.Date;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class ByDatePricing {
	
	public static Long getDaysNumber(Date date1, Date date2, double pricing){
		
        // Convert Date objects to LocalDate objects
        LocalDate localDate1 = date1.toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate();
        LocalDate localDate2 = date2.toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate();

        // Calculate the difference in days between the two LocalDate objects
        long daysDifference = ChronoUnit.DAYS.between(localDate1, localDate2);
		return (long) (daysDifference*pricing);
	}
	
}
