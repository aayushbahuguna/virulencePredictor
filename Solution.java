package virulencePredictor;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

class ML {
	double[] average;
	double[] range;
	double[] theta;

	public ML(int m, String file) throws IOException {
		File f = new File(RetweetPredictor.workingDirectory + file);
		if (f.exists() == false) {
			RetweetPredictor retweetPredictor = new RetweetPredictor(file);
		}
		Scanner st = new Scanner(new BufferedReader(new FileReader(RetweetPredictor.workingDirectory + file)));
		average = new double[m + 1];
		range = new double[m + 1];
		theta = new double[m + 1];
		for (int i = 0; i < average.length; i++) {
			average[i] = st.nextDouble();
		}
		for (int i = 0; i < range.length; i++) {
			range[i] = st.nextDouble();
		}
		for (int i = 0; i < theta.length; i++) {
			theta[i] = st.nextDouble();
		}
	}
}

public class Solution {
	public static void main(String[] args) throws IOException {
		// Features of the new tweet whose retweet count has to be predicted.
		Scanner st = new Scanner(new BufferedReader(new FileReader(RetweetPredictor.workingDirectory + "Data.txt")));
		BufferedWriter fw = new BufferedWriter(new FileWriter(RetweetPredictor.workingDirectory + "Result.txt"));
		int n = st.nextInt();
		int m = st.nextInt();
		int extraFeatures = 10;
		double[] features = new double[m + 1 + extraFeatures];
		ML someTweets = new ML(m + extraFeatures, "MLData.txt");
		Random rn = new Random();
		fw.write("Original\tPredicted\tPredictedAll");
		print(someTweets.theta);
		while (st.hasNext()) {
			features[0] = 1;
			int i = 1;
			for (; i <= m; i++) {
				features[i] = st.nextDouble();
			}
			features[i++] = features[1] * features[2];
			features[i++] = features[1] * features[3];
			features[i++] = features[1] * features[4];
			features[i++] = features[1] * features[5];
			features[i++] = features[2] * features[3];
			features[i++] = features[2] * features[4];
			features[i++] = features[2] * features[5];
			features[i++] = features[3] * features[4];
			features[i++] = features[3] * features[5];
			features[i++] = features[4] * features[5];

			int numberOfRetweets = st.nextInt();
			int x = rn.nextInt(1000);
			if (x == 0) {
				// fw.write(numberOfRetweets + "\t" + Math.max(0,predict(someTweets, features)) + "\n");
				
				System.out.println(numberOfRetweets + "\t" + predict(someTweets, features));
				
			}
		}
		fw.close();
	}

	public static int bin(int number) {
		if (number <= 0) {
			return 0;
		} else if (number < 100) {
			return 1;
		} else if (number < 10000) {
			return 2;
		} else {
			return 3;
		}
	}

	public static int predict(ML model, double[] feature) throws IOException {
		for (int i = 0; i < feature.length - 1; i++) {
			feature[i] = (feature[i] - model.average[i]) / (model.range[i]);
		}
		double predictedNumberOfRewtweets = 0;
		for (int i = 0; i < feature.length; i++) {
			predictedNumberOfRewtweets += (model.theta[i] * feature[i]);
		}
		return (int) predictedNumberOfRewtweets;
	}

	public static void print(double[] X) {
		for (int i = 0; i < X.length; i++) {
			System.out.print(X[i] + ", ");
		}
		System.out.println();
	}
}
