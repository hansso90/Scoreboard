import './fetch';

const webApiBaseUrl = 'http://localhost:8080';


export function login(userName, password) {
    // Define fetch properties
    const prefs = {
        method: 'POST',
        credentials: 'same-origin',
        headers: {
            Accept: 'application/json',
            'Content-Type': 'application/json; charset=utf-8',
            Authorization: 'Basic c2NvcmVib2FyZDoxMjM0NTY='
        }
    };

    return fetch(`${webApiBaseUrl}/oauth/token?grant_type=password&username=${userName}&password=${password}`, prefs);
}
