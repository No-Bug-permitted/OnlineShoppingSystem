import { Categories } from '@/api/model/categoryModel.ts';
//  商品详情
export interface GoodDetail {
  id: string;//商品id 跳转页面用
  name: string;//商品名字
  spuCode: string;//跟id 一样就行
  desc: string;//描述
  price: string;//价格
  oldPrice: string;//旧价格 
  brand: string;//品牌信息
  salesCount: string;//销售人气
  commentCount: string;//评价数量
  collectCount: string;//收藏数量
  mainPictures: [];//打开后的详细大图
  specs: [];//可选规格
  skus: [];//所有用户可选的规格情况 
  categories: Categories[];
  details: {//商品介绍图片，放下面的大图
    pictures: string[];
  };
}

// sku 信息
export interface sku {
  skuId: string;//唯一性编号
  specsText: string;//规格介绍，比如蓝色
}

// 热门商品请求
export type HotGoodsRequest = {
  id: string;
  type: number;
  limit: number;
};

//LHH
//评论
export interface Comment {
  id: string;
  username: string;
  text: string;
  rating: number;
  timestamp: string;
}

export interface CommentRequest {
  username: string;
  text: string;
  rating: number;
}

export interface CommentResponse<T> {
  data: T;
  status: string;
  message?: string;
}
