import { createRouter, createWebHistory } from 'vue-router';

import Login from '@/views/Login/index.vue';
import Layout from '@/views/Layout/index.vue';
import Home from '@/views/Home/index.vue';
import Category from '@/views/Category/index.vue';
import SubCategory from '@/views/SubCategory/index.vue';
import Detail from '@/views/Detail/index.vue';
import CartList from '@/views/CartList/index.vue';
import Checkout from '@/views/Checkout/index.vue';
import Pay from '@/views/Pay/index.vue';
import PayBack from '@/views/Pay/PayBack.vue';
import Member from '@/views/Member/index.vue';
import UserInfo from '@/views/Member/components/UserInfo.vue';
import UserOrder from '@/views/Member/components/UserOrder.vue';
import Register from '@/views/Register/index.vue';  // 导入注册页面
import Search from '@/views/Search/index.vue';
import Merchant from '@/views/Merchant/index.vue';
import Comments from '@/views/Comments/comments.vue';  // 导入评论页面
const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  // path和component对应关系的位置
  routes: [
    {
      path: '/',
      component: Layout,
      children: [
        {
          path: '',
          component: Home
        },
        {
          path: 'category/:id',
          component: Category
        },
        {
          path: 'category/sub/:id',
          component: SubCategory
        },
        {
          path: 'detail/:id',
          component: Detail
        },
        {
          path: 'cartlist',
          component: CartList
        },
        {
          path: 'checkout',
          component: Checkout
        },
        {
          path: 'pay',
          component: Pay
        },
        {
          path: 'paycallback',
          component: PayBack
        },
        {
          path: 'member',
          component: Member,
          redirect: 'member/user',
          children: [
            {
              path: 'user',
              component: UserInfo
            },
            {
              path: 'order',
              component: UserOrder
            }
          ]
        },
        // LHH	评论页面的路由配置
        {
          path: 'product/:id/comments',
          name: 'CommentSection',
          component: Comments
        }
      ]
    },
    {
      path: '/login',
      component: Login
    },
    {
      path: '/register', // 这里的路径是你希望跳转到注册页面的路径
      component: Register
    },
    {
      path:'/search',
      component: Search
    },
    {
      path:'/merchant',
      component: Merchant
    }
  ],
  scrollBehavior() {
    return {
      top: 0
    };
  }
});

export default router;
