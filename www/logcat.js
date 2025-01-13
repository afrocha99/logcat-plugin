var exec = require('cordova/exec');

exports.errorPlugin = function (success, error) {
    exec(success, error, 'LogCat', 'errorPlugin', []);
};

exports.sendLogs = function (success, error) {
    exec(success, error, 'LogCat', 'sendLogs', []);
};

exports.success = function (success, error) {
    exec(success, error, 'LogCat', 'success', []);
};

