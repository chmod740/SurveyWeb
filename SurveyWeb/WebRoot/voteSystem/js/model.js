$(document).ready(function(){
    $(".add").click(function(){
        $(this).parent().find('ol').append($(this).parent().find('ol').find('li:last').clone());
    });

    $(".del").click(function(){
        $(this).parent().find('ol').find('li:last').remove();
    });

    $("#button1").click(function(){
        var number=-1;
        $(".inner").each(function(){
            number++;
        });

        var th=$("ol[type='1']");
        var cloneChild=$(".moban").children().clone();
        cloneChild.find(".custom-radio").attr('name','optionsRadios'+number);
        th.append(cloneChild);
        th.find(".add:last()").click(function(event) {
            $(this).parent().find('ol').append($(this).parent().find('ol').find('li:last').clone());
        });
        th.find(".del:last()").click(function(event) {
            $(this).parent().find('ol').find('li:last').remove();
        });
    });

    $("#button2").click(function(){
        var th=$("ol[type='1']");
        var cloneChild=$(".completion").children().clone();
        th.append(cloneChild);
    });

    $("#button3").click(function(){
        var number=1;
        var myTime=new Date;
        var keyWord1="";
        var keyWord2="";
        keyWord1=keyWord1+$("#questionnaireName").val()+"@#@"+myTime.getTime()+"@#@";
        $(".inner").each(function(){
            keyWord1=keyWord1+number+"@#@";
            keyWord2=keyWord2+number+"@#@";
            number++;
            $(this).find("input[type='text']").each(function(){
                keyWord1=keyWord1+$(this).val()+"@#@";
            })
            $(this).find("input[type='checkbox']").each(function(){
                keyWord2=keyWord2+$(this).is(':checked')+"@#@";
            })

            keyWord1=keyWord1+"#$#@#@";
            keyWord2=keyWord2+"#$#@#@";
        });

        $.ajax({
            type: "POST",
            url: "addSurvey.action",
            data: "keyWord1="+keyWord1+"&keyWord2="+keyWord2,
            success: function(msg){
                alert("" + msg);
                window.location.href= msg;
            }
        });
    });

    $("#button4").click(function(){
        $(this).parent().parent().parent().find('#p').find('li:last').parent().parent().remove();
    });

    $(window).bind('beforeunload',function(){return '您输入的信息尚未保存，确定离开此页面吗？';});
});