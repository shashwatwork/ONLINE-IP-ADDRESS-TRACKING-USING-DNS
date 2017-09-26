package pp;

//Author: ^-^ Veerle ^-^
//Does all the calculations
public class subnetCalcEngine
{
	
	protected String[] strNetworkAddress = {"0","0","0","0"};
	protected String[] strHostAddress = {"0","0","0","0"};
	protected String[] strIPAddress = {"0","0","0","0"};
	protected String[] strSubAddress = {"0","0","0","0"};
	protected boolean usingDec = true;
	
	public void subnetCalcEngine ()
	{
	}
	
	protected String[] format (String[] address, boolean setAddress)
	{
			
		if (setAddress)
		{

			if (!usingDec)
			{
				
				for (int section = 0; section < 4; section++)
				{
					address[section] = convertBinToDec(address[section]);
				}
				
			}
			
		}
		else
		{
			
			if (!usingDec)
			{
				
				for (int section = 0; section < 4; section++)
				{
					address[section] = convertDecToBin(address[section]);
				}
				
			}
			
		}
		
		return address;
		
	}			
	
	private boolean checkSubAddress () //Checks the subnet address is legal
	{
		
		boolean subnetEndReached = false;
		int decNum = 0;
		
		for (int section = 0; section < 4; section++)
		{
			
			decNum = Integer.parseInt(strSubAddress[section]);
			
			if (subnetEndReached && decNum != 0)
			{
				return false;
			}
			else
			{
	
				switch (decNum)
				{
					
					case 0:
					case 128:
					case 192:
					case 224:
					case 240:
					case 248:
					case 252:
					case 254:	subnetEndReached = true;
								break;
					case 255: 	break;
					default: return false;
					
				}
				
			}
			
		}
		
		return true;		
		
	}
	
	protected boolean performCalculation () //Calculates the network and host addresses
	{
		
		if (!checkSubAddress())
		{
			return false;
		}
		else
		{		
			
			String[] strIP = new String[4];
			String[] strSub = new String[4];
			
			for (int section = 0; section < 4; section++)
			{
				
				strIP[section] = convertDecToBin(strIPAddress[section]);
				strSub[section] = convertDecToBin(strSubAddress[section]);
				
			}
			
			strIP = addPadding(strIP);
			strSub = addPadding(strSub);
			
			String[] strNetwork = new String[4];
			String[] strHost = new String[4];
			
			boolean endOfNetwork = false;
			
			for (int section = 0; section < 4; section++)
			{
				
				strNetwork[section] = "";
				strHost[section] = "";
			
				for (int bit = 0; bit < 8; bit++)
				{
					
					if (endOfNetwork)
					{
						
						strNetwork[section] += "0";
						strHost[section] += strIP[section].charAt(bit);
						
					}
					else
					{
					
						if (strSub[section].charAt(bit) == '1')
						{
							
							strNetwork[section] += strIP[section].charAt(bit);
							strHost[section] += "0";
							
						}
						else
						{
							
							endOfNetwork = true;
							strNetwork[section] += "0";
							strHost[section] += strIP[section].charAt(bit);
							
						}
						
					}					
					
				}

			}
			
			for (int section = 0; section < 4; section++)
			{
				
				strNetworkAddress[section] = convertBinToDec(strNetwork[section]);
				strHostAddress[section] = convertBinToDec(strHost[section]);
				
			}

			return true;
			
		}
		
	}
	
	private String[] addPadding (String[] strCurrentAddress)
	{
		
		for (int section = 0; section < 4; section++)
		{			
			strCurrentAddress[section] = addPadding2(strCurrentAddress[section]);			
		}
		
		return strCurrentAddress;
		
	}
	
	private String addPadding2 (String strCurrentAddressSection)
	{
		
		for (int padding = (8 - strCurrentAddressSection.length()); padding > 0; padding--)
		{
			strCurrentAddressSection = "0" + strCurrentAddressSection;
		}
		
		return strCurrentAddressSection;
		
	}
	
	private String convertDecToBin (String strDec)
	{
		
		int decNum = Integer.parseInt(strDec), length = 0;
		String strBinNum = "", answer = "";
		
		while (decNum != 0)
		{
			
			if (decNum > 1)
			{
				
				strBinNum += Integer.toString(decNum % 2);
				decNum /= 2;
				
			}
			else
			{
				
				strBinNum += Integer.toString(decNum);
				decNum = 0;
				
			}
			
		}

		length = strBinNum.length();		

		for (int padding = (8 - length); padding > 0; padding--)
		{
			answer += "0";
		}
		
		for (int i = length; i > 0; i--)
		{
			answer += strBinNum.charAt(i - 1);
		}
		
		return answer;
		
	}
	
	private String convertBinToDec (String strBin)
	{		
		
		for (int padding = (8 - strBin.length()); padding > 0; padding--)
		{
			strBin = "0" + strBin;
		}
		
		int counter = 1, decNum = 0;
		
		for (int bit = 7; bit >= 0; bit--)
		{
			
			if (strBin.charAt(bit) == '1')
			{
				decNum += counter;
			}

			counter *= 2;
			
		}
		
		return "" + decNum;
		
	}		
	
}
