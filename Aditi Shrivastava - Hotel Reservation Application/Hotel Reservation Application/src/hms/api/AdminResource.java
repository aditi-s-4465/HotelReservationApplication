package hms.api;

import java.util.Collection;

import java.util.List;
import hms.model.*;
import hms.service.*;
import hms.api.*;;

public class AdminResource {
	public Customer getCustomer (String email)
	{
		return CustomerService.getCustomer(email);
	}
	public void addRoom(List<IRoom> rooms)
	{
		for(IRoom r:rooms)
		{
			ReservationService.addRoom(r);
		}
	}
	public Collection<IRoom> getAllRooms()
	{
		return ReservationService.roomList.values();
	}
	public Collection<Customer> getAllCustomers()
	{
		return CustomerService.customerList.values();
	}
	public void displayAllReservations()
	{
		ReservationService.printAllReservation();
	}
}
