package sample;

import java.util.ArrayList;
import java.util.Comparator;
/**
 * WordCounter has all methods which are related to ArrayList. (array that stores word and its frequency)
 *
 * @author Chisa Fukutome
 */
public class WordCounter {
    /**
     * loadWordList updates words and its frequencies from poem string to the existing ArrayList.
     *
     * @param wordList ArrayList that stores a word and its frequency
     * @param poem A long sentence which the
     * @return wordList the ArrayList which is the same as the parameter ArrayList, but with additional words and its frequencies (from poem)
     */
    public static ArrayList<WordFrequency> loadWordList(ArrayList<WordFrequency> wordList, String poem) {
        //insert words into arrayList
        for(String word : poem.split(" ")) {
            if(isSameWord(word, wordList)) {
                //update word count
                countWord(word, wordList);
            } else {
                wordList.add(new WordFrequency(word, 1));
            }
        }
        return wordList;
    }//end loadWordList

    /**
     * isSameWord compares a ward and list of words from ArrayList to see whether or not the word already exists in the ArrayList.
     *
     * @param word a word to compare
     * @param wordList list of words to compare
     * @return true / false depending on the result
     */
    public static boolean isSameWord(String word, ArrayList<WordFrequency> wordList) {
        //if the word already exists in the list, return true
        for(int i = 0; i < wordList.size(); i++) {
            if(word.compareTo(wordList.get(i).getWord()) == 0) {
                return true;
            }
        }
        return false;
    }//end isSameWord

    /**
     * countWord updates frequency of the word if the word exists in the word in ArrayList.
     *
     * @param word a word to compare
     * @param wordList list of words to compare
     */
    public static void countWord(String word, ArrayList<WordFrequency> wordList) {
        for(int i = 0; i < wordList.size(); i++) {
            //find matching element
            if(wordList.get(i).getWord().compareTo(word) == 0) {
                //add frequency of the word
                wordList.get(i).addFrequency();
            }
        }
    }//end countWord

    /**
     * reverseSort reverse the order of elements in the ArrayList.
     *
     * @param wordList ArrayList which stores word and its corresponding frequencies
     * @return reversed order ArrayList
     */
    public static ArrayList<WordFrequency> reverseSort(ArrayList<WordFrequency> wordList) {

        //sort the list to ascending order
        wordList.sort(new FrequencySort());

        ArrayList<WordFrequency> reverse = new ArrayList<WordFrequency>();

        //load the list in a reverse way
        for(int i = wordList.size() - 1; i >= 0; i--) {
            reverse.add(wordList.get(i));
        }

        return reverse;
    }//end reverseSort

}//end WordCounter Class
