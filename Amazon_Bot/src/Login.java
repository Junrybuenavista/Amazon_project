import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
  
public class Login extends JFrame implements ActionListener
 {
  JButton SUBMIT;
  JPanel panel;
  JLabel label1,label2,label3;
  final JTextField  text1,text2,text3;
  JTextArea area;
  Amazon_Bot bot;
   Login()
   {
		   label1 = new JLabel();
		   label1.setText("Username:");
		   text1 = new JTextField(15);
		 
		   label2 = new JLabel();
		   label2.setText("Password:");
		   text2 = new JPasswordField(15);
		   
		   
		   label3 = new JLabel();
		   label3.setText("Report send to this Email");
		   text3 = new JTextField(15);
		  
		   SUBMIT=new JButton("SUBMIT");
		   
		   panel=new JPanel(new GridLayout(3,1));
		   panel.add(label1);
		   panel.add(text1);
		   panel.add(label2);
		   panel.add(text2);
		   panel.add(label3);
		   panel.add(text3);
		   
		   area=new JTextArea();
	       area.setEditable(false);
		   
		   add(panel,BorderLayout.NORTH);
		   add(SUBMIT,BorderLayout.SOUTH);
		   add(area,BorderLayout.CENTER);
		   
		   
		   SUBMIT.addActionListener(this);
		   setTitle("AMAZON LOGIN");
   }
  public void actionPerformed(ActionEvent ae)
   {	 
    	   bot = new Amazon_Bot(text1.getText(),text2.getText(),area,text3.getText());
    	   bot.start();
     		
   }
 
  public static void main(String arg[])
  {
		   try
		   {
			   Login frame=new Login();
			   frame.setSize(400,350);
			   frame.setVisible(true);
		   }
		   catch(Exception e)
		   {JOptionPane.showMessageDialog(null, e.getMessage());}
		   }
 }
 
