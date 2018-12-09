import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static android.support.constraint.Constraints.TAG;

public class FirebaseInterface {
    private DatabaseReference userRef, codeRef;

    public FirebaseInterface(DatabaseReference userRef, DatabaseReference codeRef) {
        this.userRef = userRef;
        this.codeRef = codeRef;
    }

    public DatabaseReference getUserRef() {
        return userRef;
    }

    public DatabaseReference getCodeRef() {
        return codeRef;
    }

    public void setUserRef(DatabaseReference userRef) {
        this.userRef = userRef;
    }

    public void setCodeRef(DatabaseReference codeRef) {
        this.codeRef = codeRef;
    }

    public void addNewUser(String name){
        String id = userRef.push().getKey();
        User user = new User(name);
        userRef.child(id).setValue(user);

    }

    public void addNewCodeSample(String code, String type, String title){
        String id = codeRef.push().getKey();
        CodeSample cs = new CodeSample(code, type, title);

        codeRef.child(id).setValue(cs);
    }




}
