package virulencePredictor;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

/* Assuming Data.txt is already present this class reads Data.txt to fill object of TrainingData
 * 
 */
public class TrainingData {
	// Number of features used for tweet prediction
	int numberOfFeatures;
	// Number of samples in training data
	int numberOfSamples;
	// A numberOfSamples X numberOfFeatures training data matrix that will be
	// used to train TweetPredictor
	double[][] X;
	// Actual value of parameter for each sample of training data
	double[] y;
	
	public TrainingData(String filePath) throws FileNotFoundException {
		Scanner st = new Scanner(new BufferedReader(new FileReader(filePath)));
		this.numberOfSamples = st.nextInt();
		this.numberOfFeatures = st.nextInt();
		X = new double[numberOfFeatures][numberOfSamples];
		y = new double[numberOfSamples];
		for(int i = 0; i < numberOfSamples; i++){
			for(int j = 0; j < numberOfFeatures; j++){
				X[i][j] = st.nextDouble();
			}
			y[i] = st.nextDouble();
		}
	}
}
