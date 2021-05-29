package ts.mm.domein;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;
import ts.mm.utils.Utils;

public class Match {
    private static ArrayList<Match> alleMatches = new ArrayList<Match>();
    private String naam;
    private String id;
    public ArrayList<Team> teams = new ArrayList<Team>();
    private Organisator organisator;

    public Match(String nm, String id){
        this.naam = nm;
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Match match = (Match) o;
        return Objects.equals(naam, match.naam) && Objects.equals(id, match.id);
    }

    public void verwijder(String ww){
        for(Match m: alleMatches){
            if(this.equals(m)){
                alleMatches.remove(m);
            }
        }
    }

    public static Match zoekMatch(String zoekID){
        for(Match m : alleMatches){
            if (m.id.equals(zoekID)){
                return m;
            }
        }
        return null;
    }
    public ArrayList<Team> getTeams() {
        return teams;
    }

    public Team getTeam(String nm){
       for(Team t: teams){
           if(nm.equals(t.getNaam())){
               return t;
           }
       }
       return null;
    }

    public static Match matchFromPost(String nm, String OrgPass, String OrgName){
        String id = Utils.generateUniqueID();

        Match outputMatch = new Match(nm, id);
        outputMatch.organisator = new Organisator(OrgName, OrgPass, outputMatch);
        outputMatch.teams.add(new Team("Team 1", 1, 5, outputMatch));
        outputMatch.teams.add(new Team("Team 2", 2, 5, outputMatch));
        alleMatches.add(outputMatch);
        return outputMatch;
    }

    @Override
    public String toString(){
        return String.format("%s met organisator %s");
    }
}
