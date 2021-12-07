package sample;

/**
 * WordFrequency stores a word and its corresponding frequency.
 *
 * @author Chisa Fukutome
 */
public class WordFrequency {

    private String word;
    private Integer frequency;

    /**
     * Constructs and sets the word & its frequency.
     *
     * @param word
     * @param frequency the word's frequency
     */
    public WordFrequency(String word, int frequency) {
        setWord(word);
        setFrequency(frequency);
    }//end constructor//end constructor

    //setters and getters
    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public Integer getFrequency() {
        return frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }
    //end setters and getters

    /**
     * Adds frequency by 1. This method is used when the word which has already stored in ArrayList is counted.
     */
    public void addFrequency() {
        this.frequency++;
    }//end countFrequency

    @Override
    public String toString() {
        return "Word: " + "'" + getWord() + "' " + "Frequency: " + getFrequency() + "\n";
    }
}//end WordFrequency Class
