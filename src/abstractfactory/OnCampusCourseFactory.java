package abstractfactory;
public class OnCampusCourseFactory implements CoursePackageFactory{
    @Override
    public String modeName() {return "ON-CAMPUS";}
    @Override
    public Syllabus createSyllabus(){return new OnCampusSyllabus();}
    @Override
    public Assessment createAssessment(){return new OnCampusAssessment();}
    @Override
    public ScheduleSlot createScheduleSlot(){return new OnCampusScheduleSlot();}}