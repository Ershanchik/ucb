package abstractfactory;
public class OnlineAssessment implements Assessment{
    @Override
    public String rules(){return "Auto-graded LMS quiz, 3 attempts, open book, proctored by webcam";}
    @Override
    public int passMark(){return 60;}}