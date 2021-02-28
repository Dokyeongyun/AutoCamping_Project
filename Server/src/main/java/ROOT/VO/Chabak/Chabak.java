package ROOT.VO.Chabak;

import java.util.Map;

public class Chabak {
    private int placeId;
    private String placeName;
    private String address;
    private String phone_number;
    private String introduce;
    private String filePath;
    private int jjim;
    private double latitude;
    private double longitude;
    private double avg_point;
    private Map<String, Integer> utilityCount;

    public Chabak() { }

    public int getPlaceId() { return placeId; }
    public String getPlaceName() { return placeName; }
    public String getAddress() { return address; }
    public String getPhone_number() { return phone_number; }
    public String getIntroduce() { return introduce; }
    public String getFilePath() { return filePath; }
    public int getJjim() { return jjim; }
    public double getLatitude() { return latitude; }
    public double getLongitude() { return longitude; }
    public double getAvg_point() { return avg_point; }
    public Map<String, Integer> getUtilityCount() { return utilityCount; }

    public void setPlaceId(int placeId) { this.placeId = placeId; }
    public void setPlaceName(String placeName) { this.placeName = placeName; }
    public void setAddress(String address) { this.address = address; }
    public void setPhone_number(String phone_number) { this.phone_number = phone_number; }
    public void setIntroduce(String introduce) { this.introduce = introduce; }
    public void setFilePath(String filePath) { this.filePath = filePath; }
    public void setJjim(int jjim) { this.jjim = jjim; }
    public void setLatitude(double latitude) { this.latitude = latitude; }
    public void setLongitude(double longitude) { this.longitude = longitude; }
    public void setAvg_point(double avg_point) { this.avg_point = avg_point; }
    public void setUtilityCount(Map<String, Integer> utilityCount) { this.utilityCount = utilityCount; }
}
