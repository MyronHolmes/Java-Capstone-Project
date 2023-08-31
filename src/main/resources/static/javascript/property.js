const propertyName= document.getElementById("property-name");
const propertyEarnings = document.getElementById("property-earnings");
const propertyForm = document.getElementById("property-form");
const propertyContainer = document.getElementById("property-container")
const propertyList = document.getElementById("property-list");

const userId = getCookie("userId")

const headers = {
    'Content-Type':'application/json'
};
const baseUrl = 'http://localhost:8080/properties'

const listProperties = arr =>{
    for(let i = 0; i < arr.length; i++){


        let propertyId = arr[i].id
        let propertyName = arr[i].propertyName
        let li = document.createElement("li");
        let buildingLink = document.createElement("p");
        buildingLink.innerHTML= propertyName;
        li.appendChild(buildingLink);

        const handleClick= async (e)=>{
            e.preventDefault();

            const response = await fetch(`${baseUrl}/${propertyId}/buildings`,{
                method:"GET",
                headers: headers
            })
                .catch(err=> console.error(err.message))
            const responseAy= await response.json()
            if (response.status === 200){
                if (responseAy.length < 2){
                    console.log('not hit')
                }else{
                    console.log( 'hit')

                    document.cookie= `propertyId=${responseAy[1]}`
                    document.cookie= `propertyName=${responseAy[2]}`
                    window.location.replace(responseAy[0]);
                }
            }
        }
        propertyList.appendChild(li);
        propertyContainer.appendChild(propertyList);
        buildingLink.addEventListener("click", handleClick);
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



const addProperty = async (e) =>{
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
            propertyName.value= '';
            propertyEarnings.value = '';
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
