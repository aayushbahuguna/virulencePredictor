package virulencePredictor;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;

/*
 * Class uses mongodb api calls to fill an object of Training Data
 */
public class TweetDataExtractor {
	static String databaseName;
	static String collectionName;
	static String outputPath;
	
	public static void main(String[] args) throws IOException{
		databaseName = "Tech";
		collectionName = "tweets";
		outputPath = "C:\\Users\\Aayush\\Desktop\\virulencePredictor\\FeaturesData.txt";		
		extractFeatures();
	}

	public static void extractFeatures() throws IOException {
		MongoClient mongoClient = new MongoClient("localhost", 27017);
		DB db = mongoClient.getDB(databaseName);
		DBCollection collection = db.getCollection(collectionName);
		BufferedWriter out = new BufferedWriter(new FileWriter(outputPath));
		out.write("" + collection.getCount());
		out.write("\n");
		out.close();
	}	
}