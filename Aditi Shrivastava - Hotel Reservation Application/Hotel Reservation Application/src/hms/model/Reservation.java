package hms.model;

import java.util.Date;

public class Reservation {
	Customer customer;
	IRoom room;
	Date checkInDate;
	Date checkOutDate;
	public Reservation(Customer c, IRoom aRoom,Date iDate, Date oDate)
	{
		customer = c;
		room = aRoom;
		checkInDate = iDate;
		checkOutDate = oDate;
	}
	public Customer getCustomer()
	{
		return customer;
	}
	public IRoom getRoom()
	{
		return room;
	}
	public Date getCheckInDate()
	{
		return checkInDate;
	}
	public Date getCheckOutDate()
	{
		return checkOutDate;
	}
	@Override
	public String toString()
	{
		return "Customer:" +getCustomer()+ " Room:" +getRoom() + " Check in Date:" +getCheckInDate()+" Check out Date:"+getCheckOutDate();
	}
}
