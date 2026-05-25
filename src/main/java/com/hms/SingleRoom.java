package com.hms;
public class SingleRoom extends Room {
    float pricePerNight=(float) 5999.00;
    boolean wifi=false;
    boolean pool=false;
    boolean breakfast=true;

    public SingleRoom(int roomNo,String roomType)
    {
        super(roomNo, roomType);
    }
    
}
