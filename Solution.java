package virulencePredictor;

public class Solution {
	public static void main(String[] args) {
		RetweetPredictor predictor = new RetweetPredictor(6, 10);
		int[] features = new int[]{};
		System.out.println(predictor.predict(features));
		

	}

}
