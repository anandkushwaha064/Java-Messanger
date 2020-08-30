package module;


import java.io.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.ListIterator;

public class Client 
{	
 public static void main(String args[])
 {
	 MainFrame m = new MainFrame();
	 m.FrameShow();
 }
}

class MainFrame implements ActionListener
{
	private static final ActionListener ActionListener = null;
	private JFrame f1;
	JSplitPane splitPane ;
	Connection con =  connectJDBC();
	JButton usernames[];
	private JButton Login,SignUp,settings,logout;
	private JLabel head,l1,userone,userList;
	private JInternalFrame userListIF,chatWindow,profile;
	int f1height,f1width;
	private String usname=null,usrid=null,previous=null;
	JLabel old[];
	
	MainFrame()
	{
		
	}
	
	public void FrameShow()
	{
		
		Dimension d1 = Toolkit.getDefaultToolkit().getScreenSize();		
		f1 = new JFrame("Messenging Application");
		f1.setVisible(true);
		f1.setSize(400,500);
		f1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f1.setExtendedState(f1.MAXIMIZED_BOTH);
		f1.setLayout(null);
		head = new JLabel("Massenger");
		head.setVisible(true);
		head.setBounds(80,5,320,50);
		head.setFont(new Font("Serif", Font.PLAIN, 35));
		Font buttonfont = new Font("Serif", Font.PLAIN, 18);
		l1 = new JLabel(" ");
		l1.setVisible(true);
		l1.setBounds(50,60,120,30);
		
		Login = new JButton("Login");
		Login.setVisible(true);
		Login.setBounds(250,30,100,30);
		Login.setFont(buttonfont);
		
		logout= new JButton("Log Out");
		logout.setVisible(false);
		logout.setBounds(250,30,100,30);
		logout.setFont(buttonfont);
		
		SignUp = new JButton("SignUp");
		SignUp.setVisible(true);
		SignUp.setBounds(360,30,100,30);
		SignUp.setFont(buttonfont);
		settings = new JButton("Settings");
		settings.setVisible(true);
		settings.setBounds(470,30,100,30);
		settings.setFont(buttonfont);
		f1.add(Login);
		f1.add(logout);
		f1.add(SignUp);
		f1.add(settings);
		f1.add(head);		
		f1.add(l1);
		Login.addActionListener(this);
		SignUp.addActionListener(this);
		settings.addActionListener(this);
		logout.addActionListener(this);
		f1.addWindowListener(new WindowAdapter() {		
		});	
	}	
	
	public void actionPerformed(ActionEvent ae)
	{
		
		if(ae.getActionCommand()=="SignUp")
		{
			f1.setEnabled(false);
			if(con!=null)
			{
				CreateAccontForms cs = new CreateAccontForms();
				cs.accoutCreate(this,(com.mysql.jdbc.Connection) con);
			}
		}
		else if(ae.getActionCommand()=="Login")
		{
			f1.setEnabled(false);
			new LoginForms(this,(com.mysql.jdbc.Connection) con);
		}
		
		else if(ae.getActionCommand()=="Log Out")
		{
			f1.dispose();	
			this.FrameShow();
		}
		else if(ae.getActionCommand()=="Settings")
		{
			f1.setEnabled(false);
			new SettingForms(this);
		}
		else 
		{
		String pre = ae.getActionCommand().replaceAll("\\s", "");
		previous = pre;
		chatWindow.dispose();
		userListIF.dispose();
		profile.dispose();
		chatFrames(usname ,usrid, pre);
		}
	}
	
	public void enableFrame()
	{
		f1.setEnabled(true);
	}	
	
	public void userSession(String usid)
	 {
			if(usid!=null)
			{
				try
				{
					Statement stmt=connectJDBC().createStatement();  
					ResultSet rs=stmt.executeQuery("Select * from user where uid =\""+ usid +"\"");
					if(rs.next())
					{
						String uname[] = rs.getString("username").split("\\s");
						chatFrames(uname[0],usid,"");
						usname=uname[0];
						usrid=usid;
						
					}
				}
				catch(Exception e)
				{
					System.out.println(e);
				}	
			}
	 }
	 
	public void chatFrames(String name,String uid,String frontuser)
	 {
		 Login.setVisible(false);
		 SignUp.setVisible(false);
		 settings.setVisible(false);
		 logout.setVisible(true);
		 
		 JLabel username = new JLabel("Available Users ");
		 username.setVisible(true);
		 username.setBounds(10,5,300,30);
		 username.setFont(new Font("Serif", Font.PLAIN, 23));
		 username.setForeground(new Color(5, 14, 105));
		 String chathead = null;
		 
		 if(frontuser!="")
			 chathead = name + " you Chatting with " + frontuser;
		 else
			 chathead = " Welcome " + name;	 	
		 
		 JLabel chatheading = new JLabel(chathead);
		 chatheading.setVisible(true);
		 chatheading.setBounds(10,5,500,30);
		 chatheading.setFont(new Font("Serif", Font.PLAIN, 23));
		 chatheading.setForeground(new Color(5, 14, 105));
	
		 f1height = f1.getHeight();
		 f1width = f1.getWidth();
		 
		 userListIF = new JInternalFrame();
		 userListIF.setVisible(true);
		 userListIF.add(username);
		 userListIF.setLayout(null);
		 userListIF.setLocation(20,80);
		 int width = (int)(f1width*22/100);
		 int position =(int)(f1width*0/100);
		 userListIF.setBounds(position,80,width,f1height-120);
		 
		 chatWindow = new JInternalFrame();
		 chatWindow.setVisible(true);
		 chatWindow.add(chatheading);
		 chatWindow.setLayout(null);
		 chatWindow.setLocation(20,80);
		 width = (int)(f1width*57/100);
		 position =(int)(f1width*22/100);
		 chatWindow.setBounds(position,80,width,f1height-120);
		 
		 profile = new JInternalFrame();
		 profile.setVisible(true);
		 profile.setLayout(null);
		 profile.setLocation(20,80);
		 position =(int)(f1width*79/100);
		 width = (int)(f1width*20/100);
		 profile.setBounds(position,80,width,f1height-120);
			
		 f1.add(userListIF);
		 f1.add(chatWindow);
		 f1.add(profile);
		 showUsers(userListIF,uid);
		 chatWindows(uid,frontuser);
	 }
		
	public void showUsers(JInternalFrame userlist,String vals)
	{	int count =0;
		try
		{   
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("Select count(uid) from user");
			if(rs.next())
			{
				count = rs.getInt("count(uid)");
				System.out.println(count);
			}
			int i=0;
			usernames = new JButton[count-1];
			rs = stmt.executeQuery("Select * from user where not uid =\""+vals+"\"");
			while(rs.next())
			{
				usernames[i] =  new JButton("  "+rs.getString("uid"));	
				usernames[i].setName(rs.getString("username"));
				usernames[i].addActionListener(this);
				applyStyleUsers(userlist,usernames[i],i);
				i++;
			}
		}
		catch(Exception e)
		{
			System.out.println(e);
		}	
	}
	
	public void chatWindows(String userid,String sentto)
	{
		ArrayList<Integer> ar = new ArrayList<Integer>();
		JTextField ta = new JTextField();
		ta.setVisible(true);
		ta.addKeyListener(new KeyAdapter()
				{
				public void keyPressed(KeyEvent ke)
					{
						String st = ta.getText();
						if(ke.getKeyCode()==10&&st!=null)
						{
//							System.out.println(st);
							insertmsg(ar,userid,sentto,st);		
							ta.setText("");
						}
					}
				});
		
		ta.setBounds(10,f1height*76/100,f1width*56/100,50);
		ta.setOpaque(true);
		ta.setFont(new Font("Serif", Font.PLAIN, 15));
		ta.setBackground(new Color(220, 228, 252));
		ta.setForeground(new Color(155,24,166));
		ta.setBorder(BorderFactory.createLineBorder(new Color(220, 228, 252)));
		chatWindow.add(ta);
		
		try
		{
			Statement stmt = con.createStatement();
			String qr =" Select seno,currentuser from chats where (currentuser =\""+ userid +"\" and sentuser =\""+ sentto +"\")"
					+ " or ( currentuser =\""+ sentto  + "\" and sentuser =\""+ userid +"\") order by seno";
			ResultSet rs = stmt.executeQuery(qr);
			
			while(rs.next())
			{
				ar.add(rs.getInt("seno"));	
//				System.out.println(rs.getInt("seno"));
			}	
			setMsgLayout(ar,userid);
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
	
	public void setMsgLayout(ArrayList<Integer> ar,String strs)
	{	
		if(old!=null)
		{for(JLabel j:old)
		{
			j.setVisible(false);
		}}
		JLabel jbl[] = new JLabel[ar.size()];
		System.out.println(ar.size());
		ListIterator lt = ar.listIterator(ar.size());
		int i=1;
		while(lt.hasPrevious())
		{
			int sno = (Integer)lt.previous();
			try
			{	
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(" Select * from chats where seno = " + String.valueOf(sno));
				if(rs.next())
				{
					Border blackline = BorderFactory.createLineBorder(new Color(220, 228, 252));
					String msg = rs.getString("msg");
					String user = rs.getString("currentuser");
					System.out.println(user);
					jbl[i-1] = new JLabel("  "+msg);
					
					jbl[i-1].setOpaque(true);
					
					int len = msg.length();
					int widthmsg = len*6+15;
					int stpointy = (f1height*(70-(5*i)))/100;
					int stpointx = 0;
					
					if(user.equals(usrid)||user.equals(strs))
					{
						stpointx = f1width*40/100;	
						jbl[i-1].setBackground(new Color(25, 136, 255));
						
					}
					else
					{
						stpointx = 40;	
						jbl[i-1].setBackground(new Color(99, 0, 212));
						
					}
					jbl[i-1].setVisible(true);
					jbl[i-1].setBounds(stpointx, stpointy, widthmsg, 30);
					jbl[i-1].setBorder(blackline);
					jbl[i-1].setForeground(new Color(255, 255, 255));	
					chatWindow.add(jbl[i-1]);
					i++;
				}
			}
			catch(Exception e)
			{}
			old = jbl;
		}
	}
	
	public void insertmsg(ArrayList<Integer> ar,String ... a)
	{
		String exc = "insert into chats (currentuser,sentuser,seen,msg) values ('"+a[0]+"','"+a[1]+"','no','"+a[2]+"')";
		try
		{
			PreparedStatement preparedStmt = con.prepareStatement(exc);
			preparedStmt.execute();
			Statement stmt = connectJDBC().createStatement();
			String qr =" Select seno,currentuser from chats where (currentuser =\""+ a[0] +"\" and sentuser =\""+ a[1] +"\")"
					+ " or ( currentuser =\""+ a[1]  + "\" and sentuser =\""+ a[0] +"\") order by seno";
			ResultSet rs = stmt.executeQuery(qr);
			while(rs.next())
			{
				ar.add(rs.getInt("seno"));	
//				System.out.println(rs.getInt("seno"));
			}
			
			
			
		}
		catch(Exception e)
		{
			System.out.println("Exception sending msg");
		}
		setMsgLayout(ar,a[0]);
	}
	
	public void applyStyleUsers(JInternalFrame frme,JButton jl,int val)
	{
		Font f = new Font("Serif", Font.PLAIN, 18);
		Border blackline = BorderFactory.createLineBorder(new Color(220, 228, 252));
		jl.setVisible(true);
		jl.setBounds(20,42*val+50,200,38);
		jl.setOpaque(true);
		jl.setBackground(new Color(220, 228, 252));
		jl.setForeground(new Color(155,24,166));
		jl.setLayout(null);
		jl.setBorder(blackline);
		jl.setFont(f);
		frme.add(jl);
	}
	
	public Connection connectJDBC()
	{
		Connection con=null;

		try
		{ 
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://Localhost/javaproject?characterEncoding=latin1","root","");  
		}
		catch(Exception e)
		{ 
			System.out.println(e);
		}  
		return con;
	}
}



