package yio.io.sifaapp.utils;

/**
 * Created by STARK on 15/06/2016.
 */
public class GreenRobotEventBus implements  EventBus {

    de.greenrobot.event.EventBus bus;

    private static class Singleton{
        private  static final GreenRobotEventBus Instance = new GreenRobotEventBus();
    }

    public static GreenRobotEventBus getInstance(){
        return  Singleton.Instance;
    }

    public GreenRobotEventBus() {
        this.bus = de.greenrobot.event.EventBus.getDefault();
    }

    @Override
    public void register(Object subscriber) {
        bus.register(subscriber);
    }

    @Override
    public void unregister(Object subscriber) {
        bus.unregister(subscriber);
    }

    @Override
    public void post(Object event) {
        bus.post(event);
    }
}
