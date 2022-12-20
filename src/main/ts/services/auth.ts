import {api} from "./api";


export interface User {
    id: number,
    username: string,
    roles: Array<string>
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
        login: build.mutation<User, LoginRequest>({
            query: (credentials) => ({
                url: "/auth/login",
                method: "POST",
                body: credentials
            }),
            invalidatesTags: (result, error) => error ? [] : ["Auth"]
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
            invalidatesTags: ["Auth"]
        }),
        usernameExists: build.query<boolean, string>({
            query: (username) => ({
                url: `/auth/exists/${username}`
            })
        }),
        getCurrentUser: build.query<User,void>({
            query: () => ({
                url: '/users/current',
                validateStatus: (response, result) => response.status==200
            }),
            providesTags: ["Auth"]
        })
    })
})

export const {useLoginMutation, useRegisterMutation, useLogoutMutation, useUsernameExistsQuery, useGetCurrentUserQuery} = authApi