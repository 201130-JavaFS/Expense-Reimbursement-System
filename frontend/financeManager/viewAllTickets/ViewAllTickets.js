
let ticketArray = [];
let data;

window.addEventListener('load', function() {
  getAllTickets();
});

function singleTicket(event) {
  event.stopPropagation();
  let rowId = this.id;

  let userName = null;
  let currentTicket = ticketArray[rowId];
  if (currentTicket.resolver != null)
  userName = currentTicket.resolver.userName;
  sessionStorage.setItem("status", currentTicket.status);
  sessionStorage.setItem("id", currentTicket.id);
  sessionStorage.setItem("amount", currentTicket.amount);
  sessionStorage.setItem("type", currentTicket.type);
  sessionStorage.setItem("submitted", currentTicket.submitted);
  sessionStorage.setItem("resolved", currentTicket.resolved);
  sessionStorage.setItem("resolver", userName);
  sessionStorage.setItem("description", currentTicket.description);
  sessionStorage.setItem("receipt", currentTicket.receipt);

  console.log(data);
  window.location.replace("viewSingleTicket/ViewSingleTicket.html");
}

function test() {
  console.log(data);
}

async function getAllTickets() {
  let response = await fetch(url+'alltickets', {credentials: "include"});

  if (response.status === 200) {
    data = await response.json();
    let index = 0;

    for (let ticket of data) {
      let row = document.createElement("tr");
      row.setAttribute("id", `${index}`);
      row.addEventListener('click', singleTicket);
      ticketArray.push(ticket);

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
        cell7.innerHTML = ticket.resolver.userName;
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
      descriptionButton.setAttribute('id', `btn${index}`);
      descriptionButton.value = 'details';
      descriptionButton.addEventListener('click', function (event) {
        event.stopPropagation();
        var popup = open("", "Popup", "width=300,height=200");
        var desc = popup.document.createElement("p");
        desc.innerHTML = ticket.description;

        popup.document.body.appendChild(desc);
      });
      cell8.appendChild(descriptionButton);
      row.appendChild(cell8);
      index++;

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
    let ticketView = document.getElementsByClassName("ticketView")[0];
    let height = ((index + 1) * 40) + 120;
    ticketView.style.height = `${height}px`;
  }
}
