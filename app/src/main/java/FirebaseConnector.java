import com.google.firebase.database.DatabaseReference;

public class FirebaseConnector {
    protected DatabaseReference dataRef;

    public FirebaseConnector(DatabaseReference dataRef) {
        this.dataRef = dataRef;
    }

    public void addNewCodeSample(String csId, CodeSample cs){
        dataRef.child("codesample").child(csId).setValue(cs);
    }

    public void addNewUser(String uId, User user){
        dataRef.child("user").child(uId).setValue(user);
    }
}
