package com.hms;
public class Suite extends Room {
    float pricePerNight=(float) 14999.00;
    boolean wifi=true;
    boolean pool=true;
    boolean breakfast=true;

    public Suite(int roomNo,String roomType)
    {
        super(roomNo, roomType);
    }
}
