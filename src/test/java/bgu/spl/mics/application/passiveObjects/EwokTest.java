package bgu.spl.mics.application.passiveObjects;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EwokTest {

    private Ewok EwokToTest;


    @BeforeEach
    void setUp() {
        EwokToTest=new Ewok() ;
    }


    @AfterEach
    void tearDown() {
    }

    /**
     * @PRE: available=false
     * @POST: available=true
     */
    @Test
    void acquire() {
        assertFalse(EwokToTest.available);
        EwokToTest.acquire();
        assertTrue(EwokToTest.available);
    }

    /**
     * @PRE: available=true
     * @POST: available=false
     */
    @Test
    void release() {
        assertTrue(EwokToTest.available);
        EwokToTest.release();
        assertFalse(EwokToTest.available);
    }
}