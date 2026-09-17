package abstractfactory;
public class OnlineScheduleSlot implements ScheduleSlot{
    @Override
    public String when(){return "Asynchronous: any time before the weekly Sunday deadline";}
    @Override
    public String where(){return "LMS + Zoom link";}}