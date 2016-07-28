package br.edu.ufcg.les.povmt.datahandlers;

import android.content.Context;
import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import br.edu.ufcg.les.povmt.models.Atividade;
import br.edu.ufcg.les.povmt.models.TiView;
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
    private Set<ValueEventListener> listeners;
    private static DAO dao;

    private DAO() {
        initialize();

        this.firebaseRef = FirebaseDatabase.getInstance().getReference();
        this.firebaseRef = firebaseRef.child("users").child(userData.getUid());

        this.listeners = new HashSet<ValueEventListener>();
        listeners.add(addListenerFirebase());
    }

    private void initialize() {
        mFirebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        userData = new UserData(mFirebaseUser.getUid());
        userData.setLastLogin(new Date());
        userData.setNome(mFirebaseUser.getDisplayName());
    }

    public static DAO getInstance() {
        if (dao == null)
            dao = new DAO();

        return dao;
    }

    private ValueEventListener addListenerFirebase() {
        return firebaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                try {
                    UserData newUserData = dataSnapshot.getValue(UserData.class);

                    if (newUserData != null)
                        userData = newUserData;

                    Log.v("DB_UPDATED", "Acabei de ser modificado por " + userData.getUid());
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
        Map<String, Atividade> atividades = userData.getAtividades();

        if (!atividades.values().contains(atividade)) {
            String key = firebaseRef.child("atividades").push().getKey();
            atividades.put(key, atividade);
        }
    }

    public void add(TimeInput ti) {
        Map<String, TimeInput> timeInputs = userData.getTimeInputs();

        if (!timeInputs.values().contains(ti)) {
            String key = firebaseRef.child("timeInputs").push().getKey();
            timeInputs.put(key, ti);
        }
    }

    public Atividade getAtividade(Atividade atv) {
        return getAtividade(atv.getName());
    }

    public Atividade getAtividade(String atvNome) {
        for (Atividade atv: userData.getAtividades().values()) {
            if (atv.getName().equals(atvNome)) {
                return atv;
            }
        }

        return null;
    }

    public List<TimeInput> getTimeInputs(Date start, Date end, Atividade atv) {
        List<TimeInput> timeInputs = new ArrayList<>();

        StringBuffer bf = new StringBuffer();
        for (TimeInput ti: userData.getTimeInputs().values()) {
            Date criado = ti.getDataCriacao();
            if (ti.getAtvPai().equals(atv)) {
                bf.append(atv.getName() + "\n");
                if (criado.after(start) && criado.before(end) ||
                        criado.equals(start) || criado.equals(end)) {
                    timeInputs.add(ti);
                }
            }
        }

        return timeInputs;
    }

    public List<TiView> getTiViews(Context context, Date start, Date end) {
        List<TiView> tiViews = new ArrayList<>();
        Collection<Atividade> atividades = userData.getAtividades().values();

        for (Atividade atv: atividades) {
            List<TimeInput> timeInputs = getTimeInputs(start, end, atv);
            Long minutes = getTotalMinutes(timeInputs);

            String hours = getHours(minutes).toString();
            String min = String.valueOf((minutes - getHours(minutes)*60));

            TiView tiView = new TiView(context, hours, min, atv, timeInputs);
            tiViews.add(tiView);
        }

        return tiViews;
    }

    public static Long getTotalMinutes(List<TimeInput> timeInputs) {
        Long totalMinutes = 0L;

        for (TimeInput ti: timeInputs) {
            totalMinutes += ti.getTime();
        }

        return totalMinutes;
    }

    public static Long getHours(Long minutes) {
        return minutes/60;
    }

    public void update() {
        if (userData != null)
            firebaseRef.setValue(userData);
    }

    public void addListener(ValueEventListener listener) {
        this.listeners.add(firebaseRef.addValueEventListener(listener));
    }
}
