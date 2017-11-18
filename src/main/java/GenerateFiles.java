import models.Hotel;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

public class GenerateFiles {

    private final int HOTEL_CSV_RECORDS = 1000;
    private final int HOTELS_COUNT = 100;
    private final int SALESMEN_COUNT = 500;
    private final int RECORDS_COUNT = 1000;


    public static void main(String[] args) {

        GenerateFiles generateFiles = new GenerateFiles();
        //generateFiles.generateHotelsCsv();
        //generateFiles.generateSalesmenCsv();
        generateFiles.generateTravelsCsv();

    }

    public void generateHotelsCsv() {
        List<String> hotelscsv = new ArrayList<>();

        Helper.initMaps();
        Helper.getNames();
        Helper.getSurnames();

        StringBuilder str = new StringBuilder();
        List<Hotel> hotels = generateUniqueHotels();
        Random random = new Random();

        String headerLine = "ID, Nazwa hotelu, Imie wlasciciela, Nazwisko Wlasciciela, Data powstania, Liczba gwiazdek, Liczba personelu, Nazwisko opiekuna, Imie opiekuna, Data urodzenia opiekuna, Plec opiekuna, Oddzial, Stanowisko, Kraj, Region, Miasto, Pensja, Waluta, Data zatrudnienia";
        hotelscsv.add(headerLine);

        for(int i = 0; i < HOTEL_CSV_RECORDS; i++) {
            str.setLength(0);
            Hotel hotel = hotels.get(random.nextInt(hotels.size()));

            str
                    .append(hotel.getId()).append(",")
                    .append(hotel.getName()).append(",")
                    .append(hotel.getOwnerName()).append(",")
                    .append(hotel.getOwnerSurname()).append(",")
                    .append(hotel.getDate()).append(",")
                    .append(hotel.getStars()).append(",")
                    .append(hotel.getPersonel()).append(",")
                    .append(Helper.getRandomSurname()).append(",")
                    .append(Helper.getRandomName()).append(",")
                    .append(Helper.getRandomDate("1970-01-01", "1998-12-31")).append(",")
                    .append(Helper.getGender()).append(",")
                    .append("ODDZ_").append(hotel.getCountry().substring(0, 3).toUpperCase()).append("_00").append(random.nextInt(9)+1).append(",")
                    .append(Helper.getRandomPosition()).append(",")
                    .append(hotel.getCountry()).append(",")
                    .append(hotel.getRegion()).append(",")
                    .append(hotel.getCity()).append(",")
                    .append(Helper.getWeight()).append(",")
                    .append(Helper.getCurrencyCode()).append(",")
                    .append(Helper.getRandomDate("2000-01-01", "2017-08-31"));

            hotelscsv.add(str.toString());
        }

        try {
            Files.write(Paths.get("E:\\workspace\\databasescript\\src\\main\\resources\\results\\hotele.csv"), hotelscsv);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private List<Hotel> generateUniqueHotels() {
        List<Hotel> hotels = new ArrayList<>();

        int id = 10000;

        for (int i = 0; i < HOTELS_COUNT; i++) {

            String country = Helper.getCountry();
            String region = Helper.getRegionForGivenCountry(country);
            String city = Helper.getCityForGivenRegion(region);

            Hotel hotel = new Hotel.HotelBuilder()
                    .withId(id++)
                    .withName(Helper.getSingleHotelName())
                    .withOwnerSurname(Helper.getRandomSurname())
                    .withOwnerName(Helper.getRandomName())
                    .withDate(Helper.getRandomDate("1930-01-01", "2015-12-31"))
                    .withPersonel(Helper.getRandomPersonalStuff())
                    .withStars(Helper.getRandomStarsNumber())
                    .withCountry(country)
                    .withRegion(region)
                    .withCity(city)
                    .build();

            hotels.add(hotel);
        }

        return hotels;
    }

    public void generateSalesmenCsv() {
        List<String> salesmen = new ArrayList<>();

        Helper.initMaps();
        Helper.getNames();
        Helper.getSurnames();

        StringBuilder str = new StringBuilder();
        Random random = new Random();

        String headerLine = "ID Sprzedawcy, Imie sprzedawcy, Nazwisko Sprzedawcy, Pensja, Data zatrudnienia, Pesel, Oddzial, Stanowisko, Kraj, Region, Miasto, Procent znizki, Data urodzenia, Numer identyfikacyjny, Plec";
        salesmen.add(headerLine);
        int id = 100;

        for(int i = 0; i < SALESMEN_COUNT; i++) {
            str.setLength(0);

            String country = Helper.getCountry();
            String region = Helper.getRegionForGivenCountry(country);
            String city = Helper.getCityForGivenRegion(region);

            str
                    .append(id++).append(",")
                    .append(Helper.getRandomName()).append(",")
                    .append(Helper.getRandomSurname()).append(",")
                    .append(String.format(Locale.ENGLISH, "%.2f", Helper.getWeight())).append(",")
                    .append(Helper.getRandomDate("2000-01-01", "2017-08-31")).append(",")
                    .append(Helper.getRandomPesel()).append(",")
                    .append("ODDZ_").append("_0").append(random.nextInt(100)).append(",")
                    .append(Helper.getSalesmanPosition()).append(",")
                    .append(country).append(",")
                    .append(region).append(",")
                    .append(city).append(",")
                    .append(random.nextInt(61)).append(",")
                    .append(Helper.getRandomDate("1970-01-01", "1998-12-31")).append(",")
                    .append(Helper.getUniqueIdNumber()).append(",")
                    .append(Helper.getGender().replace("\"", ""));

            salesmen.add(str.toString());
        }

        try {
            Files.write(Paths.get("E:\\workspace\\databasescript\\src\\main\\resources\\results\\sprzedawcy.csv"), salesmen);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void generateTravelsCsv() {
        List<String> hotelLines = null;
        List<String> salesmenLines = null;

        List<String> travels =  new ArrayList<>();

        Helper.initMaps();
        Helper.getNames();
        Helper.getSurnames();
        StringBuilder str = new StringBuilder();
        Random random = new Random();

        String headerLine = "ID wycieczki, Pesel klienta, Imie klienta, Nazwisko klienta, Numer dowodu, Plec klienta, Data urodzenia klienta, Data poczatku wycieczki, Data konca wycieczki, Kraj klienta, Miasto Klienta, Zaliczka, Cena wycieczki, Oddzial zakupu, Ocena wycieczki, ID sprzedawcy, ID hotelu, Ocena sprzedawcy";
        travels.add(headerLine);
        int id = 1000;
        DateTimeFormatter formatter = DateTimeFormatter.ISO_ZONED_DATE_TIME;

        try {
            hotelLines = Files.readAllLines(Paths.get("E:\\workspace\\databasescript\\src\\main\\resources\\results\\hotele.csv"));
            salesmenLines = Files.readAllLines(Paths.get("E:\\workspace\\databasescript\\src\\main\\resources\\results\\sprzedawcy.csv"));
        } catch (IOException e) {
            e.printStackTrace();
        }


        for (int i = 0; i < RECORDS_COUNT; i++) {

            LocalDate date = Helper.getRandomDate("2000-01-01", "2018-01-01");
            LocalDate endDate = date.plusDays(random.nextInt(14)+2);

            ZonedDateTime localDateTime = ZonedDateTime.of(date, LocalTime.of(8, 0, 0, 0), ZoneId.systemDefault());
            ZonedDateTime localDateTimeEnd = ZonedDateTime.of(endDate, LocalTime.of(15, 0, 0 , 0), ZoneId.systemDefault());

            String country = Helper.getCountry();
            String region = Helper.getRegionForGivenCountry(country);
            String city = Helper.getCityForGivenRegion(region);

            double cost = Helper.getCost();
            double deposit =  ThreadLocalRandom.current().nextDouble(0.0f, cost);

            assert salesmenLines != null;
            List<String> filtered = salesmenLines
                    .stream()
                    .filter(s -> s.contains(country))
                    .collect(Collectors.toList());

            String selectedLine = filtered.get(random.nextInt(filtered.size()));

            String[] array = selectedLine.split(",");

            str
                    .append(id++).append(",")
                    .append(Helper.getRandomPesel()).append(",")
                    .append(Helper.getRandomName()).append(",")
                    .append(Helper.getRandomSurname()).append(",")
                    .append(Helper.getPersonalId()).append(",")
                    .append(Helper.getGender()).append(",")
                    .append(localDateTime.format(formatter)).append(",")
                    .append(localDateTimeEnd.format(formatter)).append(",")
                    .append(country).append(",")
                    .append(city).append(",")
                    .append(String.format(Locale.ENGLISH, "%.2f", deposit)).append(",")
                    .append(String.format(Locale.ENGLISH, "%.2f", cost)).append(",")
                    .append(array[6]).append(",")
                    .append(random.nextInt(10)).append(",")
                    .append(array[0]).append(",")
                    .append(random.nextInt(HOTELS_COUNT) + 10000).append(",")
                    .append(random.nextInt(10));

            travels.add(str.toString());
        }

        try {
            Files.write(Paths.get("E:\\workspace\\databasescript\\src\\main\\resources\\results\\wycieczki.csv"), travels);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
