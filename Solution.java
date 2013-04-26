package virulencePredictor;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
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

	// m is the number of features.
	// file is the file which contains average, range and theta for all features.
	public ML(int m, String file) throws IOException {
		File f = new File(file);
		if (f.exists() == false) {
			// It uses the features extracted from the tweets to find
			// average, range and theta for all features. It then stores
			// these to the file.
			RetweetPredictor retweetPredictor = new RetweetPredictor(file);
		}
		Scanner st = new Scanner(new BufferedReader(new FileReader(file)));
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
		System.out.println("Should I segregate the results in buckets (y/n)");
		Scanner s = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
		String segregate = s.next();
		Scanner st = new Scanner(new BufferedReader(new FileReader("Data.txt")));
		BufferedWriter fw = new BufferedWriter(new FileWriter("Result.txt"));
		// n is the number of tweets.
		// m is the number of features extracted directly from the tweets.
		int n = st.nextInt();
		int m = st.nextInt();
		int extraFeatures = (m*(m-1))/2;
		double[] features = new double[m + 1 + extraFeatures];
		ML someTweets = new ML(m + extraFeatures, "MLData.txt");
		Random rn = new Random();
		fw.write("Original\tPredicted\n");
		while (st.hasNext()) {
			int prob = rn.nextInt(1000);
			if (prob == 0) {
				features[0] = 1;
				int i = 1;
				for (; i <= m; i++) {
					features[i] = st.nextDouble();
				}
				for (int x = 1; x <= m; x++) {
					for (int y = x+1; y <=m; y++) {
						features[i++] = features[x]*features[y];
					}
				}
				if (segregate.equals("y") || segregate.equals("Y")) {
					int numberOfRetweets = bin(st.nextInt());
					int predicted = bin(Math.max(0, predict(someTweets, features)));
					fw.write(numberOfRetweets + "\t" + predicted + "\n");
					System.out.println(numberOfRetweets + "\t" + predicted);
					st.nextLine();
				} else {
					int numberOfRetweets = st.nextInt();
					int predicted = Math.max(0, predict(someTweets, features));
					fw.write(numberOfRetweets + "\t" + predicted + "\n");
					System.out.println(numberOfRetweets + "\t" + predicted);
					st.nextLine();
				}
			} else {
				st.nextLine();	// Skip this line.
			}
		}
		fw.close();
	}

	public static int bin(int number) {
		if (number <= 0) {
			return 0;
		} else if (number < 100) {
			return 1;
		} else if (number < 1000) {
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
