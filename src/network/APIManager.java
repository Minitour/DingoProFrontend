package network;

import com.google.common.io.ByteStreams;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import com.sun.security.ntlm.Server;
import javafx.application.Platform;
import model.*;
import net.sf.jasperreports.engine.JasperPrint;
import okhttp3.*;
import sun.rmi.runtime.Log;

import java.io.*;
import java.lang.reflect.Type;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.function.BiConsumer;

/**
 * Created By Tony on 14/02/2018
 */
public class APIManager {

    public static APIManager getInstance(){
        return manager;
    }
    //singleton
    private final static APIManager manager = new APIManager();
    private final OkHttpClient client;
    private final String TAG = "API-MANAGER";

    private static Gson gson;

    static {
        gson = new Gson();
    }

    /**
     * Private constructor
     */
    private APIManager(){

        client = new OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .build();
    }

    /**
     *
     * Use this method to login.
     *
     * @param email The user's email
     * @param password The user's password
     * @param callback The callback.
     */
    public void login(String email, String password, Callbacks.Auth callback){
        JsonObject data = new JsonObject();
        data.addProperty("email",email);
        data.addProperty("password",password);

        makeRequest(Constants.Routes.login(),null,data,(json, ex) -> {

            ServerResponse r = new ServerResponse(json);

            if(ex == null && r.isOK()){
                JsonObject d = json.get("data").getAsJsonObject();
                String id = d.get("id").getAsString();
                String token = d.get("sessionToken").getAsString();
                int roleId = d.get("role").getAsInt();
                AutoSignIn.ID = id;
                AutoSignIn.SESSION_TOKEN = token;
                AutoSignIn.ROLE_ID = roleId;
                AutoSignIn.EMAIL = email;
                callback.make(r,id,token,roleId,null);
            }else
                callback.make(r,null,null,-1,ex);

        });
    }

    /**
     * Update password method.
     *
     * @param id The id of the current user.
     * @param token The session token of the user.
     * @param currentPassword The current password of the account.
     * @param newPassword The new password the user wishes to set.
     * @param callback The response callback.
     */
    public void updatePassword(String id,String token,String currentPassword,String newPassword,Callbacks.General callback){
        JsonObject body = new JsonObject();
        body.addProperty("id",id);
        body.addProperty("sessionToken",token);
        body.addProperty("currentPassword",currentPassword);
        body.addProperty("newPassword",newPassword);

        makeRequest(Constants.Routes.updatePassword(),null,body,(json, ex) ->
                callback.make(new ServerResponse(json),ex));
    }

    public void updatePassword(String currentPassword,String newPassword,Callbacks.General callback){
        updatePassword(AutoSignIn.ID,AutoSignIn.SESSION_TOKEN,currentPassword,newPassword,callback);
    }


    /**
     * This method makes a post HTTP request to a url using the given params.
     *
     * @param url The route to make http request to.
     * @param jsonBody The parameters to pass in.
     * @param callback The call back function.
     */
    private void makeRequest(String url,
                             Map<String,String> headers,
                             JsonObject jsonBody,
                             final Callbacks.Inner callback){
        //define media type
        MediaType mediaType = MediaType.parse("application/json; charset=utf-8");
        //create request body from params

        RequestBody body = RequestBody.create(mediaType,jsonBody.toString());
        //create request
        Request request;
        Request.Builder builder = new Request
                .Builder()
                .url(url)
                .post(body)
                .addHeader("content-type","application/json");

        //add additional headers
        if(headers != null)
            headers.forEach(builder::addHeader);

        request = builder.build();


        //make request
        System.out.println("SENDING: "+jsonBody.toString());
        makeOkHttpRequest(request,callback);
    }


    /**
     * Make generic OKHttpRequest
     * @param request The request object.
     * @param callback The callback.
     */
    private void makeOkHttpRequest(Request request,Callbacks.Inner callback){
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                if (callback != null)
                    callback.make(null,e);
                System.err.println( "onFailure: " + e.toString());
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {

                if (callback!= null){
                    try (ResponseBody responseBody = response.body()) {
                        String res = responseBody.string();
                        System.out.println("onResponse: " + res);

                        //make thread safe.
                        Platform.runLater(() -> {
                            try{
                                JsonParser parser = new JsonParser();
                                JsonObject o = parser.parse(res).getAsJsonObject();
                                callback.make(o,null);
                            }catch (Exception e) {
                                callback.make(null, e);
                            }

                        });
                        responseBody.close();
                    }
                }
            }
        });
    }

    /**
     * Helper method that converts a generic object into a Json Object.
     * @param object The object you wish to convert.
     * @param <T> The type of the object (auto-inferred)
     * @return The json equivalent of the object.
     */
    private <T> JsonObject toJson(T object){
        JsonElement jsonElement = gson.toJsonTree(object,object.getClass());
        return (JsonObject) jsonElement;
    }
}
