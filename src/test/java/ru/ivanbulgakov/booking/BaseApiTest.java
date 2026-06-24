package ru.ivanbulgakov.booking;

import org.junit.jupiter.api.BeforeAll;

import static ru.ivanbulgakov.booking.util.RestAssuredSpec.setupRestAssured;

public class BaseApiTest {
    @BeforeAll
    static void setUp() {
        setupRestAssured();
    }
}
