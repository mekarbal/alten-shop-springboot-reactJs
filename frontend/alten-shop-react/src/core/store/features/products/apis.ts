import {api} from "@/core/store/api";

export const productsApi = api.injectEndpoints({
  endpoints: (builder) => ({
    getProducts: builder.query<any, Record<string, any>>({
      query: (params) => ({
        url: `/products`,
        params,
      }),
    }),
  }),
});
export const { useGetProductsQuery } =
    productsApi;