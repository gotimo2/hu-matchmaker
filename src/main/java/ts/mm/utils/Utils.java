package ts.mm.utils;

import ts.mm.domein.Match;

import java.util.Random;

public class Utils {

    private static final String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

    public static String generateString(Random rng, String characters, int length)
    {
        char[] text = new char[length];
        for (int i = 0; i < length; i++)
        {
            text[i] = characters.charAt(rng.nextInt(characters.length()));
        }
        return new String(text);
    }

    public static String generateUniqueID(){
        String StringToTry = generateString(new Random(), chars, 10 );
        if (Match.zoekMatch(StringToTry) != null){
            return generateUniqueID();
        }
        else{
            return StringToTry;
        }
    }
}
