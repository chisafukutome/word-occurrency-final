package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.safety.Whitelist;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class Main extends Application {
    //variable declarations
    Button btn;
    Text text;

    @Override
    public void start(Stage primaryStage) {

        try {
            //the title of the window
            primaryStage.setTitle("Text Analyzer");

            VBox layout = new VBox();
            layout.setAlignment(Pos.BASELINE_CENTER);
            layout.setStyle("-fx-padding: 100");

            //text
            text = new Text();
            text.setText("This text analyzer counts word frequencies of the poem 'The Raven'");
            Font font = Font.font("Verdana", 10);
            text.setFont(font);

            //button (to display word frequencies)
            btn = new Button();
            btn.setText("Check Word Frequencies!");
            btn.setStyle("-fx-background-color: #FF9100");
            btn.setFont(font);
            btn.setMinHeight(100);
            btn.setMinWidth(200);
            layout.getChildren().addAll(text, btn);

            Scene scene = new Scene(layout,400,400);
            primaryStage.setScene(scene);

            //scrapes the web
            ArrayList<WordFrequency> wordList;
            wordList = scrapeWeb();

            //scene 2
            VBox layout2 = new VBox();
            layout2.setAlignment(Pos.BASELINE_CENTER);

            //text
            Text text2 = new Text();
            text2.setText("Top 20 Frequent Words");
            Font font2 = Font.font("Verdana", FontWeight.BOLD, 20);
            text2.setFont(font2);

            //text
            Text text3 = new Text();

            String longText = WordOccurrencyDB.getData();
            text3.setText(longText);

            layout2.getChildren().addAll(text3);
            Scene scene2 = new Scene(layout2, 675, 675);

            btn.setOnAction(e -> primaryStage.setScene(scene2));
            primaryStage.show();
        } catch(Exception e) {
        e.printStackTrace();
    }
    }


    public static void main(String[] args) throws IOException, SQLException {
        WordOccurrencyDB.getConnection();
        launch(args);
    }//end main method

    public static ArrayList<WordFrequency>            //event handler
    scrapeWeb() throws IOException {
        //retrieve the information of a web page
        Document doc = Jsoup.connect("https://www.gutenberg.org/files/1065/1065-h/1065-h.htm").get();

        //convert from Document to String (extracting poem part)
        String poem = convertString(doc.getElementsByClass("chapter"));

        //remove all tags
        poem = removeTags(poem);

        //remove special characters
        poem = removeSpecialChar(poem);

        //convert all upper case letters to lower case letters
        poem = poem.toLowerCase();

        //create a list to hold words and its frequencies
        ArrayList<WordFrequency> wordList = new ArrayList<>();

        //load words and its frequencies into the list
//        WordOccurrencyDB.insertData(poem);
        wordList = WordCounter.loadWordList(wordList, poem);
        //sort the list in descending order
        return WordCounter.reverseSort(wordList);
    }//end scrapeWeb

    /**
     * convertString converts the data type of HTML into string.
     *
     * @param htmlElement HTML which is not string
     * @return htmlElement.toString();
     */
    public static String convertString(Elements htmlElement) {
        return htmlElement.toString();
    }//end convertString

    /**
     * removeTags removes HTML tags from the string which contains HTML tags.
     *
     * @param html string with all of HTML tags
     * @return Jsoup.clean(html, Whitelist.none()) string without HTML tags
     */
    public static String removeTags(String html) {
        return Jsoup.clean(html, Whitelist.none());
    }//end removeTags

    /**
     * removeSpecialChar converts all of special characters into a space
     * @param html string without HTML tags
     * @return html.trim().replaceAll(" +", " ") string without any special characters
     */
    public static String removeSpecialChar(String html) {
        html = html.replaceAll("[^a-zA-Z0-9]", " ");
        //remove all 2+ spaces
        return html.trim().replaceAll(" +", " ");
    }//end removeSpecialChar
}//end Main Class
