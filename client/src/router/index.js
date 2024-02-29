import Vue from 'vue'
import VueRouter from 'vue-router'
import MainView from "@/views/MainView.vue";

Vue.use(VueRouter)

const router = new VueRouter({
    mode: 'history',
    base: import.meta.env.BASE_URL,
    routes: [
        {
            path: "/",
            redirect: "/menu"
        },
        {
            path: '/',
            component: {
                render(c) { return c('router-view') }
            },
            children: [
                {
                    path: '/menu',
                    name: 'menu',
                    component: MainView,
                    redirect: { name: 'validation'},
                    children: [
                        {
                            path: '/validate',
                            name: 'validation',
                            component: () => import('../views/Validations.vue')
                        },
                        {
                            path: '/input/text',
                            name: 'inputText',
                            component: () => import('../views/input_validations/InputText.vue')
                        }
                    ]
                },
                {
                    path: '/:catchAll(.*)',
                    name: 'NotFound',
                    component: () => import('../views/NotFound.vue')
                }
            ]
        },



    ]
})

export default router
