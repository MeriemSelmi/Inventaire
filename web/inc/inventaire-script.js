$(function() {
    var A_USER_UPDATE = 'a[id^="userupdate"]';
    var A_USER_ADD = 'a[id="useradd"]';
    var DIV_USERS_LIST = 'div[id="users"]';
    var DIV_USER_UPDATE = 'div[id^="userupdate"]';
    var DIV_USER_ADD = 'div[id="useradd"]';
    
    $(DIV_USERS_LIST).accordion({
        collapsible:true,
        active:false,
        header: 'h3'
   });

   $("#userdelete").click(function() {
      window.location = $(this).attr('href');
      return false;
   });
   
   
    $(DIV_USER_ADD +','+DIV_USER_UPDATE ).dialog({
        autoOpen: false,
        width: 'auto',
        modal: true
    });
    
    $(A_USER_ADD).click(function() {
        $(DIV_USER_ADD).dialog("open");
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