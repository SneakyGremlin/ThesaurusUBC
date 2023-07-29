package persistence;

import model.Thesaurus;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.fail;
import static persistence.LocationParameters.LOAD_LOCATION;

// Really just tests
public class TestLocationParameters {

    Thesaurus thesaurus = new Thesaurus();
    LoadValues loader = new LoadValues(thesaurus);
    SaveValues saver = new SaveValues(thesaurus);
    LocationParameters lc = new LocationParameters();

    @Test
    public void testFilePathExists() {
        try {
            loader.beginLoading(LOAD_LOCATION);
        } catch (IOException e) {
            fail();
        }

        try {
            saver.saveThesaurus(LOAD_LOCATION);
        } catch (FileNotFoundException e) {
            fail();
        }
    }
}

// clone <<<

// test annotation, expression expected, locale
