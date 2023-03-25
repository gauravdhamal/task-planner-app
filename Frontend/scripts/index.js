import navbar from "../components/navbar.js";

document.querySelector("#navbar").innerHTML = navbar();

const commonUrl = "https://task-planner-backend-production.up.railway.app";

let users = "https://task-planner-backend-production.up.railway.app/users/all";
let tasks = "https://task-planner-backend-production.up.railway.app/tasks/all";
let sprints =
  "https://task-planner-backend-production.up.railway.app/sprints/all";

// fetch(sprints)
//   .then((response) => response.json())
//   .then((data) => {
//     console.log("Data : ", data);
//   })
//   .catch((error) => {
//     console.error(error);
//   });

const sprintForm = document.querySelector("#sprint-form");

const submitSprint = async (event) => {
  event.preventDefault();

  const formData = new FormData(event.target);

  let name = formData.get("sprint-name");
  let startDate = formData.get("sprint-startdate");
  let endDate = formData.get("sprint-enddate");

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
    .then((sprintObject) => console.log(sprintObject))
    .catch((error) => console.error(error));
};

sprintForm.addEventListener("submit", submitSprint);
