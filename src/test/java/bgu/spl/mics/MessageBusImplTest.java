package bgu.spl.mics;

import bgu.spl.mics.application.messages.AttackEvent;
import bgu.spl.mics.application.passiveObjects.Attack;
import bgu.spl.mics.application.services.C3POMicroservice;
import bgu.spl.mics.application.services.HanSoloMicroservice;
import bgu.spl.mics.application.services.LeiaMicroservice;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MessageBusImplTest {
    MessageBus messageBusToCheck;

    @BeforeEach
    void setUp() {
        messageBusToCheck=MessageBusImpl.getInstance();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void subscribeEvent() {

    }

    @Test
    void subscribeBroadcast() {
    }

    @Test
    void complete() {
    }

    @Test
    void sendBroadcast() {
    }

    @Test
    void sendEvent() {
    }

    @Test
    void register() {
        MicroService MSTest=new C3POMicroservice();
        MicroService MSTest1=new HanSoloMicroservice();
        messageBusToCheck.register(MSTest);
        Event<Boolean> Att= new AttackEvent();
        MSTest1.subscribeEvent(Att,);
        MSTest.sendEvent(Att);

    }

    @Test
    void unregister() {
    }

    @Test
    void awaitMessage() {
    }
}