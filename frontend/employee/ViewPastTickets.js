
const url = 'http://localhost:8080/project1/';

window.onload = function() {
  getEmployeeTickets();
}

async function getEmployeeTickets() {
  let inputUsername = sessionStorage.getItem('inputUsername');
  let user = {
    username: inputUsername
  };

  let response = await fetch(url+'employeetickets', {
    method: "POST",
    body: JSON.stringify(user),
    credentials: "include"
  });

  if (response.status === 200) {
    let data = await response.json();

    for (let ticket of data) {
      let row = document.createElement("tr");

      let cell1 = document.createElement("td");
      cell1.innerHTML = ticket.status;
      row.appendChild(cell1);

      let cell2 = document.createElement("td");
      cell2.innerHTML = ticket.id;
      row.appendChild(cell2);

      let cell3 = document.createElement("td");
      cell3.innerHTML = "$" + ticket.amount;
      row.appendChild(cell3);

      let cell4 = document.createElement("td");
      cell4.innerHTML = ticket.type;
      row.appendChild(cell4);

      let cell5 = document.createElement("td");
      cell5.innerHTML = ticket.submitted;
      row.appendChild(cell5);

      if (ticket.resolved != null) {
        let cell6 = document.createElement("td");
        cell6.innerHTML = ticket.submitted;
        row.appendChild(cell6);
      } else {
        let cell6 = document.createElement("td");
        cell6.innerHTML = "";
        row.appendChild(cell6);
      }

      if (ticket.resolver != null) {
        let cell7 = document.createElement("td");
        cell7.innerHTML = ticket.resolver;
        row.appendChild(cell7);
      } else {
        let cell7 = document.createElement("td");
        cell7.innerHTML = "";
        row.appendChild(cell7);
      }

      let cell8 = document.createElement("td");
      cell8.innerHTML = ticket.description;
      row.appendChild(cell8);

      if (ticket.receipt != null) {
        let cell9 = document.createElement("td");
        cell9.innerHTML = ticket.receipt;
        row.appendChild(cell9);
      } else {
        let cell9 = document.createElement("td");
        cell9.innerHTML = "";
        row.appendChild(cell9);
      }
      document.getElementById("employeeTicketBody").appendChild(row);
    }
  }
}