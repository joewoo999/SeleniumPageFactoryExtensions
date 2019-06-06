const STYLE_FB = 0;
const STYLE_LT = 1;

$(document).ready(function () {
    initEvent();
    // addMask();
    // setTimeout(removeMask, 2000);
});

function initEvent() {
    var x = 0;
    var y = 0;
    $("div.over").mouseover(function () {
        $(".over span").text(x += 1);
    });

    $("div.enter").mouseenter(function () {
        $(".enter span").text(y += 1);
    });

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
        e.preventDefault();
        setTimeout(function () { removeMask(); alert('submit success');}, 2000);
        return false;
    });
}

function addMask(loadingStyleName) {
    $("body").prepend('<div id="mask" class="mask"></div>')
    $("#mask").append('<div style="height:40%"></div>');
    loadingStyleName = arguments[0] || 0;
    if (STYLE_FB == loadingStyleName) {
        $("#mask").append('<div id="facebook"><div class="bar"></div><div class="bar"></div><div class="bar"></div></div>');
    } 
}

function removeMask() {
    $("#mask").remove();
}
