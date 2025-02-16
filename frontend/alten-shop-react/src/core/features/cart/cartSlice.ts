
import { createSlice, PayloadAction } from '@reduxjs/toolkit';


const initialState: CartState = {
    cartItems: [],
    totalItems: 0,
};

const cartSlice = createSlice({
    name: 'cart',
    initialState,
    reducers: {
        addToCart: (state, action: PayloadAction<CartItem>) => {
            const existingProduct = state.cartItems.find(item => item.id === action.payload.id);
            if (existingProduct) {
                existingProduct.qty += 1;
            } else {
                state.cartItems.push({ ...action.payload, qty: 1 });
            }
            state.totalItems += 1;
        },
        decreaseOrRemove: (state, action: PayloadAction<string>) => {
            const productIndex = state.cartItems.findIndex(item => item.id === action.payload);
            if (productIndex > -1) {
                const product = state.cartItems[productIndex];
                if (product.qty > 1) {
                    product.qty -= 1;
                } else {
                    state.cartItems.splice(productIndex, 1);
                }
                state.totalItems -= 1;
            }
        },
    },
});

export const { addToCart, decreaseOrRemove } = cartSlice.actions;
export default cartSlice.reducer;
