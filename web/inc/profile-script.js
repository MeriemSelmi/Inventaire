$(function() {
    var A_USER_UPDATE = 'a[id^="userupdate"]';
    var DIV_USER_UPDATE = 'div[id^="userupdate"]';

    $(DIV_USER_UPDATE ).dialog({
        autoOpen: false,
        width: 'auto',
        modal: true
    });
    
    $(A_USER_UPDATE).click(function() {
        var currentUserId = $(this).attr('id').replace('userupdate', '');
        var action = document.getElementById('updateform').action;
        if(! /id/.test(action)){
            document.getElementById('updateform').action=action+'?id='+currentUserId;
        }
        $(DIV_USER_UPDATE).dialog("open");
    });
   
});