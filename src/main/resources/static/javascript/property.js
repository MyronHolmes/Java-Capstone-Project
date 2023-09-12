const propertyName= document.getElementById("property-name");
const propertyEarnings = document.getElementById("property-earnings");
const propertyForm = document.getElementById("property-form");
const propertyContainer = document.getElementById("property-container")
const propertyList = document.getElementById("property-list");

const userId = getCookie("userId")

const headers = {
    'Content-Type':'application/json'
};
const baseUrl = 'http://localhost:8080/properties/'

const listProperties = arr =>{
    for(let i = 0; i < arr.length; i++){

        let propertyId = arr[i].id
        let propertyName = arr[i].propertyName
        let projectedEarnings = arr[i].projectedEarnings
        let li = document.createElement("li");
        li.classList.add("d-flex", "justify-content-center")
        let actionBtn = document.createElement("button")
        actionBtn.setAttribute("type", "button")
        actionBtn.classList.add("btn", "btn-outline-primary", "btn-sm", "m-2")
        actionBtn.setAttribute("id", `${propertyId}`)
        actionBtn.innerHTML = "<svg xmlns=\"http://www.w3.org/2000/svg\" width=\"10\" height=\"10\" fill=\"currentColor\" class=\"bi bi-pen\" viewBox=\"0 0 16 16\">\n" +
            "<path d=\"m13.498.795.149-.149a1.207 1.207 0 1 1 1.707 1.708l-.149.148a1.5 1.5 0 0 1-.059 2.059L4.854 14.854a.5.5 0 0 1-.233.131l-4 1a.5.5 0 0 1-.606-.606l1-4a.5.5 0 0 1 .131-.232l9.642-9.642a.5.5 0 0 0-.642.056L6.854 4.854a.5.5 0 1 1-.708-.708L9.44.854A1.5 1.5 0 0 1 11.5.796a1.5 1.5 0 0 1 1.998-.001zm-.644.766a.5.5 0 0 0-.707 0L1.95 11.756l-.764 3.057 3.057-.764L14.44 3.854a.5.5 0 0 0 0-.708l-1.585-1.585z\"/>\n" +
            "</svg> Edit";
        li.appendChild(actionBtn)
        let deleteBtn = document.createElement("button")
        deleteBtn.setAttribute("type", "button")
        deleteBtn.classList.add("btn", "btn-outline-danger", "btn-sm", "m-2")
        deleteBtn.setAttribute("id", `${propertyId}`)
        deleteBtn.innerHTML ="Delete"
        li.appendChild(deleteBtn);
        let buildingLink = document.createElement("h3");
        buildingLink.classList.add("visible", "link-primary")
        buildingLink.innerHTML= propertyName + ": $" + projectedEarnings;
        li.appendChild(buildingLink);
        let input = document.createElement("input");
        input.classList.add("invisible");
        li.appendChild(input)


        const handleDeleteProperty= async ()=>{
            let paramId= actionBtn.getAttribute("id")
            let bodyObj ={
                id: paramId
            }

            await fetch(`${baseUrl}${paramId}`, {
                method: "DELETE",
                body: JSON.stringify(bodyObj),
                headers: headers
            })
                .catch(err=> console.error(err))
            return getProperties(userId);
        }

        const handleClick= async ()=>{

            const response = await fetch(`${baseUrl}${propertyId}/buildings`,{
                method:"GET",
                headers: headers
            })
                .catch(err=> console.error(err.message))
            const responseAy= await response.json()
            if (response.status === 200){
                if (responseAy.length < 2){
                }else{
                    document.cookie= `propertyId=${responseAy[1]}`
                    document.cookie= `propertyName=${responseAy[2]}`
                    window.location.replace(responseAy[0]);
                }
            }
        }
        const handleEditClick = async (e)=>{
            if (actionBtn.innerHTML === "Save"){
                let paramId= actionBtn.getAttribute("id")
                let bodyObj ={
                    id: paramId,
                    propertyName: input.value
                }
                await fetch(baseUrl + paramId, {
                    method: "PUT",
                    body: JSON.stringify(bodyObj),
                    headers: headers
                })
                    .catch(err => console.error(err))
                return getProperties(userId)

            }else if (actionBtn !== "Save"){
                buildingLink.classList.replace("visible", "invisible")
                input.classList.replace("invisible", "visible")
                input.value = propertyName;
                actionBtn.innerHTML= "Save"
            }

        }

        propertyList.appendChild(li);
        propertyContainer.appendChild(propertyList);
        buildingLink.addEventListener("click", handleClick);
        actionBtn.addEventListener("click", handleEditClick)
        deleteBtn.addEventListener("click", handleDeleteProperty);
    }

}



const getProperties= async (userId) =>{
    propertyList.innerText = '';

    await fetch(`${baseUrl}landlord/${userId}`, {
        method:"GET",
        headers:headers
    })
        .then(response => response.json())
        .then(data => listProperties(data))
        .catch(err => console.error(err))

}



const addProperty = async (e) =>{
    e.preventDefault();
    let bodyObj ={
        propertyName: propertyName.value,

    }


    if (bodyObj.propertyName.length === 0){
        alert("Invalid entry")
    }else {

        const response = await fetch(`${baseUrl}landlord/${userId}`, {
            method: "POST",
            body: JSON.stringify(bodyObj),
            headers: headers
        })
            .catch(err => console.error(err.message))
        if (response.status === 200) {
            propertyName.value= '';
            return getProperties(userId)

        }

    }
};



const handleLogout = () =>{
    let c = document.cookie.split(";");
    for (let i in c){
        document.cookie = /^[^=]+/.exec(c[i])[0]+"=;expires= Thu, 01 Jan 1970 00:00:00 GMT"
    }
}



function setCookie(name, value){
    document.cookie = `${name}=${value}`
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

getProperties(userId)
console.log(document.cookie)




propertyForm.addEventListener("submit", addProperty)
