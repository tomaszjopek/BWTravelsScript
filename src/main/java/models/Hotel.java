package models;

import java.time.LocalDate;

public class Hotel {
    private int id;
    private String name;
    private String ownerSurname;
    private String ownerName;
    private LocalDate date;
    private int personel;
    private int stars;
    private String country;
    private String region;
    private String city;


    public static final class HotelBuilder {
        private int id;
        private String name;
        private String ownerSurname;
        private String ownerName;
        private LocalDate date;
        private int personel;
        private int stars;
        private String country;
        private String region;
        private String city;

        public HotelBuilder() {
        }

        public static HotelBuilder aHotel() {
            return new HotelBuilder();
        }

        public HotelBuilder withId(int id) {
            this.id = id;
            return this;
        }

        public HotelBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public HotelBuilder withOwnerSurname(String ownerSurname) {
            this.ownerSurname = ownerSurname;
            return this;
        }

        public HotelBuilder withOwnerName(String ownerName) {
            this.ownerName = ownerName;
            return this;
        }

        public HotelBuilder withDate(LocalDate date) {
            this.date = date;
            return this;
        }

        public HotelBuilder withPersonel(int personel) {
            this.personel = personel;
            return this;
        }

        public HotelBuilder withStars(int stars) {
            this.stars = stars;
            return this;
        }

        public HotelBuilder withCountry(String country) {
            this.country = country;
            return this;
        }

        public HotelBuilder withRegion(String region) {
            this.region = region;
            return this;
        }

        public HotelBuilder withCity(String city) {
            this.city = city;
            return this;
        }

        public Hotel build() {
            Hotel hotel = new Hotel();
            hotel.date = this.date;
            hotel.name = this.name;
            hotel.ownerSurname = this.ownerSurname;
            hotel.region = this.region;
            hotel.city = this.city;
            hotel.stars = this.stars;
            hotel.ownerName = this.ownerName;
            hotel.personel = this.personel;
            hotel.country = this.country;
            hotel.id = this.id;
            return hotel;
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOwnerSurname() {
        return ownerSurname;
    }

    public void setOwnerSurname(String ownerSurname) {
        this.ownerSurname = ownerSurname;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getPersonel() {
        return personel;
    }

    public void setPersonel(int personel) {
        this.personel = personel;
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
