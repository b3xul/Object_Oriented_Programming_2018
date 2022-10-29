package main;
import java.util.*;

import flightBookings.*;
public class Example {

	
static void print(Object obj) {System.out.println(obj);}

public static void main(String[] args) throws FBMException{
	FBookingMgr mgr = new FBookingMgr(); 
	List<String> list1;
	
	//R1
	print("R1");
	mgr.addFareTypes("basic", "standard", "flexible");
		//try{mgr.addFareTypes("premium");}
			//catch(FBMException ex) {print("method addFareTypes already called");}
	list1 = mgr.addFlightsForDestination("Paris", "flight2", "flight1", "flight2", "flight3");
		print(list1); //[flight1, flight2, flight3] local dup of flight2
	list1 = mgr.addFlightsForDestination("London", "flight9", "flight7", "flight6", "flight8");
		print(list1); //[flight6, flight7, flight8, flight9]
	list1 = mgr.addFlightsForDestination("Berlin", "flight11", "flight2");		
		print(list1); //[flight11] global dup of flight2

	
//		mgr.addFareTypes("basifdfdc", "stangdfdard", "flexigdfgble");
	
//	//R2	
	print("R2");
	mgr.addOffer("o2", "flight1", 10, "flexible", 100); //Paris
	mgr.addOffer("o3", "flight1", 30, "standard", 80); 
	mgr.addOffer("o4", "flight3", 15, "flexible", 90); 
	mgr.addOffer("o5", "flight3", 50, "standard", 70); 
		try{mgr.addOffer("o20", "flight20", 50, "standard", 70); ;}
			catch(FBMException ex) {print("flight20 undefined");}
		try{mgr.addOffer("o20", "flight6", 50, "premium", 70); ;}
			catch(FBMException ex) {print("premium undefined");}
	mgr.addOffer("o7", "flight8", 20, "standard", 60); //London
	mgr.addOffer("o6", "flight9", 100, "basic", 25); 
	mgr.addOffer("o8", "flight11", 5, "basic", 30); //Berlin

	mgr.addRequest("r1", "Paris", "flexible", 120, 4);
	mgr.addRequest("r2", "Paris", "standard", 100, 2);
	mgr.addRequest("r3", "London", "basic", 50, 3);
	mgr.addRequest("r4", "Berlin", "basic", 50, 3);
	mgr.addRequest("r5", "Berlin", "basic", 25, 3);
		try{mgr.addRequest("r20", "Dublin", "flexible", 120, 4);}
			catch(FBMException ex) {print("Dublin undefined");}
		try{mgr.addRequest("r20", "Paris", "premium", 120, 4);}
			catch(FBMException ex) {print("premium undefined");}
	int n;
	n = mgr.getNRequests("Paris"); print(n); //2
	n = mgr.getNOffers("Paris"); print(n);  //4

	//R3
	print("R3");
	mgr.addBooking("b1", "r1", "o2"); //4 flexible price 100
	int total = mgr.getTotalPrice("b1"); print(total); //400
		try{mgr.getTotalPrice("b20");}
			catch(FBMException ex) {print("undefined booking");}
	mgr.addBooking("b2", "r3", "o6"); //3 basic price 25
	mgr.addBooking("b3", "r4", "o8"); //3 basic	price 30 2 seats left
		try{mgr.addBooking("b20", "r1", "o8");}
			catch(FBMException ex) {print("different destinations");}
		try{mgr.addBooking("b20", "r1", "o3");}
			catch(FBMException ex) {print("different fare types");}
		try{mgr.addBooking("b20", "r5", "o8");}
			catch(FBMException ex) {print("request.maxPrice < offer.price");}
		try{mgr.addBooking("b20", "r4", "o8");}
			catch(FBMException ex) {print("insufficient number of available seats");}
		
//	//R4
	print("R4");
	SortedMap<Integer, List<String>> map1 = mgr.destinationsPerNSeats();
		print(map1); //{6=[Berlin, Paris], 3=[London]}
	SortedMap<String, Integer> map2 = mgr.revenuesPerFareType();
		print(map2); //{basic=165, flexible=400}

}

}

