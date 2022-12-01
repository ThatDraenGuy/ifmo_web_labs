// var path = require("path");
//
// module.exports = {
//     entry: "./build/typescript/default.js",
//     output: {
//         path: path.resolve("build/resources/main/static"),
//         filename: "bundle.js"
//     },
//     resolve: {
//         extensions: [".js", ".jsx", ".css"]
//     },
//     module: {
//         strictExportPresence: true,
//         rules: [
//             // { test: /\.ts?$/, loader: "ts-loader" },
//             // { test: /\.tsx?$/, loader: "ts-loader" },
//             {
//                 test: /\.module.css$/,
//                 use: [
//                     // { loader: "style-loader" },  // to inject the result into the DOM as a style block
//                     // { loader: "css-modules-typescript-loader"},  // to generate a .d.ts module next to the .scss file (also requires a declaration.d.ts with "declare modules '*.scss';" in it to tell TypeScript that "import styles from './styles.scss';" means to load the module "./styles.scss.d.td")
//                     { loader: "css-loader", options: {
//                         esModule: true,
//                         modules: {
//                             namedExport: true
//                         } } },  // to convert the resulting CSS to Javascript to be bundled (modules:true to rename CSS classes in output to cryptic identifiers, except if wrapped in a :global(...) pseudo class)
//                     // NOTE: The first build after adding/removing/renaming CSS classes fails, since the newly generated .d.ts typescript module is picked up only later
//                 ] },
//
//         ]
//     }
// };

const MiniCssExtractPlugin = require("mini-css-extract-plugin");
const path = require("path");

module.exports = {
    mode: "development",
    entry: "./src/main/ts/default.tsx",
    output: {
        path: path.join(__dirname, "./build/resources/main/static"),
        clean: false,
    },
    module: {
        strictExportPresence: true,
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
        ],
    },
    plugins: [new MiniCssExtractPlugin()],
    resolve: {
        extensions: [".ts", ".tsx", ".js"],
    },
};