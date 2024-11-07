const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
  transpileDependencies: true,
  // publicPath: '/',
  publicPath: '/savage/',
  devServer: {
    proxy: {
      '/api': {
        target: 'http://localhost:8833/savage',
        changeOrigin: true,
        pathRewrite: {
          '^/api': ''
        }
      }
    }
  }
})
