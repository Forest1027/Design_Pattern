package section01.designprinciples.isp;


/**
 * ISP: Interface Segregation Principle
 * Recommendation on how to split interfaces into smaller interface
 * Put minimum methods into an interface, so user don't need to implement a method that they don't need at all at any stage.
 */
public class Demo {

}

class Document {

}

interface Machine {
    void print(Document document);
    void fax(Document document);
    void scan(Document document);
}

class MultiFunctionPrinter implements Machine{
    @Override
    public void print(Document document) {

    }

    @Override
    public void fax(Document document) {

    }

    @Override
    public void scan(Document document) {

    }
}

class OldFashionedPrinter implements Machine{
    @Override
    public void print(Document document) {

    }

    @Override
    public void fax(Document document) {

    }

    @Override
    public void scan(Document document) {

    }
}

interface Printer {
    void print(Document document);
}

interface Scanner {
    void scan(Document d);
}

// YAGNI = You ain't going to need it
// You don't need to implement the method if you are not going to need it

class JustAPrinter implements Printer {
    @Override
    public void print(Document document) {

    }
}

class Photocopier implements Printer, Scanner {
    @Override
    public void print(Document document) {

    }

    @Override
    public void scan(Document d) {

    }
}

interface MultiFunctionDevice extends Printer, Scanner{

}

class MultiFunctionMachine implements MultiFunctionDevice {

    // decoration
    private Printer printer;
    private Scanner scanner;

    public MultiFunctionMachine(Printer printer, Scanner scanner) {
        this.printer = printer;
        this.scanner = scanner;
    }

    @Override
    public void print(Document document) {
        printer.print(document);
    }

    @Override
    public void scan(Document document) {
        scanner.scan(document);
    }
}