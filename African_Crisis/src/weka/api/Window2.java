package weka.api;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


public class Window2 {
	private JFrame frame1 = new JFrame("ZEKA  1.0.0");	 //ZEYNEP ENVIRONMENT FOR KNOWLEDGE ANALYSIS
	private JPanel GUI = new JPanel(); //Ana panel
	private JPanel etiketler = new JPanel(); //Alt panel
	private JLabel etiket1 = new JLabel("Makine ��renmesi Algoritmalar�ndan birini se�iniz, ard�ndan tahmin yapt�r�n�z");
	private JPanel dugmeler = new JPanel(); //Alt panel
	private JButton jb1 = new JButton("ZeroR");
	private JButton jb2 = new JButton("J48 ");
	private JButton jb3 = new JButton("OneR");
	private JButton jb4 = new JButton("Logistic ");
	private JButton jb5 = new JButton("IBk-Knn");
	private JButton jb6 = new JButton("Tahmin Yap"); //Algoritma se�ildiyse o algoritmayla se�ilmediyse kendi se�ti�i algoritmayla test yapar
	//Datasetin en �st�ndeki 2 �rne�i test yapar
	private int x = 0 ;
	private Processor processor;
	private String probability;
	
	
	
	//Burada Algorithm nesnesi olu�turursam direk a�a��daki privateta algortihmden ��kt� kullanabilriim
	//african crisisteki main i processor ilan edip algortihm ve data aras� bir s�n�f yapsam
	//direk algorithmdeki cevaplar� window2 den salsam harika olur


public Window2() {
	frame1.setSize(500,500);
	frame1.setLocation(400, 100);
	frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //pencereyi kapatma i�lemi
	GUI.setLayout(null);//Panellere layout yap�l�r
	GUI.setBackground(Color.black);
	etiketler.setBackground(Color.DARK_GRAY);
	etiketler.setLayout(null);
	etiketler.setSize(500,50);
	etiketler.setLocation(0,0);
	GUI.add(etiketler);
	etiket1.setForeground(Color.red);
	etiket1.setSize(500,50);
	etiket1.setLocation(0,0 );
	etiket1.setHorizontalAlignment(0);
	etiketler.add(etiket1);
	dugmeler.setBackground(Color.DARK_GRAY);
	dugmeler.setLayout(null);
	dugmeler.setSize(400,300);
	dugmeler.setLocation(40,100);
	GUI.add(dugmeler);
	jb1.setSize(100,50);
	jb1.setLocation(80,50 );
	jb1.setHorizontalAlignment(0);
	jb1.addActionListener(new ActionListener() {	
		public void actionPerformed(ActionEvent e) {
		  try {
			buttonActionPerformed1(e);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		}
	});	
	dugmeler.add(jb1);
	jb2.setSize(100,50);
	jb2.setLocation(80,120);
	jb2.setHorizontalAlignment(0);
	System.out.println();
	jb2.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
		 try {
			buttonActionPerformed2(e);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		}
	});
	System.out.println();
	dugmeler.add(jb2);	
	jb3.setSize(100,50);
	jb3.setLocation(80,190 );
	jb3.setHorizontalAlignment(0);
	jb3.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
		try {
			buttonActionPerformed3(e);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		}
	});
	dugmeler.add(jb3);
	jb4.setSize(100,50);
	jb4.setLocation(210,50 );
	jb4.setHorizontalAlignment(0);
	jb4.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			try {
				buttonActionPerformed4(e);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	});
	dugmeler.add(jb4);
	
	
	jb5.setSize(100,50);
	jb5.setLocation(210,120);
	jb5.setHorizontalAlignment(0);
	jb5.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			try {
				buttonActionPerformed5(e);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	});
	dugmeler.add(jb5);
	jb6.setSize(150,80); //jb6.setSize(100,50);
	jb6.setLocation(210,190 );
	jb6.setHorizontalAlignment(0);
	dugmeler.add(jb6);
	frame1.setContentPane(GUI);
	jb6.addActionListener(new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				buttonActionPerformed6(e);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	});
	frame1.setVisible(true);
	
}
	
private void buttonActionPerformed1(ActionEvent evt) throws Exception {	
			System.out.println("ZeroR Algoritmas� se�ildi");
			this.x = 1;
			processor = new Processor(x);
			processor.doMachineLearning();
			processor.showMachineLearning();
}

private void buttonActionPerformed2(ActionEvent evt) throws Exception {	
	System.out.println("J48 Algoritmas� se�ildi");
	this.x = 2;
	processor = new Processor(x);
	processor.doMachineLearning();
	processor.showMachineLearning();
	System.out.println();
	System.out.println();
	
}

private void buttonActionPerformed3(ActionEvent evt) throws Exception {	
	System.out.println("OneR Algoritmas� se�ildi");
	this.x = 3;
	processor = new Processor(x);
	processor.doMachineLearning();
	processor.showMachineLearning();
	System.out.println();
	System.out.println();
}
private void buttonActionPerformed4(ActionEvent evt) throws Exception {	
	System.out.println("Logistic Regression Algoritmas� se�ildi");
	this.x = 4;
	processor = new Processor(x);
	processor.doMachineLearning();
	processor.showMachineLearning();
	System.out.println();
	System.out.println();
}
private void buttonActionPerformed5(ActionEvent evt) throws Exception {	
	System.out.println("IBk-knn Algoritmas� se�ildi");
	this.x = 5;
	processor = new Processor(x);
	processor.doMachineLearning();
	processor.showMachineLearning();
	System.out.println();
	System.out.println();
}
private void buttonActionPerformed6(ActionEvent evt) throws Exception {
	
	if ( x != 0) {
		JOptionPane.showMessageDialog(frame1,"L�tfen test etmek istedi�iniz �rne�in, csv dosyan�z�n ilk sat�r�nda oldu�undan emin olunuz.");
		probability = processor.getProbability();
		processor.doTestMyData();
	   showTestResult();
	System.out.println("Se�ilen Algoritma: " + processor.getName());
	System.out.println();
	System.out.println();
	System.out.println();
	System.out.println();
	System.out.println();
	System.out.println();
	System.out.println();
	System.out.println();
	System.out.println();
	System.out.println();
	System.out.println();
	System.out.println();
	System.out.println();
	System.out.println();
	System.out.println();
	System.out.println();
	System.out.println();
	this.x = 0;
	}
	else {
		JOptionPane.showMessageDialog(frame1,"L�tfen �nce makine ��renmesi algoritmas� se�iniz");
	//knn algoritmas�n� se�iyorum en iyi oldu�u i�in, ��nk� algoritma se�imi yap�lmad�
	//this.x = 5;
	//processor = new Processor(x);
	//System.out.println("Knn algoritmas� otomatik olarak se�ildi, test sonu�lar�n�z: ");
	}
	
	
}

private void showTestResult() {
	System.out.println("=====================");
	System.out.println(processor.getName() + " predicted result: " + processor.getPredString());
	String predicted = processor.getPredString();
	if (predicted.equals("crisis")) {
		System.out.println("Test edilen �rnekte, " + processor.getName() + " algoritmas�na g�re ");
	   System.out.println( "      %" + probability + "      " + " ihtimalle ekonomik kriz ya�anacak");
	}
	else {
		System.out.println("Test edilen �rnekte, " + processor.getName() + " algoritmas�na g�re ");
	   System.out.println( "      %" + probability + "      " + " ihtimalle ekonomik kriz ya�anmayacak");
	}
}




}