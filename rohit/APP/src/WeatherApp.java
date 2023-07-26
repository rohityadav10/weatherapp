import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Scanner;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


public class WeatherApp {
    private static final String API_KEY = "b6907d289e10d714a6e88b30761fae22";
    private static final String BASE_URL = "https://samples.openweathermap.org/data/2.5/forecast/hourly";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            String weatherData = getWeatherData("London");
            while (true) {
                printMenu();
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        getWeatherByDate(weatherData, scanner);
                        break;
                    case 2:
                        getWindSpeedByDate(weatherData, scanner);
                        break;
                    case 3:
                        getPressureByDate(weatherData, scanner);
                        break;
                    case 0:
                        System.out.println("Exiting the program.");
                        scanner.close();
                        return;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            }
        } catch (IOException e) {
            System.out.println("Error fetching weather data. Please check your internet connection.");
        }
    }

    private static String getWeatherData(String city) throws IOException {
        String apiUrl = BASE_URL + "?q=" + city + "&appid=" + API_KEY;
        URL url = new URL(apiUrl);
        try (InputStream inputStream = url.openStream();
             Scanner scanner = new Scanner(inputStream)) {
            scanner.useDelimiter("\\A");
            return scanner.hasNext() ? scanner.next() : "";
        }
    }

    private static void printMenu() {
        System.out.println("\nOptions:");
        System.out.println("1. Get weather");
        System.out.println("2. Get Wind Speed");
        System.out.println("3. Get Pressure");
        System.out.println("0. Exit");
        System.out.print("Enter your choice: ");
    }


    private static void getWeatherByDate(String weatherData, Scanner scanner) {
        System.out.print("Enter the date (YYYY-MM-DD HH:mm:ss): ");
        String date = scanner.nextLine();

        try {
            JSONParser jsonParser = new JSONParser();
            JSONObject jsonData = (JSONObject) jsonParser.parse(weatherData);
            JSONArray weatherList = (JSONArray) jsonData.get("list");

            for (Object weatherObject : weatherList) {
                JSONObject weatherInfo = (JSONObject) weatherObject;
                String dt_txt = (String) weatherInfo.get("dt_txt");

                if (dt_txt.startsWith(date)) {
                    JSONObject mainInfo = (JSONObject) weatherInfo.get("main");
                    double temperature = (double) mainInfo.get("temp");
                    double pressure = (double) mainInfo.get("pressure");

                    JSONObject windInfo = (JSONObject) weatherInfo.get("wind");
                    double windSpeed = (double) windInfo.get("speed");

                    System.out.println("Temperature: " + temperature + " Kelvin");
                    //System.out.println("Pressure: " + pressure + " hPa");
                    //1System.out.println("Wind Speed: " + windSpeed + " m/s");
                    break;
                }
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }



    private static void getWindSpeedByDate(String weatherData, Scanner scanner) {
        System.out.print("Enter the date (YYYY-MM-DD HH:mm:ss): ");
        String date = scanner.nextLine();
        try {
            JSONParser jsonParser = new JSONParser();
            JSONObject jsonData = (JSONObject) jsonParser.parse(weatherData);
            JSONArray weatherList = (JSONArray) jsonData.get("list");

            for (Object weatherObject : weatherList) {
                JSONObject weatherInfo = (JSONObject) weatherObject;
                String dt_txt = (String) weatherInfo.get("dt_txt");

                if (dt_txt.startsWith(date)) {
                    JSONObject mainInfo = (JSONObject) weatherInfo.get("main");
                    double temperature = (double) mainInfo.get("temp");
                    double pressure = (double) mainInfo.get("pressure");

                    JSONObject windInfo = (JSONObject) weatherInfo.get("wind");
                    double windSpeed = (double) windInfo.get("speed");

                    // System.out.println("Date: " + dt_txt);
                    //System.out.println("Temperature: " + temperature + " Kelvin");
                    //System.out.println("Pressure: " + pressure + " hPa");
                    System.out.println("Wind Speed: " + windSpeed + " m/s");
                    break;
                }
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }


    }

    private static void getPressureByDate(String weatherData, Scanner scanner) {
        System.out.print("Enter the date (YYYY-MM-DD HH:mm:ss): ");
        String date = scanner.nextLine();
        try {
            JSONParser jsonParser = new JSONParser();
            JSONObject jsonData = (JSONObject) jsonParser.parse(weatherData);
            JSONArray weatherList = (JSONArray) jsonData.get("list");

            for (Object weatherObject : weatherList) {
                JSONObject weatherInfo = (JSONObject) weatherObject;
                String dt_txt = (String) weatherInfo.get("dt_txt");

                if (dt_txt.startsWith(date)) {
                    JSONObject mainInfo = (JSONObject) weatherInfo.get("main");
                    double temperature = (double) mainInfo.get("temp");
                    double pressure = (double) mainInfo.get("pressure");

                    JSONObject windInfo = (JSONObject) weatherInfo.get("wind");
                    double windSpeed = (double) windInfo.get("speed");

                    // System.out.println("Date: " + dt_txt);
                    //System.out.println("Temperature: " + temperature + " Kelvin");
                    //System.out.println("Pressure: " + pressure + " hPa");
                    System.out.println("Wind Speed: " + windSpeed + " m/s");
                    break;
                }
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}


