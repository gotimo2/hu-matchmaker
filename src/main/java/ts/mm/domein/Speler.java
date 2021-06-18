package ts.mm.domein;

public class Speler extends Persoon {
    private Team mijnTeam;
    private Match match;
    public Speler(String nm, String ww, Team team, Match m) {
        super(nm, ww, m);
        this.mijnTeam = team;
        this.role = "speler";
    }


    public Team getTeam(){
        return mijnTeam;
    }
}
