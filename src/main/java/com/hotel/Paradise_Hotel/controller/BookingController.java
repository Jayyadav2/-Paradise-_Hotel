package com.hotel.Paradise_Hotel.controller;

import com.hotel.Paradise_Hotel.exception.InvalidBookingRequestException;
import com.hotel.Paradise_Hotel.exception.ResourceNotFoundException;
import com.hotel.Paradise_Hotel.model.BookedRoom;
import com.hotel.Paradise_Hotel.model.Room;
import com.hotel.Paradise_Hotel.response.BookingResponse;
import com.hotel.Paradise_Hotel.response.RoomResponse;
import com.hotel.Paradise_Hotel.service.BookingService;
import com.hotel.Paradise_Hotel.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import  java.util.List;



@RestController
@RequiredArgsConstructor
@RequestMapping("/bookings")
public class BookingController {

    private final BookingService bookingService;
    private final RoomService roomService;



@GetMapping("all-bookings")
    public ResponseEntity<List<BookingResponse>> getAllBookings(){
        List<BookedRoom> bookings= bookingService.getAllBookings();
        List<BookingResponse> bookingResponses= new ArrayList<>();
        for(BookedRoom room : bookings){
            BookingResponse bookingResponse= getBookingResponse(room);
            bookingResponses.add(bookingResponse);
        }
        return ResponseEntity.ok(bookingResponses);


    }


    @GetMapping("/confirmation/{confirmationCode}")
    public ResponseEntity<?> getBookingByConfirmationCode(@PathVariable String confirmationCode){

    try{
        BookedRoom booking= bookingService.findByBookingConfirmationCode(confirmationCode);
        BookingResponse bookingResponse= getBookingResponse(booking);
        return  ResponseEntity.ok(bookingResponse);
    }catch (ResourceNotFoundException ex){
        return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
     }
  }



  @PostMapping ("/room/{roomId}/booking")
  public  ResponseEntity<?> saveBooking(@PathVariable Long roomId, @RequestBody BookedRoom bookingRequest){

    try {
        String confirmationCode= bookingService.saveBooking(roomId, bookingRequest);
        return ResponseEntity.ok("Room booked successfully ! Your booking confirmation code is : "+confirmationCode);

    }catch (InvalidBookingRequestException ex){
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
  }


  @DeleteMapping("/booking/{bookingId}/delete")
  public void cancelBooking(@PathVariable Long bookingId){
    bookingService.cancelBooking(bookingId);

  }


    private BookingResponse getBookingResponse(BookedRoom booking) {

        Room theRoom= roomService.getRoomId(booking.getRoom().getId()).get();
        RoomResponse room= new RoomResponse(
                theRoom.getId(),
                theRoom.getRoomType(),
                theRoom.getRoomPrice());

        return  new BookingResponse(booking.getBookingId(), booking.getCheckInDate(),booking.getCheckOutDate(), booking.getGuestFullName()
        , booking.getGuestEmail(), booking.getNumOfAdults(), booking.getNumOfChildren(), booking.getTotalNumOfGuests(), booking.getBookingConfirmationCode()
        , room);
    }
}
