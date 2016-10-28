package groupLUXURY.hotel.data;

import groupLUXURY.util.ListUtilities;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import dw317.hotel.business.interfaces.Customer;
import dw317.hotel.business.interfaces.Reservation;
import dw317.hotel.business.interfaces.Room;

/**
 * @author kajal
 *
 */
public class SortMergeApp {
	public static void main(String[] args){
		Path databaseFolderPath = Paths.get("ReservationSys/datafiles/database");
		if (Files.notExists(databaseFolderPath)){
			File databaseDirectory = new File("ReservationSys/datafiles/database");
		}
	}
	

}
//Kajals code START
///**
//* The following code is getting the current user directory
//* and simply creating a new directory called "datafile" and 
//* inside that a new text file called "rooms.txt"
//*/
//
//String property = System.getProperty("user.dir"); //get current directory
//System.out.println(property);                     //print out current directory 
//property += File.separator + "datafiles"+File.separator; //File.separator is same as "/" so /datafile(directory)/
//String roomFile = property + "rooms.txt";                // ./datafile/room.txt  //this is the full path
//
//Room[] roomList = null;      //created roomList
//try{
//	roomList = HotelFileLoader.getRoomListFromSequentialFile(roomFile);  //loading the list 
//}catch(IOException e){
//	
//}
//
//ListUtilities.sort(roomList);
//File database =  new File(property + "database");  //setup path 
//File newRoom = new File(property +"database"+ File.separator + "rooms.txt"); //setup path
//
//try{
//	database.mkdir();        //create database directory
//	newRoom.createNewFile(); //make the rooms.txt file 
//	ListUtilities.saveListToTextFile(roomList, property +"database"+ File.separator + "rooms.txt"); 
//	// =_=
//	//I need saveListToTextFile to accept a path(including file name) and 
//	//not always save to "C:\\Users\\Sebastian\\Desktop\\Project\\ReservationSys\\datafiles\\duplicates.txt"
//}catch(Exception e){
//	
//}
//		
//
//
///**
//* This is getting the user directory and loading the customer
//* files to an arrays of customers and sorting them
//*/
//
//String property2 = System.getProperty("user.dir");             //get current directory
//property2 += File.separator + "datafiles"+File.separator   
//		+ "unsorted" + File.separator + "customers" + File.separator;   // ./datafiles/unsorted/customer/(customer file should be in a dir called "customer")
//File customerFiles = new File(property2);    //create file
//String[] custFile =  customerFiles.list();   //string array will get list of all customer files name
//
//
//
//Customer[] custList = null;      //making sure it is null
//for(String custfile: custFile){  //Just a fancy way of saying go trough the whole list
//	try{
//		custList = HotelFileLoader.getCustomerListFromSequentialFile(property2+custfile); //load all the customers to the Customer arrays
//		ListUtilities.sort(custList); // sort
//	}catch(IOException e){
//		
//	}catch(IllegalArgumentException c){
//		
//	}
//}
//
//
///**
//* This is getting the user directory and loading the reservation
//* files to an arrays of reservation and sorting them
//*/	
//
//String property3 = System.getProperty("user.dir");         //get current directory
//property3 += File.separator + "datafiles"+File.separator   
//		+ "unsorted" + File.separator + "reservations" + File.separator;  // ./datafiles/unsorted/reservations/ (customer file should be in a dir called "customer")
//File reservationFiles = new File(property3); 
//String[] resFile =  reservationFiles.list(); //string array will get list of all reservation files name
//
//for(String r : resFile){               //r is simply holding the reservation file name
//	System.out.println(property3+ r);  //print out all the reservation path + file names 
//}
//
//
//Reservation[] resList = null;
//for(String resfile: resFile){
//	try{
//		resList = HotelFileLoader.getReservationListFromSequentialFile
//		(property3 + resFile, custList, roomList); //load all the reservation to the Reservation arrays
//		ListUtilities.sort(resList);  //sort
//	}catch(IOException e){
//		
//	}catch(IllegalArgumentException c){
//		
//	}
//}
//Kajals code END
