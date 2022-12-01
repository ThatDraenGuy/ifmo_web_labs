import {createApi, fetchBaseQuery} from "@reduxjs/toolkit/dist/query/react";


export const api = createApi({
    baseQuery: fetchBaseQuery({
        baseUrl: ""
    }),
    tagTypes: ["Attempts"],
    endpoints: build => ({})
})
