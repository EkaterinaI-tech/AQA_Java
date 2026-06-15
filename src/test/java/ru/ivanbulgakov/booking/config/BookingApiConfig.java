package ru.ivanbulgakov.booking.config;

import org.aeonbits.owner.ConfigFactory;
import ru.ivanbulgakov.demowebshop.config.WebDriverConfig;

public class BookingApiConfig {

    private static final BookingConfig config = ConfigFactory.create(BookingConfig.class, System.getProperties());

    public static BookingConfig getBookingConfig() {
        return config;
    }
}
