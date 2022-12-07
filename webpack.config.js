const MiniCssExtractPlugin = require("mini-css-extract-plugin");
const path = require("path");

module.exports = {
    mode: "development",
    entry: "./src/main/ts/default.tsx",
    output: {
        path: path.join(__dirname, "./build/resources/main/static/public"),
        clean: false,
    },
    module: {
        rules: [
            {
                test: /\.(ts|tsx)$/,
                use: "babel-loader",
                exclude: /node_modules/,
            },
            {
                test: /\.module.css$/,
                use: [
                    MiniCssExtractPlugin.loader,
                    {
                        loader: "css-loader",
                        options: {
                            esModule: true,
                            modules: {
                                namedExport: true,
                            },
                        },
                    },
                ],
            },
            {
                test: /\.scss$/,
                use: [
                    MiniCssExtractPlugin.loader,
                    {
                        loader: "css-loader",
                        // options: {
                        //     esModule: true,
                        //     modules: {
                        //         namedExport: true,
                        //     },
                        // }
                    },
                    "sass-loader"
                ]
            }
        ],
    },
    plugins: [new MiniCssExtractPlugin()],
    resolve: {
        extensions: [".ts", ".tsx", ".js"],
    },
};