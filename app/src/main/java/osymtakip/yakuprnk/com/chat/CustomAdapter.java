package osymtakip.yakuprnk.com.chat;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

import osymtakip.yakuprnk.com.R;

/**
 * Created by GGYakup on 14/01/2017.
 */

public class CustomAdapter extends ArrayAdapter<Message> {

    private FirebaseUser firebaseUser;

    public CustomAdapter(Context context, ArrayList<Message> chatList, FirebaseUser firebaseUser) {
        super(context, 0, chatList);
        this.firebaseUser = firebaseUser;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Message message = getItem(position);
        if (firebaseUser.getEmail().equalsIgnoreCase(message.getGonderici())){

            convertView = LayoutInflater.from(getContext()).inflate(R.layout.message_right, parent, false);

           // TextView txtUser = (TextView) convertView.findViewById(R.id.txtUserRight);
            TextView txtMessage = (TextView) convertView.findViewById(R.id.txtMessageRight);
            TextView txtTime = (TextView) convertView.findViewById(R.id.txtTimeRight);

           // txtUser.setText(message.getGonderici());
            txtMessage.setText(message.getMesajText());
            txtTime.setText(message.getZaman());

        }else{

            convertView = LayoutInflater.from(getContext()).inflate(R.layout.message_left, parent, false);

            TextView txtUser = (TextView) convertView.findViewById(R.id.txtUserLeft);
            TextView txtMessage = (TextView) convertView.findViewById(R.id.txtMessageLeft);
            TextView txtTime = (TextView) convertView.findViewById(R.id.txtTimeLeft);

            txtUser.setText(message.getGonderici());
            txtMessage.setText(message.getMesajText());
            txtTime.setText(message.getZaman());

        }

        return convertView;
    }
}
