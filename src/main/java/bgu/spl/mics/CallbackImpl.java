package bgu.spl.mics;
package bgu.spl.mics.application.messages;
import bgu.spl.mics.Event;
public class CallbackImpl<T> implements Callback<T> {

    @Override
    public void call(T c) {
        switch (c.getClass()){
            case AttackEvent:

        }

    }
}
