package ts.mm.domein;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Speler extends Persoon {
    @JsonIgnore private Team mijnTeam;
    @JsonIgnore private Match match;
    public Speler(String nm, String ww, Team team, Match m) throws Exception {
        super(nm, ww, m);
        this.mijnTeam = team;
        this.role = "speler";
        team.voegSpelerToe(this);
    }


    @JsonIgnore public Team getTeam(){
        return mijnTeam;
    }
}
