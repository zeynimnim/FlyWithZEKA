package weka.api;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class Window1 {

	private JFrame frame1 = new JFrame("ZEKA  1.0.0"); //Þablon
	private JPanel GUI = new JPanel(); //Ana panel
	private JPanel dugme = new JPanel(); //Alt panel
	private ImageIcon icon = new ImageIcon("C:/Users/Zeynep/eclipse-workspace/African_Crisis/documents/ikon.jpg");
	private JButton jb = new JButton(icon);

	public Window1() {
		frame1.setSize(500,500);
		frame1.setLocation(400, 100);
		frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //pencereyi kapatma iþlemi
		GUI.setLayout(null);//Panellere layout yapýlýr
		GUI.setBackground(Color.black);
		dugme.setBackground(Color.DARK_GRAY);
		dugme.setLayout(null);
		dugme.setSize(400,400);
		dugme.setLocation(40,30);
		GUI.add(dugme);
		jb.setSize(400,400);
		jb.setLocation(0,0 );
		jb.setHorizontalAlignment(0);
		jb.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent evt) {
				buttonActionPerformed1(evt);
				
			}
			
			
		}
				);
		dugme.add(jb);
		frame1.setContentPane(GUI);
		frame1.setVisible(true);
	}
	/*
	public void setVisible(boolean wht) {
		frame1.setVisible(wht);
	}*/
	
	private void buttonActionPerformed1(ActionEvent evt) {
		frame1.setVisible(false);
		Window2 window2 = new Window2();		
				
	}
	
	
	
}
