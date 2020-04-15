package weka.api; 
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileReader;
import java.io.FileWriter;
//Dosya doluysa i�eri�ini silip yeni i�erik olu�tural�m.

import weka.attributeSelection.CfsSubsetEval;
import weka.attributeSelection.GreedyStepwise;
import weka.core.Instances;
import weka.core.Instance;
import weka.core.converters.ArffSaver;
import weka.core.converters.CSVLoader;
import weka.core.converters.ConverterUtils.DataSource;
import weka.filters.Filter;
import weka.filters.supervised.attribute.AttributeSelection;
import weka.filters.unsupervised.attribute.NominalToBinary;
import weka.filters.unsupervised.attribute.NumericToBinary;

public class Data {
	
	private CSVLoader loader = new CSVLoader();
	private Instances data;
	private ArffSaver saver = new ArffSaver(); 
	private DataSource source;
	
	public Data() {}	
	
	
	public Data(CSVLoader loader,Instances data, ArffSaver saver) {
		this.loader = loader;
		this.data = data;
		this.saver = saver;	
	}
	
	
	public void ConvertCsv2Arff(String file1, String file2) {
		try {    
				loader.setSource(new File(file1));  //Loader data path'i al�r.
				data = loader.getDataSet(); //Instances s�n�f�n�n data adl� objesine bu data aktar�l�r.				
				saver.setInstances(data); //�rnekler arff dosyas�na uyarlan�r		
				File f = new File(file2);
				
				if(f.length()==0) { //dosya bo�sa veya yoksa length 0 d�nd�r�r ^^ �ok kullan��l� yani
				 saver.setFile(f); //Girilen filepath adresine bu yeni arff dosyas� kaydedilir
				 saver.writeBatch(); //Bu s�n�f toplu �rnek yazar
				}
				
				
				
			     
				
		}
	
	 
		catch (Exception e) {
			System.out.println("There can be two possible reasons");
			System.out.println("Input: Data source file not found");
			System.out.println("Output: There is already such a file. ");
			e.printStackTrace();
	    	
	     }
		

	
   }
	
	
	
	public void  numeric2Binary(String file2,String file3){
		try {
		source = new DataSource(file2);
		data = source.getDataSet();
		data.setClassIndex(data.numAttributes()-1); 
	  /*Class de�eri nominal kalmal�d�r,
		Weka class de�erini real'e �evirmez. (real ve binary, numerictir) 
		Binary ise bir real tip �e�ididir.*/
		//Numeric de�erli Attribute'ler se�ilerek Binary de�erliye d�n��t�r�l�r			
	    NumericToBinary filter = new NumericToBinary(); 
	    filter.setInputFormat(data); //Filtreye dataseti verir
	    data = Filter.useFilter(data, filter); //datan�n i�eri�i de�i�ti.
	    //Filtrelenmi� yeni datay�, yeni bir arff dosyas�na kaydedelim
		saver.setInstances(data);
		File f = new File(file3);
		
		
		if (f.length()==0) {
		saver.setFile(f);
		saver.writeBatch();
		 }
		
		
		
		
		}
		catch(Exception e) {
			System.out.println("There can be two possible reasons");
			System.out.println("Input: Data source file not found");
			System.out.println("Output: There is already such a file. ");
			e.printStackTrace();
		  }
		}
		  

	public void nominal2Binary(String file3,String file4){
		try {
			source = new DataSource(file3);
			data = source.getDataSet();
			data.setClassIndex(data.numAttributes()-1);
		//Nominal de�erli Attribute'ler se�ilerek Binary de�erliye d�n��t�r�l�r
		    NominalToBinary filter = new NominalToBinary();
		    filter.setInputFormat(data);	
		    data = Filter.useFilter(data, filter); //datan�n i�eri�i de�i�ti.
		  //Filtrelenmi� yeni datay�, yeni bir arff dosyas�na kaydedelim
			saver.setInstances(data);
			File f = new File(file4);
			if(f.length() == 0) {
			saver.setFile(f);
			saver.writeBatch();	
			}
		}
		
		catch(Exception e) {
			System.out.println("There can be two possible reasons");
			System.out.println("Input: Data source file not found");
			System.out.println("Output: There is already such a file. ");
			e.printStackTrace();
		  }
		}
	
	//Baz� datasetlerde baz� �zellikler class labelini etkilemez, algoritmalar�n yanl�� �al��mas�na sebebiyet verir
    //Bu y�zden budama se�ene�i se�ilerek dah efektif �zellikler se�ilip ba�lanabilir.
	public void prunning(String file4, String file5){
		try {
			
			//load dataset
			source = new DataSource(file4);
			data = source.getDataSet();
			//Attribute selection nesnesi �retilir.
			AttributeSelection filter = new AttributeSelection();
			//Evaluator ve Search Algoritmalar� nesneleri �retilir.
			CfsSubsetEval eval = new CfsSubsetEval();
			GreedyStepwise search = new GreedyStepwise();
			//Algoritmay� geriye do�ru arama yapmak i�in ayarlayal�m.
			search.setSearchBackwards(true);
			//Filtreyi search ve evaluator algoritmalar�n� kullanmas� i�in ayarlar
			filter.setEvaluator(eval);
			filter.setSearch(search);
			//Veri k�mesini g�nderelim filtreye
			filter.setInputFormat(data);
			//Uygula
			data = Filter.useFilter(data, filter);
			//save 
			saver.setInstances(data);
			File f = new File(file5);
			if(f.length() == 0) {
			saver.setFile(f);
            saver.writeBatch();
			}
			
		}
		catch(Exception e) {
			System.out.println("There can be two possible reasons");
			System.out.println("Input: Data source file not found");
			System.out.println("Output: There is already such a file. ");
			e.printStackTrace();
		  }
		}

	

	public void only1Instance(String file5, String file7) {
		try {
			
			//File5 ilk iki instance hari� t�m instanceslar delete edilir
			source = new DataSource(file5);
			data = source.getDataSet();
			data.setClassIndex(data.numAttributes()-1);
			for(int i = data.numInstances()-1; i >0; i-- ) {
				Instance inst = data.instance(i);
				data.delete(i);		
			}
			saver.setInstances(data);
			File f = new File(file7);
			
			if(f.length() == 0) {
				saver.setFile(f);
	            saver.writeBatch();
				}
				
			
			
		
				}
		
		
		
				
							
		catch(Exception e) {
			System.out.println("There can be two possible reasons");
			System.out.println("Input: Data source file not found");
			System.out.println("Output: There is already such a file. ");
			e.printStackTrace();
		}
	}
	
	
}
