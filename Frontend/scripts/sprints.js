import navbar from "../components/navbar.js";

document.querySelector("#navbar").innerHTML = navbar();

// https://task-planner-backend-production.up.railway.app
const commonUrl = "https://task-planner-backend-production.up.railway.app";

// To get the sprint object after creating sprint.
const sprintForm = document.querySelector("#sprint-form");

const submitSprint = async (event) => {
  event.preventDefault();

  let formData = new FormData(event.target);

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
