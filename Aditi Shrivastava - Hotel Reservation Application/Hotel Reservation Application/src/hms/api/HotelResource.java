package hms.api;

import java.util.Collection;

import java.util.Date;

import hms.model.*;
import hms.service.*;
import hms.api.*;

public class HotelResource {
	public static Customer getCustomer(String email)
	{
		return CustomerService.getCustomer(email);
	}
	public static void createACustomer(String email,String firstName, String lastName)
	{
		CustomerService.addCustomer(email, firstName, lastName);
	}
	public static IRoom getRoom(String roomNumber)
	{
		return ReservationService.getARoom(roomNumber);
	}
	public static Reservation bookARoom(String customerEmail, IRoom room, Date checkInDate, Date CheckOutDate)
	{
		return ReservationService.reserveARoom(getCustomer(customerEmail), room, checkInDate, CheckOutDate);
	}
	public static Collection<Reservation> getCustomersReservations(String customerEmail)
	{
		return ReservationService.getCustomersReservation(getCustomer(customerEmail));
	}
	public static Collection<IRoom> findARoom(Date checkIn, Date checkOut)
	{
		return ReservationService.findRooms(checkIn, checkOut);
	}
}
