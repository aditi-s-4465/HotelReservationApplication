package hms.model;

public class Room implements IRoom{
	String roomNumber;
	double price;
	RoomType enumeration;
	public Room(String rNo,double aPrice, RoomType aType)
	{
		roomNumber = rNo;
		price = aPrice;
		enumeration = aType;
	}
	public String getRoomNumber()
	{
		return roomNumber;
	}
	public double getRoomPrice()
	{
		return price;
	}
	public RoomType getRoomType()
	{
		return enumeration;
	}
	public boolean isFree()
	{
		return false;
	}
    @Override
    public String toString()
    {
    	return "Room number:"+getRoomNumber()+" Price:"+getRoomPrice()+" Room Type:"+getRoomType();
    }
}
