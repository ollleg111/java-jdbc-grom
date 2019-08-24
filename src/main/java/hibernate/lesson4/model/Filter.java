package hibernate.lesson4.model;

public class Filter {

    /*
     public static final String FIND_ROOMS_BY_FILTER         =
                    "SELECT HOTEL.COUNTRY, HOTEL.CITY, HOTEL.NAME,ROOM.NUMBER_GUESTS, ROOM.PETS, " +
                            "ROOM.BREAKFAST, ROOM.PRICE FROM ROOM " +
                            "JOIN HOTEL ON ROOM.HOTEL_ID = HOTEL.HOTEL_ID AND " +
                            "HOTEL.COUNTRY = ? AND " +
                            "HOTEL.CITY = ? AND " +
                            "HOTEL.NAME LIKE 'A%' AND " +
                            "ROOM.BREAKFAST = ? AND " +
                            "ROOM.PETS  = ? AND " +
                            "ROOM.NUMBER_GUESTS = ? AND " +
                            "ROOM.PRICE <= ? ";
     */
    private String country;
    private String city;
    private String hotelName;
    private Boolean breakfastIncluded;
    private Boolean petsAllowed;
    private Integer numberOfGuests;
    private Double price;

    public Filter(String country, String city, String hotelName, Boolean breakfastIncluded,
                  Boolean petsAllowed, Integer numberOfGuests, Double price) {
        this.country = country;
        this.city = city;
        this.hotelName = hotelName;
        this.breakfastIncluded = breakfastIncluded;
        this.petsAllowed = petsAllowed;
        this.numberOfGuests = numberOfGuests;
        this.price = price;
    }

    public Integer getNumberOfGuests() {
        return numberOfGuests;
    }

    public Double getPrice() {
        return price;
    }

    public Boolean isBreakfastIncluded() {
        return breakfastIncluded;
    }

    public int getBreakfastIncluded() {
        return breakfastIncluded ? 1 : 0;
    }

    public Boolean isPetsAllowed() {
        return petsAllowed;
    }

    public int getPetsAllowed() {
        return petsAllowed ? 1 : 0;
    }

    public String getCountry() {
        return country;
    }

    public String getCity() {
        return city;
    }

    public String getHotelName() {
        return hotelName;
    }
}
