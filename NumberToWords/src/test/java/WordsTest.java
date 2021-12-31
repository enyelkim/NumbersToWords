import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class WordsTest {



    @org.junit.Test
    public void testValorMiembro() {

        assertEquals(123,Words.valorMiembro(123, 0));

        assertEquals(240,Words.valorMiembro(24, 1));

        assertEquals(2000,Words.valorMiembro(2, 3));

    }

    @org.junit.Test
    public void testTokens() {

        List<String> tokens;
        String s;

        s = null;
        tokens = Words.tokens(s);
        assertNotNull(tokens);
        assertEquals(0, tokens.size());

        s = "";
        tokens = Words.tokens(s);
        assertNotNull(tokens);
        assertEquals(0, tokens.size());

        s = "       ";
        tokens = Words.tokens(s);
        assertNotNull(tokens);
        assertEquals(0, tokens.size());


        // 28 words
        s = "Five quintillion four quadrillion three hundred and twenty trillion four hundred and thirty-two billion eight hundred and sixty-one million nine hundred and three thousand five hundred and forty-one";

        tokens = Words.tokens(s);

        assertEquals(28, tokens.size());
        assertEquals("five", tokens.get(0));
        assertEquals("quintillion", tokens.get(1));
        assertEquals("and", tokens.get(6));
        assertEquals("thirty-two", tokens.get(12));

        // 28 words whith some extra space
        s = "Five  quintillion four  quadrillion three hundred and twenty trillion four hundred and thirty-two billion eight hundred and sixty-one million nine hundred and three thousand five hundred and forty-one";

        tokens = Words.tokens(s);

        assertEquals(28, tokens.size());
        assertEquals("five", tokens.get(0));
        assertEquals("quintillion", tokens.get(1));
        assertEquals("and", tokens.get(6));
        assertEquals("thirty-two", tokens.get(12));


    }

    @org.junit.Test
    public void testGiraTokens() {

        String s;
        List<String> tokens;

        s = "";
        tokens = Words.giraTokens(s);
        assertEquals(true, tokens.isEmpty());
        assertEquals(0,tokens.size());

        // 28 words
        s = "Five quintillion four quadrillion three hundred and twenty trillion four hundred and thirty-two billion eight hundred and sixty-one million nine hundred and three thousand five hundred and forty-one";

        tokens = Words.giraTokens(s);
        assertEquals(false, tokens.isEmpty());
        assertEquals("forty-one", tokens.get(0));
        assertEquals(28,tokens.size());


    }




    @org.junit.Test
    public void testValue(){

        assertEquals(0,Words.value(null));
        assertEquals(0,Words.value(""));
        assertEquals(0,Words.value("zero"));
        assertEquals(1, Words.value("one"));
        assertEquals(5, Words.value("five"));
        assertEquals(10, Words.value("ten"));
        assertEquals(11, Words.value("eleven"));
        assertEquals(15, Words.value("fifteen"));
        assertEquals(30, Words.value("thirty"));
        assertEquals(70, Words.value("seventy"));
        assertEquals(84, Words.value("eighty-four"));

        assertEquals(100, Words.value("hundred"));

        assertEquals(1000, Words.value("thousand"));
        assertEquals(1000000, Words.value("million"));
        assertEquals(1000000000, Words.value("billion"));

    }



    @org.junit.Test
    public void testWords() {

        String s;

        s = null;
        assertEquals(0, Words.words(s));

        s = "";
        assertEquals(0, Words.words(s));

        s = " ";
        assertEquals(0, Words.words(s));

        s = "Zero";
        assertEquals(0, Words.words(s));

        s = "One";
        assertEquals(1, Words.words(s));

        s = "Ten";
        assertEquals(10, Words.words(s));

        s = "Eleven";
        assertEquals(11, Words.words(s));

        s = "Twenty";
        assertEquals(20, Words.words(s));

        s = "And twenty";
        assertEquals(20, Words.words(s));

        s = "Fifty-five";
        assertEquals(55, Words.words(s));

        s = "Two hundred";
        assertEquals(200, Words.words(s));

        s = "Four hundred and six";
        assertEquals(406, Words.words(s));

        s = "Six hundred and twelve";
        assertEquals(612, Words.words(s));

        s = "Seven hundred and sixty-eight";
        assertEquals(768, Words.words(s));


        s = "Two thousand";
        assertEquals(2000, Words.words(s));

        s = "Two thousand seven hundred and sixty-eight";
        assertEquals(2768, Words.words(s));

        s = "Three hundred and twenty-two billion ten million four hundred and thirty thousand one hundred and fifty-three";
        assertEquals(322_010_430_153L, Words.words(s));


    }



}