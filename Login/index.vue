<script setup lang="ts">
import { ref } from 'vue';
import { ElMessage } from 'element-plus';
import { RouterLink, useRouter } from 'vue-router';
import 'element-plus/theme-chalk/el-message.css';
import { useUserStore } from '@/stores/userStore.ts';
import '../re_login.scss';
const userStore = useUserStore();
// 登录表单对象
const form = ref({
  account: '',
  password: '',
  agree: true
});
//  准备规则对象
const rules = {
  account: [{ required: true, message: '用户名不能为空', trigger: 'blur' }],
  password: [
    { required: true, message: '密码不能为空', trigger: 'blur' },
    { min: 6, max: 14, message: '密码长度为6~14', trigger: 'blur' }
  ],
  agree: [
    {
      validator: (rule, value, callback) => {
        // 自定义校验
        // 勾选通过，不勾选就不通过
        if (value) {
          callback();
        } else {
          callback(new Error('请勾选协议'));
        }
      }
    }
  ]
};

const formRef = ref(null);
const router = useRouter();

const doLogin = () => {
  const { account, password } = form.value;
  formRef.value.validate(async (vaild) => {
    if (vaild) {
      await userStore.getUserInfo({ account, password });
      // 1. 提示用户
      ElMessage({ type: 'success', message: '登录成功' });
      // 2. 跳转首页
      await router.replace({ path: '/' });
    }
  });
};
</script>

<template>
  <div>
    <header class="login-header">
      <div class="container m-top-20">
        <h1 class="logo">
          <RouterLink to="/">bug不打烊</RouterLink>
        </h1>
        <RouterLink class="entry" to="/">
          进入网站首页
          <i class="iconfont icon-angle-right"></i>
          <i class="iconfont icon-angle-right"></i>
        </RouterLink>
      </div>
    </header>
    <section class="login-section">
      <div class="wrapper">
        <nav>
          <a href="javascript:;">账户登录</a>
        </nav>
        <div class="account-box">
          <div class="form">
            <el-form :model="form" :rules="rules" label-position="right" label-width="60px" status-icon ref="formRef">
              <el-form-item prop="account" label="账户">
                <el-input v-model="form.account" />
              </el-form-item>
              <el-form-item prop="password" label="密码">
                <el-input v-model="form.password" show-password />
              </el-form-item>
              <el-form-item prop="agree" label-width="22px">
                <el-checkbox v-model="form.agree" size="large">我已同意隐私条款和服务条款</el-checkbox>
              </el-form-item>
              <el-button size="large" class="subBtn" @click="doLogin">登     录</el-button>
            </el-form>
            <RouterLink to="/register">没有账户？点击注册 </RouterLink>
          </div>
        </div>
      </div>
    </section>

    <footer class="login-footer">
      <div class="container">
        <p>
          <a href="javascript:;">关于我们</a>
          <a href="javascript:;">帮助中心</a>
          <a href="javascript:;">售后服务</a>
          <a href="javascript:;">配送与验收</a>
          <a href="javascript:;">商务合作</a>
          <a href="javascript:;">搜索推荐</a>
          <a href="javascript:;">友情链接</a>
        </p>
        <p>CopyRight &copy; BUG不打烊</p>
      </div>
    </footer>
  </div>
</template>

<style scoped lang="scss">
</style>
