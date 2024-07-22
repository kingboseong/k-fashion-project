package com.project.k6.config;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import org.springframework.format.Formatter;

//날짜를 문자열로 변환하고, 문자열을 날짜로 변환하는 역할을 하는 클래스
public class LocalDateFormatter implements Formatter<LocalDate>{

	@Override
	public LocalDate parse(String text, Locale locale) throws ParseException {
		// TODO Auto-generated method stub
		return LocalDate.parse(text, DateTimeFormatter.ofPattern("yyyy-<<-dd"));
	}
	
	@Override
	public String print(LocalDate object, Locale locale) {
		// TODO Auto-generated method stub
		return DateTimeFormatter.ofPattern("yyyy-MM-dd").format(object);
	}
}
