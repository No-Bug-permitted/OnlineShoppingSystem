<template>
    <LayoutFixed />
   <LayoutNav />
   <LayoutHeader />
   <RouterView />
   
    <div class="search-results">
        <div class="search-results-header">
      <h2>"{{ keyword }}"的搜索结果如下：</h2>
    </div>
      <!-- 如果没有找到商品，显示提示 -->
      <div style="font-size: 20px;" v-if="goods.length === 0">没有找到相关商品</div>
  
      <!-- 否则显示商品列表 -->
      <div v-else>
        <GoodsItem v-for="good in goods" :key="good.id" :good="good" />
      </div>
    </div>
    <LayoutFooter />
  </template >
  
  <script setup lang="ts">
  import { ref, onMounted } from 'vue';
  import { useRoute } from 'vue-router';  // 用于获取路由参数
  import { getGoodsByKeywordAPI,GoodsItemType} from '@/api/category';  // 导入 src/api/category.ts 文件中的接口函数
  import GoodsItem from '../Home/components/GoodsItem.vue';  // 引入商品组件来渲染商品
  import LayoutNav from '../Layout/components/LayoutNav.vue';
  import LayoutHeader from '../Layout/components/LayoutHeader.vue';
  import LayoutFooter from '../Layout/components/LayoutFooter.vue';
  import LayoutFixed from '../Layout/components/LayoutFixed.vue';
  import { useCategoryStore } from '@/stores/categoryStore.ts';
const categoryStore = useCategoryStore();

onMounted(() => categoryStore.getCategory());

  const route = useRoute();  // 获取路由对象
  const keyword = ref('');  // 存储当前的搜索关键词
  const goods = ref<GoodsItemType[]>([]);  // 显式声明 goods 数组的类型为 GoodsItemType[]
  
  keyword.value = route.query.keyword as string;  // 从路由查询参数中获取 keyword
  
  // 根据关键词请求商品数据
  const getGoodsByKeyword = async (keyword: string) => {
    try {
      const response = await getGoodsByKeywordAPI({ keyword });  // 使用从 src/api/category.ts 中导入的 API 函数
      goods.value = response.items;  // 假设返回的数据格式是 { items: [...] }
    } catch (error) {
      console.error('商品数据获取失败', error);
    }
  };
  
  // 在页面加载时，获取商品数据
  onMounted(() => {
    if (keyword.value) {
      getGoodsByKeyword(keyword.value);  // 如果有关键词，执行搜索
    }
  });
  </script>
  
  <style scoped lang="scss">
   .search-results {
    display: flex;
    flex-direction: column;
    align-items: center;
    padding: 20px;
    width: 100%;
  }

  .search-results-header {
    position: sticky;
    top: 0;          // 固定在顶部
    width: 1240px;   // 容器宽度固定为 1240px
    // background-color: white;   // 确保背景不透明
    padding: 10px 0;           // 内边距
    z-index: 10;      // 确保它在最上面
    // box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);  // 阴影效果
    text-align: left;  // 文字左对齐
    margin: 0 auto;  // 居中对齐
  }

  h2 {
    font-size: 20px;
    margin: 0;
    width: 100%;   // 确保 h2 占满父容器宽度
  }

  .goods-item {
    display: flex;
    flex-wrap: wrap;
    gap: 16px;
    justify-content: center;  // 让商品项水平居中
    width: 100%;  // 确保商品列表自适应宽度
    max-width: 1240px;  // 限制最大宽度为 1240px
  }
  </style>
  