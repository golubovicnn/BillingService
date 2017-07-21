package util;

import java.util.ResourceBundle;

/**
 * Created by Andreas on 05.06.2017.
 */
public class ConfigData {

    private static ResourceBundle config = ResourceBundle.getBundle("config");
    private static String environment = config.getString("environment");
    private static String databaseName = config.getString("database-name");
    private static String databasePassword = config.getString("database-password");
    private static String apiEndPoint = config.getString("api-endpoint");

    public static EnvironmentType getEnvironment(){

        switch (environment){
            case "test":
                return EnvironmentType.TEST;
            case "production":
                return EnvironmentType.PRODUCTION;
            default:
                return EnvironmentType.TEST;
        }
    }

    public static String getApiKey(){
        return config.getString("api-key");
    }

    public static String getApiMail(){
        return config.getString("api-email");
    }

    public static String getApiEndPoint() { return apiEndPoint; }

    public static String getDatabaseName() {
        return databaseName;
    }

    public static String getDatabasePassword() {
        return databasePassword;
    }
}
