package id.co.bjj.library.general.core.utils;

import java.util.Calendar;
import java.util.Date;

public class AgeCalculatorUtil {
	private AgeCalculatorUtil() {		
	}
	
	public static AgeDto getFullAge(Date birthDate) {
		return calculateAge(birthDate);
	}

	public static Integer getYearAge(Date birthDate) {
		AgeDto ageDto = calculateAge(birthDate);
		return ageDto.getYear();
	}

	public static Integer getMonthAge(Date birthDate) {
		AgeDto ageDto = calculateAge(birthDate);
		return ageDto.getMonth();
	}

	public static Integer getDayAge(Date birthDate) {
		AgeDto ageDto = calculateAge(birthDate);
		return ageDto.getDay();
	}

	private static AgeDto calculateAge(Date birthDate) {
		int years = 0;
		int months = 0;
		int days = 0;

		// create calendar object for birth day
		Calendar birthDay = Calendar.getInstance();
		birthDay.setTimeInMillis(birthDate.getTime());

		// create calendar object for current day
		long currentTime = System.currentTimeMillis();
		Calendar now = Calendar.getInstance();
		now.setTimeInMillis(currentTime);

		// Get difference between years
		years = now.get(Calendar.YEAR) - birthDay.get(Calendar.YEAR);
		int currMonth = now.get(Calendar.MONTH) + 1;
		int birthMonth = birthDay.get(Calendar.MONTH) + 1;

		// Get difference between months
		months = currMonth - birthMonth;

		// if month difference is in negative then reduce years by one
		// and calculate the number of months.
		if (months < 0) {
			years--;
			months = 12 - birthMonth + currMonth;
			if (now.get(Calendar.DATE) < birthDay.get(Calendar.DATE))
				months--;
		} else if (months == 0 && now.get(Calendar.DATE) < birthDay.get(Calendar.DATE)) {
			years--;
			months = 11;
		}

		// Calculate the days
		if (now.get(Calendar.DATE) > birthDay.get(Calendar.DATE))
			days = now.get(Calendar.DATE) - birthDay.get(Calendar.DATE);
		else if (now.get(Calendar.DATE) < birthDay.get(Calendar.DATE)) {
			int today = now.get(Calendar.DAY_OF_MONTH);
			now.add(Calendar.MONTH, -1);
			days = now.getActualMaximum(Calendar.DAY_OF_MONTH) - birthDay.get(Calendar.DAY_OF_MONTH) + today;
		} else {
			days = 0;
			if (months == 12) {
				years++;
				months = 0;
			}
		}

		return new AgeDto(days, months, years);
	}

	public static class AgeDto {
		private Integer year;
		private Integer month;
		private Integer day;

		public AgeDto(Integer day, Integer month, Integer year) {
			this.day = day;
			this.month = month;
			this.year = year;
		}

		public Integer getYear() {
			return year;
		}

		public void setYear(Integer year) {
			this.year = year;
		}

		public Integer getMonth() {
			return month;
		}

		public void setMonth(Integer month) {
			this.month = month;
		}

		public Integer getDay() {
			return day;
		}

		public void setDay(Integer day) {
			this.day = day;
		}
	}
}
