package factorymethod;
public class LectureCourse implements Course{
    private final String subject;
    public LectureCourse(String subject){this.subject=subject;}
    @Override
    public String title(){return subject+" (lecture)";}
    @Override
    public int credits(){return 5;}
    @Override
    public String weeklyPlan(){return "2 x 50 min lecture in a big hall + reading list, assessed by a final exam";}}