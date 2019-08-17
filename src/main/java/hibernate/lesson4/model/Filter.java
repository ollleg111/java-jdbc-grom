package hibernate.lesson4.model;

public class Filter {

    private Integer numberOfGuests;
    private Double price;
    private Boolean breakfastIncluded;
    private Boolean petsAllowed;
    private String country;
    private String city;
    private String hotel;

    public Filter(Integer numberOfGuests, Double price, Boolean breakfastIncluded, Boolean petsAllowed,
                  String country, String city, String hotel) {
        this.numberOfGuests = numberOfGuests;
        this.price = price;
        this.breakfastIncluded = breakfastIncluded;
        this.petsAllowed = petsAllowed;
        this.country = country;
        this.city = city;
        this.hotel = hotel;
    }

    public Integer getNumberOfGuests() {
        return numberOfGuests;
    }

    public Double getPrice() {
        return price;
    }

    public Boolean getBreakfastIncluded() {
        return breakfastIncluded;
    }

    public Boolean getPetsAllowed() {
        return petsAllowed;
    }

    public String getCountry() {
        return country;
    }

    public String getCity() {
        return city;
    }

    public String getHotel() {
        return hotel;
    }
}
