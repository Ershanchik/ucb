package factorymethod;
public class LabCourse implements Course{
    private final String subject;
    private final int seats;
    public LabCourse(String subject, int seats){
        this.subject=subject;
        this.seats=seats;}
    @Override
    public String title(){return subject+" (lab, "+seats+" seats)";}
    @Override
    public int credits(){return 3;}
    @Override
    public String weeklyPlan(){return "1 x 100 min lab at a workstation, graded by weekly code submissions";}}