/**
 * 
 */
function validateCard() {
  var cardNumber = document.getElementById("n_carta").value;
  var cardType = getCardType(cardNumber);

  if (cardType === "Visa") {
    if (!validateVisa(cardNumber)) {
      alert("Not a valid Visa credit card number!");
      return false;
    }
  } else if (cardType === "Mastercard") {
    if (!validateMastercard(cardNumber)) {
      alert("Not a valid Mastercard number!");
      return false;
    }
  } else if (cardType === "JCB") {
    if (!validateJCB(cardNumber)) {
      alert("Not a valid JCB card number!");
      return false;
    }
  } else if (cardType === "Discover") {
    if (!validateDiscover(cardNumber)) {
      alert("Not a valid Discover card number!");
      return false;
    }
  } else if (cardType === "DinersClub") {
    if (!validateDinersClub(cardNumber)) {
      alert("Not a valid Diners Club card number!");
      return false;
    }
  } else if (cardType === "AmericanExpress") {
    if (!validateAmericanExpress(cardNumber)) {
      alert("Not a valid American Express credit card number!");
      return false;
    }
  } else {
    alert("Invalid card type!");
    return false;
  }

  return true; // La carta è valida
}

function getCardType(cardNumber) {
  // Esegui il controllo per determinare il tipo di carta
  // Restituisci "Visa", "Mastercard", "JCB", "Discover", "DinersClub", "AmericanExpress" o "Unknown"

  if (/^4[0-9]{12}(?:[0-9]{3})?$/.test(cardNumber)) {
    return "Visa";
  }

  if (/^5[1-5][0-9]{14}$/.test(cardNumber)) {
    return "Mastercard";
  }

  if (/^(?:(?:2131|1800|35\d{3})\d{11})$/.test(cardNumber)) {
    return "JCB";
  }

  if (/^(?:6(?:011|5[0-9][0-9])[0-9]{12})$/.test(cardNumber)) {
    return "Discover";
  }

  if (/^(?:3(?:0[0-5]|[68][0-9])[0-9]{11})$/.test(cardNumber)) {
    return "DinersClub";
  }

  if (/^(?:3[47][0-9]{13})$/.test(cardNumber)) {
    return "AmericanExpress";
  }

   return "Unknown";
}

function validateVisa(cardNumber) {
  var visaPattern = /^(?:4[0-9]{12}(?:[0-9]{3})?)$/;
  return visaPattern.test(cardNumber);
}

function validateMastercard(cardNumber) {
  var mastercardPattern = /^(?:5[1-5][0-9]{14})$/;
  return mastercardPattern.test(cardNumber);
}

function validateJCB(cardNumber) {
  var jcbPattern = /^(?:(?:2131|1800|35\d{3})\d{11})$/;
  return jcbPattern.test(cardNumber);
}

function validateDiscover(cardNumber) {
  var discoverPattern = /^(?:6(?:011|5[0-9][0-9])[0-9]{12})$/;
  return discoverPattern.test(cardNumber);
}

function validateDinersClub(cardNumber) {
  var dinersClubPattern = /^(?:3(?:0[0-5]|[68][0-9])[0-9]{11})$/;
  return dinersClubPattern.test(cardNumber);
}

function validateAmericanExpress(cardNumber) {
  var americanExpressPattern = /^(?:3[47][0-9]{13})$/;
  return americanExpressPattern.test(cardNumber);
}
