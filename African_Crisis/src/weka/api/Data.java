package weka.api; 
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileReader;
import java.io.FileWriter;
//Dosya doluysa içeriðini silip yeni içerik oluþturalým.

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
				loader.setSource(new File(file1));  //Loader data path'i alýr.
				data = loader.getDataSet(); //Instances sýnýfýnýn data adlý objesine bu data aktarýlýr.				
				saver.setInstances(data); //Örnekler arff dosyasýna uyarlanýr		
				File f = new File(file2);
				
				if(f.length()==0) { //dosya boþsa veya yoksa length 0 döndürür ^^ çok kullanýþlý yani
				 saver.setFile(f); //Girilen filepath adresine bu yeni arff dosyasý kaydedilir
				 saver.writeBatch(); //Bu sýnýf toplu örnek yazar
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
	  /*Class deðeri nominal kalmalýdýr,
		Weka class deðerini real'e çevirmez. (real ve binary, numerictir) 
		Binary ise bir real tip çeþididir.*/
		//Numeric deðerli Attribute'ler seçilerek Binary deðerliye dönüþtürülür			
	    NumericToBinary filter = new NumericToBinary(); 
	    filter.setInputFormat(data); //Filtreye dataseti verir
	    data = Filter.useFilter(data, filter); //datanýn içeriði deðiþti.
	    //Filtrelenmiþ yeni datayý, yeni bir arff dosyasýna kaydedelim
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
		//Nominal deðerli Attribute'ler seçilerek Binary deðerliye dönüþtürülür
		    NominalToBinary filter = new NominalToBinary();
		    filter.setInputFormat(data);	
		    data = Filter.useFilter(data, filter); //datanýn içeriði deðiþti.
		  //Filtrelenmiþ yeni datayý, yeni bir arff dosyasýna kaydedelim
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
	
	//Bazý datasetlerde bazý özellikler class labelini etkilemez, algoritmalarýn yanlýþ çalýþmasýna sebebiyet verir
    //Bu yüzden budama seçeneði seçilerek dah efektif özellikler seçilip baþlanabilir.
	public void prunning(String file4, String file5){
		try {
			
			//load dataset
			source = new DataSource(file4);
			data = source.getDataSet();
			//Attribute selection nesnesi üretilir.
			AttributeSelection filter = new AttributeSelection();
			//Evaluator ve Search Algoritmalarý nesneleri üretilir.
			CfsSubsetEval eval = new CfsSubsetEval();
			GreedyStepwise search = new GreedyStepwise();
			//Algoritmayý geriye doðru arama yapmak için ayarlayalým.
			search.setSearchBackwards(true);
			//Filtreyi search ve evaluator algoritmalarýný kullanmasý için ayarlar
			filter.setEvaluator(eval);
			filter.setSearch(search);
			//Veri kümesini gönderelim filtreye
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
			
			//File5 ilk iki instance hariç tüm instanceslar delete edilir
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
