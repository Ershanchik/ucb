package abstractfactory;
public class CourseBuilder{
    private final CoursePackageFactory factory;
    public CourseBuilder(CoursePackageFactory factory){this.factory=factory;}
    public String assemble(String subject){
        Syllabus syllabus=factory.createSyllabus();
        Assessment assessment=factory.createAssessment();
        ScheduleSlot slot=factory.createScheduleSlot();
        return """
               === %s course package: %s ===
               Syllabus  : %s
               Assessment: %s (pass mark %d%%)
               Time      : %s
               Place     : %s
               """.formatted(factory.modeName(),
                             subject,
                             syllabus.outline(subject),
                             assessment.rules(),
                             assessment.passMark(),
                             slot.when(),
                             slot.where());
    }
}