import {
    BaseQueryFn,
    createApi,
    FetchArgs,
    fetchBaseQuery,
    FetchBaseQueryError
} from "@reduxjs/toolkit/dist/query/react";
import {API_BASE_PATH} from "../constants/constants";

const baseQuery = fetchBaseQuery({
    baseUrl: API_BASE_PATH
})
const baseQueryWithAuthError: BaseQueryFn<
    string | FetchArgs,
    unknown,
    FetchBaseQueryError
    > = async (args, api, extraOptions) => {
    let result = await baseQuery(args, api, extraOptions)
    if (result.error && result.error.status == 401) {
        window.location.replace("/login")
    }
    return result
}


export const api = createApi({
    baseQuery: baseQueryWithAuthError,
    tagTypes: ["Attempts", "Auth"],
    endpoints: build => ({})
})
