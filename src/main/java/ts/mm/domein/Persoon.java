package ts.mm.domein;

public class Persoon {
    private String naam;
    private String wachtwoord;

    public void setWachtwoord(String wachtwoord) {
        this.wachtwoord = wachtwoord;
    }

    public Persoon(String nm, String ww){
        this.naam = nm;
        this.wachtwoord = ww;
    }

    public String getNaam() {
        return naam;
    }

    public String getWachtwoord() {
        return wachtwoord;
    }
}
