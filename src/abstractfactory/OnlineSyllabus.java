package abstractfactory;
public class OnlineSyllabus implements Syllabus{
    @Override
    public String outline(String subject){return subject+": 12 recorded modules, each ending with a self-check quiz";}}