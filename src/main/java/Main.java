import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.FirestoreOptions;
import com.google.cloud.firestore.WriteResult;


public class Main {

    public static void main(String[] args) throws IOException {

        // Setup Google Cloud
        // See: https://cloud.google.com/tools/intellij/docs/quickstart-IDEA
        // See: https://firebase.google.com/docs/firestore/quickstart
        // and See: https://firebase.google.com/docs/admin/setup#initialize_the_sdk

        String projectId = (args.length == 0) ? null : args[0];
        Quickstart quickStart = (projectId != null) ? new Quickstart(projectId) : new Quickstart();

        // Step 1B
        FirestoreOptions firestoreOptions =
                FirestoreOptions.getDefaultInstance().toBuilder()
                        .setProjectId("anothergcloudproject")
                        .build();
        Firestore db = firestoreOptions.getService();

        // Step 2
        DocumentReference docRef = db.collection("users").document("alovelace");
// Add document data  with id "alovelace" using a hashmap
        Map<String, Object> data = new HashMap<>();
        data.put("first", "Ada");
        data.put("last", "Lovelace");
        data.put("born", 1815);
//asynchronously write data
        ApiFuture<WriteResult> result = docRef.set(data);
// ...
// result.get() blocks on response


        try
        {
            System.out.println("Update time : " + result.get().getUpdateTime());
        }
        catch(ExecutionException e)
        {
            // this part is executed when an exception (in this example InterruptedException) occurs
        }
        catch(InterruptedException i)
        {

        }

        DocumentReference docRef2 = db.collection("users").document("aturing");
// Add document data with an additional field ("middle")
        Map<String, Object> data2 = new HashMap<>();
        data.put("first", "Alan");
        data.put("middle", "Mathison");
        data.put("last", "Turing");
        data.put("born", 1912);

        ApiFuture<WriteResult> result2 = docRef2.set(data2);

        try
        {
            System.out.println("Update time : " + result.get().getUpdateTime());
        }
        catch(ExecutionException e)
        {
            // this part is executed when an exception (in this example InterruptedException) occurs
        }
        catch(InterruptedException i)
        {

        }





    }

}


