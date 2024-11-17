// 添加购物车请求
export interface InsertCartRequest {
  skuId: string;
  count: number;
}

//  购物车商品
export interface CartItem {
  id: string;//用于排布
  skuId: string;//唯一标识
  name: string;//商品名字
  picture: string;//图片
  price: string;//价格
  selected: boolean;//是否被选择，用于样式
  stock: number;//库存
  count: number;//放入购物车的数量
}

// 合并购物车请求
export interface MergeCartRequest {
  skuId: string;
  selected: boolean;
  count: number;
}
