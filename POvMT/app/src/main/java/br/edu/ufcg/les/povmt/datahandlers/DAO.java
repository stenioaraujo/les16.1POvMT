package br.edu.ufcg.les.povmt.datahandlers;

import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import br.edu.ufcg.les.povmt.models.Atividade;
import br.edu.ufcg.les.povmt.models.TimeInput;
import br.edu.ufcg.les.povmt.models.UserData;

/**
 * Created by stenio e kallynnykarlla on 7/20/2016.
 */
public class DAO {
    private DatabaseReference firebaseRef;
    private UserData userData;
    private FirebaseUser mFirebaseUser;
    private String uid;
    private ValueEventListener listener;

    public DAO() {
        initialize();

        this.firebaseRef = FirebaseDatabase.getInstance().getReference();
        this.firebaseRef = firebaseRef.child("users").child(userData.getUid());

        this.listener = addListenerFirebase();
    }

    private void initialize() {
        mFirebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        userData = new UserData(mFirebaseUser.getUid());
        userData.setLastLogin(new Date());
        userData.setNome(mFirebaseUser.getDisplayName());
    }

    private ValueEventListener addListenerFirebase() {
        return firebaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                try {
                    userData = dataSnapshot.getValue(UserData.class);
                    Log.v("DB_UPDATED", "Acabei de ser modificado");
                } catch (Throwable t) {
                    Log.e("DB_ERROR", t.getMessage());
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e("DB_ERROR_CANCEL", databaseError.getMessage());
            }
        });
    }

    public void add(Atividade atividade) {
        List<Atividade> atividades = userData.getAtividades();

        if (!atividades.contains(atividade)) {
            atividades.add(atividade);
            firebaseRef.setValue(userData);
        }
    }

    public void add(TimeInput ti) {
        List<TimeInput> timeInputs = userData.getTimeInputs();

        if (!timeInputs.contains(ti)) {
            timeInputs.add(ti);
            firebaseRef.setValue(userData);
        }
    }

    public Atividade getAtividade(Atividade atv) {
        return getAtividade(atv.getName());
    }

    public Atividade getAtividade(String atvNome) {
        for (Atividade atv: userData.getAtividades()) {
            if (atv.getName().equals(atvNome)) {
                return atv;
            }
        }

        return null;
    }
}
