import './fetch';

const webApiBaseUrl = 'http://localhost:8080';


export function getCurrentUser(token) {
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

    return fetch(`${webApiBaseUrl}/api/v0/user/current`, prefs);
}

export function getUsers(token) {
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

    return fetch(`${webApiBaseUrl}/api/v0/user`, prefs);
}

export function createUser(token, name, username, password, chapterId) {
    // Define fetch properties
    const now = new Date().toISOString();
    const body = JSON.stringify({
        accountNonExpired: true,
        accountNonLocked: true,
        authorities: [
            {
                authority: 'ROLE_ACTIVITYMANAGEMENT'
            }
        ],
        chapter: {
            id: chapterId,
            createdAt: now,
            lastModifiedAt: now,
            name: 'string',
            new: false
        },
        createdAt: now,
        credentialsNonExpired: true,
        enabled: true,
        expired: now,
        lastModifiedAt: now,
        locked: false,
        name,
        new: true,
        password,
        role: {
            createdAt: now,
            id: 1,
            lastModifiedAt: now,
            name: 'admin',
            new: false,
            rights: [
                'ROLE_ROLEMANAGEMENT',
                'ROLE_USERMANAGEMENT',
                'ROLE_ACTIVITYMANAGEMENT'
            ]
        },
        username
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

    return fetch(`${webApiBaseUrl}/api/v0/user`, prefs);
}

export function removeUser(token, id) {
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

    return fetch(`${webApiBaseUrl}/api/v0/user/${id}`, prefs);
}
