<script setup lang="ts">
import HomePanel from './HomePanel.vue';
import { onMounted, ref } from 'vue';
import { getGoodsAPI } from '@/api/home.ts';
import GoodsItem from '@/views/Home/components/GoodsItem.vue';
import { ProductResult } from '@/api/model/homeModel.ts';

const goodsProduct = ref([] as ProductResult[]);
const getGoods = async () => {
  const res = await getGoodsAPI();
  goodsProduct.value = res.result;
};

onMounted(() => getGoods());
</script>

<template>
  <div class="home-product">
    <HomePanel :title="cate.name" v-for="cate in goodsProduct" :key="cate.id" sub-title="">
      <div class="box">
        <ul class="goods-list">
          <li v-for="good in cate.goods" :key="good.id">
            <GoodsItem :good="good" />
          </li>
        </ul>
      </div>
    </HomePanel>
  </div>
</template>

<style scoped lang="scss">
.home-product {
  background: #fff;
  margin-top: 10px;
  .sub {
    margin-bottom: 2px;

    a {
      padding: 2px 12px;
      font-size: 16px;
      border-radius: 4px;

      &:hover {
        background: $xtxColor;
        color: #fff;
      }

      &:last-child {
        margin-right: 80px;
      }
    }
  }

  .box {
    display: flex;
    width: 1240px;

    .goods-list {
      width: 1240px;
      display: inline-flex;
      margin-left: 30px;
      margin-bottom: 10px;
      flex-wrap: wrap;

      li {
        width: 220px;
        height: 220px;
        margin-left: 15px;
        margin-bottom: 60px;

        &:nth-last-child(-n + 4) {
          margin-bottom: 0;
        }

        &:nth-child(4n) {
          margin-right: 0;
        }
      }
    }
  }
}
</style>
