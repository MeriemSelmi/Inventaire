$(function() {
    var A_USER_INFO = 'a[id^="userinfo"]';
    var A_USER_UPDATE = 'a[id^="userupdate"]';
    var DIV_USER_INFO = 'div[id^="userinfo"]';
    var DIV_USER_UPDATE = 'div[id^="userupdate"]';
    
    $(DIV_USER_INFO).hide();
    $(DIV_USER_UPDATE).hide();
    
    $(A_USER_INFO).click(function() {
        var currentUserId = $(this).attr('id');
        var currentUserInfoDiv= 'div'+ '[id="' + currentUserId + '"]';
        
        $('#test').html(currentUserId);
        $(currentUserInfoDiv).toggle(250);
    });
    
    $(A_USER_UPDATE).click(function() {
        var currentUserId = $(this).attr('id');
        var currentUserUpdateDiv= 'div'+ '[id="' + currentUserId + '"]';
        
        $('#test').html(currentUserId);
        $(currentUserUpdateDiv).toggle(250);
    });
});