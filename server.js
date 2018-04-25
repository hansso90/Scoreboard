const http = require('http');
const fs = require('fs');
const path = require('path');

http.createServer((request, response) => {
    console.log('request starting...');
    console.log(request.url);
    const pathBase = './src/main/resources/public';
    let filePath = pathBase + request.url;
    if(request.url == '/') { filePath = `${pathBase}/index.html`; }
    console.log(`serving: ${filePath}`);
    const extname = path.extname(filePath);
    let contentType = 'text/html';
    switch (extname) {
        case '.js':
            contentType = 'text/javascript';
            break;
        case '.css':
            contentType = 'text/css';
            break;
        case '.json':
            contentType = 'application/json';
            break;
        case '.png':
            contentType = 'image/png';
            break;
        case '.jpg':
            contentType = 'image/jpg';
            break;
        case '.wav':
            contentType = 'audio/wav';
            break;
    }

    fs.readFile(filePath, (error, content) => {
        if(error) {
            if(error.code == 'ENOENT') {
                if(contentType === 'text/html') {
                    fs.readFile('./src/main/resources/public/index.html', (error, content) => {
                        response.writeHead(200, { 'Content-Type': contentType });
                        response.end(content, 'utf-8');
                    });
                } else {
                    response.writeHead(404);
                    response.end(`Sorry, check with the site admin for error: ${error.code} ..\n`);
                    response.end();
                }
            } else {
                response.writeHead(500);
                response.end(`Sorry, check with the site admin for error: ${error.code} ..\n`);
                response.end();
            }
        } else {
            response.writeHead(200, { 'Content-Type': contentType });
            response.end(content, 'utf-8');
        }
    });
}).listen(1337);
console.log('Server running at http://127.0.0.1:1337/');
