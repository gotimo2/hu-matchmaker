package ts.mm.domein;

import com.fasterxml.jackson.annotation.JsonProperty;
import ts.mm.utils.Utils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

public class Match implements Serializable {
    public static ArrayList<Match> alleMatches = new ArrayList<Match>();

    @JsonProperty("matchname")
    private String naam;
    private String id;
    public ArrayList<Team> teams = new ArrayList<Team>();
    private Organisator organisator;


    public Match(String matchname, String orgname, String orgpass){
        this.naam = matchname;
        this.id = Utils.generateUniqueID();
        this.organisator = new Organisator(orgname, orgpass, this);
        this.teams.add(new Team("Team 1", 1, 5, this));
        this.teams.add(new Team("Team 2", 2, 5, this));
        alleMatches.add(this);
    }

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

    public void verwijder(){
        alleMatches.remove(this);
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

    public Team getTeam(int num){
       for(Team t: teams){
           if(num == t.getNummer()){
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


    public String toString(){
        return String.format("%s met organisator %s", this.naam, this.organisator.getNaam() );
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setTeams(ArrayList<Team> teams) {
        this.teams = teams;
    }

    public Organisator getOrganisator() {
        return organisator;
    }

    public void setOrganisator(Organisator organisator) {
        this.organisator = organisator;
    }

}
