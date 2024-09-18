package com.hotel.Paradise_Hotel.service;

import com.hotel.Paradise_Hotel.exception.InternalServerException;
import com.hotel.Paradise_Hotel.exception.ResourceNotFoundException;
import com.hotel.Paradise_Hotel.model.Room;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface RoomService {

    Room addNewRoom(MultipartFile photo, String roomType, BigDecimal roomPrice) throws IOException, SQLException;

    List<String> getAllRoomTypess();

    List<Room> getAllRooms();

    byte[] getRoomPhotoByRoomId(Long roomId) throws SQLException, ResourceNotFoundException;

    void deletRoom(Long roomId);

    Room updateRoom(Long roomId, String roomType, BigDecimal roomPrice, byte[] photoBytes) throws InternalServerException;

    Optional<Room> getRoomId(Long roomId);
}
    
    

