package abstractfactory;
public class OnCampusScheduleSlot implements ScheduleSlot{
    @Override
    public String when(){return "Tuesday 10:00-11:40, fixed by the timetable office";}
    @Override
    public String where(){return "Building C, room 204";}}