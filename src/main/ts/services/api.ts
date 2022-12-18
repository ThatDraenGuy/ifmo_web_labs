import {createApi, fetchBaseQuery} from "@reduxjs/toolkit/dist/query/react";
import {API_BASE_PATH} from "../constants/constants";

export const api = createApi({
    baseQuery: fetchBaseQuery({
        baseUrl: API_BASE_PATH,
    }),
    tagTypes: ["Attempts", "Auth"],
    endpoints: build => ({})
})
