import httpInstance from '@/utils/http.ts';
import { GoodDetail, HotGoodsRequest } from '@/api/model/detailModel.ts';

export function getDetail(id: string): Result<GoodDetail> {
  return httpInstance('/goods', { params: { id } });
}

export function getHotGoodsAPI({ id, type, limit = 3 }: HotGoodsRequest): Result<Good[]> {
  return httpInstance('/goods/hot', { params: { id, type, limit } });
}
//LHH
// 获取商品评论列表
export function fetchComments(productId: string): Promise<CommentResponse<Comment[]>> {
  return httpInstance(`/products/${productId}/comments`);
}

// 提交新的评论
export function submitComment(productId: string, commentData: CommentRequest): Promise<CommentResponse<Comment>> {
  return httpInstance.post(`/products/${productId}/comments`, commentData);
}