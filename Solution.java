package virulencePredictor;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Solution {
	public static void main(String[] args) {
		RetweetPredictor predictor = new RetweetPredictor();
		// Features of the new tweet whose retweet count has to be predicted.
		int[] features = new int[]{};
		System.out.println(predictor.predict(features));
	}
	
	public static int predict(int[] feature) throws FileNotFoundException {
		Scanner st = new Scanner(new BufferedReader(new FileReader("MLData.txt")));
		double[] theta = new double[1 + feature.length];
		for(int i = 0; i < theta.length; i++){
			theta[i] = st.nextDouble();
		}
		double predictedNumberOfRewtweets = 0;
		for (int i = 0; i < feature.length; i++) {
			predictedNumberOfRewtweets += (theta[i] * feature[i]);
		}
		return (int) predictedNumberOfRewtweets;
	}
}
