package module;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
class LoginForms implements ActionListener
{
	private JFrame f1;
	private JTextField id,pass;
	private JLabel head,lid,ldob,lpass,l1,recpass,error;
	private JButton submit,cancel;
	private JCheckBox rememberme;
	private MainFrame clientFrame;
	private Validation checkVal = new Validation();
	private Color r = Color.white;
	private Color w = Color.getHSBColor(38f, 37f, 81f);
	private String checkall[]= new String[2];
	Connection con;
	BufferedReader br;
	LoginForms(MainFrame m1,Connection con) 
	{
		
		clientFrame = m1;
		this.con=con;
		f1 = new JFrame("Enter Details to Create Account");
		f1.setVisible(true);
		f1.setSize(420,315);	
		f1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f1.setLayout(null);
	    f1.setLocationRelativeTo(null);
	    f1.setDefaultCloseOperation(0);
		head = new JLabel("Enter Details For Login");
		head.setVisible(true);
		head.setBounds(90,10,300,30);
		head.setFont(new Font("Serif", Font.PLAIN, 22));
		Font font = new Font("Serif", Font.PLAIN, 16);
		Font textfont= new Font("Serif", Font.PLAIN, 16);
		l1 = new JLabel(" ");
		l1.setVisible(true);
		l1.setBounds(50,60,120,30);
		
		lid = new JLabel("Enter ID");
		lid.setVisible(true);
		lid.setBounds(50,80,120,30);
		lid.setFont(font);
		lpass = new JLabel("Password");
		lpass.setVisible(true);
		lpass.setBounds(50,120,120,30);
		lpass.setFont(font); 
		
		error= new JLabel("User Id or Password is wrong");
		error.setVisible(false);
		error.setBounds(60,160,300,20);
		error.setFont(new Font("Serif", Font.PLAIN, 16)); 
		error.setForeground(Color.BLUE);
		
		rememberme = new JCheckBox("Remember me");
		rememberme.setBounds(60,190,120,20);
		rememberme.setVisible(true);
		rememberme.setFont(new Font("Serif", Font.PLAIN, 16));

		recpass = new JLabel("Forget Password");
		recpass.setVisible(true);
		recpass.setBounds(230,190,360,20);
		recpass.setFont(new Font("Serif", Font.PLAIN, 16));
		
		id = new JTextField(30);
		id.setVisible(true);
		id.setBounds(210,80,160,30);
		id.setFont(textfont);
		pass = new JTextField(30);
		pass.setVisible(true);
		pass.setBounds(210,120,160,30);
		pass.setFont(textfont);
		submit = new JButton("Log In");
		submit.setVisible(true);
		submit.setBounds(50,230,140,30);
		cancel = new JButton("Cancel");
		cancel.setVisible(true);
		cancel.setBounds(200,230,140,30);
	
		l1 = new JLabel(" ");
		l1.setVisible(true);
		l1.setBounds(50,60,120,30);
	
		f1.add(submit);
		f1.add(cancel);
		f1.add(head);
		
		f1.add(head);
		f1.add(lid);
		f1.add(lpass);
		f1.add(id);
		f1.add(pass);
		f1.add(recpass);
		f1.add(l1);
		f1.add(rememberme);
		f1.add(error);
		submit.addActionListener(this);
		cancel.addActionListener(this);
		try
		{
		br = new BufferedReader(new FileReader("E:\\eclipse-workspace\\Project1\\log.pass"));
		id.setText(br.readLine());
		pass.setText(br.readLine());
		checkall[0]="Correct";
		checkall[1]="Correct";
		rememberme.setSelected(true);
		}
		catch(Exception e)
		{
//			System.out.println(e);
		}
		id.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent ke)
			{
				error.setVisible(false);
				if(checkVal.checkValidation(id,"usid"))
					{
						checkall[0]="Correct";
						id.setBackground(r);
					}
				else
					{	id.setBackground(w);
						checkall[0]="null";
					}
			}
		});
		
		pass.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent ke)
			{
				error.setVisible(false);
				if(checkVal.checkValidation(pass,"password"))
				{
					checkall[1]="Correct";
					pass.setBackground(r);
				}
				else
				{	pass.setBackground(w);
					checkall[1]="null";
				}
			}
		});
		
	}
		public void actionPerformed(ActionEvent ae)
		{
			if(ae.getActionCommand().equals("Cancel"))
				{
					clientFrame.enableFrame();
					f1.dispose();
				}
			if(ae.getActionCommand().equals("Log In"))
			{
				boolean yes=true;
				if(checkall[0]!="Correct")
				{
					id.setBackground(w);
					yes=false;
				}
				if(checkall[1]!="Correct")
				{
					pass.setBackground(w);
					yes=false;
				}
				if(yes==true)
				{
					String usid = id.getText();
					String passs = pass.getText();
				
					try
					{
						Statement stmt=con.createStatement();  
						ResultSet rs=stmt.executeQuery("Select * from user where uid =\""+ usid +"\" and password =\""+ passs +"\"");
						if(rs.next())
						{
							if(rememberme.isSelected())
							{
								PrintWriter pr = new PrintWriter(new File("E:\\eclipse-workspace\\Project1\\log.pass"));
								pr.write(usid+"\n");
								pr.write(passs);
								pr.flush();
								pr.close();
							}
							else
							{
								PrintWriter pr = new PrintWriter(new File("E:\\eclipse-workspace\\Project1\\log.pass"));
								pr.flush();
								pr.close();
							}
						
							clientFrame.enableFrame();
							clientFrame.userSession(rs.getString("uid"));
							f1.dispose();
						}
						else
						{
							System.out.println("Incorrect details");	
							error.setVisible(true);
						}
					}
					catch(Exception e)
					{
						
					}
				}
				else
				{
					
				}
					
			}
		}
	public static void main(String args[])
	{
		
	}
	
}
