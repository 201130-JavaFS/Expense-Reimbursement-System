
let data;
let singleTicketId;
let ticketArray = [];

window.addEventListener('load', function() {
  getAllTickets();
  document.getElementById("exit").addEventListener('click', backToDashboard);
  document.getElementById("byAll").addEventListener('click', getAllTicketsAndCloseModal);
  document.getElementById("byPending").addEventListener('click', getPendingTickets);
  document.getElementById("byApproved").addEventListener('click', getApprovedTickets);
  document.getElementById("byDenied").addEventListener('click', getDeniedTickets);
});

function backToDashboard() {
  window.location.replace("../FinanceManager.html");
}

function getFilteredTickets(chosenStatus) {
  let tableBody = document.getElementById("financeManagerTicketBody");
  while (tableBody.firstChild) {
    tableBody.removeChild(tableBody.firstChild);
  }
  
  let index = 0;
  for (let ticket of data) {
    if (ticket.status == chosenStatus) {
      let row = document.createElement("tr");
      row.setAttribute("id", `${ticket.id}`);
      row.addEventListener('click', singleTicket);

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
    let height = ((index + 1) * 40) + 150;
    ticketView.style.height = `${height}px`;
  }
}

function findValue(keyValue, ticketArray) {
  for (let i = 0; i < ticketArray.length; i++) {
    if (ticketArray[i].key == keyValue)
      return ticketArray[i];
  }
}

function getAllTicketsAndCloseModal() {
  closeModal();
  getAllTickets();
}

function getPendingTickets() {
  closeModal();
  getFilteredTickets("PENDING");
}

function getApprovedTickets() {
  closeModal();
  getFilteredTickets("APPROVED");
}

function getDeniedTickets() {
  closeModal();
  getFilteredTickets("DENIED");
}

function closeModal() {
  let modal = document.getElementById("myModal");
  modal.style.display = "none";
}

function singleTicket(event) {
  event.stopPropagation();

  let rowId = this.id;
  singleTicketId = rowId;
  let tableBody = document.getElementById("financeManagerTicketBody");
  while (tableBody.firstChild) {
    tableBody.removeChild(tableBody.firstChild);
  }
  let ticketView = document.getElementsByClassName("ticketView")[0];
  ticketView.style.height = '250px';

  let ticket = findValue(rowId, ticketArray);
  if (ticket.value.status == "PENDING") {
    createButtons();
  }

  getOneTicket(ticket.value);
}

function createButtons() {
  let resolveButtonsDiv = document.getElementById("resolveButtons");

  let approveButton = document.createElement("button");
  approveButton.setAttribute("id", "Approve");
  approveButton.innerHTML = "Approve";
  approveButton.addEventListener('click', approve);
  resolveButtonsDiv.appendChild(approveButton);

  let denyButton = document.createElement("button");
  denyButton.setAttribute("id", "Deny");
  denyButton.innerHTML = "Deny";
  denyButton.addEventListener('click', deny);
  resolveButtonsDiv.appendChild(denyButton);
}

function getOneTicket(ticket) {
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

async function getAllTickets() {
  let tableBody = document.getElementById("financeManagerTicketBody");
  while (tableBody.firstChild) {
    tableBody.removeChild(tableBody.firstChild);
  }
  let response = await fetch(url+'alltickets', {credentials: "include"});

  if (response.status === 200) {
    data = await response.json();
    let index = 0;

    for (let ticket of data) {
      let row = document.createElement("tr");
      row.setAttribute("id", `${ticket.id}`);
      row.addEventListener('click', singleTicket);

      let obj = {
        key: ticket.id,
        value: ticket
      }
      ticketArray.push(obj);

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
    } // end for loop
    let ticketView = document.getElementsByClassName("ticketView")[0];
    let height = ((index + 1) * 40) + 150;
    ticketView.style.height = `${height}px`;
  }
}

async function approve() {
  let id = {
    id: singleTicketId
  }

  let response = await fetch(url+'approveticket', {
    method: "POST",
    body: JSON.stringify(id),
    credentials: "include"
  });

  if (response.status === 200) {
    window.location.replace("../TicketResolved.html");
  }
}

async function deny() {
  let id = {
    id: singleTicketId
  }

  let response = await fetch(url+'denyticket', {
    method: "POST",
    body: JSON.stringify(id),
    credentials: "include"
  });

  if (response.status === 200) {
    window.location.replace("../TicketResolved.html");
  }
}