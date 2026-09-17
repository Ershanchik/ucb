package factorymethod;
public class LabCourseCreator extends CourseCreator{
    private final int seats;
    public LabCourseCreator(int seats){this.seats=seats;}
    @Override
    protected Course createCourse(String subject){return new LabCourse(subject,seats);}}