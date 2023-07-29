# Thesaurus

> NOTE: This application requires the Amazon Coretto Version 11.0.20 JDK.

> NOTE: Do not let the following dissuade you from exploring this project. 
> 
>       This project has been untouched since my course (CPSC 210) termination. As such there remains unimplemented functionality.
>       I do not see myself seeing the unimplemented to fruition since I have a much more efficient version in the works.
>       You may or may not see depending on where I am at regarding its completion. 

> You will find the Command Line Interface in src/main/ui/Main. 

> You will find the Graphics User Interface in src/main/ui/gui/MainForGUI. For instructions on using this refer to 
> "Instructions for Grader".

> You will find "Reflections on my Failures" at the bottom. 

> Finally: ignore the errors from the checkstyle.xml document. CPSC 210: Software Construction had us install a plugin for
> their checkstyle (I do not believe it is relevant now but rest assured I received full marks for the checkstyle component).

### A precursor and/or facilitator of lexeme-clustering in latin-scripted languages.

Note: classes are in bold; objects are in italics.  
This application is intended to deconstruct an inputted lexical construct i.e. a passage. After being input, the
program     
will effectively create a list which we will call a **Thesaurus**. This will comprise all the words used in the file.   
These words will be stored as subclass instances of the superclass **LexicalConstruct**. A **LexicalConstruct** will
have two primary attributes: forms and related,     
and a counter which tallies their incidence in the passage. At present, on account of a lack of Machine Learning   
functionality these fields' values will be set by the user after prompts. Forms ensures that different morphological    
forms and spellings are all clustered to mitigate against potential repetition. Related is a list of **
LexicalConstruct** - **LexicalConstruct** are     
self-referencing variables - which are "close" to the primary **Words** instance. At present, this closeness is a
function   
of semantics and not of lexical origin. As an example, consider the word *dog* - its related words would potentially
be    
*canine*, *wolf* et cetera. Note that it could be argued that if a flimsy criteria is implemented, one could couple **
Words**     
like *dog* with *human* - both are mammals. However, since the **Word** list is at the disposal of the user himself, and
since    
an AI algorithm is not responsible for the demarcations, this concern is redundant. After the process of inputting   
field values the algorithm will iterate through the **Thesaurus** to store each word in order of frequency e.g. if the
word    
*cat* occurs three times and *dog* twice, *cat* precedes *dog* in the Thesaurus - hence indexing. This indexing is used
to  
account for **Word** searches: the **Thesaurus** is intended to serve as one. Indexing ensures that linear searching  
is inefficient. It should be noted that in order to discard potentially unwanted **Words** i.e. if only specific **
Words**   
need to be stored or retrieved e.g. **Words** starting with c, **Thesaurus** has a method, excise, which takes as input
a   
list of words (not the class) and "excises" them from the dictionary. It should also be noted that **Words** may be
added   
freely by the user as well: the interface will allow for this.   
This data is stored in a file and can be read back into the **Thesaurus** instance when the program is rerun. The
storage must    
be explicated: it is not automatic to prevent accidental overwriting of the primary **Thesaurus** object.

This application can be used by individuals who wish to analyse and curate word frequencies in a file, primarily
linguists.    
If AI is implemented to automate the process of **Word**'s field-setting, it can effectively provide a mosaic of an   
arbitrary number of words, showing relations depending on the criteria chosen by the user. It should be relevant in
all      
languages that are not pictorial (in its non AI form) e.g. Chinese. For an immensely populated **Thesaurus** the
storage   
option can be changed to linked lists or arbitrary arity trees.

This project bears significance for me since I feel that it provides a framework for potentially all similar
problems:   
if the **Word** class is changed into another e.g. **Painting**, then the **Thesaurus** (which would then be called a   
**Gallery**) would store just that (of course you would require a modest bit of further refactoring). And if AI is
implemented    
you could potentially sort the collective human oeuvre i.e. Monets and Picassos et cetera. Please note how I say
framework:   
this application should essentially be considered just that. Without AI, it is just a seed but if AI is introduced it
germinates.

Note: The above description has been kept since I will make my program into what's avowed. You may read it still and discern the current functionalities albeit inaccurately.

### User Stories

- As a user, I want to be able to instantiate a **SingularWord** object and append it to a **Thesaurus**.
- As a user, I want to be able to instantiate a **CompoundWord** object and append it to a **Thesaurus**.
- As a user, I want to be able to instantiate a **IdiomaticExpression** object and append it to a **Thesaurus**.
- As a user, I want to be able to add RelatedLexicalConstructs to a newly instantiated **LexicalConstruct**.
- As a user, I want to be able to search for and select a **SingularWord** and rewrite its morphological forms.
- As a user, I want to be able to search for and select a **LexicalConstruct** and view its related **
  LexicalConstructs**.
- As a user, I want to be able to search for and select a **LexicalConstruct** and rewrite its related **
  LexicalConstructs**.
- As a user, I want to be able to view all **LexicalConstructs** in my **Thesaurus**.
- As a user, I want to be able to remove unwanted **LexicalConstructs** from my **Thesaurus**.
- As a user, I want to be able to manually reissue an order for re-indexing in a **Thesaurus**.
- As a user, I want the option to be able to reload my previously introduced **LexicalConstructs** into my running
  program.
- As a user, I want to be able to save my newly introduced **LexicalConstructs** to a data file.
- As a user, I want the option to either append my newly introduced **LexicalConstructs** to an existing data file or
  create a new one.
- As a user, I want my data automatically reloaded from a file into **thesaurus**.
- As a user, I want my data automatically saved from **thesaurus**.

### Instructions for Grader

- You can generate the first required event related to adding Xs to a Y by typing an entry in the text field with the
  label Add: above it, filling or not filling the text boxes below and clicking submit. All relevant fields will be
  appropriately dealt with. You just need to enter the entry and select a radio button if one is not selected.
- You can generate the second required event related to adding Xs to a Y by typing an entry present in the thesaurus in
  the textfield below where all the entries are added and above the Delete, Refresh, and View buttons and clicking
  Delete. If the entry is present then it is deleted.
- You can locate my visual component by looking at the bottom left of my screen. There is a picture of Homer Simpson's
  head there and him being there represents that the thesaurus is empty (just like his head). Add entries thereto and
  the image will change to reflect the amount of words in the thesaurus. Note that the images may appear superimposed at
  first but that is not on account of code.
- You can save the state of my application by clicking the save button in the menu bar. Can save anytime. Old entries
  are overwridden with new instances. All entries are retained.
- You can reload the state of my application by clicking the load button in the menu bar. NOTE: you can only load values
  when you start the program i.e. when no entries have been added. The two alternatives would have been resetting the
  state of the program to when the program calls load with no entries (possibly saving new entries) or just starting a
  new instance of the program which runs parallel.

- You can generate an event which corroborates the adding of LexicalConstructs(X) to Thesaurus(Y) by entering an entry
  in the same textfield as you did for the second event related to X to Y, and clicking view. You can see its fields to
  the right. NOTE: please ignore the always blank box at the top in the right; that is for a special field at present
  unutilised.


## PHASE 4

### Phase 4: Task 2
This is a representative sample of the events that are logged (sans the bullet points):

- A new SingularWord: singular1 has been added to the Thesaurus.
- A new IdiomaticExpression: idiomatic_1 has been added to the Thesaurus.
- A new CompoundWord: compound_word_1 has been added to the Thesaurus.
- A new IdiomaticExpression: idiom_to_keep has been added to the Thesaurus.
- The construct "singular1" of type: SingularWord, has been removed from the Thesaurus.
- The construct "idiomatic_1" of type: IdiomaticExpression, has been removed from the Thesaurus.
- The construct "compound_word_1" of type: CompoundWord, has been removed from the Thesaurus.

N.b. There is a precursor to the events displayed. The following will be displayed above all Events (provided there are events and sans the quotation marks):
"Note: added and deleted refer to current state of the program; in order to make the changes permanent you must save the application.
Events are:" 
N.b.b. In the case there are no events to display:
"There were no events."

### Phase 4: Task 3

Pertaining UML diagram: 
It may seem to you that a thesaurus contains a Lexical Construct but it doesn't; hence, there is no aggregation relationship there.

This is the reflection:
- The fields of East Panel i.e. NorthPanelForDefinition, BottomRightPanelForRelated, and BottomLeftPanelForMorphs are subject to semantic coupling. Essentially, the default message in all three fields, and the display format all share a common format. This was an oversight. A potential fix is to introduce an abstract class abstracting the print functionality, whilst the best fix is to do the aforementioned sans the format bit, and abstracting the display format into a separate printer class. This increases cohesion as well since now I will have a class dedicated to printing. Do refer to the association between ActionListenerForSingular and CompoundWord, and TopPanelConstructDisplay and CompoundWord.
- There is yet again heavy semantic coupling in the entirety of my Xs (Xs into Ys): Lexical Constructs, by extension the thesaurus, the gui (and ui), and the lexical deconstructor (accessed via UI). And since there is a plenitude of coupling, there is a paucity of cohesion. This may not be evident from the UML since it doesn't display dependencies or the implementation. Essentially all the entries are stored in a single format. Whilst I have ensured that the current version of the project is mildly foolproof to inconsistencies, the project is not amenable to extension. I have flimsy enforcement of the format: many-a-times I manually create an instance of A Lexical Construct subtype and use its formatting methods, and sometimes I actually create a new method entirely (in LexicalDeconstructor). I would like state here that the empty class Global Helpers was to this end but I never got around to actually abstracting. The solution for this is creating a new class solely for the purpose of enforcing the format and utilising that wherever I require the format as opposed to manual implementations.
- This is also not on this UML diagram however it still pertains to it since it is about static methods. This also applies to the above. There are several classes in my project which have functionalities that ought to be static. The above is an example. In addition to this, the refresh button in my GUI should be static as well (and by extension all the methods it calls and all pertinent variables), judging by the sheer number of times it is utilised in my GUI.
- Thesaurus' field for entries has been implemented as an ArrayList<Lexical Construct> but it ought to have been a HashMap<String, LexicalConstruct> where String represents the formatted value of the construct and LexicalConstruct is the construct itself. This also liberates me from using a second ArrayList<String> inside the thesaurus solely for the values of the LexicalConstructs (this was instrumental in designing my project). It need not be mentioned that the coupling is ginormous: I have to ensure that entries are present in both ArrayLists and deleted from both and that their formats match. HashSet<> would resolve the coupling debacle, and would also preclude me from reinventing the wheel.
- Thesaurus should be an iterable. I violate the Iterator pattern manifold times. Essentially wherever, I reference Thesaurus I expose the inner workings of its data structures (which violates data hiding design principles). Combining this with the above improves design principles adherence substantively.
- Thesaurus should be a Singleton. There is only ever one thesaurus inside my program (barring the very egregious instance of it in persistence which was a ramshackle attempt at augmenting functionality). I initially had to pass Thesaurus as a parameter several times until I declared it static in the GUI and introduced my own getInstance method (getThesaurus). The fact that I use a defining component of Singleton really means I ought to have used it. Then I wouldn't have to worry about errant thesauruses and potential inconsistencies.
- I lack a proper exception hierarchy and the one exception I do use extends a runtime exception that I never catch (for situations in which you call returnLexicalConstruct of thesaurus) while the construct isn't present. First I should have dealt with this thrown exception properly (it's not runtime firstly, it's checked). I should also have added Exceptions for proper format, duplicate entries (as opposed to implementation in the non-GUI version, though arguably the GUI version isn't so better), and proper save and load times, and even one for closing the application without saving (a reminder of sorts). It occurs to me that the last two are a matter of preference.
- I say above Thesaurus does not contain a lexical construct. Whether this may be the case or not, The relationship between a thesaurus and a Lexical Construct should be reflexive. I actually intended to make Lexical Constructs self-referential i.e. the related and morphs fields in L.C. should have been Thesauruses of Lexical Constructs. It makes much more sense when this is considered. I am aware that if this had been the case Singleton would not have implemented but it wasn't and at present Singleton applies. Back to point, it makes intuitive sense for a construct to know that it is assigned to a thesaurus.
- Since I use the same methods in my Thesaurus for the UI additions and deletions and the save/load additions and deletions, this results in a somewhat inaccurate event log. I have fixed the problem but the actual fix would of course to have separate methods for both.
- In my GUI there are instances in which I pass a String to determine what code is run. This is in violation of design principles e.g. when I'm adding a construct I use a string to determine what code to run. 
- In extension to the above, I extensively use Strings as parameters which ought to be enumeration e.g. in my UI (not GUI) I use S, I, and C which should be an enumeration; Y and N should also follow this pattern.
- This is a point of failure in my program: if somehow an individual manages to add an Idiom/Compound word as a Singular word, my program can crash because of unchecked runtime errors. The solution is of course proper exception handling, and automatic checking inside the thesaurus for consistencies (I do not implement this functionality; I only ensure consistencies as I am adding or deleting to the thesaurus). Essentially if an incorrect entry is already present inside the thesaurus the program will likely crash and I should mitigate against this possibility.
