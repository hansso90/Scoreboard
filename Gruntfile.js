module.exports = function (grunt) {
    grunt.loadNpmTasks('grunt-contrib-jshint');
    grunt.loadNpmTasks('grunt-contrib-watch');
    grunt.loadNpmTasks('grunt-browserify');

    const mainFolderJS = "./src/main/js/*.js";
    const mainFolderJSX = "./src/main/js/*.js";

    grunt.initConfig({
        browserify: {
            development: {
                src: [
                    mainFolderJS, mainFolderJSX
                ],
                dest: './src/main/resources/public/js/bundle.js',
                options: {
                    browserifyOptions: {debug: true},
                    transform: [["babelify", {
                        "babel": require("@babel/core"),
                        "presets": ["@babel/preset-env", "@babel/preset-react"]
                    }]]
                }
            }
        },
        watch: {
            files: [mainFolderJS, mainFolderJSX, './Gruntfile.js', './package.json'],
            tasks: ['browserify']
        }
    })

}