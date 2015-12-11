// Exception class from Dale/Joyce/Weems textbook

public class StackUnderflowException extends RuntimeException
{
    public StackUnderflowException()
    {
        super();
    }

    public StackUnderflowException(String message)
    {
        super(message);
    }
}