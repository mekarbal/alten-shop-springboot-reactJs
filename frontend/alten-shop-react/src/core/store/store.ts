import {
    PreloadedState,
    combineReducers,
    configureStore,
} from '@reduxjs/toolkit';
import { api } from '@/core/store/api';
import { setupListeners } from '@reduxjs/toolkit/query';
import reducers from "@/core/store/features/reducers";
import {productsApi} from "@/core/store/features/products/apis";

export const rootReducer = combineReducers({
    ...reducers,
    [api.reducerPath]: api.reducer,
});

export type RootState = ReturnType<typeof rootReducer>;

export const setupStore = (preloadedState?: PreloadedState<RootState>) => {
    return configureStore({
        reducer: rootReducer,
        preloadedState,
        middleware: (getDefaultMiddleware) =>
            getDefaultMiddleware().concat(api.middleware, productsApi.middleware)
    });
};


export const store = setupStore();
setupListeners(store.dispatch);

export type AppStore = ReturnType<typeof setupStore>;
export type AppDispatch = AppStore['dispatch'];
