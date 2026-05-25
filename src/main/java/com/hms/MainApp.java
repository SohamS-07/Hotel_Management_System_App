package com.hms;
import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

public class MainApp extends Application {

    HotelManager h1=new HotelManager();
    Label value1;
    Label value2;
    Label value3;
    Label value4;
    Label occupancy;
    VBox roomPane=new VBox(10);
    VBox guestPane=new VBox(10);

    public void updateDashboard()
    {
        value1.setText(String.valueOf(h1.getTotalRooms()));
        value2.setText(String.valueOf(h1.getOccupiedRooms()));
        value3.setText(String.valueOf(h1.getAvailableRooms()));
        value4.setText("₹ "+String.valueOf(h1.showRevenue()));
        occupancy.setText("Occupancy: "+String.valueOf(h1.occupancyPercentage())+"%");
    }

    @SuppressWarnings("static-access")
    public void updateRoomsTab()
    {
        Suite[] suite=h1.getSuites();
        SingleRoom[] single=h1.getSingle();
        DeluxeRoom[] deluxe=h1.getDeluxe();
        roomPane.getChildren().clear();
        Label room=new Label("Rooms");
        room.setStyle("-fx-font-size: 22px; -fx-font-weight: bold;");

        HBox h45=new HBox();
        ComboBox<String> type=new ComboBox<>();
        type.getItems().addAll("Single","Deluxe","Suite");
        Button add=new Button("Add Room");
        h45.getChildren().addAll(type,add);
        h45.setAlignment(Pos.CENTER);

        TextField searchrno=new TextField();
        Button search=new Button("search");
        search.setOnAction(e->{
            int rno1=Integer.parseInt(searchrno.getText());
            if(rno1>35||rno1<1)
            {
                Alert alert1=new Alert(Alert.AlertType.ERROR);
                alert1.setTitle("Search Result");
                alert1.setHeaderText(null);
                alert1.setContentText("Invalid Search Request!");
                alert1.showAndWait();
                searchrno.clear();
            }
            else
            {
                String status=h1.searchRoom(rno1);
                Alert alert=new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Search Result");
                alert.setHeaderText(null);
                if(rno1<6)
                    alert.setContentText("Room No:"+rno1+"\nType:Suite\nPrice:14999.00\nStatus:"+ status);
                else if(rno1<16&&rno1>5)
                    alert.setContentText("Room No:"+rno1+"\nType:Deluxe\nPrice:9999.00\nStatus:"+ status);
                else
                    alert.setContentText("Room No:"+rno1+"\nType:Single\nPrice:5999.00\nStatus:"+ status);
                alert.showAndWait();
                searchrno.clear();
            }
        });
        HBox h12=new HBox();
        h12.getChildren().addAll(searchrno,search);
        h12.setAlignment(Pos.TOP_RIGHT);

        Label h1_rno=new Label("Room No");
        Label h1_type=new Label("Type");
        Label h1_status=new Label("Status");
        Label h1_price=new Label("Price");
        Label h1_bfast=new Label("Breakfast");
        Label h1_wifi=new Label("WiFi");
        Label h1_pool=new Label("Pool");
        HBox h1=new HBox();
        h1.getChildren().addAll(h1_rno,h1_type,h1_status,h1_price,h1_bfast,h1_wifi,h1_pool);
        h1.setAlignment(Pos.CENTER_LEFT);
        h1_rno.setMaxWidth(Double.MAX_VALUE);
        h1_type.setMaxWidth(Double.MAX_VALUE);
        h1_status.setMaxWidth(Double.MAX_VALUE);
        h1_price.setMaxWidth(Double.MAX_VALUE);
        h1_wifi.setMaxWidth(Double.MAX_VALUE);
        h1_pool.setMaxWidth(Double.MAX_VALUE);
        h1_bfast.setMaxWidth(Double.MAX_VALUE);
        h1.setHgrow(h1_rno, Priority.ALWAYS);
        h1.setHgrow(h1_type, Priority.ALWAYS);
        h1.setHgrow(h1_status, Priority.ALWAYS);
        h1.setHgrow(h1_price, Priority.ALWAYS);
        h1.setHgrow(h1_wifi, Priority.ALWAYS);
        h1.setHgrow(h1_pool, Priority.ALWAYS);
        h1.setHgrow(h1_bfast, Priority.ALWAYS);
        h1.setStyle("-fx-font-size: 12px; -fx-font-weight: bold;");
        roomPane.getChildren().addAll(h12,room,h45,h1);
        roomPane.setPadding(new Insets(10));
        for(int i=0;i<5;i++) 
        {
            HBox h=createRoomRow(suite[i].roomNo,"Suite",suite[i].status,suite[i].pricePerNight,suite[i].wifi,suite[i].pool,suite[i].breakfast);
            if(suite[i].status.equalsIgnoreCase("Available"))
                h.setStyle("-fx-background-color: #a8f50d; -fx-border-color: #444; -fx-background-radius:10; -fx-border-radius: 10; -fx-padding: 15;");
            else if(suite[i].status.equalsIgnoreCase("Occupied"))
                h.setStyle("-fx-background-color: #f66539; -fx-border-color: #444; -fx-background-radius:10; -fx-border-radius: 10; -fx-padding: 15;");
            else
                h.setStyle("-fx-background-color: #c9cf17; -fx-border-color: #444; -fx-background-radius:10; -fx-border-radius: 10; -fx-padding: 15;");
            h.setAlignment(Pos.CENTER_LEFT);
            h.setMaxWidth(Double.MAX_VALUE);
            roomPane.getChildren().add(h);
        }
        for(int i=0;i<10;i++) 
        {
            HBox h=createRoomRow(deluxe[i].roomNo,"Deluxe",deluxe[i].status,deluxe[i].pricePerNight,deluxe[i].wifi,deluxe[i].pool,deluxe[i].breakfast);
            if(deluxe[i].status.equalsIgnoreCase("Available"))
                h.setStyle("-fx-background-color: #a8f50d; -fx-border-color: #444; -fx-background-radius:10; -fx-border-radius: 10; -fx-padding: 15;");
            else if(deluxe[i].status.equalsIgnoreCase("Occupied"))
                h.setStyle("-fx-background-color: #f66539; -fx-border-color: #444; -fx-background-radius:10; -fx-border-radius: 10; -fx-padding: 15;");
            else
                h.setStyle("-fx-background-color: #c9cf17; -fx-border-color: #444; -fx-background-radius:10; -fx-border-radius: 10; -fx-padding: 15;");
            roomPane.getChildren().add(h);
        }
        for(int i=0;i<20;i++) 
        {
            HBox h=createRoomRow(single[i].roomNo,"Single",single[i].status,single[i].pricePerNight,single[i].wifi,single[i].pool,single[i].breakfast);
            if(single[i].status.equalsIgnoreCase("Available"))
                h.setStyle("-fx-background-color: #a8f50d; -fx-border-color: #444; -fx-background-radius:10; -fx-border-radius: 10; -fx-padding: 10;");
            else if(single[i].status.equalsIgnoreCase("Occupied"))
                h.setStyle("-fx-background-color: #f66539; -fx-border-color: #444; -fx-background-radius:10; -fx-border-radius: 10; -fx-padding: 10;");
            else
                h.setStyle("-fx-background-color: #c9cf17; -fx-border-color: #444; -fx-background-radius:10; -fx-border-radius: 10; -fx-padding: 15;");
            roomPane.getChildren().add(h);
        }
    }

    @SuppressWarnings("static-access")
    public HBox createRoomRow(int roomNo,String type,String status,float price,Boolean wifi,Boolean pool,Boolean bfast)
    {
        Label h1_rno=new Label(String.valueOf(roomNo));
        Label h1_type=new Label(type);
        Label h1_status=new Label(status);
        Label h1_price=new Label(String.valueOf(price));
        Label h1_wifi=new Label(wifi?"Available":"Unavailable");
        Label h1_pool=new Label(pool?"Available":"Unavailable");
        Label h1_bfast=new Label(bfast?"Available":"Unavailable");
        HBox h1=new HBox();
        h1.getChildren().addAll(h1_rno,h1_type,h1_status,h1_price,h1_bfast,h1_wifi,h1_pool);
        h1.setAlignment(Pos.CENTER);
        h1_rno.setPadding(new Insets(10));
        h1_type.setPadding(new Insets(10));
        h1_status.setPadding(new Insets(10));
        h1_price.setPadding(new Insets(10));
        h1_wifi.setPadding(new Insets(10));
        h1_pool.setPadding(new Insets(10));
        h1_bfast.setPadding(new Insets(10));
        h1_rno.setMaxWidth(Double.MAX_VALUE);
        h1_type.setMaxWidth(Double.MAX_VALUE);
        h1_status.setMaxWidth(Double.MAX_VALUE);
        h1_price.setMaxWidth(Double.MAX_VALUE);
        h1_wifi.setMaxWidth(Double.MAX_VALUE);
        h1_pool.setMaxWidth(Double.MAX_VALUE);
        h1_bfast.setMaxWidth(Double.MAX_VALUE);
        h1.setHgrow(h1_rno, Priority.ALWAYS);
        h1.setHgrow(h1_type, Priority.ALWAYS);
        h1.setHgrow(h1_status, Priority.ALWAYS);
        h1.setHgrow(h1_price, Priority.ALWAYS);
        h1.setHgrow(h1_wifi, Priority.ALWAYS);
        h1.setHgrow(h1_pool, Priority.ALWAYS);
        h1.setHgrow(h1_bfast, Priority.ALWAYS);
        return h1;
    }

    public void updateGuestsTab()
    {
        Customer[] customer=h1.getGuests();
        guestPane.getChildren().clear();
        Label guest=new Label("Guest List");
        guest.setStyle("-fx-font-size: 22px; -fx-font-weight: bold;");

        Label h1_name=new Label("Name");
        Label h1_rno=new Label("Room No");
        Label h1_days=new Label("Days");
        Label h1_type=new Label("Type");

        HBox h12=new HBox();
        h12.getChildren().addAll(h1_name,h1_rno,h1_days,h1_type);
        h12.setAlignment(Pos.CENTER);
        h1_rno.setPadding(new Insets(8));
        h1_type.setPadding(new Insets(8));
        h1_name.setPadding(new Insets(8));
        h1_days.setPadding(new Insets(8));
        h12.setStyle("-fx-font-size: 12px; -fx-font-weight: bold;");
        Label guestno=new Label("Guests Currently Checked-In:"+String.valueOf(h1.showGuestList()));
        guestPane.getChildren().addAll(guest,h12);
        for(int i=0;i<35;i++) 
        {
            if(customer[i]==null)
                continue;
            HBox h=createGuestRow(customer[i].name,customer[i].roomNo,customer[i].days,customer[i].roomType);
            h.setStyle("-fx-background-color: #58a4fa; -fx-border-color: #444; -fx-background-radius:10; -fx-border-radius: 10; -fx-padding: 15;");
            guestPane.getChildren().add(h);
        }
        guestPane.getChildren().add(guestno);
        guestno.setStyle("-fx-font-size: 15px; -fx-font-weight: bold;");
        guestPane.setPadding(new Insets(10));
    }

    public HBox createGuestRow(String name,int roomNo,int days,String type)
    {
        Label h1_rno=new Label(String.valueOf(roomNo));
        Label h1_type=new Label(type);
        Label h1_days=new Label(String.valueOf(days));
        Label h1_name=new Label(name);
        HBox h1=new HBox();
        h1.getChildren().addAll(h1_name,h1_rno,h1_days,h1_type);
        h1.setAlignment(Pos.CENTER);
        h1_rno.setPadding(new Insets(10));
        h1_type.setPadding(new Insets(10));
        h1_name.setPadding(new Insets(10));
        h1_days.setPadding(new Insets(10));
        return h1;
    }

    @Override
    public void start(Stage stage) {
        BorderPane root=new BorderPane();
        Scene scene=new Scene(root,500,500);
        stage.setScene(scene);
        stage.setTitle("Hotel Management System");
        stage.show();

    //========================================= Dashboard =======================================================================================================

        Label title1=new Label("Total");
        value1=new Label(String.valueOf(h1.getTotalRooms()));
        title1.setStyle("-fx-font-size: 14px;");
        value1.setStyle("-fx-font-size: 22px; -fx-font-weight: bold;");
        VBox dash1=new VBox(10);
        dash1.getChildren().addAll(title1,value1);
        dash1.setAlignment(Pos.CENTER);
        dash1.setStyle("-fx-background-color: #1c61ec; -fx-border-color: #444; -fx-background-radius:10; -fx-border-radius: 10; -fx-padding: 15;");

        Label title2=new Label("Occupied");
        value2=new Label(String.valueOf(h1.getOccupiedRooms()));
        VBox dash2=new VBox(10);
        dash2.getChildren().addAll(title2,value2);
        dash2.setAlignment(Pos.CENTER);
        title2.setStyle("-fx-font-size: 14px;");
        value2.setStyle("-fx-font-size: 22px; -fx-font-weight: bold;");
        dash2.setStyle("-fx-background-color: #f53131; -fx-border-color: #444; -fx-background-radius:10; -fx-border-radius: 10; -fx-padding: 15;");
        
        Label title3=new Label("Available");
        value3=new Label(String.valueOf(h1.getAvailableRooms()));
        VBox dash3=new VBox(10);
        dash3.getChildren().addAll(title3,value3);
        dash3.setAlignment(Pos.CENTER);
        title3.setStyle("-fx-font-size: 14px;");
        value3.setStyle("-fx-font-size: 22px; -fx-font-weight: bold;");
        dash3.setStyle("-fx-background-color: #46ec1c; -fx-border-color: #444; -fx-background-radius:10; -fx-border-radius: 10; -fx-padding: 15;");

        Label title4=new Label("Revenue");
        value4=new Label("₹ "+String.valueOf(h1.showRevenue()));
        VBox dash4=new VBox(10);
        dash4.getChildren().addAll(title4,value4);
        dash4.setAlignment(Pos.CENTER);
        title4.setStyle("-fx-font-size: 14px;");
        value4.setStyle("-fx-font-size: 22px; -fx-font-weight: bold;");
        value4.setWrapText(true);
        dash4.setStyle("-fx-background-color: #e2f820; -fx-border-color: #444; -fx-background-radius:10; -fx-border-radius: 10; -fx-padding: 15;");

        dash1.setPrefWidth(120);
        dash2.setPrefWidth(120);
        dash3.setPrefWidth(120);
        dash4.setPrefWidth(120);
        Label dashTitle=new Label("Dashboard Overview");
        dashTitle.setStyle("-fx-font-size: 22px; -fx-font-weight: bold;");
        occupancy=new Label("Occupancy: "+String.valueOf(h1.occupancyPercentage())+"%");
        occupancy.setStyle("-fx-font-size: 22px; -fx-font-weight: bold;");
        HBox Dash=new HBox(10);
        Dash.setPrefHeight(420);
        Dash.getChildren().addAll(dash1,dash2,dash3,dash4);
        VBox dashboardPane=new VBox(10);
        dashboardPane.getChildren().addAll(dashTitle,Dash,occupancy);
        dashboardPane.setVisible(true);
        dashboardPane.setAlignment(Pos.CENTER);
        Dash.setAlignment(Pos.CENTER);
        dashTitle.setAlignment(Pos.CENTER);
        occupancy.setAlignment(Pos.CENTER);

    //========================================= Booking =======================================================================================================

        Label book=new Label("Booking Options");
        book.setStyle("-fx-font-size: 22px; -fx-font-weight: bold;");
        Label cin=new Label("Check In");
        cin.setStyle("-fx-font-size: 22px; -fx-font-weight: bold;");
        Label cout=new Label("Check Out");
        cout.setStyle("-fx-font-size: 22px; -fx-font-weight: bold;");
        Button chkin=new Button("Check In");
        Button chkout=new Button("Check Out");
        chkin.setAlignment(Pos.CENTER);
        chkout.setAlignment(Pos.CENTER);

        TextField name=new TextField();
        TextField days=new TextField();
        name.setPrefWidth(100);
        days.setPrefWidth(100);
        Label n=new Label("Guest Name:");
        Label d=new Label("Duration:");
        HBox Name=new HBox(2);
        Name.getChildren().addAll(n,name);
        HBox Days=new HBox(2);
        Days.getChildren().addAll(d,days);
        ComboBox<String> type=new ComboBox<>();
        type.getItems().add("Single");
        type.getItems().add("Deluxe");
        type.getItems().add("Suite");
        type.setValue("Single");
        VBox Chkinbox=new VBox(10);
        Chkinbox.getChildren().addAll(cin,Name,Days,type,chkin);
        Chkinbox.setStyle("-fx-background-color: #a8f50d; -fx-border-color: #444; -fx-background-radius:10; -fx-border-radius: 10; -fx-padding: 15;");

        TextField no=new TextField();
        no.setPrefWidth(100);
        Label N=new Label("Room No:");
        HBox No=new HBox(2);
        No.getChildren().addAll(N,no);
        VBox Chkoutbox=new VBox(10);
        Chkoutbox.getChildren().addAll(cout,No,chkout);
        Chkoutbox.setStyle("-fx-background-color: #f66539; -fx-border-color: #444; -fx-background-radius:10; -fx-border-radius: 10; -fx-padding: 15;");

        VBox bookingPane=new VBox(10);
        bookingPane.getChildren().addAll(book,Chkinbox,Chkoutbox);
        bookingPane.setPadding(new Insets(10));
        bookingPane.setVisible(false);
        bookingPane.setAlignment(Pos.CENTER);

        chkin.setOnAction(e->{
            String a=name.getText();
            int b=Integer.parseInt(days.getText());
            String c=type.getValue();
            int rno=h1.CheckIn(a, b, c);
            if(rno==-1)
            {
                Alert alert1=new Alert(Alert.AlertType.ERROR);
                alert1.setTitle("Check-In Status");
                alert1.setHeaderText(null);
                alert1.setContentText("All Rooms Are Occupied!");
                alert1.showAndWait();
                name.clear();
                days.clear();
            }
            else
            {
                updateDashboard();
                updateRoomsTab();
                updateGuestsTab();
                Alert alert=new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Check-In Status");
                alert.setHeaderText(null);
                alert.setContentText("Check-In Successful!\nRoom No Allotted:"+rno);
                alert.showAndWait();
                name.clear();
                days.clear();
            }
        });

        chkout.setOnAction(e->{
            int f=Integer.parseInt(no.getText());
            if(f>35||h1.emptyCheck(f)==1)
            {
                Alert alert=new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Check-Out Status");
                alert.setHeaderText(null);
                alert.setContentText("Invalid Room No Entered!");
                alert.showAndWait();
                no.clear();
            }
            else
            {
                float Bill=h1.CheckOut(f);
                updateDashboard();
                updateRoomsTab();
                updateGuestsTab();
                Alert alert=new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Check-Out Status");
                alert.setHeaderText(null);
                alert.setContentText("Check-Out Successful!\nTotal Bill : ₹"+Bill);
                alert.showAndWait();
                no.clear();
                PauseTransition p1=new PauseTransition(Duration.seconds(7));
                p1.setOnFinished(event->{
                    h1.makeRoomAvailable(f);
                    updateDashboard();
                    updateRoomsTab();
                    updateGuestsTab();
                });
                p1.play();
            }
        });
    
    //========================================= Rooms =======================================================================================================

        updateRoomsTab();
        ScrollPane scroll=new ScrollPane();
        scroll.setContent(roomPane);
        scroll.setVisible(false);
        scroll.setFitToWidth(true);
        roomPane.setVisible(false);
        roomPane.setAlignment(Pos.CENTER);

    //========================================= Guests =======================================================================================================

        updateGuestsTab();
        ScrollPane scroll1=new ScrollPane();
        scroll1.setContent(guestPane);
        scroll1.setVisible(false);
        scroll1.setFitToWidth(true);
        guestPane.setVisible(false);
        guestPane.setAlignment(Pos.CENTER);


    //========================================= Main Stage =======================================================================================================

        HBox header=new HBox();
        Label heading=new Label("SmartStay");
        header.getChildren().addAll(heading);
        header.setAlignment(Pos.CENTER_LEFT);
        header.setPadding(new Insets(5));
        heading.setStyle("-fx-font-size: 14px;-fx-font-weight: bold;");
        VBox sidebar=new VBox(10);
        Button tab1=new Button("Dashboard");
        Button tab2=new Button("Booking");
        Button tab3=new Button("Rooms");
        Button tab4=new Button("Guests");
        tab1.setMaxWidth(Double.MAX_VALUE);
        tab2.setMaxWidth(Double.MAX_VALUE);
        tab3.setMaxWidth(Double.MAX_VALUE);
        tab4.setMaxWidth(Double.MAX_VALUE);
        sidebar.getChildren().addAll(tab1,tab2,tab3,tab4);
        sidebar.setAlignment(Pos.TOP_LEFT);
        sidebar.setPadding(new Insets(5));
        
        StackPane center=new StackPane(dashboardPane,bookingPane,scroll,scroll1);

        tab1.setOnAction(e->{
            dashboardPane.setVisible(true);
            bookingPane.setVisible(false);
            roomPane.setVisible(false);
            scroll.setVisible(false);
            guestPane.setVisible(false);
            scroll1.setVisible(false);
        });

        tab2.setOnAction(e->{
            dashboardPane.setVisible(false);
            bookingPane.setVisible(true);
            roomPane.setVisible(false);
            scroll.setVisible(false);
            guestPane.setVisible(false);
            scroll1.setVisible(false);
        });

        tab3.setOnAction(e->{
            dashboardPane.setVisible(false);
            bookingPane.setVisible(false);
            roomPane.setVisible(true);
            scroll.setVisible(true);
            guestPane.setVisible(false);
            scroll1.setVisible(false);
        });

        tab4.setOnAction(e->{
            dashboardPane.setVisible(false);
            bookingPane.setVisible(false);
            roomPane.setVisible(false);
            scroll.setVisible(false);
            guestPane.setVisible(true);
            scroll1.setVisible(true);
        });

        root.setTop(header);
        root.setLeft(sidebar);
        root.setCenter(center);
        header.setStyle("-fx-font-size: 15px; -fx-font-weight: bold;");

        header.setPrefSize(500,50);
        sidebar.setPrefSize(100,500);
        center.setPrefSize(Double.MAX_VALUE,Double.MAX_VALUE);
        header.setStyle("-fx-background-color: #37bac1; -fx-border-color: #444; -fx-padding: 15;");
        sidebar.setStyle("-fx-background-color: #37bac1; -fx-border-color: #444;");
        center.setStyle("-fx-background-color: #37bac1; -fx-border-color: #444; -fx-padding: 15;");
    }

    public static void main(String[] args) {
        launch();
    }
}
