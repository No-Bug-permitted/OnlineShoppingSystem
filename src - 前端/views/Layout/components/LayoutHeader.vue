<template>
  <header class="app-header">
    <div class="container">
      <h1 class="logo">
        <RouterLink to="/">BUG不打烊
        </RouterLink>
      </h1>
      <HeaderNav />
    
      <div class="search">
        <input 
          v-model="searchQuery" 
          type="text" 
          placeholder="请输入搜索内容" 
          aria-label="搜索框"
        />
        <el-button type="primary"  color="black" round @click="search">搜索</el-button>
 

      </div>
      <!-- 头部购物车 -->
      <HeaderCart />
    </div>
  </header>
</template>

<script setup lang="ts">
import { ref } from 'vue';
import { useRouter } from 'vue-router';  // 引入 vue-router 用于页面跳转
import HeaderNav from '@/views/Layout/components/HeaderNav.vue';
import HeaderCart from '@/views/Layout/components/HeaderCart.vue';
import 'element-plus/dist/index.css';  // 引入 Element Plus 样式
import { useUserStore } from '@/stores/userStore';
import { ElMessage } from 'element-plus'; 
// 定义搜索框的关键词
const searchQuery = ref('');  

const router = useRouter();
const userStore = useUserStore();
// showMessage 方法：显示一个不需要确认的警告
const showMessage = () => {
  ElMessage({
    message: '请先登录再进行搜索',
    type: 'warning',  // 类型可以是 'success', 'warning', 'info', 'error'
    duration: 3000  // 消息显示时长，单位为毫秒，3000ms = 3秒
  });
};
// 搜索函数，点击按钮后跳转到搜索结果页面
const search = () => {
  if (searchQuery.value.trim()&&userStore.userInfo.token) {
    router.push({ path: '/search', query: { keyword: searchQuery.value } });  // 跳转到搜索结果页，并传递关键词
  }else{
    showMessage();
    router.push('/login');
  }
};
</script>

<style scoped lang="scss">
.app-header {
  background: #fff;

  .container {
    display: flex;
    align-items: center;
  }

  .logo {
    width: 200px;

    a {
      display: block;
      height: 132px;
      width: 100%;
      text-indent: -9999px;
      background: url('@/assets/images/logo.png') no-repeat center 18px / contain;
    }
  }

  .search {
    width: 170px;
    height: 32px;
    position: relative;
    border-bottom: 1.2px solid #b3b0b0;
    line-height: 32px;
    display: flex;
    align-items: center;
    .el-icon-search{
      color: #000000;
      font-size: 100px;
    }
    .icon-search {
      font-size: 18px;
      margin-left: 5px;
    }

    input {
      width: 140px;
      padding-left: 5px;
      color: #000000;
      border: none;
      outline: none;
    }

    
  }

  .cart {
    width: 50px;

    .curr {
      height: 32px;
      line-height: 32px;
      text-align: center;
      position: relative;
      display: block;

      .icon-cart {
        font-size: 22px;
      }

      em {
        font-style: normal;
        position: absolute;
        right: 0;
        top: 0;
        padding: 1px 6px;
        line-height: 1;
        background: $helpColor;
        color: #fff;
        font-size: 12px;
        border-radius: 10px;
        font-family: Arial;
      }
    }
  }
}
</style>
