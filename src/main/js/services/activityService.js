import './fetch';

const webApiBaseUrl = 'http://localhost:8080';


export function getActivityById(id) {
    // Define fetch properties
    const prefs = {
        method: 'GET',
        credentials: 'same-origin',
        headers: {
            Accept: 'application/json',
            'Content-Type': 'application/json; charset=utf-8'
        }
    };

    return fetch(`${webApiBaseUrl}/api/v0/activity/${id}`, prefs);
}

export function getActivities() {
    // Define fetch properties
    const prefs = {
        method: 'GET',
        credentials: 'same-origin',
        headers: {
            Accept: 'application/json',
            'Content-Type': 'application/json; charset=utf-8'
        }
    };

    return fetch(`${webApiBaseUrl}/api/v0/activity`, prefs);
}
