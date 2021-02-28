package ROOT.VO.Chabak.facility;

public class Fishing {
    private int fishingspotId;
    private String name;
    private String address;
    private String type;
    private String phone;
    private double latitude;
    private double longitude;

    public Fishing(int fishingspotId, String name, String address,
                   String type, String phone, double latitude, double longitude) {
        this.fishingspotId = fishingspotId;
        this.name = name;
        this.address = address;
        this.type = type;
        this.phone = phone;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Fishing() { }

    public int getFishingspotId() { return fishingspotId; }
    public String getName() { return name; }
    public String getAddress() { return address; }
    public String getType() { return type; }
    public String getPhone() { return phone; }
    public double getLatitude() { return latitude; }
    public double getLongitude() { return longitude; }

    public void setFishingspotId(int fishingspotId) { this.fishingspotId = fishingspotId; }
    public void setName(String name) { this.name = name; }
    public void setAddress(String address) { this.address = address; }
    public void setType(String type) { this.type = type; }
    public void setPhone(String phone) { this.phone = phone; }
    public void setLatitude(double latitude) { this.latitude = latitude; }
    public void setLongitude(double longitude) { this.longitude = longitude; }

}
