console.log(document.cookie)
const propertyName = document.getElementById("propertyBuilding");
const buildingForm = document.getElementById("building-form");
const buildingNumber = document.getElementById("building-number");
const buildingContainer = document.getElementById("building-container");


const userId = getCookie("userId");
const propertyId = getCookie("propertyId");
propertyName.innerText = getCookie("propertyName")+"'s Buildings:";

const headers = {
    'Content-Type':'application/json'
};
const baseUrl = 'http://localhost:8080/buildings'

const addBuilding = async (e)=>{
    e.preventDefault();
    bodyObj = {
        buildingNumber: buildingNumber
    }

    if (bodyObj.buildingNumber.length === 0){
        alert("Invalid entry")
    }else {
       const response= await fetch(`${baseUrl}/property/${propertyId}`, {
            method:"POST",
            body: JSON.stringify(bodyObj),
            headers: headers
        })
            .catch(err=> console.error(err.message()))
    if (response.status === 200){
        return getBuildings()
        }else {
        console.log("not hit")
    }
        buildingNumber.value = '';
    }
};


const getBuildings = async (propertyId)=>{
    await fetch(`${baseUrl}/property/${propertyId}`, {
        method: "GET",
        headers: headers
    })
        .then(response => response.json())
        .then(date => createCard(data))
        .catch((err=> console.error(err.message)))
};

const createCard= (arr) =>{
    buildingContainer.innerHTML=''
    arr.forEach(obj => {
        let buildingCard = document.createElement("div");
        buildingCard.classList.add("m-2");
        buildingCard.innerHTML= `
            <div class="card d-flex" style="width: 18rem; height: 18rem;">
                <div class="card-body d-flex flex-column justify-content-between" style="height: available">
                    <p class="card-text">${obj.body}</p>
                    <div class="d-flex justify-content-between">
                        <button>DELETE</button>
                        <button>EDIT</button>
                    </div>
                </div>
            </div>
`
        buildingContainer.appendChild(buildingCard);
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

