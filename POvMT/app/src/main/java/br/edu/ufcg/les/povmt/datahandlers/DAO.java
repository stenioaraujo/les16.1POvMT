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
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import br.edu.ufcg.les.povmt.models.Atividade;
import br.edu.ufcg.les.povmt.models.AtividadeView;
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
        this.firebaseRef = FirebaseDatabase.getInstance().getReference();

        this.listeners = new HashSet<ValueEventListener>();

        initialize();
    }

    private void createUserData() {
        userData = new UserData(mFirebaseUser.getUid());
        userData.setNome(mFirebaseUser.getDisplayName());
    }

    public void initialize() {
        for(ValueEventListener listener: listeners) {
            firebaseRef.removeEventListener(listener);
        }

        mFirebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        firebaseRef = FirebaseDatabase.getInstance().getReference();
        firebaseRef = firebaseRef.child("users").child(mFirebaseUser.getUid());

        this.listeners = new HashSet<ValueEventListener>();
        listeners.add(addListenerFirebase());

        createUserData();
    }


    /**
     * SINGLETON, recupera a instância do DB
     * @return a instância do DB
     */
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

                    Log.v("DB_UPDATED", "Acabei de ser modificado por " + mFirebaseUser.getDisplayName() + " " + mFirebaseUser.getUid());
                } catch (Throwable t) {
                    Log.e("DB_ERROR", t.getMessage() + "");
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e("DB_ERROR_CANCEL", databaseError.getMessage());
            }
        });
    }

    /**
     * Adiciona uma nova Atividade a lista de Atividades
     * @param atividade A Atividade
     */
    public void add(Atividade atividade) {
        Map<String, Atividade> atividades = userData.getAtividades();

        if (!atividades.values().contains(atividade)) {
            String key = firebaseRef.child("atividades").push().getKey();
            atividades.put(key, atividade);
        }

       // userData.setAtividades(atividades);
    }

    /**
     * Adiciona uma nova TimeInput a lista de TimeInputs
     * @param ti A TimeInput
     */
    public void add(TimeInput ti) {
        if(userData.getTimeInputs() == null){
            userData.setTimeInputs(new HashMap<String, TimeInput>());
        }
        Map<String, TimeInput> timeInputs = userData.getTimeInputs();

        if (!timeInputs.values().contains(ti)) {
            String key = firebaseRef.child("timeInputs").push().getKey();
            timeInputs.put(key, ti);
        }
    }

    /**
     * Equivalente a getAtividade(String atvNome).
     * IMPORTANTE: Alterar um objeto Atividade não altera as TimeInputs que tem ele como Pai.
     * Se quiser alterar uma Atividade use updateAtividade(Atividade antiga, Atividade nova).
     *      *
     * @param atv A Atividade
     * @return A Atividade
     */
    public Atividade getAtividade(Atividade atv) {
        if (atv != null)
            return getAtividade(atv.getName());

        return null;
    }

    public ArrayList<Atividade> getAllTasks(){
        ArrayList<Atividade> listAllTasks = new ArrayList<Atividade>();
        for(Atividade atv: userData.getAtividades().values()){
            listAllTasks.add(atv);
        }

        return listAllTasks;
    }

    public ArrayList<TimeInput> getAllTimeInputs(){
        ArrayList<TimeInput> listAllTimeInputs = new ArrayList<TimeInput>();
        for(TimeInput ti: userData.getTimeInputs().values()){
            listAllTimeInputs.add(ti);
        }

        return listAllTimeInputs;
    }


    /**
     * Recupera um objeto Atividade que tem atvNome como Nome
     * @param atvNome O nome da Atividade
     * @return O objeto Atividade com o mesmo nome
     */
    public Atividade getAtividade(String atvNome) {
        for (Atividade atv: userData.getAtividades().values()) {
            if (atv.getName().equals(atvNome)) {
                return atv;
            }
        }

        return null;
    }

    /**
     * @param start A data inicial do intervalo.
     * @param end A data final do intervalo (incluso)
     * @param atv A Atividade pai
     * @return Retorna todas as TimeInputs que tem atv como Atividade pai em um intervalo de tempo.
     */
    public List<TimeInput> getTimeInputs(Date start, Date end, Atividade atv) {
        List<TimeInput> timeInputs = new ArrayList<>();

        for (TimeInput ti: userData.getTimeInputs().values()) {
            Date criado = ti.getDataCriacao();
            if (ti.getAtvPai().equals(atv)) {
                if (criado.after(start) && criado.before(end) ||
                        criado.equals(start) || criado.equals(end)) {
                    timeInputs.add(ti);
                }
            }
        }

        return timeInputs;
    }

    /**
     * AtividadeViews são utilizadas para armazenar os dados necessários para exibir nas Views.
     * Uma AtividadeView é criada apartir das informações de uma Atividade e seus TimeInputs em um determinado intervalo de tempo
     * Todas os TimeInputs de um intervalo são considerados, não importando a Atividade pai
     * @param context O context da View que vai utilizar as AtividadeViews
     * @param start A data inicial do intervalo.
     * @param end A data final do intervalo (incluso)
     * @return Um List com todas as AtividadeViews de start até end (incluso)
     */
    public List<AtividadeView> getAtividadeViews(Context context, Date start, Date end) {
        List<AtividadeView> AtividadeViews = new ArrayList<>();
        if(userData.getAtividades() == null){
            userData.setAtividades(new HashMap<String, Atividade>());
        }
        Collection<Atividade> atividades = userData.getAtividades().values();

        for (Atividade atv: atividades) {
            List<TimeInput> timeInputs = getTimeInputs(start, end, atv);
            Long minutes = getTotalMinutes(timeInputs);

            String hours = getHours(minutes).toString();
            String min = String.valueOf((minutes - getHours(minutes)*60));

            AtividadeView AtividadeView = new AtividadeView(context, hours, min, atv, timeInputs);
            AtividadeViews.add(AtividadeView);
        }

        return AtividadeViews;
    }

    /**
     * @param timeInputs Lista de TimeInputs
     * @return A soma de todos os minutos em timeInputs
     */
    public static Long getTotalMinutes(List<TimeInput> timeInputs) {
        Long totalMinutes = 0L;

        for (TimeInput ti: timeInputs) {
            totalMinutes += ti.getTime();
        }

        return totalMinutes;
    }

    /**
     * @param minutes Minutos
     * @return Retorna as horas COMPLETAS que minutes tem.
     */
    public static Long getHours(Long minutes) {
        return minutes/60;
    }

    /**
     * NECESSÁRIO para atualizar o Banco de dados.
     * Este método é separado para questões de uso dos dados. Permitir que os dados sejam enviados em blocos.
     */
    public void update() {
        if (userData != null)
            firebaseRef.setValue(userData);
    }

    /**
     * Adiciona Um Listener ao DB. Um Listener é chamado quando alguma alteração é detectada no Banco de dados.
     * @param listener O objeto que será usado sempre que houver uma modificação no DB
     */
    public void addListener(ValueEventListener listener) {
        this.listeners.add(firebaseRef.addValueEventListener(listener));
    }

    /**
     * @return Retorna o Uid do usuário que está logado
     */
    public String getUid() {
        return userData.getUid();
    }

    /**
     * @return Retorna o nome do usuário que está logado
     */
    public String getUserName() { return userData.getNome(); }

    /**
     * Retorna uma lista de Atividades que começam com text. Utilizado no campo de autofill.
     * NÂO É CASE SENSITIVE
     * @param text texto que deve ter no início das Atividades
     * @return A lista de atividades que começam com text.
     */
    public List<Atividade> getAtividadesStartingWith(String text) {
        List<Atividade> atividades = new ArrayList<>();

        for (Atividade atv: userData.getAtividades().values()) {
            if (text != null || atv.getName().toLowerCase().startsWith(text.toLowerCase()))
                atividades.add(atv);
        }

        return atividades;
    }

    /**
     * Todos os TimeInputs que tiverem atv como Atividade pai serão removidos.
     * @param atv Atividade pai
     */
    public void removeTimeInputs(Atividade atv) {
        if (atv == null) return;

        Map<String, TimeInput> tis = userData.getTimeInputs();
        Set<String> tiIDs = new HashSet<>(tis.keySet());

        for (String tiID: tiIDs) {
            if (tis.get(tiID) != null && atv.equals(tis.get(tiID).getAtvPai())) {
                tis.remove(tiID);
            }
        }
    }

    /**
     * Remove uma Atividade da lista de Atividade.
     * TODAS os TimeInputs que têm atv como Atividade pai também serão removidos.
     *
     * @param atv A Atividade
     */
    public void removeAtividade(Atividade atv) {
        if (atv == null) return;

        removeTimeInputs(atv);

        Map<String, Atividade> atividades = userData.getAtividades();
        Set<String> atvIDs = new HashSet<>(atividades.keySet());
        for(String atvID: atvIDs) {
            if (atv.equals(atividades.get(atvID))) {
                atividades.remove(atvID);
            }
        }
    }

    /**
     * Remove um TimeInput da lista de TimeInputs
     * @param ti O TimeInput
     */
    public void removeTimeInput(TimeInput ti) {
        if (ti == null) return;

        Map<String, TimeInput> timeInputs = userData.getTimeInputs();
        Set<String> tiIDs = new HashSet<>(timeInputs.keySet());
        for(String tiID: tiIDs) {
            if (ti.equals(timeInputs.get(tiID))) {
                timeInputs.remove(tiID);
            }
        }
    }

    /**
     * Atualiza a Atividade antiga e todas os TimeInputs que tem ela como Atividade Pai.
     * Se a nova Atividade tiver um nome diferente da atividade antiga, mas Ja existir outra atividade com este nome,
     * o update nao sera possivel, nada acontece.
     * @param antiga Um objeto igual a atividade antiga, veja dao.getAtividade() para referencia.
     * @param nova Um objeto com as novas informacoes.
     */
    public void updateAtividade(Atividade antiga, Atividade nova) {
        Atividade atividade = getAtividade(antiga);

        if (antiga != null && nova != null) {
            if (!nova.getName().equals(antiga.getName())
                    && getAtividade(nova.getName()) != null) {
                Log.v("ATIVIDADE_UPDATE_ERROR", "Vc esta tentando renomear " +
                        antiga.getName() + " para " + nova.getName() + ", mas " + nova.getName() +
                        " ja existe");
                return;
            }

            List<TimeInput> timeInputsAtividade = getTimeInputs(new Date(0), new Date(), antiga);

            atividade.setName(nova.getName());
            atividade.setPriority(nova.getPriority());
            atividade.setDescription(nova.getDescription());
            atividade.setTipoAtividade(nova.getTipoAtividade());

            for (TimeInput ti: timeInputsAtividade) {
                ti.setAtvPai(atividade);
            }
        }
    }
}
