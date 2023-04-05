import navbar from "../components/navbar.js";

document.querySelector("#navbar").innerHTML = navbar();

// https://task-planner-backend-production.up.railway.app
const commonUrl = "http://localhost:8080";

let sprintFormButtonOpen = document.getElementById("sprintFormButtonOpen");
let sprintFormButtonClose = document.getElementById("sprintFormButtonClose");
let sprintFormPost = document.getElementById("sprintFormPost");
let postFormDiv = document.getElementById("postFormDiv");

sprintFormButtonOpen.addEventListener("click", () => {
  sprintFormButtonOpen.style.display = "none";
  sprintFormButtonClose.style.display = "block";
  sprintFormPost.style.display = "flex";
  sprintFormPost.style.flexDirection = "column";
  postFormDiv.style.display = "block";
});

sprintFormButtonClose.addEventListener("click", () => {
  sprintFormButtonOpen.style.display = "block";
  sprintFormButtonClose.style.display = "none";
  sprintFormPost.style.display = "none";
  postFormDiv.style.display = "none";
});

// To get the sprint object after creating sprint.
const createSprint = async (event) => {
  event.preventDefault();

  let formData = new FormData(event.target);

  let name = formData.get("sprintName");
  let startDate = formData.get("sprintStartdate");
  let endDate = formData.get("sprintEnddate");

  let sprintObject = {
    name,
    startDate,
    endDate,
  };

  sprintObject.name = name;
  sprintObject.startDate = startDate;
  sprintObject.endDate = endDate;

  const options = {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(sprintObject),
  };

  fetch(commonUrl + "/sprints/create", options)
    .then((response) => response.json())
    .then((sprintObject) => {
      console.log("sprintObject:", sprintObject);
      window.alert("Sprint created note sprintId : " + sprintObject.sprintId);
    })
    .catch((error) => console.error(error));
};

sprintFormPost.addEventListener("submit", createSprint);

async function getAllSprints() {
  let data = await fetch(commonUrl + `/sprints/all`);
}

let appendSprints = (arrayOfSprints) => {
  const sprintTableBody = document.querySelector("#sprintTable tbody");
  sprintTableBody.innerHTML = "";
  arrayOfSprints.forEach((sprint) => {
    const tr = document.createElement("tr");
    const tdSprintId = document.createElement("td");
    const tdName = document.createElement("td");
    const tdStartDate = document.createElement("td");
    const tdEndDate = document.createElement("td");

    tdSprintId.textContent = sprint.sprintId;
    tdName.textContent = sprint.name;
    tdStartDate.textContent = sprint.startDate;
    tdEndDate.textContent = sprint.endDate;

    tr.appendChild(tdSprintId);
    tr.appendChild(tdName);
    tr.appendChild(tdStartDate);
    tr.appendChild(tdEndDate);

    sprintTableBody.appendChild(tr);
  });
};
