package ROOT.VO.Chabak.facility;

public class Toilet {
    private int toiletId;
    private String address;
    private String open_time;
    private double latitude;
    private double longitude;

    public Toilet(int toiletId, String address, String open_time, double latitude, double longitude) {
        this.toiletId = toiletId;
        this.address = address;
        this.open_time = open_time;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Toilet() { }

    public int getToiletId() { return toiletId; }
    public String getAddress() { return address; }
    public String getOpen_time() { return open_time; }
    public double getLatitude() { return latitude; }
    public double getLongitude() { return longitude; }

    public void setToiletId(int toiletId) { this.toiletId = toiletId; }
    public void setAddress(String address) { this.address = address; }
    public void setOpen_time(String open_time) { this.open_time = open_time; }
    public void setLatitude(double latitude) { this.latitude = latitude; }
    public void setLongitude(double longitude) { this.longitude = longitude; }

}
