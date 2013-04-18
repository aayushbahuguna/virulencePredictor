package virulencePredictor;


public class RetweetPredictor {
	int numberOfFeatures;
	int numberOfSamples;
	TweetDataExtractor tweetDataExtractor;
	TrainingData trainingData;
	double[] theta;
	double J;
	double[] gradient;
	double alpha = 0.00000001;
	double epsilon = 0.000001;

	public RetweetPredictor(int numberOfFeatures, int numberOfSamples) {
		this.numberOfFeatures = numberOfFeatures;
		this.numberOfSamples = numberOfSamples;
		tweetDataExtractor = new TweetDataExtractor(numberOfFeatures, numberOfSamples);
		trainingData = tweetDataExtractor.trainingData;
		theta = new double[1 + trainingData.numberOfFeatures];
		J = 0;
		gradient = new double[trainingData.numberOfFeatures];
	}

	public int predict(int[] feature) {
		double predictedNumberOfRewtweets = 0;
		for (int i = 0; i < feature.length; i++) {
			predictedNumberOfRewtweets += (theta[i] * feature[i]);
		}
		return (int) predictedNumberOfRewtweets;
	}

	public void Train() {
		double Jo = J + 1;
		while (Math.abs(J - Jo) > epsilon) {
			Jo = J;
			J = calculateJ();
			gradientDescent();
		}
	}

	public void gradientDescent() {
		for (int i = 0; i < numberOfFeatures; i++) {
			gradient[i] = 0;
		}
		for (int j = 0; j < numberOfFeatures; j++) {
			for (int i = 0; i < numberOfSamples; i++) {
				gradient[j] += (h(trainingData.X[i]) - trainingData.y[i]) * trainingData.X[i][j];
			}
			gradient[j] /= numberOfSamples;
		}
		for (int i = 0; i < numberOfFeatures; i++) {
			theta[i] = theta[i] - alpha * gradient[i];
		}
	}

	double calculateJ() {
		double totalCost = 0;
		for (int i = 0; i < numberOfSamples; i++) {
			totalCost += Math.pow(h(trainingData.X[i]) - trainingData.y[i], 2);
		}
		return totalCost / (2 * numberOfSamples);
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
