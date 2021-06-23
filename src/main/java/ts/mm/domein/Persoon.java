package ts.mm.domein;

import com.fasterxml.jackson.annotation.JsonIgnore;
import ts.mm.utils.Utils;

import java.io.Serializable;
import java.security.Principal;
import java.util.ArrayList;

public class Persoon implements Principal, Serializable {
    private String naam;
    private String UID;
    @JsonIgnore private String wachtwoord;
    public static ArrayList<Persoon> allePersonen = new ArrayList<Persoon>();
    public String role;
    @JsonIgnore private Match match;

    public void setWachtwoord(String wachtwoord) {
        this.wachtwoord = wachtwoord;
    }

    public Persoon(String nm, String ww, Match m){
        this.naam = nm;
        this.wachtwoord = ww;
        this.UID = generateUID();
        this.match = m;
        if (getPersoon(nm) != null){
            throw new IllegalArgumentException("username already registered!");
        }
        allePersonen.add(this);
    }

    public static String auth(String name, String password){
        for (Persoon p: allePersonen
             ) {
            if(p.naam.equals(name) && p.wachtwoord.equals(password)){
                return p.role;
            }
        }
        return null;
    }

    public static Persoon getPersoon(String name){
        for (Persoon p: allePersonen
             ) {
            if(p.naam.equals(name)){
                return p;
            }
        }
        return null;
    }

    public String getRole(){
        return this.role;
    }
    public String getName(){
        return this.UID;
    }
    private String generateUID(){
        return Utils.generateUniqueID() + Utils.generateUniqueID();
    }
    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public static Persoon getPersoonID(String ID){
        for (Persoon p: allePersonen
             ) {
            if(p.UID.equals(ID)){
                return p;
            }
        }
        return null;
    }

    public String getUID(){
        return this.UID;
    }
    public String getWachtwoord() {
        return wachtwoord;
    }

    public Match getMatch() {
        return match;
    }
}
