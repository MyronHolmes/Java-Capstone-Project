const registerForm = document.getElementById('register-form');
const registerFN = document.getElementById('register-firstname');
const registerLN = document.getElementById('register-lastname');
const registerEmail = document.getElementById('register-email');
const registerUN = document.getElementById('register-username');
const registerPW = document.getElementById('register-password');


const headers = {
    'Content-Type':'application/json'
};
const baseUrl = 'http://localhost:8080/landlords'


const handleRegisterSubmit = async (e) =>{
    e.preventDefault()

    let bodyObj ={
        firstName: registerFN.value,
        lastName: registerLN.value,
        email: registerEmail.value,
        username: registerUN.value,
        password: registerPW.value
    }

    const response = await fetch(`${baseUrl}/register`, {
        method: "POST",
        body: JSON.stringify(bodyObj),
        headers: headers
    })
        .catch(err => console.error(err.messages))

    const responseArr = await response.json()

    if(response.status === 200){
        window.location.replace(responseArr[0])
    }
}



registerForm.addEventListener("submit", handleRegisterSubmit);



