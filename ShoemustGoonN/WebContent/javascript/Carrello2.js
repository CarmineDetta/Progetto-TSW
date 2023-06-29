/**
 * 
 */

function formAdd(id, qty) {
  event.preventDefault(); // Impedisce l'invio del modulo predefinito
  let button = document.getElementById("add").value;

	console.log(button);
	console.log(id);
	console.log(qty);
	console.log("sono nella funzione");
  
  $.ajax({
    async: true,
    url: "AjaxAddRemove",
    method: "POST",
    data: {
      stringaRicerca: id,
      button: button,
      qty: qty
    },
    dataType: "json",
    success: function (data, textStatus, jqXHR) {
      let d = data.d;
      let t = data.qty;

      let formattedValue = parseFloat(d).toFixed(2);
      $("#prezzo").text(formattedValue + "€");

      if (formattedValue.trim() !== "") {
        $(".euro").hide();
      } else {
        $(".euro").show();
      }
    }
  });
}


function formRemove(id, qty) {
	  event.preventDefault();
	  let button = document.getElementById("remove").value;

		console.log(button);
		console.log(id);
		console.log(qty);
		console.log("sono nella funzione");
	  
	  
	  $.ajax({
	    async: true,
	    url: "AjaxAddRemove",
	    method: "POST",
	    data: {
	          stringaRicerca: id,
	          button: button,
	          qty: qty
	    },
	    dataType: "json",
	    success: function (data, textStatus, jqXHR) {
	      let d = data.d;
	      let t = data.qty;

	      let formattedValue = parseFloat(d).toFixed(2);
	      $("#prezzo").text(formattedValue + "€");

	      if (formattedValue.trim() !== "") {
	        $(".euro").hide();
	      } else {
	        $(".euro").show();
	      }
	  }
	});
}