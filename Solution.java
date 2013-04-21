package virulencePredictor;

public class Solution {
	public static void main(String[] args) {
		RetweetPredictor predictor = new RetweetPredictor(6, 10);

		// Features of the new tweet whose retweet count has to be predicted.
		int[] features = new int[]{};
		System.out.println(predictor.predict(features));

	}
}
