package virulencePredictor;

import java.io.FileNotFoundException;
import java.net.UnknownHostException;

import com.mongodb.DB;
import com.mongodb.MongoClient;

/*
 * Class uses mongodb api calls to fill an object of Training Data
 */
public class TweetDataExtractor {
	public TrainingData trainingData;
	String filePath;

	public TweetDataExtractor(String dirPath) throws FileNotFoundException {
	}

	public void extractData() throws UnknownHostException {
			MongoClient mongoClient = new MongoClient("localhost");
			DB db = mongoClient.getDB( "mydb" );
			
			
	}
}
