const path = require('path');
const HtmlWebpackPlugin = require('html-webpack-plugin');

module.exports = {
//   entry: [
//     './front/script/connector.js',
//     './front/script/graph.js',
//     './front/script/paramChecker.js',
//     './front/script/utils.js'
//   ],
  entry: './front/script/index.js',
  output: {
    filename: 'bundle.js',
    path: path.resolve(__dirname, 'dist'),
    clean: true
  },
  module: {
    rules: [
        {
            test: /\.css$/i,
            use: [
                'style-loader',
                'css-loader'
            ]
        },
        {
            test: /\.(png|svg|jpg|jpeg|gif)$/i,
            type: 'asset/resource'
        },
        {
            test: /\.html$/,
            use: [
              {
                loader: 'html-loader',
              }
            ]
        }
    ]
  },
  plugins: [
    new HtmlWebpackPlugin({
      template: 'index.html'
    }),
  ]
};