import httpInstance from '@/utils/http.ts';
import { Category, CategoryChild, NavigationDataRequest, NavigationGoodList } from '@/api/model/categoryModel.ts';

//获取分类数据
export function getCategoryAPI(id: string): Result<Category> {
  return httpInstance('/category', { params: { id } });
}

// 获取二级分类列表数据
export function getCategoryFilterAPI(id: string): Result<CategoryChild> {
  return httpInstance('/category/sub/filter', { params: { id } });
}

// 获取导航数据
export function getSubCategoryAPI(data: NavigationDataRequest): Result<NavigationGoodList> {
  return httpInstance('/category/goods/temporary', { method: 'POST', data });
}

interface GoodsByKeywordRequest {
  keyword: string;
}

interface GoodsItemType {
  id: number;
  name: string;
  desc: string;
  price: number;
  picture: string;
}


export function getGoodsByKeywordAPI(data: GoodsByKeywordRequest): Result<GoodsItemType[]> {
  return httpInstance('/search/goods', { 
    method: 'POST', 
    data 
  });
}
