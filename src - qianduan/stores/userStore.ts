import { defineStore } from 'pinia';
import { ref } from 'vue';
import { LoginAPI, RegisterAPI } from '@/api/user.ts'; // 引入 RegisterAPI
import { UserInfo } from '@/api/model/userModel.ts';
import { useCartStore } from '@/stores/cartStore.ts';
import { mergeCartAPI } from '@/api/cart.ts';

export const useUserStore = defineStore(
  'user',
  () => {
    const userInfo = ref({} as Partial<UserInfo>);
    const cartStore = useCartStore();

    // 登录方法，获取用户信息
    const getUserInfo = async ({ account, password }: { account: string; password: string }) => {
      const res = await LoginAPI({ account, password });
      userInfo.value = res.result;
      // 合并购物车
      await mergeCartAPI(
        cartStore.cartList.map((item) => {
          return {
            skuId: item.skuId,
            selected: item.selected,
            count: item.count
          };
        })
      );
      await cartStore.updateNewList();
    };

    // 注册方法
    const register = async ({ phone,account,user_level, password }: { phone:string;account: string;user_level:string; password: string }) => {
      try {
        // 调用 RegisterAPI 进行用户注册
        const res = await RegisterAPI({ phone,account,user_level, password });

        if (res.code === "200") {  // 根据返回的 code 判断是否成功
          // 注册成功
          userInfo.value = res.result;  // 将注册返回的用户信息保存到 userInfo
          return { success: true, message: '注册成功' };
        } else {
          // 注册失败
          return { success: false, message: res.msg || '注册失败，请重试' };
        }
      } catch (error) {
        console.error('注册失败', error);
        throw error;  // 如果请求失败，抛出错误
      }
    };

    // 清除用户信息
    const clearUserInfo = () => {
      userInfo.value = {};
      cartStore.clearCart();
    };

    return {
      userInfo,
      getUserInfo,
      register,  // 暴露注册方法
      clearUserInfo
    };
  },
  {
    persist: true
  }
);
