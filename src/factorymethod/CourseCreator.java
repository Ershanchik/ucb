package factorymethod;
public abstract class CourseCreator{
    protected abstract Course createCourse(String subject);
    public String openCourse(String subject){
        Course course=createCourse(subject);
        return """
               === Course opened ===
               Title   : %s
               Credits : %d
               Weekly  : %s
               Workload: ~%d hours of self-study
               """.formatted(course.title(),
                             course.credits(),
                             course.weeklyPlan(),
                             course.credits()*25);
    }
}