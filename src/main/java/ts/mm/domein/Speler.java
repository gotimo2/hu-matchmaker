package ts.mm.domein;

public class Speler extends Persoon {
    private Team mijnTeam;
    public Speler(String nm, String ww, Team team) {
        super(nm, ww);
        this.mijnTeam = team;
    }

    public Team getTeam(){
        return mijnTeam;
    }
}
