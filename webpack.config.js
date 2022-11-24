var path = require("path");

module.exports = {
    entry: "./build/typescript/default.js",
    output: {
        path: path.resolve("build/resources/main/static"),
        filename: "bundle.js"
    },
};