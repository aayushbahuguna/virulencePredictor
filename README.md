VirulencePredictor
==================

Format of Data.txt
------------------
```
NumberOfTweets(t) NumberOfFeatures(n)
Feature1 Feature2 ... FeatureN NumberOfRetweets
:
:
t lines
:
:
```
Feature Description
-------------------
Feature1: Number of Followers: user->followers_count

Feature2: Verified User: user->verified

Feature3: Number of times user is listed: user->listed_count

Feature4: Time of Tweet: created_at

Feature5: Reply/Fresh: in_reply_to_user_id

NumberOfRetweets: retweet_count

How to run
----------
To extract the features from the tweet contained in the mongodb database to "Data.txt"
```
javac -classpath 'mongo-java-driver-2.11.1.jar' TweetDataExtractor.java
java -classpath '..:mongo-java-driver-2.11.1.java' virulencePredictor.TweetDataExtractor
```
To run the program once we have "Data.txt"
```
javac Solution.java TrainingData.java RetweetPredictor.java
java -classpath .. virulencePredictor.Solution
```
