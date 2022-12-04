import {combineReducers, configureStore} from "@reduxjs/toolkit";
import {api} from "./services/api";
import chooserReducer from "./slices/chooserSlice";
import paginationReducer from "./slices/paginationSlice";
import authReducer from "./slices/authSlice"


export const store = configureStore({
    reducer: {
        [api.reducerPath]: api.reducer,
        chooser: chooserReducer,
        pagination: paginationReducer,
        auth: authReducer,
    },
    middleware: (getDefaultMiddleware) => getDefaultMiddleware().concat(api.middleware)
})


export type RootState = ReturnType<typeof store.getState>
export type AppDispatch = typeof store.dispatch