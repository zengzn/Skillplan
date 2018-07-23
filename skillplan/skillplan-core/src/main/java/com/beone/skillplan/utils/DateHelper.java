package com.beone.skillplan.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import javax.ws.rs.BadRequestException;

import de.jollyday.HolidayCalendar;
import de.jollyday.HolidayManager;

/**
 * Date helping class.
 * 
 * @author zhen
 *
 */
public final class DateHelper {

	private static final DateFormat monthDateFormatter = new SimpleDateFormat("yyyyMM");
	private static final DateFormat yearDateFormatter = new SimpleDateFormat("yyyy");
	private static final DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");

	public static Date parseMonthDate(String date) {
		return parseDate(monthDateFormatter, date);
	}

	public static Date parseYearDate(String date) {
		return parseDate(yearDateFormatter, date);
	}

	public static LocalDateTime getLocalDate(Date date) {
		return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
	}

	public static boolean isWorkingDay(LocalDate localDate, String state) {
		return localDate.getDayOfWeek() != DayOfWeek.SATURDAY && localDate.getDayOfWeek() != DayOfWeek.SUNDAY
				&& !isHoliday(localDate, state);
	}

	public static long getAmountOfWorkingDays(Date from, Date to, String state) {
		LocalDate start = getLocalDate(from).toLocalDate();
		LocalDate end = getLocalDate(to).toLocalDate();

		long days = 0;
		for (LocalDate date = start; date.isBefore(end) || date.isEqual(end); date = date.plusDays(1)) {
			if (isWorkingDay(date, state)) {
				days++;
			}
		}
		return days;
	}

	public static double calculateHours(Date from, Date to, int breakMinutes) {
		long duration = to.getTime() - from.getTime();
		long breakTime = TimeUnit.MINUTES.toMillis(breakMinutes);
		return (duration - breakTime) / Double.valueOf(TimeUnit.HOURS.toMillis(1));
	}

	public static Date getMonthStart(Date date) {
		LocalDateTime localDateTime = toLocalDateTime(date).withDayOfMonth(1).with(LocalTime.MIN);
		return toDate(localDateTime);
	}

	public static Date getMonthEnd(Date date) {
		LocalDateTime localDateTime = toLocalDateTime(date).with(TemporalAdjusters.lastDayOfMonth())
				.with(LocalTime.MAX);
		return toDate(localDateTime);
	}

	public static int getPastYear() {
		return LocalDate.now().minusYears(1).getYear();
	}

	public static int getYear(Date date) {
		return toLocalDateTime(date).getYear();
	}

	public static int getMonth(Date date) {
		return toLocalDateTime(date).getMonthValue();
	}

	public static Date setGermanHour(Date date, int hour) {
		ZoneOffset currentOffsetForMyZone = ZoneId.of("Europe/Berlin").getRules().getOffset(Instant.now());
		OffsetDateTime odt = OffsetDateTime.ofInstant( date.toInstant() , currentOffsetForMyZone ).withHour(hour).withMinute(0);
		return Date.from(odt.toInstant());
	}

	public static Date toDate(LocalDate localDate) {
		return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
	}
	
	public static Date getNextMonthStart(Date date) {
		LocalDateTime localDateTime = toLocalDateTime(date);
		LocalDateTime nextlocalDateTime = localDateTime.plusMonths(1);
		Date nextMonth = toDate(nextlocalDateTime);
		return getMonthStart(nextMonth);
	}
	
	public static Date getPreviousMonthEnd(Date date) {
		LocalDateTime localDateTime = toLocalDateTime(date);
		LocalDateTime nextlocalDateTime = localDateTime.minusMonths(1);
		Date previousMonth = toDate(nextlocalDateTime);
		return getMonthEnd(previousMonth);
	}
	
	private static LocalDateTime toLocalDateTime(Date date) {
		return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
	}

	private static Date toDate(LocalDateTime localDateTime) {
		return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
	}

	private static Date parseDate(DateFormat formatter, String date) {
		try {
			return formatter.parse(date);
		} catch (ParseException e) {
			throw new BadRequestException(e);
		}
	}

	private static boolean isHoliday(LocalDate localDate, String state) {
		Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);

		HolidayManager manager = HolidayManager.getInstance(HolidayCalendar.GERMANY);
		return manager.isHoliday(calendar, state);
	}

	private DateHelper() {
		throw new AssertionError(this.getClass().getSimpleName() + " cannot be instantiated");
	}
	
	public static LocalTime parseTime(LocalTime time) {
		String converted = timeFormatter.format(time);
		return LocalTime.parse(converted);
	}


}
