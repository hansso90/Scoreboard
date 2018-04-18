/**
 * Helper for use of fetch()
 *
 * - Polyfills promise support for IE (<= IE11)
 * - Adds fetch() method to IE8
 */

var Promise = require('es6-promise').Promise;
require('fetch-everywhere');
