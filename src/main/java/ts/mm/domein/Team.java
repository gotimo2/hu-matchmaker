package ts.mm.domein;

import java.util.ArrayList;

public class Team {
    private String naam;
    private int nummer;
    private int grootte;
    private ArrayList<Speler> spelers = new ArrayList<Speler>();
    private Match match;

    public Team(String nm, int num, int gr, Match m){
        this.naam = nm;
        this.nummer = num;
        this.grootte = gr;
        this.match = m;
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public int getNummer() {
        return nummer;
    }

    public void setNummer(int nummer) {
        this.nummer = nummer;
    }

    public int getGrootte() {
        return grootte;
    }

    public void setGrootte(int grootte) {
        this.grootte = grootte;
    }

    public ArrayList<Speler> getSpelers() {
        return spelers;
    }

    public void voegSpelerToe(Speler sp){
        spelers.add(sp);
    }


    public void setSpelers(ArrayList<Speler> spelers) {
        this.spelers = spelers;
    }


    public void setMatch(Match match) {
        this.match = match;
    }
}
