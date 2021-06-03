package ts.mm;

import org.junit.Test;
import ts.mm.domein.Match;
import ts.mm.domein.Team;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.Assert.*;

/**
 * Unit test for simple App.
 */
public class AppTest
{
    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }

    @Test
    public void testFilePaths() throws Exception {
        assertNotNull(Files.readString(Paths.get("src/main/webapp/index.html")));
    }

    @Test
    public void TestMatchFromPost(){
        Match m = Match.matchFromPost("Testmatch", "testpass", "testnaam");
        assertNotNull(m.getTeam(1));
    }
}
