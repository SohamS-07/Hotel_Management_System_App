package com.hms;
public class DeluxeRoom extends Room {
    float pricePerNight=(float) 9999.00;
    boolean wifi=false;
    boolean pool=true;
    boolean breakfast=true;

    public DeluxeRoom(int roomNo,String roomType)
    {
        super(roomNo, roomType);
    }
}
