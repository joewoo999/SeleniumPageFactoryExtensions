function addFrame(newPageUrl) {
    $("body").append('<div class="box"></div>');
    $(".box").append('<div class="box-title"></div>');
    $("div.box-title").append('<button class="box-title close">X</button>');
    $("div.box-title").append('<h4 class="box-title text">frame title</h4>');
    $(".box").append('<div class="box-body"></div>');
    $("div.box-body").append('<iframe class="box frame" frameborder="0" src="' + newPageUrl + '"></iframe>');
}

function removeFrame() {
    $(".box").remove();
}

