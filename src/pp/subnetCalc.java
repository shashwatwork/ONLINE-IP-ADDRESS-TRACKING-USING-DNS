package pp;
//Author: ^-^ Veerle ^-^
//The GUI that the user interacts with
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class subnetCalc extends JPanel implements ActionListener, KeyListener, FocusListener
{
	
	private JButton cmdCalculate;
	private JTextField[] txtIP, txtSub;
	private JLabel lblBase, lblCalculate, lblIP, lblSub, lblNetwork, lblHost;
	private JRadioButton radDec, radBin;
	private ButtonGroup grpBase;
		
	private JPanel panAnswerArea, panNorthArea, panBase, panAddressGrid, panIP, panSub, panButton;
	private final Color clrBackground = new Color(75,141,221);
	private final Color clrForeground = new Color(255,255,255);

	private subnetCalcInterface engine;
	
	public void main()
	{
		
		JFrame.setDefaultLookAndFeelDecorated(true);

        JFrame frame = new JFrame("SubNet Calc V1"); //Title
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JComponent paneMain = new subnetCalc();
        paneMain.setOpaque(true);
		paneMain.setPreferredSize(new Dimension(420, 210));
        frame.setContentPane(paneMain);
                
        frame.pack();
        frame.setVisible(true);
        
    }
	
	public subnetCalc ()
	{
		
		super.setLayout(new BorderLayout());
		engine = new subnetCalcInterface(); //The interface links the GUI to the engine
		
		cmdCalculate = new JButton("Calculate");
		cmdCalculate.addActionListener(this);
		
		txtIP = new JTextField[4];
		txtSub = new JTextField[4];
		
		lblIP = new JLabel("IP address: ");
		lblSub = new JLabel("Subnet mask: ");
		
		panIP = new JPanel();
		panSub = new JPanel();
		
		panIP.add(lblIP);
		panSub.add(lblSub);
		
		for (int i = 0; i < 4; i++)
		{
			
			txtIP[i] = new JTextField("0", 2);
			txtSub[i] = new JTextField("0", 2);
			
			txtIP[i].addKeyListener(this);
			txtSub[i].addKeyListener(this);
			//Each time you select a new txt box, all the contents will be selected
			txtIP[i].addFocusListener(this);
			txtSub[i].addFocusListener(this);
			
			panIP.add(txtIP[i]);
			panSub.add(txtSub[i]);
			
			if (i != 3) //If not last section, put a decimal place separator
			{
				
				panIP.add(makeLblDot());
				panSub.add(makeLblDot());
				
			}
			
		}
		
		lblBase = new JLabel("Base System: ", JLabel.RIGHT);
		lblCalculate = new JLabel("Calculate Network and Host parts: ", JLabel.RIGHT);
		lblNetwork = new JLabel("Network address: ", JLabel.CENTER);
		lblHost = new JLabel("Host address: ", JLabel.CENTER);
		
		radDec = new JRadioButton("Decimal", true);
		radBin = new JRadioButton("Binary", false);
		
		radDec.addActionListener(this);
		radBin.addActionListener(this);
		
		grpBase = new ButtonGroup();
		grpBase.add(radDec);
		grpBase.add(radBin);
		
		panAnswerArea = new JPanel(new BorderLayout()); //Stores the network and host addresses
		panNorthArea = new JPanel(new BorderLayout()); //North has the base radio buttons, the south has the IP/Subnet
		panBase = new JPanel(); //Stores the radio buttons
		panAddressGrid = new JPanel(new GridLayout(3,1)); //Stores the IP, subnet address and the calculate button
		panButton = new JPanel(); //Stores the calculate button
		
		this.add(panAnswerArea, BorderLayout.SOUTH);
			panAnswerArea.add(lblNetwork, BorderLayout.NORTH);
			panAnswerArea.add(lblHost, BorderLayout.SOUTH);
		this.add(panNorthArea, BorderLayout.NORTH);
			panNorthArea.add(panBase, BorderLayout.NORTH);
				panBase.add(lblBase);
				panBase.add(radDec);
				panBase.add(radBin);
			panNorthArea.add(panAddressGrid, BorderLayout.SOUTH);
				panAddressGrid.add(panIP);
				panAddressGrid.add(panSub);
				panAddressGrid.add(panButton);
					panButton.add(lblCalculate);
					panButton.add(cmdCalculate);
					
		this.setBackground(clrBackground);
		panAnswerArea.setBackground(clrBackground);
		panNorthArea.setBackground(clrBackground);
		panBase.setBackground(clrBackground);
		panAddressGrid.setBackground(clrBackground);
		panIP.setBackground(clrBackground);
		panSub.setBackground(clrBackground);
		panButton.setBackground(clrBackground);
		radDec.setBackground(clrBackground);
		radBin.setBackground(clrBackground);
		
		lblNetwork.setForeground(clrForeground);
		lblHost.setForeground(clrForeground);
		lblBase.setForeground(clrForeground);
		lblCalculate.setForeground(clrForeground);
		lblIP.setForeground(clrForeground);
		lblSub.setForeground(clrForeground);
		
	}
	
	private JLabel makeLblDot () //Creates a label decimal point, much better then creating global labels
	{
		
		JLabel lblDot = new JLabel(".");
		lblDot.setForeground(clrForeground);
		return lblDot;
		
	}
	
	public void actionPerformed (ActionEvent e)
	{		
		
		String[] strIP = new String[4], strSub = new String[4], strNetwork = new String[4], strHost = new String[4];
		
		lblCalculate.setText("Calculate Network and Host parts: "); //Resets the message if a subnet mask error occured
		
		for (int section = 0; section < 4; section++)
		{
			
			strIP[section] = "";
			strSub[section] = "";
			
			if (txtIP[section].getText().equals(""))
			{
				txtIP[section].setText("0");
			}
			
			if (txtSub[section].getText().equals(""))
			{
				txtSub[section].setText("0");
			}
			
			strIP[section] = txtIP[section].getText();
			strSub[section] = txtSub[section].getText();
			
		}
		
		engine.setAddresses(strIP, strSub); //Sends the new IP and sub address

		if (e.getSource() == radDec || e.getSource() == radBin)
		{
			engine.setUsingDec(radDec.isSelected()); //will just change the base system, not calculate a new network and host
		}
		else
		{
			
			if (!engine.calculate()) //Errors occur if the addresses sent are invalid
			{
				lblCalculate.setText("Error, ilegal subnet mask");
			}
			
		}
		
		strIP = engine.getIPAddress();
		strSub = engine.getSubAddress();
		strNetwork = engine.getNetworkAddress();
		strHost = engine.getHostAddress();
		
		lblNetwork.setText("Network address: ");
		lblHost.setText("Host address: ");
		
		for (int section = 0; section < 4; section++)
		{
			
			if (engine.isUsingDec()) //Changes the size of the txt boxes depending on which system you are using
			{
				
				txtIP[section].setColumns(2);
				txtSub[section].setColumns(2);
				
			}
			else
			{
				
				txtIP[section].setColumns(6);
				txtSub[section].setColumns(6);
				
			}
			
			txtIP[section].setText(strIP[section]);
			txtSub[section].setText(strSub[section]);
			
			lblNetwork.setText(lblNetwork.getText() + strNetwork[section]);
			lblHost.setText(lblHost.getText() + strHost[section]);
			
			if (section != 3)
			{
				
				lblNetwork.setText(lblNetwork.getText() + ".");
				lblHost.setText(lblHost.getText() + ".");
				
			}
			
		}
		
	}
	
	public void keyPressed (KeyEvent e)
	{
	}
	
	public void keyTyped (KeyEvent e)
	{		
				
		JTextField txtTemp = (JTextField)e.getSource();
		char c = e.getKeyChar();
		boolean removeKey = false;
		String strTemp = txtTemp.getText();
		int startIndex = txtTemp.getSelectionEnd(); //Gets the point the key was typed at
		
		if (c == '.' || !Character.isDigit(c)) //If not a number
		{
			removeKey = true;
		}
		else
		{
			
			if (engine.isUsingDec()) //3 digit limit, input 0 - 9, number from 0 - 255
			{
				
				if (strTemp.length() == 3 && !(c == KeyEvent.VK_BACK_SPACE || c == KeyEvent.VK_DELETE))
				{
					removeKey = true;
				}
				else
				{
					
					String part1 = "", part2 = "";
					
					if (startIndex > 0) //If the index of the cursor is gt then 0
					{
						part1 = strTemp.substring(0, startIndex);
					}
					
					if (startIndex < (strTemp.length() - 1)) //If the index of the cursor is lt then the last char
					{
						part2 = strTemp.substring(startIndex, (strTemp.length() - 1));
					}
					
					int newNum = Integer.parseInt(part1 + c + part2); //Creates the number to test if it is legal

					if (newNum > 255) //If gt then 255, don't allow the number
					{
						removeKey = true;
					}

				}
				
			}
			else //8 digit limit, input 0 - 1
			{
				
				if (!(c == '0' || c == '1' || c == KeyEvent.VK_BACK_SPACE || c == KeyEvent.VK_DELETE))
				{
					removeKey = true;
				}
				else if (strTemp.length() == 8 && !(c == KeyEvent.VK_BACK_SPACE || c == KeyEvent.VK_DELETE))
				{
					removeKey = true;
				}
				
			}
			
		}
		
		if (removeKey)
		{
			e.consume();
		}
		
	}
	
	public void keyReleased (KeyEvent e)
	{
	}
	
	public void focusGained (FocusEvent e)
	{
		
		JTextField txtTemp = (JTextField)e.getSource();
		txtTemp.selectAll();
		
	}
	
	public void focusLost (FocusEvent e)
	{
	}
	
}