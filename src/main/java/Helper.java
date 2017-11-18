import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.logging.Logger;

public class Helper {

    private Logger logger = Logger.getLogger(Helper.class.getName());

    private static final String SERIAL_CLASS_VERSION = "HELPER.01";

    private static String[] currencies = {"PLN", "EUR", "USD"};
    private static Random random = new Random();
    private static List<String> names;
    private static List<String> surnames;
    private static List<String> hotelNames = Arrays.asList("Plaza", "Sheraton", "Mercurial");
    private static List<String> countries = Arrays.asList("Polska", "Hiszpania", "Francja");
    private static List<String> positions = Arrays.asList("Glowny opiekun", "Opiekun", "Przewodnik");
    private static Map<String, List<String>> regions;
    private static Map<String, List<String>> cities;
    private static String currentGender;

    public static void initMaps() {
        regions = new HashMap<>();
        cities = new HashMap<>();

        regions.put("Polska", Arrays.asList("Bieszczady", "Tatry", "Pomorze", "Mazury"));
        regions.put("Hiszpania", Arrays.asList("Asturia", "Madryt", "Barcelona", "Nawarra", "Kantabria", "Murcja"));
        regions.put("Francja", Arrays.asList("Burgundia", "Owernia", "Akwitania"));

        cities.put("Bieszczady", new ArrayList<>());
        cities.put("Tatry", new ArrayList<>());
        cities.put("Pomorze", new ArrayList<>());
        cities.put("Mazury", new ArrayList<>());

        cities.put("Asturia", new ArrayList<>());
        cities.put("Madryt", new ArrayList<>());
        cities.put("Barcelona", new ArrayList<>());
        cities.put("Nawarra", new ArrayList<>());
        cities.put("Kantabria", new ArrayList<>());
        cities.put("Murcja", new ArrayList<>());

        cities.put("Burgundia", new ArrayList<>());
        cities.put("Owernia", new ArrayList<>());
        cities.put("Akwitania", new ArrayList<>());

    }


    public static void getNames() {
        try {
            if(names == null)
                names = Files.readAllLines(Paths.get("E:\\workspace\\databasescript\\src\\main\\resources\\imiona.csv"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void getSurnames() {
        try {
            if(surnames == null)
                surnames = Files.readAllLines(Paths.get("E:\\workspace\\databasescript\\src\\main\\resources\\nazwiska.csv"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getRandomName() {
        String[] line = names.get(random.nextInt(names.size())).split(";");
        currentGender = line[2];

        return line[0];
    }

    public static String getRandomSurname() {
        return surnames.get(random.nextInt(surnames.size()));
    }


    public static String getSingleHotelName() {
        return hotelNames.get(random.nextInt(hotelNames.size()));
    }

    public static LocalDate getRandomDate(String from, String to) {
        String[] arrayFrom = from.split("-");
        String[] arrayTo = to.split("-");

        Integer[] arrayFromInteger = new Integer[]{Integer.valueOf(arrayFrom[0]), Integer.valueOf(arrayFrom[1]), Integer.valueOf(arrayFrom[2])};
        Integer[] arrayToInteger = new Integer[]{Integer.valueOf(arrayTo[0]), Integer.valueOf(arrayTo[1]), Integer.valueOf(arrayTo[2])};

        int minDay = (int) LocalDate.of(arrayFromInteger[0], arrayFromInteger[1], arrayFromInteger[2]).toEpochDay();
        int maxDay = (int) LocalDate.of(arrayToInteger[0], arrayToInteger[1], arrayToInteger[2]).toEpochDay();

        long randomDay = minDay + random.nextInt(maxDay-minDay);

        return LocalDate.ofEpochDay(randomDay);
    }

    public static int getRandomPersonalStuff() {
       return random.nextInt(200) + 30;
    }

    public static int getRandomStarsNumber() {

        return random.nextInt(5)+1;
    }

    public static String getPersonDepartmentIdentifier() {
        return "ODDZ_HISZ_001_GLOW_OPIEK";
    }

    public static String getCountry() {

        return countries.get(random.nextInt(countries.size()));
    }

    public static String getRegionForGivenCountry(String country) {

        List<String> regionsForGivenCountry = regions.get(country);

        return regionsForGivenCountry.get(random.nextInt(regionsForGivenCountry.size()));
    }

    public static String getCityForGivenRegion(String region) {
        List<String> list = cities.get(region);

        return list.size() == 0 ? "CITY" : list.get(random.nextInt(list.size()));
    }

    public static double getWeight() {

        return ThreadLocalRandom.current().nextDouble(1500.0f, 10000.0f);
    }

    public static double getWeight(double min, double max) {

        return ThreadLocalRandom.current().nextDouble(min, max);
    }

    public static String getCurrencyCode() {
        return currencies[random.nextInt(currencies.length)];
    }

    public static String getGender() {
        return currentGender;
    }

    public static String getRandomPosition() {
        return positions.get(random.nextInt(positions.size()));
    }
}
