package com.example.capstonedesignproject.view.Board;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.capstonedesignproject.Adapter.CommentAdapter;
import com.example.capstonedesignproject.VO.ArticleVO;
import com.example.capstonedesignproject.VO.CommentVO;
import com.example.capstonedesignproject.R;
import com.example.capstonedesignproject.view.ETC.HomeActivity;
import com.example.capstonedesignproject.Server.SetApplication;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class ShowPostActivity extends AppCompatActivity {
    boolean userLike; // 현재 세션의 유저가 해당 게시글 좋아요 여부
    @BindView(R.id.toolbar) Toolbar mToolbar;
    @BindView(R.id.TV_postTitle) TextView TV_title;
    @BindView(R.id.TV_postContent) TextView TV_content;
    @BindView(R.id.TV_postWriter) TextView TV_postWriter;
    @BindView(R.id.TV_postDate) TextView TV_postDate;
    @BindView(R.id.BT_postImage) ImageButton BT_postImage;
    @BindView(R.id.LL_postImage) LinearLayout LL_postImage;
    @BindView(R.id.RV_comment) RecyclerView RV_comment;
    @BindView(R.id.PB_comment) ProgressBar PB_comment;
    @BindView(R.id.CL_snackContainer) ConstraintLayout CL_snackContainer;
    @BindView(R.id.IB_commentInput) ImageButton IB_commentInput;
    @BindView(R.id.ET_comment) EditText ET_comment;

    private CommentAdapter commentAdapter;
    private ArticleVO articleData;
    int articleID = 0;
    String memberID = "";
    boolean isDeleted = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_post);
        ButterKnife.bind(this);

        // Intent로 넘어온 데이터 받기
        Intent intent = getIntent();
        articleID = intent.getIntExtra("articleID", 0);
        memberID = intent.getStringExtra("memberID");
        // 초기 설정
        Init();
        // 게시글 정보 읽어온 후 View 에 Binding
        loadArticleData();
        // 댓글 리스트 읽어오기
        loadComment();
    }

    /**
     * 초기 설정
     */
    private void Init(){
        mToolbar.setTitle("");
        setSupportActionBar(mToolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true); // 툴바에 뒤로가기버튼 추가

        commentAdapter = new CommentAdapter();
        RV_comment.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        RV_comment.setHasFixedSize(true);
        RV_comment.setAdapter(commentAdapter);
    }

    /**
     * 글 정보 읽어오기
     */
    private void loadArticleData(){
        PB_comment.setVisibility(View.VISIBLE);
        final SetApplication application = (SetApplication) Objects.requireNonNull(this).getApplication();
        Observable<List<ArticleVO>> observable = application.getArticleService().getArticle(articleID);
        observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<ArticleVO>>() {
                    @Override
                    public void onNext(List<ArticleVO> items) {
                        articleData = items.get(0);
                        PB_comment.setVisibility(View.GONE);
                    }
                    @Override
                    public void onError(Throwable e) {
                        Log.d("수신","수신실패");
                        e.printStackTrace();
                        Snackbar.make(CL_snackContainer, "게시판 데이터를 읽어올 수 없습니다.", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                        PB_comment.setVisibility(View.GONE);
                    }
                    @Override
                    public void onCompleted() {
                        articleDataBinding();
                    }
                });
    }

    /**
     * 게시글 정보 Binding
     */
    private void articleDataBinding(){
        TV_title.setText(articleData.getTitle());   // 제목
        TV_content.setText(articleData.getContent()); // 내용
        TV_postWriter.setText(articleData.getNickName()); // 작성자
        TV_postDate.setText(articleData.getCreateTime()); // 작성일자 및 시간

        // 첨부 사진
        if(articleData.getUrlPath().equals("")){
            LL_postImage.setVisibility(View.GONE);
        }
        Glide.with(this)
                .load(HomeActivity.SERVER_URL + articleData.getUrlPath())
                .placeholder(R.drawable.button_border_gray)
                .into(BT_postImage);
    }

    /**
     * 댓글 리스트 읽기
     */
    private void loadComment(){
        PB_comment.setVisibility(View.VISIBLE);
        commentAdapter.clear();
        final SetApplication application = (SetApplication) Objects.requireNonNull(this).getApplication();
        Observable<List<CommentVO>> observable = application.getArticleService().getComments(articleID);
        observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<CommentVO>>() {
                    @Override
                    public void onNext(List<CommentVO> items) {
                        Log.d("수신", "총 수신 개수: "+items.size());
                        for(int i=0; i<items.size(); i++){
                            Log.d("수신", String.valueOf(items.get(i)));
                            commentAdapter.setItemsAndRefresh(items.get(i));
                        }
                        PB_comment.setVisibility(View.GONE);
                    }
                    @Override
                    public void onError(Throwable e) {
                        Log.d("수신","수신실패");
                        e.printStackTrace();
                        Snackbar.make(CL_snackContainer, "댓글 데이터를 읽어올 수 없습니다.", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                        PB_comment.setVisibility(View.GONE);
                    }
                    @Override
                    public void onCompleted() { }
                });
    }

    /**
     * 댓글 입력하기
     */
    @OnClick(R.id.IB_commentInput) void writeComment(){
        String comment = ET_comment.getText().toString().trim();
        if(comment.equals("")){ return; }

        PB_comment.setVisibility(View.VISIBLE);
        final SetApplication application = (SetApplication) Objects.requireNonNull(this).getApplication();
        Observable<String> observable = application.getArticleService().writeComment(articleID, HomeActivity.memberID, comment);
        observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onNext(String s) {
                        PB_comment.setVisibility(View.GONE);
                    }
                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(application, "댓글 작성에 실패했습니다. 잠시 후 다시 시도해주세요.", Toast.LENGTH_SHORT).show();
                        PB_comment.setVisibility(View.GONE);
                    }
                    @Override
                    public void onCompleted() {
                        commentAdapter.clear();
                        ET_comment.setText("");
                        loadComment();
                    }
                });
    }

    /**
     * 글 삭제하기
     */
    private void deleteArticle(){
        final SetApplication application = (SetApplication) Objects.requireNonNull(this).getApplication();
        Observable<String> observable = application.getArticleService().deleteArticle(articleID);
        observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onNext(String s) { }
                    @Override
                    public void onError(Throwable e) {
                        isDeleted = false;
                        Toast.makeText(application, "게시글 삭제에 실패했습니다..", Toast.LENGTH_SHORT).show();
                        e.printStackTrace();
                    }
                    @Override
                    public void onCompleted() {
                        if(isDeleted){
                            setResult(1);
                            finish();
                        }
                    }
                });
    }

    @OnClick(R.id.BT_LikePost) void LikePost() {
        if (userLike) {
            Toast.makeText(this, "이미 Like 한 게시글입니다.", Toast.LENGTH_SHORT).show();
        } else {
            userLike = true;
            Toast.makeText(this, "Like! 감사합니다.", Toast.LENGTH_SHORT).show();
        }
    }
    @OnClick(R.id.BT_UnLikePost) void UnLikePost(){
        if (userLike) {
            userLike = false;
            Toast.makeText(this, "UnLike!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "UnLike!", Toast.LENGTH_SHORT).show();
        }
    }


    // 툴바에 메뉴 인플레이트
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        if (memberID.equals(HomeActivity.memberID)) {
            inflater.inflate(R.menu.show_post_updatable_menu, menu);
        } else {
            inflater.inflate(R.menu.show_post_menu, menu);
        }
        return true;
    }

    // 툴바 메뉴 클릭 리스너
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.post_refresh:
                loadArticleData();
                loadComment();
                return true;
            case android.R.id.home:
                finish();
                return true;
            case R.id.update:
                Intent intent = new Intent(this, WritePostActivity.class);
                intent.putExtra("writeMode", "UPDATE");
                intent.putExtra("articleData", articleData);
                startActivityForResult(intent, 1);
                return true;
            case R.id.delete:
                deleteArticle();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1){
            if(resultCode == 1){
                loadArticleData();
                loadComment();
            }
        }
    }
}

