package br.felipeberbert.fallingwords.Data;

import android.content.Context;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import br.felipeberbert.fallingwords.R;
import br.felipeberbert.fallingwords.model.Word;

/**
 * Created by Felipe Berbert on 23/03/2017.
 */

public class DataSource {


    private static DataSource INSTANCE;

    private List<Word> mWordList;

    // This class should not be instantiated.
    private DataSource(Context context) {
        try {
            mWordList = readWordsFromJson(context);
        } catch (IOException|JSONException e){
            mWordList = new ArrayList<>();
            Toast.makeText(context, context.getString(R.string.error_load_words), Toast.LENGTH_SHORT).show();
        }
    }

    public List<Word> getmWordList() {
        return mWordList;
    }

    public static DataSource getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = new DataSource(context);
        }
        return INSTANCE;
    }

    private List<Word> readWordsFromJson(Context context) throws IOException, JSONException {
        StringBuilder builder = new StringBuilder();
        InputStream in = context.getResources().openRawResource(R.raw.words);
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));

        String line;
        while ((line = reader.readLine()) != null) {
            builder.append(line);
        }

        List<Word> wordList = new ArrayList<>();

        JSONArray root = new JSONArray(builder.toString());
        for (int i = 0; i < root.length(); i++) {
            JSONObject wordJson = root.getJSONObject(i);
            Word word = new Word(wordJson.getString(Word.KEY_TEXT_ENG), wordJson.getString(Word.KEY_TEXT_SPA));
            wordList.add(word);
        }
        return wordList;
    }
}
