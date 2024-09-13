package com.hotel.Paradise_Hotel.service;

import com.hotel.Paradise_Hotel.model.Room;
import com.hotel.Paradise_Hotel.repository.RoomRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.sql.rowset.serial.SerialBlob;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Blob;
import java.sql.SQLException;

@Service
public class RoomServiceImpl implements  RoomService {
    private final RoomRepository roomRepository;

    public RoomServiceImpl(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    @Override
    public Room addNewRoom(MultipartFile file, String roomType, BigDecimal roomPrice) throws IOException, SQLException {
        Room room= new Room();
        room.setRoomType(roomType);
        room.setRoomPrice(roomPrice);
        if(!file.isEmpty()){
            byte[] photBytes= file.getBytes();
            Blob photoBlob =new SerialBlob(photBytes);
            room.setPhoto(photoBlob);
            return roomRepository.save(room);
        }
        return null;
    }
}
