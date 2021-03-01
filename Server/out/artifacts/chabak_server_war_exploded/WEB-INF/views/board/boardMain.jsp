<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:include page="../header.jsp"/>

<div class="full_container">
    <div class="left_container">

    </div>
    <div class="right_container">
        <div class="page_title_region">
            <div class="title_txt">전체글보기</div>
            <div class="small_txt">5,000 개의 글</div>
        </div>
        <div class="board_list_region">
            <table class="board_list_table">
                <thead>
                <tr>
                    <th width="10%">게시판이름</th>
                    <th width="50%">제목</th>
                    <th width="20%">작성자</th>
                    <th width="10%">작성일</th>
                    <th width="10%">조회수</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td>공지사항</td>
                    <td>게시글 테스트 제목</td>
                    <td>도경윤</td>
                    <td>2021-03-01</td>
                    <td>1</td>
                </tr>
                </tbody>
            </table>
        </div>
        <div class="pagination_region">
            <ul class="pagination">
                <li class="page-item"><a class="page-link" href="#">Previous</a></li>
                <li class="page-item"><a class="page-link" href="#">1</a></li>
                <li class="page-item"><a class="page-link" href="#">2</a></li>
                <li class="page-item"><a class="page-link" href="#">3</a></li>
                <li class="page-item"><a class="page-link" href="#">4</a></li>
                <li class="page-item"><a class="page-link" href="#">5</a></li>
                <li class="page-item"><a class="page-link" href="#">Next</a></li>
            </ul>
        </div>
        <div class="board_search_region">
            <form name="frmSearch" action="" onsubmit="return false;">
                <select id="searchDate" class="form-control" style="width: 150px; display: inline-block">
                    <option value="all">전체기간</option>
                    <option value="1d">1일</option>
                    <option value="1w">1주</option>
                    <option value="1m">1개월</option>
                    <option value="6m">6개월</option>
                    <option value="1y">1년</option>
                </select>
                <select id="searchBy" class="form-control" style="width: 150px; display: inline-block">
                    <option value="titleAndContent">제목+내용</option>
                    <option value="title">제목만</option>
                    <option value="writer">글작성자</option>
                    <option value="comment">댓글내용</option>
                    <option value="comment_writer">댓글작성자</option>
                </select>
                <input type="text" id="searchWord" class="form-control" placeholder="검색어를 입력해주세요" style="width: 200px; display: inline-block"/>
                <button class="btn" type="button" style="background-color: #77b7b4; color: white">검색</button>
            </form>
        </div>

    </div>
</div>

<jsp:include page="../footer.jsp"/>

<style>
    .page_title_region{
        border-bottom: 1px solid black;
        margin: 10px 20px;
    }
    .title_txt {
        padding: 10px;
        font-size: 25px;
        font-weight: bolder;
    }
    .small_txt {
        padding: 10px;
        font-size: 15px;
        font-weight: bold;
    }
    .board_list_region {
        margin: 0 20px;
        border-bottom: 1px solid black;
    }
    .board_list_table {
        width: 100%;
        text-align-last: center;
    }
    .board_list_table > thead > tr {
        height: 40px;
        border-bottom: 2px solid #ddd;
    }
    .board_list_table > tbody > tr {
        height: 40px;
        border-bottom: 1px solid #ddd;
    }
    .pagination_region {
        margin-top: 30px;
        width: 100%;
        text-align-last: center;
        background-color: #f9f9f8;
        padding: 16px 0 5px;
    }
    .board_search_region {
        padding: 16px 0 16px;
        border-top: 1px solid #ebebea;
        background-color: #f9f9f8;
        text-align: center;
    }
</style>