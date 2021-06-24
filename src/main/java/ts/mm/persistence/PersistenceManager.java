package ts.mm.persistence;


import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobContainerClientBuilder;
import ts.mm.domein.Match;
import ts.mm.domein.Persoon;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class PersistenceManager {
    private final static String ENDPOINT = "https://matchmakerblob.blob.core.windows.net/";
    private final static String SASTOKEN = "?sv=2020-02-10&ss=b&srt=sco&sp=rwdlacx&se=2023-06-20T22:00:00Z&st=2021-06-23T22:00:00Z&spr=https,http&sig=dUaU2LznLwFzMBVlDhJMQ9IzmkYUjkkRXGreLRtvCyg%3D";
    private final static String CONTAINER = "matchcontainer";


    private static final BlobContainerClient blobContainer = new BlobContainerClientBuilder()
            .endpoint(ENDPOINT)
            .sasToken(SASTOKEN)
            .containerName(CONTAINER)
            .buildClient();

    public static void LoadMatches() throws Exception{
        System.out.println("Loading matches");
        if (blobContainer.exists()){
            BlobClient blob = blobContainer.getBlobClient("matchcontainer");
            if (blob.exists()){
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                blob.download(baos);

                ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
                ObjectInputStream ois = new ObjectInputStream(bais);

                ArrayList<Match> matches = (ArrayList<Match>) ois.readObject();
                Match.alleMatches = matches;

                baos.close();
                bais.close();
                ois.close();
                System.out.println("Matches loaded");

            }
        }
    }

    public static void saveMatches() throws Exception{
        System.out.println("Saving matches");
        if (!blobContainer.exists()){
            blobContainer.create();
        }
        BlobClient blob = blobContainer.getBlobClient("matchcontainer");
        ArrayList<Match> MatchesToSave = Match.alleMatches;

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(MatchesToSave);

        byte[] bytes = baos.toByteArray();
        ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
        blob.upload(bais, bytes.length, true);

        baos.close();
        oos.close();
        bais.close();
        System.out.println("Matches saved");
    }

}
