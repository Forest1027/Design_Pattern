package section03.structural.decorator.exercise;

public class Demo {
}

class Bird
{
    public int age;

    public String fly()
    {
        return age < 10 ? "flying" : "too old";
    }
}

class Lizard
{
    public int age;

    public String crawl()
    {
        return (age > 1) ? "crawling" : "too young";
    }
}

class Dragon
{
    private Bird bird;
    private Lizard lizard;

    public Dragon() {
        bird = new Bird();
        lizard = new Lizard();
    }

    private int age;
    public void setAge(int age)
    {
        bird.age = age;
        lizard.age = age;
    }
    public String fly()
    {
        return bird.fly();
    }
    public String crawl()
    {
        return lizard.crawl();
    }
}
