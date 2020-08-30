package module;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

class SettingForms extends JFrame implements ActionListener 
{
	private JFrame f1;
	private JTextField name,dob; 
	private JLabel head,lname,ldob,l1;
	private JCheckBox accept = new JCheckBox("Accepet term and condtions");  
	private JButton submit,cancel;
	private MainFrame mainframe;
	SettingForms(MainFrame m)
	{
		mainframe=m;
		f1 = new JFrame("Settings");
		f1.add(new JLabel(""),SwingConstants.CENTER);
		f1.setVisible(true);
	    f1.setSize(440,280);
	    f1.setLocationRelativeTo(null);
	    f1.setDefaultCloseOperation(0);
		
	    l1 = new JLabel(" ");
		l1.setVisible(true);
		l1.setBounds(50,60,120,30);
	    head = new JLabel("Enter Ip and port address");
		head.setVisible(true);
		head.setBounds(80,10,320,30);
		head.setFont(new Font("Serif", Font.PLAIN, 22));
		Font font = new Font("Serif", Font.PLAIN, 16);
		Font textfont= new Font("Serif", Font.PLAIN, 16);
		
		lname = new JLabel("Enter Server IP");
		lname.setVisible(true);
		lname.setBounds(50,80,120,30);
		lname.setFont(font);
		ldob = new JLabel("Enter Port");
		ldob.setVisible(true);
		ldob.setBounds(50,120,120,30);
		ldob.setFont(font);
		
		name = new JTextField(30);
		name.setVisible(true);
		name.setBounds(210,80,160,30);
		name.setFont(textfont);
		dob = new JTextField(30);
		dob.setVisible(true);
		dob.setBounds(210,120,160,30);
		dob.setFont(textfont);
		
		submit = new JButton("Apply");
		submit.setVisible(true);
		submit.setBounds(50,180,140,30);
		cancel = new JButton("Cancel");
		cancel.setVisible(true);
		cancel.setBounds(200,180,140,30);
		
		f1.add(submit);
		f1.add(cancel);
		f1.add(head);
		f1.add(head);
		f1.add(lname);
		f1.add(ldob);
		f1.add(name);
		f1.add(dob);
		f1.add(accept);	
		f1.add(l1);
		
		cancel.addActionListener(this);
		f1.addWindowListener(new WindowAdapter() {
			
		public void windowClosing()
		{
			mainframe.enableFrame();
			f1.dispose();
			System.out.println("fram");
		}
		public void windowClosed(WindowEvent arg0) {
			mainframe.enableFrame();
			f1.dispose();
			System.out.println("fram");	
		}
		});	
	}	
	
	public void actionPerformed(ActionEvent ae)
	{
		if(ae.getActionCommand()=="Cancel")
		{
			mainframe.enableFrame();
			f1.dispose();
		}
	}
	public static void main(String args[])
	{
		
	}
}


