import './fetch';

const webApiBaseUrl = 'http://localhost:8080';



export function getDashboardActivities(token) {
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

    return fetch(`${webApiBaseUrl}/api/v0/dashboard`, prefs);
}
