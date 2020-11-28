package bgu.spl.mics;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterAll;

import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeUnit;


import static org.junit.jupiter.api.Assertions.*;

class FutureTest {
    private  Future<String> futureToTest;
    ///Check for omri
    @BeforeEach
    void setUp() {
        futureToTest=new Future<>();
    }

    @AfterEach
    void tearDown() {
    }

    /**
     * @PRE:
     * @POST:  Creaete object of futrue with isDone=false, and Result=null;
     */
    @Test
    void Future()
    {
        assertFalse(futureToTest.isDone());
        assertNull(futureToTest.get());
    }


    /**
     * Check if we get the same str as we inserted when we resolved the task
     */
    @Test
    void get() {
        String str="Result";
        futureToTest.resolve(str);
        assertEquals(futureToTest.get(),str);
    }


    /**
     * @PRE: not resolved
     * @POST: isDone=true,Result= the result we inserted
     */
    @Test
    void resolve() {
        assertFalse(futureToTest.isDone());
        String str = "Result";
        futureToTest.resolve(str);
        assertTrue(futureToTest.isDone());
        assertEquals(futureToTest.get(),str);
    }


    /**
     * @PRE: isDone =false
     * @POST: isDone=true
     */
    @Test
    void isDone() {
        assertFalse(futureToTest.isDone());
         futureToTest.resolve("finished");
        assertTrue(futureToTest.isDone());

    }
    /**
     * Check if we get the same str as we inserted when we resolved the task
     */
    @Test
    void testGet() {
        String str = "Result";
        assertFalse(futureToTest.isDone());
        futureToTest.get(300,TimeUnit.MILLISECONDS);
        assertFalse(futureToTest.isDone());
        futureToTest.resolve(str);
        assertEquals(futureToTest.get(300,TimeUnit.MILLISECONDS),str);

    }
}
