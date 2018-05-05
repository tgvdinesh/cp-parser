package com.parser.cp.util;

import com.parser.cp.model.payload.Task;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Optional;

public class HTMLParserTest {
    HTMLParser htmlParser;

    @Before
    public void initialize() {
        htmlParser = new HTMLParser("pre", "kattis      <div id=\"contest_time\">        <h2 class=\"title\">CodeChef - May Challenge 2018 Division 2</h2>      </div>      <div class=\"headline-wrapper\"><h1>Minimum Deletions</h1></div>      <div class=\"text-center\"><h1>Minimum Deletions</h1></div>            <table class=\"sample\" summary=\"sample data\">        <pre>222 322 4</pre>        <pre>0-1</pre>      </table>          <p><strong>Memory limit: </strong>256 MB</p>    ");
    }

    @Test
    public void testParser() {
        Optional<Task> optionalTask = htmlParser.parse();
        Assert.assertTrue(optionalTask.isPresent());
    }
}
