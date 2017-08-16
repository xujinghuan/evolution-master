package environment;

public class ThingNotFoundException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 103527737442643653L;
	
	String string;
	public ThingNotFoundException(String name) 
	{
		string=name;
	}
}
