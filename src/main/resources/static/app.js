const API_BASE = "";

let editingAdviserId = null;
let editingStudyId = null;
let editingSupervisionId = null;

document.addEventListener("DOMContentLoaded", () => {
    loadAdvisers();
    loadStudies();
    loadSupervisions();
});

// ---------------- ADVISERS ----------------

async function loadAdvisers() {
    const response = await fetch(`${API_BASE}/api/advisers`);
    const advisers = await response.json();

    const table = document.getElementById("adviserTable");
    table.innerHTML = "";

    advisers.forEach(adviser => {
        table.innerHTML += `
            <tr>
                <td>${adviser.id}</td>
                <td>${adviser.name}</td>
                <td>${adviser.department}</td>
                <td>
                    <button class="edit" onclick="editAdviser(${adviser.id}, '${escapeText(adviser.name)}', '${escapeText(adviser.department)}')">Edit</button>
                    <button class="delete" onclick="deleteAdviser(${adviser.id})">Delete</button>
                </td>
            </tr>
        `;
    });
}

async function submitAdviser() {
    if (editingAdviserId === null) {
        await createAdviser();
    } else {
        await updateAdviser();
    }
}

async function createAdviser() {
    const name = document.getElementById("adviserName").value.trim();
    const department = document.getElementById("adviserDepartment").value.trim();

    if (!name || !department) {
        alert("Please enter adviser name and department.");
        return;
    }

    const response = await fetch(`${API_BASE}/api/advisers`, {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify({ name, department })
    });

    if (!response.ok) {
        alert(await response.text());
        return;
    }

    clearAdviserForm();
    await loadAdvisers();
}

function editAdviser(id, name, department) {
    editingAdviserId = id;

    document.getElementById("adviserName").value = name;
    document.getElementById("adviserDepartment").value = department;
    document.getElementById("adviserSubmitButton").innerText = "Update Adviser";
}

async function updateAdviser() {
    const name = document.getElementById("adviserName").value.trim();
    const department = document.getElementById("adviserDepartment").value.trim();

    if (!name || !department) {
        alert("Please enter adviser name and department.");
        return;
    }

    const response = await fetch(`${API_BASE}/api/advisers/${editingAdviserId}`, {
        method: "PUT",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify({ name, department })
    });

    if (!response.ok) {
        alert(await response.text());
        return;
    }

    clearAdviserForm();
    await loadAdvisers();
    await loadSupervisions();
}

async function deleteAdviser(id) {
    if (!confirm("Are you sure you want to delete this adviser?")) {
        return;
    }

    const response = await fetch(`${API_BASE}/api/advisers/${id}`, {
        method: "DELETE"
    });

    if (!response.ok) {
        alert("Adviser could not be deleted. Delete related supervisions first.");
        return;
    }

    await loadAdvisers();
    await loadSupervisions();
}

function cancelAdviserEdit() {
    clearAdviserForm();
}

function clearAdviserForm() {
    editingAdviserId = null;
    document.getElementById("adviserName").value = "";
    document.getElementById("adviserDepartment").value = "";
    document.getElementById("adviserSubmitButton").innerText = "Add Adviser";
}

// ---------------- STUDIES ----------------

async function loadStudies() {
    const response = await fetch(`${API_BASE}/api/studies`);
    const studies = await response.json();

    const table = document.getElementById("studyTable");
    table.innerHTML = "";

    studies.forEach(study => {
        table.innerHTML += `
            <tr>
                <td>${study.id}</td>
                <td>${study.title}</td>
                <td>${study.description}</td>
                <td>
                    <button class="edit" onclick="editStudy(${study.id}, '${escapeText(study.title)}', '${escapeText(study.description)}')">Edit</button>
                    <button class="delete" onclick="deleteStudy(${study.id})">Delete</button>
                </td>
            </tr>
        `;
    });
}

async function submitStudy() {
    if (editingStudyId === null) {
        await createStudy();
    } else {
        await updateStudy();
    }
}

async function createStudy() {
    const title = document.getElementById("studyTitle").value.trim();
    const description = document.getElementById("studyDescription").value.trim();

    if (!title || !description) {
        alert("Please enter study title and description.");
        return;
    }

    const response = await fetch(`${API_BASE}/api/studies`, {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify({ title, description })
    });

    if (!response.ok) {
        alert(await response.text());
        return;
    }

    clearStudyForm();
    await loadStudies();
}

function editStudy(id, title, description) {
    editingStudyId = id;

    document.getElementById("studyTitle").value = title;
    document.getElementById("studyDescription").value = description;
    document.getElementById("studySubmitButton").innerText = "Update Study";
}

async function updateStudy() {
    const title = document.getElementById("studyTitle").value.trim();
    const description = document.getElementById("studyDescription").value.trim();

    if (!title || !description) {
        alert("Please enter study title and description.");
        return;
    }

    const response = await fetch(`${API_BASE}/api/studies/${editingStudyId}`, {
        method: "PUT",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify({ title, description })
    });

    if (!response.ok) {
        alert(await response.text());
        return;
    }

    clearStudyForm();
    await loadStudies();
    await loadSupervisions();
}

async function deleteStudy(id) {
    if (!confirm("Are you sure you want to delete this study?")) {
        return;
    }

    const response = await fetch(`${API_BASE}/api/studies/${id}`, {
        method: "DELETE"
    });

    if (!response.ok) {
        alert("Study could not be deleted. Delete related supervisions first.");
        return;
    }

    await loadStudies();
    await loadSupervisions();
}

function cancelStudyEdit() {
    clearStudyForm();
}

function clearStudyForm() {
    editingStudyId = null;
    document.getElementById("studyTitle").value = "";
    document.getElementById("studyDescription").value = "";
    document.getElementById("studySubmitButton").innerText = "Add Study";
}

// ---------------- SUPERVISIONS ----------------

async function loadSupervisions() {
    const response = await fetch(`${API_BASE}/api/supervisions`);
    const supervisions = await response.json();

    const table = document.getElementById("supervisionTable");
    table.innerHTML = "";

    supervisions.forEach(supervision => {
        table.innerHTML += `
            <tr>
                <td>${supervision.id}</td>
                <td>${supervision.adviser ? supervision.adviser.name : "-"}</td>
                <td>${supervision.study ? supervision.study.title : "-"}</td>
                <td>${supervision.student}</td>
                <td>${supervision.performance}</td>
                <td>
                    <button class="edit" onclick="editSupervision(
                        ${supervision.id},
                        ${supervision.adviser ? supervision.adviser.id : null},
                        ${supervision.study ? supervision.study.id : null},
                        '${escapeText(supervision.student)}',
                        '${escapeText(supervision.performance)}'
                    )">Edit</button>
                    <button class="delete" onclick="deleteSupervision(${supervision.id})">Delete</button>
                </td>
            </tr>
        `;
    });
}

async function submitSupervision() {
    if (editingSupervisionId === null) {
        await createSupervision();
    } else {
        await updateSupervision();
    }
}

async function createSupervision() {
    const adviserId = Number(document.getElementById("supervisionAdviserId").value);
    const studyId = Number(document.getElementById("supervisionStudyId").value);
    const student = document.getElementById("supervisionStudent").value.trim();
    const performance = document.getElementById("supervisionPerformance").value.trim();

    if (!adviserId || !studyId || !student || !performance) {
        alert("Please fill all supervision fields.");
        return;
    }

    const response = await fetch(`${API_BASE}/api/supervisions`, {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify({
            adviserId,
            studyId,
            student,
            performance
        })
    });

    if (!response.ok) {
        alert(await response.text());
        return;
    }

    clearSupervisionForm();
    await loadSupervisions();
}

function editSupervision(id, adviserId, studyId, student, performance) {
    editingSupervisionId = id;

    document.getElementById("supervisionAdviserId").value = adviserId;
    document.getElementById("supervisionStudyId").value = studyId;
    document.getElementById("supervisionStudent").value = student;
    document.getElementById("supervisionPerformance").value = performance;
    document.getElementById("supervisionSubmitButton").innerText = "Update Supervision";
}

async function updateSupervision() {
    const adviserId = Number(document.getElementById("supervisionAdviserId").value);
    const studyId = Number(document.getElementById("supervisionStudyId").value);
    const student = document.getElementById("supervisionStudent").value.trim();
    const performance = document.getElementById("supervisionPerformance").value.trim();

    if (!adviserId || !studyId || !student || !performance) {
        alert("Please fill all supervision fields.");
        return;
    }

    const response = await fetch(`${API_BASE}/api/supervisions/${editingSupervisionId}`, {
        method: "PUT",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify({
            adviserId,
            studyId,
            student,
            performance
        })
    });

    if (!response.ok) {
        alert(await response.text());
        return;
    }

    clearSupervisionForm();
    await loadSupervisions();
}

async function deleteSupervision(id) {
    if (!confirm("Are you sure you want to delete this supervision?")) {
        return;
    }

    const response = await fetch(`${API_BASE}/api/supervisions/${id}`, {
        method: "DELETE"
    });

    if (!response.ok) {
        alert(await response.text());
        return;
    }

    await loadSupervisions();
}

function cancelSupervisionEdit() {
    clearSupervisionForm();
}

function clearSupervisionForm() {
    editingSupervisionId = null;
    document.getElementById("supervisionAdviserId").value = "";
    document.getElementById("supervisionStudyId").value = "";
    document.getElementById("supervisionStudent").value = "";
    document.getElementById("supervisionPerformance").value = "";
    document.getElementById("supervisionSubmitButton").innerText = "Add Supervision";
}

// ---------------- HELPER ----------------

function escapeText(value) {
    if (value === null || value === undefined) {
        return "";
    }

    return String(value)
        .replaceAll("\\", "\\\\")
        .replaceAll("'", "\\'")
        .replaceAll('"', "&quot;");
}