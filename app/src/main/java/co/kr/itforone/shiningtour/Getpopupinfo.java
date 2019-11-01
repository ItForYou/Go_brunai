package co.kr.itforone.shiningtour;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class Getpopupinfo extends StringRequest {

    private static final String URL = "http://shiningtour.itforone.co.kr/bbs/volley.get_popup.php";
    private Map<String, String> parameters = new HashMap();

    public Getpopupinfo(Response.Listener<String> paramListener)
    {
        super(Request.Method.POST, URL, paramListener, null);
    }
    @Override
    public Map<String, String> getParams()
    {
        return this.parameters;
    }

}
