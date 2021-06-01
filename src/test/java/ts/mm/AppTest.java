package ts.mm;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import ts.mm.domein.Match;
import ts.mm.domein.Team;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

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
        assertTrue( Files.readString(Paths.get("src/main/webapp/index.html")) != null);
    }

    @Test
    public void TestMatchFromPost(){
        Match m = Match.matchFromPost("Testmatch", "testpass", "testnaam");
        assertTrue(m.getTeam(1) instanceof Team);
    }
}
