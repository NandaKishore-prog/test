package jcombo;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JFrame;

public class MyFrame extends JFrame implements ActionListener {
	JComboBox comboBox;
	MyFrame(){
	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	this.setLayout(new FlowLayout());
	
	String[] lavs= {"myuu","ammu","chloo"};
	
	comboBox=new JComboBox(lavs);
	comboBox.addActionListener(this);
	this.add(comboBox);
	this.setSize(200,300);
	this.pack();
	this.setVisible(true);
	
	
	
	}
	
	

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource()==comboBox) {
			System.out.println(comboBox.getSelectedItem());
			
		}
		
	} 
	

}
