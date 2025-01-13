var exec = require('cordova/exec');

exports.error = function (success, error) {
    exec(success, error, 'LogCat', 'error', []);
};

exports.sendLogs = function (success, error) {
    exec(success, error, 'LogCat', 'sendLogs', []);
};

exports.success = function (success, error) {
    exec(success, error, 'LogCat', 'success', []);
};

