package ts.mm.domein;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Organisator extends Persoon{
    public Organisator(String nm, String ww, Match m){
        super(nm, ww, m);
        this.role = "organisator";
    }
}

