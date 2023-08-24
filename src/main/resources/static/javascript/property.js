const cookieArr = document.cookie.split('=');
const userId = cookieArr[1]
console.log(cookieArr)
console.log(cookieArr[1])


const propertyName= document.getElementById("property-name");
const propertyEarnings = document.getElementById("property-earnings");
const propertyForm = document.getElementById("property-form");
const propertyContainer = document.getElementById("property-container")
const propertyList = document.getElementById("property-list");

const headers = {
    'Content-Type':'application/json'
};
const baseUrl = 'http://localhost:8080/properties';

const listProperties = arr =>{
    for(i = 0; i < arr.length; i++){

        let li = document.createElement("li");
        li.innerText= ''
        li.innerText= `${arr[i].propertyName}`
        propertyList.appendChild(li);
        propertyContainer.appendChild(propertyList);

    }
}

const getProperties= async (userId) =>{
    propertyList.innerText = '';

    await fetch(`${baseUrl}/landlord/${userId}`, {
        method:"GET",
        headers:headers
    })
        .then(response => response.json())
        .then(data => listProperties(data))
        .catch(err => console.error(err))

}

const handleSubmit = async (e) =>{
    e.preventDefault();
    let bodyObj ={
        propertyName: propertyName.value,
        projectedEarnings: propertyEarnings.value
    }

    if (bodyObj.propertyName.length === 0){
        alert("Invalid entry")
    }else {

        const response = await fetch(`${baseUrl}/landlord/${userId}`, {
            method: "POST",
            body: JSON.stringify(bodyObj),
            headers: headers
        })
            .catch(err => console.error(err.message))
        if (response.status === 200) {

        }

        propertyName.value= '';
        propertyEarnings.value = '';
        return getProperties(userId);
    }
}

getProperties(userId);

propertyForm.addEventListener("submit", handleSubmit)