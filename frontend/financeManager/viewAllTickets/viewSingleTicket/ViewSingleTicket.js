
window.addEventListener('load', function() {
  let ticketView = document.getElementsByClassName("ticketView")[0];
  ticketView.style.height = '250px';

  let floatAmount = parseFloat(sessionStorage.getItem("amount"));
  let newResolved = sessionStorage.getItem("resolved");
  if (newResolved == "null") newResolved = null;
  else removeButtons();
  let newResolver = sessionStorage.getItem("resolver");
  if (newResolver == "null") newResolver = null;
  let newReceipt = sessionStorage.getItem("receipt");
  if (newReceipt == "null") newReceipt = null;
  let ticket = {
    status: sessionStorage.getItem("status"),
    id: sessionStorage.getItem("id"),
    amount: floatAmount,
    type: sessionStorage.getItem("type"),
    submitted: sessionStorage.getItem("submitted"),
    resolved: newResolved,
    resolver: newResolver,
    description: sessionStorage.getItem("description"),
    receipt: newReceipt
  }
  loadTicket(ticket);
});

function removeButtons() {
  let resolveButtonsDiv = document.getElementById("resolveButtons");
  resolveButtonsDiv.style.display = "none";
  let ticketView = document.getElementsByClassName("ticketView")[0];
  ticketView.style.height = '220px';
}

function loadTicket(ticket) {
  let row = document.createElement("tr");

  // status
  let cell1 = document.createElement("td");
  cell1.innerHTML = ticket.status;
  row.appendChild(cell1);

  // id
  let cell2 = document.createElement("td");
  cell2.innerHTML = ticket.id;
  row.appendChild(cell2);

  // amount
  let cell3 = document.createElement("td");
  cell3.innerHTML = "$" + ticket.amount.toFixed(2);
  row.appendChild(cell3);

  // type
  let cell4 = document.createElement("td");
  cell4.innerHTML = ticket.type;
  row.appendChild(cell4);

  // submitted
  let cell5 = document.createElement("td");
  cell5.innerHTML = ticket.submitted;
  row.appendChild(cell5);

  // resolved
  if (ticket.resolved != null) {
    let cell6 = document.createElement("td");
    cell6.innerHTML = ticket.resolved;
    row.appendChild(cell6);
  } else {
    let cell6 = document.createElement("td");
    cell6.innerHTML = "";
    row.appendChild(cell6);
  }

  // resolver
  if (ticket.resolver != null) {
    let cell7 = document.createElement("td");
    cell7.innerHTML = ticket.resolver;
    row.appendChild(cell7);
  } else {
    let cell7 = document.createElement("td");
    cell7.innerHTML = "";
    row.appendChild(cell7);
  }

  // description
  let cell8 = document.createElement("td");
  let descriptionButton = document.createElement('input');
  descriptionButton.type = "button";
  descriptionButton.setAttribute('id', `btn0`);
  descriptionButton.value = 'details';
  descriptionButton.addEventListener('click', function () {
    var popup = open("", "Popup", "width=300,height=200");
    var desc = popup.document.createElement("p");
    desc.innerHTML = ticket.description;

    popup.document.body.appendChild(desc);
  });
  cell8.appendChild(descriptionButton);
  row.appendChild(cell8);

  // receipt
  if (ticket.receipt != null) {
    let cell9 = document.createElement("td");
    cell9.innerHTML = ticket.receipt;
    row.appendChild(cell9);
  } else {
    let cell9 = document.createElement("td");
    cell9.innerHTML = "";
    row.appendChild(cell9);
  }
  document.getElementById("financeManagerTicketBody").appendChild(row);
}
