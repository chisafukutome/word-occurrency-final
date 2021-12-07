package sample;

import java.io.IOException;
import java.sql.*;

public class WordOccurrencyDB {
    public static Connection getConnection() {
        try {
            //check database connection
            String jdbcURL = "jdbc:mysql://localhost:3306/word_occurrences";
            String username = "root";
            String password = "toor123";
            Connection connection = DriverManager.getConnection(jdbcURL, username, password);
//            System.out.println("Connected.");
            return connection;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }//end getConnection method

    public static void insertData(String poem) {
        try {
            Connection con = getConnection();
            for(String word : poem.split(" ")) {
                if(hasData(word)) {
                    //update frequency count
                    updateFrequency(word);
                } else {
                    //insert words into database
                    PreparedStatement inserted = con.prepareStatement("INSERT INTO word (word, frequency) VALUES ('"+word+"', 1)");
                    //execute the query
                    inserted.executeUpdate();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("Insert Completed.");
        }
        //insert words into arrayList
//        for(String word : poem.split(" ")) {
//            if(isSameWord(word, wordList)) {
//                //update word count
//                countWord(word, wordList);
//            } else {
//                wordList.add(new WordFrequency(word, 1));
//            }
//        }
//        return wordList;
    }
    public static boolean hasData(String poemWord) {
        try {
            Connection con = getConnection();
            Statement statement = con.createStatement();
            String value = "SELECT word FROM word WHERE word='"+poemWord+"'";
            ResultSet result = statement.executeQuery(value);

            //Check to see whether or not the word already exists in the database.
            if(result.next()) {
                //Data already exists.
                return true;
            } else {
                //Data not exists.
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    public static String getData() {
        int count = 0;
        String rowData = " ";
        String longString = " ";
        try {
            Connection con = getConnection();
            PreparedStatement statement = con.prepareStatement("SELECT word, frequency FROM word ORDER BY frequency DESC");
            ResultSet result = statement.executeQuery();

            while(result.next() && count < 20) {
                count++;
               rowData = getRowData(con, statement, result, count);
               longString += rowData;
            }
            return longString;
        }catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public static String getRowData(Connection con, PreparedStatement statement, ResultSet result, int count) throws IOException, SQLException{
        return count + " " + "Word: "+ "'" + result.getString("word") + "'" + " Frequency: " + result.getInt("frequency") + "\n";
    }
    public static void updateFrequency(String poemWord) {
        int frequency = 0;
        try {
            Connection con = getConnection();
            Statement statement = con.createStatement();
            //retrieve frequency of the word
            String getFrequency = "SELECT frequency FROM word WHERE word='"+poemWord+"'";
            ResultSet result = statement.executeQuery(getFrequency);
            while (result.next()) {
                frequency = result.getInt("frequency");
            }

            //add the frequency by 1
            frequency++;
            //update the frequency
            PreparedStatement updateFreq = con.prepareStatement("UPDATE word SET frequency="+frequency+" WHERE word='"+poemWord+"'");
            //execute the query
            updateFreq.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//end updateFrequency
}//end WordOccurrencyDB Class
