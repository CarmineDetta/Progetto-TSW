
//funzione per mettere l'effetto sull'immagine del catalogo in jquery


$(document).ready(function() {
  $(".product-item").hover(
    function() {
      $(this).css({
        "background-color": "#ff6347",
        "transition": "0.5s",
        "transform": "scale(1.01)",
        "box-shadow": "3px 6px 14px -2px rgba(45, 59, 85, 0.82)"
      });
    },
    function() {
      $(this).css({
        "background-color": "initial",
        "transition": "0.5s",
        "transform": "initial",
        "box-shadow": "initial"
      });
    }
  );
});