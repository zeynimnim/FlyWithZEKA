package weka.api;

import java.util.Random;
import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;

import weka.classifiers.Evaluation;
import weka.classifiers.Classifier;
import weka.classifiers.trees.J48;
import weka.classifiers.rules.ZeroR;
//import weka.classifiers.bayes.NaiveBayes;
import weka.classifiers.rules.OneR;
import weka.classifiers.functions.Logistic;
import weka.classifiers.lazy.IBk;
import weka.filters.supervised.attribute.AttributeSelection;
import weka.attributeSelection.CfsSubsetEval;
import weka.attributeSelection.GreedyStepwise;
import weka.filters.Filter;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.ArffSaver;
import weka.core.converters.ConverterUtils.DataSource;
import java.io.File;
import java.text.DecimalFormat;

public class Algorithma {
	
	private String algorithmName;
	private DataSource source;
	private Instances data;
	private Instances trainInstances;
	private Instances testInstances;
	private ArffSaver saver = new ArffSaver();
	private Random rand = new Random(1);
	private int folds = 10;
	private Evaluation eval;
	private String model;
	private String probability;
	private String predString;
	private Classifier obj ;
	private DecimalFormat df = new DecimalFormat("##.###"); 
	private double predNB;
	
	public String getName() {
		return algorithmName;
	}
	
    public String getModel() {
    	return model;
    }
	
	public void createAlgorithm(int x, String file5) {
		
		try {
			
			source = new DataSource(file5);
			trainInstances = source.getDataSet(); 
			trainInstances.setClassIndex(trainInstances.numAttributes()-1);
			eval= new Evaluation(trainInstances);
			//Test datas� ��renilen datan�n 10 par�aya ayr�l�p random al�nmas�yla olu�turulacak
			testInstances = source.getDataSet();
			testInstances.setClassIndex(testInstances.numAttributes()-1);
			
			
			switch (x) {
			case 1:{
				startZeroR(source,trainInstances);
				algorithmName = "ZeroR"; 
				break;
			}
			case 2:{
				startJ48(source,trainInstances);
				algorithmName = "J48 Decision Tree"; 
				break;
			}		
			case 3:{
				startOneR(source,trainInstances);
				algorithmName = "OneR"; 
				break;
			}	
			case 4:{
				startLogistic(source,trainInstances);
				algorithmName = "Logistic Regression"; 
				break;
			}	
			case 5: {
				startIBk(source,trainInstances);
				algorithmName = "IBk knn"; 
				break;
			}
			default:
				break;
				
		   }
			
			
		}catch(Exception e) {
			System.out.println("There can be two possible reasons");
			System.out.println("Input: Data source file not found");
			System.out.println("Output: There is already such a file. ");
			e.printStackTrace();
		}
		
		
		
	}
	
	public void testData(String file,int x) {
		
			
		
		try {	
	
	
			//A�a��daki sat�rlar�n amac� dosyada manuel test datas�n� test etmektir	
			switch (x) {
			case 1: {
				ZeroR zeroR = (ZeroR)weka.core.SerializationHelper.read(model); 
				source = new DataSource(file);
				Instances testDataset = source.getDataSet();     	
		      	testDataset.setClassIndex(testInstances.numAttributes()-1);
				
					for(int i = 0; i < testDataset.numInstances(); i++) {
						double actualClass = testDataset.instance(i).classValue();
						String actual = testDataset.classAttribute().value((int) actualClass);
						Instance newInst = testDataset.instance(i);
					    predNB = zeroR.classifyInstance(newInst);
						predString = testDataset.classAttribute().value((int)predNB);
						
					}
					
				break;
			}
			case 2: {
				J48 tree = (J48)weka.core.SerializationHelper.read(model); 
				source = new DataSource(file);
				Instances testDataset = source.getDataSet();     	
		      	testDataset.setClassIndex(testInstances.numAttributes()-1);
				
					for(int i = 0; i < testDataset.numInstances(); i++) {
						double actualClass = testDataset.instance(i).classValue();
						String actual = testDataset.classAttribute().value((int) actualClass);
						Instance newInst = testDataset.instance(i);
						predNB = tree.classifyInstance(newInst);
						predString = testDataset.classAttribute().value((int)predNB);
						
					}
				break;
			}/*
			case 3: {
				NaiveBayes nb = (NaiveBayes)weka.core.SerializationHelper.read(model); 
				source = new DataSource(file);
				Instances testDataset = source.getDataSet();     	
		      	testDataset.setClassIndex(testInstances.numAttributes()-1);
				System.out.println("=====================");
				System.out.println("Actual class," + algorithmName + " predicted");	
					for(int i = 0; i < testDataset.numInstances(); i++) {
						double actualClass = testDataset.instance(i).classValue();
						String actual = testDataset.classAttribute().value((int) actualClass);
						Instance newInst = testDataset.instance(i);
						double predNB = nb.classifyInstance(newInst);
						String predString = testDataset.classAttribute().value((int)predNB);
						System.out.println(actual + ", " + predString);
					}
				break;
			}*/
			case 3: {
				OneR oneR = (OneR)weka.core.SerializationHelper.read(model);
				source = new DataSource(file);
				Instances testDataset = source.getDataSet();     	
		      	testDataset.setClassIndex(testInstances.numAttributes()-1);
				
					for(int i = 0; i < testDataset.numInstances(); i++) {
						double actualClass = testDataset.instance(i).classValue();
						String actual = testDataset.classAttribute().value((int) actualClass);
						Instance newInst = testDataset.instance(i);
					    predNB = oneR.classifyInstance(newInst);
						predString = testDataset.classAttribute().value((int)predNB);
					}
				break;
		     	}
			case 4: {
				Logistic logistic = (Logistic)weka.core.SerializationHelper.read(model); 
				source = new DataSource(file);
				Instances testDataset = source.getDataSet();     	
		      	testDataset.setClassIndex(testInstances.numAttributes()-1);
	
					for(int i = 0; i < testDataset.numInstances(); i++) {
						double actualClass = testDataset.instance(i).classValue();
						String actual = testDataset.classAttribute().value((int) actualClass);
						Instance newInst = testDataset.instance(i);
						predNB = logistic.classifyInstance(newInst);
						predString = testDataset.classAttribute().value((int)predNB);
	
					}
				break;
			}
			case 5: { 
				IBk knn = (IBk)weka.core.SerializationHelper.read(model); 
				source = new DataSource(file);
				Instances testDataset = source.getDataSet();     	
		      	testDataset.setClassIndex(testInstances.numAttributes()-1);
				
					for(int i = 0; i < testDataset.numInstances(); i++) {
						double actualClass = testDataset.instance(i).classValue();
						String actual = testDataset.classAttribute().value((int) actualClass);
						Instance newInst = testDataset.instance(i);
						predNB = knn.classifyInstance(newInst);
						predString = testDataset.classAttribute().value((int)predNB);
						
					}
				break;
			}
			default:
				break;
				
			}	
		      
			
			}
			catch (Exception e) {
					System.out.println("There can be two possible reasons");
					System.out.println("Input: Data source file not found");
					System.out.println("Output: There is already such a file. ");
					e.printStackTrace();
				
			}
		
		
		
	}
	
	
	public void showEvaluation() throws Exception {

	    //A�a��daki sat�r�n amac� algoritman�n de�erlendirilmesidir  
	    
	    System.out.println("========== " + algorithmName + " ========= \n");
	    System.out.println("Algoritma de�erlendirme sonu�lar� a�a��da g�steriliyor: \n");
		System.out.println(eval.toSummaryString("Evaluation results: \n",false));
	    System.out.println(eval.toMatrixString("Overall Confusion Matrix"));
	}
		
	
	private void startZeroR(DataSource source,Instances trainInstances) {
		
		//ZeroR nesnesi olu�turulur
		try {
		ZeroR zeroR = new ZeroR();
		algorithmName = "ZeroR";
		
		//A�a��daki iki sat�r�n amac� algoritman�n ��renilmi� modelini kaydetmektir
	    zeroR.buildClassifier(trainInstances);
	    
	    weka.core.SerializationHelper.write("/Users/Zeynep/eclipse-workspace/African_Crisis/documents/ZeroR.model",zeroR);
	    model = "/Users/Zeynep/eclipse-workspace/African_Crisis/documents/ZeroR.model";
	    
	    eval.crossValidateModel(zeroR, testInstances, folds, rand); 
	    probability =  df.format(eval.pctCorrect());
	    
		}
		catch(Exception e) {
			System.out.println("There can be two possible reasons");
			System.out.println("Input: Data source file not found");
			System.out.println("Output: There is already such a file. ");
			e.printStackTrace();
		}
		
		
	}
	
	private void startJ48(DataSource source,Instances trainInstances) {
		
		//J48 nesnesi olu�turulur
				try {
				J48 tree = new J48();
				algorithmName = "J48 Decision Tree";
				//J48 ayarlar� yap�l�r
				String[] options = new String[1];
                options[0]= "-B"; // A�a�ta sadece ikili ayr�m yap�laca�� zaman bu ayarlanabilir
				tree.setOptions(options);
				
		//A�a��daki iki sat�r�n amac� algoritman�n ��renilmi� modelini kaydetmektir
			    tree.buildClassifier(trainInstances);
			    
			   
			    weka.core.SerializationHelper.write("/Users/Zeynep/eclipse-workspace/African_Crisis/documents/J48.model",tree);     
			   
			    model = "/Users/Zeynep/eclipse-workspace/African_Crisis/documents/J48.model"; 
			   
			  
			  
	
		//A�a��daki sat�r�n amac� algoritman�n de�erlendirilmesidir  
			    eval.crossValidateModel(tree, testInstances, folds, rand);	 
			    probability =  df.format(eval.pctCorrect());
			    
				}
				catch(Exception e) {
					System.out.println("There can be two possible reasons");
					System.out.println("Input: Data source file not found");
					System.out.println("Output: There is already such a file. ");
					e.printStackTrace();
				}
				
			
	}/*
	private void startNaiveBayes(DataSource source,Instances trainInstances) {
		//Naive Bayes nesnesi olu�turulur
				try {
				NaiveBayes nb = new NaiveBayes();
				algorithmName = "Naive Bayes";
				
		//A�a��daki iki sat�r�n amac� algoritman�n ��renilmi� modelini kaydetmektir
			    nb.buildClassifier(trainInstances);
			    weka.core.SerializationHelper.write("/Users/Zeynep/eclipse-workspace/African_Crisis/documents/NaiveBayes.model",nb);
			    model = "/Users/Zeynep/eclipse-workspace/African_Crisis/documents/NaiveBayes.model";
			  
				
		//A�a��daki sat�r�n amac� algoritman�n de�erlendirilmesidir  
			    eval.crossValidateModel(nb, testInstances, folds, rand);
			    System.out.println("========== " + algorithmName + " ========= \n");
				System.out.println(eval.toSummaryString("Evaluation results: \n",false));
			    System.out.println(eval.toMatrixString("Overall Confusion Matrix"));	
			    DecimalFormat df = new DecimalFormat("##.###");  
			    System.out.println("Algoritman�n do�ru �al��ma oran�: %" + df.format(eval.pctCorrect()));
			    
				}
				catch(Exception e) {
					System.out.println("There can be two possible reasons");
					System.out.println("Input: Data source file not found");
					System.out.println("Output: There is already such a file. ");
					e.printStackTrace();
				}
	
	}*/
	
	private void startOneR(DataSource source,Instances trainInstances) {
		//OneR nesnesi olu�turulur
		try {
		OneR oneR = new OneR();
		algorithmName = "OneR";
		
//A�a��daki iki sat�r�n amac� algoritman�n ��renilmi� modelini kaydetmektir
	    oneR.buildClassifier(trainInstances);
	    weka.core.SerializationHelper.write("/Users/Zeynep/eclipse-workspace/African_Crisis/documents/OneR.model",oneR);
	    model = "/Users/Zeynep/eclipse-workspace/African_Crisis/documents/OneR.model";
	  
		
//A�a��daki sat�r�n amac� algoritman�n de�erlendirilmesidir  
	    eval.crossValidateModel(oneR, testInstances, folds, rand);
	    probability =  df.format(eval.pctCorrect());	
	    
		}
		catch(Exception e) {
			System.out.println("There can be two possible reasons");
			System.out.println("Input: Data source file not found");
			System.out.println("Output: There is already such a file. ");
			e.printStackTrace();
		}
	
	}
	private void startLogistic(DataSource source,Instances trainInstances) {
		//Logistic Regression nesnesi olu�turulur
				try {
				Logistic logistic = new Logistic();
				algorithmName = "Logistic Regression";
				
		//A�a��daki iki sat�r�n amac� algoritman�n ��renilmi� modelini kaydetmektir
			    logistic.buildClassifier(trainInstances);
			    weka.core.SerializationHelper.write("/Users/Zeynep/eclipse-workspace/African_Crisis/documents/Logistic.model",logistic);
			    model = "/Users/Zeynep/eclipse-workspace/African_Crisis/documents/Logistic.model";
			  
				
		//A�a��daki sat�r�n amac� algoritman�n de�erlendirilmesidir  
			    eval.crossValidateModel(logistic, testInstances, folds, rand);
			    probability =  df.format(eval.pctCorrect());
			    
				}
				catch(Exception e) {
					System.out.println("There can be two possible reasons");
					System.out.println("Input: Data source file not found");
					System.out.println("Output: There is already such a file. ");
					e.printStackTrace();
				}
			
	
	}
	private void startIBk(DataSource source,Instances trainInstances) {
		//IBk nesnesi olu�turulur
		try {
		IBk knn = new IBk();
		algorithmName = "IBk-knn";
		
//A�a��daki iki sat�r�n amac� algoritman�n ��renilmi� modelini kaydetmektir
	    knn.buildClassifier(trainInstances);
	    weka.core.SerializationHelper.write("/Users/Zeynep/eclipse-workspace/African_Crisis/documents/IBk.model",knn);
	    model = "/Users/Zeynep/eclipse-workspace/African_Crisis/documents/IBk.model";
	  
		
//A�a��daki sat�r�n amac� algoritman�n de�erlendirilmesidir  
	    eval.crossValidateModel(knn, testInstances, folds, rand);
	    probability =  df.format(eval.pctCorrect());
	    
		}
		catch(Exception e) {
			System.out.println("There can be two possible reasons");
			System.out.println("Input: Data source file not found");
			System.out.println("Output: There is already such a file. ");
			e.printStackTrace();
		}
			
		
		
	
   }

	public String getProb() {
		// TODO Auto-generated method stub
		return probability;
	}

	public String getPredString() {
		
		return predString;
	}

	
}
