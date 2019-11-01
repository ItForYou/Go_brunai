package co.kr.itforone.shiningtour;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class uploadProfile  extends StringRequest{
    private static final String URL = "http://shiningtour.itforone.co.kr/Android.uploadprofile.php";
    private Map<String, String> parameters = new HashMap();

    public uploadProfile(String id, String img, String filename, Response.Listener<String> paramListener)
    {
        super(Method.POST, URL, paramListener, null);
        this.parameters.put("id", id);
        this.parameters.put("img", img);
        this.parameters.put("filename", filename);
    }
    @Override
    public Map<String, String> getParams()
    {
        return this.parameters;
    }
}
