package com.mongodb.airbnb;

public class Listing {

    private String id;
    private String name;
    private long hostId;
    private String hostName;
    private String neighbourhoodGroup;
    private String neighbourhood;
    private double latitude;
    private double longitude;
    private String roomType;
    private double price;
    private int minimumNights;
    private int numberOfReviews;
    private String lastReview;
    private Double reviewsPerMonth;
    private int calculatedHostListingsCount;
    private int availability365;
    private int numberOfReviewsLtm;
    private String license;

    public Listing(String id, String name, long hostId, String hostName, String neighbourhoodGroup,
                   String neighbourhood, double latitude, double longitude, String roomType,
                   double price, int minimumNights, int numberOfReviews, String lastReview,
                   Double reviewsPerMonth, int calculatedHostListingsCount, int availability365,
                   int numberOfReviewsLtm, String license) {
        this.id = id;
        this.name = name;
        this.hostId = hostId;
        this.hostName = hostName;
        this.neighbourhoodGroup = neighbourhoodGroup;
        this.neighbourhood = neighbourhood;
        this.latitude = latitude;
        this.longitude = longitude;
        this.roomType = roomType;
        this.price = price;
        this.minimumNights = minimumNights;
        this.numberOfReviews = numberOfReviews;
        this.lastReview = lastReview;
        this.reviewsPerMonth = reviewsPerMonth;
        this.calculatedHostListingsCount = calculatedHostListingsCount;
        this.availability365 = availability365;
        this.numberOfReviewsLtm = numberOfReviewsLtm;
        this.license = license;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public long getHostId() { return hostId; }
    public void setHostId(long hostId) { this.hostId = hostId; }

    public String getHostName() { return hostName; }
    public void setHostName(String hostName) { this.hostName = hostName; }

    public String getNeighbourhoodGroup() { return neighbourhoodGroup; }
    public void setNeighbourhoodGroup(String neighbourhoodGroup) { this.neighbourhoodGroup = neighbourhoodGroup; }

    public String getNeighbourhood() { return neighbourhood; }
    public void setNeighbourhood(String neighbourhood) { this.neighbourhood = neighbourhood; }

    public double getLatitude() { return latitude; }
    public void setLatitude(double latitude) { this.latitude = latitude; }

    public double getLongitude() { return longitude; }
    public void setLongitude(double longitude) { this.longitude = longitude; }

    public String getRoomType() { return roomType; }
    public void setRoomType(String roomType) { this.roomType = roomType; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public int getMinimumNights() { return minimumNights; }
    public void setMinimumNights(int minimumNights) { this.minimumNights = minimumNights; }

    public int getNumberOfReviews() { return numberOfReviews; }
    public void setNumberOfReviews(int numberOfReviews) { this.numberOfReviews = numberOfReviews; }

    public String getLastReview() { return lastReview; }
    public void setLastReview(String lastReview) { this.lastReview = lastReview; }

    public Double getReviewsPerMonth() { return reviewsPerMonth; }
    public void setReviewsPerMonth(Double reviewsPerMonth) { this.reviewsPerMonth = reviewsPerMonth; }

    public int getCalculatedHostListingsCount() { return calculatedHostListingsCount; }
    public void setCalculatedHostListingsCount(int calculatedHostListingsCount) {
        this.calculatedHostListingsCount = calculatedHostListingsCount;
    }

    public int getAvailability365() { return availability365; }
    public void setAvailability365(int availability365) { this.availability365 = availability365; }

    public int getNumberOfReviewsLtm() { return numberOfReviewsLtm; }
    public void setNumberOfReviewsLtm(int numberOfReviewsLtm) { this.numberOfReviewsLtm = numberOfReviewsLtm; }

    public String getLicense() { return license; }
    public void setLicense(String license) { this.license = license; }

    @Override
    public String toString() {
        return "Listing {\n" +
                "  id='" + id + '\'' + ",\n" +
                "  name='" + name + '\'' + ",\n" +
                "  hostId=" + hostId + ",\n" +
                "  hostName='" + hostName + '\'' + ",\n" +
                "  neighbourhoodGroup='" + neighbourhoodGroup + '\'' + ",\n" +
                "  neighbourhood='" + neighbourhood + '\'' + ",\n" +
                "  latitude=" + latitude + ",\n" +
                "  longitude=" + longitude + ",\n" +
                "  roomType='" + roomType + '\'' + ",\n" +
                "  price=" + price + ",\n" +
                "  minimumNights=" + minimumNights + ",\n" +
                "  numberOfReviews=" + numberOfReviews + ",\n" +
                "  lastReview='" + lastReview + '\'' + ",\n" +
                "  reviewsPerMonth=" + reviewsPerMonth + ",\n" +
                "  calculatedHostListingsCount=" + calculatedHostListingsCount + ",\n" +
                "  availability365=" + availability365 + ",\n" +
                "  numberOfReviewsLtm=" + numberOfReviewsLtm + ",\n" +
                "  license='" + license + '\'' + "\n" +
                '}';
    }
}
