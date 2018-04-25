import './fetch';

const webApiBaseUrl = 'http://localhost:8080';


export function getCategoryById(id, token) {
    // Define fetch properties
    const prefs = {
        method: 'GET',
        credentials: 'same-origin',
        headers: {
            Accept: 'application/json',
            'Content-Type': 'application/json; charset=utf-8',
            Authorization: `Bearer ${token}`
        }
    };

    return fetch(`${webApiBaseUrl}/api/v0/category/${id}`, prefs);
}

export function getCategories(token) {
    // Define fetch properties
    const prefs = {
        method: 'GET',
        credentials: 'same-origin',
        headers: {
            Accept: 'application/json',
            'Content-Type': 'application/json; charset=utf-8',
            Authorization: `Bearer ${token}`
        }
    };

    return fetch(`${webApiBaseUrl}/api/v0/category`, prefs);
}

export function createCategory(token, name, defaultStardust) {
    // Define fetch properties
    const body = JSON.stringify({
        name,
        defaultStardust
    });
    const prefs = {
        method: 'POST',
        credentials: 'same-origin',
        headers: {
            Accept: 'application/json',
            'Content-Type': 'application/json; charset=utf-8',
            Authorization: `Bearer ${token}`
        },
        body
    };

    return fetch(`${webApiBaseUrl}/api/v0/category`, prefs);
}

export function removeCategory(token, id) {
    // Define fetch properties
    const prefs = {
        method: 'DELETE',
        credentials: 'same-origin',
        headers: {
            Accept: 'application/json',
            'Content-Type': 'application/json; charset=utf-8',
            Authorization: `Bearer ${token}`
        },

    };

    return fetch(`${webApiBaseUrl}/api/v0/category/${id}`, prefs);
}
