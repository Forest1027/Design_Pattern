package section03.structural.adapter.exercise;

public class Demo {
}

class Square
{
    public int side;

    public Square(int side)
    {
        this.side = side;
    }
}

interface Rectangle
{
    int getWidth();
    int getHeight();

    default int getArea()
    {
        return getWidth() * getHeight();
    }
}

class SquareToRectangleAdapter implements Rectangle
{

    private int width;
    private int height;

    public SquareToRectangleAdapter(Square square)
    {
        int side = square.side;
        width = side;
        height = side;

    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }
}
