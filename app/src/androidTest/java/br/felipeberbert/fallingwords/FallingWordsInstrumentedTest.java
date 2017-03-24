package br.felipeberbert.fallingwords;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import br.felipeberbert.fallingwords.Data.DataSource;
import br.felipeberbert.fallingwords.model.Word;

import static org.junit.Assert.*;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class FallingWordsInstrumentedTest {
    @Test
    public void loadWords() throws Exception {
        Context appContext = InstrumentationRegistry.getTargetContext();
        List<Word> wordList = DataSource.getInstance(appContext).getmWordList();
        assertNotNull(wordList);
        assertFalse(wordList.isEmpty());
    }
}
