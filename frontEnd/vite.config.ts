import {fileURLToPath, URL} from 'node:url'

import {defineConfig} from 'vite'
import vue from '@vitejs/plugin-vue'
import VueDevTools from 'vite-plugin-vue-devtools'

// https://vitejs.dev/config/
export default defineConfig({
    plugins: [
        vue(),
        VueDevTools(),
    ],
    resolve: {
        alias: {
            '@': fileURLToPath(new URL('./src', import.meta.url))
        }
    },

    server: {
        proxy: {
            // Bike
            'api/bike/clone': {
                target: 'http://localhost:8080/bike/clone',
                changeOrigin: true,
                rewrite: (path) => path.replace(/^\/api\/bike\/clone/, ''),
                ws: true
            },
            '/api/bike/getAll': {
                target: 'http://localhost:8080/bike/getAll',
                changeOrigin: true,
                rewrite: (path) => path.replace(/^\/api\/bike\/getAll/, ''),
                ws: true
            },

            // Car
            'api/car/clone': {
                target: 'http://localhost:8080/car/clone',
                changeOrigin: true,
                rewrite: (path) => path.replace(/^\/api\/car\/clone/, ''),
                ws: true
            },
            '/api/car/getAll': {
                target: 'http://localhost:8080/car/getAll',
                changeOrigin: true,
                rewrite: (path) => path.replace(/^\/api\/car\/getAll/, ''),
                ws: true
            },

            //Bike rent
            '/api/rent/bike/getAll': {
                target: 'http://localhost:8080/rent/bike/getAllRents',
                changeOrigin: true,
                rewrite: (path) => path.replace(/^\/api\/rent\/bike\/getAll/, ''),
                ws: true
            },
            'api/rent/bike/endRent': {
                target: 'http://localhost:8080/rent/bike/endRent',
                changeOrigin: true,
                rewrite: (path) => path.replace(/^\/api\/rent\/bike\/endRent/, ''),
                ws: true
            },

            //Car rent
            '/api/rent/car/getAll': {
                target: 'http://localhost:8080/rent/car/getAllRents',
                changeOrigin: true,
                rewrite: (path) => path.replace(/^\/api\/rent\/car\/getAll/, ''),
                ws: true
            },
            'api/rent/car/endRent': {
                target: 'http://localhost:8080/rent/car/endRent',
                changeOrigin: true,
                rewrite: (path) => path.replace(/^\/api\/rent\/car\/endRent/, ''),
                ws: true
            },

            // User
            '/api/user/getAll': {
                target: 'http://localhost:8080/user/getAll',
                changeOrigin: true,
                rewrite: (path) => path.replace(/^\/api\/user\/getAll/, ''),
                ws: true
            },

        }
    }

})
