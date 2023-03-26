import navbar from "../components/navbar.js";

document.querySelector("#navbar").innerHTML = navbar();

// https://task-planner-backend-production.up.railway.app
const commonUrl = "https://task-planner-backend-production.up.railway.app";

async function getSprintData() {
  let response = await fetch(commonUrl + "/sprints/all");
  if (!response.ok) {
    throw new Error("Network response was not ok");
  }
  let data = await response.json();
  // console.log("GET getSprintData request successful:", data);
  return data;
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

    const tasksElement = document.createElement("div");
    tasksElement.classList.add("sprint-tasks");
    sprint.tasks.forEach((task) => {
      const taskElement = document.createElement("div");
      taskElement.classList.add("task");

      const taskIdElement = document.createElement("div");
      taskIdElement.classList.add("task-id");
      taskIdElement.textContent = `Task ID: ${task.taskId}`;

      const typeElement = document.createElement("div");
      typeElement.classList.add("task-type");
      typeElement.textContent = `Type: ${task.type}`;

      const descriptionElement = document.createElement("div");
      descriptionElement.classList.add("task-description");
      descriptionElement.textContent = `Description: ${task.description}`;

      const statusElement = document.createElement("div");
      statusElement.classList.add("task-status");
      statusElement.textContent = `Status: ${task.status}`;

      const priorityElement = document.createElement("div");
      priorityElement.classList.add("task-priority");
      priorityElement.textContent = `Priority: ${task.priority}`;

      const commentElement = document.createElement("div");
      commentElement.classList.add("task-comment");
      commentElement.textContent = `Comment: ${task.comment}`;

      taskElement.appendChild(taskIdElement);
      taskElement.appendChild(typeElement);
      taskElement.appendChild(descriptionElement);
      taskElement.appendChild(statusElement);
      taskElement.appendChild(priorityElement);
      taskElement.appendChild(commentElement);

      tasksElement.appendChild(taskElement);
    });

    sprintElement.appendChild(sprintIdElement);
    sprintElement.appendChild(nameElement);
    sprintElement.appendChild(startDateElement);
    sprintElement.appendChild(endDateElement);
    sprintElement.appendChild(tasksElement);

    sprintList.appendChild(sprintElement);
  });
}
