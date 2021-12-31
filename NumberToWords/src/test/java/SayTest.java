import static org.junit.Assert.assertEquals;

public class SayTest {
    @org.junit.Test
    public void testSay() {

        assertEquals("Zero", Say.say(0));
        assertEquals("One", Say.say(1));
        assertEquals("Five", Say.say(5));
        assertEquals("Ten", Say.say(10));
        assertEquals("Eleven", Say.say(11));
        assertEquals("Twenty", Say.say(20));
        assertEquals("Fifty-five", Say.say(55));
        assertEquals("Two hundred", Say.say(200));
        assertEquals("Four hundred and six", Say.say(406));
        assertEquals("Six hundred and twelve", Say.say(612));
        assertEquals("Seven hundred and sixty-eight", Say.say(768));
        assertEquals("Two thousand", Say.say(2000));
        assertEquals("Two thousand seven hundred and sixty-eight", Say.say(2768));
        assertEquals("Three hundred and twenty-two billion ten million four hundred and thirty thousand one hundred and fifty-three", Say.say(322_010_430_153L));


    }   }