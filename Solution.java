package virulencePredictor;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Solution {
	public static void main(String[] args) throws IOException {
		// Features of the new tweet whose retweet count has to be predicted.
		double[] features = new double[5];
		Scanner st = new Scanner(System.in);
		while(true){
			for(int i = 0; i < 5; i++){
				features[i] = st.nextDouble();
			}
			System.out.println(predict(features));
		}
	}
	
	public static int predict(double[] feature) throws IOException {
		//RetweetPredictor retweetPredictor = new RetweetPredictor();
		File f = new File("C://Users//Aayush//Dropbox//workspace//workspaceWindows//AlgorithmJava//IIT Ropar//virulencePredictor//MLData.txt");
		if(f.exists() == false){
			RetweetPredictor retweetPredictor = new RetweetPredictor();
		}
		Scanner st = new Scanner(new BufferedReader(new FileReader("C://Users//Aayush//Dropbox//workspace//workspaceWindows//AlgorithmJava//IIT Ropar//virulencePredictor//MLData.txt")));
		double[] average = new double[1 + feature.length];
		double[] range = new double[1 + feature.length];
		double[] theta = new double[1 + feature.length];
		for(int i = 0; i <= feature.length; i++){
			average[i] = st.nextDouble();
		}
		for(int i = 0; i <= feature.length; i++){
			range[i] = st.nextDouble();
		}
		for(int i = 0; i <= feature.length; i++){
			theta[i] = st.nextDouble();
		}
		for(int i = 0; i < feature.length; i++){
			feature[i] = (feature[i] - average[i + 1]) / (range[i + 1]);
		}
		/*System.out.println("AVERAGE");
		print(average);
		System.out.println("RANGE");
		print(range);
		System.out.println("FEATURES");
		print(feature);
		System.out.println("THETA");
		print(theta);*/
		double predictedNumberOfRewtweets = 0;
		for (int i = 0; i < feature.length; i++) {
			predictedNumberOfRewtweets += (theta[i] * feature[i]);
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
