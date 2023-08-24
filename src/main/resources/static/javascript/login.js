const username = document.getElementById('login-username');
const password = document.getElementById('login-password');
const loginForm = document.getElementById('login-form');

const headers = {
    'Content-Type':'application/json'
};
const baseUrl = 'http://localhost:8080/landlords';


const handelLoginSubmit = async (e) =>{

    e.preventDefault();

    let bodyObj = {
        username: username.value,
        password: password.value
    }

    const response = await fetch(`${baseUrl}/login`, {
            method:"POST",
            body: JSON.stringify(bodyObj),
            headers: headers
    })
        .catch(err => console.error(err.messages))

    const responseArr = await response.json()
    if(response.status === 200){
        if(responseArr.length === 2){
            alert((responseArr[1]))
        }else{
            document.cookie = `userId=${responseArr[1]}`;
            alert(`Welcome To Property Portal ${responseArr[2]}!`)
            window.location.replace(responseArr[0]);

        }

    }
};

loginForm.addEventListener('submit', handelLoginSubmit);
