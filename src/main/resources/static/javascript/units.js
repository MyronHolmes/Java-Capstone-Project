console.log(document.cookie)

const heading = document.getElementById("building-units");
const unitContainer= document.getElementById("unit-container");
const inputUnitNumber = document.getElementById("unit-number")
const inputRent = document.getElementById("rent");
const inputUnitType = document.getElementById("floor-plan")
const inputVacancy = document.getElementById("vacancy")
const unitForm = document.getElementById("unit-form")
let editUnitNumber = document.getElementById("edit-unit-number")
let editRent = document.getElementById("edit-rent")
let editUnitType= document.getElementById("edit-floor-plan")
let editVacancy = document.getElementById("edit-vacancy")
let updateBtn = document.getElementById("update-button")
let optionV = document.getElementById("option-vacancy")
let optionVM = document.getElementById("option-vacancy-m")
let optionU = document.getElementById("option-unit-type")
let optionUM = document.getElementById("option-unit-type-m")

let buildingNumber =getCookie("buildingNumber");
let buildingId = getCookie("buildingId");
heading.innerText = "Building " + buildingNumber + " Units:";

const headers = {
    'Content-Type':'application/json'
};
const baseUrl = 'http://localhost:8080/units/'

const getUnits = async(buildingId)=>{
    unitContainer.innerText = '';
    await fetch(`${baseUrl}building/${buildingId}`, {
        method: "GET",
        headers: headers
    })
        .then(response=> response.json())
        .then(data=> createCard(data))
        .catch(err=> console.error(err))
}

const getUnitById= async (unitId)=>{
    await fetch(baseUrl + unitId, {
        method:"GET",
        headers:headers
    })
        .then(res => res.json())
        .then(data => populateModal(data))
        .catch(err => console.error(err.message))
}

const addUnit = async (e) =>{
    e.preventDefault();

    let bodyObj ={
        unitNumber: inputUnitNumber.value,
        rent: inputRent.value,
        unitType: inputUnitType.value,
        vacancy: inputVacancy.value
    }

    if (bodyObj.unitNumber === ''){
        alert("Invalid Entry")
    } else if (bodyObj.rent === ''){
        alert("Invalid Entry")
    }else if (bodyObj.unitType === "select the unit type"){
        alert("Invalid Entry")
    }else if(bodyObj.vacancy === "select the units vacancy status"){
        alert("Invalid Entry")
    } else {
        const response = await fetch(`${baseUrl}building/${buildingId}`, {
            method: "POST",
            body: JSON.stringify(bodyObj),
            headers: headers
        })
            .catch(err=> console.error(err))
        if (response.status === 200){
            inputUnitNumber.value= ''
            inputRent.value= ''
            inputUnitType.value = optionU.value
            inputVacancy.value = optionV.value

            return getUnits(buildingId)
        }else{
        }
    }

}

const populateModal = (obj)=>{

    editUnitNumber.value= ""
    editUnitNumber.value= obj.unitNumber
    editRent.value=""
    editRent.value = obj.rent
    editUnitType.value = optionUM
    editUnitType.value = obj.unitType
    editVacancy.value= optionVM
    editVacancy.value = obj.vacancy

    updateBtn.setAttribute('data-unit-id', obj.id)
};

const handleUnitEdit = async (unitId)=>{
    let bodyObj ={
        id: unitId,
        unitNumber: editUnitNumber.value,
        rent: editRent.value,
        unitType: editUnitType.value,
        vacancy: editVacancy.value
    }
    editUnitNumber.value =''
    editRent.value = ''

    await fetch(baseUrl + unitId, {
        method: "PUT",
        body: JSON.stringify(bodyObj),
        headers: headers
    })
        .catch(err => console.error(err))

    return getUnits(buildingId)
}

const handleUnitDelete = async (unitId)=>{
    let bodyObj ={
        id: unitId
    }
    await fetch(baseUrl + unitId, {
        method: "DELETE",
        body: JSON.stringify(bodyObj),
        headers: headers
    })
        .catch(err=> console.error(err))
    return getUnits(buildingId)
}

const createCard = (arr)=>{
    unitContainer.innerHTML=''
    arr.forEach(obj => {
        if (obj.unitType === "STUDIO") {
            obj.unitType = "Studio"
        } if (obj.unitType === "ONE_BED_ONE_BATH") {
            obj.unitType = "Beds: 1, Baths: 1"
        } if (obj.unitType === "TWO_BED_ONE_BATH") {
            obj.unitType = "Beds: 2, Baths: 1"
        } if (obj.unitType === "TWO_BED_TWO_BATH") {
            obj.unitType = "Beds: 2, Baths: 2"
        } if (obj.unitType === "THREE_BED_THREE_BATH") {
            obj.unitType = "Beds: 3, Baths: 3"
        } if (obj.vacancy === true) {
            obj.vacancy = "Vacant"
        }if (obj.vacancy === false) {
            obj.vacancy = "Occupied"
        }


        let unitCard = document.createElement("div");
        unitCard.classList.add("m-2");
        unitCard.innerHTML = `
            <div class="card d-flex" style="width: 18rem; height: 18rem;">
                <div class="card-body d-flex flex-column justify-content-between" style="height: available">
                    <h4 class="card-text d-flex flex-row justify-content-center" id="card-${obj.id}">Unit: ${obj.unitNumber} <br>
                    Rent: $${obj.rent}<br>
                    Floor Plan: ${obj.unitType}<br>
                    Status: ${obj.vacancy}</h4>
                    <div class="d-flex justify-content-between">
                    <!-- Button trigger modal -->
                        <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#staticBackdrop" onclick="getUnitById(${obj.id})">
                        EDIT
                        </button>
                        <button type="button" class="btn btn-danger" onclick="handleUnitDelete(${obj.id})">
                        DELETE
                        </button>
                    </div>
                </div>
            </div>
            `
        unitContainer.appendChild(unitCard)
    })
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



getUnits(buildingId)

unitForm.addEventListener("submit", addUnit)
updateBtn.addEventListener("click", (e)=>{
    let unitId = e.target.getAttribute("data-unit-id")
    handleUnitEdit(unitId)
})