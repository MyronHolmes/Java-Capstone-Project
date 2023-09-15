console.log(document.cookie)

const propertyName = document.getElementById("propertyBuilding");
const buildingForm = document.getElementById("building-form");
const buildingNumber = document.getElementById("building-number");
const buildingContainer = document.getElementById("building-container");
let updateBodyBtn= document.getElementById("save-button");
let editForm= document.getElementById("edit-building-form");
let editBuildingNumber = document.getElementById("edit-building-number");



const userId = getCookie("userId");
const propertyId = getCookie("propertyId");
propertyName.innerText = getCookie("propertyName")+"'s Buildings:";

const headers = {
    'Content-Type':'application/json'
};
const baseUrl = 'http://localhost:8080/buildings/'

const addBuilding = async (e)=>{
    e.preventDefault();
    bodyObj = {
        buildingNumber: buildingNumber.value
    }

    if (bodyObj.buildingNumber.length === 0){
        alert("Invalid entry")
    }else {
        const response= await fetch(`${baseUrl}property/${propertyId}`, {
            method:"POST",
            body: JSON.stringify(bodyObj),
            headers: headers
        })
            .catch(err=> console.error(err.message()))
        if (response.status === 200){
            buildingNumber.value = '';
            return getBuildings(propertyId)
        }else {
        }
    }
};


const getBuildings = async (propertyId)=>{

    await fetch(`${baseUrl}property/${propertyId}`, {
        method: "GET",
        headers: headers
    })
        .then(response => response.json())
        .then(data => createCard(data))
        .catch((err=> console.error(err)))
};

const handleBuildingDelete= async (buildingId)=>{

    let bodyObj ={
        id: buildingId,
    }
    await fetch(baseUrl + buildingId, {
        method: "DELETE",
        body: JSON.stringify(bodyObj),
        headers: headers
    })
        .catch(err => console.error(err))

    return getBuildings(propertyId)
}

const handleBuildingEdit= async (buildingId)=>{


    let bodyObj ={
        id: buildingId,
        buildingNumber: editBuildingNumber.value
    }
    await fetch(baseUrl + buildingId, {
        method: "PUT",
        body: JSON.stringify(bodyObj),
        headers: headers
    })
        .catch(err => console.error(err))

    return getBuildings(propertyId)
}

const getBuildingById = async (buildingId)=>{
    await fetch(baseUrl + buildingId, {
        method:"GET",
        headers:headers
    })
        .then(res => res.json())
        .then(data => populateModal(data))
        .catch(err => console.error(err.message))
};

const populateModal = (obj)=>{

    editBuildingNumber.value= ""
    editBuildingNumber.value= obj.buildingNumber
    editBuildingNumber.setAttribute("placeholder", "" )
    editBuildingNumber.setAttribute("placeholder", `${obj.buildingNumber}` )


    updateBodyBtn.setAttribute('data-building-id', obj.id)
};


const createCard= (arr) =>{
    buildingContainer.innerHTML=''
    arr.forEach(obj => {
        let buildingCard = document.createElement("div");
        buildingCard.classList.add("m-2");
        buildingCard.innerHTML= `
            <div class="card d-flex" style="width: 18rem; height: 18rem;">
                <div class="card-body d-flex flex-column justify-content-between" style="height: available">
                    <h2 class="card-text link-primary display-1 d-flex flex-row justify-content-center" id="card-${obj.id}" onclick="handleClick(${obj.id})">${obj.buildingNumber}</h2>
                    <div class="d-flex justify-content-between">
                    <!-- Button trigger modal -->
                        <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#staticBackdrop" onclick="getBuildingById(${obj.id})">
                        EDIT
                        </button>
                        <button type="button" class="btn btn-danger" onclick="handleBuildingDelete(${obj.id})">
                        DELETE
                        </button>
                    </div>
                </div>
            </div>
`
        editBuildingNumber.innerText= obj.buildingNumber
        buildingContainer.appendChild(buildingCard);
    })
};

const handleClick = async (buildingId)=>{
    const response = await fetch(`${baseUrl}${buildingId}/units`, {
        method: "GET",
        headers: headers
    })
        .catch(err=> console.error(err.message))
    const responseAry= await response.json()
    if (response.status === 200){
        if (responseAry.length < 2){
        }else{

            document.cookie= `buildingId=${responseAry[1]}`
            document.cookie= `buildingNumber=${responseAry[2]}`
            window.location.replace(responseAry[0]);
        }
    }
}







function getCookie(name){
    const cDecoded = decodeURIComponent(document.cookie);
    const cArr =cDecoded.split('; ');
    let result = null;

    cArr.forEach(ele =>{
        if (ele.indexOf(name) === 0){
            result = ele.substring(name.length + 1)
        }
    })
    return result;
}


getBuildings(propertyId);

buildingForm.addEventListener("submit", addBuilding);
updateBodyBtn.addEventListener("click", (e)=>{
    let buildingId = e.target.getAttribute("data-building-id")
    handleBuildingEdit(buildingId)
})