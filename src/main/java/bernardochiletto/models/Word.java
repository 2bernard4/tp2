package bernardochiletto.models;

public class Word{
    private String word;
    private int lenght;

    public Word(String word, int lenght) {
        this.word = word;
        this.lenght = lenght;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public int getLenght() {
        return lenght;
    }

    public void setLenght(int lenght) {
        this.lenght = lenght;
    }

}
