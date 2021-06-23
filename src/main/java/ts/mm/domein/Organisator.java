package ts.mm.domein;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;

public class Organisator extends Persoon implements Serializable {
    public Organisator(String nm, String ww, Match m){
        super(nm, ww, m);
        this.role = "organisator";
    }
}

