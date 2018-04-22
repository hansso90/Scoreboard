module.exports = function (grunt) {
    grunt.loadNpmTasks('grunt-contrib-jshint');
    grunt.loadNpmTasks('grunt-contrib-watch');
    grunt.loadNpmTasks('grunt-browserify');

    const mainFolderJS = './src/main/js/**';
    grunt.initConfig({
        browserify: {
            development: {
                src: [
                    mainFolderJS
                ],
                dest: './src/main/resources/public/js/bundle.js',
                options: {
                    browserifyOptions: {
                        debug: true,
                        extensions: ['.js', '.jsx'],

                        transform:
                        [[
                            'babelify',
                            {
                                presets: ['es2015', 'react'],
                                plugins: ['transform-object-rest-spread', ['transform-runtime', {
                                    polyfill: false,
                                    regenerator: true
                                }]]
                            }
                        ]]
                    }
                },
            }
        },
        watch: {
            files: [mainFolderJS, './Gruntfile.js', './package.json'],
            tasks: ['browserify']
        }
    });
};
