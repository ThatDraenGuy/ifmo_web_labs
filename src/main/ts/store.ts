import {combineReducers, configureStore} from "@reduxjs/toolkit";
import {api} from "./services/api";
import chooserReducer from "./slices/chooserSlice";


export const store = configureStore({
    reducer: combineReducers({
        chooser: chooserReducer,
        [api.reducerPath]: api.reducer
    }),
    middleware: (getDefaultMiddleware => getDefaultMiddleware().concat(api.middleware))
})


export type RootState = ReturnType<typeof store.getState>
export type AppDispatch = typeof store.dispatch