package sample;

import java.util.Comparator;
/**
 * FrequencySort sorts by word frequency.
 *
 * @author Chisa Fukutome
 */
public class FrequencySort implements Comparator<WordFrequency>{
    /**
     * @param one the first word frequency to compare
     * @param two the other word frequency to compare with "one"
     * @return one.getFrequency().compareTo(two.getFrequency()) the result of the comparison
     */
    public int compare(WordFrequency one, WordFrequency two) {
        return one.getFrequency().compareTo(two.getFrequency());
    }
}//end FrequencySort
