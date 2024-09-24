package com.hotel.Paradise_Hotel.service;

import com.hotel.Paradise_Hotel.exception.InvalidBookingRequestException;
import com.hotel.Paradise_Hotel.exception.ResourceNotFoundException;
import com.hotel.Paradise_Hotel.model.BookedRoom;
import com.hotel.Paradise_Hotel.model.Room;
import com.hotel.Paradise_Hotel.repository.BookingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BookingServiceImpl implements BookingService {
    private final BookingRepository bookingRepository;
    private final RoomService roomService;


    @Override
    public List<BookedRoom> getAllBookingsByRoomID(Long roomId) {
        return bookingRepository.findByRoomId(roomId);

    }

    @Override
    public List<BookedRoom> getAllBookings() {
        return bookingRepository.findAll();

    }

    @Override
    public BookedRoom findByBookingConfirmationCode(String confirmationCode) {

        return bookingRepository.findByBookingConfirmationCode(confirmationCode).orElseThrow(() -> new ResourceNotFoundException("Booking not Found"));
    }

    @Override
    public String saveBooking(Long roomId, BookedRoom bookingRequest) {
        if (bookingRequest.getCheckOutDate().isBefore(bookingRequest.getCheckInDate())) {
            throw new InvalidBookingRequestException("Check-in date must come before check-out date");

        }
        Room room = roomService.getRoomId(roomId).get();
        List<BookedRoom> existingBookings = room.getBookings();
        boolean roomIsAvailable= roomIsAvialable(bookingRequest, existingBookings);
        if(roomIsAvailable){
            room.addBooking(bookingRequest);
            bookingRepository.save(bookingRequest);
        }else {
            throw new InvalidBookingRequestException("Sorry, This room has been booked for the selected dates");
        }
        return bookingRequest.getBookingConfirmationCode();
      }

        @Override
        public void cancelBooking(Long bookingId) {
            bookingRepository.deleteById(bookingId);
        }




    private  boolean roomIsAvialable ( BookedRoom bookingRequest, List<BookedRoom> existingBookings){
        return existingBookings.stream().noneMatch(
                existingBooking ->
                        bookingRequest.getCheckInDate().equals(existingBooking.getCheckInDate())
                                || (bookingRequest.getCheckOutDate().isBefore(existingBooking.getCheckOutDate()))
                                || bookingRequest.getCheckInDate().isAfter(existingBooking.getCheckInDate())
                                && bookingRequest.getCheckInDate().isBefore(existingBooking.getCheckOutDate())
                                || (bookingRequest.getCheckInDate().isBefore(existingBooking.getCheckInDate()))
                                && bookingRequest.getCheckOutDate().equals(existingBooking.getCheckOutDate())
                                || (bookingRequest.getCheckInDate().isBefore(existingBooking.getCheckInDate()))
                                && bookingRequest.getCheckOutDate().isAfter(existingBooking.getCheckOutDate())
                                || (bookingRequest.getCheckInDate().equals(existingBooking.getCheckOutDate()))
                                && bookingRequest.getCheckOutDate().equals(existingBooking.getCheckInDate())
                                ||(bookingRequest.getCheckInDate().equals(existingBooking.getCheckOutDate()))
                                && bookingRequest.getCheckOutDate().equals(bookingRequest.getCheckInDate())

        );
        }



}
