module.exports = function (grunt) {
    grunt.loadNpmTasks('grunt-jsxhint');
    grunt.loadNpmTasks('grunt-contrib-watch');
    grunt.loadNpmTasks('grunt-browserify');

    const mainFolder = './src/main/js/*.js';
    const subFolders = './src/main/js/*/*.js';
    const mainFolderJsx = './src/main/js/*.jsx';
    const subFolderJsx = './src/main/js/*/*.jsx';

    grunt.initConfig({
        jshint: {
            jshintrc: '.jshintrc',
            ignores: [],
            additionalSuffixes: ['.js']
        },
        browserify: {
            development: {
                src: [
                    mainFolder
                ],
                dest: './src/main/resources/public/js/bundle.js',
                options: {
                    browserifyOptions: { debug: true },
                    transform: [['babelify', { presets: ['es2015'] }]]
                }
            }
        },
        watch: {
            files: [mainFolder, subFolders, mainFolderJsx, subFolderJsx, './Gruntfile.js', './package.json'],
            tasks: ['jshint', 'browserify']
        }
    });
};
