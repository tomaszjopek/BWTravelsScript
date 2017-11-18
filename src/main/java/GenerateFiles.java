import models.Hotel;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GenerateFiles {

    private final int HOTEL_CSV_RECORDS = 1000;
    private final int HOTELS_COUNT = 100;


    public static void main(String[] args) {

        GenerateFiles generateFiles = new GenerateFiles();
        generateFiles.generateHotelsCsv();

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

}
