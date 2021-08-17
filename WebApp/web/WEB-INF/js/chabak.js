function filterSearch() {
    let region_filter = "";
    let option_filter = "";
    let search_keyword = "";

    $('input:checkbox[name="region_check"]:checked').each(function () {
        region_filter += this.value + "_";
    });

    $('input:checkbox[name="option_check"]').each(function () {
        if (this.checked) {
            option_filter += 'T_';
        } else {
            option_filter += 'F_';
        }
    });

    search_keyword = $("#searchKeyword").val();

    location.href = '/chabak/chabakSearch?'
        + 'region=' + encodeURI(region_filter, "UTF-8")
        + '&facility=' + encodeURI(option_filter, "UTF-8")
        + '&keyword=' + encodeURI(search_keyword, "UTF-8");
}

/* 차박지 검색조건 - 지역 선택 Checkbox 클릭 이벤트 */
function changeSearchRegionCheck(checkId) {
    let regionCheckObj = $("#" + checkId);
    let regionCheckParentObj = regionCheckObj.parent("td");
    if (regionCheckObj.is(":checked")) {
        regionCheckObj.prop('checked', false);
        regionCheckParentObj.css("background", "#e1e1e1")
        regionCheckParentObj.css("color", "black")
    } else {
        regionCheckObj.prop('checked', true);
        regionCheckParentObj.css("background", "crimson")
        regionCheckParentObj.css("color", "white")
    }
}