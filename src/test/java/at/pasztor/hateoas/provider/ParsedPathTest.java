package at.pasztor.hateoas.provider;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ParsedPathTest {
    @Test
    public void testNoVariablesNoPath() {
        ParsedPath parsedPath = new ParsedPath("/");
        assertEquals("http://localhost/", parsedPath.render("http://localhost", new String[0]));
    }

    @Test
    public void testNoVariablesLongPath() {
        ParsedPath parsedPath = new ParsedPath("/test1/test2/test3");
        assertEquals("http://localhost/test1/test2/test3", parsedPath.render("http://localhost", new String[0]));
    }

    @Test
    public void testSingleVariableSubstitution() {
        ParsedPath parsedPath = new ParsedPath("/{variable}");
        assertEquals("http://localhost/test1", parsedPath.render("http://localhost", new String[]{"test1"}));

    }

}
