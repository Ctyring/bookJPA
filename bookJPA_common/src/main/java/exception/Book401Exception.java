package exception;

public class Book401Exception extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public Book401Exception(String message){
		super(message);
	}

	public Book401Exception(Throwable cause)
	{
		super(cause);
	}

	public Book401Exception(String message, Throwable cause)
	{
		super(message,cause);
	}
}
