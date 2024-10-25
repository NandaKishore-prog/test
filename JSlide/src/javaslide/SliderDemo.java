package javaslide;

import java.awt.Font;
import javax.swing.JButton;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class SliderDemo implements ChangeListener {
	JFrame Frame;
	JLabel label;
	JPanel panel;
	JSlider slider;
	static int a;
	 SliderDemo(){
		 Frame=new JFrame("slider demo");
		 label=new JLabel();
		 panel=new JPanel();
		 slider=new JSlider(0,100,50);
		 //slider.setPreferredSize(new Dimension(1220,1220));
		 slider.setSize(420,420);
		 slider.setPaintTicks(true);
		 slider.setMinorTickSpacing(10);
		 slider.setPaintTrack(true);
		 slider.setMajorTickSpacing(25);
		 slider.setPaintLabels(true);
		 slider.setFocusable(true);
		 slider.setFont(new Font("cursive", Font.PLAIN,19));
		 
		JButton closeButton = new JButton("Close");
		
		closeButton.addActionListener(e -> Frame.dispose());
		panel.add(closeButton);

		
		 slider.addChangeListener(this);
		 Frame.setSize(420,420);
		 panel.add(label);
		 panel.add(slider);
		 Frame.add(panel);
		 Frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 
		
		 Frame.setVisible(true);
		 
	 }

	 
	@Override
	public void stateChanged(ChangeEvent e) {
		// TODO Auto-generated method stub
		label.setFont(new Font("MV Boli", Font.PLAIN,19));
		int value=slider.getValue();
	 label.setText("C = " +slider.getValue());
		 System.out.println(value);
		
	}

}
