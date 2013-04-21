package virulencePredictor;

import java.io.File;
import java.net.UnknownHostException;
import java.util.Scanner;

import com.mongodb.*;

/*
 * Class uses mongodb api calls to fill an object of Training Data
 */
public class TweetDataExtractor {
	public TrainingData trainingData;
	String filePath;

	public TweetDataExtractor(int numberOfFeatures, int numberOfSamples) {
		trainingData = new TrainingData(numberOfFeatures, numberOfSamples);
	}

	public void fillData() throws UnknownHostException {
		// If we have already extracted data once we can just read it from file. We don't need to extract data using mongodb api
		File file = new File(filePath);
		if (file.exists()) {
			// Read from file and fill trainingData
			Scanner st = new Scanner("Data.txt");
			
			
		} else {
			// Extract data from mongodb and write useful data to file
			extractData();
			writeToFile();
		}
	}

	public void extractData() {
			MongoClient mongoClient = new MongoClient("localhost");
			DB db = mongoClient.getDB( "mydb" );
			
	}

	public void writeToFile() {
	}
}
