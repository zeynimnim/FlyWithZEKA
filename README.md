# FlyWithZEKA - ZEKA
## Zeynep Environment for Knowledge Analysis

About My Financial Project:  ZEKA – Zeynep Environment for Knowledge Analysis

I wanted to combine my intense interest in the economy with my computer knowledge. "African Economic Bank Systemic Crisis Data" attracted my attention among the databases on the site named Kaggle. In this dataset, the African countries have features like above  between 1950 and 2014:
is there a systemic crisis?
is there any domestic debt?
is there any foreign debt?
Is there inflation?
is there independence?
etc. It had 12 features. In Class, it was marked whether there was an economic crisis in the country that year. My goal is to tell the possibility whether it will be experiencing an economic crisis this year with the knowledge I have learned from previous years,.

I converted all of the features to 0/1 binary type so that all algorithms can work correctly. I created a Java eclipse project, I imported the WEKA library, and examined how many percent of the zeroR, j48 decision tree, oneR, logistic regression, knn algorithms result in this dataset.

ZeroR gave 91.123% accuracy, which means that the rate of no_crisis in the data (don’t have economic crisis) is 91.123% without any learning. 

OneR gave 97.733% accuracy. Since the economic crisis is inevitable when there is a systemic crisis, the oneR algorithm has worked so well. Because the OneR algorithm includes determining the class according to a single feature.

Logistic Regression gave 98.111% accuracy which is the best result among these 5 algorithms. Because this algorithm has also taken the effects of features other than the systemic crisis feature (of course, the coefficient of this feature is the biggest). 

So I choose logistic regression, and I can predict that an African country will suffer or will not suffer an economic crisis this year with a probability of 98.111%.

#### Please download African_Crisis zip, in this file you will African_Crisis/documents/.. theese: 

 ###### .settings
 ###### bin
###### documents:
 african_crisis (csv file),
 ikon (jpg fot GUI),
 okubeni (readMe)
###### src:
 weka (java project),
 african_crisis (csv file),
 ikon (jpg fot GUI)




