package com.bss.mintlocker.webservices;

import android.content.Context;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Request.Method;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.RetryPolicy;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.Map;

/**
 * Created by bhawanisingh on 24/09/15.
 */
public class WebService {

    public static final String BASE_URL = "http://bsslife.com/mentlocker/app/";
    public static final String URL_LOGIN = "login/format/json/";
    public static final String URL_REGISTER = "register/format/json/";
    public static final String URL_FORGOTPASSWORD = "forgot_password/format/json/";

    public static final String URL_SSNVALIDATION = "ssn_validation/format/json/";
    public static final String URL_EMAILVALIDATION = "email_validation/format/json/";


/*for login*/
    public static void Login(Context cxt,Map<String, String> data, Listener<JSONObject> listner, ErrorListener error) {
        RequestQueue queue = Volley.newRequestQueue(cxt);



        CustomRequest request = new CustomRequest(Method.POST, BASE_URL + URL_LOGIN, data, listner, error);
        int socketTimeout = 30000;//30 seconds - change to what you want
        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        request.setRetryPolicy(policy);
        queue.add(request);
    }


/*for register*/

    public static void Register(Context cxt,Map<String, String> data, Listener<JSONObject> listner, ErrorListener error) {
        RequestQueue queue = Volley.newRequestQueue(cxt);



        CustomRequest request = new CustomRequest(Method.POST, BASE_URL + URL_REGISTER, data, listner, error);
        int socketTimeout = 30000;//30 seconds - change to what you want
        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        request.setRetryPolicy(policy);
        queue.add(request);
    }

/*for ForgotPassword*/

    public static void ForgotPassword(Context cxt,Map<String, String> data, Listener<JSONObject> listner, ErrorListener error) {
        RequestQueue queue = Volley.newRequestQueue(cxt);



        CustomRequest request = new CustomRequest(Method.POST, BASE_URL + URL_FORGOTPASSWORD, data, listner, error);
        int socketTimeout = 30000;//30 seconds - change to what you want
        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        request.setRetryPolicy(policy);
        queue.add(request);
    }



    /*for login*/
    public static void SSNValidation(Context cxt,Map<String, String> data, Listener<JSONObject> listner, ErrorListener error) {
        RequestQueue queue = Volley.newRequestQueue(cxt);



        CustomRequest request = new CustomRequest(Method.POST, BASE_URL + URL_SSNVALIDATION, data, listner, error);
        int socketTimeout = 30000;//30 seconds - change to what you want
        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        request.setRetryPolicy(policy);
        queue.add(request);
    }


    /*for login*/
    public static void EmailValidation(Context cxt,Map<String, String> data, Listener<JSONObject> listner, ErrorListener error) {
        RequestQueue queue = Volley.newRequestQueue(cxt);



        CustomRequest request = new CustomRequest(Method.POST, BASE_URL + URL_EMAILVALIDATION, data, listner, error);
        int socketTimeout = 30000;//30 seconds - change to what you want
        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        request.setRetryPolicy(policy);
        queue.add(request);
    }



    static class CustomRequest extends Request<JSONObject> {

        private Listener<JSONObject> listener;
        private Map<String, String> params;

        public CustomRequest(String url, Map<String, String> params, Listener<JSONObject> reponseListener,
                             ErrorListener errorListener) {
            super(Method.GET, url, errorListener);
            this.listener = reponseListener;
            this.params = params;
        }

        public CustomRequest(int method, String url, Map<String, String> params, Listener<JSONObject> reponseListener,
                             ErrorListener errorListener) {
            super(method, url, errorListener);
            this.listener = reponseListener;
            this.params = params;
        }

        protected Map<String, String> getParams() throws com.android.volley.AuthFailureError {
            return params;
        };

        @Override
        protected Response<JSONObject> parseNetworkResponse(NetworkResponse response) {
            try {
                String jsonString = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
                return Response.success(new JSONObject(jsonString), HttpHeaderParser.parseCacheHeaders(response));
            } catch (UnsupportedEncodingException e) {
                return Response.error(new ParseError(e));
            } catch (JSONException je) {
                return Response.error(new ParseError(je));
            }
        }

        @Override
        protected void deliverResponse(JSONObject response) {
// TODO Auto-generated method stub
            listener.onResponse(response);
        }
    }



/*    JsonArrayRequest getRequest = new JsonArrayRequest(url,
            new Response.Listener<JSONArray>()
            {
                @Override public void onResponse(JSONArray response) {

                    Log.d("Response", response.toString());
                }
            },
            new Response.ErrorListener()
            {
                @Override public void onErrorResponse(VolleyError error) {
                    Log.d("Error.Response", error.toString());
                }
            }
    );
//    VolleySingleton.getInstance(this).addToRequestQueue(getRequest); //Call to get dashboard feed
}*/



    static class CustomRequestJsonArray extends Request<JSONArray> {

        private Listener<JSONArray> listener;
        private Map<String, String> params;

        public CustomRequestJsonArray(String url, Map<String, String> params, Listener<JSONArray> reponseListener,
                             ErrorListener errorListener) {
            super(Method.GET, url, errorListener);
            this.listener = reponseListener;
            this.params = params;
        }

        public CustomRequestJsonArray(int method, String url, Map<String, String> params, Listener<JSONArray> reponseListener,
                             ErrorListener errorListener) {
            super(method, url, errorListener);
            this.listener = reponseListener;
            this.params = params;
        }

        protected Map<String, String> getParams() throws com.android.volley.AuthFailureError {
            return params;
        };



        @Override
        protected Response<JSONArray> parseNetworkResponse(NetworkResponse response) {
            try {
                String jsonString = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
                return Response.success(new JSONArray(jsonString), HttpHeaderParser.parseCacheHeaders(response));
            } catch (UnsupportedEncodingException e) {
                return Response.error(new ParseError(e));
            } catch (JSONException je) {
                return Response.error(new ParseError(je));
            }
        }

        @Override
        protected void deliverResponse(JSONArray response) {
// TODO Auto-generated method stub
            listener.onResponse(response);
        }
    }



}
