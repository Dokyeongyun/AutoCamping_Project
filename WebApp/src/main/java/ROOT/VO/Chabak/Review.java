package ROOT.VO.Chabak;

public class Review {
    private int placeId;
    private String placeName;
    private String memberId;
    private String memberNickname;
    private String reviewContent;
    private Double evalPoint;
    private String evalDate;

    public Review() { }

    public int getPlaceId() {
        return placeId;
    }

    public void setPlaceId(int placeId) {
        this.placeId = placeId;
    }

    public String getPlaceName() {
        return placeName;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getMemberNickname() {
        return memberNickname;
    }

    public void setMemberNickname(String memberNickname) {
        this.memberNickname = memberNickname;
    }

    public String getReviewContent() {
        return reviewContent;
    }

    public void setReviewContent(String reviewContent) {
        this.reviewContent = reviewContent;
    }

    public Double getEvalPoint() {
        return evalPoint;
    }

    public void setEvalPoint(Double evalPoint) {
        this.evalPoint = evalPoint;
    }

    public String getEvalDate() {
        return evalDate;
    }

    public void setEvalDate(String evalDate) {
        this.evalDate = evalDate;
    }

    @Override
    public String toString() {
        return "Review{" +
                "placeId=" + placeId +
                ", placeName='" + placeName + '\'' +
                ", memberId='" + memberId + '\'' +
                ", memberNickname='" + memberNickname + '\'' +
                ", reviewContent='" + reviewContent + '\'' +
                ", evalPoint=" + evalPoint +
                ", evalDate='" + evalDate + '\'' +
                '}';
    }
}
