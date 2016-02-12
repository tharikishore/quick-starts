import java.util.ArrayList;


public interface PasswordCheckerInterface 
{

	public boolean isValidPassword (String passwordString) throws LengthException, NoDigitException, NoUpperAlphaException, NoLowerAlphaException, InvalidSequenceException;

	public ArrayList<String> validPasswords (ArrayList<String> passwords);

}