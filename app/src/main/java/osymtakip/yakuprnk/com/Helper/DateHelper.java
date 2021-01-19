package osymtakip.yakuprnk.com.Helper;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by GGYakup on 17.08.2017.
 */

public class DateHelper {

    public String date_to_date(String nonformat)
    {
        String oldFormat = "yyyy-MM-dd HH:mm:ss";
        String newFormat = "dd.MM.yyyy HH:mm";

        SimpleDateFormat sdf1 = new SimpleDateFormat(oldFormat);
        SimpleDateFormat sdf2 = new SimpleDateFormat(newFormat);

        String formated=null;
        try {
            formated=sdf2.format(sdf1.parse(nonformat));

        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return  formated;

    }
    public String date_to_exclude_sec(String nonformat)
    {
        String oldFormat = "yyyy-MM-dd HH:mm:ss";
        String newFormat = "yyyy-MM-dd HH:mm";

        SimpleDateFormat sdf1 = new SimpleDateFormat(oldFormat);
        SimpleDateFormat sdf2 = new SimpleDateFormat(newFormat);

        String formated=null;
        try {
            formated=sdf2.format(sdf1.parse(nonformat));

        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return  formated;

    }
    public String date_to_just_date(String nonformat)
    {
        String oldFormat = "yyyy-MM-dd HH:mm:ss";
        String newFormat = "dd.MM.yyyy";

        SimpleDateFormat sdf1 = new SimpleDateFormat(oldFormat);
        SimpleDateFormat sdf2 = new SimpleDateFormat(newFormat);

        String formated=null;
        try {
             formated=sdf2.format(sdf1.parse(nonformat));

        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return  formated;

    }

    public String date_to_just_date_re(String nonformat)
    {
        String oldFormat = "yyyy-MM-dd HH:mm:ss";
        String newFormat = "yyyy-MM-dd";

        SimpleDateFormat sdf1 = new SimpleDateFormat(oldFormat);
        SimpleDateFormat sdf2 = new SimpleDateFormat(newFormat);

        String formated=null;
        try {
            formated=sdf2.format(sdf1.parse(nonformat));

        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return  formated;

    }

    public String date_to_just_time(String nonformat)
    {
        String oldFormat = "yyyy-MM-dd HH:mm:ss";
        String newFormat = "HH:mm";

        SimpleDateFormat sdf1 = new SimpleDateFormat(oldFormat);
        SimpleDateFormat sdf2 = new SimpleDateFormat(newFormat);

        String formated=null;
        try {
            formated=sdf2.format(sdf1.parse(nonformat));

        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return  formated;

    }
    public String date_to_just_day_month(String nonformat)
    {
        String oldFormat = "yyyy-MM-dd HH:mm:ss";
        String newFormat = "dd.MM";

        SimpleDateFormat sdf1 = new SimpleDateFormat(oldFormat);
        SimpleDateFormat sdf2 = new SimpleDateFormat(newFormat);

        String formated=null;
        try {
            formated=sdf2.format(sdf1.parse(nonformat));

        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return  formated;

    }
    public String date_to_just_year(String nonformat)
    {
        String oldFormat = "yyyy-MM-dd HH:mm:ss";
        String newFormat = "yyyy";

        SimpleDateFormat sdf1 = new SimpleDateFormat(oldFormat);
        SimpleDateFormat sdf2 = new SimpleDateFormat(newFormat);

        String formated=null;
        try {
            formated=sdf2.format(sdf1.parse(nonformat));

        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return  formated;

    }
    public String date_to_point_date(String nonformat)
    {
        String oldFormat = "yyyy-MM-dd";
        String newFormat = "dd.MM.yyyy";

        SimpleDateFormat sdf1 = new SimpleDateFormat(oldFormat);
        SimpleDateFormat sdf2 = new SimpleDateFormat(newFormat);

        String formated=null;
        try {
            formated=sdf2.format(sdf1.parse(nonformat));

        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return  formated;

    }

    public String date_tr_to_date(String nonformat)
    {
        String oldFormat = "dd-MM-yyyy HH:mm";
        String newFormat = "yyyy-MM-dd HH:mm:ss";

        SimpleDateFormat sdf1 = new SimpleDateFormat(oldFormat);
        SimpleDateFormat sdf2 = new SimpleDateFormat(newFormat);

        String formated=null;
        try {
            formated=sdf2.format(sdf1.parse(nonformat));

        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return  formated;

    }


    public String time_to_hour(String nonformat)
    {
        String oldFormat = "HH:mm";
        String newFormat = "HH";

        SimpleDateFormat sdf1 = new SimpleDateFormat(oldFormat);
        SimpleDateFormat sdf2 = new SimpleDateFormat(newFormat);

        String formated=null;
        try {
            formated=sdf2.format(sdf1.parse(nonformat));

        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return  formated;

    }

    public String time_to_min(String nonformat)
    {
        String oldFormat = "HH:mm";
        String newFormat = "mm";

        SimpleDateFormat sdf1 = new SimpleDateFormat(oldFormat);
        SimpleDateFormat sdf2 = new SimpleDateFormat(newFormat);

        String formated=null;
        try {
            formated=sdf2.format(sdf1.parse(nonformat));

        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return  formated;

    }
    public String date_to_short_date(String nonformat) {
        String oldFormat = "yyyy-MM-dd HH:mm:ss";
        String newFormat = "d MMM";

        SimpleDateFormat sdf1 = new SimpleDateFormat(oldFormat);
        SimpleDateFormat sdf2 = new SimpleDateFormat(newFormat);

        String formated = null;
        try {
            formated = sdf2.format(sdf1.parse("2017-09-12 16:04:04"));

        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return  formated;
    }

    public String current_date()
    {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar c = Calendar.getInstance();

       return  dateFormat.format(c.getTime());

    }

    public String current_date_tr()
    {
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm");
        Calendar c = Calendar.getInstance();

        return  dateFormat.format(c.getTime());

    }

    public String current_just_date()
    {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();

        return  dateFormat.format(c.getTime());

    }


    public String current_just_date_tr()
    {
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        Calendar c = Calendar.getInstance();

        return  dateFormat.format(c.getTime());

    }
    public String current_just_date_tire_tr()
    {
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Calendar c = Calendar.getInstance();

        return  dateFormat.format(c.getTime());

    }

    public String current_time()
    {
        DateFormat dateFormat = new SimpleDateFormat("HH:mm");
        Calendar c = Calendar.getInstance();

        return  dateFormat.format(c.getTime());

    }
    public String current_hour()
    {
        DateFormat dateFormat = new SimpleDateFormat("HH");
        Calendar c = Calendar.getInstance();

        return  dateFormat.format(c.getTime());

    }
    public String current_min()
    {
        DateFormat dateFormat = new SimpleDateFormat("mm");
        Calendar c = Calendar.getInstance();

        return  dateFormat.format(c.getTime());

    }
}
