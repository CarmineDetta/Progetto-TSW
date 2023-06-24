/**
 * 
 */
function formValidation() {
  event.preventDefault(); // Impedisce l'invio del modulo predefinito
  
  let stringaParziale = document.getElementById("productId").value;
  let qty = document.getElementById("numeri").value;
  
  console.log(stringaParziale);
  console.log(qty);
  console.log("sono nella funzione");

  $.ajax({
    async: true,
    url: "AjaxCart",
    method: "POST",
    data: {
      stringaRicerca: stringaParziale,
      qty: qty
    },
    dataType: "json",
    success: function(data, textStatus, jqXHR) {
      // Gestisci la risposta del server qui
    }
  });
}
