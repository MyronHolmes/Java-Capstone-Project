const cookieArr = document.cookie.split('=');
const UserId = cookieArr[1]
console.log(cookieArr)
console.log(cookieArr[1])

const propertyName= document.getElementById("property-name");
const propertyEarnings = document.getElementById("property-earnings");
const submitbtn = document.getElementById("submit-button");

const headers = {
    'Content-Type':'application/json'
};
const baseUrl = 'http://localhost:8080/properties';

const handleSubmit = async (e) =>{
    e.preventDefault()
    let bodyObj ={
        propertyName: propertyName.value,
        projectedEarnings: propertyEarnings.value
    }

    const response = await fetch(`${baseUrl}/landlord/`)
}