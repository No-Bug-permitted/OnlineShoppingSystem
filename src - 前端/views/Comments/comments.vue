<script setup lang="ts">
import { ref, onMounted, computed } from 'vue';
import { ElMessage } from 'element-plus';
import { useRoute } from 'vue-router';
import { fetchComments, submitComment } from '@/api/detail.ts';

// 模拟的假评论数据
const fakeComments = Array.from({ length: 20 }, (_, index) => ({
  username: `JK大魔王${index + 1}号`,
  text: `这是第 ${index + 1} 条假评论，内容随便写一下。`,
  rating: Math.floor(Math.random() * 5) + 1, // 随机评分 1-5
  timestamp: new Date().toLocaleDateString(),
}));

const route = useRoute();
const productId = ref(route.params.id); // 获取商品ID
const comments = ref(fakeComments); // 默认使用假评论数据
const newComment = ref({
  username: '当前用户', // 假设从登录信息中获取
  text: '',
  rating: 0,
});

// 分页相关的变量
const currentPage = ref(1); // 当前页数
const pageSize = 10; // 每页评论数量

// 计算当前页的评论
const currentComments = computed(() => {
  const start = (currentPage.value - 1) * pageSize;
  const end = start + pageSize;
  return comments.value.slice(start, end);
});

// 评论总数
const totalComments = computed(() => comments.value.length);

// 加载评论：先尝试从服务器获取，如果失败，则使用假数据
const loadComments = async () => {
  try {
    const response = await fetchComments(productId.value);
    comments.value = response.data; // 从服务器获取到的数据
  } catch (error) {
    console.error('Failed to load comments from server:', error);
    comments.value = fakeComments; // 使用假数据
  }
};

// 提交评论
const handleSubmit = async () => {
  if (newComment.value.text && newComment.value.rating > 0) {
    try {
      await submitComment(productId.value, newComment.value);
      ElMessage.success('评论提交成功');
      newComment.value.text = '';
      newComment.value.rating = 0;
      loadComments(); // 提交后刷新评论列表
    } catch (error) {
      ElMessage.error('提交失败，请稍后再试');
      console.error('Failed to submit comment:', error);
    }
  } else {
    ElMessage.error('请填写评论内容和评分');
  }
};

// 在组件挂载时加载评论
onMounted(() => {
  loadComments();
});
</script>

<template>
  <div class="comment-section">
    <el-card class="box-card">
      <div slot="header" class="clearfix">
        <span>商品评论</span>
      </div>
      <!-- 评论展示区，使用 flex 布局实现左右撑开 -->
      <div class="comment-list">
        <el-timeline v-if="currentComments.length" class="timeline">
          <el-timeline-item
            v-for="(comment, index) in currentComments"
            :key="index"
            :timestamp="comment.timestamp"
            placement="top"
          >
            <p>{{ comment.username }}</p>
            <el-rate v-model="comment.rating" disabled></el-rate>
            <p>{{ comment.text }}</p>
          </el-timeline-item>
        </el-timeline>
        <p v-else>暂无评论</p>
      </div>

      <!-- 评论输入区 -->
      <el-divider></el-divider>
      <div class="comment-form">
        <el-input
          type="textarea"
          v-model="newComment.text"
          placeholder="请输入您的评论"
        />
        <el-rate v-model="newComment.rating" :max="5"></el-rate>
        <el-button type="primary" @click="handleSubmit">提交评论</el-button>
      </div>

      <!-- 分页组件 -->
      <el-pagination
        v-if="totalComments > pageSize"
        :current-page="currentPage"
        :page-size="pageSize"
        :total="totalComments"
        @current-change="currentPage = $event"
        layout="total, prev, pager, next, jumper"
        :page-size-options="[10, 20, 30]"
        :i18n="{
          total: '共{total}条',
          goto: '跳转至',
        }"
      />
    </el-card>
  </div>
</template>

<style scoped>
.comment-section {
  width: 100%;
  max-width: 800px; /* 增加最大宽度 */
  margin: 0 auto;
}

.comment-list {
  display: flex;
  flex-direction: column;
  gap: 10px;
}

.timeline {
  width: 100%;
  margin: 0;
}

.comment-form {
  display: flex;
  flex-direction: column;
  gap: 10px;
}

.el-pagination {
  margin-top: 20px;
  text-align: center;
}
</style>
