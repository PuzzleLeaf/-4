package puzzleleaf.tistory.com.custompinktheme;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by cmtyx on 2016-12-30.
 */

public class IconData extends Activity {

    static public void saveValue(Context ctx){
        SharedPreferences prefs = ctx.getSharedPreferences("icon", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt("val", 1);
        editor.commit();
    }

    // 환경설정을 가져온다(실행정보).
    static public int getValue(Context ctx){
        SharedPreferences prefs = ctx.getSharedPreferences("icon", Context.MODE_PRIVATE);
        return prefs.getInt("val",0);//값이 없으면 1로 리턴
    }

    static public void saveAdCount(Context ctx,int temp){
        SharedPreferences prefs = ctx.getSharedPreferences("ad", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt("adCount", temp);
        editor.commit();
    }

    // 환경설정을 가져온다(실행정보).
    static public int getAdCount(Context ctx){
        SharedPreferences prefs = ctx.getSharedPreferences("ad", Context.MODE_PRIVATE);
        return prefs.getInt("adCount",1);//값이 없으면 1로 리턴
    }



}
