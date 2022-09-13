package hms.model;

public enum RoomType {
	SINGLE("Single"),
	DOUBLE("Double");
	
	private String rType;
	public String getType()
	{
		return this.rType;
	}

	private RoomType(String string) {
		this.rType=string;
	}
}
