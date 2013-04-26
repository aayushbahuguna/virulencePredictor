package virulencePredictor;

import java.io.FileWriter;
import java.io.IOException;
import java.io.File;

/*
 * This class reads the required features from the file "Data.txt"
 * It then applies ML algorithm Liner Regression to calculate
 * data required for predicting number of retweets of a
 * given tweet and stores that data into a file "MLData.txt"
 * Class provides a static function used for predicting number of
 * retweets given the features of tweet. 
 */
public class RetweetPredictor {
	TrainingData trainingData;
	double[] theta;
	double J;
	double[] gradient;
	double alpha = 2;
	double epsilon = 100;

	public RetweetPredictor(String file) throws IOException {
		trainingData = new TrainingData("Data.txt");
		theta = new double[1 + trainingData.numberOfFeatures];
		J = 0;
		gradient = new double[trainingData.numberOfFeatures];
		train();
		writeToFile(file);
	}

	public void train() throws IOException {
		double Jo = J + 100;
		while (Math.abs(J - Jo) >= epsilon) {
			 System.out.println((J - Jo) + " " + (Math.abs(J - Jo) > epsilon ? "true" : "false"));
			Jo = J;
			J = calculateJ();
			gradientDescent();
		}
	}

	public void writeToFile(String file) throws IOException {
		FileWriter fw = new FileWriter(file);
		for (int i = 0; i < 1 + trainingData.numberOfFeatures; i++) {
			fw.write(trainingData.average[i] + " ");
		}
		fw.write("\n");
		for (int i = 0; i < 1 + trainingData.numberOfFeatures; i++) {
			fw.write(trainingData.range[i] + " ");
		}
		fw.write("\n");
		for (int i = 0; i < 1 + trainingData.numberOfFeatures; i++) {
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
}
