package abstractfactory;
public interface CoursePackageFactory{
    String modeName();
    Syllabus createSyllabus();
    Assessment createAssessment();
    ScheduleSlot createScheduleSlot();}