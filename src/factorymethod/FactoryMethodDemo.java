package factorymethod;
import java.util.List;
public class FactoryMethodDemo{
    public static void main(String[]args){
        List<CourseCreator>curriculum=List.of(
                new LectureCourseCreator(),
                new LabCourseCreator(24),
                new SeminarCourseCreator());
        for (CourseCreator creator:curriculum){System.out.println(creator.openCourse("Design Patterns"));}
    }
}