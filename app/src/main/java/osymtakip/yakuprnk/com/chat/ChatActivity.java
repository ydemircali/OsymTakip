package osymtakip.yakuprnk.com.chat;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import osymtakip.yakuprnk.com.Helper.DateHelper;
import osymtakip.yakuprnk.com.R;

public class ChatActivity extends AppCompatActivity {

    private FirebaseDatabase db;
    private DatabaseReference dbRef;
    private FirebaseUser fUser;
    private ArrayList<Message> chatLists = new ArrayList<>();
    private CustomAdapter customAdapter;
    private String subject;
    private ListView listView;
    private RelativeLayout sent_icon;
    private EditText inputChat;
    DateHelper dateHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        dateHelper=new DateHelper();

        listView = (ListView)findViewById(R.id.chatListView);
        inputChat = (EditText)findViewById(R.id.inputMessage);
        sent_icon = (RelativeLayout)findViewById(R.id.sent_icon_layout);

        db = FirebaseDatabase.getInstance();
        fUser = FirebaseAuth.getInstance().getCurrentUser();


        customAdapter = new CustomAdapter(getApplicationContext(),chatLists,fUser);
        listView.setAdapter(customAdapter);

        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            // referansa ulaşıp ilgili sohbetleri getirebilmemiz için gerekli yapı
            subject = bundle.getString("subject");
            dbRef = db.getReference("ChatSubjects/"+subject+"/mesaj");
            setTitle(subject);
        }


        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                chatLists.clear();
                for(DataSnapshot ds : dataSnapshot.getChildren()){
                    Message message = ds.getValue(Message.class);
                    chatLists.add(message);
                    //Log.d("VALUE",ds.getValue(Message.class).getMesajText());
                }
                customAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        sent_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(inputChat.getText().length()>=4){

                    long msTime = System.currentTimeMillis();
                    String dateTime=dateHelper.current_date_tr();
                    Message message = new Message(inputChat.getText().toString(),fUser.getEmail(),dateTime);
                    dbRef.push().setValue(message);
                    inputChat.setText("");

                }else{

                    Toast.makeText(getApplicationContext(),"Gönderilecek mesaj uzunluğu en az 3 karakter olmalıdır!",Toast.LENGTH_SHORT).show();
                }


            }
        });


    }
}
