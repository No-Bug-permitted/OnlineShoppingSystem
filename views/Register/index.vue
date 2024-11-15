<script setup lang="ts">
import { ref } from 'vue';
import { ElMessage } from 'element-plus';
import { useRouter } from 'vue-router';
import { useUserStore } from '@/stores/userStore.ts';
import '../re_login.scss';

const userStore = useUserStore();
const router = useRouter();

const form = ref({
  phone:'',
  account:'',
  user_level: '', // 绑定 user_level
  password: '',
  confirmPassword: ''
});

const rules = {
  account: [{ message: '用户名不能为空', trigger: 'blur' }],
  phone:[{ 
      required: true, 
      message: '手机号不能为空', 
      trigger: 'blur' 
    },
    {
      pattern: /^1[3-9]\d{9}$/, 
      message: '请输入有效的手机号', 
      trigger: 'blur' 
    }],
  password: [
    { required: true, message: '密码不能为空', trigger: 'blur' },
    { min: 6, max: 14, message: '密码长度为6~14', trigger: 'blur' }
  ],
  user_level: [{ required: true, message: '账号身份不能为空', trigger: 'blur' }],
  confirmPassword: [
    { required: true, message: '请确认密码', trigger: 'blur' },
    { validator: (rule, value, callback) => {
      if (value !== form.value.password) {
        callback(new Error('密码和确认密码不一致'));
      } else {
        callback();
      }
    }}
  ]
};

const formRef = ref(null);

const doRegister = () => {
  const { phone,account,user_level, password} = form.value;
  formRef.value.validate(async (valid) => {
    if (valid) {
      console.log(user_level);
      try {
        const res = await userStore.register({ phone,account,user_level, password }); // 传递 user_level
        if (res.success) {
          ElMessage({ type: 'success', message: '注册成功！' });
          router.push('/login');  // 注册成功后跳转到登录页面
        } else {
          ElMessage({ type: 'error', message: res.message || '注册失败，请重试' });
        }
      } catch (error) {
        ElMessage({ type: 'error', message: '注册过程中出现错误' });
      }
    }
  });
};

// 下拉选项和选中的值（设置对应的 1 和 2）
const options = ref([
  { value: '0', label: '消费者' },  // 0 代表消费者
  { value: '1', label: '商家' }     // 1 代表商家
]);

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
          <a href="javascript:;">注册账户</a>
        </nav>
        <div class="account-box">
          <div class="form">
            <el-form :model="form" :rules="rules" label-position="left" label-width="80px" status-icon ref="formRef">
              <el-form-item prop="phone" label="手机号" class="form-item">
                <el-input v-model="form.phone" placeholder="请输入有效手机号" />
              </el-form-item>
              <el-form-item prop="account" label="用户名" class="form-item">
                <el-input v-model="form.account" placeholder="若空则默认手机号" />
              </el-form-item>
              <el-form-item prop="user_level" label="账号身份" class="form-item">
                <el-select v-model="form.user_level" placeholder="请选择">
                 <el-option
                   v-for="item in options"
                   :key="item.value"
                   :label="item.label"
                   :value="item.value">  
                 </el-option>
                </el-select>
              </el-form-item>
              <el-form-item prop="password" label="密码" class="form-item">
                <el-input v-model="form.password" type="password" placeholder="6-14位密码"/>
              </el-form-item>
              <el-form-item prop="confirmPassword" label="确认密码" class="form-item">
                <el-input v-model="form.confirmPassword" type="password" />
              </el-form-item>
              <el-button size="large" class="subBtn" @click="doRegister">注     册</el-button>
            </el-form>
          </div>
        </div>
      </div>
    </section>
    
  </div>
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
</template>

<style scoped>
/* 样式部分保持不变 */
</style>
