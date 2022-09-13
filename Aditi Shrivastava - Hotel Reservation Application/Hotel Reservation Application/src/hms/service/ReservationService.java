package hms.service;

import java.util.ArrayList;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import hms.model.*;

public class ReservationService {
	public static Map<String, IRoom> roomList = new HashMap<String,IRoom>();
	public static List<Reservation> reservationList = new ArrayList<Reservation>();
	public static void addRoom(IRoom room)
	{
		String x = room.getRoomNumber();
		roomList.put(x, room);
	}
	public static IRoom getARoom(String roomId)
	{
		return roomList.get(roomId);
	}
	public static Reservation reserveARoom(Customer customer, IRoom room, Date checkInDate, Date checkOutDate)
	{
		Reservation r = new Reservation(customer,room,checkInDate,checkOutDate);
		(reservationList).add(r);
		return r;
	}
	public static Collection<IRoom> findRooms(Date checkInDate, Date checkOutDate) //when called if returned collection is zero then call again with +/- 7 days in checkin and checkout 
	{	
		List<Date> rDates = getDateRange(checkInDate,checkOutDate); //all the individual dates you need a reservation for
		Map<String, IRoom> aRooms = new HashMap<String,IRoom>(); //available rooms for given reservation dates above
		List<Date> resDates =new ArrayList<Date>(); //dates where a room num is available 
		for (String num:roomList.keySet())
		{
			resDates=new ArrayList<Date>();
			IRoom x = roomList.get(num);
			for (Reservation y:reservationList)
			{
				String z = y.getRoom().getRoomNumber();
				if(z==num)
				{
					Date iDate = y.getCheckInDate();
					Date oDate = y.getCheckOutDate();
				List<Date> bookedDates = getDateRange(iDate,oDate);
					resDates.addAll(bookedDates);
				}
			}
		System.out.println(rDates);
		System.out.println(resDates);
			int c = 0;
			for(Date r: rDates)
			{
				if (resDates.contains(r))
				{

				}
				else
				{
				
					c++;

					}
			}
			if (c==rDates.size())
			{
				aRooms.put(num, x);
			}
			c=0;
		}
		return aRooms.values(); 
	}
	public static Collection<Reservation> getCustomersReservation(Customer customer)
	{
		List<Reservation> reservations =new ArrayList<Reservation>();
		for (Reservation r: reservationList)
		{
			if (r.getCustomer()==customer)
			{
				reservations.add(r);
			}
		}
		return reservations;
	}
	public static void printAllReservation()
	{
		for (Reservation r:reservationList)
		{
			System.out.println(r.toString());
		}
	}
	
	public static List<Date> getDateRange (Date sDate, Date eDate)
	{
		List<Date> dates = new ArrayList<Date>();
		// DateFormat formatting = new SimpleDateFormat("dd/MM/yyyy");
		long a = 86400000; // number of milliseconds in a day
		long eTime = eDate.getTime();
		long sTime = sDate.getTime();
		while (sTime<=eTime)
		{
			dates.add(new Date(sTime));
			sTime = sTime+a;
		}
		return dates;
	}
}
