import {api} from "./api";


export interface User {
    username: string,
    _links: {
        self: string,
        attempts: string
    }
}

export interface UserResponse {

}

export interface LoginRequest {
    username: string,
    password: string
}

const authApi = api.injectEndpoints({
    endpoints: build => ({
        login: build.mutation<UserResponse, LoginRequest>({
            query: (credentials) => ({
                url: "/auth/login/process",
                method: "POST",
                body: credentials
            })
        })
    })
})

export const {useLoginMutation} = authApi