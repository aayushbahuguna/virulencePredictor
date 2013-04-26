package virulencePredictor;

import java.io.*;
import java.util.*;
import com.mongodb.*;

/*
 * Extract features from tweets stored in mongodb collections to a txt file.
 */
public class TweetDataExtractor {
	String databaseName;
	String collectionName;
	String outputPath;

	public TweetDataExtractor(String databaseName, String collectionName, String outputPath) {
		this.databaseName = databaseName;
		this.collectionName = collectionName;
		this.outputPath = outputPath;
	}

	public static void main(String[] args) throws IOException {
		File f = new File("Data.txt");
		if (!f.exists()) {
			System.out.println("Data Extraction started");
			TweetDataExtractor dataExtractor = new TweetDataExtractor("Tech", "tweets", "Data.txt");
			dataExtractor.extractFeatures();
			System.out.println("Done with data extraction");
		} else {
			System.out.println("Data.txt already exists");
			System.out.println("Please delete Data.txt if you want to extract features from mongodb database.");
			System.out.println("This is in place to avoid accedental overwrite of Data.txt");
		}
	}

	public void extractFeatures() throws IOException {
		MongoClient mongoClient = new MongoClient("localhost", 27017);
		DB db = mongoClient.getDB(databaseName);
		DBCollection collection = db.getCollection(collectionName);
		BufferedWriter out = new BufferedWriter(new FileWriter(outputPath));
		int count = Integer.parseInt(String.valueOf(collection.getCount()));
		out.write(count + " " + 5);
		out.write("\n");
		DBCursor cursor = collection.find();
		try {
			while (cursor.hasNext()) {
				DBObject tweet = cursor.next();
				DBObject user = (DBObject) tweet.get("user");

				out.write(user.get("followers_count") + " ");

				if ((Boolean) (user.get("verified")).equals(false)) {
					out.write(0 + " ");
				} else {
					out.write(1 + " ");
				}

				out.write(user.get("listed_count") + " ");

				String time = ((String) tweet.get("created_at")).split("\\s+")[3];
				time = time.replaceAll(":", "");
				out.write(time + " ");

				if (tweet.get("in_reply_to_user_id") == null) {
					out.write(1 + " ");
				} else {
					out.write(0 + " ");
				}

				out.write(tweet.get("retweet_count") + "\n");
			}
		} finally {
			cursor.close();
		}
		out.close();
	}
}
