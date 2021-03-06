package weka.api;//Weka Api, Filter pakedi altında supervised ve unsupervised algoritma arayüzleri sunar

import java.util.Scanner;

public class Processor {
  
	 private Data data = new Data();
	 private String file1 = "/Users/Zeynep/eclipse-workspace/African_Crisis/documents/african_crises.csv";
	 private String file2 = "/Users/Zeynep/eclipse-workspace/African_Crisis/documents/african_crises.arff";
     private String file3 = "/Users/Zeynep/eclipse-workspace/African_Crisis/documents/african_crises2.arff";
	 private String file4 = "/Users/Zeynep/eclipse-workspace/African_Crisis/documents/african_crises3.arff";
	 private String file5 = "/Users/Zeynep/eclipse-workspace/African_Crisis/documents/situationActual.arff";
    
	 private Algorithma algorithm = new Algorithma();
     private int x;
     private String name;
     
     public String getName() {
    	 return name;
     }
     
	 public Processor(int x) {
		  this.x = x;  
	 }
	 
	
	 
	 public void doMachineLearning() {
		//CSV dosyası ARFF dosyasına çevrilir			    
	      data.ConvertCsv2Arff(file1,file2);	    			
        //NumericToBinary ve NominalToBinary Filtreleri özelliklere uygulanır        
        data.numeric2Binary(file2,file3);    
        data.nominal2Binary(file3,file4);
        //Makine Öğrenmesi Algoritmalarını verimize uygulayalım ve model oluşturalım
		  algorithm.createAlgorithm(x,file4);
		  this.name = algorithm.getName();
	 }
	 
	 public void showMachineLearning() throws Exception {
			
			  algorithm.showEvaluation();
			 
		 }
	 
	 public void doTestMyData() {
		 
		 
		  //Test edilecek data, gerekli formata çevrilir
		 data.only1Instance(file4, file5);   
          //Oluşturulmuş model ile ülkenin ekonomik kriz yaşayıp yaşamayacağını tahmin edelim
         algorithm.testData(file5, x); 
	 }
	 
	 public String getPredString() {
		 return algorithm.getPredString();
	 }

	public String getProbability() {
		return algorithm.getProb();
	}
	
	 
	
}



