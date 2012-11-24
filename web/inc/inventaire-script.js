$(function() {
    var ALINK_USER = 'a[id^="user"]';
    var DIV_USER_INFO = 'div[id^="user"]';
    var DIV_USER_ALTER = 'div[id^="alteruser"]';
    
    $(DIV_USER_INFO).hide();
    $(DIV_USER_ALTER).hide();
    
    $(ALINK_USER).click(function() {
        var currentUserId = $(this).attr('id');
        var currentUserInfoDiv= 'div'+ '[id="' + currentUserId + '"]';
        
        $('#test').html(currentUserId);
        $(currentUserInfoDiv).toggle(250);
    });
});