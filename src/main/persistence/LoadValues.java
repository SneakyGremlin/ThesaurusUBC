package persistence;

import model.*;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Stream;

// Reads previously introduced LexicalConstructs from a data file.
public class LoadValues {

    Thesaurus thesaurus;

    // CONSTRUCTOR
    // EFFECTS: instantiates a LoadValues object, sets thesaurus field to supplied thesaurus
    public LoadValues(Thesaurus thesaurus) {
        this.thesaurus = thesaurus;
    }

    // MODIFIES: this
    // EFFECTS: takes an "empty" Thesaurus object and returns a Thesaurus object loaded with Lexical Constructs
    //          First read the values from location
    //          Then call loadThesaurus method
    public Thesaurus beginLoading(String location) throws IOException {
        StringBuilder toIncrementTo = new StringBuilder();
        Stream<String> stream = Files.lines(Paths.get(location), StandardCharsets.UTF_8);
        stream.forEach(s -> toIncrementTo.append(s));
        stream.close();
        String thesaurusValuesAsJsonString = toIncrementTo.toString();

        JSONObject thesaurusAsJsonObject = new JSONObject(thesaurusValuesAsJsonString);
        JSONArray thesaurusAsJsonArray = thesaurusAsJsonObject.getJSONArray("thesaurus");
        return loadThesaurus(thesaurusAsJsonArray);
        // JSONObject().put("thesaurus", loadThesaurus(thesaurusAsJsonArray))
    }

    // MODIFIES: this
    // EFFECTS: iterates over the stored JSON Objects in thesaurusAsJsonArray and adds corresponding entries to 
    //          thesaurus.entries and thesaurus.entriesAsStrings AFTER determining the type of LexicalConstruct the 
    //          object stores
    public Thesaurus loadThesaurus(JSONArray thesaurusAsJsonArray) {
        for (Object constructAsJavaObject : thesaurusAsJsonArray) {
            JSONObject constructAsJasonObject = (JSONObject) constructAsJavaObject;
            if (constructAsJasonObject.getString("type").equals("S")) {
                storeSingularInsideThesaurus(constructAsJasonObject);
            } else { // if (constructAsJasonObject.getString("type").equals("M")) {
                storeMultiInsideThesaurus(constructAsJasonObject, constructAsJasonObject.getString(
                        "compoundOrIdiom"));
            }

        }
        // Since this can only be called from the beginning of the program i.e. when thesaurus is empty
        EventLog.getInstance().clear();
        return thesaurus;
    }

    // MODIFIES: this
    // EFFECTS: stores the values from the JSON Object into a new singular Word object and adds that to the thesaurus.
    public void storeSingularInsideThesaurus(JSONObject constructAsJson) {
        SingularWord singularWord = new SingularWord(constructAsJson.getString("value"));
        singularWord.overwriteRelatedLexicalConstructs(
                convertJsonArray(constructAsJson.getJSONArray("relatedLexicalConstructs")));
        singularWord.overwriteMorphologicalForms(
                convertJsonArray(constructAsJson.getJSONArray("morphologicalForms")));

        thesaurus.addLexicalConstruct(singularWord);
    }

    // MODIFIES: this
    // EFFECTS: stores the values from the JSON Object into a new MultiLexemeLexicalConstructs object
    //          and adds that to the thesaurus. MultiLexemeLexicalConstructs is abstract so subtype is stored
    //          and subtype is determined via the stringDeterminant.
    public void storeMultiInsideThesaurus(JSONObject constructAsJson, String stringDeterminant) {
        if ((constructAsJson.getString("compoundOrIdiom")).equals("C")) {
            storeCompound(constructAsJson);
        } else {
            storeIdiom(constructAsJson);
        }
    }

    // MODIFIES: this
    // EFFECTS: stores a CompoundWord inside a thesaurus
    public void storeCompound(JSONObject constructAsJson) {
        CompoundWord compoundWord = new CompoundWord(constructAsJson.getString("value"));
        compoundWord.overwriteRelatedLexicalConstructs(
                convertJsonArray(constructAsJson.getJSONArray("relatedLexicalConstructs")));

        thesaurus.addLexicalConstruct(compoundWord);
    }

    // MODIFIES: this
    // EFFECTS: stores an IdiomaticExpression inside a thesaurus
    public void storeIdiom(JSONObject constructAsJson) {
        IdiomaticExpression idiomaticExpression = new IdiomaticExpression(constructAsJson.getString("value"));
        idiomaticExpression.overwriteRelatedLexicalConstructs(
                convertJsonArray(constructAsJson.getJSONArray("relatedLexicalConstructs")));

        thesaurus.addLexicalConstruct(idiomaticExpression);
    }

    // EFFECTS: returns a JSONArray object as ArrayList<String>
    public ArrayList<String> convertJsonArray(JSONArray jsonArray) {
        ArrayList<String> stringArrayToReturn = new ArrayList<>();
        for (Object jsonArrayElementAsJavaObject : jsonArray) {
            String jsonArrayElementAsString = ((String) jsonArrayElementAsJavaObject);
            stringArrayToReturn.add(jsonArrayElementAsString);
        }
        return stringArrayToReturn;
    }
}

// if you cast something is its type maintained i.e. previous object!!!

// !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!HIGH-PRIORITY: incidence counters!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
// note: only implement incidence counters after you implement indexing as well

// is "modifies" only for relatively global (declared private protected)? when they are changed?  !!!

// what happens when the key pair doesn't exist? JSON !!!

// return Collections.unmodifiableList(thingies) <<<

// constructor has no return part/specification <<<

// can use just <<<
// try(){} without catch if add throw in method ... !!! what are the parts called? is signature the whole thing? !!!

// scope overwriting when declaring a variable with same name as parameter name !!!

// why can't I just use a JSONObject type in the for loop directly !!!

// if you sout in a non UI package does it still print? !!!

// for casting <<<
/*
public boolean isCompoundWord(Object toCheck) {
        MultiLexemeLexicalConstructs toMulti = (MultiLexemeLexicalConstructs) toCheck;
        return (toMulti.getCompoundOrIdiom()).equals("C");
    }
    ^^^ This works

public boolean isCompoundWord(Object toCheck) {
        return ((MultiLexemeLexicalConstructs) toCheck.getCompoundOrIdiom()).equals("C");
    }
    ^^^ This does not work
    Maybe because of parameter evaluation i.e. the parameter inside is evaluated first so toCheck only become Multi afer
    call to getCom... which does not exist

    return ((MultiLexemeLexicalConstructs) toCheck).getCompoundOrIdiom().equals("C");
    ^^^^^^^^^ THIS WORKS

    in sum watch the bracket placement

 */