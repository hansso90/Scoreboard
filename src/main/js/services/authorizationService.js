import './fetch';
import fetchIntercept from 'fetch-intercept';


fetchIntercept.register({
    request: (url, config) => {
        const token = localStorage.getItem("token");
        window.TOKEN = token;
        if(token && token != 'undefined'){
            config.headers.Authorization = `Bearer ${token}`;
        }
        return [url, config];
    }
});

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
            'Content-Length': suffix.length,

        },
        body: suffix
    };

    const tokenPromise = fetch(`${webApiBaseUrl}/oauth/token`, prefs);
    tokenPromise.then(o => o.body.getReader().read().then(obj => {
        const tokenJSON = JSON.parse(new TextDecoder().decode(obj.value));
        localStorage.setItem('token', tokenJSON.access_token);
    }));

    return tokenPromise;
}
