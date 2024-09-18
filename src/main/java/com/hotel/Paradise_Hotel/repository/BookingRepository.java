package com.hotel.Paradise_Hotel.repository;

import com.hotel.Paradise_Hotel.model.BookedRoom;
import com.hotel.Paradise_Hotel.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookingRepository extends JpaRepository<BookedRoom,Long> {



    List<BookedRoom> findByRoomId(Long roomId);
    BookedRoom findByBookingConfirmationCode(String confirmationCode);
}
