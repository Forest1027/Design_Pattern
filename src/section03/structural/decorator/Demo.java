package section03.structural.decorator;

public class Demo {
    public static void main(String[] args) {
        MagicString string = new MagicString("Test");
        System.out.println(string.getNumberOfVowels());
    }
}

class MagicString {
    public String string;

    public MagicString(String string) {
        this.string = string;
    }

    public long getNumberOfVowels() {
        return string.chars().mapToObj(c -> (char) c).filter(c -> "aeiou".contains(c.toString())).count();
    }

    public int length() {
        return string.length();
    }

    public boolean isEmpty() {
        return string.isEmpty();
    }

    public char charAt(int index) {
        return string.charAt(index);
    }
}
