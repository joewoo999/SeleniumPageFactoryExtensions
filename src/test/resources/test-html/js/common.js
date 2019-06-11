const STYLE_FB = 0;

$(document).ready(function () {
    initEvent();
});

function initEvent() {
    $("#alert").click(function () {
        alert("click");
        $("#result").val("alert");
    });

    $("#confirm").click(function () {
        if (confirm("choose")) {
            $("#result").val("confirm ok");
        } else {
            $("#result").val("confirm cancel");
        }
    });

    $("#prompt").click(function () {
        var res = prompt("enter:");
        if (null != res) {
            res = 0 == res.length ? "blank" : res;
            $("#result").val(res);
        } else {
            $("#result").val("prompt cancel");
        }
    });

    $("#result").dblclick(function () {
        $("#result").val("double click");
    });

    $('#submit-form').submit(function (e) {
        addMask();
        addLoading();
        setTimeout(function () { removeMask(); alert('submit success'); }, 2000);
        return false;
    });

    $('#new-iframe').click(function (e) {
        addMask();
        addFrame("./new_window.html");

        $('.box-title.close').click(function (e) {
            removeFrame();
            removeMask();
        });
    });
}

function addMask(loadingStyleName) {
    $("body").prepend('<div id="mask" class="mask"></div>')
    $("#mask").append('<div style="height:40%"></div>');
}

function addLoading(loadingStyleName) {
    loadingStyleName = arguments[0] || 0;
    if (STYLE_FB == loadingStyleName) {
        $("#mask").append('<div id="facebook"><div class="bar"></div><div class="bar"></div><div class="bar"></div></div>');
    }
}

function removeMask() {
    $("#mask").remove();
}

function addFrame(newPageUrl) {
    $("#append").append('<div class="box"></div>');
    $(".box").append('<div class="box-title"></div>');
    $("div.box-title").append('<button class="box-title close">X</button>');
    $("div.box-title").append('<h4 class="box-title text">frame title</h4>');
    $(".box").append('<div class="box-body"></div>');
    $("div.box-body").append('<iframe class="box frame" src="' + newPageUrl + '">');
}

function removeFrame() {
    $(".box").remove();
}


