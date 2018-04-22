import './fetch';

const webApiBaseUrl = 'http://localhost:8080';


export function login(userName, password) {
    // return Promise.resolve({
    //     ok: true,
    //     status: 200
    // });

    // Define fetch properties
    const prefs = {
        method: 'POST',
        credentials: 'same-origin',
        headers: {
            Authorization: 'Basic c2NvcmVib2FyZDoxMjM0NTY=',
            Accept: 'application/json',
            'Content-Type': 'applciation/x-www-form-urlencoded; charset=utf-8'
        }
    };

    return fetch(`${webApiBaseUrl}/oauth/token?grant_type=password&username=${userName}&password=${password}`, prefs);
}
