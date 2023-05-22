package com.alianza.clients.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.alianza.clients.enums.BussinessErrorEnum;
import com.alianza.clients.exception.AlianzaException;

import lombok.experimental.UtilityClass;

@UtilityClass
public class DateUtil {
		
	public String dateToString(Date date) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(Constants.DATE_FORMAT);
		return simpleDateFormat.format(date);
	}
	
	public Date stringToDate(String date) throws AlianzaException {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(Constants.DATE_FORMAT);
		try {
			return simpleDateFormat.parse(date);
		} catch (ParseException e) {
			throw new AlianzaException(BussinessErrorEnum.DATE_PARSE_EXCEPTION, e);
		}
	}

}
