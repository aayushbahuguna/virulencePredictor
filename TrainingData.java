package virulencePredictor;

public class TrainingData {
	// Number of features used for tweet prediction
	public int numberOfFeatures;
	// Number of samples in training data
	public int numberOfSamples;
	// A numberOfSamples X numberOfFeatures training data matrix that will be
	// used to train TweetPredictor
	public double[][] X;
	// Actual value of parameter for each sample of training data
	public double[] y;

	public TrainingData(int numberOfFeatures, int numberOfSamples) {
		this.numberOfFeatures = numberOfFeatures;
		this.numberOfSamples = numberOfSamples;
		X = new double[numberOfFeatures][numberOfSamples];
		y = new double[numberOfSamples];
	}
}
