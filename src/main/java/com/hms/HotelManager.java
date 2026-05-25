package com.hms;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class HotelManager {
    int j=0;
    double totalrevenue=0;
    Scanner sc=new Scanner(System.in);

    Customer[] customers = new Customer[40];
    Suite[] suites = new Suite[5];
    DeluxeRoom[] deluxerooms = new DeluxeRoom[10];
    SingleRoom[] singlerooms = new SingleRoom[25];

    public HotelManager() {

        for(int i=0;i<5;i++)
            suites[i] = new Suite(i+1,"Suite");

        for(int i=0;i<10;i++)
            deluxerooms[i] = new DeluxeRoom(i+6,"Deluxe");

        for(int i=0;i<20;i++)
            singlerooms[i] = new SingleRoom(i+16,"Single");

    }

    public void saveGuest(Customer c)
    {
        FileWriter f;
        try 
        {
            f = new FileWriter("GuestLog.txt",true);
            f.write("name:"+c.name+" |"+" room no:"+c.roomNo+" |"+" duration:"+c.days+" |"+" revenue:"+c.totalPrice+"\n");
            f.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int bookSuite(String name,int days)
    {
        int i;
            for(i=0;i<5;i++)
            {
                if(suites[i].status.equalsIgnoreCase("Available"))
                    break;
            }
            if(i!=5)
            {
                if(j >= customers.length)
                {
                    System.out.println("Customer list full!");
                    return -1;
                }
                customers[j]=new Customer(name, suites[i].roomNo, days);
                customers[j++].roomType="Suite";
                suites[i].status="Occupied";
                return suites[i].roomNo;
            }
            else
            {
                System.out.println("Sorry no suite rooms currently available!");
                return -1;
            }   
    }

    public int bookDeluxe(String name,int days)
    {
       int i;
            for(i=0;i<10;i++)
            {
                if(deluxerooms[i].status.equalsIgnoreCase("Available"))
                    break;
            }
            if(i!=10)
            {
                if(j >= customers.length)
                {
                    System.out.println("Customer list full!");
                    return -1;
                }
                customers[j]=new Customer(name, deluxerooms[i].roomNo, days);
                customers[j++].roomType="Deluxe";
                deluxerooms[i].status="Occupied";
                return deluxerooms[i].roomNo;
            }
            else
            {    
                System.out.println("Sorry no deluxe rooms currently available!");
                return -1;
            } 
    }

    public int bookSingle(String name,int days)
    {
        int i;
            for(i=0;i<20;i++)
            {
                if(singlerooms[i].status.equalsIgnoreCase("Available"))
                    break;
            }
            if(i!=20)
            {
                if(j >= customers.length)
                {
                    System.out.println("Customer list full!");
                    return -1;
                }
                customers[j]=new Customer(name, singlerooms[i].roomNo, days);
                customers[j++].roomType="Single";
                singlerooms[i].status="Occupied";
                return singlerooms[i].roomNo;
            }
            else
            {    
                System.out.println("Sorry no single rooms currently available!");
                return -1;
            }
    }

    public int CheckIn(String name,int days,String type)
    {
        int rno;
        if(type.equalsIgnoreCase("Suite"))
            rno=bookSuite(name,days);
        else if(type.equalsIgnoreCase("Deluxe"))
            rno=bookDeluxe(name,days);
        else if(type.equalsIgnoreCase("Single"))
            rno=bookSingle(name,days);
        else
        {
            System.out.println("Invalid room type!");
            return -1;
        }
        return rno;
    }

    public float CheckOut(int rno)
    {
        int k;
        float t=0;
        for(k=0;k<customers.length;k++)
        {
            if(customers[k]!=null && customers[k].roomNo==rno)
                break;
        }
        if(k!=customers.length)
        {
            String s=customers[k].roomType;
            if(s.equalsIgnoreCase("Suite"))
            {
                customers[k].calcTotalPrice(14999);
                customers[k].calcTax(14999);
            }
            else if(s.equalsIgnoreCase("Deluxe"))
            {
                customers[k].calcTotalPrice(9999);
                customers[k].calcTax(9999);
            }
            else if(s.equalsIgnoreCase("Single"))
            {
                customers[k].calcTotalPrice(5999);
                customers[k].calcTax(5999);
            }
            System.out.println("Room Cost:"+customers[k].beforeTax);
            System.out.println("GST(18%):"+customers[k].tax);
            System.out.println("Total Bill is:"+customers[k].totalPrice);
            t=customers[k].totalPrice;
            totalrevenue+=customers[k].totalPrice;
            saveGuest(customers[k]);
            if(s.equalsIgnoreCase("Suite"))
                suites[rno-1].status="Cleaning";
            else if(s.equalsIgnoreCase("Deluxe"))
                deluxerooms[rno-6].status="Cleaning";
            else if(s.equalsIgnoreCase("Single"))
                singlerooms[rno-16].status="Cleaning";
            customers[k]=null;
        }
        else
            System.out.println("The room is unoccupied. Please check again!");
        return t;
    }
    
    public void AvailableRooms()
    {
        System.out.println("These are the Rooms currently available:-");
        System.out.println("Suites");
        for(int i=0;i<5;i++)
        {
            if(suites[i].status.equalsIgnoreCase("Available"))
            {
                System.out.println("Room no:"+suites[i].roomNo);
                System.out.println("Price per night:"+suites[i].pricePerNight);
                System.out.println();
            }
        }
        System.out.println();
        System.out.println();
        System.out.println("Deluxe Rooms");
        for(int i=0;i<10;i++)
        {
            if(deluxerooms[i].status.equalsIgnoreCase("Available"))
            {
                System.out.println("Room no:"+deluxerooms[i].roomNo);
                System.out.println("Price per night:"+deluxerooms[i].pricePerNight);
                System.out.println();
            }
        }
        System.out.println();
        System.out.println();
        System.out.println("Single Rooms");
        for(int i=0;i<20;i++)
        {
            if(singlerooms[i].status.equalsIgnoreCase("Available"))
            {
                System.out.println("Room no:"+singlerooms[i].roomNo);
                System.out.println("Price per night:"+singlerooms[i].pricePerNight);
                System.out.println();
            }
        }
    }

    public void RoomFacilities()
    {
        System.out.println("Please enter room no to check for:");
        int chk=sc.nextInt();
        sc.nextLine();
        if(chk>35)
        {
            System.out.println("Invalid room no entered. Please try again!");
            return;
        }
        System.out.println("The available facilities are:-");
        if(chk<6)
        {
            int index=chk-1;
            System.out.println("Breakfast:"+(suites[index].breakfast? "Available" : "Not Available"));
            System.out.println("Pool Access:"+(suites[index].pool? "Available" : "Not Available"));
            System.out.println("WiFi:"+(suites[index].wifi? "Available" : "Not Available"));
        }
        else if(chk>5&&chk<16)
        {
            int index=chk-6;
            System.out.println("Breakfast:"+(deluxerooms[index].breakfast? "Available" : "Not Available"));
            System.out.println("Pool Access:"+(deluxerooms[index].pool? "Available" : "Not Available"));
            System.out.println("WiFi:"+(deluxerooms[index].wifi? "Available" : "Not Available"));
        }
        else
        {
            int index=chk-16;
            System.out.println("Breakfast:"+(singlerooms[index].breakfast? "Available" : "Not Available"));
            System.out.println("Pool Access:"+(singlerooms[index].pool? "Available" : "Not Available"));
            System.out.println("WiFi:"+(singlerooms[index].wifi? "Available" : "Not Available"));
        }
    }

    public String searchRoom(int rno)
{
    if(rno > 35)
    {
        System.out.println("Invalid room number!");
        return "Invalid";
    }
    if(rno <= 5)
    {
        int index = rno - 1;
        System.out.println("Room No:"+rno);
        System.out.println("Type: Suite");
        System.out.println("Price: " + suites[index].pricePerNight);
        System.out.println("Status: " + suites[index].status);
        return suites[index].status;
    }
    else if(rno <= 15)
    {
        int index = rno - 6;
        System.out.println("Room No:"+rno);
        System.out.println("Type: Deluxe");
        System.out.println("Price: " + deluxerooms[index].pricePerNight);
        System.out.println("Status: " + deluxerooms[index].status);
        return deluxerooms[index].status;
    }
    else
    {
        int index = rno - 16;
        System.out.println("Room No:"+rno);
        System.out.println("Type: Single");
        System.out.println("Price: " + singlerooms[index].pricePerNight);
        System.out.println("Status: " + singlerooms[index].status);
        return singlerooms[index].status;
    }
}

public double showRevenue()
{
    return totalrevenue;
}

public int showGuestList()
{
    System.out.println("Guest List:-");
    int c1 = 0;
    for(int i=0;i<customers.length;i++)
    {
        if(customers[i]!=null)
        {
            System.out.println("Guest Name:"+customers[i].name);
            System.out.println("Room No:"+customers[i].roomNo);
            System.out.println();
            System.out.println();
            c1++;
        }
    }
    return c1;
}

public int getOccupiedRooms()
{
    int occupied = 0;
    for(int i=0;i<customers.length;i++)
    {
        if(customers[i] != null)
            occupied++;
    }
    return occupied;
}

public int getAvailableRooms()
{
    int occupied = 0;
    for(int i=0;i<customers.length;i++)
    {
        if(customers[i] != null)
            occupied++;
    }
    return(35-occupied);
}

public int getTotalRooms()
{
    return 35;
}

public int occupancyPercentage()
{
    int occ=getOccupiedRooms();
    int tot=getTotalRooms();
    return (int) (((double) occ/tot)*100);
}

public int emptyCheck(int rno)
{
    int k;
    for(k=0;k<customers.length;k++)
        {
            if(customers[k]!=null && customers[k].roomNo==rno)
                break;
        }
    if(k==customers.length)
        return 1;
    return 0;
}

public Suite[] getSuites()
{
    return suites;
}

public DeluxeRoom[] getDeluxe()
{
    return deluxerooms;
}

public SingleRoom[] getSingle()
{
    return singlerooms;
}

public Customer[] getGuests()
{
    return customers;
}

public void makeRoomAvailable(int rono)
{
    if(rono<6)
        suites[rono-1].status="Available";
    else if(rono<16&&rono>5)
        deluxerooms[rono-6].status="Available";
    else
        singlerooms[rono-16].status="Available";
}
}