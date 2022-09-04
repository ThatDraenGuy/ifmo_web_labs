const path = require('path');
const HtmlWebpackPlugin = require('html-webpack-plugin');
const CopyWebpackPlugin = require('copy-webpack-plugin');

module.exports = {
  entry: './front/script/index.js',
  output: {
    filename: 'front/bundle.js',
    path: path.resolve(__dirname, 'dist'),
    assetModuleFilename: 'img/[hash][ext][query]',
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
            type: 'asset/resource',
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
    // move php-realted stuff unchanged to be able to use 'dist' folder as a complete final product
    new CopyWebpackPlugin({
        patterns: [
            { from: 'back', to: 'back' },
            { from: 'front/style/response.css', to: 'back/style/response.css'}
        ]
    })
  ]
};