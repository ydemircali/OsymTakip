package osymtakip.yakuprnk.com.Helper;

/**
 * Created by HP PC on 20.7.2017.
 */
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class SessionManager {

    SharedPreferences pref;

    Editor editor;
    Context _context;

    // Shared pref mode
    int PRIVATE_MODE = 0;

    // Shared preferences file name
    private static final String PREF_NAME = "OsymTakip";

    public SessionManager(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    public void setLogin(boolean isLoggedIn) {

        editor.putBoolean("isLoggedIn", isLoggedIn);
        editor.commit();
    }
    public boolean isLoggedIn(){
        return pref.getBoolean("isLoggedIn", false);
    }

    public void setUserId(String userId){
        editor.putString("userid", userId);
        editor.commit();
    }
    public String getUserId(){return pref.getString("userid",null);}

    public void setSinavlar(String sinavlar){
        editor.putString("sinavlar", sinavlar);
        editor.commit();
    }
    public String getSinavlar(){return pref.getString("sinavlar",null);}

    public void setDuyurular(String duyurular){
        editor.putString("duyurular", duyurular);
        editor.commit();
    }
    public String getDuyurular(){return pref.getString("duyurular",null);}

    public void setWidgetId(int widgetId){
        editor.putInt("widgetId", widgetId);
        editor.commit();
    }
    public int getWidgetId(){return pref.getInt("widgetId",0);}
}