package hms.admin;
import java.util.Collection;
import java.util.Scanner;

import hms.model.Customer;
import hms.model.IRoom;
import hms.model.Reservation;
import hms.model.Room;
import hms.model.RoomType;
import hms.service.CustomerService;
import hms.service.ReservationService;


public class AdminMenu {
	Scanner scanner = null;
	public AdminMenu()
	{
		scanner = new Scanner(System.in);
	}
	public static void main(String[] args) {
		AdminMenu a = new AdminMenu();
		a.showAdminMenu();
	}
	public void showAdminMenu()
	{
		boolean corrctSelection = true;
		String choice = "1";
		while (corrctSelection)
		{
			System.out.println();
			System.out.println();
			System.out.println("----------------------------------------------------------------------------");
			System.out.println("~Admin Menu~");
			System.out.println("1. See all Customers");
			System.out.println("2. See all Rooms");
			System.out.println("3. See all Reservations");
			System.out.println("4. Add a Room");
			System.out.println("5. Back to Main Menu");
			System.out.println("Select one of the numbers above and just type the number");
			choice = scanner.nextLine();
		//	System.out.println(choice);
			if (choice.equalsIgnoreCase("1")||choice.equalsIgnoreCase("2")||choice.equalsIgnoreCase("3")||choice.equalsIgnoreCase("4")||choice.equalsIgnoreCase("5"))
			{
				corrctSelection=false;
			}
			else
			{
				System.out.println("Invalid choice; please choose again");
			}
		}
		if (choice.equalsIgnoreCase("1"))
		{
			this.seeAllCustomers();
		}
		if (choice.equalsIgnoreCase("2"))
		{
			this.seeAllRooms();
		}
		if (choice.equalsIgnoreCase("3"))
		{
			this.seeAllReservations();
		}
		if (choice.equalsIgnoreCase("4"))
		{
			this.addRoom();
		}
		if (choice.equalsIgnoreCase("5"))
		{
			MainMenu m = new MainMenu();
			m.showMainMenu();
		}
	}
	public void seeAllCustomers()
	{
		Collection <Customer> CustomerList = CustomerService.getAllCustomers();
		int count= 0;
		for (Customer c:CustomerList)
		{
			System.out.println(c.toString());
			count++;
		}
		if (count==0)
		{
			System.out.println("No Customers :p Be better at marketing");
		}
		this.showAdminMenu();
	}
	public void seeAllRooms()
	{
		Collection<IRoom> rooms = ReservationService.roomList.values();
		int count = 0;
		for (IRoom r: rooms)
		{
			System.out.println(r.toString());
			count++;
		}
		if (count==0)
		{
			System.out.println("No Reservations :p Be better at marketing");
		}
		this.showAdminMenu();
	}
	public void seeAllReservations()
	{
		Collection<Reservation> reservations = ReservationService.reservationList;
		for (Reservation r: reservations)
		{
			System.out.println(r.toString());
		}
		this.showAdminMenu();
	}
	public void addRoom()
	{
		System.out.println("Write a room number: ");
		String rNo = scanner.nextLine();
		System.out.println("Give us a price in dollars: ");
		double aPrice = Double.parseDouble(scanner.nextLine());
		String aType = "single";
		boolean roomType = true;
		while (roomType)
		{
			System.out.println("Single or Double Room: ");
			aType = scanner.nextLine();
			if (aType.equalsIgnoreCase("Single")||aType.equalsIgnoreCase("Double"))
			{
				roomType = false;
			}
		}
		RoomType rType = RoomType.SINGLE;
		if (aType.equalsIgnoreCase("Single"))
		{
			rType = RoomType.SINGLE ;
		}
		else
		{
			rType = RoomType.DOUBLE ;
		}
		boolean isFree = true;
		String isRoomFree="y";
		while (isFree)
		{
			System.out.println("Is it free y/n ");
			isRoomFree = scanner.nextLine();
			if (isRoomFree.equalsIgnoreCase("y")||isRoomFree.equalsIgnoreCase("n"))
			{
				isFree = false;
			}
		}
		if (isRoomFree.equalsIgnoreCase("y"))
		{
			isFree = true;
		}
		else
		{
			isFree = false;
		}
		IRoom newRoom = new Room(rNo,aPrice,rType);
		ReservationService.addRoom(newRoom);
		System.out.println(ReservationService.roomList.values());
		System.out.println("New room added");
		this.showAdminMenu();
	}
}
