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
