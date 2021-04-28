package ROOT.VO.Chabak;

public class Review {
    private int placeId;
    private String memberId;
    private String nickName;
    private String review_content;
    private Double evaluation_point;
    private String eval_time;

    public Review() { }

    public int getPlaceId() { return placeId; }
    public String getMemberId() { return memberId; }
    public String getNickName() { return nickName; }
    public String getReview_content() { return review_content; }
    public Double getEvaluation_point() { return evaluation_point; }
    public String getEval_time() { return eval_time; }

    public void setPlaceId(int placeId) { this.placeId = placeId; }
    public void setMemberId(String memberId) { this.memberId = memberId; }
    public void setNickName(String nickName) { this.nickName = nickName; }
    public void setReview_content(String review_content) { this.review_content = review_content; }
    public void setEvaluation_point(Double evaluation_point) { this.evaluation_point = evaluation_point; }
    public void setEval_time(String eval_time) { this.eval_time = eval_time; }

}
