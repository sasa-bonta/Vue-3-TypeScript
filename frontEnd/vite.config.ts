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
            'api/bike/create': {
                target: 'http://localhost:8080/bike/create',
                changeOrigin: true,
                rewrite: (path) => path.replace(/^\/api\/bike\/create/, ''),
                ws: true
            },
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
            'api/bike/delete': {
                target: 'http://localhost:8080/bike/delete',
                changeOrigin: true,
                rewrite: (path) => path.replace(/^\/api\/bike\/delete/, ''),
                ws: true
            },

            // Car
            'api/car/create': {
                target: 'http://localhost:8080/car/create',
                changeOrigin: true,
                rewrite: (path) => path.replace(/^\/api\/car\/create/, ''),
                ws: true
            },
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
            'api/car/delete': {
                target: 'http://localhost:8080/car/delete',
                changeOrigin: true,
                rewrite: (path) => path.replace(/^\/api\/car\/delete/, ''),
                ws: true
            },

            //Bike rent
            'api/rent/bike/createRent': {
                target: 'http://localhost:8080/rent/bike/createRent',
                changeOrigin: true,
                rewrite: (path) => path.replace(/^\/api\/rent\/bike\/createRent/, ''),
                ws: true
            },
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
            'api/rent/bike/deleteRent': {
                target: 'http://localhost:8080/rent/bike/deleteRent',
                changeOrigin: true,
                rewrite: (path) => path.replace(/^\/api\/rent\/rent\/bike\/deleteRent/, ''),
                ws: true
            },

            //Car rent
            'api/rent/car/createRent': {
                target: 'http://localhost:8080/rent/car/createRent',
                changeOrigin: true,
                rewrite: (path) => path.replace(/^\/api\/rent\/car\/createRent/, ''),
                ws: true
            },
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
            'api/rent/car/deleteRent': {
                target: 'http://localhost:8080/rent/car/deleteRent',
                changeOrigin: true,
                rewrite: (path) => path.replace(/^\/api\/rent\/rent\/car\/deleteRent/, ''),
                ws: true
            },

            // User
            'api/user/create': {
                target: 'http://localhost:8080/user/create',
                changeOrigin: true,
                rewrite: (path) => path.replace(/^\/api\/user\/create/, ''),
                ws: true
            },
            '/api/user/getAll': {
                target: 'http://localhost:8080/user/create',
                changeOrigin: true,
                rewrite: (path) => path.replace(/^\/api\/user\/getAll/, ''),
                ws: true
            },

        }
    }

})
