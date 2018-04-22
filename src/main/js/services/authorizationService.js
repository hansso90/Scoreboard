import './fetch';

const webApiBaseUrl = 'http://localhost:8080';


export function login(userName, password) {
    // return Promise.resolve({
    //     ok: true,
    //     status: 200
    // });
    const formData = new FormData();
    const suffix = `grant_type=password&username=${userName}&password=${password}`;

    formData.append('grant_type', 'password');
    formData.append('username', userName);
    formData.append('password', password);


    // Define fetch properties
    const prefs = {
        method: 'POST',
        //credentials: 'same-origin',
        headers: {
            Authorization: 'Basic c2NvcmVib2FyZDoxMjM0NTY=',
            Accept: 'application/json',
            'Content-Type': 'application/x-www-form-urlencoded; charset=utf-8',
            'Content-Length' : suffix.length,

        },
        body: suffix
    };

    fetch(`${webApiBaseUrl}/oauth/token`, prefs).then(o => o.body.getReader().read().then(obj => console.log(new TextDecoder().decode(obj.value))));

    return fetch(`${webApiBaseUrl}/oauth/token`, prefs);
}
