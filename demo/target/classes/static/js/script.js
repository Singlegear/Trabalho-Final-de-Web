$ (document).ready(function(){
    $(".hidden_div").hide();
    $(".open_div2").hide();
    $(".open_div").click(function() {
        $(".open_div").hide();
        $(".open_div2").show();
        $(this).toggleClass("active").prev().slideToggle("show");
        return false;
    });
    $(".open_div2").click(function() {
        $(".open_div").show();
        $(".open_div2").hide();
        $(this).toggleClass("active").prev().prev().prev().slideToggle("show");
        return false;
    });
});


// $( function() {
//     $( "#tabs" ).tabs();
//   } );