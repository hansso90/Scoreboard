module.exports = function (grunt) {
    grunt.loadNpmTasks('grunt-contrib-jshint');
    grunt.loadNpmTasks('grunt-contrib-watch');
    grunt.loadNpmTasks('grunt-browserify');

    const mainFolder = "./src/main/js/*.js";

    grunt.initConfig({
        jshint: {
            all: [mainFolder]
        },
        browserify: {
            development: {
                src: [
                    mainFolder
                ],
                dest: './src/main/resources/public/js/bundle.js',
                options: {
                    browserifyOptions: {debug: true},
                    transform: [["babelify", {"presets": ["es2015"]}]]
                }
            }
        },
        watch: {
            files: [mainFolder],
            tasks: ['jshint', 'browserify']
        }
    })

}