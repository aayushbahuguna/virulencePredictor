package virulencePredictor;

import java.io.*;
import java.util.*;
import com.mongodb.*;
/*
 * Extract features from tweets stored in mongodb collections to a txt file.
 */
public class TweetDataExtractor {
	static String databaseName;
	static String collectionName;
	static String outputPath;

	public TweetDataExtractor(String databaseName, String collectionName, String outputPath) {
		this.databaseName = databaseName;
		this.collectionName = collectionName;
		this.outputPath = outputPath;
	}

	public static void extractFeatures() throws IOException {
		MongoClient mongoClient = new MongoClient("localhost", 27017);
		DB db = mongoClient.getDB(databaseName);
		DBCollection collection = db.getCollection(collectionName);
		BufferedWriter out = new BufferedWriter(new FileWriter(outputPath));
		out.write(String.valueOf(collection.getCount()));
		out.write("\n");
		out.close();
	}
}
