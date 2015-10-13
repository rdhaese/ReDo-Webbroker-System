package org.realdolmen.webbroker.controller;

import org.realdolmen.webbroker.model.Booking;
import org.realdolmen.webbroker.model.Discount;
import org.realdolmen.webbroker.model.Trip;
import org.realdolmen.webbroker.repository.BookingRepository;
import org.realdolmen.webbroker.repository.DiscountRepository;
import org.realdolmen.webbroker.repository.FlightRepository;
import org.realdolmen.webbroker.service.PriceCalcService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;
import java.io.Serializable;

/**
 * Controller to purchase a trip (confirmation of a booking).
 *
 * @author Youri Flement
 */
@Named
@RequestScoped
public class  ConfirmBookingController implements Serializable {

    private static final Logger LOGGER = LoggerFactory.getLogger(ConfirmBookingController.class);

    @Inject
    private CurrentBookingController currentBookingController;
    @Inject
    private LoggedInUserController loggedInUserController;

    @Inject
   private BookingRepository bookingRepository;

    @Inject
    private FlightRepository flightRepository;

    @Inject
    private DiscountRepository discountRepository;

    @Inject
    private PriceCalcService priceCalcService;


    //TODO REMOVE STATE BEFORE PRODUCTION (keep fields!//
    private String creditcardNumber = "67034200303993013";
    private String creditcardExpiryDate = "01/16";
    /////////////////////////////////////////////////////

    private String paymentMethod;
    private boolean noMoreSeats = false;

    @PostConstruct
    public void initializePaymentMethod(){
        setPaymentMethod("credit-card");
    }

    @Transactional
    public String purchase() {
        Booking booking = currentBookingController.getCurrentBooking();
        booking.setBookingUser(loggedInUserController.getLoggedInUser());
        Trip trip =  booking.getTrip();
        Integer availableSeats = trip.getFlight().getAvailableSeats();
        if(availableSeats < booking.getNumberOfPassengers()) {
            noMoreSeats = true;
            return "confirm-booking";
        } else {
            bookingRepository.addBooking(booking);
            trip.getFlight().setAvailableSeats(availableSeats - booking.getNumberOfPassengers());
            flightRepository.updateFlight(trip.getFlight());

            LOGGER.info("New booking: " + booking);
            currentBookingController.setCurrentBooking(null);
            return "thank-you";
        }
    }

    public Double getTotalPriceWithDiscountForCurrentBooking(){
        return priceCalcService.getTotalBookingPriceWithDiscount(currentBookingController.getCurrentBooking());
    }

    public Double getTotalPriceWithoutDiscountForCurrentBooking(){
        return priceCalcService.getTotalBookingPriceWithoutDiscount(currentBookingController.getCurrentBooking());
    }

    public String getCreditcardNumber() {
        return creditcardNumber;
    }

    public void setCreditcardNumber(String creditcardNumber) {
        this.creditcardNumber = creditcardNumber;
    }

    public String getCreditcardExpiryDate() {
        return creditcardExpiryDate;
    }

    public void setCreditcardExpiryDate(String creditcardExpiryDate) {
        this.creditcardExpiryDate = creditcardExpiryDate;
    }

    public boolean isNoMoreSeats() {
        return noMoreSeats;
    }

    public void setNoMoreSeats(boolean noMoreSeats) {
        this.noMoreSeats = noMoreSeats;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    /**
     * @param paymentMethod the user wants to use
     * @return 'confirm-booking', so the page can be refreshed
     */
    public String setPaymentMethod(String paymentMethod) {
        Discount discount = discountRepository.getOnName("Credit Card");
       if("credit-card".equals(paymentMethod)) {
           if (!currentBookingController.getCurrentBooking().getDiscounts().contains(discount)) {
               currentBookingController.getCurrentBooking().getDiscounts().add(discount);
           }
       } else {
           if (currentBookingController.getCurrentBooking().getDiscounts().contains(discount)) {
               currentBookingController.getCurrentBooking().getDiscounts().remove(discount);
           }
       }
        this.paymentMethod = paymentMethod;
        return "confirm-booking";
    }
}
