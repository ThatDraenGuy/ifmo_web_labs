import {createApi, fetchBaseQuery} from "@reduxjs/toolkit/dist/query/react";
import {RootState} from "../store";
import {API_BASE_PATH} from "../constants/constants";

export const api = createApi({
    baseQuery: fetchBaseQuery({
        baseUrl: API_BASE_PATH,
        prepareHeaders: ((headers, {getState}) => {
            const auth = (getState() as RootState).auth;
            if (auth && auth.token) {
                headers.set('authorization', `Bearer ${auth.token}`)
            }
        })
    }),
    tagTypes: ["Attempts"],
    endpoints: build => ({})
})
