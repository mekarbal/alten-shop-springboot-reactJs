import { createSlice } from '@reduxjs/toolkit';

export interface IProductsSlice {
  items: any[];
}

const initialState: IProductsSlice = {
  items: [],
};

export const productsSlice = createSlice({
  name: 'products',
  initialState,
  reducers: {},
});

export default productsSlice.reducer;




