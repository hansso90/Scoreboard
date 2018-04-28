import './fetch';

const webApiBaseUrl = 'http://localhost:8080';


export function getActivityById(id, token) {
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

    return fetch(`${webApiBaseUrl}/api/v0/activity/${id}`, prefs);
}

export function getActivities(token) {
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

    return fetch(`${webApiBaseUrl}/api/v0/activity`, prefs);
}

export function createActivity(token, user, chapterId, categoryId, stardust, description, date) {
    const body = JSON.stringify({
        category: {
            id: categoryId
        },
        chapter: {
            id: chapterId
        },
        date: date,
        description: description,
        stardust: stardust,
        users: [user]
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

    return fetch(`${webApiBaseUrl}/api/v0/activity`, prefs);
}