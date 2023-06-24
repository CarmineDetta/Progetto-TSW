/**
 * 
 */

function formValidation() {
    let date1Element = document.getElementById("date1");
    let date2Element = document.getElementById("date2");

    checkData(date1Element.value, date2Element.value);
}

function checkData(date1, date2) {
    let date1Value = Date.parse(date1);
    let date2Value = Date.parse(date2);

    if (date1Value > date2Value) {
        alert("Data inizio maggiore della Data di fine!");
        return false;
    } else {
        return true;
    }
}
