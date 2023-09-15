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
    }else if(responseArr.message === "could not execute statement [ERROR: duplicate key value violates unique constraint \"landlords_email_key\"\n  Detail: Key (email)=(test@email.com) already exists.] [insert into landlords (email,first_name,last_name,password,username) values (?,?,?,?,?)]; SQL [insert into landlords (email,first_name,last_name,password,username) values (?,?,?,?,?)]; constraint [landlords_email_key]") {
        alert("Email Address Already Taken")
    }else if(responseArr.message === "could not execute statement [ERROR: duplicate key value violates unique constraint \"landlords_username_key\"\n  Detail: Key (username)=(test) already exists.] [insert into landlords (email,first_name,last_name,password,username) values (?,?,?,?,?)]; SQL [insert into landlords (email,first_name,last_name,password,username) values (?,?,?,?,?)]; constraint [landlords_username_key]"){
        alert("Username Not Available")
    }
}



registerForm.addEventListener("submit", handleRegisterSubmit);



