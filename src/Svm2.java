
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import static java.lang.System.out;
import java.util.ArrayList;
import java.util.Enumeration;
import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.classifiers.evaluation.NominalPrediction;
import weka.classifiers.evaluation.Prediction;
import weka.classifiers.functions.SMO;
import weka.classifiers.functions.supportVector.PrecomputedKernelMatrixKernel;
import weka.core.DenseInstance;
import weka.core.Instance;
import weka.core.Instances;
import weka.classifiers.functions.supportVector.PolyKernel;
import weka.classifiers.functions.supportVector.RBFKernel;

public class Svm2 {

  

    public BufferedReader readDataFile(String filename) {
        BufferedReader inputReader = null;
        try {
            inputReader = new BufferedReader(new FileReader(filename));
        } catch (FileNotFoundException ex) {
            out.println("File not found: " + filename);
        }
        return inputReader;
    }


    public void Main() {
        
        double[] cValues = new double[] { 0.1, 1, 3, 5, 10, 50, 100 };
        double[] gammaValues = new double[] { -9, -7, -5, -3, -1, 1, 3 ,10,50,100};
        double[] exponentValues = new double[] { 1, 2, 3, 4, 5, 10, 20 ,30,50,100};
        double[] erruers = new double[]{0,0,0,0,0,0} ;
        double erreur=0;
        ArrayList<myclass> my =new ArrayList<>();
        try {
            BufferedReader datafile;
            datafile = readDataFile("D:\\M1 MID\\S2\\tp svm\\diabetes.arff");
            Instances data = new Instances(datafile);
            data.setClassIndex(data.numAttributes() - 1);
            System.out.println("nombre d'attributs="+data.numAttributes()+", taille du dataset="+data.size());
            Instances trainingData = new Instances(data, 0, 200);
            Instances testingData1 = new Instances(data, 200, 300);
             Instances testingData2 = new Instances(data, 500, 268);
            System.out.println("size traindataset:"+trainingData.size()+"size testdataset:"+testingData1.size());
           //System.out.println("testt"+ testingData.toString());
            Evaluation evaluation = new Evaluation(trainingData);
     
            SMO smo = new SMO();
            //regularisateur
           // smo.setC(cValues[3]);
           // plynomial kernel
            PolyKernel kernel = new PolyKernel();
        for(int m =0;m<cValues.length;m++){
            smo.setC(cValues[m]);
            myclass my2=new myclass(cValues[m]);
            for(int i=0;i<exponentValues.length;i++){
                System.out.print("i="+i+" ");
                my2.add_E(exponentValues[i]);
                erreur=0;
                kernel.setExponent(exponentValues[i]);
              ((SMO) smo).setKernel(kernel);
                // RBF kernel
                /*
                RBFKernel kernel1=new RBFKernel();
                ((RBFKernel) kernel1).setGamma(gammaValues[7]);
                ((SMO) smo).setKernel(kernel1);*/

                smo.buildClassifier(trainingData);

                /*evaluation.evaluateModel(smo, trainingData);
                System.out.println(evaluation.toSummaryString());
                evaluation.evaluateModel(smo, testingData1);
                System.out.println(evaluation.toSummaryString());*/

                // Test instance 
                  //System.out.println("prediction d'une instance personalisée");
                /*  Instance instance = new DenseInstance(8);
                instance.setValue(data.attribute("preg"), 5);
                instance.setValue(data.attribute("plas"), 100);
                instance.setValue(data.attribute("pres"), 60);    
                instance.setValue(data.attribute("skin"), 0); 
                instance.setValue(data.attribute("insu"), 100); 
                instance.setValue(data.attribute("mass"), 35); 
                instance.setValue(data.attribute("pedi"), 0.33); 
                instance.setValue(data.attribute("age"), 50); 
                instance.setDataset(data);*/
                //System.out.println("The instance: " + instance);
                //System.out.println("classe de l'instance:"+smo.classifyInstance(instance));
                //System.out.println("prediction sur base de test 2");
                Enumeration enume =testingData1.enumerateInstances();
                int k=0;

                while(enume.hasMoreElements()){

                    Instance ind=(Instance)enume.nextElement();
 
                    double c=smo.classifyInstance(ind);
                    //System.out.println("prediction de l'nstance numero: "+k+"="+c+", l'etiquette de l'expert "+ind.classValue());
                    k++;
                    if (c!=ind.classValue()) erreur++;
                }

                erreur=erreur/k;
                my2.add_erreur(erreur);
                    /*System.out.println("erreur "+erreur);
                    erruers[i]=erreur;*/

            }//end for2
            System.out.println("Erreur minimal ="+my2.min()+" Exp="+my2.get_E()+" C="+my2.C);
            my.add(my2);
        }//end for1
        
        double min=my.get(0).min();
        int indice=0;
        for(int i=0;i<my.size();i++){
            if(min>my.get(i).min()) {
                min=my.get(i).min();
                indice=i;
            }
        }
            System.out.println("Erreur minimal ="+min+" Exp="+my.get(indice).get_E()+" C="+my.get(indice).C);
            
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public static void main(String[] args) {
        // TODO code application logic here
        Svm2 x=new Svm2();
        x.Main();
    }
    
}
