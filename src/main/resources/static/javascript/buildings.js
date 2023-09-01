console.log(document.cookie)

const propertyName = document.getElementById("propertyBuilding");
const buildingForm = document.getElementById("building-form");
const buildingNumber = document.getElementById("building-number");
const buildingContainer = document.getElementById("building-container");
let buildingBody =document.getElementById("building-body");
let updateBodyBtn= document.getElementById("save-button")


const userId = getCookie("userId");
const propertyId = getCookie("propertyId");
console.log(getCookie("propertyId"))
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
    console.log(buildingNumber.value)
    console.log("property" + propertyId)

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
        console.log("not hit")
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

const getBuildingById = async (buildingId)=>{
    await fetch(baseUrl + buildingId, {
        method:"GET",
        headers:headers
    })
        .then(res => res.json())
        .then(data => populateModal(data))
        .catch(err => console.error(err.message))
};

const handleBuildingEdit= async (buildingId)=>{
    let bodyObj ={
        id: bodyId,
        body: buildingBody.value
    }
    await fetch(baseUrl, {
        method: "PUT",
        body: JSON.stringify(bodyObj),
        headers: headers
    })
        .catch(err => console.error(err))

    return getBuildings(propertyId)
}

const createCard= (arr) =>{
    buildingContainer.innerHTML=''
    arr.forEach(obj => {
        let buildingCard = document.createElement("div");
        buildingCard.classList.add("m-2");
        buildingCard.innerHTML= `
            <div class="card d-flex" style="width: 18rem; height: 18rem;">
                <div class="card-body d-flex flex-column justify-content-between" style="height: available">
                    <p class="card-text">${obj.buildingNumber}</p>
                    <div class="d-flex justify-content-between">
                    <!-- Button trigger modal -->
                        <button type="button" class="btn btn-primary" id="edit-btn-${obj.id}" data-bs-toggle="modal" data-bs-target="#staticBackdrop" onclick="getBuildingById(${obj.id})">
                        EDIT
                        </button>
                        <button>DELETE</button>
                    </div>
                </div>
            </div>
`


        buildingContainer.appendChild(buildingCard);
        document.body.appendChild(buildingContainer)
        let editBtn = document.getElementById(`edit-btn-${obj.id}`)

    })
};

const populateModal = (obj)=>{
    console.log(obj.buildingNumber)
    buildingBody.innerText=''
    buildingBody.innerText= obj.buildingNumber
    updateBodyBtn.setAttribute('data-building-id', obj.id)
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