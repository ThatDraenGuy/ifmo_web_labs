import {api} from "./api";


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
                // responseHandler: (response) => {
                //     return response.text().then(value => {
                //         console.log(value)
                //         return value;
                //     });
                // }
            })
        })
    })
})

export const {useLoginMutation} = authApi