package hms.admin;
import java.util.Collection;
import java.util.Date;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.text.SimpleDateFormat;
import hms.model.*;
import hms.service.*;
import hms.api.*;

public class MainMenu {
	Scanner scanner = null;
	public MainMenu()
	{
		 scanner = new Scanner(System.in);
	}
		 
	public static void main(String[] args) {
		MainMenu m = new MainMenu();
		m.showMainMenu();
	}
	public void showMainMenu()
	{
		boolean corrctSelection = true;
		String choice = "1";
		while (corrctSelection)
		{
			System.out.println();
			System.out.println();
			System.out.println("----------------------------------------------------------------------------");
			System.out.println("~Main Menu~");
			System.out.println("1. Find and reserve a room");
			System.out.println("2. See my reservations");
			System.out.println("3. Create an account");
			System.out.println("4. Admin");
			System.out.println("5. Exit");
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
			this.findAndReserveRoom();
		}
		if (choice.equalsIgnoreCase("2"))
		{
			this.seeMyReservations();
		}
		if (choice.equalsIgnoreCase("3"))
		{
			this.createAnAccount();
		}
		if (choice.equalsIgnoreCase("4"))
		{
			this.getAdminMenu();
		}
		if (choice.equalsIgnoreCase("5"))
		{
			System.out.println("Thanks. I hope we meet agin soon. Sayonara");
			System.exit(0);
		}
		this.showMainMenu();
	}
	public void findAndReserveRoom ()
	{
		boolean cICheck = true;
		Date cIDate = new Date();
		while (cICheck)
		{
			System.out.println("Write Check In Date in the format mm/dd/yyy:");
			String iDate = scanner.nextLine();
			cIDate = this.convertToDate(iDate);
			if(cIDate==null){}
			else
			{
				cICheck=false;
			}
		}
		
		boolean cOCheck = true;
		Date cODate = new Date();
		while (cOCheck)
		{
			System.out.println("Write Check Out Date in the format mm/dd/yyy that is after the check in date:");
			String oDate = scanner.nextLine();
			cODate = this.convertToDate(oDate);
			if(cODate!=null)
			{
				if (cIDate.before(cODate))
				{
					cOCheck=false;
				}
			}
			else
			{
				
			}
		}
		Collection<IRoom> aRooms = HotelResource.findARoom(cIDate, cODate);
		int count = 0;
		for(IRoom r:aRooms)
		{
			count++;
		}
		if (count==0)
		{
			System.out.println("Sorry no rooms available for your wanted dates :p");
			return;
		}
		for(IRoom r:aRooms)
		{
			System.out.println(r.toString());
		}
		boolean yNCheck = true;
		String yOrN = "y";
		while (yNCheck)
		{
			System.out.println("Would you like to book a room? y/n");
			yOrN = scanner.nextLine();
			if (yOrN.equalsIgnoreCase("y")||yOrN.equalsIgnoreCase("n"))
			{
				yNCheck=false;
			}
		}
		if(yOrN.equalsIgnoreCase("y"))
		{
			boolean accStat = true;
			String hasAcc = "y";
			while(accStat)
			{
				System.out.println("Do you have an account? y/n");
				hasAcc = scanner.nextLine();
				if (hasAcc.equalsIgnoreCase("y")||hasAcc.equalsIgnoreCase("n"))
				{
					accStat=false;
				}
			}
			if (hasAcc.equalsIgnoreCase("Y"))
			{
				this.roomReservation(aRooms, cIDate, cODate);
			}
			else
			{
				this.createAnAccount();
				this.roomReservation(aRooms, cIDate, cODate);
				
			}			
		}
		else
		{
		}
	}
	public void seeMyReservations()
	{
		boolean cEmail = true;
		String emailAcc = "email";
		while (cEmail)
		{
			System.out.println("Please enter your email in the format 'name@domain.com': ");
			emailAcc = scanner.nextLine();
			boolean a = emailCheck(emailAcc);
			if (a)
			{
				cEmail=false;
			}
			else {}
		}
		System.out.println("Below are your reservations");
		int c = 0;
		for (Reservation r: ReservationService.reservationList)
		{
			if (r.getCustomer()== CustomerService.getCustomer(emailAcc))
			{
				System.out.println(r.toString());
				c++;
			}
		}
		if (c==0)
		{
			System.out.println("You have no reservations :p");
		}
	}
	public void createAnAccount ()
	{
		System.out.print("Type your first name: ");
		String firstName = scanner.nextLine();
		System.out.print("Type your last name: ");
		String lastName = scanner.nextLine();
		String emailAcc = "email"; 
		boolean cEmail = true;
		while (cEmail)
		{
			System.out.println("Please enter your email in the format 'name@domain.com': ");
			emailAcc = scanner.nextLine();
			boolean a = emailCheck(emailAcc);
			if (a)
			{
				cEmail=false;
			}
		}
		CustomerService.addCustomer(emailAcc, firstName, lastName);
		System.out.println("Account Created");
		System.out.println();
	}
	public void getAdminMenu ()
	{
		AdminMenu a = new AdminMenu();
		a.showAdminMenu();
	}
	public Date convertToDate(String stringDate)
	{
		try
		{
			if (Pattern.matches("^[0-3]?[0-9]/[0-3]?[0-9]/(?:[0-9]{2})?[0-9]{2}$", stringDate))
			{
				Date dDate = new SimpleDateFormat("MM/dd/yyyy").parse(stringDate);
				return dDate;
			}
			else
			{
				return null;
			}
		}
		catch(Exception e)
		{
			return null;
		}
			
	}
	public boolean emailCheck(String email)
	{
		  String emailRegex = "^(.+)@(.+).(.+)$";
	      Pattern pattern = Pattern.compile(emailRegex);
	      return pattern.matcher(email).matches();
	}
	public void roomReservation(Collection<IRoom> aRoomList, Date cIDate, Date cODate)
	{
		String emailAcc = "email"; 
		boolean cEmail = true;
		while (cEmail)
		{
			System.out.println("Please enter your email in the format to book your room 'name@domain.com': ");
			emailAcc = scanner.nextLine();
			boolean a = emailCheck(emailAcc);
			if (a)
			{
				cEmail=false;
			}
		}
		String rNo = "000";
		boolean rNoCorrect = true;
		while (rNoCorrect)
		{
			System.out.println("Please enter a valid room number: ");
			rNo = scanner.nextLine();
			IRoom room = ReservationService.getARoom(rNo);
			if (room!=null)
			{
				for (IRoom r: aRoomList)
				{
					if (room == r)
					{
						ReservationService.reserveARoom(CustomerService.getCustomer(emailAcc), room, cIDate, cODate);
						rNoCorrect=false;
						System.out.println("Room has been booked");
						System.out.println();
					}
				}
			}
		}
	}
}
