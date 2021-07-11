function showErrorMsg(obj, msg) {
    obj.attr("class", "joinErrorText");
    obj.html(msg);
    obj.show();
}

function showSuccessMsg(obj, msg) {
    obj.attr("class", "joinErrorText green");
    obj.html(msg);
    obj.show();
}

function hideMsg(obj) {
    obj.hide();
}