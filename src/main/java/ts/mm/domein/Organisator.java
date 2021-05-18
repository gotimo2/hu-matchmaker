package ts.mm.domein;

public class Organisator extends Persoon{
    private Match match;
    public Organisator(String nm, String ww, Match match){
        super(nm, ww);
        this.match = match;
    }

    public Match getMatch() {
        return match;
    }
}

