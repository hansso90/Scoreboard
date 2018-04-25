import './fetch';

const webApiBaseUrl = 'http://localhost:8080';


export function getChapterById(id, token) {
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

    return fetch(`${webApiBaseUrl}/api/v0/chapter/${id}`, prefs);
}

export function getChapters(token) {
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

    return fetch(`${webApiBaseUrl}/api/v0/chapter`, prefs);
}

export function createChapter(token, name) {
    // Define fetch properties
    const body = JSON.stringify({
        name
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

    return fetch(`${webApiBaseUrl}/api/v0/chapter`, prefs);
}

export function removeChapter(token, id) {
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

    return fetch(`${webApiBaseUrl}/api/v0/chapter/${id}`, prefs);
}
