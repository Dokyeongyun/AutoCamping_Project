package ROOT.VO.Chabak;

public class ChabakDibs {

    private String memberId;

    private int placeId;

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public int getPlaceId() {
        return placeId;
    }

    public void setPlaceId(int placeId) {
        this.placeId = placeId;
    }

    @Override
    public String toString() {
        return "ChabakDibs{" +
                "memberId='" + memberId + '\'' +
                ", placeId=" + placeId +
                '}';
    }
}
