import navbar from "../components/navbar.js";

document.querySelector("#navbar").innerHTML = navbar();

// https://task-planner-backend-production.up.railway.app
const commonUrl = "http://localhost:8080";

async function getSprintData() {
  let response = await fetch(commonUrl + "/sprints/all");
  if (!response.ok) {
    throw new Error("Network response was not ok");
  }
  let data = await response.json();
  // console.log("GET getSprintData request successful:", data);
  return data;
}

async function getTasksBySprintId(sprintId) {
  let response = await fetch(commonUrl + `/sprints/tasks/${sprintId}`);
  if (!response.ok) {
    throw new Error("Network response was not ok");
  } else {
    let data = await response.json();
    // console.log("GET getTasksBySprintId request successful:", data);
    return data;
  }
}

async function main() {
  try {
    let arrayOfSprints = await getSprintData();
    sprintData(arrayOfSprints);
    // console.log("arrayOfSprints: ", arrayOfSprints);
  } catch (error) {
    console.error("Error occurred during GET request:", error);
  }
}

main();

function sprintData(arrayOfSprints) {
  const sprintList = document.getElementById("sprintList");

  arrayOfSprints.forEach((sprint) => {
    const sprintElement = document.createElement("div");
    sprintElement.classList.add("sprint");

    const sprintIdElement = document.createElement("div");
    sprintIdElement.classList.add("sprint-id");
    sprintIdElement.textContent = `Sprint ID: ${sprint.sprintId}`;

    const nameElement = document.createElement("div");
    nameElement.classList.add("sprint-name");
    nameElement.textContent = `Sprint Name: ${sprint.name}`;

    const startDateElement = document.createElement("div");
    startDateElement.classList.add("sprint-start-date");
    startDateElement.textContent = `Start Date: ${sprint.startDate}`;

    const endDateElement = document.createElement("div");
    endDateElement.classList.add("sprint-end-date");
    endDateElement.textContent = `End Date: ${sprint.endDate}`;

    sprintElement.appendChild(sprintIdElement);
    sprintElement.appendChild(nameElement);
    sprintElement.appendChild(startDateElement);
    sprintElement.appendChild(endDateElement);

    sprintList.appendChild(sprintElement);
  });

  arrayOfSprints.forEach(({ sprintId, name, startDate, endDate }) => {
    main1(sprintId);
  });
}

async function main1(sprintId) {
  try {
    let arraysOfTaskPerSprint = await getTasksBySprintId(sprintId);
    // console.log("arraysOfTaskPerSprint:", arraysOfTaskPerSprint);
    arraysOfTaskPerSprint.forEach(
      ({ taskId, type, description, status, priority, comment }) => {
        console.log(`Task ID: ${taskId}`);
        console.log(`Type: ${type}`);
        console.log(`Description: ${description}`);
        console.log(`Status: ${status}`);
        console.log(`Priority: ${priority}`);
        console.log(`Comment: ${comment}`);
      }
    );
  } catch (error) {
    console.error("Error occurred during GET request:", error);
  }
}
