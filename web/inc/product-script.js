

var icons = {
      header: "ui-icon-star",
      activeHeader: "ui-icon-star"
    };
    

    
$(function() {
      
    $('#productSection').hide();
    
    $( "#accordion" ).accordion({
      active:false,
      icons: icons,
      collapsible:true,      
      heightStyle: "content"
    
    });
    
   var s = $('#errorP').val();
   var id = '#'+s;
   $(id).show();
   
   var s = $('#errorId').val();
   var id = '#'+s;
   $(id).show();

   var open = $('#addError').val();
   if(open=="open"){
   $('#addForm').show();
       
   }
  });

  
  function showForm(n){

           
        runShowEffect(n);
        return false;
        
}
     
 
   
  
 
  var cfg = ($.hoverintent = {
    sensitivity: 7,
    interval: 100
  });
  
  
  function runShowEffect(n){
      
      var options = {};
       options = { percent: 100 };
       $( "#"+n ).show("blind" , options,500);
       $( "#"+n ).attr('hidden','false');
       
    };

  function runHideEffect(n){
      
      var options = {};
      options = { percent: 0 };
      $( "#"+n).hide( "blind", options,500 );
  }
    
 
  $.event.special.hoverintent = {
    setup: function() {
      $( this ).bind( "mouseover", jQuery.event.special.hoverintent.handler );
    },
    teardown: function() {
      $( this ).unbind( "mouseover", jQuery.event.special.hoverintent.handler );
    },
    handler: function( event ) {
      var that = this,
        args = arguments,
        target = $( event.target ),
        cX, cY, pX, pY;
 
      function track( event ) {
        cX = event.pageX;
        cY = event.pageY;
      };
      pX = event.pageX;
      pY = event.pageY;
      function clear() {
        target
          .unbind( "mousemove", track )
          .unbind( "mouseout", arguments.callee );
        clearTimeout( timeout );
      }
      function handler() {
        if ( ( Math.abs( pX - cX ) + Math.abs( pY - cY ) ) < cfg.sensitivity ) {
          clear();
          event.type = "hoverintent";
          // prevent accessing the original event since the new event
          // is fired asynchronously and the old event is no longer
          // usable (#6028)
          event.originalEvent = {};
          jQuery.event.handle.apply( that, args );
        } else {
          pX = cX;
          pY = cY;
          timeout = setTimeout( handler, cfg.interval );
        }
      }
      var timeout = setTimeout( handler, cfg.interval );
      target.mousemove( track ).mouseout( clear );
      return true;
    }
  };


function viderChamps(c){

  $(c).attr('value','');
  $(c).css('color','black');
}