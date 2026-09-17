package abstractfactory;
public class AbstractFactoryDemo{
    public static void main(String[]args){
        String mode=args.length >0?args[0]:"online";
        CoursePackageFactory factory=pickFactory(mode);
        CourseBuilder builder=new CourseBuilder(factory);
        System.out.println(builder.assemble("Design Patterns"));}
    private static CoursePackageFactory pickFactory(String mode){
        return switch (mode.toLowerCase()){
            case "online"->new OnlineCourseFactory();
            case "campus","oncampus"->new OnCampusCourseFactory();
            default -> throw new IllegalArgumentException("Unknown delivery mode: "+mode);};
    }
}