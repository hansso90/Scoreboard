/*
 * This file represents the Dust (and React)-Server.
 *
 * Note: The webserver send a coars header for convienence.
 */

const express = require('express');

const app = express();

const port = process.env.PORT || 1337;
const env = process.env.NODE_ENV || 'local'; // production, local, development

//Start server.
const server = app.listen(port, () => {
 'use strict';
 
 const host = server.address().address;
 const port = server.address().port;
 
    console.log('Server listening at http://%s:%s', host, port);
});

// app.get('/', function(req, res) {
    // res.send(`NODE_ENV is ${process.env.NODE_ENV}`);
// })

module.exports = server;
