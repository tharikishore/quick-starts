/**
 * 
 * @author Max Gabel
 */

/**
 *For GUI – check if Password and re-typed 
 *Password are identical (class UnmatchedExcpetion)
 *Message – The passwords do not match

 */
public class UnmatchedExcpetion extends Exception
{
	    public UnmatchedExcpetion(){}
		
		public UnmatchedExcpetion(String message)
		{
			super(message);
		}
}
