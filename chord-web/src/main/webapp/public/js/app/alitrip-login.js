/**
 * @Author: zhang
 * @Time:2017/4/30
 */

$(function () {
    function funToPwd(event) {
        let password = $("#password").val() ;
        if (null === password || '' === password) {
            $.alert({
                title: "提示",
                content: "密码不为空"
            });
            event.preventDefault();
        } else {
            $("#password").val(btoa($("#token").val() + "->" + password));
        }
    }

    function funToUserKey(event) {
        let userKey = $("#userKey").val();
        if (null === userKey || '' === userKey) {
            $.alert({
                title: "提示",
                content: "账号名不为空"
            });
            event.preventDefault();
        }

    }


    function checkVal() {
        $("input[name='password']").on("blur", funToPwd);
        $("input[name='userKey']").on("blur", funToUserKey);
        $("form").on({"submit": funToUserKey, "submit": funToPwd});
    }

    let init = function () {
        /**
         * 校验输入内容
         */
        checkVal();
    }();
});