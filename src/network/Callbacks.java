package network;

import com.google.gson.JsonObject;
import javafx.fxml.FXML;
import model.*;
import net.sf.jasperreports.engine.JasperPrint;

import java.io.InputStream;
import java.util.List;

/**
 * Created By Tony on 14/02/2018
 */
public final class Callbacks {

    private Callbacks(){}

    @FunctionalInterface
    interface Inner{
        void make(JsonObject json, Exception exception);
    }

    /**
     * General callback, used only by the API Manager internally.
     */
    @FunctionalInterface
    public interface General{
        void make(ServerResponse response, Exception exception);
    }

    @FunctionalInterface
    public interface Auth {
        void make(ServerResponse response, String id, String token, int roleId, Exception exception);
    }

    @FunctionalInterface
    public interface Report {
        void make(ServerResponse response, List<model.Report> reports, Exception exception);
    }

    @FunctionalInterface
    public interface Appeal {
        void make(ServerResponse response, List<model.Appeal> appeals, Exception exception);
    }

    @FunctionalInterface
    public interface Defendant {
        void make(ServerResponse response, List<model.Defendant> defendants, Exception exception);
    }

    @FunctionalInterface
    public interface Landmark {
        void make(ServerResponse response, List<model.Landmark> landmarks, Exception exception);
    }

    @FunctionalInterface
    public interface Partnership {
        void make(ServerResponse response, List<model.Partnership> partnerships, Exception exception);
    }

    @FunctionalInterface
    public interface Route {
        void make(ServerResponse response, List<model.Route> routes, Exception exception);
    }

    @FunctionalInterface
    public interface Shift {
        void make(ServerResponse response, List<model.Shift> shifts, Exception exception);
    }

    @FunctionalInterface
    public interface VehicleModel {
        void make(ServerResponse response, List<model.VehicleModel> vehicleModels, Exception exception);
    }

    @FunctionalInterface
    public interface Vehicle {
        void make(ServerResponse response, List<model.Vehicle> vehicles, Exception exception);
    }
}
