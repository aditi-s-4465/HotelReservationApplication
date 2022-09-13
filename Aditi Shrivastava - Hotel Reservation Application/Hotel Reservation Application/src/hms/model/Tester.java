package hms.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Tester {

	public static void main(String[] args) throws Exception {
		String startDate = "05/11/22";
		String endDate = "06/11/22";
		DateFormat format = new SimpleDateFormat("mm/dd/yy");
		Date startDatee = (Date) format.parse(startDate);
		Date endDatee = (Date) format.parse(endDate);
		
		List datesInRange = new ArrayList<>();
		Calendar start = Calendar.getInstance();
		start.setTime(startDatee);
		Calendar end = Calendar.getInstance();
		end.setTime(endDatee);
		start.add(Calendar.DAY_OF_YEAR,1);
		System.out.println(start);
	}

}
