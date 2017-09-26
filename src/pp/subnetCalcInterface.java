package pp;

//Author: ^-^ Veerle ^-^
//Layer between GUI and engine, translates between the user and the engine code
public class subnetCalcInterface extends subnetCalcEngine
{
	
	public void subnetCalcEngine ()
	{
	}
	
	public void setAddresses (String[] strIP, String[] strSub)
	{
		
		strIPAddress = format(strIP, true);
		strSubAddress = format(strSub, true);
		strNetworkAddress = format(strNetworkAddress, true);
		strHostAddress = format(strHostAddress, true);
		
	}
	
	public String[] getIPAddress ()
	{
		return format(strIPAddress, false);		
	}
	
	public String[] getSubAddress ()
	{
		return format(strSubAddress, false);
	}
	
	public String[] getNetworkAddress ()
	{
		return format(strNetworkAddress, false);
	}
	
	public String[] getHostAddress ()
	{
		return format(strHostAddress, false);
	}
	
	public boolean calculate ()
	{
		return performCalculation();
	}				
	
	public void setUsingDec (boolean usingDec)
	{
		this.usingDec = usingDec;
	}
	
	public boolean isUsingDec ()
	{
		return usingDec;
	}
	
}
