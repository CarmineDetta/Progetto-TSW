function formValidation() {
  var prezzo = document.getElementById("prezzo");
  var quantita = document.getElementById("quantita");

  if (price(prezzo) && allnumeric(quantita)) {
    return true; // Tutti i controlli di validazione sono superati
  } else {
    return false; // Almeno uno dei controlli di validazione ha fallito
  }
}

function price(inputtxt) {
  var numbers = /^(\d+|\d+\.\d+)$/;
  if (inputtxt.value.match(numbers)) {
    return true;
  } else {
    alert('Inserisci solo caratteri numerici');
    inputtxt.focus();
    return false;
  }
}

function allnumeric(inputtxt) {
  var numbers = /^[0-9]+$/;
  if (inputtxt.value.match(numbers)) {
    return true;
  } else {
    alert('Please input numeric characters only');
    inputtxt.focus();
    return false;
  }
}

