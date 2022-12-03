import {createApi, fetchBaseQuery} from "@reduxjs/toolkit/dist/query/react";
import {RootState} from "../store";

export const api = createApi({
    baseQuery: fetchBaseQuery({
        baseUrl: "",
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
