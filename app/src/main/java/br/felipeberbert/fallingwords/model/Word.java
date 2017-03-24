package br.felipeberbert.fallingwords.model;

/**
 * Created by Felipe Berbert on 23/03/2017.
 */

public class Word { // I guess that technically they are not "words" but let's abstract this fact for this simple app

    public static final String KEY_TEXT_ENG = "text_eng";
    public static final String KEY_TEXT_SPA = "text_spa";

    private String textEng;
    private String textSpa;

    /**
     * @param textEng Word in english
     * @param textSpa Word in spanish
     */
    public Word(String textEng, String textSpa) {
        this.textEng = textEng;
        this.textSpa = textSpa;
    }

    public String getTextEng() {
        return textEng;
    }

    public String getTextSpa() {
        return textSpa;
    }
}
