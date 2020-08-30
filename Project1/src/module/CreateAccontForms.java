package module;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.*;
import java.io.File;
import java.util.regex.*;

import javax.imageio.ImageIO;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import java.sql.*;
import com.mysql.jdbc.Connection;

class CreateAccontForms implements ActionListener
{
	private JFrame f1;
	private JTextField name,dob,pass,passw,mobile; 
	private JLabel head,lname,ldob,lpass,gen,lpassw,lmobile,l1;
	private JRadioButton male,female;
	private ButtonGroup b1=new ButtonGroup();
	private JCheckBox accept = new JCheckBox("Accepet term and contions");  
	private JButton submit,cancel;
	private MainFrame mainframe;
	private Validation v = new Validation();
	private Color r = Color.white;
	private Color w = Color.getHSBColor(38f, 37f, 81f);
	String checkall[] = new String[7];
	Connection con;
	CreateAccontForms() {
		
	}
	
	public String accoutCreate(MainFrame m,Connection con)
	{
		mainframe=m;
		this.con=con;
		f1 = new JFrame("Enter Details to Create Account");
		f1.setVisible(true);
		f1.setLayout(null);
	    f1.setSize(440,480);	
	    f1.setLocationRelativeTo(null);
	    f1.setDefaultCloseOperation(0);
	    l1 = new JLabel(" ");
		l1.setVisible(true);
		l1.setBounds(50,60,120,30);
		
	
	    head = new JLabel("Enter Details to SignUp");
		head.setVisible(true);
		head.setBounds(80,10,320,30);
		head.setFont(new Font("Serif", Font.PLAIN, 22));
		Font font = new Font("Serif", Font.PLAIN, 16);
		Font textfont= new Font("Serif", Font.PLAIN, 16);
		
		lname = new JLabel("Enter Name");
		lname.setVisible(true);
		lname.setBounds(50,80,120,30);
		lname.setFont(font);
		ldob = new JLabel("DOB (dd-mm-yyyy)");
		ldob.setVisible(true);
		ldob.setBounds(50,120,120,30);
		ldob.setFont(font);
		gen = new JLabel("Gender");
		gen.setVisible(true);
		gen.setBounds(50,160,120,30);
		gen.setFont(font);
		lpass = new JLabel("Password");
		lpass.setVisible(true);
		lpass.setBounds(50,200,120,30);
		lpass.setFont(font); 
		lpassw = new JLabel("Confirm Password");
		lpassw.setVisible(true);
		lpassw.setBounds(50,240,120,30);
		lpassw.setFont(font);
		lmobile = new JLabel("Mobile");
		lmobile.setVisible(true);
		lmobile.setBounds(50,280,120,30);
		lmobile.setFont(font);

		name = new JTextField(30);
		name.setVisible(true);
		name.setBounds(210,80,160,30);
		name.setFont(textfont);
		dob = new JTextField(30);
		dob.setVisible(true);
		dob.setBounds(210,120,160,30);
		dob.setFont(textfont);
		
		male = new JRadioButton("Male");
		male.setVisible(true);
		male.setBounds(210,160,80,30);
		male.setFont(textfont);
		female = new JRadioButton("Female");
		female.setVisible(true);
		female.setBounds(290,160,80,30);
		female.setFont(textfont);
		
		pass = new JTextField(30);
		pass.setVisible(true);
		pass.setBounds(210,200,160,30);
		pass.setFont(textfont);
		passw = new JTextField(30);
		passw.setVisible(true);
		passw.setBounds(210,240,160,30);
		passw.setFont(textfont);
		mobile = new JTextField(30);
		mobile.setVisible(true);
		mobile.setBounds(210,280,160,30);
		mobile.setFont(textfont);
		
		accept.setVisible(true);
		accept.setBounds(60,320,360,20);
		accept.setFont(new Font("Serif", Font.PLAIN, 16));
		
		submit = new JButton("Create Account");
		submit.setVisible(true);
		submit.setBounds(50,360,140,30);
		cancel = new JButton("Cancel");
		cancel.setVisible(true);
		cancel.setBounds(200,360,140,30);
		
		f1.add(submit);
		f1.add(cancel);
		f1.add(head);
		f1.add(head);
		f1.add(lname);
		f1.add(ldob);
		f1.add(gen);
		f1.add(lpass);
		f1.add(lpassw);
		f1.add(lmobile);
		
		f1.add(name);
		f1.add(dob);
		f1.add(pass);
		f1.add(passw);
		f1.add(mobile);
		
		name.addKeyListener(new KeyAdapter() {

			public void keyReleased(KeyEvent ke)
			{
				if(v.checkValidation(name,"name"))
					{checkall[0]="Correct";
					name.setBackground(r);}
				else
				{
					name.setBackground(w);
					checkall[0]="null";
				}
			}
			
		});
		
		dob.addKeyListener(new KeyAdapter(){
		public void keyReleased(KeyEvent ke)
		{
			if(v.checkValidation(dob,"dob"))
			{
				checkall[1]="Correct";
				dob.setBackground(r);
			}
			else
			{	dob.setBackground(w);
				checkall[1]="null";
			}
			
		}});
		pass.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent ke)
			{
				if(v.checkValidation(pass,"password"))
				{	checkall[2]="Correct";
					pass.setBackground(r);
				}
				else
				{
					pass.setBackground(w);
					checkall[2]="null";
				}
			}});
			
		passw.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent ke)
			{
				if(pass.getText().equals(passw.getText())) {
					checkall[3]="Correct";
					passw.setBackground(r);}
				else {
					passw.setBackground(w);
					checkall[3]="null";
				
					}
			}});
			
		mobile.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent ke)
			{
				if(v.checkValidation(mobile,"mob")) {
					checkall[4]="Correct";
					mobile.setBackground(r);
					}
				else {
					mobile.setBackground(w);
					checkall[4]="null";
				}
				
			}});
		accept.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				if(e.getStateChange()==2)
				{
					checkall[6]="Correct";
					accept.setBackground(w);
				}
				else {
					accept.setBackground(r);
					checkall[6]="null";
				}	
			}
		});
		
		
		male.addActionListener(this);
		female.addActionListener(this);
		
		f1.add(accept);
		b1.add(male);
		b1.add(female);
		f1.add(male);
		f1.add(female);
		f1.add(l1);
		
		cancel.addActionListener(this);
		submit.addActionListener(this);
		
		 
		f1.addWindowListener(new WindowAdapter() {
			public void windowClosing()
			{
				mainframe.enableFrame();
				f1.dispose();
			}
		});	
	return null;	
	}	
		
	public static void main(String args[])
	{
		
	}
	
	private boolean insertDetails()
	{
		try
		{
		Statement stmt=con.createStatement();  
		ResultSet rs=stmt.executeQuery("Select STR_TO_DATE(\""+dob.getText()+"\", \"%d-%m-%Y\")"); 
		rs.next();
		String st = rs.getString("STR_TO_DATE(\""+dob.getText()+"\", \"%d-%m-%Y\")");
		String uname[] = name.getText().split("\\s");
		uname[0]=uname[0]+mobile.getText().substring(0,5);
		String exc = "insert into user (username,dob,uid,password,gender,mobileno)"
				+ " values ('"+name.getText()+"','"+st+"','"+uname[0]+"','"+pass.getText()+"','"+checkall[5]+"','"+mobile.getText()+"')";
		PreparedStatement preparedStmt = con.prepareStatement(exc);
		preparedStmt.execute();
		System.out.println("Inserted Successfully");
		mainframe.enableFrame();
		mainframe.userSession(uname[0]);
		f1.dispose();
		}
		catch(Exception e)
		{
			System.out.println("An internal error occur");
		}
		return false;
	}
	
	public void actionPerformed(ActionEvent ae)
		{
			if(ae.getActionCommand().equals("Cancel"))
			{
				mainframe.enableFrame();
				f1.dispose();
			}
			if(ae.getActionCommand().equals("Create Account"))
			{
				if(this.shouldSubmited())
				{
					System.out.println(this.shouldSubmited());
					this.insertDetails();	
				}	
			}
			if(ae.getActionCommand().equals("Male"))
			{
				checkall[5]=ae.getActionCommand();
				male.setBackground(null);
				female.setBackground(null);
			}
			if(ae.getActionCommand().equals("Female"))
			{
				checkall[5]=ae.getActionCommand();
				male.setBackground(null);
				female.setBackground(null);
			}	
		}

	public boolean shouldSubmited()
	{
		boolean yes=true;
		if(checkall[0]!="Correct")
			{
			name.setBackground(w);
			yes = false;
			}
		if(checkall[1]!="Correct")
			{
			dob.setBackground(w);
			yes = false;
			}
		if(checkall[2]!="Correct")
			{
			pass.setBackground(w);
			yes = false;
			}
		if(checkall[3]!="Correct")
			{
			passw.setBackground(w);
			yes = false;
			}
		if(checkall[4]!="Correct")
			{
			mobile.setBackground(w);
			yes = false;
			}
		if(checkall[5]=="null")
			{
			male.setBackground(w); 
			female.setBackground(w);
			yes = false;
			}
		if(checkall[6]=="Correct")
			{
			accept.setBackground(w);
			yes = false;
			}
	   return yes; 
	}
}

class Validation 
{	
	private Pattern p;
	private Matcher m;
	public boolean checkValidation(JTextField v,String name)
	{
		String val = v.getText();
		switch(name)
		{
		case "name":
			p = Pattern.compile("[a-zA-Z.\\s]+");
			m = p.matcher(val);
			if(m.find()&&m.group().equals(val))
			{
				return true;		 
			}
		break;
		case "mob":
			p = Pattern.compile("(0|91)?[6-9][0-9]{9}");
			m = p.matcher(val);
			if(m.find()&&m.group().equals(val))
			{
				return true;		
			}
		break;
		case "password":
			p = Pattern.compile("[a-z]");
			m = p.matcher(val);
			Pattern p1 = Pattern.compile("[A-Z]");
			Matcher m1 = p1.matcher(val);
			Pattern p2 = Pattern.compile("[0-9]");
			Matcher m2 = p2.matcher(val);
			Pattern p3 = Pattern.compile("[=/\\.&|^]*()[]{}[-+]]");
			Matcher m3 = p3.matcher(val);
			
			if(m.find()&&m1.find()&&m2.find()&&val.length()>=6&&!m3.find())
			{
				return true;
			}
		break;
		case "dob":
			p = Pattern.compile("(([0-2][0-9])|(3[0-1]))-((0[0-9])|(1[0-2]))-((19[6-9][0-9])|(20[0-1][0-9]))");
			m = p.matcher(val);
			if(m.find())
			{
				return true;
			}
		break;
		case "usid":
			p = Pattern.compile("[a-zA-Z]");
			m = p.matcher(val);
			Pattern p4 = Pattern.compile("[0-9]");
			Matcher m4 = p4.matcher(val);
			if(m.find()&&m4.find()&&val.length()>=2)
			{
				return true;
			}	
		break;
		}		
	return false;	
	}
	
}
