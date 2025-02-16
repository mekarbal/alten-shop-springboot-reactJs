import { createApi, fetchBaseQuery } from '@reduxjs/toolkit/query/react';

export const api = createApi({
    reducerPath: 'api',
    baseQuery: fetchBaseQuery({
        baseUrl:import.meta.env.REACT_APP_REST_API_URL ||  "https://fakestoreapi.com",
    }),
    keepUnusedDataFor: 30,
    endpoints: () => ({}),

});



