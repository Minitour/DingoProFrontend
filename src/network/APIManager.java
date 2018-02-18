package network;

import com.google.common.io.ByteStreams;
import com.google.gson.*;
import javafx.application.Platform;
import model.*;
import model.Route;
import net.sf.jasperreports.engine.JasperPrint;
import okhttp3.*;

import java.io.*;
import java.util.*;
import java.util.concurrent.TimeUnit;

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
     * Use this method to add an appeal to report.
     *
     * @param id The id of the current user.
     * @param token The session token of the user.
     * @param appeal The Appeal to add to report.
     * @param report The report to add the appeal to.
     * @param callback The response callback.
     */

    public void addAppealToReport(String id, String token, Appeal appeal, Report report, Callbacks.General callback) {
        JsonObject body = new JsonObject();
        body.addProperty("id", id);
        body.addProperty("sessionToken", token);
        body.add("appeal", toJson(appeal));
        body.add("report", toJson(report));

        makeRequest(Constants.Routes.addAppealToReport(), null, body, (json, exception) -> {
            callback.make(new ServerResponse(json), exception);
        });

    }

    public void addAppealToReport(Appeal appeal, Report report, Callbacks.General callback) {
        addAppealToReport(AutoSignIn.ID, AutoSignIn.SESSION_TOKEN, appeal, report, callback);
    }


    /**
     * Use this method to add landmark to a route.
     *
     * @param id The id of the current user.
     * @param token The session token of the user.
     * @param landmark The landmark to add to the route.
     * @param route The route to add the landmark to.
     * @param callback The response callback
     */

    public void addLandmarkToRoute(String id, String token, Landmark landmark, Route route, Callbacks.General callback) {
        JsonObject body = new JsonObject();
        body.addProperty("id", id);
        body.addProperty("sessionToken", token);
        body.add("landmark", toJson(landmark));
        body.add("route", toJson(route));

        makeRequest(Constants.Routes.addLandmarkToRoute(), null, body, (json, exception) -> {
            callback.make(new ServerResponse(json), exception);
        });
    }

    public void addLandmarkToRoute(Landmark landmark, Route route, Callbacks.General callback) {
        addLandmarkToRoute(AutoSignIn.ID, AutoSignIn.SESSION_TOKEN, landmark, route, callback);
    }


    /**
     * Use this method to assign an officer to a partnership.
     *
     * @param id The id of the current user.
     * @param token The session token of the user.
     * @param officer The officer to add to the partnership.
     * @param part The partnership to add the officer to.
     * @param callback The response callback.
     */

    public void assignOfficerToPartnership(String id, String token, OperationalOfficer officer, Partnership part, Callbacks.General callback) {
        JsonObject body = new JsonObject();
        body.addProperty("id", id);
        body.addProperty("sessionToken", token);
        body.add("officer", toJson(officer));
        body.add("partnership", toJson(part));

        makeRequest(Constants.Routes.assignOfficerToPartnership(), null, body, (json, exception) -> {
            callback.make(new ServerResponse(json), exception);
        });
    }

    public void assignOfficerToPartnership(OperationalOfficer officer, Partnership part, Callbacks.General callback) {
        assignOfficerToPartnership(AutoSignIn.ID, AutoSignIn.SESSION_TOKEN, officer, part, callback);
    }

    /**
     * Use this method to add a partnership to a shift.
     *
     * @param id The id of the current user.
     * @param token The session token of the user.
     * @param part The partnership to add to the shift.
     * @param shift The shift to add the partnership to.
     * @param callback The response callback.
     */

    public void assignPartnershipToShift(String id, String token, Partnership part, Shift shift, Callbacks.General callback) {
        JsonObject body = new JsonObject();
        body.addProperty("id", id);
        body.addProperty("sessionToken", token);
        body.add("partnership", toJson(part));
        body.add("shift", toJson(shift));

        makeRequest(Constants.Routes.assignPartnershipToShift(), null, body, (json, exception) -> {
            callback.make(new ServerResponse(json), exception);
        });
    }

    public void assignPartnershipToShift(Partnership part, Shift shift, Callbacks.General callback) {
        assignPartnershipToShift(AutoSignIn.ID, AutoSignIn.SESSION_TOKEN, part, shift, callback);
    }


    /**
     * Use this method to assign a route to a shift.
     *
     * @param id The id of the current user.
     * @param token The session token of the user.
     * @param route The route to add to the shift.
     * @param shift The shift to add the route to.
     * @param callback The response callback
     */

    public void assignRouteToShift(String id, String token, Route route, Shift shift, Callbacks.General callback) {
        JsonObject body = new JsonObject();
        body.addProperty("id", id);
        body.addProperty("sessionToken", token);
        body.add("route", toJson(route));
        body.add("shift", toJson(shift));

        makeRequest(Constants.Routes.assignRouteToShift(), null, body, (json, exception) -> {
            callback.make(new ServerResponse(json), exception);
        });
    }

    public void assignRouteToShift(Route route, Shift shift, Callbacks.General callback) {
        assignRouteToShift(AutoSignIn.ID, AutoSignIn.SESSION_TOKEN, route, shift, callback);
    }

    /**
     * Use this method to create a new defendant.
     *
     * @param id The id of the current user.
     * @param token The session token of the user.
     * @param defendant The defendant to create.
     * @param callback The response callback.
     */

    public void createDefendant(String id, String token, Defendant defendant, Callbacks.General callback) {
        JsonObject body = new JsonObject();
        body.addProperty("id", id);
        body.addProperty("sessionToken", token);
        body.add("defendant", toJson(defendant));

        makeRequest(Constants.Routes.createDefendant(), null, body, (json, exception) -> {
            callback.make(new ServerResponse(json), exception);
        });
    }

    public void createDefendant(Defendant defendant, Callbacks.General callback) {
        createDefendant(AutoSignIn.ID, AutoSignIn.SESSION_TOKEN, defendant, callback);
    }

    /**
     * Use this method to create an Operational Officer Report.
     *
     * @param id The id of the current user.
     * @param token The session token of the user.
     * @param report The report to create.
     * @param callback The response callback.
     */

    public void createOfficerReport(String id, String token, OfficerReport report, Callbacks.General callback) {
        JsonObject body = new JsonObject();
        body.addProperty("id", id);
        body.addProperty("sessionToken", token);
        body.add("officerReport", toJson(report));

        makeRequest(Constants.Routes.createOfficerReport(), null, body, (json, exception) -> {
            callback.make(new ServerResponse(json), exception);
        });
    }

    public void createOfficerReport(OfficerReport report, Callbacks.General callback) {
        createOfficerReport(AutoSignIn.ID, AutoSignIn.SESSION_TOKEN, report, callback);
    }

    /**
     * Use this method to create a new partnership.
     *
     * @param id The id of the current user.
     * @param token The session token of the user.
     * @param part The partnership to create.
     * @param callback The response callback.
     */
    public void createPartnership(String id, String token, Partnership part, Callbacks.General callback) {
        JsonObject body = new JsonObject();
        body.addProperty("id", id);
        body.addProperty("sessionToken", token);
        body.add("partnership", toJson(part));

        makeRequest(Constants.Routes.createPartnership(), null, body, (json, exception) -> {
            callback.make(new ServerResponse(json), exception);
        });
    }

    public void createPartnership(Partnership part, Callbacks.General callback) {
        createPartnership(AutoSignIn.ID, AutoSignIn.SESSION_TOKEN, part, callback);
    }


    /**
     * Use this method to create a general report.
     *
     * @param id The id of the current user.
     * @param token The session token of the user.
     * @param report The report to create.
     * @param callback The response callback.
     */
    public void createReport(String id, String token, Report report, Callbacks.General callback) {
        JsonObject body = new JsonObject();
        body.addProperty("id", id);
        body.addProperty("sessionToken", token);
        body.add("report", toJson(report));

        makeRequest(Constants.Routes.createReport(), null, body, (json, exception) -> {
            callback.make(new ServerResponse(json), exception);
        });
    }

    public void createReport(Report report, Callbacks.General callback) {
        createReport(AutoSignIn.ID, AutoSignIn.SESSION_TOKEN, report, callback);
    }

    /**
     * Use this method to create a volunteer report.
     * @param id The id of the current user.
     * @param token The session token of the user.
     * @param report The report to create.
     * @param callback The response callback.
     */

    public void createVolunteerReportFromDingoReport(String id, String token, VolunteerReport report, Callbacks.General callback) {
        JsonObject body = new JsonObject();
        body.addProperty("id", id);
        body.addProperty("sessionToken", token);
        body.add("volunteerReport", toJson(report));

        makeRequest(Constants.Routes.createVolunteerReportFromDingoReport(), null, body, (json, exception) -> {
            callback.make(new ServerResponse(json), exception);
        });
    }

    public void createVolunteerReportFromDingoReport(VolunteerReport report, Callbacks.General callback) {
        createVolunteerReportFromDingoReport(AutoSignIn.ID, AutoSignIn.SESSION_TOKEN, report, callback);
    }

    /**
     * Use this method to get all reports.
     *
     * @param id The id of the current user.
     * @param token The session token of the user.
     * @param callback The response callback.
     */
    public void getAllReports(String id, String token, Callbacks.Report callback) {
        JsonObject body = new JsonObject();
        body.addProperty("id", id);
        body.addProperty("sessionToken", token);

        makeRequest(Constants.Routes.getAllReports(), null, body, (json, exception) -> {
            ServerResponse response = new ServerResponse(json);
            if (exception == null) {
                List<Report> reports = new ArrayList<>();
                JsonArray array = gson.fromJson(json.get("data").getAsJsonArray(), JsonArray.class);

                for (JsonElement element : array) {
                    try {
                        Report report = gson.fromJson(element, Report.class);
                        reports.add(report);
                    } catch (Exception e){
                        System.err.println(e.getMessage());
                    }
                }
                callback.make(response, reports, null);
            }
            else {
                callback.make(response, null, exception);
            }
        });
    }

    public void getAllReports(Callbacks.Report callback) {
        getAllReports(AutoSignIn.ID, AutoSignIn.SESSION_TOKEN, callback);
    }

    /**
     * Use this method to get all appeals.
     *
     * @param id The id of the current user.
     * @param token The session token of the user.
     * @param callback The response callback.
     */

    public void getAppeals(String id, String token, Callbacks.Appeal callback) {
        JsonObject body = new JsonObject();
        body.addProperty("id", id);
        body.addProperty("sessionToken", token);

        makeRequest(Constants.Routes.getAppeals(), null, body, (json, exception) -> {
            ServerResponse response = new ServerResponse(json);
            if (exception == null) {
                List<Appeal> appeals = new ArrayList<>();
                JsonArray array = gson.fromJson(json.get("data").getAsJsonArray(), JsonArray.class);

                for (JsonElement element : array) {
                    try {
                        Appeal appeal = gson.fromJson(element, Appeal.class);
                        appeals.add(appeal);
                    }catch (Exception e) {
                        System.err.println(e.getMessage());
                    }
                }
                callback.make(response, appeals, null);
            }
            else {
                callback.make(response, null, exception);
            }
        });
    }

    public void getAppeals(Callbacks.Appeal callback) {
        getAppeals(AutoSignIn.ID, AutoSignIn.SESSION_TOKEN, callback);
    }

    /**
     * Use this method to get all defendants.
     *
     * @param id The id of the current user.
     * @param token The session token of the user.
     * @param callback The response callback.
     */

    public void getDefendants(String id, String token, Callbacks.Defendant callback) {
        JsonObject body = new JsonObject();
        body.addProperty("id", id);
        body.addProperty("sessionToken", token);

        makeRequest(Constants.Routes.getDefendants(), null, body, (json, exception) -> {
            ServerResponse response = new ServerResponse(json);
            if (exception == null) {
                List<Defendant> defendants = new ArrayList<>();
                JsonArray array = gson.fromJson(json.get("data").getAsJsonArray(), JsonArray.class);

                for (JsonElement element : array) {
                    try {
                        Defendant defendant = gson.fromJson(element, Defendant.class);
                        defendants.add(defendant);
                    }catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                }
                callback.make(response, defendants, null);
            }
            else {
                callback.make(response, null, exception);
            }
        });
    }

    public void getDefendants(Callbacks.Defendant callback) {
        getDefendants(AutoSignIn.ID, AutoSignIn.SESSION_TOKEN, callback);
    }

    /**
     * Use this method to get all landmarks.
     *
     * @param id The id of the current user.
     * @param token The session token of the user.
     * @param callback The response callback.
     */

    public void getLandmarks(String id, String token, Callbacks.Landmark callback) {
        JsonObject body = new JsonObject();
        body.addProperty("id", id);
        body.addProperty("sessionToken", token);

        makeRequest(Constants.Routes.getLandmarks(), null, body, (json, exception) -> {
            ServerResponse response = new ServerResponse(json);
            if (exception == null) {
                List<Landmark> landmarks = new ArrayList<>();
                JsonArray array = gson.fromJson(json.get("data").getAsJsonArray(), JsonArray.class);

                for (JsonElement element : array) {
                    try {
                        Landmark landmark = gson.fromJson(element, Landmark.class);
                        landmarks.add(landmark);
                    }catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                }
                callback.make(response, landmarks, null);
            }
            else {
                callback.make(response, null, exception);
            }
        });
    }

    public void getLandmarks(Callbacks.Landmark callback) {
        getLandmarks(AutoSignIn.ID, AutoSignIn.SESSION_TOKEN, callback);
    }

    /**
     * Use this method to get all partnerships.
     *
     * @param id The id of the current user.
     * @param token The session token of the user.
     * @param callback The response callback.
     */

    public void getPartnerships(String id, String token, Callbacks.Partnership callback) {
        JsonObject body = new JsonObject();
        body.addProperty("id", id);
        body.addProperty("sessionToken", token);

        makeRequest(Constants.Routes.getPartnerships(), null, body, (json, exception) -> {
            ServerResponse response = new ServerResponse(json);
            if (exception == null) {
                List<Partnership> partnerships = new ArrayList<>();
                JsonArray array = gson.fromJson(json.get("data").getAsJsonArray(), JsonArray.class);

                for (JsonElement element : array) {
                    try {
                        Partnership part = gson.fromJson(element, Partnership.class);
                        partnerships.add(part);
                    }catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                }
                callback.make(response, partnerships, null);
            }
            else {
                callback.make(response, null, exception);
            }
        });
    }

    public void getPartnerships(Callbacks.Partnership callback) {
        getPartnerships(AutoSignIn.ID, AutoSignIn.SESSION_TOKEN, callback);
    }

    public void getOfficers(String id, String token,Callbacks.Officers callback){
        JsonObject body = new JsonObject();
        body.addProperty("id", id);
        body.addProperty("sessionToken", token);

        makeRequest(Constants.Routes.getOfficers(), null, body, (json, exception) -> {
            ServerResponse response = new ServerResponse(json);
            if (exception == null) {
                List<OperationalOfficer> officers = new ArrayList<>();
                JsonArray array = gson.fromJson(json.get("data").getAsJsonArray(), JsonArray.class);

                for (JsonElement element : array) {
                    try {
                        OperationalOfficer officer = gson.fromJson(element, OperationalOfficer.class);
                        officers.add(officer);
                    }catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                }
                callback.make(response, officers, null);
            }
            else {
                callback.make(response, null, exception);
            }
        });
    }

    public void getOfficers(Callbacks.Officers callback){
        getOfficers(AutoSignIn.ID,AutoSignIn.SESSION_TOKEN,callback);
    }


    //------------------------------------------------------------------------------------------------

    public void getReportsFromDingoReport() {

    }

    /**
     * Use this method to get all routes.
     *
     * @param id The id of the current user.
     * @param token The session token of the user.
     * @param callback The response callback.
     */

    public void getRoutes(String id, String token, Callbacks.Route callback) {
        JsonObject body = new JsonObject();
        body.addProperty("id", id);
        body.addProperty("sessionToken", token);

        makeRequest(Constants.Routes.getRoutes(), null, body, (json, exception) -> {
            ServerResponse response = new ServerResponse(json);
            if (exception == null) {
                List<Route> routes = new ArrayList<>();
                JsonArray array = gson.fromJson(json.get("data").getAsJsonArray(), JsonArray.class);

                for (JsonElement element : array) {
                    try {
                        Route route = gson.fromJson(element, Route.class);
                        routes.add(route);
                    }catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                }
                callback.make(response, routes, null);
            }
            else {
                callback.make(response, null, exception);
            }
        });
    }

    public void getRoutes(Callbacks.Route callback) {
        getRoutes(AutoSignIn.ID, AutoSignIn.SESSION_TOKEN, callback);
    }

    /**
     * Use this method to get all shifts.
     *
     * @param id The id of the current user.
     * @param token The session token of the user.
     * @param callback The response callback.
     */

    public void getShifts(String id, String token, Callbacks.Shift callback) {
        JsonObject body = new JsonObject();
        body.addProperty("id", id);
        body.addProperty("sessionToken", token);

        makeRequest(Constants.Routes.getShifts(), null, body, (json, exception) -> {
            ServerResponse response = new ServerResponse(json);
            if (exception == null) {
                List<Shift> shifts = new ArrayList<>();
                JsonArray array = gson.fromJson(json.get("data").getAsJsonArray(), JsonArray.class);

                for (JsonElement element : array) {
                    try {
                        Shift shift = gson.fromJson(element, Shift.class);
                        shifts.add(shift);
                    }catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                }
                callback.make(response, shifts, null);
            }
            else {
                callback.make(response, null, exception);
            }
        });
    }

    public void getShifts(Callbacks.Shift callback) {
        getShifts(AutoSignIn.ID, AutoSignIn.SESSION_TOKEN, callback);
    }

    /**
     * Use this method to get all vehicle models.
     *
     * @param id The id of the current user.
     * @param token The session token of the user.
     * @param callback The response callback.
     */

    public void getVehicleModels(String id, String token, Callbacks.VehicleModel callback) {
        JsonObject body = new JsonObject();
        body.addProperty("id", id);
        body.addProperty("sessionToken", token);

        makeRequest(Constants.Routes.getVehicleModels(), null, body, (json, exception) -> {
            ServerResponse response = new ServerResponse(json);
            if (exception == null) {
                List<VehicleModel> vehicleModels = new ArrayList<>();
                JsonArray array = gson.fromJson(json.get("data").getAsJsonArray(), JsonArray.class);

                for (JsonElement element : array) {
                    try {
                        VehicleModel vehicleModel = gson.fromJson(element, VehicleModel.class);
                        vehicleModels.add(vehicleModel);
                    }catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                }
                callback.make(response, vehicleModels, null);
            }
            else {
                callback.make(response, null, exception);
            }
        });
    }

    public void getVehicleModels(Callbacks.VehicleModel callback) {
        getVehicleModels(AutoSignIn.ID, AutoSignIn.SESSION_TOKEN, callback);
    }

    /**
     * Use this method to get all vehicles.
     *
     * @param id The id of the current user.
     * @param token The session token of the user.
     * @param callback The response callback.
     */

    public void getVehicles(String id, String token, Callbacks.Vehicle callback) {
        JsonObject body = new JsonObject();
        body.addProperty("id", id);
        body.addProperty("sessionToken", token);

        makeRequest(Constants.Routes.getVehicles(), null, body, (json, exception) -> {
            ServerResponse response = new ServerResponse(json);
            if (exception == null) {
                List<Vehicle> vehicles = new ArrayList<>();
                JsonArray array = gson.fromJson(json.get("data").getAsJsonArray(), JsonArray.class);

                for (JsonElement element : array) {
                    try {
                        Vehicle vehicle = gson.fromJson(element, Vehicle.class);
                        vehicles.add(vehicle);
                    }catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                }
                callback.make(response, vehicles, null);
            }
            else {
                callback.make(response, null, exception);
            }
        });
    }

    public void getVehicles(Callbacks.Vehicle callback) {
        getVehicles(AutoSignIn.ID, AutoSignIn.SESSION_TOKEN, callback);
    }

    /**
     * Use this method to submit an appeal.
     *
     * @param id The id of the current user.
     * @param token The session token of the user.
     * @param appeal The appeal to submit.
     * @param callback The response callback.
     */

    public void submitAppeal(String id, String token, Appeal appeal, Callbacks.General callback) {
        JsonObject body = new JsonObject();
        body.addProperty("id", id);
        body.addProperty("sessionToken", token);
        body.add("appeal", toJson(appeal));

        makeRequest(Constants.Routes.submitAppeal(), null, body, (json, exception) -> {
            callback.make(new ServerResponse(json), exception);
        });
    }

    public void submitAppeal(Appeal appeal, Callbacks.General callback) {
        submitAppeal(AutoSignIn.ID, AutoSignIn.SESSION_TOKEN, appeal, callback);
    }

    /**
     * Load jasper report from server in a given time frame.
     * @param id The id of the current user.
     * @param token The session token of the user.
     * @param from The start date.
     * @param to The end date.
     * @param callback The callback containing the jasper print.
     */

    public void exportReportByDate(String id,String token,Date from,Date to,Callbacks.Jasper callback) {
        Map<String, String> headers = new HashMap<>();
        headers.put("id", id);
        headers.put("sessionToken", token);
        headers.put("from", "" + from.getTime());
        headers.put("to", "" + to.getTime());
        requestResource(Constants.Routes.exportReports(), headers, (stream, e) -> {
            if (e == null) {
                try {
                    byte[] bytes = ByteStreams.toByteArray(stream);
                    JasperPrint print = deserialize(bytes);
                    callback.make(print, null);
                } catch (IOException | ClassNotFoundException e1) {
                    e1.printStackTrace();
                    callback.make(null, e1);
                }
            } else {
                callback.make(null, e);
            }
        });
    }

    public void exportReportByDate(Date from,Date to,Callbacks.Jasper callback) {
        exportReportByDate(AutoSignIn.ID, AutoSignIn.SESSION_TOKEN, from, to, callback);
    }




    /**
     * Use this method to load images/resources from the server.
     * @param url The url of the resource
     * @param headers The headers for the GET request
     * @param callback The response callback.
     */
    private void requestResource(String url,Map<String,String> headers,final Callbacks.Resource callback){
        Request request;

        Request.Builder builder = new Request
                .Builder()
                .url(url)
                .get();
        //add additional headers
        if(headers != null)
            headers.forEach(builder::addHeader);

        request = builder.build();

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
                        InputStream res = new ByteArrayInputStream(responseBody.bytes());

                        //make thread safe.
                        Platform.runLater(() -> {
                            callback.make(res,null);
                        });
                        responseBody.close();
                    }
                }
            }
        });
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

    public static byte[] serialize(Object obj) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ObjectOutputStream os = new ObjectOutputStream(out);
        os.writeObject(obj);
        return out.toByteArray();
    }
    public static <T> T deserialize(byte[] data) throws IOException, ClassNotFoundException {
        ByteArrayInputStream in = new ByteArrayInputStream(data);
        ObjectInputStream is = new ObjectInputStream(in);
        return (T)is.readObject();
    }
}
