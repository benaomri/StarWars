package bgu.spl.mics;

import bgu.spl.mics.application.messages.AttackEvent;
import bgu.spl.mics.application.passiveObjects.Attack;
import bgu.spl.mics.application.services.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MessageBusImplTest {
    /**
     * Creating variables we use in all the tests
     * Before each test it will be initial
     */
    MessageBus messageBusToCheck;
    MicroService testMS1;
    MicroService testMS2;

    @BeforeEach
    void setUp() {
        messageBusToCheck=MessageBusImpl.getInstance();

        testMS1=new HanSoloMicroservice();
        testMS2=new C3POMicroservice();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void subscribeBroadcast() {
    }

    @Test
    void complete() {
    }

    @Test
    void sendBroadcast() {

        MicroService testBroad=new MicroService("Broad") {
            @Override
            protected void initialize() {

            }
        };
        messageBusToCheck.subscribeBroadcast(Broadcast.class,testBroad);
        Broadcast broad=new Broadcast() {};
        testBroad.sendBroadcast(broad);

        try {
            assertEquals(messageBusToCheck.awaitMessage(testMS1),broad);
        } catch (InterruptedException e) {
            assertFalse(true);
        }


    }

    @Test
    void sendEvent() {
        AttackEvent Att=new AttackEvent();
        messageBusToCheck.subscribeEvent(Att.getClass(),testMS1);
        messageBusToCheck.sendEvent(Att);
       testMS2.sendEvent(Att);
        try {
            assertEquals(messageBusToCheck.awaitMessage(testMS1),Att);
        } catch (InterruptedException e) {
            assertFalse(true);
        }
    }


    @Test
    void awaitMessage() {
    }
}