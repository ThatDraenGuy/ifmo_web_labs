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

export interface RegisterResponse {}
export interface RegisterRequest {
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
        register: build.mutation<RegisterResponse, RegisterRequest>({
            query: (request) => ({
                url: "/auth/register",
                method: "POST",
                body: request
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
        }),
        usernameExists: build.query<boolean, string>({
            query: (username) => ({
                url: `/users/exists/${username}`
            })
        })
    })
})

export const {useLoginMutation, useRegisterMutation, useLogoutMutation, useUsernameExistsQuery} = authApi