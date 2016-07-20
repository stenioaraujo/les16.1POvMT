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
import java.util.Date;
import java.util.List;
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
    private ValueEventListener listener;
    private static DAO dao;

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

    public List<TimeInput> getTimeInputs(Date start, Date end, Atividade atv) {
        List<TimeInput> timeInputs = new ArrayList<>();

        StringBuffer bf = new StringBuffer();
        for (TimeInput ti: userData.getTimeInputs()) {
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
        List<Atividade> atividades = userData.getAtividades();

        for (Atividade atv: atividades) {
            List<TimeInput> timeInputs = getTimeInputs(start, end, atv);
            Long minutes = getTotalMinutes(timeInputs);

            String hours = getHours(minutes).toString();
            String min = String.valueOf((minutes - getHours(minutes)));

            TiView tiView = new TiView(context, hours, min, atv, timeInputs);
            tiViews.add(tiView);
        }

        return tiViews;
    }

    public Long getTotalMinutes(List<TimeInput> timeInputs) {
        Long totalMinutes = 0L;

        for (TimeInput ti: timeInputs) {
            totalMinutes += ti.getTime();
        }

        return totalMinutes;
    }

    public Long getHours(Long minutes) {
        return minutes/60;
    }
}
