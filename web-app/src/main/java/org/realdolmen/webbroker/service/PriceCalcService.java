package org.realdolmen.webbroker.service;

import org.realdolmen.webbroker.model.Booking;
import org.realdolmen.webbroker.model.Discount;
import org.realdolmen.webbroker.model.Flight;
import org.realdolmen.webbroker.model.Trip;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import java.time.Period;

/**
 * Created by RDEAX37 on 12/10/2015.
 * Class to use when you need to calculate prices
 *
 * @author Robin D'Haese
 */
@Stateless
@LocalBean
public class PriceCalcService {

    /**
     * @param flight to calculate total price for
     * @return flight price + (flight price * (flight margin / 100))
     */
    public Double getTotalFlightPrice(Flight flight) {
        return flight.getPrice() + (flight.getPrice() * (flight.getMargin()) / 100);
    }

    /**
     * @param trip to calculate total price for
     * @return trip accommodation price * total days of trip + total trip flight price
     */
    public Double getTotalTripPrice(Trip trip) {
        return trip.getAccommodationPrice() * getDaysOfTrip(trip) + getTotalFlightPrice(trip.getFlight());
    }

    /**
     * @param trip to calculate total days for
     * @return the amount of days between the trips start date and end date
     */
    public Integer getDaysOfTrip(Trip trip) {
        return Period.between(trip.getStartDate().toLocalDate(), trip.getEndDate().toLocalDate()).getDays();
    }

    /**
     * @param booking to calculate total price without discount for
     * @return total booking trip price * booking number of passengers
     */
    public Double getTotalBookingPriceWithoutDiscount(Booking booking) {
        return getTotalTripPrice(booking.getTrip()) * booking.getNumberOfPassengers();
    }

    /**
     * @param booking to calculate total price with discount for
     * @return (total booking trip price * booking number of passengers) - total of booking discounts
     */
    public Double getTotalBookingPriceWithDiscount(Booking booking) {
        return getTotalBookingPriceWithoutDiscount(booking) - getTotalDiscountForBooking(booking);
    }

    /**
     * @param booking to calculate total discount for
     * @return total of (for each discount -&gt; total discount)
     */
    public Double getTotalDiscountForBooking(Booking booking) {
        Double totalDiscount = 0D;
        for (Discount discount : booking.getDiscounts()) {
            totalDiscount += getDiscountTotalFor(getTotalBookingPriceWithoutDiscount(booking), discount);
        }
        return totalDiscount;
    }

    /**
     * @param totalPrice to calculate discount on
     * @param discount   to calculate from
     * @return If not a percentage, the quantity of the discount is returned. If it is a percentage: the total price is divided by 100 and multiplied with the discounts' quantity
     */
    public Double getDiscountTotalFor(Double totalPrice, Discount discount) {
        if (discount.isPercentage()) {
            return (totalPrice / 100) * discount.getQuantity();
        }
        return discount.getQuantity();
    }
}
