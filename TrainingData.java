package virulencePredictor;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

/*
 * Assuming Data.txt is already present, this class reads
 * Data.txt to fill object of TrainingData
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
	double[] average;
	double[] range;

	public TrainingData(String filePath) throws IOException {
		System.out.println("Starting Reading Data");
		Scanner st = new Scanner(new BufferedReader(new FileReader(filePath)));
		this.numberOfSamples = st.nextInt();
		int originalFeatures = st.nextInt();
		int extraFeatures = (originalFeatures*(originalFeatures - 1))/2;
		this.numberOfFeatures = originalFeatures + extraFeatures;
		X = new double[numberOfSamples][1 + numberOfFeatures];
		y = new double[numberOfSamples];
		average = new double[1 + numberOfFeatures];
		range = new double[1 + numberOfFeatures];
		for (int i = 0; i < numberOfSamples; i++) {
			X[i][0] = 1;
			double numberOfFollowers = st.nextDouble();
			X[i][1] = numberOfFollowers;
			int j = 2;
			for (; j < 1 + numberOfFeatures - extraFeatures; j++) {
				X[i][j] = st.nextDouble();
			}
			for (int k = 1; k <= originalFeatures; k++) {
				for (int l = k+1; l <= originalFeatures; l++) {
					X[i][j++] = X[i][k] * X[i][l];
				}
			}
			y[i] = st.nextDouble();
		}
		System.out.println("Done Reading Data");
		System.out.println("Starting Normalization");
		normalizeFeatures();
		System.out.println("Done Normalizing Data");
	}

	public void normalizeFeatures() throws IOException {
		average[0] = 1;
		range[0] = 1;
		for (int j = 1; j <= numberOfFeatures; j++) {
			double avg = 0;
			double max = Integer.MIN_VALUE, min = Integer.MAX_VALUE;
			for (int i = 0; i < numberOfSamples; i++) {
				avg = avg + X[i][j] / numberOfSamples;
				max = Math.max(X[i][j], max);
				min = Math.min(X[i][j], min);
			}
			average[j] = avg;
			range[j] = Math.max((max - min), 1);
			for (int i = 0; i < numberOfSamples; i++) {
				X[i][j] = (X[i][j] - average[j]) / range[j];
			}
		}
	}

	public void print() {
		for (int i = 0; i < X.length; i++) {
			for (int j = 0; j < X[0].length; j++) {
				System.out.print(X[i][j] + " ");
			}
			System.out.println(y[i]);
			System.out.println();
		}
	}
}
