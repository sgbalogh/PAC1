// Exception class from Dale/Joyce/Weems textbook

public class StackOverflowException extends Exception
{
    public StackOverflowException()
    {
        super();
    }

    public StackOverflowException(String message)
    {
        super(message);
    }
}