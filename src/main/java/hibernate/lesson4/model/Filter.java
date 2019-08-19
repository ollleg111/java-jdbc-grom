package hibernate.lesson4.model;

public class Filter {

    private Integer numberOfGuests;
    private Double price;
    private Boolean breakfastIncluded;
    private Boolean petsAllowed;
    private String country;
    private String city;
    private String hotelName;

    public Filter(Integer numberOfGuests, Double price, Boolean breakfastIncluded, Boolean petsAllowed,
                  String country, String city, String hotelName) {
        this.numberOfGuests = numberOfGuests;
        this.price = price;
        this.breakfastIncluded = breakfastIncluded;
        this.petsAllowed = petsAllowed;
        this.country = country;
        this.city = city;
        this.hotelName = hotelName;
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
