import products from "@/core/store/features/products/slice";
import cartReducer from '@/core/features/cart/cartSlice';

const reducers = {
    products: products,
    cart: cartReducer,

};
export default reducers;
