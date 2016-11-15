package groupLUXURY.hotel.data;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import dw317.hotel.business.DawsonHotelFactory;
import dw317.hotel.business.RoomType;
import dw317.hotel.business.interfaces.Customer;
import dw317.hotel.business.interfaces.HotelFactory;
import dw317.hotel.business.interfaces.Reservation;
import dw317.hotel.business.interfaces.Room;
import dw317.hotel.data.interfaces.ListPersistenceObject;
import dw317.hotel.data.interfaces.ReservationDAO;

public class ReservationListDB implements ReservationDAO {

        private List<Reservation> database;
        private List<Room> allRooms;
        private final ListPersistenceObject listPersistenceObject;
        private final HotelFactory factory;

        public ReservationListDB(ListPersistenceObject listPersistenceObject) {
                this.listPersistenceObject = listPersistenceObject;
                this.database = this.listPersistenceObject.getReservationDatabase();
                this.allRooms = listPersistenceObject.getRoomDatabase();
                this.factory = DawsonHotelFactory.DAWSON;

        }

        public ReservationListDB(ListPersistenceObject listPersistenceObject, HotelFactory factory) {
                this.listPersistenceObject = listPersistenceObject;
                this.database = this.listPersistenceObject.getReservationDatabase();
                this.allRooms = listPersistenceObject.getRoomDatabase();
                this.factory = factory;
        }

        @Override
        public void add(Reservation reserv) throws DuplicateReservationException {
                for (int i = 0; i < database.size(); i++) {
                        if (this.database.get(i).overlap(reserv)) // check if the
                                                                                                                // reservation overlaps
                                                                                                                // with an existing one.
                        {
                                throw new DuplicateReservationException();
                        }
                }
                Reservation copyReserv = factory.getReservationInstance(reserv.getCustomer(), reserv.getRoom(),
                                reserv.getCheckInDate().getYear(), reserv.getCheckInDate().getMonthValue(),
                                reserv.getCheckInDate().getDayOfMonth(), reserv.getCheckOutDate().getYear(),
                                reserv.getCheckOutDate().getMonthValue(), reserv.getCheckOutDate().getDayOfMonth());

                int index = binarySearch(copyReserv);
                database.add(index, copyReserv);
        }

        private int binarySearch(Reservation keyReserv) {
                int low = 0;
                int high = database.size() - 1;
                int mid = 0;
                while (low <= high) {
                        mid = (low + high) / 2;
                        if (database.get(mid).compareTo(keyReserv) > 0)// copy is smaller
                        {
                                high = mid - 1;
                        } else if (database.get(mid).compareTo(keyReserv) < 0) // copy is
                                                                                                                                        // bigger
                        {
                                low = mid + 1;
                        }
                }
                return low;
        }

        @Override
        public void disconnect() throws IOException {
                listPersistenceObject.saveReservationDatabase(this.database);
                this.database = null;
        }

        @Override
        public List<Reservation> getReservations(Customer cust) {
                List<Reservation> reservations = new ArrayList<>();
                for (Reservation r : this.database) {
                        if (r.getCustomer().equals(cust)) {
                                reservations.add(r);
                        }
                }
                return reservations;
        }

        @Override
        public void cancel(Reservation reserv) throws NonExistingReservationException {
                int index = binarySearch(reserv);
                if (index > 0) {
                        this.database.remove(index);
                } else {
                        throw new NonExistingReservationException("No such reservation was found in the database.");
                }
        }

        @Override
        public List<Room> getReservedRooms(LocalDate checkin, LocalDate checkout) {
                List<Room> reservedRooms = new ArrayList<>();

                for (int index = 0; index < this.database.size(); index++) 
                {
                        if ((this.database.get(index).getCheckInDate().isBefore(checkout))
                                        && ((this.database.get(index).getCheckOutDate().isAfter(checkin)))) 
                        {
                                                reservedRooms.add(this.database.get(index).getRoom());
                        }
                }
                Collections.sort(reservedRooms);
                removeDuplicates(reservedRooms);
                return reservedRooms;

        }
        
        private static void removeDuplicates(List<Room> rooms){
                for (int index=0;index<rooms.size()-1;index++){
                                if (rooms.get(index).equals(rooms.get(index+1))){
                                        rooms.remove(index+1);
                                        index--;
                        }
                }
        }

        @Override
        public List<Room> getFreeRooms(LocalDate checkin, LocalDate checkout) {

                List<Room> reservedRooms = new ArrayList<>();

                for (int index = 0; index < this.database.size(); index++) 
                {
                        if (!((this.database.get(index).getCheckInDate().isBefore(checkout))
                                        && ((this.database.get(index).getCheckOutDate().isAfter(checkin))))) 
                        {
                                                reservedRooms.add(this.database.get(index).getRoom());
                        }
                }
                Collections.sort(reservedRooms);
                removeDuplicates(reservedRooms);
                return reservedRooms;


        }

        @Override
        public List<Room> getFreeRooms(LocalDate checkin, LocalDate checkout, RoomType roomType) {
                List<Room> reservedRooms = new ArrayList<>();

                for (int index = 0; index < this.database.size(); index++) 
                {
                        if (!((this.database.get(index).getCheckInDate().isBefore(checkout))
                                        && ((this.database.get(index).getCheckOutDate().isAfter(checkin))))) 
                        {
                                if((this.database.get(index).getRoom().getRoomType()==roomType)){
                                        reservedRooms.add(this.database.get(index).getRoom());
                                }
                                                
                        }
                }
                Collections.sort(reservedRooms);
                removeDuplicates(reservedRooms);
                return reservedRooms;
        }

        @Override
        public void clearAllPast() {
                for (int i = 0; i < this.database.size(); i++) {
                        if (this.database.get(i).getCheckOutDate().compareTo(LocalDate.now()) < 0) {
                                this.database.remove(i);
                                i--;
                        }
                }

        }

        @Override
        public String toString() {
                StringBuilder total = new StringBuilder("Number of reservations in database: " + database.size());

                for (int i = 0; i < database.size(); i++) {
                        total.append("\n" + database.get(i));
                }

                return total.toString();
        }

}
