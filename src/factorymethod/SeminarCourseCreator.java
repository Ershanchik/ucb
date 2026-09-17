package factorymethod;
public class SeminarCourseCreator extends CourseCreator{
    @Override
    protected Course createCourse(String subject){return new SeminarCourse(subject);}}