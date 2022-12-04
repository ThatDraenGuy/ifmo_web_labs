import {api} from "./api";
import {logout} from "../slices/authSlice";
import {useAppDispatch} from "../hooks";
import {store} from "../store";


export interface User {
    id: number,
    username: string,
    roles: Array<string>
}

export interface LoginResponse {
    token: string,
    user: User
}

export interface LoginRequest {
    username: string,
    password: string
}

export const authApi = api.injectEndpoints({
    endpoints: build => ({
        login: build.mutation<LoginResponse, LoginRequest>({
            query: (credentials) => ({
                url: "/auth/login",
                method: "POST",
                body: credentials
            })
        }),
        logout: build.mutation<any,number>({
            query: (userId) => ({
                url: `/users/id/${userId}/logout`,
                method: "POST"
            }),
            transformResponse: (response) => {
                store.dispatch(logout())
                return response;
            }
        })
    })
})

export const {useLoginMutation, useLogoutMutation} = authApi