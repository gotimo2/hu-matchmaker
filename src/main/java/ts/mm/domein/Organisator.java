package ts.mm.domein;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Organisator extends Persoon{
    private Match match;
    public Organisator(String nm, String ww, Match match){
        super(nm, ww);
        this.match = match;
    }

    @JsonIgnore
    public Match getMatch() {
        return match;
    }

    public void setMatch(Match match) {
        this.match = match;
    }
}

