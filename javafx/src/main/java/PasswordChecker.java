import java.util.ArrayList;

public class PasswordChecker implements PasswordCheckerInterface
{   
	boolean isValidPasswordBool = false;
    ArrayList<String> returnValidPasswords;
    char [] upperABC = {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
    char [] lowerABC = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
    char [] numbersChar = {'0','1','2','3','4','5','6','7','8','9'};
    int accumulator1 = 0;
    int accumulator2 = 0;
    int accumulator3 = 0;
    int accumulator4 = 0;
    int [] capsTally;
    int [] lowerTally;
    int [] numberTally;
    
    
			public boolean isValidPassword (String passwordString) throws LengthException, NoDigitException, NoUpperAlphaException, NoLowerAlphaException, InvalidSequenceException
			{ 
				
				
						if(passwordString.length() >= 8)
						{   
						   //----
							capsTally = new int[passwordString.length()];
							for(int a = 0; a < passwordString.length(); a++)
							{
								capsTally [a] = 0;
							}	
							
						    lowerTally = new int[passwordString.length()];
						    for(int b = 0; b < passwordString.length(); b++)
							{
								capsTally [b] = 0;
							}
						    
						    numberTally = new int [passwordString.length()];
						    for(int c = 0; c < passwordString.length(); c++)
							{
								capsTally [c] = 0;
							}
						   //------  
								for(int j =0; j <passwordString.length();j++ )
								{
									for(char temp1 : upperABC)
									{
										if(passwordString.charAt(j)== temp1)
										{
											capsTally [j] = 1;
										}
									}
								}
								for(int k =0; k <passwordString.length();k++ )
								{
									for(char temp2 : lowerABC)
									{
										if(passwordString.charAt(k)== temp2)
										{
											lowerTally [k] = 1;
										}
									}
								}
								for(int m =0; m <passwordString.length();m++ )
								{
									for(char temp3 : numbersChar)
									{
										if(passwordString.charAt(m)== temp3)
										{
											numberTally [m] = 1;
										}
									}
								}
								//--------
								for(int accumulatorFor1 :capsTally )
								{
									accumulator1 += accumulatorFor1;
								}
								for(int accumulatorFor2 :lowerTally )
								{
									accumulator2 += accumulatorFor2;
								}
								for(int accumulatorFor3 :numberTally )
								{
									accumulator3 += accumulatorFor3;
								}
								//-------
										if (accumulator1 ==0)
										{
											throw new NoUpperAlphaException("NoUpperAlphaException: The password must contain at least one uppercase alphabetic character.");
											//Throw No Caps exception
										}
										if (accumulator2 ==0)
										{
											throw new NoLowerAlphaException("NoLowerAlphaException: The password must contain at least one lowercase alphabetic character.");
											//Throw No lower exception
										}
										if (accumulator3 ==0)
										{
											throw new NoDigitException("NoDigitException: The password must contain at least one digit.");
											//Throw No digit exception
										}
								//------
								for(int x =0;x <passwordString.length();x++)
								{   
									accumulator4 = 0;
									
									    for(int y =x+1;y <passwordString.length();y++)
										{
											if(passwordString.charAt(x) ==passwordString.charAt(y) )
											{
												accumulator4 += 1;
											}
										}	
										if(accumulator4 >= 2)
										{
											throw new InvalidSequenceException("InvalidSequenceException: The password cannot contain more than two of the same character in sequence.");
											//return cannot contain more than two of the same char exception.
										}
								}				
						}	
						else
						{
							throw new LengthException("LengthException: The password must be at least 8 characters long.");
							//throw must be greater 8 or greater exception.
						}		
					
					
				
				
				//might hhave to put a return in each throw area!!!!COMEBACK TO THIS ONCE GUI IS UP.
						isValidPasswordBool = true;
				
				return isValidPasswordBool;
			}
			
			public ArrayList<String> validPasswords (ArrayList<String> passwords)
			{      
				ArrayList<String> forLoopValPassW = new ArrayList<String>();
				int iteratorKeeper = 0;
				
				do{
					String tempPasswordSingle = "";
					
				        for(int tempIterator = iteratorKeeper;tempIterator < passwords.size(); tempIterator++)
						{
						   tempPasswordSingle = passwords.get(tempIterator);
							
							if(tempPasswordSingle.length() >= 8)
							{   
							   //----
								capsTally = new int[tempPasswordSingle.length()];
									
								    for(int a = 0; a < tempPasswordSingle.length(); a++)
									{
										capsTally [a] = 0;
									}	
								
							    lowerTally = new int[tempPasswordSingle.length()];
							    
								    for(int b = 0; b < tempPasswordSingle.length(); b++)
									{
										capsTally [b] = 0;
									}
							    
							    numberTally = new int [tempPasswordSingle.length()];
							   
								    for(int c = 0; c < tempPasswordSingle.length(); c++)
									{
										capsTally [c] = 0;
									}
							   //------  
									for(int j =0; j <tempPasswordSingle.length();j++ )
									{
										for(char temp1 : upperABC)
										{
											if(tempPasswordSingle.charAt(j)== temp1)
											{
												capsTally [j] = 1;
											}
										}
									}
									for(int k =0; k <tempPasswordSingle.length();k++ )
									{
										for(char temp2 : lowerABC)
										{
											if(tempPasswordSingle.charAt(k)== temp2)
											{
												lowerTally [k] = 1;
											}
										}
									}
									for(int m =0; m <tempPasswordSingle.length();m++ )
									{
										for(char temp3 : numbersChar)
										{
											if(tempPasswordSingle.charAt(m)== temp3)
											{
												numberTally [m] = 1;
											}
										}
									}
									//--------
									for(int accumulatorFor1 :capsTally )
									{
										accumulator1 += accumulatorFor1;
									}
									for(int accumulatorFor2 :lowerTally )
									{
										accumulator2 += accumulatorFor2;
									}
									for(int accumulatorFor3 :numberTally )
									{
										accumulator3 += accumulatorFor3;
									}
									//-------
											if (accumulator1 ==0)
											{
												
												forLoopValPassW.add((tempPasswordSingle+" "+"The password must contain at least one uppercase alphabetic character.\n"));
												break;
												//Throw No Caps exception
											}
											if (accumulator2 ==0)
											{
												forLoopValPassW.add((tempPasswordSingle+" "+"The password must contain at least one lowercase alphabetic character.\n"));
												break;
												//Throw No lower exception
											}
											if (accumulator3 ==0)
											{
												
												forLoopValPassW.add((tempPasswordSingle+" "+"The password must contain at least one digit.\n"));
												break;
												//Throw No digit exception
											}
									//------
									for(int x =0;x <tempPasswordSingle.length();x++)
									{   accumulator4 = 0;
										for(int y =x+1;y <tempPasswordSingle.length();y++)
										{
											if(tempPasswordSingle.charAt(x) ==tempPasswordSingle.charAt(y) )
											{
												accumulator4 += 1;
											}
										}	
										if(accumulator4 >= 2)
										{
											forLoopValPassW.add((tempPasswordSingle+" "+"The password cannot contain more than two of the same character in sequence.\n"));
											break;
											//return cannot contain more than two of the same char exception.
										}
									}				
							}	
							else
							{
								forLoopValPassW.add( (tempPasswordSingle+" "+"The password must be at least 8 characters long\n"));
								break;
								//throw must be greater 8 or greater exception.
							}	
						}
				
				  iteratorKeeper += 1;
				
				}while(passwords.size() > iteratorKeeper);
				
			  return forLoopValPassW;
			}
}
