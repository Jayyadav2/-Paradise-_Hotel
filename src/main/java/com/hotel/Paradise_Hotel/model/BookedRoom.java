package com.hotel.Paradise_Hotel.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Locale;
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookedRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookingId;

    @Column(name="Check_In")
    private LocalDate checkInDate;

    @Column(name="Check_Out")
    private LocalDate checkOutDate;

    @Column(name="guest_FullName")
    private String guestFullName;

    @Column(name="guest_Email")
    private String guestEmail;

    @Column(name="adults")
    private int numOfAdults;

    @Column(name="children")
    private int numOfChildren;

    @Column(name="total_guest")
    private int totalNumOfGuest;

    @Column(name="confirmation_Code")
    private String bookingConfirmationCode;

    @ManyToOne(fetch = FetchType.LAZY)

    @JoinColumn (name = "room_id")
    private Room room;

    public void calculateTotalNumOFGuest(){
        this.totalNumOfGuest=this.numOfAdults +this.numOfChildren;
    }

    public void setNumOfAdults(int numofAdults) {
        this.numOfAdults = numofAdults;
        calculateTotalNumOFGuest();
    }

    public void setNumOfChildren(int numofChildren) {
        this.numOfChildren = numofChildren;
        calculateTotalNumOFGuest();
    }

    public void setBookingConfirmationCode(String bookingConfirmationCode) {
        this.bookingConfirmationCode = bookingConfirmationCode;
    }
}
