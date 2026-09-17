package abstractfactory;
public class OnlineCourseFactory implements CoursePackageFactory{
    @Override
    public String modeName(){return "ONLINE";}
    @Override
    public Syllabus createSyllabus(){return new OnlineSyllabus();}
    @Override
    public Assessment createAssessment(){return new OnlineAssessment();}
    @Override
    public ScheduleSlot createScheduleSlot(){return new OnlineScheduleSlot();}}