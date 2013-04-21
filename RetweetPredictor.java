package virulencePredictor;

import java.io.FileWriter;
import java.io.IOException;
/*
 * This class calls TweetDataExtractor to extract required data into a file "Data.txt". It then applies Machine Learning
 * algorithm Liner Regression to calculate data required for predicting number of retweets of a given tweet and stores that data into a file "MLData.txt"
 * Class provides a static function used for predicting number of retweets given the features of tweet. 
 */
public class RetweetPredictor {
	TrainingData trainingData;
	double[] theta;
	double J;
	double[] gradient;
	double alpha = 0.00000001;
	double epsilon = 0.000001;

	public RetweetPredictor() throws IOException {
		TweetDataExtractor dataExtractor = new TweetDataExtractor();		
		trainingData = new TrainingData("Data.txt");
		theta = new double[1 + trainingData.numberOfFeatures];
		J = 0;
		gradient = new double[trainingData.numberOfFeatures];
		Train();
	}
	
	public void Train() throws IOException {
		double Jo = J + 1;
		while (Math.abs(J - Jo) > epsilon) {
			Jo = J;
			J = calculateJ();
			gradientDescent();
		}
		FileWriter fw = new FileWriter("MLData.txt");
		for(int i = 0; i <= trainingData.numberOfFeatures; i++){
			fw.write(theta[i] + " ");
		}
		fw.close();
	}

	public void gradientDescent() {
		for (int i = 0; i < trainingData.numberOfFeatures; i++) {
			gradient[i] = 0;
		}
		for (int j = 0; j < trainingData.numberOfFeatures; j++) {
			for (int i = 0; i < trainingData.numberOfSamples; i++) {
				gradient[j] += (h(trainingData.X[i]) - trainingData.y[i]) * trainingData.X[i][j];
			}
			gradient[j] /= trainingData.numberOfSamples;
		}
		for (int i = 0; i < trainingData.numberOfFeatures; i++) {
			theta[i] = theta[i] - alpha * gradient[i];
		}
	}

	double calculateJ() {
		double totalCost = 0;
		for (int i = 0; i < trainingData.numberOfSamples; i++) {
			totalCost += Math.pow(h(trainingData.X[i]) - trainingData.y[i], 2);
		}
		return totalCost / (2 * trainingData.numberOfSamples);
	}

	public double h(double[] X) {
		double hx = 0;
		for (int i = 0; i < theta.length; i++) {
			hx += theta[i] * X[i];
		}
		return hx;
	}

	public static void print(double[] X) {
		for (int i = 0; i < X.length; i++) {
			System.out.print(X[i] + ", ");
		}
		System.out.println();
	}

}
