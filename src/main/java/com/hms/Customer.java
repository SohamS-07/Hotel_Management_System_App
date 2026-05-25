package com.hms;
public class Customer {
    String name;
    int roomNo;
    String roomType;
    int days;
    float totalPrice;
    float beforeTax;
    float tax;

    public Customer(String name,int roomNo,int days)
    {
        this.name=name;
        this.roomNo=roomNo;
        this.days=days;
    }

    public void calcTotalPrice(float pricePerNight)
    {
        beforeTax=days*pricePerNight;
        totalPrice=(float) (beforeTax*1.18); 
    }

    public void calcTax(float pricePerNight)
    {
        tax=(float) (pricePerNight*0.18);
    }
    
}
