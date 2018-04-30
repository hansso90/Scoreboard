import './fetch';

const webApiBaseUrl = 'http://localhost:8080';

export function login(userName, password) {
    const body = `grant_type=password&username=${userName}&password=${password}`;
    // Define fetch properties
    const prefs = {
        method: 'POST',
        credentials: 'same-origin',
        headers: {
            Authorization: 'Basic c2NvcmVib2FyZDoxMjM0NTY=',
            Accept: 'application/json',
            'Content-Type': 'application/x-www-form-urlencoded; charset=utf-8',
            'Content-Length': body.length,

        },
        body
    };

    return fetch(`${webApiBaseUrl}/oauth/token`, prefs);
}


export function logout(token) {
    // Define fetch properties
    const prefs = {
        method: 'DELETE',
        credentials: 'same-origin',
        headers: {
            Authorization: `Bearer ${token}`,
            Accept: 'application/json'

        }
    };

    return fetch(`${webApiBaseUrl}/oauth/token`, prefs);
}
