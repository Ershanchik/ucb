package factorymethod;
public class SeminarCourse implements Course{
    private final String subject;
    public SeminarCourse(String subject){this.subject=subject;}
    @Override
    public String title(){return subject+" (seminar)";}
    @Override
    public int credits(){return 2;}
    @Override
    public String weeklyPlan(){return "1 x 90 min discussion for max 15 students, graded by an essay and participation";}}