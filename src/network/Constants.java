package network;

/**
 * Created By Tony on 14/02/2018
 */
final public class Constants {

    /**
     * Protocol
     */
    public final static String protocol = "http";

    /**
     * Address
     */
    public final static String address = "localhost";

    /**
     * Port
     */
    public final static String port = "8080";

    /**
     * @return full path
     */
    public final static String getAddress(){
        return protocol + "://" + address + ":" + port;
    }

    /**
     * Routes class, contains all API routes.
     */
    public static class Routes {

        public static String login(){
            return getAddress() + "/signin";
        }
        public static String updatePassword(){return getAddress() + "/updatePassword"; }
        public static String addAppealToReport() {return getAddress() + "/addAppealToReport"; }
        public static String addLandmarkToRoute() {return getAddress() + "/addLandmarkToRoute"; }
        public static String assignOfficerToPartnership() {return getAddress() + "/assignOfficerToPartnership"; }
        public static String assignPartnershipToShift() {return getAddress() + "/assignPartnershipToShift"; }
        public static String assignRouteToShift() {return getAddress() + "/assignRouteToShift"; }
        public static String createDefendant() {return getAddress() + "/createDefendant" ;}
        public static String createOfficerReport() {return getAddress() + "/createOfficerReport" ;}
        public static String createPartnership() {return getAddress() + "/createPartnership" ;}
        public static String createReport() {return getAddress() + "/createReport"; }
        public static String createVolunteerReportFromDingoReport() {return getAddress() + "/createVolunteerReportFromDingoReport"; }
        public static String getAllReports() {return getAddress() + "/getAllReports"; }
        public static String exportReports() { return getAddress() + "/exportReports";}
        public static String getAppeals() {return getAddress() + "/getAppeals"; }
        public static String getDefendants() {return getAddress() + "/getDefendants"; }
        public static String getLandmarks() {return getAddress() + "/getLandmarks"; }
        public static String getPartnerships() {return getAddress() + "/getPartnerships"; }
        public static String getOfficers() {
            return getAddress() + "/getOfficers";
        }
        public static String getRoutes() {return getAddress() + "/getRoutes"; }
        public static String getShifts() {return getAddress() + "/getShifts"; }
        public static String getVehicleModels() {return getAddress() + "/getVehicleModels"; }
        public static String getVehicles() {return getAddress() + "/getVehicles"; }
        public static String submitAppeal() {return getAddress() + "/submitAppeal"; }
    }
    public static class Codes{
        public static final int SUCCESS=200;
        public static final int MISSING_PARAMETERS=401;

    }

}
