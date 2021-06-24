package ts.mm.domein;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;

public class Speler extends Persoon implements Serializable {
    @JsonIgnore private final Team mijnTeam;
    @JsonIgnore private Match match;
    public Speler(String nm, String ww, Team team, Match m) throws Exception {
        super(nm, ww, m);
        this.mijnTeam = team;
        this.role = "speler";
        team.voegSpelerToe(this);
    }

    public void Leave(){
        mijnTeam.spelers.remove(this);
    }
    @JsonIgnore public Team getTeam(){
        return mijnTeam;
    }
}
