package ts.mm.domein;

import java.io.Serializable;

public class Organisator extends Persoon implements Serializable {
    public Organisator(String nm, String ww, Match m){
        super(nm, ww, m);
        this.role = "organisator";
    }
}

