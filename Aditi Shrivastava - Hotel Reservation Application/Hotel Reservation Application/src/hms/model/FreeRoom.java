package hms.model;

public class FreeRoom extends Room {
	public FreeRoom(String rNo, RoomType aType ){		
		super(rNo,0.0,aType);
	}
	
	@Override
	public String toString()
	{
		return "Price: Free";
	}
}
