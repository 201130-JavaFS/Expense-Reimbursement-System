
let modal;
let span;
let button;

window.addEventListener('load', function() {
  modal = document.getElementById("myModal");
  span = document.getElementsByClassName("close")[0];
  button = document.getElementById("filterStatus");

  button.onclick = function() {
    modal.style.display = "block";
  }

  span.onclick = function() {
    modal.style.display = "none";
  }
  
  window.onclick = function(event) {
    if (event.target == modal) {
      modal.style.display = "none";
    }
  }

});