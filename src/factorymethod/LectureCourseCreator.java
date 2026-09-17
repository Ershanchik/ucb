package factorymethod;
public class LectureCourseCreator extends CourseCreator{
    @Override
    protected Course createCourse(String subject){return new LectureCourse(subject);}}