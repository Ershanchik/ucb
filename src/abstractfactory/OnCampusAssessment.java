package abstractfactory;
public class OnCampusAssessment implements Assessment{
    @Override
    public String rules(){return "Written exam in the exam hall, 2 hours, closed book, invigilated";}
    @Override
    public int passMark(){return 50;}
}
