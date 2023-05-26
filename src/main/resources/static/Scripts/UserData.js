let User = {};
let result={};
let music={};

function submitData() {
    User.userEmail = document.getElementById("userEmail").value;
    User.userPassword = document.getElementById("userPassword").value;

    async function callApi() {
         result = await fetch('http://localhost:8088/viewUser', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(User)
        });
        try {
            result = await result.json();
            console.log(result);
        } catch (err) {
            if (err != null) {
                alert("Inavalid username or password");
                return;
            }
        }
        window.location = "http://localhost:8088/html/Interface.html";
        return;
    }
    callApi();
}

function displayPostApi(input,url) {
    async function callApi() {
         result = await fetch(url, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(input)
        });
        result= await result.json();
        // music=result;
        return result;
    }
    return callApi();
}

function displayGetApi(url){
    async function callApi() {
        result = await fetch(url);
        result=await result.json();
       return result 
    }
    return callApi();
}

function displayPostApiText(input,url) {
    async function callApi() {
         result = await fetch(url, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(input)
        });
        result= await result.text();
        return result;
    }
    return callApi();
}