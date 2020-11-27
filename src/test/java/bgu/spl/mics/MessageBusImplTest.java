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
    MicroService testHanMS;
    MicroService testC3MS;

    @BeforeEach
    void setUp() {
        messageBusToCheck=MessageBusImpl.getInstance();
        testHanMS=new HanSoloMicroservice();
        testC3MS=new C3POMicroservice();
    }

    @AfterEach
    void tearDown() {
    }


    @Test
    void complete() {
        Event Att=new AttackEvent();
        Future<String> mission=messageBusToCheck.sendEvent(Att);
        String result="Finished";
        messageBusToCheck.complete(Att,result);
        assertEquals(result,mission.get());
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
            assertEquals(messageBusToCheck.awaitMessage(testHanMS),broad);
        } catch (InterruptedException e) {
            fail();
        }


    }

    @Test
    void sendEvent() {
        AttackEvent Att=new AttackEvent();
        messageBusToCheck.subscribeEvent(Att.getClass(),testHanMS);
        messageBusToCheck.sendEvent(Att);
       testC3MS.sendEvent(Att);
        try {
            assertEquals(messageBusToCheck.awaitMessage(testHanMS),Att);
        } catch (InterruptedException e) {
            fail();
        }
    }


    @Test
    void awaitMessage() {
        AttackEvent Att=new AttackEvent();
        messageBusToCheck.subscribeEvent(Att.getClass(),testHanMS);
        messageBusToCheck.sendEvent(Att);
        //Checking if fetching from Q
        try {
            assertEquals(messageBusToCheck.awaitMessage(testHanMS),Att);

        } catch (InterruptedException e) {
           fail();
        }

        AttackEvent Att1=new AttackEvent();
        try {
            Message msg=messageBusToCheck.awaitMessage(testHanMS);
            messageBusToCheck.sendEvent(Att1);
            assertEquals(msg,Att1);
        } catch (InterruptedException e) {
            fail();
        }

    }
}