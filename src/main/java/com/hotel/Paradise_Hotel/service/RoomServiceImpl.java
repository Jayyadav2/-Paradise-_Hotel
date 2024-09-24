package com.hotel.Paradise_Hotel.service;

import com.hotel.Paradise_Hotel.exception.InternalServerException;
import com.hotel.Paradise_Hotel.exception.ResourceNotFoundException;
import com.hotel.Paradise_Hotel.model.Room;
import com.hotel.Paradise_Hotel.repository.RoomRepository;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialException;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Blob;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

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

    @Override
    public List<String> getAllRoomTypess() {
        return roomRepository.findDistinctRoomTypes();
    }

    @Override
    public List<Room> getAllRooms() {
        return roomRepository.findAll();
    }
    public byte[] getRoomPhotoByRoomId(Long roomId) throws SQLException, ResourceNotFoundException {
        Optional<Room> theRoom= roomRepository.findById(roomId);
        if(theRoom.isEmpty()){
            throw new ResourceNotFoundException("Sorry, Room not Found");
        }
        Blob photoBlob= theRoom.get().getPhoto();
        if(photoBlob!=null){
            return photoBlob.getBytes(1,(int)photoBlob.length());
        }
        return null;
    }

    @Override
    public void deletRoom(Long roomId) {
        Optional<Room> theRoom= roomRepository.findById(roomId);
        if(theRoom.isPresent()){
            roomRepository.deleteById(roomId);
        }

    }

    @Override
    public Room updateRoom(Long roomId, String roomType, BigDecimal roomPrice, byte[] photoBytes) throws InternalServerException {

        Room room =roomRepository.findById(roomId).orElseThrow(() -> new ResourceNotFoundException("Room not found"));
        if (roomType!=null) room.setRoomType((roomType));
        if(roomPrice!=null) room.setRoomPrice(roomPrice);
        if(photoBytes!=null && photoBytes.length>0){
            try {
                room.setPhoto(new SerialBlob(photoBytes));
            }catch (SerialException e) {
                throw new RuntimeException(e);
            } catch (SQLException ex) {
                throw new InternalServerException("Error updating Room");
            }
        }


        return roomRepository.save(room);
    }

    @Override
    public Optional<Room> getRoomId(Long roomId) {
        return Optional.of(roomRepository.findById(roomId).get());
    }

    @Override
    public List<Room> getAvailableRooms(LocalDate checkInDate, LocalDate checkOutDate, String roomType) {
        return roomRepository.findAvailableRoomByDateAndType(checkInDate, checkOutDate, roomType);
    }


}
